package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    UserAccount getByUserId(String id);
    UserAccount getByUsername(String username);
    UserAccount getByContext();
}
