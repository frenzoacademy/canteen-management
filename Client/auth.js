import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

export const { handlers, auth, signIn, signOut } = NextAuth({
  session: { strategy: "jwt" },
  pages: {
    signIn: "/login",
  },
  providers: [
    CredentialsProvider({
      name: "Sign in",
      id: "credentials",
      credentials: {},
      async authorize(credentials) {
        if (credentials.label === "rfid") {
          if (credentials.rfid) {
            // write code to find the user by rfid
          }
        } else if (credentials.label === "login") {
          if (!credentials?.email || credentials.password) {
            //   write a fetch the data using email and password
          }
          return null;
        }
        // todo - check the user id
        return {
          ...user,
          id: user.id ?? "",
        };
      },
    }),
  ],
  callbacks: {
    session: ({ session, token }) => {
      return {
        ...session,
        ...token,
        user: {
          ...session.user,
        },
      };
    },
    jwt: ({ token, user }) => {
      if (user) {
        const u = user;
        return {
          ...token,
          id: u.id,
          randomKey: u.randomKey,
        };
      }
      return token;
    },
  },
});
