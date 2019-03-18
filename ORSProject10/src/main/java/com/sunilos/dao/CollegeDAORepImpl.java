package com.sunilos.dao;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.RoleDTO;

@Repository
public class CollegeDAORepImpl extends BaseDAORepImpl<CollegeDTO, CollegeJpaRespositoryInt> implements CollegeDAOInt {

	@Override
	public CollegeDTO findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	protected Criteria<CollegeDTO> getCriteria(CollegeDTO dto) {

		Criteria<CollegeDTO> criteria = null;

		if (dto.getName() != null && dto.getName().length() > 0) {
			if (criteria == null) {
				criteria = new Criteria<>("name", "=", dto.getName());
			} else {
				criteria.and(new Criteria<>("name", "=", dto.getName()));
			}
		}

		if (dto.getCity() != null && dto.getCity().length() > 0) {
			if (criteria == null) {
				criteria = new Criteria<>("city", "=", dto.getCity());
			} else {
				criteria.and(new Criteria<>("city", "=", dto.getCity()));
			}
		}

		return criteria;
	}


}
