"use client";
import dynamic from "next/dynamic";

const StudentInfoForm = dynamic(
  () => {
    return import("@/components/forms/StudentInfoForm");
  },
  {
    ssr: false,
  }
);

const page = () => {
  return (
    <div>
      <StudentInfoForm />
    </div>
  );
};

export default page;
