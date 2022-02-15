package service;

import dao.user.UserDao;
import entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean signUp(User user) {
        return user != null && userDao.create(user).getId() != -1;
    }

    public User getUserByCredentials(String login, String password) {;
        return userDao.getUserByLoginAndPassword(login, password);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getUserById(long userId) {
        return userDao.get(userId);
    }

    public boolean changeUserStatus(long userId, boolean status) {
        return userDao.changeStatus(userId, status);
    }

    public boolean checkLogin(String login){
        return userDao.checkLogin(login)!=null;
    }

    public static void putToSession(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("id",user.getId());
        switch (user.getRole_id()) {
            case 1:
                session.setAttribute("role", "admin");
                break;
            case 2:
                session.setAttribute("role", "manager");
                break;
            case 3:
                session.setAttribute("role", "user");
                break;
        }
        session.setAttribute("authorized", true);
    }
}
