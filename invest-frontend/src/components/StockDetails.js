// src/components/StockDetails.js
import React from 'react';

const StockDetails = ({ stock, onClose }) => {
  if (!stock) return null;
  
  return (
    <div className="card">
      <div className="card-header d-flex justify-content-between align-items-center">
        <h5 className="mb-0">Информация об акции</h5>
        <button type="button" className="btn-close" onClick={onClose}></button>
      </div>
      <div className="card-body">
        <h4>{stock.name} ({stock.ticker})</h4>
        <div className="row mt-4">
          <div className="col-md-6">
            <p><strong>Текущая цена:</strong> ${stock.currentPrice}</p>
            <p><strong>Изменение за день:</strong> {stock.dailyChange}%</p>
            <p><strong>Объем торгов:</strong> {stock.volume}</p>
            <p><strong>Рыночная капитализация:</strong> ${stock.marketCap}</p>
          </div>
          <div className="col-md-6">
            <p><strong>52-недельный максимум:</strong> ${stock.high52Week}</p>
            <p><strong>52-недельный минимум:</strong> ${stock.low52Week}</p>
            <p><strong>P/E коэффициент:</strong> {stock.peRatio}</p>
            <p><strong>Дивидендная доходность:</strong> {stock.dividendYield}%</p>
          </div>
        </div>
        <div className="mt-3">
          <h5>О компании</h5>
          <p>{stock.description}</p>
        </div>
      </div>
    </div>
  );
};

export default StockDetails;
