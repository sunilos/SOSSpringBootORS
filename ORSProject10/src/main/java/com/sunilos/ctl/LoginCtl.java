package com.sunilos.ctl;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.dto.RoleDTO;
import com.sunilos.dto.UserDTO;
import com.sunilos.form.ChangePasswordForm;
import com.sunilos.form.ForgetPasswordForm;
import com.sunilos.form.LoginForm;
import com.sunilos.form.UserRegistrationForm;
import com.sunilos.service.RoleServiceInt;

/**
 * Contains login form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@RestController
@RequestMapping(value = "/auth")
public class LoginCtl extends BaseCtl<LoginForm, RoleDTO, RoleServiceInt> {

	@GetMapping(value = "login")
	public ORSResponse displayLogin() {
		ORSResponse res = new ORSResponse(true);
		res.addData(new LoginForm());
		return res;
	}

	@PostMapping(value = "login")
	public ORSResponse submitLogin(@RequestBody @Valid LoginForm form, BindingResult bindingResult) {

		ORSResponse res = valiate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}
		UserDTO dto = new UserDTO();
		// res.setSuccess(false);
		// res.addResult(ORSResponse.MESSAGE, "Invalid ID/PAssword");
		dto.setFirstName("SunilOS");
		dto.setLastName("InfoTech Pvt Ltd");
		res.addResult(ORSResponse.DATA, dto);
		return res;
	}

	@GetMapping("changepassword")
	public ORSResponse displayChangePassword() {
		ORSResponse res = new ORSResponse(true);
		res.addData(new ChangePasswordForm());
		return res;
	}

	@PostMapping("changepassword")
	public ORSResponse submitChangePassword(@RequestBody @Valid ChangePasswordForm form, BindingResult bindingResult) {
		ORSResponse res = valiate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		} else {
			res.addMessage("Password is changed!");
			return res;
		}
	}

	@GetMapping("signup")
	public ORSResponse displaySignUp() {
		ORSResponse res = new ORSResponse(true);
		res.addData(new UserRegistrationForm());
		return res;
	}

	@PostMapping("signup")
	public ORSResponse submitSignUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {
		ORSResponse res = valiate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}
		res.addMessage("User registration is done");
		return res;
	}

	/**
	 * Display forgot password form
	 * 
	 * @return
	 */
	@GetMapping("forgotPassword")
	public ORSResponse displayForgotPassword() {
		ORSResponse res = new ORSResponse(true);
		res.addData(new ForgetPasswordForm());
		return res;
	}

	/**
	 * Submit forgot password form
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("forgotPassword")
	public ORSResponse submitForgotPassword(@RequestBody @Valid ForgetPasswordForm form, BindingResult bindingResult) {
		ORSResponse res = valiate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}
		res.addMessage("Pasword is sent to your registred email id");
		return res;
	}

}
