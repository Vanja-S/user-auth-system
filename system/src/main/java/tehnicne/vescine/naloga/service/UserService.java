package tehnicne.vescine.naloga.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tehnicne.vescine.naloga.entity.User;
import tehnicne.vescine.naloga.exception.UserNotFoundException;
import tehnicne.vescine.naloga.exception.UsernameAlreadyInUseException;
import tehnicne.vescine.naloga.user.WebUser;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName) throws UserNotFoundException;

    void save(WebUser webUser);

    void update(WebUser webUser, String oldUsername) throws UserNotFoundException, UsernameAlreadyInUseException;

    List<User> findAll();

}
