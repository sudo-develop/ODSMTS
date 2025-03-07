// import React from 'react';
// import ReactDOM from 'react-dom/client';
// import './index.css';
// import App from './App';
// import reportWebVitals from './reportWebVitals';

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );

// // If you want to start measuring performance in your app, pass a function
// // to log results (for example: reportWebVitals(console.log))
// // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();


///////////////////////////////////////////////////////////////////////////////////////

// import React from "react";
// import { createRoot } from "react-dom/client";
// import { Provider } from "react-redux";
// import { BrowserRouter } from "react-router-dom";
// import store from "./redux/store";
// import AppRoutes from "./routes/AppRouts"; // ✅ Use AppRoutes directly

// const root = createRoot(document.getElementById("root"));
// root.render(
//   <Provider store={store}>
//     <BrowserRouter>
//       <AppRoutes />
//     </BrowserRouter>
//   </Provider>
// );


import React from "react";
import { createRoot } from "react-dom/client";
import { Provider } from "react-redux";
import { PersistGate } from "redux-persist/integration/react"; // ✅ Ensure Redux Persist is applied
import { BrowserRouter } from "react-router-dom";
import store, { persistor } from "./redux/store";
import AppRoutes from "./routes/AppRouts"; 

const root = createRoot(document.getElementById("root"));
root.render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <BrowserRouter>
        <AppRoutes />
      </BrowserRouter>
    </PersistGate>
  </Provider>
);
