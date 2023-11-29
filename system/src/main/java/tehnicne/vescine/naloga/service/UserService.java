package tehnicne.vescine.naloga.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tehnicne.vescine.naloga.entity.User;
import tehnicne.vescine.naloga.user.WebUser;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);

    void save(WebUser webUser);

}
