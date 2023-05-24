import React, { useEffect, useState } from "react";
import { Table, Modal, Button, Select } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { BiEdit } from "react-icons/bi";
import { AiFillFilePdf } from "react-icons/ai";
import { Link } from "react-router-dom";
import {
  getOrders,
  exportOrder,
  updateStatusOrder,
} from "../features/order/orderSlice";
const { Option } = Select;

const EditOrderModal = ({ visible, onCancel, onOk, order }) => {
  const [status, setStatus] = useState(order.status);

  const handleStatusChange = (value) => {
    setStatus(value);
  };

  return (
    <Modal
      title={`Edit order ${order.id}`}
      visible={visible}
      onCancel={onCancel}
      footer={[
        <Button key="cancel" onClick={onCancel}>
          Cancel
        </Button>,
        <Button key="submit" type="primary" onClick={() => onOk(status)}>
          Save
        </Button>,
      ]}
    >
      <p>Thông tin chi tiết đơn hàng:</p>
      <p>Mã hóa đơn: {order.id}</p>
      <p>Mã người dùng: {order.createdBy}</p>
      <p>Tổng tiền hóa đơn: {order.total}</p>
      <p>Người tạo hóa đơn: {order.createdBy}</p>
      <p>
        Tình trạng đơn:
        <Select defaultValue={status} onChange={handleStatusChange}>
          <Option value={0}>Đã hủy đơn</Option>
          <Option value={1}>Chưa xác nhận</Option>
          <Option value={2}>Đã xác nhận</Option>
          <Option value={3}>Đang giao hàng</Option>
          <Option value={4}>Đã thanh toán</Option>
        </Select>
      </p>
      <p>Ngày tạo hóa đơn: {new Date(order.createdAt).toLocaleString()}</p>
    </Modal>
  );
};

const columns = [
  {
    title: "STT",
    dataIndex: "key",
  },
  {
    title: "Mã hóa đơn",
    dataIndex: "idOrder",
    sorter: (a, b) => a.idOrder - b.idOrder,
  },
  {
    title: "Mã người dùng",
    dataIndex: "idUser",
  },
  {
    title: "Tổng tiền hóa đơn",
    dataIndex: "total",
    sorter: (a, b) => a.total - b.total,
    render: (total) =>
      new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
      }).format(total),
  },
  {
    title: "Người tạo hóa đơn",
    dataIndex: "idCreatedBy",
  },
  {
    title: "Tình trạng đơn",
    dataIndex: "status",
  },
  {
    title: "Date",
    dataIndex: "date",
    sorter: (a, b) => new Date(a.date) - new Date(b.date),
    render: (date) =>
      new Date(date).toLocaleString(undefined, {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
        hour: "numeric",
        minute: "numeric",
        second: "numeric",
      }),
  },

  {
    title: "Action",
    dataIndex: "action",
  },
];

const Orders = () => {
  const [modalVisible, setModalVisible] = useState(false);
  const [selectedOrder, setSelectedOrder] = useState(null);
  const [data, setData] = useState([]);

  const handleEditOrder = (order) => {
    setSelectedOrder(order);
    setModalVisible(true);
  };

  const handleModalCancel = () => {
    setModalVisible(false);
  };

  const handleModalOk = (status) => {
    if (selectedOrder) {
      const id = selectedOrder.id;
      console.log(id);
      // call API to update order status
      updateStatusOrder(id, status)
        .then(() => dispatch(getOrders()))
        .then(() => setModalVisible(false));
    }
  };

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getOrders());
  }, []);
  const orderState = useSelector((state) => state);
  const { orders } = orderState.order;

  const handleClickExport = (id) => {
    exportOrder(id);
  };
  console.log(orders);

  useEffect(() => {
    setData(
      orders.map((order, index) => {
        let statusText = "";
        switch (order.status) {
          case 0:
            statusText = "Đã hủy đơn";
            break;
          case 1:
            statusText = "Chưa xác nhận";
            break;
          case 2:
            statusText = "Đã xác nhận";
            break;
          case 3:
            statusText = "Đang giao hàng";
            break;
          case 4:
            statusText = "Đã thanh toán";
            break;
          default:
            statusText = "Không xác định";
        }
        return {
          key: index + 1,
          idOrder: order.id,
          idUser: order.createdBy,
          total: order.total,
          idCreatedBy: order.createdBy,
          status: statusText,
          date: order.createdAt,
          action: (
            <div style={{ display: "flex" }}>
              <Link
                to="/"
                className="fs-3 text-danger"
                onClick={(e) => {
                  e.preventDefault();
                  handleEditOrder(order);
                }}
              >
                <BiEdit />
              </Link>
              <Link
                className="ms-3 fs-3 text-danger"
                to="/"
                onClick={(e) => {
                  e.preventDefault();
                  handleClickExport(order.id);
                }}
              >
                <AiFillFilePdf />
              </Link>
            </div>
          ),
        };
      })
    );
  }, [orders]);

  return (
    <div>
      <h3 className="mb-4 title">List Order</h3>
      <div>{<Table columns={columns} dataSource={data} />}</div>
      {selectedOrder && (
        <EditOrderModal
          visible={modalVisible}
          onCancel={handleModalCancel}
          onOk={handleModalOk}
          order={selectedOrder}
        />
      )}
    </div>
  );
};

export default Orders;
