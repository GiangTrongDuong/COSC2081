public class Customer {
    protected String cid;
    protected String name;
    protected String address;
    protected int phoneNum;
    protected String username;
    protected String password;

    public Customer(String s, String s1, String s2, String s3, String s4, String s5) {
        this.cid = s;
        this.name = s1;
        this.address = s2;
        this.phoneNum = Integer.parseInt(s3);
        this.username = s4;
        this.password = s5;
    }

    public void reAssign(String s, String s1, String s2, String s3, String s4, String s5){
        this.cid = s;
        this.name = s1;
        this.address = s2;
        this.phoneNum = Integer.parseInt(s3);
        this.username = s4;
        this.password = s5;
    }


    @Override
    public String toString() {
        return "\nCustomer " + name + "\n"
                + "ID: " + cid + "\n"
                + "Address: " + address + "\n"
                + "Phone numbers: " + phoneNum + "\n"
                + "Username: " + username + "\n";
    }
}
