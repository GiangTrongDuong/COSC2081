
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

    @Override
    public String toString(){
        return "Admin username: " + admin_username +
                "Admin password " + admin_password;
    }
}
