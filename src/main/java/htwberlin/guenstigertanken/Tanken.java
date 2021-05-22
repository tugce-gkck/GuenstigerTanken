package htwberlin.guenstigertanken;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tanken {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Timestamp date;
    private String name, city;
    private double distance;
    private double price;

    public Tanken(){

    }
    public Tanken(long id,String date,String name,String city,double distance,double price){
        this.date = Timestamp.valueOf(date);
        this.id = id;
        this.name = name;
        this.city = city;
        this.distance = distance;
        this.price = price;
    }
    public Tanken(String date,String name,String city,double distance,double price){
        this(0,date,name,city,distance,price);
    }
    public Tanken(long id,Timestamp date,String name,String city,double distance,double price){
        this.date = date;
        this.id = id;
        this.name = name;
        this.city = city;
        this.distance = distance;
        this.price = price;
    }
    public Tanken(Timestamp date,String name,String city,double distance,double price){
        this(0,date,name,city,distance,price);
    }

    @Override
    public String toString() {
        return "Tanken[id=" + id +
                     ",name=" + name +
                     ",city=" + city +
                     ",distance=" + distance +
                     ",price=" + price +
                     "]" ;
    }

    // getters & setters omitted for brevity

    public long getId() {
        return id;
    }

    public Timestamp getDate() {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
