import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import productService from './productService';
import { useNavigate } from 'react-router-dom';

function ProductEditForm() {
    const {id} = useParams()
    const [product, setProduct] = useState({})
    const navigate = useNavigate();

    useEffect(()=>{
        console.log('call api')
        productService.getProductsId(id).then(res=>setProduct(res))
    }, [])

  const handleNameChange = (value) => {
    setProduct({...product, name: value})
  };

  const handlePriceChange = (value) => {
    setProduct({...product, price: value})
  };
  
  const handleCategoryIdChange = (value) => {
    setProduct({...product, categoryId: value})
  };

  const handleStatusChange = (value) => {
    setProduct({...product, status: value})
  };

  const handleImageChange = (value) => {
    setProduct({...product, image: value})
  };
  
  const handleTotalSoldChange = (value) => {
    setProduct({...product, totalSold: value})
  };

  const handleDescriptionChange = (value) => {
    setProduct({...product, description: value})
  };

  const handleQuantityChange = (value) => {
    setProduct({...product, quantity: value})
  };

  const handleSave = async ()=>{
    await productService.updateProduct(id, product);
    navigate('/admin/new-page-product');
  }

  return (
    <form >
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Name</label>
        <input type="text" class="form-control" id="validationDefault01" value={product.name} onChange={e => handleNameChange(e.target.value)} />
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Price</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.price} onChange={e => handlePriceChange(e.target.value)} />
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Category Id</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.categoryId} onChange={e => handleCategoryIdChange(e.target.value)} />
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Status</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.status} onChange={e => handleStatusChange(e.target.value)} />
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Image</label>
        <input type="text" class="form-control" id="validationDefault01" value={product.image} onChange={e => handleImageChange(e.target.value)}/>
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">total Sold</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.totalSold} onChange={e => handleTotalSoldChange(e.target.value)}/>
      </div>
      <div class="form-group">
        <label for="exampleFormControlTextarea1">Description</label>
        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" value={product.description} onChange={e => handleDescriptionChange(e.target.value)}></textarea>
      </div>
      <div class="col-md-4 mb-3">
        <label for="validationDefault01">Quantity</label>
        <input type="number" class="form-control" id="validationDefault01" value={product.quantity} onChange={e => handleQuantityChange(e.target.value)} />
      </div>
      <button type="button" onClick={handleSave}>Save</button>
    </form>
  );
}

export default ProductEditForm;