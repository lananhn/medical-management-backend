package com.medicalmanagement.services;

import com.medicalmanagement.constants.ERole;
import com.medicalmanagement.constants.Status;
import com.medicalmanagement.entity.Role;
import com.medicalmanagement.entity.ServiceEntity;
import com.medicalmanagement.entity.TypeOfService;
import com.medicalmanagement.entity.User;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.RoleRepository;
import com.medicalmanagement.repository.UserRepository;
import com.medicalmanagement.services.dto.request.UserDTO;
import com.medicalmanagement.services.dto.request.UpdateUserDTO;
import com.medicalmanagement.services.dto.response.UserDto;
import com.medicalmanagement.services.dto.response.UserProjection;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder encoder;

    public User findByIdUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
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
            User user = new User();
            user.setUsername(userDTO.getUserName());
            user.setName(userDTO.getName());
            user.setPassword(encoder.encode(userDTO.getPassWord()));
            user.setAddress(userDTO.getAddress());
            user.setEmail(userDTO.getEmail());
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
        User user = findByIdUser(id);
        if (user.getStatus().equals(Status.ACTIVE.getId())) {
            user.setStatus(Status.INACTIVE.getId());
            userRepository.save(user);
        } else {
            user.setStatus(Status.ACTIVE.getId());
            userRepository.save(user);
        }
    }
    @Transactional
    public User updateUser(Long id, UpdateUserDTO userDTO) {
        User user = findByIdUser(id);
        user.setName(user.getName());
        user.setPassword(encoder.encode(userDTO.getPassWord()));
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
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
//    @Transactional
//    public UserDto getUserById(Long id, Long idRole) {
//        User entity = userRepository.findById(id).orElseThrow(() ->
//                new Exception("Chưa tồn tại thông tin của nhân viên"));
//        Role role = roleRepository.findById(idRole).orElseThrow(() ->
//                new Exception("Chưa tồn tại thông tin của role"));
//        if (entity.getRoles().getId() != typeOfService.getId()) {
//            throw new Exception("Dịch vụ không thuộc trong các loại dịch vụ");
//        }
//    }
}
