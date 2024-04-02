"use client";
import FoodMenuCard from "@/components/FoodMenuCard";
import { useGetFoodInventories } from "@/features/foodInventory/foodInventory.hooks";

const foods = [
  {
    id: 1,
    food_name: "Food 1",
    amount: "100",
    breakfast: true,
    afternoon: false,
    snacks: true,
    dinner: false,
    image_url:
      "https://images.pexels.com/photos/14775031/pexels-photo-14775031.jpeg?auto=compress&cs=tinysrgb&w=600",
  },
  {
    id: 2,
    food_name: "Food 2",
    amount: "150",
    breakfast: false,
    afternoon: true,
    snacks: false,
    dinner: false,
    image_url:
      "https://images.pexels.com/photos/14774996/pexels-photo-14774996.jpeg?auto=compress&cs=tinysrgb&w=600",
  },
  {
    id: 3,
    food_name: "Food 3",
    amount: "200",
    breakfast: false,
    afternoon: false,
    snacks: true,
    dinner: true,
    image_url:
      "https://images.pexels.com/photos/14774990/pexels-photo-14774990.jpeg?auto=compress&cs=tinysrgb&w=600",
  },
];

const Page = () => {
  const { data } = useGetFoodInventories();
  console.log(data);
  // const { user } = await auth();
  // const {
  //   data: { user },
  // } = useSession();
  const renderMealSection = (mealType) => {
    return (
      <div>
        <h2 className="text-lg font-medium my-5">{mealType}</h2>
        <div className="flex gap-5 flex-wrap">
          {data
            ?.filter((food) => food[mealType.toLowerCase()])
            ?.map((food, index) => (
              <FoodMenuCard
                key={index}
                id={food.food_id}
                name={food.name}
                amount={food.amount}
                image={food.photoBase64}
              />
            ))}
        </div>
      </div>
    );
  };

  return (
    <div>
      {renderMealSection("Breakfast")}
      {renderMealSection("Lunch")}
      {renderMealSection("Eveningfood")}
      {renderMealSection("Dinner")}
    </div>
  );
};

export default Page;
