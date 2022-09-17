import java.io.*;
import java.util.*;

public class orderId {
    protected String Oid;
    protected String item;
    protected int price;

    public static void createOrder() throws IOException {
        //Create File
        File itemFile = new File("items.txt");
        File orderFile = new File("order.txt");
        //Create print writer to write to order id
        PrintWriter pw = new PrintWriter(new FileOutputStream(orderFile, true));
        //Create scanner class
        Scanner scfItem = new Scanner(itemFile);
        Scanner scfOrder = new Scanner(orderFile);
        Scanner inp = new Scanner(System.in);
        //Create value to evaluate loop
        boolean match = false;
        while (!match) {
            // display items in the shop
            BufferedReader br = new BufferedReader(new FileReader("items.txt"));
            Scanner sc = new Scanner(System.in);
            List lines = new ArrayList();
            //create a 2D arrayList and append each line of the txt file into a sub-array in the 2D array
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] currentLine = line.split(",");
                lines.add(currentLine);
            }
            String[][] strings = (String[][]) lines.toArray(new String[lines.size()][]);

            //loop through the 2D array and display the product on CLI
            for (int i = 0; i < strings.length; i++) {
                for (int j = 0; j < strings[i].length; j++) {
                    System.out.print(strings[i][j] + " | ");
                }
                System.out.println("\n");
            }

            //Requesting for info
            System.out.println("Insert ID of the products you want to buy (Please be cautious that we" +
                    " would need you to insert the exact item ID): ");
            String inputID = inp.nextLine();

            System.out.println("Insert the amount you want to buy (number): ");
            int amount = inp.nextInt();
            String lineInFile = scfItem.nextLine();
            String[] currentLine = lineInFile.split(", ");
            while (!match) {
                if (currentLine[0].trim().equals(inputID.trim())) {
                    match = true;
                    //Automatically create new ID for order
                    int numID = 1;
                    while (scfOrder.hasNextLine()) {
                        scfOrder.nextLine();
                        numID++;
                    }

                    pw.append("\nOID-" + numID + ", " + currentLine[1] + ", " + amount + ", " +
                            (amount * Float.parseFloat(currentLine[2])) + ", unpaid, " + Main.currentCus.cid);
                    pw.close();
                    System.out.println("Order create complete! Your order ID is " + numID
                    + "\nItem: " + currentLine[1]
                    + "\nAmount " + amount +"\n Price per product " + currentLine[2]
                    + "\nTotal Price: " + (amount * Float.parseFloat(currentLine[2])));

                    String input = "";

                    while (!"next".equals(input)) {
                        System.out.println("\n Type (next) to return to the menu");
                        input = inp.nextLine();

                    }

                    Main.menu();
                } else {
                    if(scfItem.hasNextLine()){
                        lineInFile = scfItem.nextLine();
                        currentLine = lineInFile.split(", ");
                    }else{
                        System.out.println("Found no item with ID of \"" + inputID + "\"");
                        System.out.println("Press Enter to return to main menu");
                        String enter = inp.nextLine();
                        Main.menu();
                    }
                }
            }
        }
    }
    public static void viewOrder() throws IOException {
        //Create file object and Scanner Object
        File orderFile = new File("order.txt");
        Scanner inp = new Scanner(System.in);
        Scanner scf = new Scanner(orderFile);
        Scanner scfOD = new Scanner(orderFile);
        //Return to user all order ID they currently have
        boolean end = false;
        String currentLine = scf.nextLine();
        System.out.println("Active order for account " + Main.currentCus.username);
        while (true) {
            String[] currentOrder = currentLine.split(", ");
            if(Main.currentCus.cid.equals(currentOrder[5])) {
                System.out.print(currentOrder[0] + ", ");
            }
            if(scf.hasNextLine()){
                currentLine = scf.nextLine();
            }else{
                break;
            }
        }
            System.out.println("\nInsert order id you want to look at:");
            String input = inp.nextLine();
            String line = scfOD.nextLine();
            boolean Continue = true;
            while (Continue) {
                String[] currentLineList = line.split(", ");
                if (input.trim().equals(currentLineList[0])) {
                    System.out.println("Order ID " + currentLineList[0]
                            + "\nItem ordered: " + currentLineList[1]
                            + "\nAmount: " + currentLineList[2]
                            + "\nTotal Price: " + currentLineList[3]
                            + "\nStatus: " + currentLineList[4]);
                    System.out.println("\nPress enter to return to menu");
                    String enter = inp.nextLine();
                    Main.menu();
                } else {
                    if (scfOD.hasNextLine()) {
                        line = scfOD.nextLine();
                    } else {
                        System.out.println("Invalid input! \nPlease insert correctly the order id!");
                        System.out.println("\n");
                        viewOrder();
                    }
                }
            }
        }

        public static void updateOrderStatus() throws IOException{
            File orderFile = new File("order.txt");
            Scanner scf = new Scanner(orderFile);
            Scanner scfScan = new Scanner(orderFile);
            Scanner inp = new Scanner(System.in);

            boolean match = false;
            String orderFileLine = scf.nextLine();
            String fileContents = "";

            while(true){
                String[] currentLine = orderFileLine.split(", ");
                fileContents = fileContents.concat(orderFileLine+System.lineSeparator());
                if(currentLine[4].equals("unpaid")){
                    System.out.println("Current status of order " + currentLine[0] + " : unpaid");
                    if(scf.hasNextLine()) {
                        orderFileLine = scf.nextLine();
                    } else {
                        break;
                    }
                } else if (currentLine[4].equals("paid")) {
                    System.out.println("Current status of order " + currentLine[0] + " : paid");
                    if(scf.hasNextLine()) {
                        orderFileLine = scf.nextLine();
                    } else {
                        break;
                    }
                } else {
                    if(scf.hasNextLine()){
                        orderFileLine = scf.nextLine();
                    } else{
                        break;
                    }
                }

            }

            System.out.println("Insert id of order you want to change: ");
            String input = inp.nextLine();

            String lineInFile = scfScan.nextLine();
            while(!match) {
                String[] newLineArray = lineInFile.split(", ");
                if (newLineArray[0].equals(input) && newLineArray[4].equals("unpaid")) {
                    String oldPrice = newLineArray[2];
                    newLineArray[4] = "paid";
                    match = true;
                    String oldLine = lineInFile;
                    String newLine = String.join(", ", newLineArray);
                    fileContents = fileContents.replaceAll(oldLine, newLine);
                    FileWriter writer = new FileWriter(orderFile);
                    writer.append(fileContents);
                    writer.flush();
                    System.out.println("Change status of order" + newLineArray[0] + " from unpaid to paid"
                            + "\nPress enter to return to menu");
                    String enter = inp.nextLine();
                    Main.menuAdmin();
                } else if (newLineArray[0].equals(input) && newLineArray[4].equals("paid")) {
                    System.out.println("Status is already paid for order " + input
                            + "\nPress enter to return to menu");
                    String enter = inp.nextLine();
                    Main.menuAdmin();
                }else {
                    if (scfScan.hasNextLine()) {
                        lineInFile = scfScan.nextLine();
                    } else {
                        System.out.println("Found no item at ID: " + input
                                + "\nPress enter to return to menu");
                        String enter = inp.nextLine();
                        Main.menuAdmin();
                    }
                }
            }

    }

}
