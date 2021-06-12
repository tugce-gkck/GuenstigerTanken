package htwberlin.guenstigertanken;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name="Tanken")
public class Tanken {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="date")
    private Timestamp date;
    @Column(name="name")
    private String name;
    @Column(name="city")
    private String city;
    @Column(name="distance")
    private double distance;
    @Column(name="price")
    private double price;
    @Column(name="wc")
    private boolean wc;
    @Column(name="restaurant")
    private boolean restaurant;
    @Column(name="carwash")
    private boolean carwash;

    public Tanken(){

    }
    public Tanken(long id,String date,String name,String city,double distance,double price,boolean wc, boolean restaurant, boolean carwash){
        this.date = Timestamp.valueOf(date);
        this.id = id;
        this.name = name;
        this.city = city;
        this.distance = distance;
        this.price = price;
        this.wc = wc;
        this.restaurant = restaurant;
        this.carwash = carwash;
    }
    public Tanken(String date,String name,String city,double distance,double price,boolean wc, boolean restaurant, boolean carwash){
        this(0,date,name,city,distance,price,wc,restaurant,carwash);
    }
    public Tanken(long id,Timestamp date,String name,String city,double distance,double price,boolean wc, boolean restaurant, boolean carwash){
        this.date = date;
        this.id = id;
        this.name = name;
        this.city = city;
        this.distance = distance;
        this.price = price;
        this.wc = wc;
        this.restaurant = restaurant;
        this.carwash = carwash;
    }
    public Tanken(Timestamp date,String name,String city,double distance,double price,boolean wc, boolean restaurant, boolean carwash){
        this(0,date,name,city,distance,price,wc,restaurant,carwash);
    }

    @Override
    public String toString() {
        return "Tanken[id=" + id +
                     ",name=" + name +
                     ",city=" + city +
                     ",distance=" + distance +
                     ",price=" + price +
                     ",wc=" + wc +
                     ",restaurant=" + restaurant +
                     ",carwash=" + carwash +
                     "]" ;
    }

    // getters & setters omitted for brevity

    public boolean isWc() {
        return wc;
    }

    public void setWc(boolean wc) {
        this.wc = wc;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isCarwash() {
        return carwash;
    }

    public void setCarwash(boolean carwash) {
        this.carwash = carwash;
    }

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
