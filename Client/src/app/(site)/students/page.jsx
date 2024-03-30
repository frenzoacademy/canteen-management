"use client";
import PageHeader from "@/components/PageHeader";
import {
  useGetStudents,
  useRemoveStudent,
} from "@/features/students/students.hooks";
import Image from "next/image";
import Link from "next/link";
import { useEffect, useState } from "react";

const Page = () => {
  const { data, isLoading } = useGetStudents();
  const { mutate } = useRemoveStudent();

  if (isLoading) return <h1>Loading...</h1>;

  return (
    <div>
      <PageHeader
        pageTitle="Student's Informations"
        buttonTitle=" Add Student"
        navigation={`/students/add`}
      />

      <div className="overflow-x-auto mt-10 rounded-md shadow-md">
        <table className="table-auto w-full border  border-black  ">
          <thead>
            <tr className="bg-black text-white">
              <th className="px-4 py-3 text-left"> Name</th>
              <th className="px-4 py-3 text-left">RFID</th>
              <th className="px-4 py-3 text-left">Email</th>
              <th className="px-4 py-3 text-center">Edit</th>
              <th className="px-4 py-3 text-center">Delete</th>
            </tr>
          </thead>
          <tbody>
            {data?.map((student, index) => (
              <tr
                key={index}
                className={(index + 1) % 2 === 0 ? "bg-gray-100" : "bg-white"}
              >
                <td className=" px-4 py-2 flex items-center gap-4">
                  <Image
                    width={100}
                    height={100}
                    src={`data:image/png;base64,${student.image}`}
                    alt={`${student.First_name} ${student.Last_name}`}
                    className="w-12 h-12 object-cover rounded-full"
                  />
                  <h1 className="font-medium text-base">
                    {student.First_name} {student.Last_name}
                  </h1>
                </td>

                <td className=" px-4 py-2">{student.rfid_Number}</td>
                <td className=" px-4 py-2">{student.email}</td>
                <td className=" px-4 py-2 text-center">
                  <Link
                    href={`/students/edit/${student?.student_id}`}
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                  >
                    Edit
                  </Link>
                </td>
                <td className=" px-4 py-2 text-center">
                  <button
                    onClick={() => mutate(student?.student_id)}
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
