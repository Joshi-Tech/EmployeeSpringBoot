package com.vivahsanskar.vivahsanskar.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data //This is used to create Getter and Setter
@Document //This annotation maps to one of the collection of MongoDB
@NoArgsConstructor
public class EmployeeDetails {
    @Id //This is used to make a parameter primary key.
    private ObjectId id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private int age;
    @NonNull
    private String gender;
    private String profession;
    private String qualification;
    private LocalDate date;
}
