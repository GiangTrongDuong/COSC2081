import java.util.*;
import java.io.*;

public class Main {
    boolean isLoggedIn = false;
    Customer currentCus;

    {
        currentCus = new Customer("none","none","none","0","none","none","none");
    }

    void listAll() throws IOException {
        //create a bufferedReader and user scanner
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        Scanner sc = new Scanner(System.in);
        List lines = new ArrayList();
        //create a 2D arrayList and append each line of the txt file into a sub-array in the 2D array
        for(String line = br.readLine(); line != null; line = br.readLine()){
            String[] currentLine = line.split(",");
            lines.add(currentLine);
        }
        String[][] strings = (String[][]) lines.toArray(new String[lines.size()][]);
//        System.out.println(strings);

        //loop through the 2D array and display the product on CLI
        for(int i = 0; i < strings.length; i++){
            for(int j = 0; j < strings[i].length; j++){
                System.out.println(strings[i][j] + " | ");
            }
            System.out.println("\n");
        }
        //A pause statement to give the user some space to read message above
            String input = "";
            while (!"next".equals(input)) {
                System.out.println("\n Type (next) to return to the menu");
                input = sc.nextLine();

            }
            menu();
        }



        void sortPrice () throws IOException {
            //create a bufferedReader and user scanner
            BufferedReader br = new BufferedReader(new FileReader("items.txt"));
            Scanner sc = new Scanner(System.in);
            List lines = new ArrayList();
            //create a 2D arrayList and append each line of the txt file into a sub-array in the 2D array
            for(String line = br.readLine(); line != null; line = br.readLine()){
                String[] currentLine = line.split(",");
                lines.add(currentLine);
            }

            String[][] strings = (String[][]) lines.toArray(new String[lines.size()][]);

            System.out.println("Sort by price, please choose between highest to lowest or lowest to highest");
            System.out.println("Input 1 for high to low, 2 for low to high");
            int intInput = sc.nextInt(); //

            int col = 2;


            for(int i = 0; i < strings.length; i++){
                for(int j = 0; j < strings[i].length; j++){
                    System.out.println(strings[i][j] + " | ");
                }
                System.out.println("\n");
            }



        }

        void sortCart(){

        }
        void register () throws IOException {
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
            menu();

        }

        void viewInfo() throws IOException {
            Scanner inp = new Scanner(System.in);
            System.out.println("Customer Info" +
                    "\n Name: " + currentCus.name
                    + "\n ID: " + currentCus.cid
                    + "\n Registered Address: " + currentCus.address
                    + "\n Phone number: " + currentCus.phoneNum
                    + "\n Membership Tier: " + currentCus.memberTier);
            System.out.println("\nPress enter to continue");
            String enter = inp.nextLine();
            menu();
        }

        boolean login() throws IOException {
            //Function to take in customer input and  read file "customer.txt"
            File file = new File("customers.txt");
            Scanner scf = new Scanner(file);
            Scanner inp = new Scanner(System.in);
            //Create value that will be use in the loop
            boolean isCorrect = false;
            boolean passCorrect = false;
            String currentLine = null;
            //Loop to check for username input of user
            while (!isCorrect) {
                System.out.println("Insert VALID username");
                String inputUser = inp.nextLine();
                String lineInFile = scf.nextLine();
                while (!isCorrect) {
                    if (lineInFile.contains(inputUser)) {
//                        System.out.println(lineInFile);
                        System.out.println("correct username");
                        isCorrect = true;
                        currentLine = lineInFile;
                        break;

                    } else {
                        if(scf.hasNextLine()){
                            lineInFile = scf.nextLine();
                        }else {
                            System.out.println("Found no username at " + inputUser);
                            //A pause statement to give the user some space to read message above
                            System.out.println("Press enter to continue");
                            String enter = inp.nextLine();
                            menu();
                        }
                    }
                }
            }

                System.out.println("Please insert your password");
                String inputPass = inp.nextLine();
                //Loop to check for password input of user
                while (!passCorrect) {
                    if (currentLine.contains(inputPass)) {
//                        System.out.println(currentLine);
                        System.out.println("Correct password, logged in");
                        passCorrect = true;
                        break;
                    } else {
                        System.out.println("Invalid password, try again latter");
                        break;
                    }
                }

                //Loop to check if both username and password match
                if(isCorrect  && passCorrect){
                    //Return the line from "customer.txt" according to login info and append it into array list
                    String[] currentUser = currentLine.split(",");
                    List<String> currentUserString = Arrays.asList(currentUser);
                    ArrayList<String> UserString = new ArrayList<String>(currentUserString);
                  currentCus.reAssign(UserString.get(0), UserString.get(1), UserString.get(2), UserString.get(3), UserString.get(4), UserString.get(5),UserString.get(6));

                    //A pause statement to give the user some space to read message above
                    System.out.println("Press enter to continue");
                    String enter = inp.nextLine();
                    isLoggedIn = true;
                    menu();
                }else {
                    //A pause statement to give the user some space to read message above
                    System.out.println("Press enter to continue");
                    String enter = inp.nextLine();
                    menu();
                }
                return isLoggedIn;
        }

        void logout() throws IOException{
        currentCus.reAssign("none","none","none","0","none","none","none");
        isLoggedIn = false;
        menu();
        }

        void menu () throws IOException {
            Scanner kb = new Scanner(System.in);

            //Print out the information of Group name, number, student name and ID, lecturer name.
            System.out.println("COSC2081 GROUP ASSIGNMENT ");
            System.out.println("STORE ORDER MANAGEMENT SYSTEM");
            System.out.println("Instructor: Mr. Minh Vu");
            System.out.println("Group: 12");
            System.out.println("s3926135, Giang Trong Duong");
            System.out.println("s3926369, Tran Gia Hung");
            System.out.println("s3926016, Truong Adam Nhat Anh");

            //Check whether user has logged in. If logged in the user will be greeted with message bellow + custom menu.
            if(isLoggedIn){
                System.out.println("\nWelcome " +currentCus.name + ",you are now logged in as " + currentCus.username);
                //Print out menu interface for logged-in user
                System.out.println("""
                    \nPlease choose one of these option (insert number bellow according to selection)
                    \t1. List all Products
                    \t2. Sort by price
                    \t3. Sort by category
                    \t4. View information
                    \t5. Logout""");
                byte ch = kb.nextByte();
                if (ch == 1) {
                    listAll();
                } else if (ch == 2) {
                    sortPrice();
                }else if (ch == 3) {
                    sortCart();
                } else if (ch == 4) {
                    viewInfo();
                } else if (ch == 5) {
                    logout();
                } else {
                    System.out.println("===============================" + "\n====Invalid Input, re-enter====" + "\n===============================");
                    menu();
                }
//                }else if(){

                }else {
                    //Print out menu interface for user not logged in
                    System.out.println("""
                    \nPlease choose one of these option (insert number bellow according to selection)
                    \t1. List all Products
                    \t2. Sort by price
                    \t3. Sort by category
                    \t4. Register
                    \t5. Login""");

                    byte ch = kb.nextByte();
                    if (ch == 1) {
                        listAll();
                    } else if (ch == 2) {
                        sortPrice();
                    }else if (ch == 3) {
                        sortCart();
                    } else if (ch == 4) {
                        register();
                    } else if (ch == 5) {
                        login();
                    } else {
                        System.out.println("===============================" + "\n====Invalid Input, re-enter====" + "\n===============================");
                        menu();
                    }
                }

            }
            //Print out menu interface to console



        void menuAdmin() throws IOException{

        }

        public static void main (String[]args) throws IOException {
            Main program = new Main();
            program.menu();



        }
}
