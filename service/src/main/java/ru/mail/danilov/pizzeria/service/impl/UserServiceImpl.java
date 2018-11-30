package ru.mail.danilov.pizzeria.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mail.danilov.pizzeria.dao.ProfileDao;
import ru.mail.danilov.pizzeria.dao.RoleDao;
import ru.mail.danilov.pizzeria.dao.UserDao;
import ru.mail.danilov.pizzeria.dao.model.Profile;
import ru.mail.danilov.pizzeria.dao.model.Role;
import ru.mail.danilov.pizzeria.dao.model.User;
import ru.mail.danilov.pizzeria.dao.utils.RoleEnum;
import ru.mail.danilov.pizzeria.service.UserService;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.converter.impl.dao.ProfileConverter;
import ru.mail.danilov.pizzeria.service.dto.ChangePasswordDto;
import ru.mail.danilov.pizzeria.service.dto.ProfileDto;
import ru.mail.danilov.pizzeria.service.dto.RoleDto;
import ru.mail.danilov.pizzeria.service.dto.UserDto;
import ru.mail.danilov.pizzeria.service.utils.AuthenticatedUserUtil;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final ProfileDao profileDao;
    private final ConverterDto<UserDto, User> userDtoConverter;
    private final Converter<UserDto, User> userConverter;
    private final ProfileConverter profileConverter;
    private final ConverterDto<RoleDto, Role> roleDtoConverter;
    private final AuthenticatedUserUtil userUtil;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           RoleDao roleDao,
                           ProfileDao profileDao,
                           @Qualifier("userDtoConverter") ConverterDto<UserDto, User> userDtoConverter,
                           @Qualifier("userConverter") Converter<UserDto, User> userConverter,
                           @Qualifier("profileConverter") ProfileConverter profileConverter,
                           @Qualifier("roleDtoConverter") ConverterDto<RoleDto, Role> roleDtoConverter,
                           AuthenticatedUserUtil userUtil,
                           BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.profileDao = profileDao;
        this.userDtoConverter = userDtoConverter;
        this.userConverter = userConverter;
        this.profileConverter = profileConverter;
        this.userUtil = userUtil;
        this.roleDtoConverter = roleDtoConverter;
        this.encoder = encoder;
    }


    @Override
    public List<UserDto> getForPage(Long page) {
        List<User> users = userDao.getForPage(page);
        return userDtoConverter.toDtoList(users);
    }

    @Override
    public UserDto register(UserDto userDto) {
        User user = userConverter.toEntity(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setDelete(false);
        user.setEnable(true);
        Role role = roleDao.getByName(RoleEnum.CUSTOMER.name());
        user.setRole(role);
        userDao.create(user);
        return userDto;
    }

    @Override
    public UserDto getById(Long id) {
        User user = userDao.findOne(id);
        UserDto userDto = userDtoConverter.toDto(user);
        logger.info("Getting user success");
        return userDto;
    }

    @Override
    public void delete(Long id) {
        User user = userDao.findOne(id);
        user.setDelete(true);
        logger.info("user was delete");
    }

    @Override
    public void enable(Long id) {
        User user = userDao.findOne(id);
        if (user.getEnable()) {
            user.setEnable(false);
            logger.info("user disabled");
        } else {
            user.setEnable(true);
            logger.info("user enabled");
        }
    }

    @Override
    public Long countPages(Long quantity) {
        logger.info("start count item controllers");
        Long count = userDao.countAll();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }

    @Override
    public ProfileDto saveProfile(ProfileDto profileDto) {
        Profile profile = profileConverter.toEntity(profileDto);
        User user = userDao.findOne(userUtil.getUserPrincipal().getId());
        user.setProfile(profile);
        profile.setUser(user);
        profileDao.create(profile);
        return profileDto;
    }

    @Override
    public List<RoleDto> getRoleList() {
        List<Role> roles = roleDao.findAll();
        return roleDtoConverter.toDtoList(roles);
    }

    @Override
    public void update(UserDto userDto) {
        User user = userDao.findOne(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setLogin(userDto.getLogin());
    }

    @Override
    public void changeRole(Long userId, Long roleId) {
        User user = userDao.findOne(userId);
        Role role = roleDao.findOne(roleId);
        user.getRole().getUsers().remove(user.getRole());
        user.setRole(role);
        role.getUsers().add(user);
    }

    @Override
    public void changePassword(ChangePasswordDto passwordDto) {
        User user = userDao.findOne(passwordDto.getUser());
        String password = encoder.encode(passwordDto.getNewPassword());
        user.setPassword(password);
    }
}
