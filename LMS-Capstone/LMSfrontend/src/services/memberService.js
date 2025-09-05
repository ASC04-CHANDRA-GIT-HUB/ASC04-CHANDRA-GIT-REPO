import api from './api';

export const getMembers = async () => (await api.get('/members')).data;
export const createMember = async (data) => (await api.post('/members', data)).data;
export const updateMember = async (id, data) => (await api.put(`/members/${id}`, data)).data;
export const deleteMember = async (id) => (await api.delete(`/members/${id}`)).data;