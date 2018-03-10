package com.github.tolgaybalci.hospital.repository;

import com.github.tolgaybalci.hospital.domain.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long>{
}
