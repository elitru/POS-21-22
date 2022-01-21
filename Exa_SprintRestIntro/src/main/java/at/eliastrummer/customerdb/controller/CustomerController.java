package at.eliastrummer.customerdb.controller;

import at.eliastrummer.customerdb.dao.Customer;
import at.eliastrummer.customerdb.data.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Customer>> getAll(@RequestParam(value = "page", required = false) int page, @RequestParam(value = "size", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastname"));
        return ResponseEntity.ok(customerRepository.findAll(pageable));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.of(customerRepository.findById(customerId));
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        try {
            var created = customerRepository.save(customer);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(created.getCustomerId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}