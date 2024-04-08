import { axios } from "@/app/libs/axios";
import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

export const authOptions = {
  providers: [
    CredentialsProvider({
      async authorize(credentials) {
        try {
          const authResponse = await axios.post("/login", credentials);
          return authResponse?.data;
        } catch (ex) {
          return null;
        }
      },
    }),
  ],
  debugger: true,
  session: {
    strategy: "jwt", // JSON Web Token
  },
  jwt: { encryption: true },
  pages: {
    signIn: "/login",
  },
  callbacks: {
    async jwt({ token, user }) {
      return { ...token, ...user };
    },
    async session({ session, token, user }) {
      session.user = token;
      return session;
    },
  },
  secret: process.env.NEXTAUTH_SECRET,

  // debug: true,
};
export default NextAuth(authOptions);
