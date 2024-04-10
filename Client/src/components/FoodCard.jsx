"use client";
import { useEffect, useState } from "react";
import Image from "next/image";

const FoodCard = ({
  food_id,
  image,
  name,
  amount,
  addToCartHandler,
  disabled,
  isSuccess,
}) => {
  const [quantity, setQuantity] = useState(1);
  const [showAddToCart, setShowAddToCart] = useState(true);

  const decreaseQuantity = () => {
    if (quantity > 1) {
      const newQuantity = quantity - 1;
      const newTotalAmount = newQuantity * amount;
      setQuantity(newQuantity);
      addToCartHandler({
        food_id,
        quantity: newQuantity,
        totalAmount: parseInt(newTotalAmount),
      });
    } else {
      setShowAddToCart(true);
      addToCartHandler({
        food_id,
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
      food_id,
      quantity: newQuantity,
      totalAmount: parseInt(newTotalAmount),
    });
  };

  const addToCart = () => {
    setShowAddToCart(false);
    return addToCartHandler({
      food_id,
      quantity,
      totalAmount: quantity * parseInt(amount),
    });
  };

  useEffect(() => {
    if (isSuccess) {
      setShowAddToCart(true);
    }
  }, [isSuccess]);

  return (
    <div className="max-w-xs rounded overflow-hidden shadow-lg">
      <div className="flex justify-center items-center border h-[200px] w-[200px]">
        <Image
          width={250}
          height={250}
          src={`data:image/png;base64,${image}`}
          alt={"food"}
        />
      </div>
      <div className="px-6 py-4">
        <div className="font-bold text-xl mb-2">{name}</div>
        <p className="text-gray-700 text-base">₹ {amount}</p>
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
