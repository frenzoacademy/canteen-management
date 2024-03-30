"use client";
import { ArrowUpRightIcon } from "@heroicons/react/24/outline";
import { motion } from "framer-motion";

const WalletBalance = () => {
  return (
    <div className="bg-black px-4 py-5 rounded-md flex flex-col gap-4">
      <h1 className="text-white  text-xl flex gap-2 items-center">
        Gross Volume Purchased <ArrowUpRightIcon className="h-4 w-4" />
      </h1>
      <div className="text-white flex gap-2 items-end">
        <span className="text-2xl font-medium mb-[2px]">₹</span>
        <>
          {[..."800"].map((letter, index) => (
            <motion.h1
              key={index}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: index * 0.1 }}
              className="text-6xl font-semibold tracking-tighter "
            >
              {letter}
            </motion.h1>
          ))}
        </>
      </div>
    </div>
  );
};

export default WalletBalance;
