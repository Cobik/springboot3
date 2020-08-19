package web.service.interf;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    boolean addUser(User user) throws SQLException;
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(long id);
    User getUsersById(long id);
    boolean updateUser1(User user);


    User findByUsername(String username);
}
