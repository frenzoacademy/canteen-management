import { signIn } from "../../../../auth";

const OthersLoginForm = () => {
  // todo -  change to specific keys
  const handleSubmit = async (values) => {
    const res = await signIn("credentials", {
      redirect: false,
      email: values.email,
      password: values.password,
      label: "login",
      redirectTo: callbackUrl,
    });
  };
  return (
    <form>
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
        />
      </div>
      <div className="mt-5">
        <label
          htmlFor="password"
          className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
        >
          Password
        </label>
        <input
          type="text"
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="Password"
        />
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
