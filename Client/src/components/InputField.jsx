"use client";
import { useFormContext } from "react-hook-form";

const InputField = ({ name, label }) => {
  const { register } = useFormContext();

  return (
    <div className="relative mb-6">
      <input
        type="text"
        id={name}
        {...register(name)}
        className="w-full px-3 py-2 border-b-2 border-gray-300 focus:outline-none focus:border-blue-500"
      />
      <label
        htmlFor={name}
        className="absolute left-3 transition-all duration-300 top-2 text-gray-700"
      >
        {label}
      </label>
    </div>
  );
};

export default InputField;
