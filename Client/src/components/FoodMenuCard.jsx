"use client";
import Image from "next/image";
import PopOver from "./PopOver";
import { useState } from "react";

const FoodMenuCard = ({ image, name, amount, id, quantity }) => {
  const [isPopOverOpen, setIsPopOverOpen] = useState(false);

  const openPopOver = () => {
    setIsPopOverOpen(true);
  };

  const closePopOver = () => {
    setIsPopOverOpen(false);
  };
  return (
    <div className="max-w-xs rounded overflow-hidden shadow-lg">
      <div className="flex justify-center items-center  w-[200px] h-[200px]">
        <Image
          width={250}
          height={250}
          src={`data:image/png;base64,${image}`}
          className="object-cover object-center"
          alt={"food"}
        />
      </div>
      <div className="px-6 py-4">
        <div className="font-bold text-xl mb-2">{name}</div>
        <p className="text-gray-700 text-base">₹ {amount}</p>
        <p className="text-gray-400 text-sm mt-2">Quantity : {quantity}</p>
        <div className="flex justify-between mt-4">
          <div className="flex">
            <button
              type="button"
              onClick={openPopOver}
              className="inline-flex justify-center rounded-md border border-transparent bg-black px-4 py-2 text-sm font-medium text-white hover:bg-opacity-80 focus:outline-none focus-visible:ring-2 focus-visible:ring-blue-500 focus-visible:ring-offset-2"
            >
              Update Quantity
            </button>
          </div>
        </div>
      </div>
      <PopOver
        isOpen={isPopOverOpen}
        closeModal={closePopOver}
        foodName={name}
        food_id={id}
      />
    </div>
  );
};

export default FoodMenuCard;
