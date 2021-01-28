package com.quora.users.repository;

import com.quora.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Modifying
    @Transactional
    @Query(value = "update quora_users set is_private = not is_private where user_email = ?1", nativeQuery = true)
    Long switchPrivacy(String userEmail);
}
