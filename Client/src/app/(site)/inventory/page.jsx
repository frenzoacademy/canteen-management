"use client";
import PageHeader from "@/components/PageHeader";
import { useGetFoodInventories } from "@/features/foodInventory/foodInventory.hooks";
import Image from "next/image";
import { useRouter } from "next/navigation";

const Page = () => {
  const route = useRouter();
  const { data } = useGetFoodInventories();
  const food = [
    {
      name: "Food 1",
      image:
        "https://images.pexels.com/photos/14774995/pexels-photo-14774995.jpeg?auto=compress&cs=tinysrgb&w=600",
      quantity_in_stock: 10,
      availability: "true",
      amount: 5.99,
    },
    {
      name: "Food 2",
      image:
        "https://images.pexels.com/photos/17321493/pexels-photo-17321493/free-photo-of-a-plate-with-four-meatballs-with-toothpicks.jpeg?auto=compress&cs=tinysrgb&w=600",
      quantity_in_stock: 20,
      availability: "true",
      amount: 8.49,
    },
    {
      name: "Food 3",
      image:
        "https://images.pexels.com/photos/14774988/pexels-photo-14774988.jpeg?auto=compress&cs=tinysrgb&w=600",
      quantity_in_stock: 15,
      availability: "false",
      amount: 6.99,
    },
  ];
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
            {data?.map((item, index) => (
              <tr
                key={index}
                className={(index + 1) % 2 === 0 ? "bg-gray-100" : "bg-white"}
              >
                <td className=" px-4 py-2 flex items-center gap-4">
                  <Image
                    width={100}
                    height={100}
                    src={item.image}
                    alt={`${item.name}`}
                    className="w-12 h-12 object-cover rounded-full"
                  />
                  <h1 className="font-medium text-base">{item.name}</h1>
                </td>

                <td className=" px-4 py-2">{item.quantity}</td>
                <td className=" px-4 py-2">{item.isAvailability}</td>
                <td className=" px-4 py-2">$ {item.amount}</td>
                <td className=" px-4 py-2 text-center">
                  <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Edit
                  </button>
                </td>
                <td className=" px-4 py-2 text-center">
                  <button className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
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
