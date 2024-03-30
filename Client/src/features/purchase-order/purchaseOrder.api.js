import { axios } from "@/libs/axios";

export const getPurchaseOrders = async () => {
  const { data } = await axios.get(`/purchase-order`);
  return data;
};

export const getPurchaseOrder = async (id) => {
  const { data } = await axios.get(`/purchase-order/${id}`);
  return data;
};

export const createPurchaseOrder = async (values) => {
  const { data } = await axios.post(`/purchase-order`, values);
  return data;
};

export const updatePurchaseOrder = async ({ id, values }) => {
  const { data } = await axios.put(`/purchase-order/${id}`, values);
  return data;
};

export const removePurchaseOrder = async (id) => {
  const { data } = await axios.delete(`/purchase-order/${id}`);
  return data;
};
