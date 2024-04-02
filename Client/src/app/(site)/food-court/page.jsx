"use client";
import FoodCard from "@/components/FoodCard";
import { useGetFoodInventories } from "@/features/foodInventory/foodInventory.hooks";
import { useAddPurchaseOrder } from "@/features/purchase-order/purchaseOrder.hooks";
import { ArrowRightIcon } from "@heroicons/react/24/outline";
import { useState } from "react";

const Page = () => {
  const [cart, setCart] = useState([]);
  console.log(cart);
  const [walletAmount, setWalletAmount] = useState(800);
  const { data } = useGetFoodInventories();
  const { mutate } = useAddPurchaseOrder();

  const handlePurchase = () => {
    mutate(cart);
  };

  const addToCartHandler = (item) => {
    console.log({ item });
    const itemAmount = parseInt(item.totalAmount);
    const itemQuantity = parseInt(item.quantity);

    const existingCartItem = cart.find(
      (cartItem) => cartItem.food_id === item.food_id
    );
    const existingQuantity = existingCartItem ? existingCartItem.quantity : 0;
    const existingAmount = existingCartItem ? existingCartItem.totalAmount : 0;

    let changeInAmount;
    if (itemQuantity === 0) {
      changeInAmount = -existingAmount;
    } else {
      changeInAmount =
        (itemAmount / itemQuantity) * (itemQuantity - existingQuantity);
    }

    const newWalletAmount = walletAmount - parseInt(changeInAmount);

    if (newWalletAmount >= 0) {
      const existingItemIndex = cart.findIndex(
        (cartItem) => cartItem.food_id === item.food_id
      );

      if (existingItemIndex !== -1) {
        const updatedCart = [...cart];
        updatedCart[existingItemIndex].quantity = parseInt(item.quantity);
        updatedCart[existingItemIndex].totalAmount = parseInt(item.totalAmount);

        if (item.quantity === 0) {
          updatedCart.splice(existingItemIndex, 1);
        }

        setCart(updatedCart);
      } else {
        if (item.quantity > 0) {
          const newItem = {
            ...item,
            studentForm: {
              student_id: 1,
            },
            status: "success",
            date: new Date(),
          };
          setCart([...cart, newItem]);
        }
      }

      setWalletAmount(newWalletAmount);
    }
  };
  return (
    <div>
      <div className="px-3 py-2 bg-red-200 mb-5 text-red-600 border-red-500 border rounded-md">
        <h1 className="font-normal text-sm ">Scan your RFID card to order</h1>
      </div>
      <div className="bg-black py-4 px-5 rounded-md text-white flex justify-between items-center">
        <div>
          <h1 className="font-medium text-lg">Your</h1>
          <h1 className=" font-bold text-xl flex gap-5 items-center">
            Wallet Amount <ArrowRightIcon className="h-5 w-5" />
          </h1>
        </div>
        <h1 className="font-black text-2xl">$ {walletAmount}</h1>
      </div>
      <div className="flex gap-5 flex-wrap mt-10">
        {data?.map((food, index) => (
          <FoodCard
            key={index}
            food_id={food?.food_id}
            name={food?.name}
            amount={food?.amount}
            image={food?.photoBase64}
            addToCartHandler={addToCartHandler}
            disabled={food?.amount > walletAmount}
          />
        ))}
      </div>
      <div className="  fixed bottom-0 right-0 p-10">
        <button
          type="submit"
          onClick={handlePurchase}
          className="px-4 py-2 bg-green-600 text-white rounded-lg mt-3"
        >
          Purchase
        </button>
      </div>
    </div>
  );
};

export default Page;
