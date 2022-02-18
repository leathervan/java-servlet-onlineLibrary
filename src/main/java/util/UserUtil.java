package util;

import entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserUtil {
    public static void putToSession(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("id",user.getId());
        switch (user.getRole_id()) {
            case 1:
                session.setAttribute("role", "admin");
                break;
            case 2:
                session.setAttribute("role", "worker");
                break;
            case 3:
                session.setAttribute("role", "user");
                break;
        }
        session.setAttribute("authorized", true);
    }
}