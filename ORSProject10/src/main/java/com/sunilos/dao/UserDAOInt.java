package com.sunilos.dao;

import java.util.List;

import com.sunilos.dto.UserDTO;

public interface UserDAOInt extends BaseDAOInt<UserDTO> {

	public UserDTO findByLogin(String login);

	public UserDTO findByLoginAndPassword(String login, String password);
	
	public List<UserDTO> findByMobileNo(String mobile);
	
}
