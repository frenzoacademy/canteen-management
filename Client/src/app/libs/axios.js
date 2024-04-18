import { authOptions } from "@/pages/api/auth/[...nextauth]";
import Axios from "axios";
import { getServerSession } from "next-auth";
import { getSession } from "next-auth/react";

export const axios = Axios.create({
  baseURL: process.env.NEXT_PUBLIC_APIURL,
});

const requestInterceptor = async (config) => {
  let token = null;

  try {
    token = await getServerSession(authOptions);
    // console.log(token);
  } catch (err) {
    // console.log(err);
  }

  const session = await getSession();
  if (session?.user) {
    config.headers.set("Authorization", session?.user?.token);
  } else if (token) {
    config.headers.set("Authorization", token?.user?.token);
  }
  // const token = getItem();
  // if (token) {
  //   config.headers["Authorization"] = token;
  // }
  return config;
};
axios.interceptors.request.use(requestInterceptor);
