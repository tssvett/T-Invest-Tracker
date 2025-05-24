// src/pages/Portfolio.js
import React, { useState, useEffect } from 'react';
import shareService from '../api/shareService';
import ShareDetails from '../components/ShareDetails';
import { PieChart, Pie, Cell, ResponsiveContainer, Legend, Tooltip } from 'recharts';

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#8884d8', '#82ca9d', '#ffc658'];

const Portfolio = () => {
  const [portfolio, setPortfolio] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [selectedShare, setSelectedShare] = useState(null);

  useEffect(() => {
    const fetchPortfolio = async () => {
      try {
        setLoading(true);
        const data = await shareService.getUserPortfolio();
        console.log("portfolio data: ", data);
        setPortfolio(data);
        setError('');
      } catch (error) {
        console.error('Ошибка при загрузке портфеля:', error);
        setError('Не удалось загрузить ваш инвестиционный портфель. Попробуйте позже.');
      } finally {
        setLoading(false);
      }
    };

    fetchPortfolio();
  }, []);

  const handleShowDetails = (share) => {
    try {
      const response = shareService.getShareById(share.shareId);
      response.then((share_full) => {
        setSelectedShare(share_full);      
      });
    } catch (error) {
      console.error(error);
    } 
  };

  const handleCloseDetails = () => {
    setSelectedShare(null);
  };

  const prepareChartData = () => {
    if (!portfolio || !portfolio.length) return [];

    return portfolio.map(share => ({
      name: shareService.getShareById(share.shareId).ticker,
      value: share.sharePrice * share.count
    }));
  };

  const handleDeleteShare = async (user_share) => {
    if (!window.confirm('Вы уверены, что хотите удалить акцию из портфеля?')) return;

    try {
      await shareService.deleteShare(user_share);
      setPortfolio(prev => prev.filter(share => share.shareId !== user_share.shareId));
      //window.location.reload(); 
    } catch (error) {
      console.error('Ошибка удаления:', error);
      setError('Не удалось удалить акцию');
    }
  };


  if (portfolio) {
    // portfolio.forEach(share => {
    //   //share.nominal = Math.floor(Math.random() * 100);
    // });
    portfolio.nominal = portfolio.reduce((sum, share) => sum + share.sharePrice * (share.shareCount ? share.shareCount : 1), 0);

  }


  if (loading) {
    return (
      <div className="container mt-5 text-center">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Загрузка...</span>
        </div>
        <p className="mt-2">Загрузка вашего портфеля...</p>
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

  if (portfolio.length == 0) {
    return (
      <div className="container mt-5">
        <div className="alert alert-info" role="alert">
          В вашем портфеле пока нет акций. Перейдите на страницу "Акции", чтобы добавить акции в портфель.
        </div>
      </div>
    );
  }
  //console.log("portfolio: ", portfolio);
  return (
    <div className="container mt-4">
      <h2 className="mb-4">Мой инвестиционный портфель</h2>

      <div className="row mb-4">
        <div className="col-md-4">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Общая стоимость</h5>
              <h2 className="card-text">${portfolio.nominal}</h2>
            </div>
          </div>
        </div>
        <div className="col-md-4">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Прибыль/Убыток</h5>
              <h2 className={`card-text ${(portfolio.nominal - 0) >= 0 ? 'text-success' : 'text-danger'}`}>
                ${portfolio.nominal} ({0}%)
              </h2>
            </div>
          </div>
        </div>
        <div className="col-md-4">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Количество акций</h5>
              <h2 className="card-text">{portfolio.length}</h2>
            </div>
          </div>
        </div>
      </div>

      <div className="row">
        <div className="col-md-7">
          <div className="card">
            <div className="card-header">
              <h5 className="mb-0">Акции в вашем портфеле</h5>
            </div>
            <div className="card-body">
              <div className="table-responsive">
                <table className="table table-hover">
                  <thead>
                    <tr>
                      <th>Тикер</th>
                      <th>Название</th>
                      <th>Количество</th>
                      <th>Цена</th>
                      <th>Всего</th>
                      <th>Действия</th>
                    </tr>
                  </thead>
                  <tbody>
                    {portfolio.map(share => (
                      <tr key={share.id}>
                        <td>{shareService.getShareById(share.shareId).ticker}</td>
                        <td>{shareService.getShareById(share.shareId).name}</td>
                        <td>{share.shareCount}</td>
                        <td>${share.sharePrice}</td>
                        <td>${share.sharePrice * (share.shareCount ? share.shareCount : 1)}</td>

                        <td>
                          <button
                            className="btn btn-sm btn-primary"
                            onClick={() => handleShowDetails(share)}
                          >
                            Детали
                          </button>
                          <button 
                            className="btn btn-sm btn-danger"
                            onClick={() => handleDeleteShare(share)}
                          >
                            Удалить акцию
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>

        <div className="col-md-5">
          {selectedShare ? (
            <ShareDetails
              share={selectedShare}
              onClose={handleCloseDetails}
            />
          ) : (
            <div className="card">
              <div className="card-header">
                <h5 className="mb-0">Распределение портфеля</h5>
              </div>
              <div className="card-body">
                <ResponsiveContainer width="100%" height={300}>
                  <PieChart>
                    <Pie
                      data={prepareChartData()}
                      cx="50%"
                      cy="50%"
                      outerRadius={80}
                      fill="#8884d8"
                      dataKey="value"
                      label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
                    >
                      {prepareChartData().map((entry, index) => (
                        <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                      ))}
                    </Pie>
                    <Tooltip formatter={(value) => [`$${value.toFixed(2)}`, 'Стоимость']} />
                    <Legend />
                  </PieChart>
                </ResponsiveContainer>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Portfolio;
