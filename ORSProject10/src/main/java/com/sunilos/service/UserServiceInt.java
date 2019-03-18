package com.sunilos.service;

import java.util.List;

import com.sunilos.dto.MarksheetDTO;
import com.sunilos.dto.RoleDTO;
import com.sunilos.dto.UserDTO;
import com.sunilos.exception.ApplicationException;

/**
 * User Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface UserServiceInt extends BaseServiceInt<UserDTO> {

	/**
	 * Registers a user
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public long registerUser(UserDTO dto);

	/**
	 * Finds user by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return dto
	 */
	public UserDTO findByLogin(String login);

	/**
	 * Change Password By pk
	 * 
	 * @param pk
	 *            ,oldPassword,newPassword : get parameter
	 * @return dto
	 */
	public boolean changePassword(Long id, String oldPassword, String newPassword);

	/**
	 * User Authentication
	 * 
	 * @return dto : Contains User's information
	 * @param dto
	 */
	public UserDTO authenticate(UserDTO dto);

	/**
	 * Lock User for certain time duration
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 */
	public boolean lock(String login);

	/**
	 * Reset Password of User with auto generated Password
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 */
	public boolean resetPassword(String login) throws ApplicationException;

	/**
	 * Send the password of User to his Email
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 */
	public boolean forgetPassword(String login) throws ApplicationException;

	/**
	 * Get User Roles
	 * 
	 * @return RoleDTO : User Role
	 * @param dto
	 */
	public RoleDTO getRole(UserDTO dto);

	/**
	 * Update User access
	 * 
	 * @return dto
	 * @param dto
	 * @throws ApplicationException
	 */
	public UserDTO updateAccess(UserDTO dto);
}