package com.hx.myshop.plus.business.service;

import com.google.common.collect.Lists;
import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jxlgcmh
 * @date 2019-09-15 11:19
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.1")
    private UmsAdminService umsAdminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdmin umsAdmin = umsAdminService.getByUserName(username);
        if (umsAdmin == null) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);
        return new User(umsAdmin.getUsername(), umsAdmin.getPassword(), grantedAuthorities);
    }
}
