"use client";
import { useState } from "react";
import Image from "next/image";

const FoodCard = ({
  id,
  image,
  name,
  amount,
  addToCartHandler,
  disabled,
  isPopOverOpen,
  setIsPopOverOpen,
}) => {
  const [quantity, setQuantity] = useState(1);
  const [showAddToCart, setShowAddToCart] = useState(true);

  const decreaseQuantity = () => {
    if (quantity > 1) {
      const newQuantity = quantity - 1;
      const newTotalAmount = newQuantity * amount;
      setQuantity(newQuantity);
      addToCartHandler({
        id,
        quantity: newQuantity,
        totalAmount: parseInt(newTotalAmount),
      });
    } else {
      setShowAddToCart(true);
      addToCartHandler({
        id,
        quantity: 0,
        totalAmount: 0,
      });
    }
  };

  const increaseQuantity = () => {
    const newQuantity = quantity + 1;
    const newTotalAmount = newQuantity * amount;
    setQuantity(newQuantity);
    addToCartHandler({
      id,
      quantity: newQuantity,
      totalAmount: parseInt(newTotalAmount),
    });
  };

  const addToCart = () => {
    if (isPopOverOpen) {
      setShowAddToCart(false);
      return addToCartHandler({
        id,
        quantity,
        totalAmount: quantity * parseInt(amount),
      });
    } else {
      setIsPopOverOpen(true);
    }
  };

  return (
    <div className="max-w-xs rounded overflow-hidden shadow-lg">
      <div className="flex justify-center items-center border">
        <Image width={250} height={100} src={image} alt={"food"} />
      </div>
      <div className="px-6 py-4">
        <div className="font-bold text-xl mb-2">{name}</div>
        <p className="text-gray-700 text-base">$ {amount}</p>
        <div className="flex justify-between mt-4">
          {showAddToCart ? (
            <button
              onClick={addToCart}
              disabled={disabled}
              className="bg-black hover:bg-opacity-80 text-white font-bold py-2 px-4 rounded disabled:bg-opacity-50 disabled:cursor-not-allowed"
            >
              Add Item
            </button>
          ) : (
            <div className="flex">
              <button
                onClick={decreaseQuantity}
                className="bg-gray-200 text-gray-700 font-bold py-2 px-4 rounded-l"
              >
                -
              </button>
              <span className="bg-gray-200 text-gray-700 font-bold py-2 px-4">
                {quantity}
              </span>
              <button
                onClick={increaseQuantity}
                disabled={disabled}
                className="bg-gray-200 text-gray-700 font-bold py-2 px-4 rounded-r disabled:cursor-not-allowed"
              >
                +
              </button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default FoodCard;
