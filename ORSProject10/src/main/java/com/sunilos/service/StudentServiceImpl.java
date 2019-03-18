package com.sunilos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.dao.StudentDAOInt;
import com.sunilos.dto.StudentDTO;
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
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, StudentDAOInt>
		implements StudentServiceInt {

	private static Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Transactional(readOnly = true)
	public StudentDTO findByEmail(String email) {
		StudentDTO dto = baseDao.findByEmail(email);
		return dto;
	}

	@Override
	protected void checkDuplicate(StudentDTO dto) {
		StudentDTO dtoExist = findByEmail(dto.getEmail());
		if (dtoExist != null && dto.getId() != dtoExist.getId()) {
			throw new DuplicateRecordException("Student email already exists");
		}
	}

}