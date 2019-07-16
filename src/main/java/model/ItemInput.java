package model;

public class ItemInput{
    public String id;
    public String name;
    public double price;
    public int num;
    public double total_price;

    public ItemInput(String id, String name, double price, int num, double total_price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.num = num;
        this.total_price = total_price;

    }
}
