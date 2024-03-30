import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { toast } from "sonner";
import {
  createStudent,
  getStudent,
  getStudents,
  removeStudent,
  updateStudent,
} from "./students.api";

export const useGetStudents = () =>
  useQuery({
    queryKey: ["getStudents"],
    queryFn: () => getStudents(),
    enabled: true,
  });

export const useGetStudent = (id) =>
  useQuery({
    queryKey: ["getStudents", id],
    queryFn: () => getStudent(id),
    enabled: !!id,
  });

export const useAddStudent = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: createStudent,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["getStudents"],
        refetchType: "active",
      });
      toast.success("Student added successfully");
    },
    onError: () => {
      toast.error("Something went Wrong!");
    },
  });
};

export const useEditStudent = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: updateStudent,
    onSuccess: (data, { id }) => {
      queryClient.setQueryData(["getStudents", id], { data });
      queryClient.invalidateQueries({ queryKey: ["getStudents"] });
      toast.success("Student updated successfully");
    },
    onError: () => {
      toast.error("Something Went Wrong");
    },
  });
};

export const useRemoveStudent = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: removeStudent,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["getStudents"],
        refetchType: "active",
      });
      setTimeout(() => toast.success("Student deleted successfully"), 1000);
    },
    onError: () => toast.error("Something Went Wrong"),
  });
};
