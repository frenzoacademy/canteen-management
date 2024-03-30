import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { toast } from "sonner";
import {
  createPurchaseOrder,
  getPurchaseOrder,
  getPurchaseOrders,
  removePurchaseOrder,
  updatePurchaseOrder,
} from "./purchaseOrder.api";

export const useGetPurchaseOrders = () =>
  useQuery({
    queryKey: ["getPurchaseOrders"],
    queryFn: () => getPurchaseOrders(),
    enabled: true,
  });

export const useGetPurchaseOrder = (id) =>
  useQuery({
    queryKey: ["getPurchaseOrders", id],
    queryFn: () => getPurchaseOrder(id),
    enabled: !!id,
  });

export const useAddPurchaseOrder = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: createPurchaseOrder,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["getPurchaseOrders"],
        refetchType: "active",
      });
      toast.success("Purchase Order added successfully");
    },
    onError: () => {
      toast.error("Something went Wrong!");
    },
  });
};

export const useEditPurchaseOrder = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: updatePurchaseOrder,
    onSuccess: (data, { id }) => {
      queryClient.setQueryData(["getPurchaseOrders", id], { data });
      queryClient.invalidateQueries({ queryKey: ["getPurchaseOrders"] });
      toast.success("Purchase Order updated successfully");
    },
    onError: () => {
      toast.error("Something Went Wrong");
    },
  });
};

export const useRemovePurchaseOrder = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: removePurchaseOrder,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["getPurchaseOrders"],
        refetchType: "active",
      });
      setTimeout(
        () => toast.success("Purchase Order deleted successfully"),
        1000
      );
    },
    onError: () => toast.error("Something Went Wrong"),
  });
};
