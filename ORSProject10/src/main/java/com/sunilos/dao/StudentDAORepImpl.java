package com.sunilos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.RoleDTO;
import com.sunilos.dto.StudentDTO;

@Repository
public class StudentDAORepImpl extends BaseDAORepImpl<StudentDTO, StudentJpaRespositoryInt> implements StudentDAOInt {

	@Autowired
	CollegeDAOInt collegeDao = null;

	@Override
	public StudentDTO findByEmail(String email) {
		return repository.findByEmail(email);
	}

	
	@Override
	protected void populate(StudentDTO dto) {
		CollegeDTO collegeDTO = collegeDao.findByPK(dto.getCollegeId());
		dto.setCollegeName(collegeDTO.getName());
	}

}
