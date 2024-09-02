package com.multirkh.chimhaha_clone_oidc.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByGithubId(String id);
    UserEntity findByUserId(String id);
    UserEntity findByNaverId(String id);
}
