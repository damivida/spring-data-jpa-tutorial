package com.example.springdatajpatutotial.repository;


import com.example.springdatajpatutotial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Queries derived from method name
    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String name);
    public List<Student> findByLastNameNotNull();
    public List<Student> findByGuardianName(String guardianName);
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName);
    public List<Student> deleteByFirstName(String firstName);

    //JPQL queries
    @Query("select s from Student s where s.emailId = ?1")
    public Student findByEmailAddress(String emailAddress);
    @Query("select s.firstName from Student s where s.lastName = ?1")
    public List<String> findNameByLastName(String lastName);

    //Native SQL query
    @Query(
            value = "select * from tbl_student s where s.guardian_email = ?1",
            nativeQuery = true
    )
    public List<Student> findByGuardianEmail(String email);


    @Query(
            value = "select * from tbl_student s where s.guardian_email = :email and s.guardian_name = :name",
            nativeQuery = true
    )
    public List<Student> findByGuardianEmailAndGuardianName(@Param("email") String email, @Param("name") String name);

    @Modifying
    //Transactional should be used in service layer
    @Transactional
    @Query(
            value = "update tbl_student s set s.last_name = :lastName where s.email_address = :email",
            nativeQuery = true
    )
      int updateLastNameByEmail(@Param("lastName") String lastName, @Param("email") String email);



    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student s set guardian_mobile = :guardianMobile where first_name = :firstName",
            nativeQuery = true
    )
    int updateGuardianPhoneByFirstName(@Param("guardianMobile") String guardianMobile, @Param("firstName") String firstName);

}
