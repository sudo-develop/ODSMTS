
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { fetchRequestById, fetchUserEmailsByHospital } from '../../../api';
import emailjs from '@emailjs/browser';
import '../../styles/connect-hospital-style.css';

emailjs.init('8kl9DOoi2GW1uDDTT');

const ConnectHospitals = () => {
  const { state } = useLocation();
  const navigate = useNavigate();
  const [requestDetails, setRequestDetails] = useState(null);
  const [hospitalEmails, setHospitalEmails] = useState([]);
  const [selectedEmails, setSelectedEmails] = useState([]);
  const [message, setMessage] = useState('');
  const [status, setStatus] = useState('');
  const [isLoading, setIsLoading] = useState(true);
  const [isSending, setIsSending] = useState(false);

  // Redux values
  const userEmail = useSelector(state => state.user.email);
  const token = useSelector(state => state.user.token);
  const requestId = state?.requestId;
  const hospitalDetails = useSelector(state => state.user.hospitalDetails);

  // Email validation helper
  const validateEmail = (email) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  };

  useEffect(() => {
    if (!requestId) {
      navigate('/hospital-dashboard');
      return;
    }

    const fetchData = async () => {
      try {
        const requestData = await fetchRequestById(requestId, token);
        setRequestDetails(requestData);
        
        if (requestData?.createdBy) {
          const emails = await fetchUserEmailsByHospital(requestData.createdBy, token);
          const validEmails = emails.filter(email => validateEmail(email));
          
          setHospitalEmails(validEmails);
          setSelectedEmails(validEmails);

          if (validEmails.length === 0) {
            setStatus('No valid recipient emails found for this hospital.');
          }
        }
      } catch (error) {
        setStatus(`Error: ${error.message}`);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [requestId, token, navigate]);

  // Email selection handlers
  const handleEmailSelection = (email) => {
    setSelectedEmails(prev => 
      prev.includes(email) 
        ? prev.filter(e => e !== email) 
        : [...prev, email]
    );
  };
  const handleFulfill = () => {
    navigate(`/fulfill/${requestId}`, { 
      state: { requestDetails } // Pass request data to next page
    });
  };

  const selectAllEmails = () => setSelectedEmails(hospitalEmails);
  const deselectAllEmails = () => setSelectedEmails([]);

const handleSendEmail = async () => {
    // Trim and validate emails more rigorously
    const validEmails = selectedEmails
      .map(email => email.trim()) // Trim whitespace first
      .filter(email => {
        const isValid = validateEmail(email);
        // Additional check for EmailJS requirements
        if (!isValid) console.warn('Invalid email filtered out:', email);
        return isValid;
      });
  
    // Enhanced validation for message content
    if (!message.trim() || message.trim().length < 10) {
      setStatus('Message must contain at least 10 characters');
      return;
    }
  
    // Verify we have valid recipients after trimming
    if (validEmails.length === 0) {
      setStatus('No valid recipients selected. Check email formats.');
      return;
    }
  
    setIsSending(true);
    setStatus('');
  
    try {
      const emailPromises = validEmails.map(email => {
        // Verify critical parameters before sending
        if (!email || !userEmail) {
          console.error('Missing email parameters:', { email, userEmail });
          return Promise.reject('Invalid email parameters');
        }
  
        // Prepare template parameters with fallbacks
        const templateParams = {
          sender_hospital: hospitalDetails?.name || 'Unknown Hospital',
          to_email: email, // Verified match with EmailJS template
          from_email: userEmail.includes('@') ? userEmail : 'no-reply@medicalapp.com',
          message: message.trim(),
          request_id: requestDetails?.requestId || 'N/A',
          hospital_name: requestDetails?.hospitalName || 'Unknown Hospital',
          drug_name: requestDetails?.drugName || 'Unspecified Drug',
          form_name: requestDetails?.formName || 'Unknown Form',
          quantity: requestDetails?.quantity ?? 0,
          fulfilled_quantity: requestDetails?.fulfilledQuantity ?? 0,
          status: requestDetails?.status || 'Unknown Status',
          request_date: requestDetails?.requestDate 
            ? new Date(requestDetails.requestDate).toLocaleDateString()
            : 'Date not available'
        };
  
        // Debugging: Log final parameters
        console.log('Sending email to:', email, 'with params:', templateParams);
  
        return emailjs.send(
          'service_xr7zx7r',
          'template_73jb0hp',
          templateParams
        );
      });
  
      // Await all promises with error catching per email
      const results = await Promise.allSettled(emailPromises);
      
      // Handle partial successes
      const failedEmails = results
        .filter(result => result.status === 'rejected')
        .map((result, index) => validEmails[index]);
  
      if (failedEmails.length > 0) {
        throw new Error(`Failed to send to: ${failedEmails.join(', ')}`);
      }
  
      setStatus(`Successfully sent to ${validEmails.length} recipients!`);
      // setTimeout(() => navigate('/hospital-dashboard'), 3000);
    } catch (error) {
      // Enhanced error reporting
      const errorMessage = error.message || error.text || 'Unknown error occurred';
      console.error('Full email error:', error);
      setStatus(`Error: ${errorMessage}. Check console for details.`);
    } finally {
      setIsSending(false);
    }
  };

  if (isLoading) {
    return <div className="loading">Loading request details...</div>;
  }

  return (
    <div className="connect-container">
      <button 
        className="back-button"
        onClick={() => navigate('/hospital-dashboard')}
      >
        &larr; Back to Dashboard
      </button>

      <div className="request-details">
        <h2>Request #{requestDetails?.requestId}</h2>
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
            <span>Fulfilled Qty:</span>
            <span>{requestDetails?.fulfilledQuantity || 0}</span>
          </div>
          <div className="detail-item">
            <span>Status:</span>
            <span className={`status-${requestDetails?.status?.toLowerCase()}`}>
              {requestDetails?.status}
            </span>
          </div>
        </div>
      </div>

      <div className="email-form">
        <div className="form-group">
          <label>From:</label>
          <input 
            type="email" 
            value={userEmail} 
            readOnly 
            className="email-input"
          />
        </div>

        <div className="form-group">
          <label>Select Recipients:</label>
          <div className="email-selector">
            <div className="selection-controls">
              <button type="button" onClick={selectAllEmails}>
                Select All
              </button>
              <button type="button" onClick={deselectAllEmails}>
                Deselect All
              </button>
            </div>
            
            <div className="email-list">
              {hospitalEmails.map(email => (
                <label 
                  key={email} 
                  className={`email-item ${!validateEmail(email) ? 'invalid-email' : ''}`}
                >
                  <input
                    type="checkbox"
                    checked={selectedEmails.includes(email)}
                    onChange={() => handleEmailSelection(email)}
                    disabled={!validateEmail(email)}
                  />
                  {email}
                  {!validateEmail(email) && (
                    <span className="error-text"> (Invalid)</span>
                  )}
                </label>
              ))}
            </div>
          </div>
        </div>

        <div className="form-group">
          <label>Message:</label>
          <textarea
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            className="message-input"
            rows="6"
            placeholder={`Dear ${requestDetails?.hospitalName} team,\n\nWe would like to propose...`}
          />
        </div>

        <div className="button-group">
              <button
                className="cancel-button"
                onClick={() => navigate('/hospital-dashboard')}
                disabled={isSending}
              >
                Cancel
              </button>

              <div className="send-section">
                <button 
                  onClick={handleSendEmail}
                  disabled={isSending || selectedEmails.length === 0}
                  className="send-button"
                >
                  {isSending ? 'Sending...' : `Send to ${selectedEmails.length} Selected`}
                </button>

                <button
                  className="fulfill-button"
                  onClick={() => navigate(`/fulfill/request/${requestId}`, { state: { requestDetails } })}
                  disabled={isSending}
                >
                  Fulfill Request
                </button>
              </div>
            </div>

            {status && (
              <div className={`status ${status.includes('success') ? 'success' : 'error'}`}>
                {status}
              </div>
            )}
      </div>
    </div>
  );
};

export default ConnectHospitals;