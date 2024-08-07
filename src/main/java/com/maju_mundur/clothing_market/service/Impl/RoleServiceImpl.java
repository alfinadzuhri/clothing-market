package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.constant.UserRole;
import com.maju_mundur.clothing_market.entity.Role;
import com.maju_mundur.clothing_market.repository.RoleRepository;
import com.maju_mundur.clothing_market.service.RoleService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(UserRole role) {
        return roleRepository.findByRole(role)
                .orElseGet(() -> roleRepository.saveAndFlush(
                        Role.builder()
                                .role(role)
                                .createdAt(new Date())
                                .updatedAt(new Date())
                                .build()
                ));
    }

}
