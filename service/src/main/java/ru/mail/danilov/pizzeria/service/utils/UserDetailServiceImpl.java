package ru.mail.danilov.pizzeria.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mail.danilov.pizzeria.dao.UserDao;
import ru.mail.danilov.pizzeria.dao.model.User;

import javax.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByLogin(username);
        UserPrincipal userPrincipal;
        if (user != null) {
            userPrincipal = new UserPrincipal(user);
        } else throw new UsernameNotFoundException("User not found");
        return userPrincipal;
    }
}
