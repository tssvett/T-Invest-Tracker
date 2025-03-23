// src/components/StockCard.js
import React from 'react';
import stockService from '../api/stockService';

const StockCard = ({ stock, onShowDetails }) => {
  const handleAddToPortfolio = async () => {
    try {
      await stockService.addStockToPortfolio(stock.id);
      alert('Акция успешно добавлена в портфель');
    } catch (error) {
      console.error('Ошибка при добавлении акции в портфель:', error);
      alert('Ошибка при добавлении акции в портфель');
    }
  };
  
  return (
    <div className="card mb-3">
      <div className="card-body">
        <h5 className="card-title">{stock.name} ({stock.ticker})</h5>
        <p className="card-text">Текущая цена: ${stock.currentPrice}</p>
        <p className="card-text">Изменение: {stock.change}%</p>
        <div className="d-flex justify-content-between mt-3">
          <button 
            className="btn btn-primary"
            onClick={() => onShowDetails(stock)}
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

export default StockCard;
