package com.github.tolgaybalci.hospital.repository;

import com.github.tolgaybalci.hospital.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long>{
}
