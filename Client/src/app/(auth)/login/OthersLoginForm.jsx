"use client";
import { signIn } from "next-auth/react";
import { useSearchParams } from "next/navigation";
import { useState } from "react";
import { useForm } from "react-hook-form";

const OthersLoginForm = () => {
  const searchParams = useSearchParams();
  const callbackUrl = searchParams.get("callbackUrl");

  const [error, setError] = useState("");
  const {
    register,
    handleSubmit,
    watch,
    reset,
    formState: { errors },
  } = useForm({
    defaultValues: {
      email: "",
      password: "",
    },
  });

  const onSubmit = async ({ email, password }) => {
    try {
      const response = await signIn("credentials", {
        redirect: false,
        email,
        password,
        callbackUrl: callbackUrl || "/",
      });
      const { ok, error, url } = response;
      if (!ok && error === "CredentialsSignin") {
        setError({ message: "Bad email or password !" });
      }

      if (ok && url) router.push(url);
    } catch (error) {
      setError({ message: error.message });
    }
    reset();
  };
  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div>
        <label
          htmlFor="email"
          className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
        >
          Email
        </label>
        <input
          type="text"
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="Email"
          {...register("email", { required: "This filed is mandatory" })}
        />
        <h1 className="mt-1 text-sm text-red-600 font-medium">
          {errors?.email?.message}
        </h1>
      </div>
      <div className="mt-5">
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
          {...register("password", { required: "This filed is mandatory" })}
        />
        <h1 className="mt-1 text-sm text-red-600 font-medium">
          {errors?.password?.message}
        </h1>
      </div>
      <div>
        <h1 className="text-sm text-red-600">{error.message}</h1>
      </div>
      <div>
        <button
          type="submit"
          className="px-4 py-2 bg-black text-white rounded-lg mt-3"
        >
          Submit
        </button>
      </div>
    </form>
  );
};

export default OthersLoginForm;
