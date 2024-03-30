import chef from "@/assets/chef.svg";
import canteen from "@/assets/canteen.svg";
import student from "@/assets/students.svg";
import stocks from "@/assets/stocks.svg";
import transaction from "@/assets/transaction.svg";
import menu from "@/assets/menu.svg";
import user from "@/assets/user.jpg";
import Image from "next/image";
import Link from "next/link";

const DesktopSidebar = () => {
  const navigation = [
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
      name: "Food Inventory",
      href: "/inventory",
      icon: stocks,
    },
    {
      name: "Food court",
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
  ];

  return (
    <div className="w-20  sm:w-64 bg-black  sticky top-0 h-screen px-3">
      <div className="flex items-center pt-10  gap-4 ">
        <Image
          src={user}
          alt="user"
          className="h-12 w-12 rounded-full object-cover"
        />
        <div className="text-white hidden sm:block">
          <h1 className="font-medium text-lg">Senthil Kumar</h1>
          <h1 className="font-normal text-sm">senthi@gmail.com</h1>
        </div>
      </div>
      <div className="mt-7 flex flex-col gap-5">
        {navigation?.map((item, index) => (
          <Link
            key={index}
            href={item?.href}
            className="flex items-center gap-4 bg-gray-800 sm:px-5 sm:py-3 p-3    rounded-xl hover:scale-[1.02]"
          >
            <Image src={item?.icon} alt="icon" className="h-6 w-6 text-white" />
            <h1 className="font-normal text-base text-white hidden sm:block ">
              {item?.name}
            </h1>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default DesktopSidebar;
