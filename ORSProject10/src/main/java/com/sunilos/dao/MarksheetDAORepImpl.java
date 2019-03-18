package com.sunilos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.MarksheetDTO;
import com.sunilos.dto.StudentDTO;

@Repository
public class MarksheetDAORepImpl extends BaseDAORepImpl<MarksheetDTO, MarksheetJpaRespositoryInt>
		implements MarksheetDAOInt {

	@Autowired
	StudentDAOInt studentDao = null;

	@Override
	public MarksheetDTO findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public MarksheetDTO findByRollNo(String rollNo) {
		return repository.findByRollNo(rollNo);
	}

	@Override
	public List<MarksheetDTO> getMeritList() {
		return repository.getMeritList();
	}

	@Override
	protected void populate(MarksheetDTO dto) {
		StudentDTO studentDto = studentDao.findByPK(dto.getStudentId());
		dto.setName(studentDto.getFirstName() + " " + studentDto.getLastName());
	}

	@Override
	protected Criteria<MarksheetDTO> getCriteria(MarksheetDTO dto) {

		Criteria<MarksheetDTO> criteria = null;

		if (dto.getName() != null && dto.getName().length() > 0) {
			if (criteria == null) {
				criteria = new Criteria<>("name", "=", dto.getName());
			} else {
				criteria.and(new Criteria<>("name", "=", dto.getName()));
			}
		}

		return criteria;
	}

}
