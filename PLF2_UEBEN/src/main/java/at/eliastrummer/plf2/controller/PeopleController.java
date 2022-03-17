package at.eliastrummer.plf2.controller;

import at.eliastrummer.plf2.dto.CreatePersonPayload;
import at.eliastrummer.plf2.pojos.Hobby;
import at.eliastrummer.plf2.pojos.Person;
import at.eliastrummer.plf2.repositories.HobbyRepository;
import at.eliastrummer.plf2.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/people")
@Slf4j
public class PeopleController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @GetMapping
    public ResponseEntity<Page<Person>> getPeople(@RequestParam(name = "page", required = false) Integer page) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page, 1);
        return ResponseEntity.ok(personRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody CreatePersonPayload person) {
        Person newPerson = new Person();
        newPerson.setDateOfBirth(person.getBirthdate());
        newPerson.setName(person.getName());

        for(String hobbyName : person.getHobbies()) {
            Hobby existingHobby = hobbyRepository.findByNameIgnoreCase(hobbyName);

            if (existingHobby != null) {
                newPerson.getHobbies().add(existingHobby);
                continue;
            }

            Hobby newHobby = new Hobby();
            newHobby.setName(hobbyName);
            hobbyRepository.save(newHobby);
            newPerson.getHobbies().add(newHobby);
        }

        personRepository.save(newPerson);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPerson.getId())
                .toUri()).build();
    }
}
