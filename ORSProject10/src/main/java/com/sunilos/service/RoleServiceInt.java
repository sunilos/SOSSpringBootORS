package com.sunilos.service;

import java.util.List;

import com.sunilos.dto.RoleDTO;
import com.sunilos.exception.DuplicateRecordException;

/**
 * Role Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface RoleServiceInt extends BaseServiceInt<RoleDTO> {

	/**
	 * Finds a Role by name.
	 * 
	 * @param roleName
	 * @return
	 */
	public RoleDTO findByName(String roleName);

}