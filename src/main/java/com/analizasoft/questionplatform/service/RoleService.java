package com.analizasoft.questionplatform.service;

import com.analizasoft.questionplatform.domain.entity.Role;
import com.analizasoft.questionplatform.domain.enums.RoleName;
import com.analizasoft.questionplatform.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName(RoleName.ROLE_USER);
    }

    public Role getAdminRole() {
        return roleRepository.findByName(RoleName.ROLE_ADMIN);
    }
}
