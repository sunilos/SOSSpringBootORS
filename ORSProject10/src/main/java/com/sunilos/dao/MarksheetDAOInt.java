package com.sunilos.dao;

import java.util.List;

import com.sunilos.dto.MarksheetDTO;

/**
 * Marksheet DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface MarksheetDAOInt extends BaseDAOInt<MarksheetDTO> {

	/**
	 * Finds Marksheet by name.
	 * 
	 * @param name
	 * @return
	 */
	public MarksheetDTO findByName(String name);

	/**
	 * Finds Marskheet by roll number
	 * 
	 * @param rollNo
	 * @return
	 */
	public MarksheetDTO findByRollNo(String rollNo);

	/**
	 * Get merit list
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<MarksheetDTO> getMeritList();
}
