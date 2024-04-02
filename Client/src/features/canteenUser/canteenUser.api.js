import { axios } from "@/app/libs/axios";

export const getCanteenUsers = async () => {
  const { data } = await axios.get(`/canteenManager`);
  return data;
};

export const getCanteenUser = async (id) => {
  const { data } = await axios.get(`/canteenManager/${id}`);
  return data;
};

export const createCanteenUser = async (values) => {
  const { data } = await axios.post(`/canteenManager`, values);
  return data;
};

export const updateCanteenUser = async ({ id, values }) => {
  const { data } = await axios.put(`/canteenManager/${id}`, values, {
    headers: { "Content-Type": "multipart/form-data" },
  });
  return data;
};

export const removeCanteenUser = async (id) => {
  const { data } = await axios.delete(`/canteenManager/${id}`);
  return data;
};
