package com.example.Dosify.repository;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

   Doctor findByEmailId(String email);
   Doctor findByMobNo(String mobNo);
   List<Doctor> findByGenderAndAge(Gender gender, int age);
   Doctor findByName(String name);
   @Query(value = "Select * from Doctor c where c.age>=:startAge and c.age<=:endAge", nativeQuery = true)
   List<Doctor> findAllAge(int startAge, int endAge);
}
