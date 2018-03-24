package com.github.tolgaybalci.hospital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity{

    private String name;

    @NotNull
    @ManyToOne
    private Hospital hospital;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors;

}
