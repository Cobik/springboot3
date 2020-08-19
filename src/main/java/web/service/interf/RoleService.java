package web.service.interf;

import web.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role getRole(String name);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
}
