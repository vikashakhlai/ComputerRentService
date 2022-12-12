package service.computer.Service.interfaces;

import service.computer.Models.User;
import service.computer.Exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    User saveUser(User user);
    User findByLogin(String login);
    List<User> findAll();
    User findById(Long id);
    User findByLoginAndPassword(String login, String password);
    boolean existsUserByLogin(String login);
    boolean existsUserByLoginAndPassword(String login, String password);
}
