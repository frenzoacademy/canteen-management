import { axios } from "@/libs/axios";

export const getCanteenUsers = async () => {
  const { data } = await axios.get(`/canteen-user`);
  return data;
};

export const getCanteenUser = async (id) => {
  const { data } = await axios.get(`/canteen-user/${id}`);
  return data;
};

export const createCanteenUser = async (values) => {
  const { data } = await axios.post(`/canteen-user`, values);
  return data;
};

export const updateCanteenUser = async ({ id, values }) => {
  const { data } = await axios.put(`/canteen-user/${id}`, values);
  return data;
};

export const removeCanteenUser = async (id) => {
  const { data } = await axios.delete(`/canteen-user/${id}`);
  return data;
};
