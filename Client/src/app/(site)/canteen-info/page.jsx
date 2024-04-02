"use client";
import PageHeader from "@/components/PageHeader";
import {
  useGetCanteenUsers,
  useRemoveCanteenUser,
} from "@/features/canteenUser/canteenUser.hooks";
import Image from "next/image";
import Link from "next/link";

const Page = () => {
  const { data } = useGetCanteenUsers();
  const { mutate } = useRemoveCanteenUser();

  return (
    <div>
      <PageHeader
        pageTitle="Canteen user's Informations"
        buttonTitle="Add Canteen user"
        navigation={`/canteen-info/add`}
      />

      <div className="overflow-x-auto mt-10 rounded-md shadow-md">
        <table className="table-auto w-full border  border-black  ">
          <thead>
            <tr className="bg-black text-white">
              <th className="px-4 py-3 text-left"> Name</th>
              <th className="px-4 py-3 text-left">Phone number</th>
              <th className="px-4 py-3 text-left">Email</th>
              <th className="px-4 py-3 text-center">Edit</th>
              <th className="px-4 py-3 text-center">Delete</th>
            </tr>
          </thead>
          <tbody>
            {data?.map((employee, index) => (
              <tr
                key={index}
                className={(index + 1) % 2 === 0 ? "bg-gray-100" : "bg-white"}
              >
                <td className=" px-4 py-2 flex items-center gap-4">
                  <Image
                    width={100}
                    height={100}
                    src={`data:image/png;base64,${employee.imageBase64}`}
                    alt={`${employee.first_Name} ${employee.last_Name}`}
                    className="w-12 h-12 object-cover rounded-full"
                  />
                  <h1 className="font-medium text-base">
                    {employee.first_name} {employee.last_name}
                  </h1>
                </td>

                <td className=" px-4 py-2">{employee.mob_number}</td>
                <td className=" px-4 py-2">{employee.email}</td>
                <td className=" px-4 py-2 text-center">
                  <Link
                    href={`/canteen-info/edit/${employee.id}`}
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                  >
                    Edit
                  </Link>
                </td>
                <td className=" px-4 py-2 text-center">
                  <button
                    onClick={() => mutate(employee.id)}
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Page;
