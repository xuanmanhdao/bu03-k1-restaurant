import axios from "axios";
import { base_url } from "../base/baseUrl";
import { config } from "../base/axiosconfig";

const getHistoryOrder = async (id) => {
    const response = await axios.get(`${base_url}order/customer/get/${id}`, config);
    return response.data.data;
};

const OrderService = {
    getHistoryOrder,
}
export default OrderService;