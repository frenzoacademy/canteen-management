import { axios } from "@/libs/axios";

export const getFoodInventories = async () => {
  const { data } = await axios.get(`/food-inventory`);
  return data;
};

export const getFoodInventory = async (id) => {
  const { data } = await axios.get(`/food-inventory/${id}`);
  return data;
};

export const createFoodInventory = async (values) => {
  const { data } = await axios.post(`/food-inventory`, values);
  return data;
};

export const updateFoodInventory = async ({ id, values }) => {
  const { data } = await axios.put(`/food-inventory/${id}`, values);
  return data;
};

export const removeFoodInventory = async (id) => {
  const { data } = await axios.delete(`/food-inventory/${id}`);
  return data;
};
