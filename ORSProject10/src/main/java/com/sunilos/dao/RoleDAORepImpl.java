package com.sunilos.dao;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.RoleDTO;

@Repository
public class RoleDAORepImpl extends BaseDAORepImpl<RoleDTO, RoleJpaRespositoryInt> implements RoleDAOInt {

	@Override
	public RoleDTO findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	protected Criteria<RoleDTO> getCriteria(RoleDTO dto) {

		Criteria<RoleDTO> criteria = new Criteria<RoleDTO>("1", "1", "1");

		if (dto.getName() != null && dto.getName().length() > 0) {
			if (criteria == null) {
				criteria = new Criteria<RoleDTO>("name", "=", dto.getName());
			} else {
				criteria.and(new Criteria<RoleDTO>("name", "=", dto.getName()));
			}
		}

		if (dto.getDescription() != null && dto.getDescription().length() > 0) {
			if (criteria == null) {
				criteria = new Criteria<RoleDTO>("description", "=", dto.getDescription());
			} else {
				criteria.and(new Criteria<RoleDTO>("description", "=", dto.getDescription()));
			}
		}

		return criteria;
	}
}
