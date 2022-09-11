import java.io.*;
import java.util.*;

public class member extends Customer{
    public member(String cid, String name, String address,
                  String phoneNum, String memberTier, String username, String password) {
        super(cid, name, address, phoneNum, memberTier, username, password);
    }

    public member() {

    }

    static void viewInfo() throws IOException {
        String file = "customers.txt";
        BufferedReader reader = null;
        String line = "";
        Scanner scanner = new Scanner(System.in);
        try {
            reader = new BufferedReader(new FileReader(file));
            Scanner scf = new Scanner(new File((file)));
            while((line = reader.readLine()) != null) {
                String ID = scf.next();
                if (ID.contains(username)) {
                    List<String> arr = new ArrayList<>(List.of(line.split(",")));
                    System.out.println("Customer Info" +
                            "\n Name: " + name
                            + "\n ID: " + cid
                            + "\n Registered Address: " + address
                            + "\n Phone number: " + phoneNum
                            + "\n Membership Tier: " + memberTier);
                }
            }
            System.out.println("\nPress enter to continue");
            if (scanner.hasNextLine()) {
                Main.menu();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    void registerMemberShip() throws IOException {
        String file = "customers.txt";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                viewInfo();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // delete old membership then append new membership

//    public createorder(String cid, String name, String address,
//                            String phoneNum) {
//
//        return
//    }
}
