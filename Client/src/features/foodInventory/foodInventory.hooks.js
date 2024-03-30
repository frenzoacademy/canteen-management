import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { toast } from "sonner";
import {
  createFoodInventory,
  getFoodInventories,
  getFoodInventory,
  removeFoodInventory,
  updateFoodInventory,
} from "./foodInventory.api";

export const useGetFoodInventories = () =>
  useQuery({
    queryKey: ["getFoodInventories"],
    queryFn: () => getFoodInventories(),
    enabled: true,
  });

export const useGetFoodInventory = (id) =>
  useQuery({
    queryKey: ["getFoodInventories", id],
    queryFn: () => getFoodInventory(id),
    enabled: !!id,
  });

export const useAddFoodInventory = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: createFoodInventory,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["getFoodInventories"],
        refetchType: "active",
      });
      toast.success("Food Inventory added successfully");
    },
    onError: () => {
      toast.error("Something went Wrong!");
    },
  });
};

export const useEditFoodInventory = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: updateFoodInventory,
    onSuccess: (data, { id }) => {
      queryClient.setQueryData(["getFoodInventories", id], { data });
      queryClient.invalidateQueries({ queryKey: ["getFoodInventories"] });
      toast.success("Food Inventory updated successfully");
    },
    onError: () => {
      toast.error("Something Went Wrong");
    },
  });
};

export const useRemoveFoodInventory = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: removeFoodInventory,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["getFoodInventories"],
        refetchType: "active",
      });
      setTimeout(
        () => toast.success("Food Inventory deleted successfully"),
        1000
      );
    },
    onError: () => toast.error("Something Went Wrong"),
  });
};
