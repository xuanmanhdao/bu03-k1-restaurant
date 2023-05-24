import { createAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import RoleService from "./roleService";

export const getRoles = createAsyncThunk("role/get-roles", async (thunkAPI) => {
  try {
    return await RoleService.getRoleList();
  } catch (error) {
    return thunkAPI.rejectWithValue(error);
  }
});

export const getRolesById = createAsyncThunk(
  "role/get",
  async (id, thunkAPI) => {
    try {
      return await RoleService.getRolesById(id);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

export const resetState = createAction("Reset_all");

const initialState = {
  roles: [],
  isError: false,
  isLoading: false,
  isSuccess: false,
  message: "",
};
export const roleSlice = createSlice({
  name: "roles",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getRoles.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getRoles.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.getRoles = action.payload;
      })
      .addCase(getRoles.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })
      .addCase(getRolesById.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getRolesById.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.getRolesById = action.payload;
      })
      .addCase(getRolesById.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })
      .addCase(resetState, () => initialState);
  },
});
export default roleSlice.reducer;
