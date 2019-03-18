package com.sunilos.dao;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.RoleDTO;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Repository
public interface RoleJpaRespositoryInt extends BaseJpaRespositoryInt<RoleDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param roleName
	 * @return
	 */
	public RoleDTO findByName(String roleName);

}
