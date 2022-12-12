package service.computer.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import service.computer.Models.User;
import service.computer.Service.interfaces.IUserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByLogin(username);
            return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
