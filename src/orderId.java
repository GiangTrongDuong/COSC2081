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
            System.out.println("Insert ID of the products you want to buy: ");
            String inputID = inp.nextLine();
            System.out.println("Insert the amount you want to buy (number)");
            int amount = inp.nextInt();
            String lineInFile = scfItem.nextLine();
            String[] currentLine = lineInFile.split(", ");
            while (!match) {
                if (currentLine[0].equals(inputID)) {
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
                        System.out.println("Found no item");
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
                if (input.equals(currentLineList[0])) {
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
                        Main.menu();
                    }
                }
            }
        }



}
