package com.sunilos.dao;

import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.StudentDTO;

/**
 * College DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface StudentDAOInt extends BaseDAOInt<StudentDTO> {

	/**
	 * Finds college by name.
	 * 
	 * @param name
	 * @return
	 */
	public StudentDTO findByEmail(String email);

}
