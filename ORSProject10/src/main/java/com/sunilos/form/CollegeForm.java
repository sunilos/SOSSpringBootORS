package com.sunilos.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sunilos.dto.BaseDTO;
import com.sunilos.dto.CollegeDTO;

/**
 * Contains College form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class CollegeForm extends BaseForm {

	@NotEmpty
	private String name;

	@NotEmpty
	private String address;

	@NotEmpty
	private String state;

	@NotEmpty
	private String city;

	@NotNull
	@Pattern(regexp = "\\d{10}")
	private String phoneNo;

	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Populate dto from form
	 */
	@Override
	public BaseDTO getDto() {
		CollegeDTO dto = new CollegeDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setAddress(address);
		dto.setCity(city);
		dto.setState(state);
		dto.setPhoneNo(phoneNo);
		return dto;
	}

	/**
	 * Populate from from dto
	 */
	@Override
	public void populate(BaseDTO bDto) {
		CollegeDTO dto = (CollegeDTO) bDto;
		id = dto.getId();
		name = dto.getName();
		address = dto.getAddress();
		city = dto.getCity();
		state = dto.getState();
		phoneNo = dto.getPhoneNo();
	}

}