package htwberlin.guenstigertanken;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Tanken {
    private long id;
    private LocalDateTime date;
    private String name, city;
    private double distance;
    private double price;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Tanken(long id,String date,String name,String city,double distance,double price){

        this.id = id;
        this.date = LocalDateTime.parse(date, formatter);
        this.name = name;
        this.city = city;
        this.distance = distance;
        this.price = price;

    }
    public Tanken(String date,String name,String city,double distance,double price){
        this(0,date,name,city,distance,price);
    }

    @Override
    public String toString() {
        String str = "Tanken[id=" + id;
        str += ",name=" + name;
        str += ",city=" + city;
        str += ",distance=" + distance;
        str += ",price=" + price;
        str += "]" ;
        return str;
    }

    // getters & setters omitted for brevity

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public double getDistance() {
        return distance;
    }

    public double getPrice() {
        return price;
    }
}
