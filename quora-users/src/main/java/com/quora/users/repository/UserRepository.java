package com.quora.users.repository;

import com.quora.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Modifying
    @Transactional
    @Query(value = "update quora_users set is_private = not is_private where user_email = ?1", nativeQuery = true)
    Integer switchPrivacy(String userEmail);

    List<User> findByAssociatedBusinessEmail(String associatedBusinessEmail);

    @Query(value = "select * from quora_users where is_private = '0'",nativeQuery = true)
    List<User> getPublicUser();

    @Query(value = "select associated_business_email from quora_users where user_email=?1",nativeQuery = true)
    String getBusinessEmail(String userEmail);
}
