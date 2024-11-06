package com.example.smartdeltasystems.repository;



import com.example.smartdeltasystems.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}