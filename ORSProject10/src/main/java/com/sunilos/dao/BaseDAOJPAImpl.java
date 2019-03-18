package com.sunilos.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.BaseDTO;

public abstract class BaseDAOJPAImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public long add(T dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(T dto) {
		entityManager.merge(dto);
	}

	@Override
	public void delete(T dto) {
		entityManager.remove(dto);
	}

}
