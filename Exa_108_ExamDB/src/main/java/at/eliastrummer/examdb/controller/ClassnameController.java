package at.eliastrummer.examdb.controller;

import at.eliastrummer.examdb.pojos.Classname;
import at.eliastrummer.examdb.repositories.ClassnameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classnames")
public class ClassnameController {
    @Autowired
    private ClassnameRepository classnameRepository;


    @GetMapping
    public List<Classname> getAllClassNames() {
        return classnameRepository.findAll().stream().sorted(Comparator.comparing(Classname::getClassname)).collect(Collectors.toList());
    }
}