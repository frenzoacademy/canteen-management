"use client";
import { useState } from "react";
import { Tab } from "@headlessui/react";
import StudentLoginForm from "@/app/(auth)/login/StudentLoginForm";
import OthersLoginForm from "@/app/(auth)/login/OthersLoginForm";

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Example() {
  const [selectedTab, setSelectedTab] = useState(0);

  const tabClass = (index) => {
    return classNames(
      "w-full rounded-lg py-2.5 text-sm font-medium leading-5",
      "ring-white/60 ring-offset-2 ring-offset-blue-400 focus:outline-none",
      selectedTab === index
        ? "bg-white text-black shadow"
        : "text-white hover:bg-white/[0.12] hover:text-white"
    );
  };

  const handleTabClick = (index) => {
    setSelectedTab(index);
  };

  return (
    <div className="w-full max-w-md px-2 py-16 sm:px-0">
      <Tab.Group>
        <Tab.List className="flex space-x-1 rounded-xl bg-blue-900/20 p-1 sticky">
          <Tab className={tabClass(0)} onClick={() => handleTabClick(0)}>
            Students
          </Tab>
          <Tab className={tabClass(1)} onClick={() => handleTabClick(1)}>
            Others
          </Tab>
        </Tab.List>
        <Tab.Panels className="mt-2">
          <Tab.Panel
            className={classNames(
              "rounded-xl bg-white py-10 px-3",
              "ring-white/60 ring-offset-2 ring-offset-blue-400 focus:outline-none"
            )}
          >
            <StudentLoginForm />
          </Tab.Panel>
          <Tab.Panel
            className={classNames(
              "rounded-xl bg-white py-10 px-3",
              "ring-white/60 ring-offset-2 ring-offset-blue-400 focus:outline-none "
            )}
          >
            <OthersLoginForm />
          </Tab.Panel>
        </Tab.Panels>
      </Tab.Group>
    </div>
  );
}
