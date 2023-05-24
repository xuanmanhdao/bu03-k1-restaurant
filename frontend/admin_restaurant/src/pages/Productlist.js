import React, { useEffect, useState } from "react";
import { Table } from "antd";
import { useDispatch, useSelector } from "react-redux";

import { createProducts, deleteProducts, editProduct, getProducts } from "../features/product/productSlice";
import { Link, useNavigate } from "react-router-dom";
import { AiFillDelete, AiFillEdit } from "react-icons/ai";

const columns = [
  {
    title: "ID",
    dataIndex: "key",
    key: "id",
  },
  {
    title: "CATEGORY ID",
    dataIndex: "categoryId",
  },
  {
    title: "STATUS",
    dataIndex: "status",
  },
  {
    title: "IMAGE",
    dataIndex: "image",
    render: (image) => <img src={image} alt="product" width="50" height="50" />,
  },
  {
    title: "NAME",
    dataIndex: "name",
  },
  {
    title: "Price",
    dataIndex: "price",
  },
  {
    title: "TOTAL SOLD",
    dataIndex: "totalSold",
  },
  {
    title: "DESCRIPTION",
    dataIndex: "description",
  },
  {
    title: "QUANTITY",
    dataIndex: "quantity",
  },
  {
    title: "CREATED AT",
    dataIndex: "createdAt",
  },
  {
    title: "UPDATED AT",
    dataIndex: "updatedAt",
  },
  {
    title: "UPDATED BY",
    dataIndex: "updatedBy",
  },
  {
    title: "CREATED BY",
    dataIndex: "createdBy",
  },
  {
    title: "",
    dataIndex: "edit",
  }
];

const Productlist = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(() => {
    dispatch(getProducts());
  }, []);

  const handleDelete = (productId) => {
    dispatch(deleteProducts(productId));
    navigate('/admin/new-page-product');
    window.location.reload();
  }

  const handleAddProduct = () => {
    navigate('/admin/create-product');
  }


  const productState = useSelector((state) => state.product.products);
  const data1 = [];
  for (let i = 0; i < productState.length; i++) {
    data1.push({
      key: productState[i].id,
      categoryId: productState[i].categoryId,
      status: productState[i].status,
      image: productState[i].image,
      name: productState[i].name,
      price: `${productState[i].price}`,
      totalSold: productState[i].totalSold,
      description: productState[i].description,
      quantity: productState[i].quantity,
      createdAt: productState[i].createdAt,
      updatedBy: productState[i].updatedBy,
      createdBy: productState[i].createdBy,
    });
  }
  console.log(data1);
  return (
    <div>
      <h3 className="mb-4 title">Products</h3>
      <button onClick={handleAddProduct}>ADD PRODUCT</button>
      <div>
        <Table
          columns={[
            ...columns,
            {
              title: "",
              dataIndex: "delete",
              render: (text, record) => (
                <>
                  <Link
                    className="ms-3 fs-3 text-danger"
                    to={`/admin/edit-product/${record.key}`}
                  >
                    <AiFillEdit />
                  </Link>
                  <Link
                    className="ms-3 fs-3 text-danger"
                    to="/"
                    onClick={(e) => {
                      e.preventDefault();
                      handleDelete(record.key);
                    }}
                  >
                    <AiFillDelete />
                  </Link>
                </>
              ),
            },
          ]}
          dataSource={data1}
        />
      </div>
    </div>
  );
};

export default Productlist;
