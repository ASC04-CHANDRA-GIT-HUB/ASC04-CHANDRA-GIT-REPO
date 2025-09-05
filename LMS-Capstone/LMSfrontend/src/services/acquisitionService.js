import api from './api';

export const getAcquisitions = async () => (await api.get('/acquisitions')).data;
export const createAcquisition = async (data) => (await api.post('/acquisitions', data)).data;
export const updateAcquisition = async (id, data) => (await api.put(`/acquisitions/${id}`, data)).data;
export const deleteAcquisition = async (id) => (await api.delete(`/acquisitions/${id}`)).data;