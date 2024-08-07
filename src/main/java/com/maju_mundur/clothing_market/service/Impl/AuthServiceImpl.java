package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.constant.UserRole;
import com.maju_mundur.clothing_market.dto.request.AuthRequest;
import com.maju_mundur.clothing_market.dto.response.LoginResponse;
import com.maju_mundur.clothing_market.dto.response.RegisterResponse;
import com.maju_mundur.clothing_market.entity.Customer;
import com.maju_mundur.clothing_market.entity.Merchant;
import com.maju_mundur.clothing_market.entity.Role;
import com.maju_mundur.clothing_market.entity.UserAccount;
import com.maju_mundur.clothing_market.repository.UserAccountRepository;
import com.maju_mundur.clothing_market.service.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    @Value("${clothing_market.superadmin.username}")
    private String superAdminUsername;

    @Value("${clothing_market.superadmin.email}")
    private String superAdminEmail;

    @Value("${clothing_market.superadmin.password}")
    private String superAdminPassword;

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initSuperAdmin(){
        Optional<UserAccount> currentUser = userAccountRepository.findByUsername(superAdminUsername);
        if (currentUser.isPresent()) {
            return;
        }

        Role superAdmin = roleService.getOrSave(UserRole.ROLE_SUPER_ADMIN);
        Role customer = roleService.getOrSave(UserRole.ROLE_MERCHANT);
        Role merchant = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        UserAccount account = UserAccount.builder()
                .username(superAdminUsername)
                .password(passwordEncoder.encode(superAdminPassword))
                .role(List.of(superAdmin, merchant, customer))
                .isEnable(true)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        userAccountRepository.save(account);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse customerRegister(AuthRequest request) {
        Role role = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        String hashPassword = passwordEncoder.encode(request.getPassword());

        UserAccount account = UserAccount.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .role(List.of(role))
                .createdAt(new Date())
                .updatedAt(new Date())
                .isEnable(true)
                .build();

        userAccountRepository.saveAndFlush(account);
        Customer customer = Customer.builder()
                .status(true)
                .userAccount(account)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        customerService.create(customer);

        return RegisterResponse.builder()
                .username(account.getUsername())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse merchantRegister(AuthRequest request) {
        Role role = roleService.getOrSave(UserRole.ROLE_MERCHANT);

        String hashPassword = passwordEncoder.encode(request.getPassword());

        UserAccount account = UserAccount.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .role(List.of(role))
                .createdAt(new Date())
                .updatedAt(new Date())
                .isEnable(true)
                .build();

        userAccountRepository.saveAndFlush(account);

        Merchant merchant = Merchant.builder()
                .status(true)
                .userAccount(account)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        merchantService.create(merchant);

        return RegisterResponse.builder()
                .username(account.getUsername())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginResponse login(AuthRequest request) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        Authentication authenticated = authenticationManager.authenticate(authentication);

        UserAccount userAccount = (UserAccount) authenticated.getPrincipal();
        String token = jwtService.generateToken(userAccount);
        return LoginResponse.builder()
                .token(token)
                .username(userAccount.getUsername())
                .roles(userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }
}
