package pengliu.me.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengliu.me.dao.UserDao;
import pengliu.me.domain.User;
import pengliu.me.service.UserService;
import pengliu.me.utils.Common;

/**
 * Created by peng on 16-4-13.
 */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

    public boolean canLogin(User user)
    {
        User returnedUser = userDao.findUserByName(user.getName());
        if(returnedUser == null)
        {
            return false;
        }

        return returnedUser.getPassword().equals(user.getPassword());
    }

    public void updateLoginTime(String name)
    {
        User returnedUser = userDao.findUserByName(name);
        if(returnedUser != null)
        {
            returnedUser.setLastLoginTime(Common.getTimeStampNow());
            this.userDao.persist(returnedUser);
        }
    }
}
