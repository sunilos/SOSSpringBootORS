package com.sunilos.dao;

import com.sunilos.dto.CollegeDTO;

/**
 * College DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface CollegeDAOInt extends BaseDAOInt<CollegeDTO> {

	/**
	 * Finds college by name.
	 * 
	 * @param name
	 * @return
	 */
	public CollegeDTO findByName(String name);

}
