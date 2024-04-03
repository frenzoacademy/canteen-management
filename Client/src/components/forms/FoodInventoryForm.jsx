"use client";
import {
  useAddFoodInventory,
  useEditFoodInventory,
  useGetFoodInventory,
} from "@/features/foodInventory/foodInventory.hooks";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";

const FoodInventoryForm = ({ editId }) => {
  const route = useRouter();
  const { mutate: addInventory, isSuccess } = useAddFoodInventory();
  const { mutate: updateInventory, isSuccess: editSuccess } =
    useEditFoodInventory();
  const { data } = useGetFoodInventory(editId);
  const [image, setImage] = useState([]);

  const {
    register,
    handleSubmit,
    setValue,
    watch,
    reset,
    formState: { errors },
  } = useForm({
    defaultValues: {
      name: "",
      file: "",
      quantity: 0,
      isAvailability: false,
      breakfast: false,
      lunch: false,
      eveningfood: false,
      dinner: false,
      alltime: false,
      amount: 0,
    },
  });

  if (isSuccess || editSuccess) {
    route.push(`/inventory`);
  }

  useEffect(() => {
    if (data && editId) {
      setValue("name", data.name || "");
      setValue("quantity", data.quantity || 0);
      setValue("isAvailability", data.availability || false);
      setValue("breakfast", data.breakfast || false);
      setValue("lunch", data.lunch || false);
      setValue("eveningfood", data.eveningfood || false);
      setValue("dinner", data.dinner || false);
      setValue("alltime", data.alltime || false);
      setValue("amount", data.amount || 0);
    }
  }, [data, editId]);

  const handleFileChange = (e) => {
    const selectedImage = e.target.files[0];
    setImage(selectedImage);
  };

  useEffect(() => {
    if (!editId) {
      setValue("file", image);
    }
  }, [image, setValue]);

  const onSubmit = (data) => {
    try {
      const formData = new FormData();

      Object.keys(data).forEach((key) => {
        formData.append(key, data[key]);
      });

      const response = editId
        ? updateInventory({ id: editId, values: formData })
        : addInventory(formData);
      reset();
    } catch (error) {
      console.error("Error submitting form data:", error);
    }
  };

  const cancel = () => {
    reset();
  };

  return (
    <div>
      <h1 className="text-xl font-bold mb-5">Food Inventory Form</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="grid sm:grid-cols-3 grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-5">
          <div>
            <label
              htmlFor="name"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Name
            </label>
            <input
              type="text"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Name"
              {...register("name", { required: "This filed is mandatory" })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.name?.message}
            </h1>
          </div>

          {!editId && (
            <>
              <div>
                <label
                  htmlFor="quantity"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  Quantity
                </label>
                <input
                  type="number"
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Quantity"
                  {...register("quantity")}
                />
              </div>
              <div>
                <label
                  htmlFor="Image"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  Image
                </label>
                <input
                  type="file"
                  onChange={handleFileChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                />
              </div>
            </>
          )}
          <div>
            <h1 className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
              Is Available
            </h1>
            <div className="flex items-center justify-start border-2 border-black gap-5 px-4 py-4 rounded-md">
              <input
                type="checkbox"
                className="h-5 w-5 accent-black"
                {...register("isAvailability")}
              />{" "}
              <label
                htmlFor="isAvailability"
                className="text-sm font-medium text-gray-900 dark:text-white"
              >
                Is Available
              </label>
            </div>
          </div>
          <div>
            <label
              htmlFor="amount"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Amount
            </label>
            <input
              type="number"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Amount"
              {...register("amount", { required: "This filed is mandatory" })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.amount?.message}
            </h1>
          </div>
          <div className="col-start-1  sm:col-span-3 md:col-span-3 col-span-1">
            <h1 className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
              Meal Timing
            </h1>
            <div className="grid sm:grid-cols-3 grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-5">
              <div className="flex items-center justify-start border-2 border-black gap-5 px-4 py-4 rounded-md">
                <input
                  type="checkbox"
                  className="h-5 w-5 rounded-md accent-black"
                  {...register("breakfast")}
                />{" "}
                <label
                  htmlFor="breakfast"
                  className="text-sm font-medium text-gray-900 dark:text-white"
                >
                  Break Fast
                </label>
              </div>
              <div className="flex items-center justify-start gap-5 border-2 border-black px-4 py-4 rounded-md">
                <input
                  type="checkbox"
                  className="h-5 w-5 accent-black"
                  {...register("lunch")}
                />{" "}
                <label
                  htmlFor="lunch"
                  className="text-sm font-medium text-gray-900 dark:text-white"
                >
                  Lunch
                </label>
              </div>
              <div className="flex items-center justify-start gap-5 border-2 border-black px-4 py-4 rounded-md">
                <input
                  type="checkbox"
                  className="h-5 w-5 accent-black"
                  {...register("eveningfood")}
                />{" "}
                <label
                  htmlFor="eveningfood"
                  className="text-sm font-medium text-gray-900 dark:text-white"
                >
                  Snakcs
                </label>
              </div>
              <div className="flex items-center justify-start gap-5 border-2 border-black px-4 py-4 rounded-md">
                <input
                  type="checkbox"
                  className="h-5 w-5 accent-black"
                  {...register("dinner")}
                />{" "}
                <label
                  htmlFor="dinner"
                  className="text-sm font-medium text-gray-900 dark:text-white"
                >
                  Dinner
                </label>
              </div>
              <div className="flex items-center justify-start gap-5 border-2 border-black px-4 py-4 rounded-md">
                <input
                  type="checkbox"
                  className="h-5 w-5 accent-black"
                  {...register("alltime")}
                />{" "}
                <label
                  htmlFor="allTime"
                  className="text-sm font-medium text-gray-900 dark:text-white"
                >
                  All Time
                </label>
              </div>
            </div>
          </div>
        </div>
        <div className="border-t border-t-slate-200  mt-3 flex justify-between">
          <button
            type="submit"
            onClick={() => cancel()}
            className="px-4 py-2 bg-red-600 text-white rounded-lg mt-3"
          >
            Cancel
          </button>
          <button
            type="submit"
            className="px-4 py-2 bg-black text-white rounded-lg mt-3"
          >
            Submit
          </button>
        </div>
      </form>
    </div>
  );
};

export default FoodInventoryForm;
