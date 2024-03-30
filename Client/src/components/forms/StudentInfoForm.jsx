"use client";
import {
  useAddStudent,
  useEditStudent,
  useGetStudent,
} from "@/features/students/students.hooks";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import useScanDetection from "use-scan-detection-react18";

const StudentInfoForm = ({ editId }) => {
  const { mutate: addStudent } = useAddStudent();
  const { mutate: updateStudent } = useEditStudent();
  const { data } = useGetStudent(editId);
  const [code, setCode] = useState(null);
  const [image, setImage] = useState([]);

  const {
    register,
    handleSubmit,
    reset,
    setValue,
    watch,
    formState: { errors },
  } = useForm({
    defaultValues: {
      First_name: editId && data ? data.first_name : "",
      Last_name: editId && data ? data.last_name : "",
      aadhar_number: editId && data ? data.aadhar_number : "",
      address: editId && data ? data.address : "",
      date_time: editId && data ? data.date_time : "",
      department: editId && data ? data.department : "",
      mob_number: editId && data ? data.mob_number : "",
      rfid_Number: editId && data ? data.rfid_Number : code,
      email: editId && data ? data.email : "",
      file: editId && data ? data.file : "",
    },
  });
  const passwordValue = watch("password");

  useEffect(() => {
    if (data && editId) {
      setValue("First_name", data.first_name || "");
      setValue("Last_name", data.last_name || "");
      setValue("aadhar_number", data.aadhar_number || "");
      setValue("address", data.address || "");
      setValue("date_time", data.date_time || "");
      setValue("department", data.department || "");
      setValue("mob_number", data.mob_number || "");
      setValue("rfid_Number", data.rfid_Number || code || "");
      setValue("email", data.email || "");
      setValue("file", data.file || "");
    }
  }, [data, editId, setValue, code]);

  useEffect(() => {
    setValue("rfid_Number", code);
  }, [code, setValue]);

  useEffect(() => {
    setValue("file", image);
  }, [image, setValue]);

  useScanDetection({
    onComplete: (code) => {
      setCode(code);
    },
  });

  const onSubmit = (data) => {
    try {
      const formData = new FormData();

      Object.keys(data).forEach((key) => {
        if (key === "file" && data[key] instanceof File) {
          formData.append(key, data[key]);
        } else {
          formData.append(key, data[key]);
        }
      });

      const response = editId
        ? updateStudent({ id: editId, values: data })
        : addStudent(formData);

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
      <h1 className="text-xl font-bold mb-5">Student Info Form</h1>
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
              {...register("First_name", {
                required: "This filed is mandatory",
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.First_name?.message}
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
              placeholder="Last name"
              {...register("Last_name", {
                required: "This filed is mandatory",
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.Last_name?.message}
            </h1>
          </div>
          <div>
            <label
              htmlFor="date"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Date
            </label>
            <input
              type="date"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              {...register("date_time", {
                required: "This filed is mandatory",
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.date_time?.message}
            </h1>
          </div>
          <div>
            <label
              htmlFor=""
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Department
            </label>
            <input
              type="text"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Department"
              {...register("department", {
                required: "This filed is mandatory",
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.department?.message}
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
              htmlFor="rfid-number"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              RFID Number
            </label>
            <input
              type="number"
              disabled
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="RFID"
              {...register("rfid_Number", {
                required: "This filed is mandatory",
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.rfid_Number?.message}
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
          <div>
            <label
              htmlFor="confirmPassword"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Confirm Password
            </label>
            <input
              type="password"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Confirm password"
              {...register("confirmPassword", {
                required: "This field is required",
                validate: (value) =>
                  value === passwordValue || "The passwords do not match",
              })}
            />
            <h1 className="mt-1 text-sm text-red-600 font-medium">
              {errors?.confirmPassword?.message}
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
            {editId ? "Update" : "Submit"}
          </button>
        </div>
      </form>
    </div>
  );
};

export default StudentInfoForm;
