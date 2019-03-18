package com.sunilos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunilos.dto.RoleDTO;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface RoleDAOInt extends BaseDAOInt<RoleDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param roleName
	 * @return
	 */
	public RoleDTO findByName(String name);

}
