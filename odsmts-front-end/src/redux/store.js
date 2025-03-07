// import { configureStore } from "@reduxjs/toolkit";
// import userReducer from "./userSlice";

// const store = configureStore({
//   reducer: {
//     user: userReducer,
//   },
// });

// export default store;

import { configureStore } from "@reduxjs/toolkit";
import storage from "redux-persist/lib/storage"; // Uses localStorage
import { persistReducer, persistStore } from "redux-persist";
import userReducer from "./userSlice"; // ✅ Import user reducer

// const persistConfig = {
//   key: "user",
//   storage,
//   whitelist: ["token", "username", "roleId"], // ✅ Persist only necessary data
// };

const persistConfig = {
  key: "user",
  storage,
  whitelist: ["token", "username", "email", "roleId", "hospitalDetails"], // Include email and hospitalDetails
};


const persistedUserReducer = persistReducer(persistConfig, userReducer);

const store = configureStore({
  reducer: {
    user: persistedUserReducer, // ✅ Use persisted reducer
  },
});

export const persistor = persistStore(store); // ✅ Create persistor instance
export default store;
