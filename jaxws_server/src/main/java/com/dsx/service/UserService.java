package com.dsx.service;



import com.dsx.entity.User;

import javax.jws.WebService;
import javax.websocket.server.PathParam;
import java.util.List;

@WebService
public interface UserService {


	public void saveUser(User user);


	public void updateUser(User user);


	public List<User> findAllUsers();


	public User finUserById( Integer id);


	public void deleteUser( Integer id);
}
