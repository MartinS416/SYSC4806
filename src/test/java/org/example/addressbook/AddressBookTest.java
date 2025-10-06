package org.example.addressbook;

import org.example.addressbook.entity.AddressBook;
import org.example.addressbook.entity.BuddyInfo;
import org.example.addressbook.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AddressBookTest {

    @Autowired
    private AddressRepository ar;

    @Test
    void testJPA() {
        BuddyInfo buddy = new BuddyInfo("asdf", "asdf1234");
        AddressBook addressBook = new AddressBook(new ArrayList<>());
        addressBook.addBuddy(buddy);

        addressBook = ar.save(addressBook);

        AddressBook addressBook2 = ar.findById(addressBook.getId()).orElse(null);

        assertThat(addressBook2).isNotNull();
        assertEquals("asdf", addressBook2.getBuddy(0).getName());
        assertEquals("asdf1234", addressBook2.getBuddy(0).getAddress());
    }

    @Test
    void testAddBuddy() {
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy("Tim", "303 Carleton Street");
        addressBook.addBuddy("Fez", "304 Carleton Street");
        addressBook.addBuddy("Jim", "305 Carleton Street");

        List<BuddyInfo> list = addressBook.getBuddyInfoList();
        assertEquals(3, list.size());

        assertEquals("Fez", list.get(0).getName());
        assertEquals("Jim", list.get(1).getName());
        assertEquals("Tim", list.get(2).getName());
    }

    @Test
    void testRemoveBuddy() {
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy("Tim", "123 St");
        addressBook.addBuddy("Fez", "456 St");

        addressBook.removeBuddy("Tim");

        List<BuddyInfo> list = addressBook.getBuddyInfoList();
        assertEquals(1, list.size());
        assertEquals("Fez", list.get(0).getName());
    }

    @Test
    void testSetAndGetBuddyInfoList() {
        AddressBook addressBook = new AddressBook();
        ArrayList<BuddyInfo> newList = new ArrayList<>();
        newList.add(new BuddyInfo("Derek", "999 Carleton Street"));
        addressBook.setBuddyInfoList(newList);

        assertEquals(1, addressBook.getBuddyInfoList().size());
        assertEquals("Derek", addressBook.getBuddyInfoList().get(0).getName());
    }
}