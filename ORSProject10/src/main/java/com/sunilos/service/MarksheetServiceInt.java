package com.sunilos.service;

import java.util.List;

import com.sunilos.dto.MarksheetDTO;

/**
 * College Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface MarksheetServiceInt extends BaseServiceInt<MarksheetDTO> {

	/**
	 * Finds marksheet by name.
	 * 
	 * @param name
	 * @return
	 */
	public MarksheetDTO findByName(String name);

	/**
	 * Finds marksheet by Roll No
	 * 
	 * @param rollNo
	 * @return
	 */
	public MarksheetDTO findByRollNo(String rollNo);

	/**
	 * Gets merit list of students
	 * 
	 * @return
	 */
	public List<MarksheetDTO> getMeritList();
}