"use client";
import WalletBalance from "@/components/WalletBalance";
import { useGetPurchaseOrders } from "@/features/purchase-order/purchaseOrder.hooks";
import { format } from "date-fns";

const Page = () => {
  const { data } = useGetPurchaseOrders();
  console.log(data);
  let currentDate = format(new Date(), "MMMM do yyyy");
  const totalAmount = data?.reduce(
    (total, order) => total + order.totalAmount,
    0
  );
  console.log(totalAmount);

  return (
    <div>
      <WalletBalance amount={totalAmount} />
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
              {data?.map((transaction, index) => (
                <tr
                  key={index}
                  className="hover:bg-slate-900  hover:bg-opacity-95 border-b-[1px] border-b-slate-700"
                >
                  <td className="px-3 py-4">
                    {transaction?.studentForm.rfid_Number}
                  </td>
                  <td className="px-3 py-4">
                    {transaction.foodItems?.length} Items
                  </td>
                  <td className="px-3 py-4">{currentDate}</td>
                  <td className="px-3 py-4">{transaction.status}</td>
                  <td className="px-3 py-4 text-red-600">
                    - ₹{transaction.totalAmount}
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
