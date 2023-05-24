import React from 'react';
import Modal from 'react-modal';

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

function ModalNoti(props) {
  const { isOpen, onRequestClose, message } = props;

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      style={customStyles}
      contentLabel="Order Modal"
    >
      <h2>{message}</h2>
      <button onClick={onRequestClose}>Close</button>
    </Modal>
  );
}

export default ModalNoti;