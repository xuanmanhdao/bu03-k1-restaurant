import axios from "axios";
import { config } from "../../utils/axiosconfig";
import { base_url } from "../../utils/baseUrl";

const getOrders = async () => {
  const response = await axios.get(`${base_url}order/staff/list-order`, config);
  return response.data.data;
};

const exportOrder = async (id) => {
  const response = await axios.get(`${base_url}order/staff/export/${id}`, {
    ...config,
    responseType: "blob",
  });
  // use file-saver to create a popup to save the file
  var now = new Date();
  var formattedDate =
    now.getFullYear().toString() +
    (now.getMonth() + 1).toString().padStart(2, "0") +
    now.getDate().toString().padStart(2, "0") +
    now.getHours().toString().padStart(2, "0") +
    now.getMinutes().toString().padStart(2, "0") +
    now.getSeconds().toString().padStart(2, "0");
  console.log(formattedDate);
  const fileName = `invoice_${id}_${formattedDate}.pdf`;
  const url = window.URL.createObjectURL(new Blob([response.data]));
  const link = document.createElement("a");
  link.href = url;
  link.setAttribute("download", fileName);
  document.body.appendChild(link);
  link.click();
};

const updateStatusOrder = async (id, newStatus) => {
  const response = await axios.put(
    `${base_url}order/staff/update-status/${id}?status=${newStatus}`,
    {},
    config
  );
  return response.data.data;
};

const orderService = {
  getOrders,
  exportOrder,
  updateStatusOrder
};

export default orderService;
