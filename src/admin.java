
import java.io.*;
import java.util.*;

class Admin {
    String admin_password = "admin";
    String admin_username = "root";
    private int id;
    private String password;
    public void Admin(int id, String password){
        Main admin = new Main();

    }
    

    void login() throws IOException{
        //Create Scanner class
        Scanner inp = new Scanner(System.in);
        //Ask user to input
        System.out.println("Insert admin username");
        String inputAdUser = inp.nextLine();
        System.out.println("Insert admin password");
        String inputAdPass = inp.nextLine();

        if(inputAdUser.equals(admin_username) && inputAdPass.equals(admin_password)){
            System.out.println("Successfully log in as admin, press enter to continue");
            String enter = inp.nextLine();
            Main.menuAdmin();
        } else {
            System.out.println("Failed login, press enter to return to menu");
            String enter = inp.nextLine();
            Main.menu();
        }
    }

    public static void searchOrder() throws IOException{
        //Create file object and Scanner object
        File file = new File("order.txt");
        File user = new File("customers.txt");
        Scanner inp = new Scanner(System.in);
        Scanner scf = new Scanner(file);
        Scanner scfUser = new Scanner(user);

        //Create function that display all current Customer ID
        System.out.println("All customer ID currently are:");
        String line = scfUser.nextLine();
        while(scfUser.hasNextLine()){
            String[] arrayLine = line.split(",");
            line = scfUser.nextLine();
            System.out.print(arrayLine[0] + ", ");
        }

        System.out.println("\nInsert customer ID you want to check for order:");
        String adInp = inp.nextLine();

        String Cline = scf.nextLine();
        while(true) {
            String[] arrayCline = Cline.split(", ");
            if (adInp.equals(arrayCline[5])) {
                System.out.println("\nOrder ID " + arrayCline[0]
                        + "\nItem ordered: " + arrayCline[1]
                        + "\nAmount: " + arrayCline[2]
                        + "\nTotal Price: " + arrayCline[3]
                        + "\nStatus: " + arrayCline[4]);
            }
                if (scf.hasNextLine()) {
                    Cline = scf.nextLine();
                } else {
                    System.out.println("Found no result at " + adInp +
                            " (maybe syntax error or customer has no order?)");
                    break;
                }
            }
        System.out.println("Press enter to return to menu:");
        String enter = inp.nextLine();
        Main.menuAdmin();
        }

    @Override
    public String toString(){
        return "Admin username: " + admin_username +
                "Admin password " + admin_password;
    }
}
