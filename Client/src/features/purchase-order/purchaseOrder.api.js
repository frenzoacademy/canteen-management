import { axios } from "@/app/libs/axios";

export const getPurchaseOrders = async () => {
  const { data } = await axios.get(`/purchase`);
  return data;
};

export const getPurchaseOrder = async (id) => {
  const { data } = await axios.get(`/purchase/${id}`);
  return data;
};

export const createPurchaseOrder = async (values) => {
  const { data } = await axios.post(`/purchase`, values);
  return data;
};

export const updatePurchaseOrder = async ({ id, values }) => {
  const { data } = await axios.put(`/purchase/${id}`, values);
  return data;
};

export const removePurchaseOrder = async (id) => {
  const { data } = await axios.delete(`/purchase/${id}`);
  return data;
};
