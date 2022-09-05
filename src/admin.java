
import java.io.*;

public class admin {
    String admin_password = "admin";
    String admin_username = "root";
    private int id;
    private String password;
    public void admin(int id, String password){
        Main admin = new Main();

    }

    @Override
    public String toString(){
        return "Admin username: " + admin_username +
                "Admin password " + admin_password;
    }
}
