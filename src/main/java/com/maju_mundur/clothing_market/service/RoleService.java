package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.constant.UserRole;
import com.maju_mundur.clothing_market.entity.Role;

public interface RoleService {
    Role getOrSave(UserRole role);
}
