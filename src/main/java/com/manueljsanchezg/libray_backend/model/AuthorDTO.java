package com.manueljsanchezg.libray_backend.model;

import java.util.Date;

public class AuthorDTO {

    private String name;
    private Date birthDate;

    public AuthorDTO(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public AuthorDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
