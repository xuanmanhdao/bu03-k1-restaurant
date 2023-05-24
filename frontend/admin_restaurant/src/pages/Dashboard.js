import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { BsArrowDownRight, BsArrowUpRight } from "react-icons/bs";
import { Column } from "@ant-design/plots";
import { Table } from "antd";
import { getOrders } from "../features/orderByHieu/orderSlice";
import { getOrdersTotalPriceOfYear } from "../features/orderByHieu/orderSlice";
const columns = [
  {
    title: "PRODUCT ID",
    dataIndex: "key",
  },
  {
    title: "NAME",
    dataIndex: "name",
  },
  {
    title: "QUANTITY",
    dataIndex: "quantity",
  },
];

const Dashboard = () => {

  const dispatch = useDispatch();

  useEffect(() => {
    console.log("user Effect")
    dispatch(getOrders());
    dispatch(getOrdersTotalPriceOfYear())
  }, []);

  const State = useSelector((state) => state);
  const orderState = State.order.orders;
  let orderTotalOfYearState = State.order.getOrdersTotalPriceOfYear;


  const dataOrders = [];
  for (let i = 0; i < orderState.length; i++) {
    dataOrders.push({
      key: orderState[i].productId,
      name: orderState[i].name,
      quantity: orderState[i].quantity
    });
  }

  // function getTotalPriceOfYear(month) {
  //   const totalArray = orderTotalOfYearState.filter(item => item.createdAt === month).map(item => item.total);
  //   console.log('total array:',totalArray);
  //   return totalArray;
  // }


  console.log(orderState);
  console.log(orderTotalOfYearState);
  // console.log(State);

  const data = [
    {
      type: "Jan",
      sales: 30,
      // sales: Number(getTotalPriceOfYear(3)),
    },
    {
      type: "Feb",
      sales: 52,
      // sales: Number(getTotalPriceOfYear(5)),
    },
    {
      type: "Mar",
      sales: 61,
    },
    {
      type: "Apr",
      sales: 145,
    },
    {
      type: "May",
      sales: 48,
    },
    {
      type: "Jun",
      sales: 38,
    },
    {
      type: "July",
      sales: 38,
    },
    {
      type: "Aug",
      sales: 38,
    },
    {
      type: "Sept",
      sales: 38,
    },
    {
      type: "Oct",
      sales: 38,
    },
    {
      type: "Nov",
      sales: 38,
    },
    {
      type: "Dec",
      sales: 38,
    },
  ];
  const config = {
    data,
    xField: "type",
    yField: "sales",
    color: ({ type }) => {
      return "#ffd333";
    },
    label: {
      position: "middle",
      style: {
        fill: "#FFFFFF",
        opacity: 1,
      },
    },
    xAxis: {
      label: {
        autoHide: true,
        autoRotate: false,
      },
    },
    meta: {
      type: {
        alias: "Month",
      },
      sales: {
        alias: "Income",
      },
    },
  };
  return (
    <div>
      <h3 className="mb-4 title">Dashboard</h3>
      <div className="d-flex justify-content-between align-items-center gap-3">
        <div className="d-flex justify-content-between align-items-end flex-grow-1 bg-white p-3 roudned-3">
          <div>
            <p className="desc">Total</p>
            <h4 className="mb-0 sub-title">$1100</h4>
          </div>
          <div className="d-flex flex-column align-items-end">
            <h6>
              <BsArrowDownRight /> 32%
            </h6>
            <p className="mb-0  desc">Compared To April 2022</p>
          </div>
        </div>
        <div className="d-flex justify-content-between align-items-end flex-grow-1 bg-white p-3 roudned-3">
          <div>
            <p className="desc">Total</p>
            <h4 className="mb-0 sub-title">$1100</h4>
          </div>
          <div className="d-flex flex-column align-items-end">
            <h6 className="red">
              <BsArrowDownRight /> 32%
            </h6>
            <p className="mb-0  desc">Compared To April 2022</p>
          </div>
        </div>
        <div className="d-flex justify-content-between align-items-end flex-grow-1 bg-white p-3 roudned-3">
          <div>
            <p className="desc">Total</p>
            <h4 className="mb-0 sub-title">$1100</h4>
          </div>
          <div className="d-flex flex-column align-items-end">
            <h6 className="green">
              <BsArrowDownRight /> 32%
            </h6>
            <p className="mb-0 desc">Compared To April 2022</p>
          </div>
        </div>
      </div>
      <div className="mt-4">
        <h3 className="mb-5 title">Income Statics</h3>
        <div>
          <Column {...config} />
        </div>
      </div>
      <div className="mt-4">
        <h3 className="mb-5 title">Recent Orders</h3>
        <div>
          <Table columns={columns} dataSource={dataOrders} />
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
