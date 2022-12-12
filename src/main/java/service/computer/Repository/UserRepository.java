package service.computer.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.computer.Models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findAll();
    boolean existsUserByLogin(String login);
    boolean existsUserByLoginAndPassword(String login, String password);
    User getById(Long id);
    User findByActivationCode(String code);
}
