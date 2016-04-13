package pengliu.me.service;

import pengliu.me.domain.User;

/**
 * Created by peng on 16-4-13.
 */
public interface UserService
{
    boolean canLogin(User user);
}
