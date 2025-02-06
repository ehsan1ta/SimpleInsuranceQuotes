package com.example.siqa.model.da;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Quote> quotes;

}
