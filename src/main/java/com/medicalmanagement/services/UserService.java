package com.medicalmanagement.services;

import com.medicalmanagement.constants.ERole;
import com.medicalmanagement.constants.Status;
import com.medicalmanagement.entity.Role;
import com.medicalmanagement.entity.UserEntity;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.RoleRepository;
import com.medicalmanagement.repository.UserRepository;
import com.medicalmanagement.services.dto.UserDTO;
import com.medicalmanagement.services.dto.request.UpdateUserDTO;
import com.medicalmanagement.services.dto.response.UserProjection;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserEntity findByIdUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new Exception("Thông tin của nhân viên chưa tồn tại"));
        return user;
    }
    @Transactional
    public List<UserProjection> listUser() {
        List<UserProjection> listUser = userRepository.listUser();
        return listUser;
    }
    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUserName())) {
            throw new Exception("Username đã tồn tại");
        }
            UserEntity user = new UserEntity();
            user.setUsername(userDTO.getUserName());
            user.setName(userDTO.getName());
            user.setPassword(userDTO.getPassWord());
            user.setAddress(userDTO.getAddress());
            user.setEmail(userDTO.getEmail());
            user.setPosition(userDTO.getPosition());
            user.setPhone(userDTO.getPhone());
            user.setStatus(1);

            Set<String> strRoles = userDTO.getRoles();
            Set<Role> roles = new HashSet<>();

                strRoles.forEach(role -> {
                            switch (role) {
                                case "admin":
                                    Role roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN)
                                            .orElseThrow(() -> new Exception("Không tìm thấy role"));
                                    roles.add(roleAdmin);
                                    break;
                                case "doctor":
                                    Role roleDoctor = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                            .orElseThrow(() -> new Exception("Không tìm thấy role"));
                                    roles.add(roleDoctor);
                                    break;
                                case "accountant":
                                    Role roleAccountant = roleRepository.findByName(ERole.ROLE_ACCOUNTANT)
                                            .orElseThrow(() -> new Exception("Không tìm thấy role"));
                                    roles.add(roleAccountant);
                                    break;
                                case "nurse":
                                    Role roleNurse = roleRepository.findByName(ERole.ROLE_NURSE)
                                            .orElseThrow(() -> new Exception("Không tìm thấy role"));
                                    roles.add(roleNurse);
                                default:
                                    Role roleNurse2 = roleRepository.findByName(ERole.ROLE_NURSE)
                                            .orElseThrow(() -> new Exception("Không tìm thấy role"));
                                    roles.add(roleNurse2);
                            }
                        }
                );
            user.setRoles(roles);
            userRepository.save(user);
        return userDTO;
    }
    @Transactional
    public void updateStatus(Long id) {
        UserEntity user = findByIdUser(id);
        if (user.getStatus().equals(Status.ACTIVE.getId())) {
            user.setStatus(Status.INACTIVE.getId());
            userRepository.save(user);
        } else {
            user.setStatus(Status.ACTIVE.getId());
            userRepository.save(user);
        }
    }
    @Transactional
    public UserEntity updateUser(Long id, UpdateUserDTO userDTO) {
        UserEntity user = findByIdUser(id);
        user.setName(user.getName());
        user.setPassword(userDTO.getPassWord());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPosition(userDTO.getPosition());
        user.setPhone(userDTO.getPhone());

        Set<String> strRoles = userDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new Exception("Không tìm thấy role"));
                            roles.add(roleAdmin);
                            break;
                        case "doctor":
                            Role roleDoctor = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                    .orElseThrow(() -> new Exception("Không tìm thấy role"));
                            roles.add(roleDoctor);
                            break;
                        case "accountant":
                            Role roleAccountant = roleRepository.findByName(ERole.ROLE_ACCOUNTANT)
                                    .orElseThrow(() -> new Exception("Không tìm thấy role"));
                            roles.add(roleAccountant);
                            break;
                        case "nurse":
                            Role roleNurse = roleRepository.findByName(ERole.ROLE_NURSE)
                                    .orElseThrow(() -> new Exception("Không tìm thấy role"));
                            roles.add(roleNurse);
                        default:
                            Role roleNurse2 = roleRepository.findByName(ERole.ROLE_NURSE)
                                    .orElseThrow(() -> new Exception("Không tìm thấy role"));
                            roles.add(roleNurse2);
                    }
                }
        );
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }
}
