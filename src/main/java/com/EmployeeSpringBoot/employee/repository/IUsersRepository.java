package com.EmployeeSpringBoot.employee.repository;

import com.EmployeeSpringBoot.employee.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUsersRepository extends MongoRepository<Users, ObjectId> {

    Users findByUserName(String userName);

    void deleteByUserName(String name);
}
