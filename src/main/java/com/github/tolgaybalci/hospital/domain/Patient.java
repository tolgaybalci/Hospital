package com.github.tolgaybalci.hospital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntity{

    private String name;

    private String surname;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate birthDate;

    private Gender gender;

    @OneToMany
    private List<Appoint> appoints;
}
