package com.sunilos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sunilos.dto.MarksheetDTO;
import com.sunilos.dto.UserDTO;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Repository
public interface UserJpaRespositoryInt extends BaseJpaRespositoryInt<UserDTO> {

	public UserDTO findByLogin(String login);

	public UserDTO findByLoginAndPassword(String login, String password);

	public List<UserDTO> findByMobileNo(String mobile);

}
