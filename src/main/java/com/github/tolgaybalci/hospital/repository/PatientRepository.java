package com.github.tolgaybalci.hospital.repository;

import com.github.tolgaybalci.hospital.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>{

}
