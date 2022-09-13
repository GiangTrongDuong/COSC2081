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
        System.out.println("Your current member tier is " + memberTier);
        Scanner sc = new Scanner(System.in);
        String[] membership = {"Regular", "Silver", "Gold", "Platinum"};
        System.out.println("Please choose between our membership package: " +
                membership[0] + ", " + membership[1] + ", " + membership[2] +
                ", " + membership[3]);
        String input = ((sc.nextLine()).trim()).toLowerCase();
        String check = memberTier.toLowerCase();
        if (!input.equals(check)) {
            System.out.println("You've just chosen " + input.toUpperCase() + " membership package!");
            switch (input) {
                case "regular" -> memberTier = "Regular";
                case "silver" -> memberTier = "Silver";
                case "gold" -> memberTier = "Gold";
                case "platinum" -> memberTier = "Platinum";
            }
            // record specific data into array and then change then append back (stay at the same index)
            // "https://www.youtube.com/watch?v=EfS6i_jAm4g" at 34 mins

            // record all data into array, change it then re-append back (which means delete
            // all then append)

            // record specific data into array and then create brand new data array (new index)

            //
            Main.menu();
        }
        else {
            System.out.println("This is the membership package you have already chosen!" +
                    " Please enter \"enter\" different packages or" +
                    " \"exit\" if you changed your mind!");
            String reenter = ((sc.nextLine()).trim()).toLowerCase();
            if (reenter.equals("exit")) {
                Main.menu();
            } else if (reenter.equals("enter")) {
                registerMemberShip();
            } else {
                System.out.println("Incorrect input");
                registerMemberShip();
            }
        }
    }
}
