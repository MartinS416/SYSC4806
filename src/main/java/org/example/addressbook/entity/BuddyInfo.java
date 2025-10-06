package org.example.addressbook.entity;

import jakarta.persistence.*;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name = "";
    private String address = "";

    public BuddyInfo() {}
    public BuddyInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    @Override public String toString() {
        return "BuddyInfo [id="+ id + ", name ="+ name + ", address=" + address +"]";
    }
}
