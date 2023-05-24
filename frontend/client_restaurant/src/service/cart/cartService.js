import axios from "axios";
import { base_url } from "../base/baseUrl";
import { config } from "../base/axiosconfig";
import { getProducts } from "../product/productService";

const getCart = async () => {
    const response = await axios.get(`${base_url}cart-detail/get-cart-detail`, config);

    return response.data.data;
};
const createItemCart = async (cart) => {
    const response = await axios.post(`${base_url}cart-detail/save`, cart, config);
    return response.data.data;
};

const updateItemCart = async (id, quantity) => {
    const response = await axios.put(`${base_url}cart-detail/update-quantity`, {}, { params: { quantity, productId: id }, ...config });
    return response.data.message;
};

const delItemCart = async (ids) => {
    const idList = Array.isArray(ids) ? ids : ids.split(",");
    const response = await axios.delete(`${base_url}cart-detail?ids=${idList.join(",")}`, config);
    return response.data.message;
};


const getItemCart = async () => {
    const productResponse = await getProducts();
    const products = productResponse.reduce((obj, product) => {
        obj[product.id] = product;
        return obj;
    }, {});
    if (localStorage.getItem('user') === null) {
        return [];
    } else {
        let response;
        do {
            response = await getCart();
        } while (response === undefined);
        const cart = response.map((item) => {
            const product = products[item.productId];
            return {
                id: item.productId,
                name: product.name,
                price: product.price,
                image: product.image,
                inStock: product.quantity,
                qty: item.quantity,
            };
        })
        return cart;
    }

};

const order = async (order) => {
    const response = await axios.post(`${base_url}order/`, order, config);
    return response;
}

const cartService = {
    createItemCart,
    getCart,
    updateItemCart,
    getItemCart,
    delItemCart,
    order
};

export default cartService;
