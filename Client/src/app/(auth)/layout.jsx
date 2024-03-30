import { Inter } from "next/font/google";
import "../(auth)/login/globals.css";

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "RFID Canteen management",
  description: "College canteen management system",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div>{children}</div>
      </body>
    </html>
  );
}
