package com.example.authserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.authserver.client.LoginClient;
import com.example.authserver.entity.UserInfo;
import com.example.authserver.util.RequestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息获取
 * @author pkpm
 *
 */
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Resource
    private LoginClient loginClient;

    /**
     * 通过 Username 加载用户详情
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String password =  RequestUtils.getCurrentRequest().getParameter("password");
        Integer userId = loginClient.login(username, password);
        logger.info("user_id : {}", userId);
        //return new User(userId == null ? username : userId.toString(), password, authorities);
        try {
			if (userId != 0) {
				UserDetails userDetails = new User(userId.toString(), password, authorities);
				return userDetails;
			}
			return new User(username,password, authorities);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new User(username,password, authorities);
    }
  /*  @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String password =  RequestUtils.getCurrentRequest().getParameter("password");
        Integer i = loginClient.login(username, password);
       // logger.info("user_id : {}", userId);
        //return new User(userId == null ? username : userId.toString(), password, authorities);
        if (i != 0) {
        	UserDetails userDetails = new User(userInfo.getUserID().toString(), password, authorities);
        	return userDetails;
        }
        return null;
    }*/
}
