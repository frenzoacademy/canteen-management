import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { toast } from "sonner";
import {
  createCanteenUser,
  getCanteenUser,
  getCanteenUsers,
  removeCanteenUser,
  updateCanteenUser,
} from "./canteenUser.api";

export const useGetCanteenUsers = () =>
  useQuery({
    queryKey: ["getCanteenUsers"],
    queryFn: () => getCanteenUsers(),
    enabled: true,
  });

export const useGetCanteenUser = (id) =>
  useQuery({
    queryKey: ["getCanteenUsers", id],
    queryFn: () => getCanteenUser(id),
    enabled: !!id,
  });

export const useAddCanteenUser = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: createCanteenUser,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["getCanteenUsers"],
        refetchType: "active",
      });
      toast.success("Canteen User added successfully");
    },
    onError: () => {
      toast.error("Something went Wrong!");
    },
  });
};

export const useEditCanteenUser = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: updateCanteenUser,
    onSuccess: (data, { id }) => {
      queryClient.setQueryData(["getCanteenUsers", id], { data });
      queryClient.invalidateQueries({ queryKey: ["getCanteenUsers"] });
      toast.success("Canteen User updated successfully");
    },
    onError: () => {
      toast.error("Something Went Wrong");
    },
  });
};

export const useRemoveCanteenUser = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: removeCanteenUser,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["getCanteenUsers"],
        refetchType: "active",
      });
      setTimeout(
        () => toast.success("Canteen User deleted successfully"),
        1000
      );
    },
    onError: () => toast.error("Something Went Wrong"),
  });
};
