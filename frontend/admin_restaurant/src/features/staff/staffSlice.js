import { createAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import staffService from "./staffService";

export const getStaffList = createAsyncThunk("owner/list", async (thunkAPI) => {
  try {
    return await staffService.getStaffList();
  } catch (error) {
    return thunkAPI.rejectWithValue(error);
  }
});

export const createStaff = createAsyncThunk(
  "owner/add",
  async (data, thunkAPI) => {
    try {
      return await staffService.createStaff(data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

export const updateStaff = createAsyncThunk(
  "owner/update",
  async (data, thunkAPI) => {
    try {
      return await staffService.updateStaff(data);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

export const deleteStaff = createAsyncThunk(
  "owner/delete",
  async (id, thunkAPI) => {
    try {
      return await staffService.removeStaff(id);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

export const getStaffById = createAsyncThunk(
  "owner/get",
  async (id, thunkAPI) => {
    try {
      return await staffService.getStaffById(id);
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

export const resetState = createAction("Reset_all");

const initialState = {
  staffs: [],
  isError: false,
  isLoading: false,
  isSuccess: false,
  message: "",
};
export const staffSlice = createSlice({
  name: "staffs",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getStaffList.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getStaffList.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.products = action.payload;
      })
      .addCase(getStaffList.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })

      .addCase(createStaff.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(createStaff.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.createdProduct = action.payload;
      })
      .addCase(createStaff.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })

      .addCase(updateStaff.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(updateStaff.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.createdProduct = action.payload;
      })
      .addCase(updateStaff.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })

      .addCase(getStaffById.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getStaffById.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.getStaffById = action.payload;
      })
      .addCase(getStaffById.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })

      .addCase(deleteStaff.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(deleteStaff.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.createdProduct = action.payload;
      })
      .addCase(deleteStaff.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })

      .addCase(resetState, () => initialState);
  },
});
export default staffSlice.reducer;
