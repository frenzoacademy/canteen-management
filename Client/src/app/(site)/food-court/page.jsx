"use client";
import FoodCard from "@/components/FoodCard";
import RfidScanner from "@/components/RfidScanner";
import { ArrowRightIcon } from "@heroicons/react/24/outline";
import { useEffect, useState } from "react";

const foods = [
  {
    id: 1,
    food_name: "Food 1",
    amount: "100",
    image_url:
      "https://images.pexels.com/photos/14775031/pexels-photo-14775031.jpeg?auto=compress&cs=tinysrgb&w=600",
  },
  {
    id: 2,
    food_name: "Food 2",
    amount: "150",
    image_url:
      "https://images.pexels.com/photos/14774996/pexels-photo-14774996.jpeg?auto=compress&cs=tinysrgb&w=600",
  },
  {
    id: 3,
    food_name: "Food 3",
    amount: "200",
    image_url:
      "https://images.pexels.com/photos/14774990/pexels-photo-14774990.jpeg?auto=compress&cs=tinysrgb&w=600",
  },
];

const Page = () => {
  const [cart, setCart] = useState([]);
  const [walletAmount, setWalletAmount] = useState(800);
  const [rfidNumber, setRfidNumber] = useState(null);
  const [message, setMessage] = useState("");
  const [isPopOverOpen, setIsPopOverOpen] = useState(true);

  const getRfid = (id) => {
    setRfidNumber(id);
  };

  useEffect(() => {
    if (rfidNumber === "2060149593") {
      setIsPopOverOpen(false);
    }
  }, [rfidNumber]);

  const openPopOver = () => {
    setIsPopOverOpen(true);
  };

  const closePopOver = () => {
    setIsPopOverOpen(false);
  };

  const addToCartHandler = (item) => {
    const itemAmount = parseInt(item.totalAmount);
    const itemQuantity = parseInt(item.quantity);

    const existingCartItem = cart.find((cartItem) => cartItem.id === item.id);
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
        (cartItem) => cartItem.id === item.id
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
          setCart([...cart, item]);
        }
      }

      setWalletAmount(newWalletAmount);
    }
  };
  return (
    <div>
      <h1 className="text-2xl font-medium mb-5">Order Food</h1>
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
        {foods?.map((food, index) => (
          <FoodCard
            key={index}
            id={food?.id}
            name={food?.food_name}
            amount={food?.amount}
            image={food?.image_url}
            addToCartHandler={addToCartHandler}
            isPopOverOpen={isPopOverOpen}
            setIsPopOverOpen={setIsPopOverOpen}
            disabled={food?.amount > walletAmount}
          />
        ))}
      </div>
      <RfidScanner
        isOpen={isPopOverOpen}
        closeModal={closePopOver}
        getRfid={getRfid}
        isRfid={rfidNumber}
      />
    </div>
  );
};

export default Page;
