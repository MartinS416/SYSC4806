package org.example.addressbook;
import org.example.addressbook.entity.BuddyInfo;
import org.example.addressbook.repository.BuddyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BuddyInfoTest {

    @Autowired
    private BuddyRepository br;

    @Test
    void testJPA() {
        BuddyInfo buddy = new BuddyInfo("asdf", "asdf1234");
        br.save(buddy);

        BuddyInfo buddy2 = br.findById(buddy.getId()).orElse(null);

        assertThat(buddy2).isNotNull();
        assertThat(buddy2.getName()).isEqualTo("asdf");
        assertThat(buddy2.getAddress()).isEqualTo("asdf1234");
    }

    @Test
    void testConstructorAndGetters() {
        BuddyInfo buddy = new BuddyInfo("Alice", "123 Main St");

        assertThat(buddy.getName()).isEqualTo("Alice");
        assertThat(buddy.getAddress()).isEqualTo("123 Main St");
    }

    @Test
    void testSetName() {
        BuddyInfo buddy = new BuddyInfo("Alice", "123 Main St");
        buddy.setName("Bob");

        assertThat(buddy.getName()).isEqualTo("Bob");
    }

    @Test
    void testSetAddress() {
        BuddyInfo buddy = new BuddyInfo("Alice", "123 Main St");
        buddy.setAddress("456 Carleton St");

        assertThat(buddy.getAddress()).isEqualTo("456 Carleton St");
    }
}
