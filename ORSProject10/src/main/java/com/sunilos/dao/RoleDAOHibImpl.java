package com.sunilos.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sunilos.dto.RoleDTO;

/**
 * Hibernate implementation of Role DAO.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

//@Repository
public class RoleDAOHibImpl implements RoleDAOInt {

	SessionFactory sessionFactory = null;

	@Autowired
	public void setSessionFactory(EntityManagerFactory entityManagerFactory) {
		if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
	}

	private static Logger log = Logger.getLogger(RoleDAOHibImpl.class);

	public long add(RoleDTO dto) {
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	public void update(RoleDTO dto) {
		log.debug("Role Dao Update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("Role Dao Update End");

	}

	public void delete(RoleDTO dto) {
		log.debug("Role Dao Delete Started");
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("Role Dao Delete End");

	}

	public RoleDTO findByName(String roleName) {
		log.debug("Role DAO Find by Name Started");
		RoleDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class)
				.add(Restrictions.eq("name", roleName)).list();

		System.out.println("List Size in Find By Name Dao" + list.size());

		if (list.size() == 1) {
			dto = (RoleDTO) list.get(0);
			System.out.println("DTO Not Null");
		}
		log.debug("Role DAO Find by Name Ended");
		return dto;

	}

	public RoleDTO findByPK(long pk) {
		log.debug("RoleDAO Find by PK Started");
		System.out.println("in find by pk dao start");
		RoleDTO dto = null;
		dto = (RoleDTO) sessionFactory.openSession().get(RoleDTO.class, pk);

		log.debug("RoleDAO Find by PK Ended");
		System.out.println("in find by pk dao end=" + dto.getId());
		return dto;
	}

	public List search(RoleDTO dto, int pageNo, int pageSize) throws DataAccessException {
		log.debug("DAO search Started");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class);

		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
			}
		}

		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}

		List list = criteria.list();

		log.debug("DAO search End");

		return list;

	}

	public List search(RoleDTO dto) throws DataAccessException {
		return search(dto, 0, 0);
	}

}
