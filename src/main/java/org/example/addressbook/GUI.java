package org.example.addressbook;

import org.example.addressbook.entity.AddressBook;
import org.example.addressbook.repository.AddressRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/gui")
public class GUI {
    private final AddressRepository ar;

    public GUI(AddressRepository ar) {
        this.ar=ar;
    }

    @GetMapping("/{id}")
    public String viewGUI(@PathVariable Long id, Model model) {
        Optional<AddressBook> addressBookOptional = ar.findById(id);
        if(addressBookOptional.isPresent()) {
            model.addAttribute("buddies", addressBookOptional.get().getBuddyInfoList());
            return "addressbook";
        }
        throw new RuntimeException("AddressBook Not Found");
    }
}
