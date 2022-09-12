import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class member extends Customer{
    public member(String cid, String name, String address,
                  String phoneNum, String memberTier, String username, String password) {
        super(cid, name, address, phoneNum, memberTier, username, password);
    }

    public member() {

    }

    void viewInfo() throws IOException {
        String line = "";
        String path = "customers.txt";
        Scanner sc = new Scanner(new FileReader(path));
        List<String> list = new ArrayList<>();
        Scanner user = new Scanner(System.in);
        try {
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                list.add(data);
            }
            for (String s : list) {
                String[] arr = s.split(",");
                String x = (String) Array.get(arr, 5);
                String compare1 = x.trim();
                String compare2 = username.trim();
                if (compare1.equals(compare2)) {
                    System.out.println("Customer Info" +
                            "\n Name: " + name
                            + "\n ID: " + cid
                            + "\n Registered Address: " + address
                            + "\n Phone number: " + phoneNum
                            + "\n Membership Tier: " + memberTier);
                    System.out.println("\nPress enter to continue");
                    String enter = user.nextLine();
                    Main.menu();
                } else {
                    continue;
                }
            }
        } catch (Exception ignored) {}
    }


    void registerMemberShip() throws IOException {
        viewInfo();
        
    }
}
