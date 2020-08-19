package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Role;


public interface RoleDao extends JpaRepository<Role, Long> {
    Role getRoleByRoleName(String name);

    Role getRoleById(Long id);
}
