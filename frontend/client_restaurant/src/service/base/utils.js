import axios from "axios";

const imageDefaut = "https://lavenderstudio.com.vn/wp-content/uploads/2017/03/chup-san-pham.jpg";

export const checkImage = async (imageUrl) => {
    try {
        const response = await axios.head(imageUrl);
        if (response.status === 200) {
            return imageUrl; // URL hình ảnh tồn tại
        }
    } catch (error) {
        // Xử lý lỗi
        imageUrl = imageDefaut;
    }
    return imageUrl; // Trả về URL ảnh mặc định nếu không tồn tại
};

