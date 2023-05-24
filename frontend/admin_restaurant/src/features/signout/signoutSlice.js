import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { signoutServices } from "./signoutServices";

const initialState = {
  isError: false,
  isLoading: false,
  isSuccess: false,
  message: "",
};

export const signout = createAsyncThunk("auth/signout", async (_, thunkAPI) => {
  try {
    return await signoutServices();
  } catch (error) {
    return thunkAPI.rejectWithValue(error);
  }
});

export const signoutSlice = createSlice({
  name: "signout",
  initialState: initialState,
  reducers: {},
  extraReducers: (buildeer) => {
    buildeer
      .addCase(signout.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(signout.fulfilled, (state) => {
        state.isSuccess = true;
        state.isError = false;
        state.isLoading = false;
        state.message = "success";
      })
      .addCase(signout.rejected, (state) => {
        state.isSuccess = true;
        state.isError = false;
        state.isLoading = false;
        state.message = "failed";
      });
  },
});

export default signoutSlice.reducer;
