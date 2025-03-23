// src/pages/StocksList.js
import React, { useState, useEffect } from 'react';
import stockService from '../api/stockService';
import StockCard from '../components/StockCard';
import StockDetails from '../components/StockDetails';

const StocksList = () => {
  const [stocks, setStocks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [selectedStock, setSelectedStock] = useState(null);
  
  useEffect(() => {
    const fetchStocks = async () => {
      try {
        setLoading(true);
        const data = await stockService.getAllStocks();
        setStocks(data);
        setError('');
      } catch (error) {
        console.error('Ошибка при загрузке списка акций:', error);
        setError('Не удалось загрузить список акций. Попробуйте позже.');
      } finally {
        setLoading(false);
      }
    };
    
    fetchStocks();
  }, []);
  
  const handleShowDetails = (stock) => {
    setSelectedStock(stock);
  };
  
  const handleCloseDetails = () => {
    setSelectedStock(null);
  };
  
  if (loading) {
    return (
      <div className="container mt-5 text-center">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Загрузка...</span>
        </div>
        <p className="mt-2">Загрузка списка акций...</p>
      </div>
    );
  }
  
  if (error) {
    return (
      <div className="container mt-5">
        <div className="alert alert-danger" role="alert">
          {error}
        </div>
      </div>
    );
  }
  
  return (
    <div className="container mt-4">
      <h2 className="mb-4">Доступные акции</h2>
      
      <div className="row">
        <div className="col-md-7">
          <div className="stocks-scroll" style={{ maxHeight: '80vh', overflowY: 'auto' }}>
            {stocks.length === 0 ? (
              <p>Акции не найдены</p>
            ) : (
              stocks.map(stock => (
                <StockCard
                  key={stock.id}
                  stock={stock}
                  onShowDetails={handleShowDetails}
                />
              ))
            )}
          </div>
        </div>
        
        <div className="col-md-5">
          {selectedStock ? (
            <StockDetails 
              stock={selectedStock} 
              onClose={handleCloseDetails} 
            />
          ) : (
            <div className="alert alert-info">
              Выберите акцию слева для просмотра подробной информации
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default StocksList;
