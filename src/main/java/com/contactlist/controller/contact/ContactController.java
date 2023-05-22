package com.contactlist.controller.contact;

import com.contactlist.payload.dto.ContactDTO;
import com.contactlist.persistance.model.Contact;
import com.contactlist.persistance.repository.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhoist:4200")
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("getAll")
    public List<ContactDTO> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found with id: " + id));
        return convertToDTO(contact);
    }

    @PostMapping("/add")
    public ContactDTO createContact(@RequestBody ContactDTO contactDTO) {
        Contact contact = convertToEntity(contactDTO);
        Contact savedContact = contactRepository.save(contact);
        return convertToDTO(savedContact);
    }

    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found with id: " + id));
        Contact updatedContact = convertToEntity(contactDTO);
        BeanUtils.copyProperties(updatedContact, existingContact, "id");
        Contact savedContact = contactRepository.save(existingContact);
        return convertToDTO(savedContact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found with id: " + id));
        contactRepository.delete(contact);
    }

    private ContactDTO convertToDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        BeanUtils.copyProperties(contact, contactDTO);
        return contactDTO;
    }

    private Contact convertToEntity(ContactDTO contactDTO) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactDTO, contact);
        return contact;
    }
}


