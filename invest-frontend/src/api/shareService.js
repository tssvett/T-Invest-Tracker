// src/api/shareService.js
import axios from './axiosConfig';

const getAllShares = async () => {
  try {
    //const response = await axios.post('/share/all');
    return [{
      "id": "a9eb4238-eba9-488c-b102-b6140fd08e38",
      "assetId": "df2480ba-ddc0-4bea-b1be-2a42f9617b6e",
      "ticker": "AAPL",
      "currency": "usd",
      "name": "Apple",
      "exchange": "unknown",
      "sector": "it",
      "countryOfRiskName": "Соединенные Штаты Америки",
      "buyAvailableFlag": false,
      "sellAvailableFlag": false,
      "figi": "BBG000B9XRY4",
      "shareType": "SHARE_TYPE_COMMON",
      "dlongClient": 0,
      "dshortClient": 0
    },
    {
      "id": "376abc5c-359f-4d59-8a9c-468e8c92282f",
      "assetId": "49a4c4ea-acfb-440f-ac4a-359b483557cd",
      "ticker": "CGEN",
      "currency": "usd",
      "name": "Compugen",
      "exchange": "unknown",
      "sector": "health_care",
      "countryOfRiskName": "Государство Израиль",
      "buyAvailableFlag": false,
      "sellAvailableFlag": false,
      "figi": "BBG000B9ZLB0",
      "shareType": "SHARE_TYPE_COMMON",
      "dlongClient": 0,
      "dshortClient": 0
    },]
    //return response.data;
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
