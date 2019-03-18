package com.sunilos.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sunilos.dto.BaseDTO;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface BaseJpaRespositoryInt<T extends BaseDTO> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

}
