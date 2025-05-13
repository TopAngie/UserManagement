package com.angelica.usermanager;


import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Addresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String workaddress;
    private String homeaddress;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Addresses() {}

    public Addresses(String workaddress, String homeaddress) {
        this.workaddress = workaddress;
        this.homeaddress = homeaddress;
    }

    // Getters and Setters
    public int getId() { return id; }

    public String getWorkaddress() { return workaddress; }

    public void setWorkaddress(String workaddress) { this.workaddress = workaddress; }

    public String getHomeaddress() { return homeaddress; }

    public void setHomeaddress(String homeaddress) { this.homeaddress = homeaddress; }

    public Users getUser() { return user; }

    public void setUser(Users user) { this.user = user; }
}
