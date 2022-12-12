package service.computer.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.computer.Models.Role;
import service.computer.Models.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(Role name);
}
