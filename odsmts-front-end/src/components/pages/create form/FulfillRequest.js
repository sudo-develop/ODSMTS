import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { fetchRequestById, getAvailableDrugCount, fulfillRequest } from '../../../api';
import '../../styles/connect-hospital-style.css';

const FulfillRequest = () => {
  const { requestId } = useParams();
  const navigate = useNavigate();
  const { state } = useLocation();

  const [requestDetails, setRequestDetails] = useState(state?.requestDetails || null);
  const [fulfillQuantity, setFulfillQuantity] = useState('');
  const [status, setStatus] = useState('');
  const [isLoading, setIsLoading] = useState(!state?.requestDetails);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [availableCount, setAvailableCount] = useState('');
  const [showPopup, setShowPopup] = useState(false);

  const token = useSelector((state) => state.user.token);
  const currentHospital = useSelector((state) => state.user.hospitalDetails);
  const hospitalId = useSelector((state) => state.user.hospitalId);

  const drugId = requestDetails?.drugId;
  const drugFormId = requestDetails?.drugFormId;
  const remainingQty = requestDetails
    ? requestDetails.quantity - (requestDetails.fulfilledQuantity || 0)
    : 0;
  const maxFulfillQty = Math.min(remainingQty, availableCount || 0);

  const fetchAvailability = async () => {
    if (!drugId || !drugFormId || !hospitalId) return;
    try {
      const countResponse = await getAvailableDrugCount(
        drugId,
        drugFormId,
        hospitalId,
        token
      );
      setAvailableCount(countResponse.availableCount);
    } catch (error) {
      setStatus(`Error fetching availability: ${error.message}`);
    }
  };

  const fetchRequestDetails = async () => {
    try {
      const requestData = await fetchRequestById(requestId, token);
      setRequestDetails(requestData);
    } catch (error) {
      setStatus(`Error: ${error.message}`);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    if (!state?.requestDetails) {
      fetchRequestDetails();
    } else {
      setIsLoading(false);
    }
  }, [requestId, token, state]);

  useEffect(() => {
    fetchAvailability();
  }, [drugId, drugFormId, hospitalId, token]);

  const handleFulfillSubmit = async () => {
    if (!fulfillQuantity || isNaN(fulfillQuantity) || fulfillQuantity <= 0) {
      setStatus('Please enter a valid positive quantity');
      return;
    }
  
    if (availableCount === 0) {
      setStatus('No available stock to fulfill this request');
      return;
    }
  
    if (Number(fulfillQuantity) > maxFulfillQty) {
      setStatus(`Quantity exceeds allowed limit. Max allowed is ${maxFulfillQty}`);
      return;
    }
  
    if (!requestDetails) {
      setStatus('Request details not loaded');
      return;
    }
  
    setIsSubmitting(true);
    setStatus('');
  
    try {
      const payload = {
        requestId: requestDetails.requestId,
        fromHospitalId: hospitalId,
        toHospitalId: requestDetails.createdBy,
        fulfilledQuantity: Number(fulfillQuantity),
      };
  
      await fulfillRequest(payload, token);
  
      // Refresh request details & availability
      await fetchRequestDetails();
      await fetchAvailability();
  
      setStatus('Fulfillment submitted successfully!');
      setShowPopup(true);
  
      setTimeout(() => {
        setShowPopup(false);
        setFulfillQuantity('');
        navigate('/hospital-dashboard'); // Navigate after popup
      }, 5000);
    } catch (error) {
      setStatus(`Error: ${error.message || 'Failed to fulfill request'}`);
    } finally {
      setIsSubmitting(false);
    }
  };
  

  const handleQuantityChange = (e) => {
    const value = e.target.value;
    if (value === '') {
      setFulfillQuantity('');
      return;
    }
    const numericValue = Number(value);
    if (numericValue > 0 && numericValue <= maxFulfillQty) {
      setFulfillQuantity(numericValue);
    } else if (numericValue > maxFulfillQty) {
      setFulfillQuantity(maxFulfillQty);
    }
  };

  if (isLoading) {
    return <div className="loading">Loading request details...</div>;
  }

  return (
    <div className="connect-container">
      <button className="back-button" onClick={() => navigate('/hospital-dashboard')}>
        &larr; Back to Dashboard
      </button>
      <button className="back-button" onClick={() => navigate('/connect/hospital')}>
        &larr; Back to Connect
      </button>

      <div className="request-details">
        <h2>Fulfill Request #{requestDetails?.requestId}</h2>
        <div className="details-grid">
          <div className="detail-item">
            <span>Hospital:</span>
            <span>{requestDetails?.hospitalName}</span>
          </div>
          <div className="detail-item">
            <span>Drug:</span>
            <span>{requestDetails?.drugName}</span>
          </div>
          <div className="detail-item">
            <span>Form:</span>
            <span>{requestDetails?.formName}</span>
          </div>
          <div className="detail-item">
            <span>Requested Qty:</span>
            <span>{requestDetails?.quantity}</span>
          </div>
          <div className="detail-item">
            <span>Remaining Qty:</span>
            <span>
              {requestDetails.quantity - (requestDetails.fulfilledQuantity || 0)}
            </span>
          </div>
          <div className="detail-item">
            <span>Status:</span>
            <span className={`status-${requestDetails?.status?.toLowerCase()}`}>
              {requestDetails?.status}
            </span>
          </div>
          <div className="detail-item">
            <span>Available Qty:</span>
            <span>
              {availableCount !== null ? availableCount : 'Loading availability...'}
            </span>
          </div>
        </div>
      </div>

      <div className="email-form">
        <div className="form-group">
          <label>Fulfilling Hospital:</label>
          <input
            type="text"
            value={currentHospital?.name || 'Our Hospital'}
            readOnly
            className="email-input"
          />
        </div>

        <div className="form-group">
          <label>Quantity to Fulfill:</label>
          <input
            type="number"
            value={fulfillQuantity}
            onChange={handleQuantityChange}
            className="email-input"
            min="1"
            max={maxFulfillQty}
            disabled={availableCount === 0}
          />
          <p className="limit-hint">Max Quantity you can fulfill: {maxFulfillQty}</p>
        </div>

        <div className="button-group">
          <button
            className="cancel-button"
            onClick={() => navigate('/connect/hospital')}
            disabled={isSubmitting}
          >
            Cancel
          </button>

          <button
            className="fulfill-button"
            onClick={handleFulfillSubmit}
            disabled={isSubmitting || !fulfillQuantity || availableCount === 0}
          >
            {isSubmitting ? 'Submitting...' : 'Submit Fulfillment'}
          </button>
        </div>

        {status && (
          <div className={`status ${status.includes('successfully') ? 'success' : 'error'}`}>
            {status}
          </div>
        )}

        {showPopup && (
          <div className="popup-success">
            ✅ Fulfillment Successful! Available Quantity: {availableCount}
          </div>
        )}
      </div>
    </div>
  );
};

export default FulfillRequest;
