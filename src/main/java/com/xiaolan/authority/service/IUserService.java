package com.xiaolan.authority.service;

import com.xiaolan.authority.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService {
    /**
     * 添加用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    User addUser(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    void delUser(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    User upUser(User user);
    /**
     * 查询一个具体用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    User findUser(User user);
    /**
     * 查询一个具体用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    User findById(Long id);

    User findByUsername(String username);

    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    List<User> listOfUser(User user);

    List<User> queryNotAdmin(User user);

    User getByUsernameFromMapper(String username, String password);

    User getByUsername(String username);


    Page<User> findByNicknameLike(String nickname, Pageable pageRequest);

}
