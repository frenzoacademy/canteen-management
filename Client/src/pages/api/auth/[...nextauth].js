import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import { axios } from "@/libs/axios";

const users = [
  { id: 1, username: "testuser", email: "test@gmail.com", password: 12345678 },
];

export const authOptions = {
  providers: [
    CredentialsProvider({
      async authorize(credentials) {
        const { username, password } = credentials;
        const user = users.find(
          (user) => user.username === username && user.password === password
        );

        if (user) {
          return user; // Return user data if authentication succeeds
        } else {
          return null; // Return null if authentication fails
        }
      },
    }),
  ],
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
  secret: process.env.SECRET,

  // debug: true,
};
export default NextAuth(authOptions);
