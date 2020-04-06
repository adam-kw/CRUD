package com.adamkwiecinski.thymeleafdemo.thymeleafdemo.data;

import com.adamkwiecinski.thymeleafdemo.thymeleafdemo.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    
}
