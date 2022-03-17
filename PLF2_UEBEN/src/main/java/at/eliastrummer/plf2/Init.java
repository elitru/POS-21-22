package at.eliastrummer.plf2;

import at.eliastrummer.plf2.pojos.Hobby;
import at.eliastrummer.plf2.pojos.Person;
import at.eliastrummer.plf2.pojos.Teacher;
import at.eliastrummer.plf2.pojos.TeacherData;
import at.eliastrummer.plf2.repositories.HobbyRepository;
import at.eliastrummer.plf2.repositories.PersonRepository;
import at.eliastrummer.plf2.repositories.TeacherRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Init {
    private static final File PEOPLE_FILE = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "people.json").toFile();
    private static final File TEACHERS_FILE = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "teachers.xml").toFile();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostConstruct
    public void onInitDB() {
        loadPeople();
        loadTeachers();
    }

    private void loadPeople() {
        try {
            List<Hobby> hobbies = new ArrayList<>();
            List<Person> people = new ObjectMapper().readValue(PEOPLE_FILE, new TypeReference<List<Person>>() { });

            for(Person p : people) {
                for (int i = 0; i < p.getHobbies().size(); i++) {
                    final Hobby hobby = p.getHobbies().get(i);

                    if (!hobbies.contains(hobby)) {
                        hobbies.add(hobby);
                        hobby.getPeople().add(p);
                        continue;
                    }

                    Hobby existingHobbyRef = hobbies.stream().filter(h -> h.getRawId() == hobby.getRawId()).findFirst().get();
                    existingHobbyRef.getPeople().add(p);
                    p.getHobbies().set(i, existingHobbyRef);
                }
            }

            personRepository.saveAll(people);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTeachers() {
        TeacherData data = JAXB.unmarshal(TEACHERS_FILE, TeacherData.class);
        teacherRepository.saveAll(data.getTeachers());
    }
}