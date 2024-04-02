"use client";
import { Tab } from "@headlessui/react";
import OthersLoginForm from "@/app/(auth)/login/OthersLoginForm";

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Example() {
  return (
    <div className="w-full max-w-md px-2 py-16 sm:px-0">
      <Tab.Group>
        <Tab.Panels className="mt-2">
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
