﻿// src/components/ShareDetails.js
import React from 'react';

const ShareDetails = ({ share, onClose }) => {
  if (!share) return null;
  //share.nominal = Math.floor(Math.random() * 100);
  //share.nominal = Math.floor(Math.random() * 100);
  // share.chashare = Math.floor(Math.random() * 10);
  //share.volumshare = Math.floor(Math.random() * 10);
  //share.marketCap = Math.floor(Math.random() * 10000);
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
            <p><strong>Текущая цена:</strong> ${share.nominal}</p>
            <p><strong>Изменение за день:</strong> {share.chashare}%</p>
            {/*<p><strong>Объем торгов:</strong> {share.volumshare}</p>*/}
            <p><strong>Рыночная капитализация:</strong> ${share.marketCapitalization}M</p>
          </div>
          <div className="col-md-6">
            <p><strong>Валюта:</strong> ${share.currency}</p>
            <p><strong>Сектор:</strong> ${share.sector}</p>
            <p><strong>Страна:</strong> {share.countryOfRiskName}</p>
            <p><strong>Доступна для покупки:</strong> {share.buyAvailableFlag}%</p>
            {/*<p><strong>52-недельный максимум:</strong> ${share.high52Week}</p>
            <p><strong>52-недельный минимум:</strong> ${share.low52Week}</p>
            <p><strong>P/E коэффициент:</strong> {share.peRatsharep}</p>
            <p><strong>Дивидендная доходность:</strong> {share.dividendYield}%</p>*/}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ShareDetails;
