package com.sunilos.dao;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.CollegeDTO;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Repository
public interface CollegeJpaRespositoryInt extends BaseJpaRespositoryInt<CollegeDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param roleName
	 * @return
	 */
	public CollegeDTO findByName(String roleName);

}
