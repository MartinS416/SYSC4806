package org.example.addressbook;

import org.example.addressbook.entity.AddressBook;
import org.example.addressbook.entity.BuddyInfo;
import org.example.addressbook.repository.AddressRepository;
import org.example.addressbook.repository.BuddyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addressbooks")
public class AddressBookController {
    private final AddressRepository ar;
    private final BuddyRepository br;

    public AddressBookController(AddressRepository ar, BuddyRepository br){
        this.ar = ar;
        this.br = br;
    }


    @PostMapping
    public AddressBook createAddressBook() {
        return ar.save(new AddressBook());
    }

    @PostMapping("/{id}/buddy")
    public AddressBook addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddyInfo) {
        Optional<AddressBook> addressBookOptional = ar.findById(id);
        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            addressBook.addBuddy(buddyInfo);
            br.save(buddyInfo);
            return ar.save(addressBook);
        }
        throw new RuntimeException("AddressBook Not Found");
    }

    @DeleteMapping("/{id}/buddy/{buddyId}")
    public AddressBook removeBuddy(@PathVariable Long id, @PathVariable long buddyId){
        Optional<AddressBook> addressBookOptional = ar.findById(id);
        if (addressBookOptional.isPresent()) {
            Optional<BuddyInfo> buddyInfoOptional = br.findById(buddyId);
            AddressBook addressBook = addressBookOptional.get();
            if(buddyInfoOptional.isPresent()){
                BuddyInfo buddyInfo = buddyInfoOptional.get();
                addressBook.removeBuddy(buddyInfo);
                br.delete(buddyInfo);
                return ar.save(addressBook);
            }
            throw new RuntimeException("Buddy Not Found");
        }
        throw new RuntimeException("AddressBook Not Found");
    }
}
