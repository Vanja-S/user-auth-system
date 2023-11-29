package tehnicne.vescine.naloga.dao;

import tehnicne.vescine.naloga.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);
}
