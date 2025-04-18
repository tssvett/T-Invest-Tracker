// src/api/shareService.js
import axios from './axiosConfig';

const getAllShares = async () => {
  try {
    const response = await axios.post('/share/all');
    return response.data;
  } catch (error) {
    throw error;
  }
};

const getShareById = async (id) => {
  try {
    const response = await axios.get(`/share/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

// const addShareToPortfolio = async (shareId) => {
//   try {
//     const response = await axios.post('/share/add', { shareId });
//     return response.data;
//   } catch (error) {
//     throw error;
//   }
// };

const getUserPortfolio = async () => {
  try {
    const response = await axios.get('/portfolio');
    return response.data;
  } catch (error) {
    throw error;
  }
};

const shareService = {
  getAllShares,
  getShareById,
  // addShareToPortfolio,
  getUserPortfolio
};

export default shareService;
