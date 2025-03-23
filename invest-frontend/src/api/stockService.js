// src/api/stockService.js
import axios from './axiosConfig';

const getAllStocks = async () => {
  try {
    const response = await axios.get('/stocks');
    return response.data;
  } catch (error) {
    throw error;
  }
};

const getStockById = async (id) => {
  try {
    const response = await axios.get(`/stocks/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

const addStockToPortfolio = async (stockId) => {
  try {
    const response = await axios.post('/portfolio/add', { stockId });
    return response.data;
  } catch (error) {
    throw error;
  }
};

const getUserPortfolio = async () => {
  try {
    const response = await axios.get('/portfolio');
    return response.data;
  } catch (error) {
    throw error;
  }
};

const stockService = {
  getAllStocks,
  getStockById,
  addStockToPortfolio,
  getUserPortfolio
};

export default stockService;
