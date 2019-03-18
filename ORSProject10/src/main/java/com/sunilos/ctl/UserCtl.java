package com.sunilos.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.sunilos.dto.RoleDTO;
import com.sunilos.dto.UserDTO;
import com.sunilos.form.UserForm;
import com.sunilos.service.RoleServiceInt;
import com.sunilos.service.UserServiceInt;

//@RestController
//@RequestMapping(value = "/User")
public class UserCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@Autowired
	private RoleServiceInt roleService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		List<RoleDTO> list = roleService.search(null);
		ORSResponse res = new ORSResponse(true, "", list);
		return res;
	}

	/*
	@PostMapping("/changepassword")
	public String submitChangePassword(Locale locale, HttpSession session,
			@ModelAttribute("form") @Valid ChangePasswordForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "ChangePassword";
		}

		// New password and confirm password must be same
		if (form.getNewPassword().equals(form.getConfirmPassword())) {

			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto = service.findByPK(dto.getId());

			// Old password must be valid
			if (dto.getPassword().equals(form.getOldPassword())) {
				// Change Password
				dto.setPassword(form.getNewPassword());
				service.update(dto);
				String msg = messageSource.getMessage("message.success", null, locale);
				model.addAttribute("success", msg);
			} else {
				model.addAttribute("error", "Old Password is not valid.");
			}
		} else {
			model.addAttribute("error", "New Password and Confirm Password does not match");
		}
		return "ChangePassword";
	}
	*/

}
