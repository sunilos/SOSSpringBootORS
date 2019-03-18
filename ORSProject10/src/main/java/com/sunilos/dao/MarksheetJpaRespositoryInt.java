package com.sunilos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sunilos.dto.MarksheetDTO;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Repository
public interface MarksheetJpaRespositoryInt extends BaseJpaRespositoryInt<MarksheetDTO> {

	/**
	 * Finds marksheet by roll number
	 * 
	 * @param rollNo
	 * @return
	 */
	public MarksheetDTO findByRollNo(String rollNo);

	/**
	 * finds Marksheet by name
	 * 
	 * @param name
	 * @return
	 */
	public MarksheetDTO findByName(String name);

	/**
	 * Gets erit list of students 
	 * @return
	 */
	@Query("from MarksheetDTO order by (physics + chemistry + maths) desc")
	public List<MarksheetDTO> getMeritList();

}
