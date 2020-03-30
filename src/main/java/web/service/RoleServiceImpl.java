package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.repository.RoleRepo;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }
    @Override
    public List<Role> allRoles() {
        return roleRepo.findAll();
    }

    @Override
    public void addRole(Role role) {
    }

    @Override
    public void updateRole(Role role) {
    }

    @Override
    public void deleteRole(Long id) {
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepo.findById(id);
    }

    @Override
    public Role getRoleByName(String role) {
        return roleRepo.findByNameRole(role);
    }
}
