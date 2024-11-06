package com.example.smartdeltasystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
@Getter
@Setter
@AllArgsConstructor
public class Student {
    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String group;
    private double averageGrade;

    // Геттеры и сеттеры
}
