import Tabs from "@/components/Tabs";
import { Suspense } from "react";

const page = () => {
  return (
    <Suspense>
      <div className="flex justify-center items-center flex-col  bg-slate-900 h-screen">
        <h1 className="text-white text-xl font-black">LOGIN </h1>

        <Tabs />
      </div>
    </Suspense>
  );
};

export default page;
