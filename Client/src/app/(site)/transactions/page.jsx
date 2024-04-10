"use client";
import WalletBalance from "@/components/WalletBalance";
import { useGetPurchaseOrders } from "@/features/purchase-order/purchaseOrder.hooks";

const transactions = [
  {
    RFID: "165441313",
    products: "3",
    category: "Subscription",
    date: "Jul 21, 2023",
    status: "Pending",
    amount: "₹ 10.00",
  },
  {
    RFID: "165441582",
    products: "8",
    category: "Subscription",
    date: "Jul 21, 2023",
    status: "In progress",
    amount: "₹ 5.00",
  },
  {
    RFID: "165441946",
    products: "10",
    category: "Car",
    date: "Jul 20, 2023",
    status: "Success",
    amount: "₹ 346.78",
  },
];

const Page = () => {
  const { data } = useGetPurchaseOrders();

  return (
    <div>
      <WalletBalance />
      <div className="mt-10">
        <div className="w-full overflow-auto text-slate-400 bg-black  rounded-lg ">
          <table className="w-full table-auto">
            <thead>
              <tr className="text-left bg-black text-white font-sans tracking-wide  border-b-[1px] border-b-slate-700">
                <th className="px-3 py-5 ">Student RFID</th>
                <th className="px-3 py-5">Purchased Products</th>
                <th className="px-3 py-5">Date</th>
                <th className="px-3 py-5">Status</th>
                <th className="px-3 py-5">Amount</th>
              </tr>
            </thead>
            <tbody>
              {transactions.map((transaction, index) => (
                <tr
                  key={index}
                  className="hover:bg-slate-900  hover:bg-opacity-95 border-b-[1px] border-b-slate-700"
                >
                  <td className="px-3 py-4">{transaction.RFID}</td>
                  <td className="px-3 py-4">{transaction.products} Items</td>
                  <td className="px-3 py-4">{transaction.date}</td>
                  <td className="px-3 py-4">{transaction.status}</td>
                  <td className="px-3 py-4 text-red-600">
                    - {transaction.amount}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Page;
