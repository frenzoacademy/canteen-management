import { axios } from "@/app/libs/axios";

export const getStudents = async () => {
  const { data } = await axios.get(`/student`);
  return data;
};

export const getStudent = async (id) => {
  const { data } = await axios.get(`/student/${id}`);
  return data;
};

export const createStudent = async (values) => {
  const { data } = await axios.post(`/student`, values);
  return data;
};

export const updateStudent = async ({ id, values }) => {
  const { data } = await axios.put(`/student/${id}`, values, {
    headers: { "Content-Type": "multipart/form-data" },
  });
  return data;
};

export const removeStudent = async (id) => {
  const { data } = await axios.delete(`/student/${id}`);
  return data;
};
