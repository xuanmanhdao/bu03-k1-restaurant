import React from 'react';
import Modal from 'react-modal';
import CategoryService from './categoryService';

const customStyles = {
  content: {
    top: '50%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
  },
};

Modal.setAppElement('#root');

function ModalCategoryDelete(props) {
  const { isOpen, onRequestClose, message, categoryId } = props;
 const deleteCategory = async ()=>{
    await CategoryService.deleteCategory(categoryId)
 }
  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      style={customStyles}
      contentLabel="Order Modal"
    >
      <h2>{message}</h2>
      <button onClick={deleteCategory}>Delete</button>
      <button onClick={onRequestClose}>Close</button>
    </Modal>
  );
}

export default ModalCategoryDelete;