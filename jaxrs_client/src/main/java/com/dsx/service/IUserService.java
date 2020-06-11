package com.dsx.service;



import com.dsx.entity.User;

import javax.ws.rs.*;
import java.util.List;


public interface IUserService {

	public void saveUser(User user);

	public void updateUser(User user);

	public List<User> findAllUsers();

	public User finUserById(Integer id);

	public void deleteUser(Integer id);
}
