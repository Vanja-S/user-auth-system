package tehnicne.vescine.naloga.dao;

import tehnicne.vescine.naloga.entity.User;
import tehnicne.vescine.naloga.exception.UserNotFoundException;

import java.util.List;

public interface UserDao {

    User findByUserName(String userName) throws UserNotFoundException;

    boolean exists(String userName);

    void save(User theUser);

    void update(User theUser);

    List<User> findAll();
}
