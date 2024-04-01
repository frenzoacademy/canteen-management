import { axios } from "@/libs/axios";

export const getFoodInventories = async () => {
  const { data } = await axios.get(`/foodInventory`);
  return data;
};

export const getFoodInventory = async (id) => {
  const { data } = await axios.get(`/foodInventory/${id}`);
  return data;
};

export const createFoodInventory = async (values) => {
  const { data } = await axios.post(`/foodInventory`, values);
  return data;
};

export const updateFoodInventory = async ({ id, values }) => {
  const { data } = await axios.put(`/foodInventory/${id}`, values, {
    headers: { "Content-Type": "multipart/form-data" },
  });
  return data;
};

export const removeFoodInventory = async (id) => {
  const { data } = await axios.delete(`/foodInventory/${id}`);
  return data;
};
