"use client";
import chef from "@/assets/chef.svg";
import canteen from "@/assets/canteen.svg";
import student from "@/assets/students.svg";
import stocks from "@/assets/stocks.svg";
import transaction from "@/assets/transaction.svg";
import menu from "@/assets/menu.svg";
import user from "@/assets/user.jpg";
import Image from "next/image";
import Link from "next/link";
import { signOut, useSession } from "next-auth/react";
import { useGetCanteenUser } from "@/features/canteenUser/canteenUser.hooks";
import { useGetStudent } from "@/features/students/students.hooks";
import { useEffect, useState } from "react";
import {
  ArrowLeftStartOnRectangleIcon,
  ArrowRightStartOnRectangleIcon,
} from "@heroicons/react/24/outline";

const DesktopSidebar = () => {
  const [userData, setUserData] = useState();
  const session = useSession();
  const userId = session?.data?.user?.id;
  const role = session?.data?.user?.role;
  const { data: canteenUserData } = useGetCanteenUser(userId);
  const { data: studentData } = useGetStudent(userId);

  useEffect(() => {
    if (role === "ROLE_CANTEEN_MANAGER") {
      setUserData(canteenUserData);
    } else if (role === "ROLE_STUDENT") {
      setUserData(studentData);
    }
  }, [role, canteenUserData, studentData]);

  const [navigation, setNavigation] = useState([]);

  useEffect(() => {
    if (role === "ROLE_CANTEEN_MANAGER") {
      setNavigation([
        {
          name: "Food Inventory",
          href: "/inventory",
          icon: stocks,
        },
        {
          name: "Food Court",
          href: "/food-court",
          icon: canteen,
        },
        {
          name: "Food Menu",
          href: "/food-menu",
          icon: menu,
        },
        {
          name: "Transactions",
          href: "/transactions",
          icon: transaction,
        },
      ]);
    } else if (role === "ROLE_STUDENT") {
      setNavigation([
        {
          name: "Transactions",
          href: "/transactions",
          icon: transaction,
        },
      ]);
    } else {
      // For other roles, like admin
      setNavigation([
        {
          name: "Student Info",
          href: "/students",
          icon: student,
        },
        {
          name: "Canteen User",
          href: "/canteen-info",
          icon: chef,
        },
        {
          name: "Transactions",
          href: "/transactions",
          icon: transaction,
        },
      ]);
    }
  }, [role]);

  return (
    <div className="w-20  sm:w-64 bg-black h-screen sticky top-0 px-3">
      <div className="flex items-center pt-10  gap-4 ">
        <Image
          src={user}
          alt="user"
          className="h-12 w-12 rounded-full object-cover"
        />
        <div className="text-white hidden sm:block">
          <h1 className="font-medium text-lg">
            {canteenUserData
              ? canteenUserData.first_name
              : studentData?.First_name}
          </h1>
          <h1 className="font-normal text-sm">
            {canteenUserData ? canteenUserData.email : studentData?.email}
          </h1>
        </div>
      </div>
      <div className="mt-7 ">
        <div className="flex flex-col gap-2">
          {navigation?.map((item, index) => (
            <Link
              key={index}
              href={item?.href}
              className="flex items-center gap-4 bg-gray-800 sm:px-5 sm:py-3 p-3    rounded-xl hover:scale-[1.02]"
            >
              <Image
                src={item?.icon}
                alt="icon"
                className="h-6 w-6 text-white"
              />
              <h1 className="font-normal text-base text-white hidden sm:block ">
                {item?.name}
              </h1>
            </Link>
          ))}
        </div>

        <div className="mt-10">
          {session?.data?.user || userId ? (
            <button
              onClick={() => {
                signOut();
              }}
              className="flex items-center text-white gap-4 bg-red-600 w-full sm:px-5 sm:py-3 p-3 rounded-xl"
            >
              <ArrowRightStartOnRectangleIcon className="h-6 w-6" />
              <h1 className="hidden sm:block">Log Out</h1>
            </button>
          ) : (
            <Link
              href={`/login`}
              className="flex items-center text-white gap-4 bg-gray-800 w-full sm:px-5 sm:py-3 p-3 rounded-xl"
            >
              <ArrowLeftStartOnRectangleIcon className="h-6 w-6" />
              <h1 className="hidden sm:block">Log in</h1>
            </Link>
          )}
        </div>
      </div>
    </div>
  );
};

export default DesktopSidebar;
