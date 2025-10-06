package org.example.addressbook.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_book_id")
    private List<BuddyInfo> buddyInfoList = new ArrayList<>();

    public AddressBook() {}

    public AddressBook(List<BuddyInfo> buddyInfoList) {
        this.buddyInfoList = buddyInfoList;
    }

    public List<BuddyInfo> getBuddyInfoList() {
        return buddyInfoList;
    }

    public void setBuddyInfoList(List<BuddyInfo> buddyInfoList) {
        this.buddyInfoList = buddyInfoList;
    }

    public void addBuddy(BuddyInfo b) {
        buddyInfoList.add(b);
    }

    public void addBuddy(String name, String address) {
        BuddyInfo buddy = new BuddyInfo(name, address);
        buddyInfoList.add(buddy);
        buddyInfoList.sort(Comparator.comparing(BuddyInfo::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public void removeBuddy(String name) {
        buddyInfoList.removeIf(b -> b.getName().equals(name));
    }

    public void removeBuddy(BuddyInfo buddy) {
        buddyInfoList.remove(buddy);
    }

    public BuddyInfo getBuddy(int i) {
        return buddyInfoList.get(i);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (BuddyInfo b : buddyInfoList) {
            sb.append(b.getName())
                    .append(" | ")
                    .append(b.getAddress())
                    .append("\n");
        }
        return sb.toString();
    }

    public void showAddressBook() {
        System.out.println("==== Address Book ====");
        for (BuddyInfo b : buddyInfoList) {
            System.out.println(b.getName() + " | " + b.getAddress());
        }
        System.out.println("======================");
    }
}
