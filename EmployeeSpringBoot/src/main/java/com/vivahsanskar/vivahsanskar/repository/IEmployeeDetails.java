package com.vivahsanskar.vivahsanskar.repository;

import com.vivahsanskar.vivahsanskar.entity.EmployeeDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IEmployeeDetails extends MongoRepository<EmployeeDetails, ObjectId> {
}
