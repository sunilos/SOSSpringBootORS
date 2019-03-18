package com.sunilos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.sunilos.dto.BaseDTO;

public abstract class BaseDAORepImpl<T extends BaseDTO, D extends BaseJpaRespositoryInt<T>> implements BaseDAOInt<T> {

	@Autowired
	protected D repository;

	@Override
	public T findByPK(long pk) {

		Optional<T> optional = repository.findById(pk);
		T dto = optional.get();
		return dto;
	}

	/**
	 * Creates criteria for search method
	 * 
	 * @param dto
	 * @return
	 */
	protected Criteria<T> getCriteria(T dto) {
		return null;
	}

	@Override
	public List search(T dto, int pageNo, int pageSize) {

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

		Criteria<T> criteria = getCriteria(dto);

		Page<T> page = null;

		if (criteria != null) {
			page = repository.findAll(criteria, pageRequest);
		} else {
			page = repository.findAll(pageRequest);
		}

		return page.getContent();
	}

	@Override
	public List search(T dto) {
		Criteria<T> criteria = getCriteria(dto);
		List<T> list = repository.findAll(criteria);
		return list;
	}

	/**
	 * Populate required data
	 * 
	 * @param dto
	 */
	protected void populate(T dto) {
	}

	@Override
	public long add(T dto) {
		populate(dto);
		dto = repository.save(dto);
		return dto.getId();
	}

	@Override
	public void update(T dto) {
		populate(dto);
		dto = repository.save(dto);
	}

	@Override
	public void delete(T dto) {
		repository.delete(dto);
	}

}
