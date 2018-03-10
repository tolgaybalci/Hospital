package com.github.tolgaybalci.hospital.repository;

import com.github.tolgaybalci.hospital.domain.Hospital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long>{
}
