import React, { useEffect, useState } from "react";
import OrderService from "../../service/history-order/historyOrder";
import { decodeToken } from "../../service/base/decodeToken";
import { Alert, Spinner, Table } from "react-bootstrap";
const HistoryOrder = ({ id }) => {
  const [history, setHistory] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const decodedToken = await decodeToken();
        const userId = decodedToken.id;
        const data = await OrderService.getHistoryOrder(userId);
        const orders = data;
        setHistory(orders);
      } catch (error) {
        console.error(error);
      } finally {
        setIsLoading(false); // Đặt isLoading thành false trong cả trường hợp thành công và thất bại
      }
    };
    fetchData();
  }, [id]);

  const sortedHistory = [...history].sort((a, b) => b.id - a.id);
  return (
    <div>
      <h1>Order History</h1>
      {isLoading ? (
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      ) : (
        <>
          {sortedHistory.length === 0 ? (
            <Alert variant="info">
              Vui lòng đặt order để hiển thị được lịch sử!
            </Alert>
          ) : (
            <Table striped bordered>
              <thead>
                <tr>
                  <th>Order ID</th>
                  <th>Total</th>
                  <th>Status</th>
                  <th>Product Name</th>
                  <th>Product Quantity</th>
                  <th>Product Price</th>
                  <th>Product Description</th>
                  <th>Product Image</th>
                  <th>Total Sold</th>
                </tr>
              </thead>
              <tbody>
                {sortedHistory.map((order) => (
                  <tr key={order.id}>
                    <td>{order.id}</td>
                    <td>{order.total}</td>
                    <td>{order.status}</td>
                    {order.items.map((item) => (
                      <React.Fragment key={item.id}>
                        <td>{item.product.name}</td>
                        <td>{item.quantity}</td>
                        <td>{item.price}</td>
                        <td>{item.product.description}</td>
                        <td>
                          <img
                            src={
                              "https://inkythuatso.com/uploads/thumbnails/800/2022/05/129028238-2748634342066155-777440049447499125-n-12-09-40-30.jpg"
                            }
                            alt={item.product.name}
                            style={{ maxWidth: "100px" }}
                          />
                        </td>
                        <td>{item.product.totalSold}</td>
                      </React.Fragment>
                    ))}
                  </tr>
                ))}
              </tbody>
            </Table>
          )}
        </>
      )}
    </div>
  );
};
export default HistoryOrder;
