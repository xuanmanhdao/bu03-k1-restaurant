import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import orderService from "./orderService";

const initialState = {
  orders: [],
  isError: false,
  isLoading: false,
  isSuccess: false,
  message: "",
};

export const getOrders = createAsyncThunk(
  "order/get-orders",
  async (thunkAPI) => {
    try {
      console.log("true");
      return await orderService.getOrders();
    } catch (error) {
      console.log("fail");
      return thunkAPI.rejectWithValue(error);
    }
  }
);

export const exportOrder = (id) => {
  try {
    console.log(id);
    console.log("true");
    return orderService.exportOrder(id);
  } catch (error) {
    console.log(error);
  }
};

export const updateStatusOrder = (id, newStatus) => {
  try {
    console.log(id);
    console.log("true");
    return orderService.updateStatusOrder(id, newStatus);
  } catch (error) {
    console.log(error);
  }
};

export const orderSlice = createSlice({
  name: "order",
  initialState: initialState,
  reducers: {},
  extraReducers: (buildeer) => {
    buildeer
      .addCase(getOrders.pending, (state) => {
        console.log("pending");
        state.isLoading = true;
      })
      .addCase(getOrders.fulfilled, (state, action) => {
        console.log("fulfilled");
        console.log(action);
        state.isError = false;
        state.isLoading = false;
        state.isSuccess = true;
        state.orders = action.payload;
        state.message = "success";
      })
      .addCase(getOrders.rejected, (state, action) => {
        console.log("rejected");
        state.isError = true;
        state.isSuccess = false;
        state.message = action.error;
        state.isLoading = false;
      });
  },
});

export default orderSlice.reducer;
