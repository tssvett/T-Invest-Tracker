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
    //console.log(response.data)
    return response.data;
  } catch (error) {
    throw error;
  }
};

const addShareToPortfolio = async (shareId) => {
  try {
    //console.log("add share shareId", shareId);
    //console.log("add share userId", localStorage.getItem('user'));

    const response = await axios.post('/portfolio', { 
      userId: localStorage.getItem('user'),
      shareId: shareId });
    return response.data;
  } catch (error) {
    throw error;
  }
};

const getUserPortfolio = async () => {
  try {
    const userId = localStorage.getItem('user');
    const response = await axios.get(`/portfolio/share/${userId}`);
    //console.log("get user share");
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

const deleteShare = async (user_share) => {
  console.log("delete share: ", user_share);
  const response = await axios.delete('/portfolio', {
    data:{
      id: user_share.id,
      userId: user_share.userId,
      shareId: user_share.shareId,
      shareCount: user_share.shareCount,
      sharePrice: user_share.sharePrice
    }
  });
  console.log("delete response: ", response);
  return response.status;
};

const shareService = {
  getAllShares,
  getShareById,
  addShareToPortfolio,
  getUserPortfolio,
  deleteShare
};

export default shareService;
