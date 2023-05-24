import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import productService from './productService';
import { useNavigate } from 'react-router-dom';

function ProductCreateForm() {

  const navigate = useNavigate();
  const [product, setProduct] = useState({});

  const handleSave = async () => {
    await productService.createProduct(product);
    navigate('/admin/new-page-product');
  }

  const handleName = (name) => setProduct({ ...product, name })
  const handlePrice = (price) => setProduct({ ...product, price })
  const handleCategoryId = (categoryId) => setProduct({ ...product, categoryId })
  const handleStatus = (status) => setProduct({ ...product, status })
  const handleImage = (image) => setProduct({ ...product, image })
  const handleTotalSold = (totalSold) => setProduct({ ...product, totalSold })
  const handleDescription = (description) => setProduct({ ...product, description })
  const handleQuantity = (quantity) => setProduct({ ...product, quantity })


  return (
    <form >
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Name</label>
        <input type="text" class="form-control" id="validationDefault01" value={product.name} onChange={e => handleName(e.target.value)} />
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Price</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.price} onChange={e => handlePrice(e.target.value)} />
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Category Id</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.categoryId} onChange={e => handleCategoryId(e.target.value)} />
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Status</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.status} onChange={e => handleStatus(e.target.value)} />
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Image</label>
        <input type="text" class="form-control" id="validationDefault01" value={product.image} onChange={e => handleImage(e.target.value)}/>
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">total Sold</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.totalSold} onChange={e => handleTotalSold(e.target.value)}/>
      </div>
      <div class="form-group">
        <label for="exampleFormControlTextarea1">Description</label>
        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" value={product.description} onChange={e => handleDescription(e.target.value)}></textarea>
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Quantity</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.quantity} onChange={e => handleQuantity(e.target.value)} />
      </div>
      <button type="button" onClick={handleSave}>Save</button>
    </form>
  );

}

export default ProductCreateForm;