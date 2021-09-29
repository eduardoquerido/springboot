package br.gov.sp.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    @JsonView({View.RegistrationAllView.class, View.RegistrationView.class, View.ProfileAllView.class})
    private Long id;

    @Column(name = "registration_email")
    @JsonView({View.RegistrationAllView.class, View.RegistrationView.class, View.ProfileAllView.class})
    private String email;

    @Column(name = "registration_password")
    @JsonView(View.RegistrationView.class)
    private String password;

    @Column(name = "registration_name")
    @JsonView({View.RegistrationAllView.class, View.RegistrationView.class, View.ProfileAllView.class})
    private String name;

    @Column(name = "registration_cellphone")
    @JsonView({View.RegistrationAllView.class, View.RegistrationView.class})
    private String cellphone;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "registrations")
    private Set<Profile> profiles;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }
}