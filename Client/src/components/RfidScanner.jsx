"use client";
import { Dialog, Transition } from "@headlessui/react";
import { XMarkIcon } from "@heroicons/react/24/outline";
import scan from "@/assets/scan.jpg";
import scanSuccess from "@/assets/scan-success.jpg";
import Image from "next/image";
import { Fragment } from "react";
import useScanDetection from "use-scan-detection-react18";

const RfidScanner = ({ isOpen, closeModal, getRfid, isRfid, message }) => {
  useScanDetection({
    onComplete: (code) => {
      getRfid(code);
    },
  });

  const handleCloseModal = () => {
    closeModal();
  };
  return (
    <>
      <Transition appear show={isOpen || false} as={Fragment}>
        <Dialog as="div" className="relative z-10" onClose={closeModal}>
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-black/25" />
          </Transition.Child>

          <div className="fixed inset-0 overflow-y-auto">
            <div className="flex min-h-full items-center justify-center p-4 text-center">
              <Transition.Child
                as={Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0 scale-95"
                enterTo="opacity-100 scale-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100 scale-100"
                leaveTo="opacity-0 scale-95"
              >
                <Dialog.Panel className="w-full max-w-md transform overflow-hidden rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all">
                  <Dialog.Title
                    as="h3"
                    className="text-lg font-medium leading-6 text-gray-900"
                  >
                    <div className="flex justify-between">
                      <h1>Scan Your Id</h1>
                      <button
                        className="border text-red-600 rounded-md"
                        onClick={handleCloseModal}
                      >
                        <XMarkIcon className="h-5 w-5" />
                      </button>
                    </div>
                  </Dialog.Title>
                  {isRfid === null ? (
                    <div>
                      <Image src={scan} alt="image" />
                      <h1 className="text-center font-semibold text-xl">
                        Scan your RFID
                      </h1>
                    </div>
                  ) : (
                    <div>
                      <h1 className="text-center font-semibold text-xl">
                        Successfully Scanned
                      </h1>
                    </div>
                  )}
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition>
    </>
  );
};

export default RfidScanner;
