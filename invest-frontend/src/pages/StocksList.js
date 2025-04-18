// src/pages/SharesList.js
import React, { useState, useEffect } from 'react';
import shareService from '../api/shareService';
import ShareCard from '../components/ShareCard';
import ShareDetails from '../components/ShareDetails';

const SharesList = () => {
  const [shares, setShares] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [selectedShare, setSelectedShare] = useState(null);
  
  useEffect(() => {
    const fetchShares = async () => {
      try {
        setLoading(true);
        const data = await shareService.getAllShares();
        setShares(data);
        setError('');
      } catch (error) {
        console.error('Ошибка при загрузке списка акций:', error);
        setError('Не удалось загрузить список акций. Попробуйте позже.');
      } finally {
        setLoading(false);
      }
    };
    
    fetchShares();
  }, []);
  
  const handleShowDetails = (share) => {
    setSelectedShare(share);
  };
  
  const handleCloseDetails = () => {
    setSelectedShare(null);
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
          <div className="shares-scroll" style={{ maxHeight: '80vh', overflowY: 'auto' }}>
            {shares.length === 0 ? (
              <p>Акции не найдены</p>
            ) : (
              shares.map(share => (
                <ShareCard
                  key={share.id}
                  share={share}
                  onShowDetails={handleShowDetails}
                />
              ))
            )}
          </div>
        </div>
        
        <div className="col-md-5">
          {selectedShare ? (
            <ShareDetails 
              share={selectedShare} 
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

export default SharesList;
