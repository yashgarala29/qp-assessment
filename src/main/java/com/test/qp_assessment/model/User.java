package com.test.qp_assessment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;


}
