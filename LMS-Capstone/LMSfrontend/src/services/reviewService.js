import api from './api';

export const getReviews = async () => (await api.get('/reviews')).data;
export const createReview = async (data) => (await api.post('/reviews', data)).data;
export const updateReview = async (id, data) => (await api.put(`/reviews/${id}`, data)).data;
export const deleteReview = async (id) => (await api.delete(`/reviews/${id}`)).data;