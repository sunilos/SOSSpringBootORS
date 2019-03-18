package com.sunilos.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.StudentDTO;

//@Repository
public class StudentDAOJPAImpl extends BaseDAOJPAImpl<StudentDTO> implements StudentDAOInt {

	@Override
	public StudentDTO findByPK(long pk) {
		System.out.println("JPA findbypk");
		return entityManager.find(StudentDTO.class, pk);
	}

	@Override
	public StudentDTO findByEmail(String email) {

		String hql = "FROM StudentDTO where email like '" + email + "'";
		List<StudentDTO> list = (List<StudentDTO>) entityManager.createQuery(hql).getResultList();

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<StudentDTO> cq = cb.createQuery(StudentDTO.class);
		Root<StudentDTO> from = cq.from(StudentDTO.class);
		cq.select(from);

		/*
		 * ParameterExpression<String> p = cb.parameter(String.class);
		 * cq.select(from).where(cb.equal(from.get("name"), p));
		 * 
		 * TypedQuery<StudentDTO> query = entityManager.createQuery(cq);
		 * query.setParameter(p, roleName);
		 */

		TypedQuery<StudentDTO> query = entityManager.createQuery(cq);
		List<StudentDTO> list = query.getResultList();

		return list;
	}

	@Override
	public List search(StudentDTO dto) {
		String hql = "FROM StudentDTO ";
		return (List) entityManager.createQuery(hql).getResultList();
	}
}
