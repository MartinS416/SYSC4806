package org.example.addressbook.repository;
import org.example.addressbook.entity.BuddyInfo;
import org.springframework.data.repository.CrudRepository;

public interface BuddyRepository extends CrudRepository<BuddyInfo, Long> {
}
