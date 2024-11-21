package com.EmployeeSpringBoot.employee.repository;

import com.EmployeeSpringBoot.employee.entity.EmployeeDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IEmployeeDetails extends MongoRepository<EmployeeDetails, ObjectId> {
}
