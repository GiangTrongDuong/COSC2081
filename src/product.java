import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class product {
    protected String ID;
    protected String title;
    protected String price;
    protected String category;

    public product(String ID, String title, String price, String category){
        this.ID = ID;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public static void addProduct() throws IOException {
        //Create file, scanner class and print writer object
        Scanner sc = new Scanner(System.in);
        File product = new File("items.txt");
        Scanner item = new Scanner(product);
        PrintWriter pw = new PrintWriter(new FileOutputStream(product, true));
        //Generate customer product ID
        int numID = 1;
        while (item.hasNextLine()) {
            item.nextLine();
            numID++;


        }

        int rand = (int)(Math.random()*2100);
        //Ask admin for input/ product register
        System.out.println("Insert product name");
        String prodName = sc.nextLine();
        System.out.println("Insert product price");
        String prodPrice = sc.nextLine();
        System.out.println("Insert product category");
        String prodCat = sc.nextLine();

        pw.append("I00").append(String.valueOf(numID)).append("-").append(String.valueOf(rand)).append(", ").append(prodName).append(", ").append(prodPrice).append(", ").append(prodCat);
    }

    public static void replacePrice() throws IOException {
        //Create Scanner class
            File itemF = new File("items.txt");
            Scanner scFile = new Scanner(new File("items.txt"));
            Scanner scFiles = new Scanner(new File("items.txt"));
            Scanner scInp = new Scanner(System.in);
            String fileContents = "";

            while(scFile.hasNextLine()){
                String nextLine = scFile.nextLine();
                fileContents = fileContents.concat(nextLine+System.lineSeparator());
            }
    
            System.out.println(fileContents);
            System.out.println("Insert product ID you want to change price");
            String id = scInp.nextLine();
            boolean match = false;
            String lineInFile = scFiles.nextLine();

            while(!match) {
                String[] newLineArray = lineInFile.split(", ");
                if (newLineArray[0].equals(id)) {
                    System.out.println("Insert new price for " + newLineArray[1] + " (number)");
                    float newPrice = scInp.nextFloat();
                    String oldPrice = newLineArray[2];
                    newLineArray[2] = String.valueOf(newPrice);
                    match = true;
                    String oldLine = lineInFile;
                    String newLine = String.join(", ", newLineArray);
                    fileContents = fileContents.replaceAll(oldLine, newLine);
                    FileWriter writer = new FileWriter(itemF);
                    writer.append(fileContents);
                    writer.flush();
                    System.out.println("Change price for item with ID " + id + "from " + oldPrice + " to " + newLineArray[2]
                            + "\nPress enter to return to menu");
                    String enter = scInp.nextLine();
                    Main.menuAdmin();
                } else {
                    if (scFiles.hasNextLine()) {
                        lineInFile = scFiles.nextLine();
                    } else {
                        System.out.println("Found no item at ID: " + id
                                + "\nPress enter to return to menu");
                        String enter = scInp.nextLine();
                        Main.menuAdmin();
                    }
                }
            }



    }
    public String toString() {
        return String.format("ID: %d\r\nTitle: %s\r\nPrice %s\r\nCategory %d\r\n",
                this.ID, this.title, this.price, this.category);
    }
}
