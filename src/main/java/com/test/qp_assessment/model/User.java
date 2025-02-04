package com.test.qp_assessment.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_users")
@Data
public class User {
    @Id
    private Long id;
    private String name;
    private String username;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;


}
