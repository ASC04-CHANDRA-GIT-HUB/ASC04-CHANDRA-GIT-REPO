import api from './api';

export const getCirculations = async () => (await api.get('/circulations')).data;
export const createCirculation = async (data) => (await api.post('/circulations', data)).data;
export const updateCirculation = async (id, data) => (await api.put(`/circulations/${id}`, data)).data;
export const deleteCirculation = async (id) => (await api.delete(`/circulations/${id}`)).data;