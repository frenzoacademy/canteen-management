import CanteenUserForm from "@/components/forms/CanteenUserForm";

const page = ({ params }) => {
  const [editId] = params?.editId;
  return (
    <div>
      <CanteenUserForm editId={editId} />
    </div>
  );
};

export default page;
