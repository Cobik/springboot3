package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.User;


@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean findByUsernameAndPassword(String username, String password);

    User findUserById(Long id);

    void removeById(Long id);

}
