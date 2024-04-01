"use client";
import {
  useAddCanteenUser,
  useEditCanteenUser,
  useGetCanteenUser,
} from "@/features/canteenUser/canteenUser.hooks";
import { useRouter } from "next/navigation";
import { useEffect, useRef, useState } from "react";
import { useForm } from "react-hook-form";

const CanteenUserForm = ({ editId }) => {
  const [image, setImage] = useState([]);
  const route = useRouter();
  const { mutate: addCanteenManager, isSuccess: addSuccess } =
    useAddCanteenUser();
  const { data } = useGetCanteenUser(editId);
  const { mutate: updateCanteenManager, isSuccess: editSuccess } =
    useEditCanteenUser();

  const {
    register,
    handleSubmit,
    watch,
    setValue,
    control,
    formState: { errors },
    reset,
  } = useForm({
    defaultValues: {
      first_name: "",
      last_name: "",
      email: "",
      address: "",
      aadhar_number: "",
      mob_number: "",
      file: "",
      password: "",
    },
  });

  if (addSuccess || editSuccess) {
    route.push(`/canteen-info`);
  }

  useEffect(() => {
    setValue("file", image);
  }, [image, setValue]);

  useEffect(() => {
    if (data && editId) {
      setValue("first_name", data.first_name || "");
      setValue("last_name", data.last_name || 0);
      setValue("email", data.email || false);
      setValue("address", data.address || false);
      setValue("aadhar_number", data.aadhar_number || false);
      setValue("mob_number", data.mob_number || false);
      setValue("password", data.password || false);
      setValue("file", data.photoBase64 || false);
    }
  }, [data, editId]);

  const onSubmit = (data) => {
    try {
      const formData = new FormData();

      Object.keys(data).forEach((key) => {
        formData.append(key, data[key]);
      });

      const response = editId
        ? updateCanteenManager({ id: editId, values: data })
        : addCanteenManager(formData);
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
      <h1 className="text-xl font-bold mb-5">Canteen User Form</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="grid sm:grid-cols-3 grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-5">
          <div>
            <label
              htmlFor="firstName"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              First name
            </label>
            <input
              type="text"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="First name"
              {...register("first_name", {
                required: "This filed is mandatory",
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.first_name?.message}
            </h1>
          </div>
          <div>
            <label
              htmlFor="lastName"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Last name
            </label>
            <input
              type="text"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Last Name"
              {...register("last_name", {
                required: "This filed is mandatory",
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.last_name?.message}
            </h1>
          </div>

          <div>
            <label
              htmlFor="Image"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Image{" "}
            </label>
            <input
              type="file"
              onChange={(e) => setImage(e.target.files[0])}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            />
          </div>
          <div>
            <label
              htmlFor="email"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Email
            </label>
            <input
              type="email"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="example@gmail.com"
              {...register("email", {
                required: "This filed is mandatory",
                pattern: {
                  value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                  message: "Invalid email address",
                },
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.email?.message}
            </h1>
          </div>

          <div>
            <label
              htmlFor="phoneNumber"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Phone Number
            </label>
            <input
              type="number"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="9874561230"
              {...register("mob_number", {
                required: "This filed is mandatory",
                minLength: {
                  value: 10,
                  message: "Invalid phone number",
                },
                maxLength: {
                  value: 10,
                  message: "Invalid phone number",
                },
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.mob_number?.message}
            </h1>
          </div>
          <div>
            <label
              htmlFor="aadhar"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Aadhar Number
            </label>
            <input
              type="number"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="1234-4668-9876"
              {...register("aadhar_number")}
            />
          </div>
          <div>
            <label
              htmlFor="address"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Address
            </label>
            <input
              type="text"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Address"
              {...register("address")}
            />
          </div>
          <div>
            <label
              htmlFor="password"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Password
            </label>
            <input
              type="password"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Password"
              {...register("password", {
                required: "This field is required",
                minLength: {
                  value: 8,
                  message: "Password must have at least 8 characters",
                },
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.password?.message}
            </h1>
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

export default CanteenUserForm;
