package com.sunilos.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.dao.BaseDAOInt;
import com.sunilos.dto.BaseDTO;
import com.sunilos.exception.DatabaseException;
import com.sunilos.exception.DuplicateRecordException;

public abstract class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> {

	private static Logger log = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	protected D baseDao;

	protected abstract void checkDuplicate(T dto);

	@Transactional(readOnly = true)
	public T findById(long id) {
		T dto = baseDao.findByPK(id);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<T> search(T dto, int pageNo, int pageSize) {
		return baseDao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List<T> search(T dto) {
		return baseDao.search(dto);
	}

	@Transactional(readOnly = false)
	public long add(T dto) throws DuplicateRecordException {
		// check duplicate
		checkDuplicate(dto);
		long pk = baseDao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(T dto) throws DuplicateRecordException {
		checkDuplicate(dto);
		baseDao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T delete(long id) {
		log.debug("Role Service delete Start");
		T dto = findById(id);
		if (dto == null) {
			throw new DatabaseException("Record not found");
		}
		baseDao.delete(dto);
		log.debug("Role Service delete End");
		return dto;
	}

}
