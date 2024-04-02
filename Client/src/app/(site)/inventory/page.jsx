"use client";
import PageHeader from "@/components/PageHeader";
import {
  useGetFoodInventories,
  useRemoveFoodInventory,
} from "@/features/foodInventory/foodInventory.hooks";
import Image from "next/image";
import Link from "next/link";
import { useRouter } from "next/navigation";

const Page = () => {
  const route = useRouter();
  const { data } = useGetFoodInventories();
  const { mutate: removeFoods } = useRemoveFoodInventory();
  console.log(data);

  return (
    <div>
      <PageHeader
        pageTitle="Food Inventory Informations"
        buttonTitle="Add Food Inventory"
        navigation={`/inventory/add`}
      />

      <div className="overflow-x-auto mt-10 rounded-md shadow-md">
        <table className="table-auto w-full border  border-black  ">
          <thead>
            <tr className="bg-black text-white">
              <th className="px-4 py-3 text-left"> Name</th>
              <th className="px-4 py-3 text-left">Quantity</th>
              <th className="px-4 py-3 text-left">Avalability</th>
              <th className="px-4 py-3 text-left">Amount</th>
              <th className="px-4 py-3 text-center">Edit</th>
              <th className="px-4 py-3 text-center">Delete</th>
            </tr>
          </thead>
          <tbody>
            {data &&
              data?.map((item, index) => (
                <tr
                  key={index}
                  className={(index + 1) % 2 === 0 ? "bg-gray-100" : "bg-white"}
                >
                  <td className=" px-4 py-2 flex items-center gap-4">
                    <Image
                      width={100}
                      height={100}
                      src={`data:image/png;base64,${item.photoBase64}`}
                      alt={`${item.name}`}
                      className="w-12 h-12 object-cover rounded-full"
                    />
                    <h1 className="font-medium text-base">{item.name}</h1>
                  </td>

                  <td className=" px-4 py-2">{item.quantity}</td>
                  <td className=" px-4 py-2">
                    {item.availability ? "true" : "false"}
                  </td>
                  <td className=" px-4 py-2">₹ {item.amount}</td>
                  <td className=" px-4 py-2 text-center">
                    <Link
                      href={`/inventory/edit/${item?.food_id}`}
                      className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                    >
                      Edit
                    </Link>
                  </td>
                  <td className=" px-4 py-2 text-center">
                    <button
                      onClick={() => removeFoods(item.food_id)}
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
