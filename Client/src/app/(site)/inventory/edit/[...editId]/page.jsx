import FoodInventoryForm from "@/components/forms/FoodInventoryForm";

const page = ({ params }) => {
  const [editId] = params?.editId;

  return (
    <div>
      <FoodInventoryForm editId={editId} />
    </div>
  );
};

export default page;
