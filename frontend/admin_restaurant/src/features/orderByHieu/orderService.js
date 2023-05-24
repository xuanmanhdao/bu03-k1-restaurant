import axios from "axios";
import { config } from "../../utils/axiosconfig";
import { base_url } from "../../utils/baseUrl";

const getOrders = async () => {
    const respone = await axios.get(`${base_url}order/owner/totalOfMonth?year=2023&month=3`, config);
    // console.log("chay vao getOrders")
    return respone.data.data;
};
const getOrdersTotalPriceOfYear = async () => {
    const respone = await axios.get(`${base_url}order/owner/getTotalPriceOfYear?year=2023`, config);
    // console.log("chay vao getOrdersTotalPriceOfYear")
    return respone.data.data;
};

const orderService = {
    getOrders,
    getOrdersTotalPriceOfYear
}
export default orderService;