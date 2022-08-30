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
                System.out.print(strings[i][j] + " | ");
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

        void sortPrice () {

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
            pw.append("COO" + numID + "," + name + "," + address + "," + phone + ",Regular" + ", " + userName + ", " + password);
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

        boolean login() throws IOException {
            File file = new File("customers.txt");
            Scanner scf = new Scanner(file);
            Scanner inp = new Scanner(System.in);

            boolean isCorrect = false;
            boolean passCorrect = false;
            String currentLine = null;

            while (isCorrect == false) {
                System.out.println("Insert VALID username");
                String inputUser = inp.nextLine();
                String lineInFile = scf.nextLine();
                while (isCorrect == false) {
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
                while (passCorrect == false) {
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

                if(isCorrect == true && passCorrect == true){
                    String[] currentUser = currentLine.split(",");
                    List<String> currentUserString = Arrays.asList(currentUser);
                    ArrayList<String> UserString = new ArrayList<String>(currentUserString);
                  currentCus.reAssign(UserString.get(0), UserString.get(1), UserString.get(2), UserString.get(3), UserString.get(4), UserString.get(5),UserString.get(6));
                    System.out.println(currentCus);

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

        void menu () throws IOException {
            Scanner kb = new Scanner(System.in);

            System.out.println("COSC2081 GROUP ASSIGNMENT ");
            System.out.println("STORE ORDER MANAGEMENT SYSTEM");
            System.out.println("Instructor: Mr. Minh Vu");
            System.out.println("Group: 12");
            System.out.println("s3926135, Giang Trong Duong");

            if(isLoggedIn == true){
                System.out.println("\nWelcome " +currentCus.name + ",you are now logged in as " + currentCus.username);
//                System.out.println(isLoggedIn);
            }

            System.out.println("\nPlease choose one of these option"
                    + "\n\t1. List all Products" + "\n\t2. Sort by price" + "\n\t3. Sort by category" + "\n\t4. Register" + "\n\t5. Login");
            byte ch = kb.nextByte();

            if (ch == 1) {
                listAll();
            } else if (ch == 2) {
                sortPrice();
            } else if (ch == 4) {
                register();
            } else if (ch == 5) {
                login();
            } else {
                System.out.println("===============================" + "\n====Invalid Input, re-enter====" + "\n===============================");
                menu();
            }
        }


        public static void main (String[]args) throws IOException {
            Main program = new Main();
            program.menu();



        }
}
