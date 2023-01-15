package com.medicalmanagement.repository;

import com.medicalmanagement.entity.User;
import com.medicalmanagement.services.dto.response.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    @Query(nativeQuery = true, value = "SELECT users.id as userId, users.address as address, users.email as email, users.name as name, users.password as password, users.phone as phone, users.status as status, users.username as username,\n" +
            "user_roles.role_id as roleId, roles.name as roleName\n" +
            "FROM users\n" +
            "INNER JOIN user_roles on user_roles.user_id=users.id\n" +
            "INNER JOIN roles on user_roles.role_id=roles.id")
    List<UserProjection> listUser();
}
