public class RecordItem {

    private String date;
    private String product;
    private String type;
    private String quantity;
    private String status;

    public RecordItem(String date, String product, String type, String quantity, String status) {
        this.date = date;
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.status = status;
    }

    public String getDate() { return date; }
    public String getProduct() { return product; }
    public String getType() { return type; }
    public String getQuantity() { return quantity; }
    public String getStatus() { return status; }
}
