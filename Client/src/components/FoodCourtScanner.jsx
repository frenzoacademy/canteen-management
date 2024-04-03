import useScanDetection from "use-scan-detection-react18";

const FoodCourtScanner = ({ rfid, addRfid }) => {
  useScanDetection({
    onComplete: (code) => {
      addRfid(code);
    },
  });

  return rfid === null ? (
    <div className="px-3 py-2 bg-red-200 mb-5 text-red-600 border-red-500 border rounded-md">
      <h1 className="font-normal text-sm ">Scan your RFID card to order</h1>
    </div>
  ) : (
    <div className="px-3 py-2 bg-green-200 mb-5 text-green-600 border-green-500 border rounded-md">
      <h1 className="font-normal text-sm ">Student RFID has scanned</h1>
    </div>
  );
};

export default FoodCourtScanner;
