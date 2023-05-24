import cartService from "../cart/cartService";


export const cartReducer = (state, action) => {
  switch (action.type) {
    case "SET_PRODUCTS":
      return { ...state, products: action.payload };

    case "SET_CART":
      return { ...state, cart: action.payload };

    case "ADD_TO_CART":
      const updatedCart = [...state.cart, []];

      if (localStorage.getItem('user') === null) {
        return { ...state, cart: updatedCart };
      } else {
        const addedProduct = { ...action.payload, qty: 1 };
        // Thêm sản phẩm vào giỏ hàng trong state
        const updatedCart = [...state.cart, { ...addedProduct, productId: addedProduct.id, quantity: 1 }];
        // Gửi yêu cầu Axios để thêm sản phẩm vào database
        cartService.createItemCart({ productId: addedProduct.id, quantity: 1 }).then((res) => {
          console.log("Thêm sản phẩm vào giỏ thành công");
        });
        return { ...state, cart: updatedCart };
      }

    case "REMOVE_FROM_CART":
      // Tạo một mảng chứa các ID sản phẩm cần xóa
      let idsToRemove;
      if (Array.isArray(action.payload)) {
        // Xử lý khi payload là một mảng
        idsToRemove = action.payload;
      } else {
        // Xử lý khi payload là một chuỗi
        idsToRemove = [action.payload];
      }
      // Tìm sản phẩm cần xóa trong giỏ hàng
      const productToRemove = state.cart.filter((c) => idsToRemove.includes(c.id));
      console.log(state.cart.find((c) => c.id));
      // Kiểm tra nếu sản phẩm tồn tại trong giỏ hàng
      if (productToRemove) {
        // Gửi yêu cầu Axios để xóa sản phẩm khỏi database
        cartService.delItemCart(idsToRemove).then((res) => {
          console.log("Xóa sản phẩm khỏi giỏ hàng thành công");
        });
      }
      // Lọc các sản phẩm không phải là sản phẩm cần xóa và cập nhật giỏ hàng
      const updatedCart2 = state.cart.filter((c) => !idsToRemove.includes(c.id));

      return { ...state, cart: updatedCart2 };

    case "CHANGE_CART_QTY":
      const updatedCart3 = state.cart.map((c) =>
        c.id === action.payload.id ? { ...c, qty: action.payload.qty } : c
      );
      cartService.updateItemCart(action.payload.id, action.payload.qty).then((res) => {
        console.log("sửa số lượng sản phẩm trong giỏ thành công");
      });
      return { ...state, cart: updatedCart3 };

    default:
      return state;
  }
};

export const productReducer = (state, action) => {
  switch (action.type) {
    case "SORT_BY_PRICE":
      return { ...state, sort: action.payload };
    case "FILTER_BY_STOCK":
      return { ...state, byStock: !state.byStock };
    case "FILTER_BY_SEARCH":
      return { ...state, searchQuery: action.payload };
    case "CLEAR_FILTERS":
      return { byStock: false, byFastDelivery: false, byRating: 0 };
    default:
      return state;
  }
};
