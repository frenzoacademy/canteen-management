import StudentInfoForm from "@/components/forms/StudentInfoForm";

const page = ({ params }) => {
  const [editId] = params?.editId;

  return (
    <div>
      <StudentInfoForm editId={Number(editId)} />
    </div>
  );
};

export default page;
