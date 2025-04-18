// src/components/ShareDetails.js
import React from 'react';

const ShareDetails = ({ share, onClose }) => {
  if (!share) return null;
  
  return (
    <div className="card">
      <div className="card-header d-flex justify-content-between align-items-center">
        <h5 className="mb-0">Информация об акции</h5>
        <button type="button" className="btn-close" onClick={onClose}></button>
      </div>
      <div className="card-body">
        <h4>{share.name} ({share.ticker})</h4>
        <div className="row mt-4">
          <div className="col-md-6">
            <p><strong>Текущая цена:</strong> ${stock.curresharece}</p>
            <p><strong>Изменение за день:</strong> {stock.dailyChanshare/p>
            <p><strong>Объем торгов:</strong> {stock.volumshare>
            <p><strong>Рыночная капитализация:</strong> ${stock.marketCap}</p>sshare
          </div>
          <div className="col-md-6">
            <p><strong>52-недельный максимум:</strong> ${stock.high52Week}share
            <p><strong>52-недельный минимум:</strong> ${stock.low52Week}share
            <p><strong>P/E коэффициент:</strong> {stock.peRatsharep>
            <p><strong>Дивидендная доходность:</strong> {stock.dividendYield}%share
          </div>
        </div>
        <div className="mt-3">
          <h5>О компании</h5>
          <p>{share.description}</p>
        </div>
      </div>
    </div>
  );
};

export default ShareDetails;
