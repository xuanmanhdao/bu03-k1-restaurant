import { createSlice, createAsyncThunk, createAction } from "@reduxjs/toolkit";
import orderService from "./orderService";

export const getOrders = createAsyncThunk(
  "order/get-orders",
  async (thunkAPI) => {
    try {
      return await orderService.getOrders();
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
)
export const getOrdersTotalPriceOfYear = createAsyncThunk(
  "order/get-orders-total-price-of-year",
  async (thunkAPI) => {
    try {
      return await orderService.getOrdersTotalPriceOfYear();
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
)

export const resetState = createAction("Reset_all");
const initialState = {
  orders: [],
  isError: false,
  isLoading: false,
  isSuccess: false,
  message: "",
};

export const orderSlice = createSlice({
  name: "orders",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getOrders.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getOrders.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.orders = action.payload;
      })
      .addCase(getOrders.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })
      .addCase(getOrdersTotalPriceOfYear.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getOrdersTotalPriceOfYear.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isError = false;
        state.isSuccess = true;
        state.getOrdersTotalPriceOfYear = action.payload;
      })
      .addCase(getOrdersTotalPriceOfYear.rejected, (state, action) => {
        state.isLoading = false;
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
      })
      .addCase(resetState, () => initialState);
  },
});

export default orderSlice.reducer