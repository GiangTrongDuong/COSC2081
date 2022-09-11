import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class member extends Customer{
    public member(String cid, String name, String address,
                  String phoneNum, String memberTier, String username, String password) {
        super(cid, name, address, phoneNum, memberTier, username, password);
    }

    public member() {

    }

    void registerMemberShip() throws FileNotFoundException {
        Scanner checkfile = new Scanner(new File("customers.txt")).useDelimiter(",\\s");
        String user = username;
        List<String> list = new ArrayList<>();
        while (checkfile.hasNextLine()) {
            list.add(checkfile.nextLine());
            for (String s : list) {
                String[] arr = s.split(",");
                if (list.contains(user)) {
                    String membership = list.get(4);
                    System.out.println(membership);
                }
            }
        }
    }
}
