// import { createSlice } from "@reduxjs/toolkit";

// const initialState = {
//   token: null,
//   username: null,
//   roleId: null,
// };

// const userSlice = createSlice({
//   name: "user",
//   initialState,
//   reducers: {
//     loginSuccess: (state, action) => {
//       state.token = action.payload.token;
//       state.username = action.payload.username;
//       state.roleId = action.payload.roleId;
//     },
//     logout: (state) => {
//       state.token = null;
//       state.username = null;
//       state.roleId = null;
//     },
//   },
// });

// export const { loginSuccess, logout } = userSlice.actions;
// export default userSlice.reducer;




// when i need to use this redux data anywere in code reffer to this
// import { useSelector } from "react-redux";

// const { username, roleId } = useSelector((state) => state.user);



// import { createSlice } from "@reduxjs/toolkit";

// const initialState = {
//   token: null,
//   username: null,
//   roleId: null,
// };

// const userSlice = createSlice({
//   name: "user",
//   initialState,
//   reducers: {
//     loginSuccess: (state, action) => {
//       state.token = action.payload.token;
//       state.username = action.payload.username;
//       state.roleId = action.payload.roleId;
//     },
//     logout: (state) => {
//       state.token = null;
//       state.username = null;
//       state.roleId = null;
//     },
//   },
// });

// export const { loginSuccess, logout } = userSlice.actions;
// export default userSlice.reducer;


import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  token: null,
  username: null,
  email: null,
  roleId: null,
  hospitalDetails: null, // ✅ Store hospital details
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    loginSuccess: (state, action) => {
      state.token = action.payload.token;
      state.username = action.payload.username;
      state.email = action.payload.email;
      state.roleId = action.payload.roleId;
      state.hospitalDetails = action.payload.hospitalDetails; // ✅ Store full hospital details
    },
    logout: (state) => {
      state.token = null;
      state.username = null;
      state.email = null;
      state.roleId = null;
      state.hospitalDetails = null;
    },
  },
});

export const { loginSuccess, logout } = userSlice.actions;
export default userSlice.reducer;
