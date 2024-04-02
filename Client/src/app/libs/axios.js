import Axios from "axios";
import { getSession } from "next-auth/react";

// const authRequestInterceptor = async config => {
//   const session = await getSession();
//   if (session) {
//     config.headers.set('Authorization', session.accessToken.email);
//   }

//   config.headers.Accept = 'application/json';
//   return config;
// };

export const axios = Axios.create({
  baseURL: process.env.NEXT_PUBLIC_APIURL,
});

// axios.interceptors.request.use(authRequestInterceptor);
