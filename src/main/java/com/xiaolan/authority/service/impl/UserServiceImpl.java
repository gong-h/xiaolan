package com.xiaolan.authority.service.impl;

import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.mapper.UserMapper;
import com.xiaolan.authority.repository.IUserRepository;
import com.xiaolan.authority.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements IUserService {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsernameFromMapper(String username, String password) {
        return userMapper.getByUsernameFromMapper(username);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public Page<User> findByNicknameLike(String nickname, Pageable pageRequest) {
        return userRepository.findByNicknameLike(nickname, pageRequest);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User upUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUser(User user) {
        return userRepository.findOne(user.getId());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> listOfUser(User user) {
        if (user == null)
            user = new User();
        user.setCreateDate(null);
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("username", GenericPropertyMatchers.contains())// like包含name属性
                .withMatcher("nickname", GenericPropertyMatchers.contains())// like包含description属性
                ;
        return userRepository.findAll(Example.of(user, matcher));
    }

    @Override
    public List<User> queryNotAdmin(User user) {
        return userRepository.queryUsersNotAdmin();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("不存在该用户");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
