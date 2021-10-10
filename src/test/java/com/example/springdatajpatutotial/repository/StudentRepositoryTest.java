package com.example.springdatajpatutotial.repository;

import com.example.springdatajpatutotial.entity.Guardian;
import com.example.springdatajpatutotial.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Disabled()
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("emailJane.email")
                .firstName("Jane")
                .lastName("Doe")
                //.guardianName("Joe")
                // .guardianEmail("guardian@email.com")
                .build();

        Student student1 = Student.builder()
                .emailId("email@test")
                .firstName("TestUser1")
                .build();

       Student studentSaved =  studentRepository.save(student);
        Assertions.assertEquals(studentSaved, student);

    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("John")
                .email("guardian01@guardian.email")
                .mobile("098-555")
                .build();

        Student student = Student.builder()
                .emailId("student02@email.email")
                .firstName("Jane")
                .lastName("studentLastname0")
                .guardian(guardian)
                .build();

        Student studentSaved = studentRepository.save(student);
        Assertions.assertEquals(studentSaved, student);
    }

    @Test
    public void getAllStudents() {

        List<Student> getAllStudents = studentRepository.findAll();
        System.out.println(getAllStudents);
    }


    @Test
    public void findByFirstName() {

        String firstName = "Jane";
        List<Student> studentListFound = studentRepository.findByFirstName(firstName);
        Assertions.assertEquals(studentListFound.get(0).getFirstName(), firstName);
        studentListFound.forEach(student -> System.out.println(student));
    }

    @Test
    public void findByFirstNameContaining() {

        String name = "Ja";
        List<Student> studentListFound = studentRepository.findByFirstNameContaining(name);
        studentListFound.forEach(studentData -> System.out.println(studentData));
        Assertions.assertEquals(name, studentListFound.get(0).getFirstName().substring(0,2));
    }


    @Test
    public void findByLastNameNotNull() {

        String lastName = "Doe";
        List<Student> studentListFound =  studentRepository.findByLastNameNotNull();
        studentListFound.forEach(studentData -> System.out.println(studentData));
        Assertions.assertEquals(lastName, studentListFound.get(0).getLastName());
    }

    @Test
    public void findByGuardianName() {

       String guardianName =  "Jane";

       List<Student> studentListFound = studentRepository.findByGuardianName(guardianName);
       studentListFound.forEach(studentFound -> System.out.println(studentFound));
       Assertions.assertEquals(guardianName, studentListFound.get(0).getGuardian().getName());
    }

    @Test
    public void findByFirstNameAndLastName() {

        String firstName = "Jane";
        String lastName ="Doe";

       List<Student> studentListFound =  studentRepository.findByFirstNameAndLastName(firstName,lastName);
       studentListFound.forEach(studentData -> System.out.println(studentData));
       Assertions.assertEquals(firstName, studentListFound.get(0).getFirstName());
    }

    @Test
    public void findByEmailAddress() {

        String email = "student@email.email";

        Student studentFound = studentRepository.findByEmailAddress(email);
        System.out.println(studentFound);
        Assertions.assertEquals(email, studentFound.getEmailId());
    }

    @Test
    public void findNameByLastName(){

        String lastName = "Doe";
        String firstName = "John";

        List<String> studentListFound = studentRepository.findNameByLastName(lastName);
        studentListFound.forEach(student -> System.out.println(student));
        Assertions.assertEquals(firstName, studentListFound.get(0));
    }

    @Test
    public void findByGuardianEmail(){

        String guardianEmail = "guardian@email.com";
        List<Student> studentListFound =  studentRepository.findByGuardianEmail(guardianEmail);
        studentListFound.forEach(studentData -> System.out.println(studentData));
        Assertions.assertEquals(guardianEmail, studentListFound.get(0).getGuardian().getEmail());
    }

    @Test
    public void findByGuardianEmailAndGuardianName() {

        String name = "Joe";
        String email = "guardian@email.com";
        String firstNameFound = "Jane";

        List<Student> studentListFound = studentRepository.findByGuardianEmailAndGuardianName(email, name);
        studentListFound.forEach(student -> System.out.println(student));
        Assertions.assertEquals(email, studentListFound.get(0).getGuardian().getEmail());
        Assertions.assertEquals(firstNameFound, studentListFound.get(0).getFirstName());

    }

    @Test
    public void updateLastNameByEmail() {

        String lastName = "Doe";
        String email = "student02@email.email";

       int studentUpdated =  studentRepository.updateLastNameByEmail(lastName,email);
       System.out.println("Student updated: " + studentUpdated);
       Assertions.assertEquals(1, studentUpdated);
    }

}