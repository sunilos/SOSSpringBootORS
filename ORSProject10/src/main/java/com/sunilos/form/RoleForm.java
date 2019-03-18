package com.sunilos.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.sunilos.dto.BaseDTO;
import com.sunilos.dto.RoleDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class RoleForm extends BaseForm {

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BaseDTO getDto() {

		RoleDTO dto = new RoleDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setDescription(description);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {

		if (bDto == null) {
			return;
		}

		RoleDTO dto = (RoleDTO) bDto;

		id = dto.getId();
		name = dto.getName();
		description = dto.getDescription();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}

}