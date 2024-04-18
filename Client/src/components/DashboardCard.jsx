import {
  ClipboardDocumentCheckIcon,
  CurrencyDollarIcon,
  LifebuoyIcon,
  UserIcon,
} from "@heroicons/react/24/outline";

const stats = [
  {
    title: "Total Sales",
    count: "₹ 8000",
    icon: CurrencyDollarIcon,
    color: "blue",
  },
  {
    title: "Total Students",
    count: 80,
    icon: UserIcon,
    color: "yellow",
  },
  {
    title: "Total Foods",
    count: 20,
    icon: LifebuoyIcon,
    color: "orange",
  },
  {
    title: "Total Orders",
    count: 100,
    icon: ClipboardDocumentCheckIcon,
    color: "green",
  },
];

const DashboardCard = () => {
  return (
    <div className="grid grid-cols-4 gap-5">
      {stats?.map((item, index) => (
        <div
          key={index}
          className="border p-5 rounded-md flex flex-col items-start gap-5"
        >
          <div className={`p-3 rounded-full bg-${item.color}-300`}>
            <item.icon className={`h-5 w-5 text-${item.color}-600`} />
          </div>
          <div className=" flex flex-col items-start gap-2">
            <h1 className="text-lg font-semibold">{item?.count}</h1>
            <h1 className="text-base font-normal">{item?.title}</h1>
          </div>
        </div>
      ))}
    </div>
  );
};

export default DashboardCard;
