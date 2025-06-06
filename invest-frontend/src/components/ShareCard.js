﻿// src/components/ShareCard.js
import React from 'react';
import shareService from '../api/shareService';

const ShareCard = ({ share, onShowDetails }) => {
  //share.nominal = Math.floor(Math.random() * 100);
  //share.chashare = Math.floor(Math.random() * 10);

  const handleAddToPortfolio = async () => {
    try {
      console.log("share", share);
      await shareService.addShareToPortfolio(share.id);
      alert('Акция успешно добавлена в портфель');
    } catch (error) {
      console.error('Ошибка при добавлении акции в портфель:', error);
      alert('Ошибка при добавлении акции в портфель');
    }
  };
  
  return (
    <div className="card mb-3">
      <div className="card-body">
        <h5 className="card-title">{share.name} ({share.ticker})</h5>
        <p className="card-text">Текущая цена: ${share.nominal}</p>
        <p className="card-text">Изменение: {share.chashare}%</p>
        <div className="d-flex justify-content-between mt-3">
          <button 
            className="btn btn-primary"
            onClick={() => onShowDetails(share)}
          >
            Подробнее
          </button>
          <button 
            className="btn btn-success"
            onClick={handleAddToPortfolio}
          >
            В портфель
          </button>
        </div>
      </div>
    </div>
  );
};

export default ShareCard;
