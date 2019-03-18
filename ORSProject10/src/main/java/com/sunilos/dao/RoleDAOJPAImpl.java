package com.sunilos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.dto.RoleDTO;

//@Repository
public class RoleDAOJPAImpl extends BaseDAOJPAImpl<RoleDTO> implements RoleDAOInt {


	@Override
	public RoleDTO findByPK(long pk) {
		System.out.println("JPA findbypk");
		return entityManager.find(RoleDTO.class, pk);
	}

	@Override
	public RoleDTO findByName(String roleName) {

		String hql = "FROM RoleDTO where name like '" + roleName + "'";
		List<RoleDTO> list = (List<RoleDTO>) entityManager.createQuery(hql).getResultList();

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RoleDTO> cq = cb.createQuery(RoleDTO.class);
		Root<RoleDTO> from = cq.from(RoleDTO.class);
		cq.select(from);

		/*
		 * ParameterExpression<String> p = cb.parameter(String.class);
		 * cq.select(from).where(cb.equal(from.get("name"), p));
		 * 
		 * TypedQuery<RoleDTO> query = entityManager.createQuery(cq);
		 * query.setParameter(p, roleName);
		 */

		TypedQuery<RoleDTO> query = entityManager.createQuery(cq);
		List<RoleDTO> list = query.getResultList();

		return list;
	}

	@Override
	public List search(RoleDTO dto) {
		String hql = "FROM RoleDTO ";
		return (List) entityManager.createQuery(hql).getResultList();
	}

}
