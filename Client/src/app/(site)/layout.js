import { Inter } from "next/font/google";
import "./globals.css";
import DesktopSidebar from "@/components/DesktopSidebar";
import Provider from "@/components/Provider";
import { getServerSession } from "next-auth";
import { authOptions } from "@/pages/api/auth/[...nextauth]";

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "RFID Canteen management",
  description: "College canteen management system",
};

export default function RootLayout({ children }) {
  const session = getServerSession(authOptions);

  return (
    <html lang="en">
      <body className={inter.className}>
        <Provider session={session}>
          <div className="flex">
            <div>
              <DesktopSidebar />
            </div>
            <div className="p-10 w-full ">{children}</div>
          </div>
        </Provider>
      </body>
    </html>
  );
}
