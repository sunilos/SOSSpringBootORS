package com.sunilos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.UserDTO;

@Repository
public class UserDAORepImpl extends BaseDAORepImpl<UserDTO, UserJpaRespositoryInt>
		implements UserDAOInt {

	
	@Override
	public UserDTO findByLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public UserDTO findByLoginAndPassword(String login, String password) {
		return repository.findByLoginAndPassword(login, password);
	}

	@Override
	public List<UserDTO> findByMobileNo(String mobile) {
		return repository.findByMobileNo(mobile);
	}

	@Override
	protected Criteria<UserDTO> getCriteria(UserDTO dto) {

		Criteria<UserDTO> criteria = null;

		if (dto.getFirstName()!= null && dto.getFirstName().length() > 0) {
			if (criteria == null) {
				criteria = new Criteria<UserDTO>("name", "=", dto.getFirstName());
			} else {
				criteria.and(new Criteria<UserDTO>("name", "=", dto.getFirstName()));
			}
		}

		return criteria;
	}

}
