import api from './api';

export const getCatalogues = async () => (await api.get('/catalogues')).data;
export const createCatalogue = async (data) => (await api.post('/catalogues', data)).data;
export const updateCatalogue = async (id, data) => (await api.put(`/catalogues/${id}`, data)).data;
export const deleteCatalogue = async (id) => (await api.delete(`/catalogues/${id}`)).data;