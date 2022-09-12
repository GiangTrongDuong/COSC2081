import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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

        pw.append("\nI00" + numID + "-" + rand + ", " + prodName + ", " + prodPrice + ", " + prodCat);
    }

    public String toString() {
        return String.format("ID: %d\r\nTitle: %s\r\nPrice %s\r\nCategory %d\r\n",
                this.ID, this.title, this.price, this.category);
    }
}
