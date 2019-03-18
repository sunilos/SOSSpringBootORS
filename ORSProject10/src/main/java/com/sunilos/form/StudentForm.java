package com.sunilos.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sunilos.dto.BaseDTO;
import com.sunilos.dto.StudentDTO;
import com.sunilos.util.Util;

;

/**
 * Contains Student form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class StudentForm extends BaseForm {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String dob;

    @NotEmpty
    @Pattern(regexp = "\\d{10}", message = "{Pattern.form.phoneNo}")
    private String mobileNo;

    @NotEmpty
    @Email
    private String email;

    private String collegeName;

    @NotNull
    private Long collegeId;

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public BaseDTO getDto() {
        StudentDTO dto = new StudentDTO();
        dto.setId(id);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setDob(Util.getDate(dob));
        dto.setMobileNo(mobileNo);
        dto.setEmail(email);
        dto.setCollegeId(collegeId);
        return dto;
    }

    @Override
    public void populate(BaseDTO bDto) {
        StudentDTO dto = (StudentDTO) bDto;
        id = dto.getId();
        firstName = dto.getFirstName();
        lastName = dto.getLastName();
        dob = Util.getDate(dto.getDob());
        mobileNo = dto.getMobileNo();
        email = dto.getEmail();
        collegeId = dto.getCollegeId();

    }

}
