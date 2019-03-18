package com.sunilos.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.dao.Criteria;
import com.sunilos.dao.RoleDAOInt;
import com.sunilos.dao.RoleJpaRespositoryInt;
import com.sunilos.dto.BaseDTO;
import com.sunilos.dto.RoleDTO;
import com.sunilos.exception.DatabaseException;
import com.sunilos.exception.DuplicateRecordException;

/**
 * Session facade of Role Service. It is transactional, apply declarative
 * transactions with help of Spring AOP.
 * 
 * If unchecked exception is propagated from a method then transaction is rolled
 * back.
 * 
 * Default propagation value is Propagation.REQUIRED and readOnly = false
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, RoleDAOInt> implements RoleServiceInt {

	private static Logger log = Logger.getLogger(RoleServiceImpl.class);

	@Transactional(readOnly = true)
	public RoleDTO findByName(String roleName) {
		RoleDTO dto = baseDao.findByName(roleName);
		return dto;
	}

	@Override
	protected void checkDuplicate(RoleDTO dto) {
		RoleDTO dtoExist = findByName(dto.getName());
		if (dtoExist != null && dto.getId() != dtoExist.getId()) {
			throw new DuplicateRecordException("Role Name already exists");
		}
	}
	
	/*
	@Override
	protected Criteria<RoleDTO> getCriteria(RoleDTO dto) {
		Criteria<RoleDTO> criteria = new Criteria<RoleDTO>("1",":","");
		if(dto.getName()!=null && dto.getName().length()>0){
			criteria.and(new Criteria<>("name",":",dto.getName()));
		}
		return criteria;
	}
	*/


}