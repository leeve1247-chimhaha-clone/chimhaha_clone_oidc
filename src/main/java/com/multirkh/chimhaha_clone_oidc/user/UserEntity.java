package com.multirkh.chimhaha_clone_oidc.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(unique = true)
    private String githubId;

    @Column(unique = true)
    private String naverId;
}
