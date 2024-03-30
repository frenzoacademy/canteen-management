"use client";
import { useRouter } from "next/navigation";

const PageHeader = ({ pageTitle, buttonTitle, navigation }) => {
  const route = useRouter();
  return (
    <div className="flex justify-between items-center">
      <h1 className="text-2xl font-medium">{pageTitle}</h1>
      <button
        onClick={() => route.push(navigation)}
        className="px-4 py-2 bg-black text-white rounded-lg"
      >
        {buttonTitle}
      </button>
    </div>
  );
};

export default PageHeader;
