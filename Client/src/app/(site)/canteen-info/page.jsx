"use client";
import PageHeader from "@/components/PageHeader";
import Image from "next/image";

const Page = () => {
  const canteen = [
    {
      firstName: "John",
      lastName: "Doe",
      aadharNumber: "1234 5678 9012",
      address: "123 Main St, Cityville",
      date: "1990-01-01",
      department: "IT",
      phoneNumber: "123-456-7890",
      rfid: "RFID123",
      email: "john.doe@example.com",
      image:
        "https://images.pexels.com/photos/2859616/pexels-photo-2859616.jpeg?auto=compress&cs=tinysrgb&w=600",
    },
    {
      firstName: "Alice",
      lastName: "Smith",
      aadharNumber: "9876 5432 1098",
      address: "456 Elm St, Townsville",
      date: "1985-05-15",
      department: "HR",
      phoneNumber: "987-654-3210",
      rfid: "RFID456",
      email: "alice.smith@example.com",
      image:
        "https://images.pexels.com/photos/2681751/pexels-photo-2681751.jpeg?auto=compress&cs=tinysrgb&w=600",
    },
    {
      firstName: "Michael",
      lastName: "Johnson",
      aadharNumber: "2468 1357 9021",
      address: "789 Oak St, Villageton",
      date: "1982-11-30",
      department: "Finance",
      phoneNumber: "555-123-4567",
      rfid: "RFID789",
      email: "michael.johnson@example.com",
      image:
        "https://images.pexels.com/photos/2887718/pexels-photo-2887718.jpeg?auto=compress&cs=tinysrgb&w=600",
    },
  ];

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
            {canteen.map((employee, index) => (
              <tr
                key={index}
                className={(index + 1) % 2 === 0 ? "bg-gray-100" : "bg-white"}
              >
                <td className=" px-4 py-2 flex items-center gap-4">
                  <Image
                    width={100}
                    height={100}
                    src={employee.image}
                    alt={`${employee.firstName} ${employee.lastName}`}
                    className="w-12 h-12 object-cover rounded-full"
                  />
                  <h1 className="font-medium text-base">
                    {employee.firstName} {employee.lastName}
                  </h1>
                </td>

                <td className=" px-4 py-2">{employee.phoneNumber}</td>
                <td className=" px-4 py-2">{employee.email}</td>
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
