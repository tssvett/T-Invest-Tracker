// src/pages/Analytics.js
import React, { useState, useEffect } from 'react';
import axios from '../api/axiosConfig';
import { 
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, 
  Legend, ResponsiveContainer, BarChart, Bar, PieChart, Pie, Cell 
} from 'recharts';

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#8884d8'];

const Analytics = () => {
  const [performanceData, setPerformanceData] = useState([]);
  const [sectorData, setSectorData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        
        // В реальном приложении здесь должны быть запросы к вашему API
        // Для примера используем моковые данные
        
        // Имитация загрузки данных о производительности портфеля
        const performanceResponse = await new Promise(resolve => {
          setTimeout(() => {
            resolve({
              data: [
                { date: '2025-01', value: 10000, benchmarkValue: 10000 },
                { date: '2025-02', value: 10200, benchmarkValue: 10150 },
                { date: '2025-03', value: 10500, benchmarkValue: 10300 },
                { date: '2025-04', value: 10800, benchmarkValue: 10200 },
                { date: '2025-05', value: 11200, benchmarkValue: 10400 },
                { date: '2025-06', value: 11000, benchmarkValue: 10600 }
              ]
            });
          }, 500);
        });
        
        // Имитация загрузки данных о секторах
        const sectorResponse = await new Promise(resolve => {
          setTimeout(() => {
            resolve({
              data: [
                { name: 'Технологии', value: 45 },
                { name: 'Финансы', value: 20 },
                { name: 'Здравоохранение', value: 15 },
                { name: 'Промышленность', value: 10 },
                { name: 'Потребительские товары', value: 10 }
              ]
            });
          }, 500);
        });
        
        setPerformanceData(performanceResponse.data);
        setSectorData(sectorResponse.data);
        setError('');
      } catch (error) {
        console.error('Ошибка при загрузке аналитических данных:', error);
        setError('Не удалось загрузить аналитические данные. Попробуйте позже.');
      } finally {
        setLoading(false);
      }
    };
    
    fetchData();
  }, []);
  
  if (loading) {
    return (
      <div className="container mt-5 text-center">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Загрузка...</span>
        </div>
        <p className="mt-2">Загрузка аналитических данных...</p>
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
      <h2 className="mb-4">Инвестиционная аналитика</h2>
      
      <div className="row mb-4">
        <div className="col-md-12">
          <div className="card">
            <div className="card-header">
              <h5 className="mb-0">Динамика стоимости портфеля</h5>
            </div>
            <div className="card-body">
              <ResponsiveContainer width="100%" height={400}>
                <LineChart
                  data={performanceData}
                  margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
                >
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="date" />
                  <YAxis />
                  <Tooltip formatter={(value) => [`$${value}`, 'Стоимость']} />
                  <Legend />
                  <Line 
                    type="monotone" 
                    dataKey="value" 
                    stroke="#8884d8" 
                    name="Ваш портфель" 
                    strokeWidth={2}
                    activeDot={{ r: 8 }}
                  />
                  <Line 
                    type="monotone" 
                    dataKey="benchmarkValue" 
                    stroke="#82ca9d" 
                    name="Индекс S&P 500" 
                    strokeWidth={2}
                  />
                </LineChart>
              </ResponsiveContainer>
            </div>
          </div>
        </div>
      </div>
      
      <div className="row">
        <div className="col-md-6">
          <div className="card">
            <div className="card-header">
              <h5 className="mb-0">Распределение по секторам</h5>
            </div>
            <div className="card-body">
              <ResponsiveContainer width="100%" height={300}>
                <PieChart>
                  <Pie
                    data={sectorData}
                    cx="50%"
                    cy="50%"
                    outerRadius={100}
                    fill="#8884d8"
                    dataKey="value"
                    label={({name, percent}) => `${name} ${(percent * 100).toFixed(0)}%`}
                  >
                    {sectorData.map((entry, index) => (
                      <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                    ))}
                  </Pie>
                  <Tooltip formatter={(value) => [`${value}%`, 'Доля']} />
                  <Legend />
                </PieChart>
              </ResponsiveContainer>
            </div>
          </div>
        </div>
        
        <div className="col-md-6">
          <div className="card">
            <div className="card-header">
              <h5 className="mb-0">Ключевые метрики</h5>
            </div>
            <div className="card-body">
              <div className="table-responsive">
                <table className="table">
                  <tbody>
                    <tr>
                      <th>Годовая доходность:</th>
                      <td className="text-success">+12.5%</td>
                    </tr>
                    <tr>
                      <th>Коэффициент Шарпа:</th>
                      <td>1.2</td>
                    </tr>
                    <tr>
                      <th>Волатильность:</th>
                      <td>15.8%</td>
                    </tr>
                    <tr>
                      <th>Бета:</th>
                      <td>0.85</td>
                    </tr>
                    <tr>
                      <th>Корреляция с S&P 500:</th>
                      <td>0.72</td>
                    </tr>
                    <tr>
                      <th>Максимальная просадка:</th>
                      <td className="text-danger">-8.3%</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div className="row mt-4">
        <div className="col-md-12">
          <div className="card">
            <div className="card-header d-flex justify-content-between align-items-center">
              <h5 className="mb-0">Интеграция с PowerBI</h5>
              <button className="btn btn-outline-primary btn-sm">Открыть в PowerBI</button>
            </div>
            <div className="card-body text-center">
              <p>Для более глубокой аналитики, вы можете использовать интеграцию с PowerBI.</p>
              <p>В этом месте будет встроенный дашборд PowerBI или ссылка на внешний дашборд.</p>
              <button className="btn btn-primary">Настроить интеграцию с PowerBI</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Analytics;
