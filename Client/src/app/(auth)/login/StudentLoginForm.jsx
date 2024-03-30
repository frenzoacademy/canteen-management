import React from "react";

const StudentLoginForm = () => {
  return (
    <div>
      <form>
        <div>
          <label
            htmlFor="rfid"
            className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >
            RFID
          </label>
          <input
            type="text"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="RFID"
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
    </div>
  );
};

export default StudentLoginForm;
