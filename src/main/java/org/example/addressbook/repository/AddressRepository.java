package org.example.addressbook.repository;
import org.example.addressbook.entity.AddressBook;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressBook, Long> {

}
