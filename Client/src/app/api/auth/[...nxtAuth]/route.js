import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

const users = [
  {
    id: 1,
    username: "testuser",
    email: "test@gmail.com",
    password: 12345678,
  },
  {
    id: 2,
    username: "testuser",
    email: "admin@gmail.com",
    password: 12345678,
  },
];

export const authOptions = {
  providers: [
    CredentialsProvider({
      name: "credentials",
      credentials: {},
      async authorize(credentials) {
        const { email, password } = credentials;
        const user = users.find(
          (user) => user.email === email && user.password === password
        );

        if (user) {
          return user;
        } else {
          return null;
        }
      },
    }),
  ],
  session: {
    strategy: "jwt",
  },
  jwt: { encryption: true },
  secret: process.env.NEXTAUTH_SECRET,
  pages: {
    signIn: "/",
  },
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
    onError: async (error, _context) => {
      // Handle the error
      console.error("NextAuth Error:", error);
      // Redirect the user or perform any other action
    },
  },
};

const handler = NextAuth(authOptions);

export { handler as GET, handler as POST };
