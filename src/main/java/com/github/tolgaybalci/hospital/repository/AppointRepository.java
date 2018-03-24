package com.github.tolgaybalci.hospital.repository;

import com.github.tolgaybalci.hospital.domain.Appoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointRepository extends CrudRepository<Appoint, Long>{
}
