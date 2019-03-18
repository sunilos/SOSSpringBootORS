package com.sunilos.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.dao.CollegeDAOInt;
import com.sunilos.dao.CollegeJpaRespositoryInt;
import com.sunilos.dao.MarksheetDAOInt;
import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.MarksheetDTO;
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
public class MarksheetServiceImpl extends BaseServiceImpl<MarksheetDTO, MarksheetDAOInt>
		implements MarksheetServiceInt {

	private static Logger log = Logger.getLogger(MarksheetServiceImpl.class);

	@Transactional(readOnly = true)
	public MarksheetDTO findByName(String name) {
		return baseDao.findByName(name);
	}

	@Override
	public MarksheetDTO findByRollNo(String rollNo) {
		
		System.out.println("Roll No is " + rollNo);
		return baseDao.findByRollNo(rollNo);
	}

	@Override
	public List<MarksheetDTO> getMeritList() {
		return baseDao.getMeritList();
	}

	@Override
	protected void checkDuplicate(MarksheetDTO dto) {
		MarksheetDTO dtoExist = findByRollNo(dto.getRollNo());
		if (dtoExist != null && dto.getId() != dtoExist.getId()) {
			throw new DuplicateRecordException("Rollno already exists");
		}
	}

}