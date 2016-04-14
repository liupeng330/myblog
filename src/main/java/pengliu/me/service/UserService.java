package pengliu.me.service;

import pengliu.me.domain.User;

import java.sql.Timestamp;

/**
 * Created by peng on 16-4-13.
 */
public interface UserService
{
    boolean canLogin(User user);
    void updateLoginTime(String name);
}
