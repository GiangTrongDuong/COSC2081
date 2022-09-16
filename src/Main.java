import java.util.*;
import java.io.*;

public class Main {
    public static Customer currentCus = new Customer();
    static member currentMem = new member();
    static Admin currentAd = new Admin();

    static void listAll() throws IOException {
        //create a bufferedReader and user scanner
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        Scanner sc = new Scanner(System.in);
        List lines = new ArrayList();
        //create a 2D arrayList and append each line of the txt file into a sub-array in the 2D array
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String[] currentLine = line.split(",");
            lines.add(currentLine);
        }
        String[][] strings = (String[][]) lines.toArray(new String[lines.size()][]);
//        System.out.println(strings);

        //loop through the 2D array and display the product on CLI
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
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


    static void sortPrice() throws IOException {
        //create a bufferedReader and user scanner
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        Scanner sc = new Scanner(System.in);
        List lines = new ArrayList();
        //create a 2D arrayList and append each line of the txt file into a sub-array in the 2D array
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String[] currentLine = line.split(",");
            lines.add(currentLine);
        }

        String[][] strings = (String[][]) lines.toArray(new String[lines.size()][]);
        System.out.println(Arrays.deepToString(strings));
        System.out.println("Sort by price, please choose between highest to lowest or lowest to highest");
        System.out.println("Input 1 for high to low, 2 for low to high");
        int intInput = sc.nextInt(); //

        int col = 2;


        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                // System.out.println(strings[i][j] + " | ");
                // System.out.println("\n");

                String str = strings[i][col];
                float num = Float.parseFloat(str);
                // System.out.println(num);
                for (int k = 0; k < strings.length; k++) {
                    String str2 = strings[k][col];
                    float num2 = Float.parseFloat(str2);
                    if (intInput == 1) {
                        if (num > num2) {
                            String[] temp = strings[i];
                            strings[i] = strings[k];
                            strings[k] = temp;

                                                }
                    } else if (intInput == 2) {
                        if (num < num2) {
                            String[] temp = strings[i];
                            strings[i] = strings[k];
                            strings[k] = temp;

                        }
                    }

                }
            }

        }


    }

    static void sortCart() throws IOException {
        //Create scanner class for input and read from items.txt
        File items = new File("items.txt");
        Scanner scf = new Scanner(items);
        Scanner inp = new Scanner(System.in);

        boolean found = false;
        System.out.println("Type in an item category you want to filter" +
                "\nAvailable category: Earbud, Laptop, Phone, Speaker, Headset");
        String inputUser = inp.nextLine();
        String currentLine = scf.nextLine();
        while (scf.hasNextLine()) {
            String[] currentItem = currentLine.split(", ");
            if (inputUser.equals(currentItem[3])) {
                found = true;
                System.out.println(currentLine);
                if (scf.hasNextLine()) {
                    currentLine = scf.nextLine();
                }
            } else {
                currentLine = scf.nextLine();
                if(!scf.hasNextLine() && !found) {
                    System.out.println("\nFound no item category at " + inputUser + ", make sure to insert exactly as show");
                    System.out.println("Type 1 to exit, type enter or any key to search again");
                    int enter = inp.nextInt();
                    if (enter == 1) {
                        menu();
                    } else {
                        sortCart();
                    }
                } else if (!scf.hasNextLine() && found == true) {
                    System.out.println("\nFinish searching by category, press enter to return to menu");
                    String enter2 = inp.nextLine();
                    menu();
                }
            }
        }
    }



    static void menu() throws IOException {
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
        if (currentCus.isLoggedIn) {
            System.out.println("\nWelcome " + currentCus.name + ",you are now logged in as " + currentCus.username);
            //Print out menu interface for logged-in user
            System.out.println("""
                    \nPlease choose one of these option (insert number bellow according to selection)
                    \t1. List all Products
                    \t2. Sort by price
                    \t3. Sort by category
                    \t4. View information
                    \t5. Logout
                    \t6. Register membership
                    \t7. Create order
                    \t8. Check order status
                    Enter your choice: """);
            byte ch = kb.nextByte();
            if (ch == 1) {
                listAll();
            } else if (ch == 2) {
                sortPrice();
            } else if (ch == 3) {
                sortCart();
            } else if (ch == 4) {
                currentMem.viewInfo();
            } else if (ch == 5) {
                currentCus.logout();
            } else if (ch == 6) {
                currentMem.registerMemberShip();
            } else if (ch == 7) {
                orderId.createOrder();
            } else if (ch == 8) {
                orderId.viewOrder();
            } else {
                System.out.println("===============================" + "\n====Invalid Input, re-enter====" + "\n===============================");
                menu();
            }
//                }else if(){

        } else {
            //Print out menu interface for user not logged in
            System.out.println("""
                    \nPlease choose one of these option (insert number bellow according to selection)
                    \t1. List all Products
                    \t2. Sort by price
                    \t3. Sort by category
                    \t4. Register
                    \t5. Login
                    \t6. Login as Admin
                    Enter your choice: """);

            byte ch = kb.nextByte();
            if (ch == 1) {
                listAll();
            } else if (ch == 2) {
                sortPrice();
            } else if (ch == 3) {
                sortCart();
            } else if (ch == 4) {
                currentCus.register();
            } else if (ch == 5) {
                currentCus.login();
            } else if (ch == 6) {
                currentAd.login();
            } else {
                System.out.println("===============================" + "\n====Invalid Input, re-enter====" + "\n===============================");
                menu();
            }
        }

    }
    //Print out menu interface to console


    static void menuAdmin() throws IOException {
        Scanner kb = new Scanner(System.in);
        System.out.println("""
                \nPlease choose one of these option (insert number bellow according to selection)
                \t1. Add new product
                \t2. Get order info by checking customer ID
                \t3. Change order status
                \t4. Update product price
                \t5. Logout
                Enter your choice: """);
        byte ch = kb.nextByte();
        if (ch == 1) {
            product.addProduct();
        } else if (ch == 2) {
            Admin.searchOrder();
        } else if (ch == 3) {
            orderId.updateOrderStatus();
        } else if (ch == 4) {
            product.replacePrice();
        } else if (ch == 5) {
            menu();
        } else {
            System.out.println("===============================" + "\n====Invalid Input, re-enter====" + "\n===============================");
            menuAdmin();
        }
    }


    public static void main(String[] args) throws IOException {
        Main program = new Main();
        program.menu();


    }
}
