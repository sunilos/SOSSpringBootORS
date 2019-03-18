package com.sunilos.service;

import java.util.List;

import com.sunilos.dao.BaseJpaRespositoryInt;
import com.sunilos.dto.BaseDTO;
import com.sunilos.dto.RoleDTO;
import com.sunilos.exception.DuplicateRecordException;

/**
 * Role Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface BaseServiceInt<T extends BaseDTO> {

	/**
	 * Adds a Role.
	 * 
	 * @param dto
	 * @return
	 * @throws DuplicateRecordException
	 */
	public long add(T dto) throws DuplicateRecordException;

	/**
	 * Updates a Role.
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 */
	public void update(T dto) throws DuplicateRecordException;

	/**
	 * Deletes a Role
	 * 
	 * @param id
	 */
	public T delete(long id);

	/**
	 * Finds a Role by ID
	 * 
	 * @param id
	 * @return
	 */
	public T findById(long id);

	/**
	 * Searches Roles with pagination.
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List search(T dto, int pageNo, int pageSize);

	/**
	 * Searches Roles
	 * 
	 * @param dto
	 * @return
	 */
	public List search(T dto);

}