import java.io.*;
import java.util.*;

public class Customer {
    protected static String cid;
    protected static String name;
    protected static String address;
    protected static int phoneNum;
    protected static String memberTier;
    protected static String username;
    protected static String password;
    protected boolean isLoggedIn = false;

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Customer() {
        this.cid = "none";
        this.name = "none";
        this.address = "none";
        this.phoneNum = 0;
        this.memberTier = "none";
        this.username = "none";
        this.password = "none";
    }

    public Customer(String cid, String name, String address,
                    String phoneNum, String memberTier, String username, String password) {
        this.cid = cid;
        this.name = name;
        this.address = address;
        this.phoneNum = Integer.parseInt(phoneNum);
        this.memberTier = memberTier;
        this.username = username;
        this.password = password;
    }

//    public static void reAssign(String s, String s1, String s2, String s3, String s4, String s5, String s6){
//        return
//        this.cid = s;
//        this.name = s1;
//        this.address = s2;
//        this.phoneNum = Integer.parseInt(s3);
//        this.memberTier = s4;
//        this.username = s5;
//        this.password = s6;
//    }
//
//    public void reAssign(){
//        this.cid = "none";
//        this.name = "none";
//        this.address = "none";
//        this.phoneNum = Integer.parseInt("none");
//        this.memberTier = "none";
//        this.username = "none";
//        this.password = "none";
//    }

    void register() throws IOException {
        //create Scanner object for input and reader
        Scanner sc = new Scanner(System.in);
        Scanner file = new Scanner(new File("customers.txt"));
        File customer = new File("customers.txt");

        int numID = 1;
        while (file.hasNextLine()) {
            file.nextLine();
            numID++;
        }


        //Function to take in customer input (via console)
        System.out.println("Please enter your name");
        String name = sc.nextLine();
        System.out.println("Please enter your address");
        String address = sc.nextLine();
        System.out.println("Please enter your phone (int number only)");
        int phone = Integer.parseInt(sc.nextLine());
        System.out.println("Please enter your username");
        String userName = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();
        //PrintWriter function to write to customer.txt
        PrintWriter pw = new PrintWriter(new FileOutputStream(customer, true));
        pw.append("\nCOO" + numID + "," + name + "," + address + "," + phone + ",Regular" + ", " + userName + ", " + password);
        pw.close();


        System.out.println("Successfully register");
        //A pause statement to give the user some space to read message above
        String input = "";
        while (!"next".equals(input)) {
            System.out.println("\n Type (next) to return to the menu");
            input = sc.nextLine();
//                System.out.println(input);
        }
        Main main = new Main();
        main.menu();
    }



    boolean login() throws IOException {
        //Function to take in customer input and  read file "customer.txt"
        File file = new File("customers.txt");
        Scanner scf = new Scanner(file);
        Scanner inp = new Scanner(System.in);
        //Create value that will be use in the loop
        boolean userCorrect = false;
        boolean passCorrect = false;
        String currentLine = null;
        //Loop to check for username input of user
        while (!userCorrect) {
            System.out.println("Insert username: ");
            String inputUser = inp.nextLine();
            String lineInFile = scf.nextLine();
            while (!userCorrect) {
                if (lineInFile.contains(inputUser)) {
//                        System.out.println(lineInFile);
                    System.out.println("Correct username");
                    userCorrect = true;
                    currentLine = lineInFile;
                    break;

                } else {
                    if(scf.hasNextLine()){
                        lineInFile = scf.nextLine();
                    }else {
                        System.out.println("Found no username at " + inputUser);
                        //A pause statement to give the user some space to read message above
                        System.out.println("Choose 1 to exit, choose any to retry");
                        int enter = inp.nextInt();
                        if (enter == 1) {
                            Main.menu();
                        } else {
                            login();
                        }
                    }
                }
            }
        }

        System.out.println("Insert password: ");
        String inputPass = inp.nextLine();
        //Loop to check for password input of user
        while (!passCorrect) {
            if (currentLine.contains(inputPass)) {
//                        System.out.println(currentLine);
                System.out.println("Correct password, logged in");
                passCorrect = true;
                break;
            } else {
                System.out.println("Invalid password, try again!");
                login();
            }
        }

        //Loop to check if both username and password match
        if(userCorrect && passCorrect){
            //Return the line from "customer.txt" according to login info and append it into array list
            ArrayList<String> UserString = new ArrayList<>(Arrays.asList(currentLine.split(",")));
            new Customer(UserString.get(0), UserString.get(1), UserString.get(2),
                    UserString.get(3), UserString.get(4), UserString.get(5),UserString.get(6));

            //A pause statement to give the user some space to read message above
            System.out.println("Press Enter to continue");
            String enter = inp.nextLine();
            isLoggedIn = true;
            Main main = new Main();
            main.menu();
            // give main a parameter
        }else {
            //A pause statement to give the user some space to read message above
            System.out.println("Press Enter to continue");
            String enter = inp.nextLine();
            Main main = new Main();
            main.menu();
        }
        return isLoggedIn;
    }

    void logout() throws IOException{
        new Customer();
        isLoggedIn = false;
        Main main = new Main();
        main.menu();
    }


            
    @Override
    public String toString() {
        return "\nCustomer " + name + "\n"
                + "ID: " + cid + "\n"
                + "Address: " + address + "\n"
                + "Phone numbers: " + phoneNum + "\n"
                + "Username: " + username + "\n";
    }
}
