package com.sunilos.dao;

import java.util.List;

import com.sunilos.dto.RoleDTO;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface BaseDAOInt<T> {

	/**
	 * Adds a Role.
	 * 
	 * @param dto
	 * @return
	 */
	public long add(T dto);

	/**
	 * Updates a Role.
	 * 
	 * @param dto
	 */
	public void update(T dto);

	/**
	 * Deletes a Role.
	 * 
	 * @param dto
	 */
	public void delete(T dto);

	/**
	 * Finds Role by Primary Key.
	 * 
	 * @param pk
	 * @return
	 */
	public T findByPK(long pk);

	/**
	 * Searches Role with pagination.
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List search(T dto, int pageNo, int pageSize);

	/**
	 * Seraches Role.
	 * 
	 * @param dto
	 * @return
	 */
	public List search(T dto);

}
