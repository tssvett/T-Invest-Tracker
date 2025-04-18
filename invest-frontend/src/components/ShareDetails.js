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
            <p><strong>Текущая цена:</strong> ${share.curresharece}</p>
            <p><strong>Изменение за день:</strong> {share.dailyChanshare}%</p>
            <p><strong>Объем торгов:</strong> {share.volumshare}</p>
            <p><strong>Рыночная капитализация:</strong> ${share.marketCap}</p>
          </div>
          <div className="col-md-6">
            <p><strong>52-недельный максимум:</strong> ${share.high52Week}</p>
            <p><strong>52-недельный минимум:</strong> ${share.low52Week}</p>
            <p><strong>P/E коэффициент:</strong> {share.peRatsharep}</p>
            <p><strong>Дивидендная доходность:</strong> {share.dividendYield}%</p>
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
