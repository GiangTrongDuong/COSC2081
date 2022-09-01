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

    public String toString() {
        return String.format("ID: %d\r\nTitle: %s\r\nPrice %s\r\nCategory %d\r\n",
                this.ID, this.title, this.price, this.category);
    }
}
