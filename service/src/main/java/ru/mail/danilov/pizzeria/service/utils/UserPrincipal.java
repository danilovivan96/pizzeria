package ru.mail.danilov.pizzeria.service.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mail.danilov.pizzeria.dao.model.Permission;
import ru.mail.danilov.pizzeria.dao.model.User;
import ru.mail.danilov.pizzeria.service.dto.ProfileDto;

import java.util.List;

public class UserPrincipal implements UserDetails {
    private Long id;
    private String login;
    private String password;
    private String role;
    private List<GrantedAuthority> authorities;
    private Boolean isDelete;
    private Boolean isEnable;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = user.getRole().getName();
        String[] authorityList = user.getRole().getPermissions().stream()
                .map(Permission::getName).toArray(String[]::new);
        this.authorities = AuthorityUtils.createAuthorityList(authorityList);
        this.isDelete = user.getDelete();
        this.isEnable = user.getEnable();

    }

    public Boolean getDelete() {
        return isDelete;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {

        return role;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
