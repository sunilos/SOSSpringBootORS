package com.sunilos.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.RoleDTO;
import com.sunilos.dto.StudentDTO;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Repository
public interface StudentJpaRespositoryInt extends BaseJpaRespositoryInt<StudentDTO> {

	public StudentDTO findByEmail(String email);

}
