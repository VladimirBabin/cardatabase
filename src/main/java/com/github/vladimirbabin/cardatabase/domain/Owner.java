package com.github.vladimirbabin.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String firstname;
    private String lastname;

    @JsonIgnore
    @OneToMany(mappedBy ="owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "car_onwer",
//            joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "ownerid")
//    )
//    private Set<Car> cars = new HashSet<>();

    public Owner(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
