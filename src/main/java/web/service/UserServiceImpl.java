package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import web.service.interf.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        User user1 = userDao.findUserById(user.getId());
        user1.setUsername(user.getUsername());
        user1.setName(user.getName());
        user1.setRoles(user.getRoles());

        if (user.getPassword().isEmpty()) {
            user1.setPassword(userDao.findUserById(user.getId()).getPassword());
            userDao.save(user1);
            return;
        }
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user1);
    }


    @Transactional
    @Override
    public boolean updateUser1(User user) {
        User user1 = userDao.findUserById(user.getId());
        user1.setUsername(user.getUsername());
        user1.setName(user.getName());
        user1.setRoles(user.getRoles());

        if (user.getPassword().isEmpty()) {
            user1.setPassword(userDao.findUserById(user.getId()).getPassword());
            userDao.save(user1);
            return true;
        }
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user1);
        return true;
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userDao.removeById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUsersById(long id) {
        return userDao.findUserById(id);
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }


}
