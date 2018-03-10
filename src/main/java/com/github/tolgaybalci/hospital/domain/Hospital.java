package com.github.tolgaybalci.hospital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hospital extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;

    @Lob
    private String address;
}
