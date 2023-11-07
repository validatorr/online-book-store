package v.hryhoryk.onlinebookstore.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v.hryhoryk.onlinebookstore.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(Role.RoleName roleName);
}
