package com.github.tolgaybalci.hospital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appoint extends BaseEntity {

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate date;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;
}
