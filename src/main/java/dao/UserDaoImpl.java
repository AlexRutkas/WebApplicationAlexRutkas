package dao;

import model.Role;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean isExist(String login, String password) {
        if (login == null || password == null)
            return false;
        return login.equals("admin") && password.equals("admin")
                || login.equals("user") && password.equals("user");
    }

    @Override
    public Role getRoleByLoginPassword(String login, String password) {
        switch (login) {
            case "admin": return Role.ADMIN;
            case "user": return Role.USER;
        }
        return Role.DEFAULT;
    }
}
