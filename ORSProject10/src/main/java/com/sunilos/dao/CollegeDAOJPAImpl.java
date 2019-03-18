package com.sunilos.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.CollegeDTO;

//@Repository
public class CollegeDAOJPAImpl extends BaseDAOJPAImpl<CollegeDTO> implements CollegeDAOInt {

	@Override
	public CollegeDTO findByPK(long pk) {
		System.out.println("JPA findbypk");
		return entityManager.find(CollegeDTO.class, pk);
	}

	@Override
	public CollegeDTO findByName(String name) {

		String hql = "FROM CollegeDTO where name like '" + name + "'";
		List<CollegeDTO> list = (List<CollegeDTO>) entityManager.createQuery(hql).getResultList();

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CollegeDTO> cq = cb.createQuery(CollegeDTO.class);
		Root<CollegeDTO> from = cq.from(CollegeDTO.class);
		cq.select(from);

		/*
		 * ParameterExpression<String> p = cb.parameter(String.class);
		 * cq.select(from).where(cb.equal(from.get("name"), p));
		 * 
		 * TypedQuery<CollegeDTO> query = entityManager.createQuery(cq);
		 * query.setParameter(p, roleName);
		 */

		TypedQuery<CollegeDTO> query = entityManager.createQuery(cq);
		List<CollegeDTO> list = query.getResultList();

		return list;
	}

	@Override
	public List search(CollegeDTO dto) {
		String hql = "FROM CollegeDTO ";
		return (List) entityManager.createQuery(hql).getResultList();
	}
}
