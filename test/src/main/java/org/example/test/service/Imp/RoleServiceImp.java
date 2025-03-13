package org.example.test.service.Imp;


import lombok.RequiredArgsConstructor;
import org.example.test.model.Role;
import org.example.test.repository.RoleRepository;
import org.example.test.service.RoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    final RoleRepository roleRepository;
    @Override
    public Role getByName(String name) {
        return roleRepository.findByRole(name);
    }
}
