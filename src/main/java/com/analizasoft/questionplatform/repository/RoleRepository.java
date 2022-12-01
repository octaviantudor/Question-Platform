package com.analizasoft.questionplatform.repository;

import com.analizasoft.questionplatform.domain.entity.Role;
import com.analizasoft.questionplatform.domain.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
