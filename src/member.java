import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

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
            while (true) {
                if (input.equals("regular")) {
                   memberTier = "Regular";
                   System.out.println("You've just chosen " + input.toUpperCase() + " membership package!");
                   break;
                }
                if (input.equals("silver")) {
                    memberTier = "Silver";
                    System.out.println("You've just chosen " + input.toUpperCase() + " membership package!");
                    break;
                }
                if (input.equals("gold")) {
                    memberTier = "Gold";
                    System.out.println("You've just chosen " + input.toUpperCase() + " membership package!");
                    break;
                }
                if (input.equals("platinum")) {
                    memberTier = "Platinum";
                    System.out.println("You've just chosen " + input.toUpperCase() + " membership package!");
                    break;
                }
                else {
                    System.out.println("Invalid input!");
                    System.out.println("Please choose appropriate package!\n");
                    registerMemberShip();
                }
            }

            try {
                // input the file content to the StringBuffer "input"
                BufferedReader file = new BufferedReader(new FileReader("customers.txt"));
                List<String> list = new ArrayList<>();
                String line;
                String fileContents = "";
                Scanner scFile = new Scanner(new File("customers.txt"));

                while(scFile.hasNextLine()){
                    String nextLine = scFile.nextLine();
                    fileContents = fileContents.concat(nextLine+System.lineSeparator());
                }

                while ((line = file.readLine()) != null) {
                    list.add(line);
                }
                file.close();

//             logic to replace lines in the string (could use regex here to be generic)
                for (String s : list) {
                    String[] arr = s.split(",");
                    String x = (String) Array.get(arr, 5);
                    String compare1 = x.trim();
                    String compare2 = username.trim();
                    if (compare1.equals(compare2)) {
                        arr[4] = memberTier;
                        String newLine = String.join(",", arr);
                        fileContents = fileContents.replaceAll(s, newLine);
                        FileWriter writer = new FileWriter("customers.txt");
                        writer.append(fileContents);
                        writer.flush();
                        Scanner user = new Scanner(System.in);
                        System.out.println("Your membership package has been updated!");
                        System.out.println("\nPress enter to continue");
                        String enter = user.nextLine();
                        Main.menu();
                    } else {
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println("Problem reading file.");
            }
        }
        else {
            System.out.println("This is the membership package you have already chosen!" +
                    " Please enter \"enter\" different packages or" +
                    " \"exit\" if you changed your mind!");
            String reenter = ((sc.nextLine()).trim()).toLowerCase();
            if (reenter.equals("exit")) {
                Main.menu();
            } else if (reenter.trim().equals("enter")) {
                registerMemberShip();
            } else {
                System.out.println("Incorrect input!\n");
                registerMemberShip();
            }
        }
    }
}
