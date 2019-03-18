package com.sunilos.ctl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.dao.Criteria;
import com.sunilos.dao.RoleJpaRespositoryInt;
import com.sunilos.dto.RoleDTO;
import com.sunilos.form.RoleForm;

@RestController
@RequestMapping(value = "RoleJPA")
public class RoleJPACtl {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@Autowired
	private RoleJpaRespositoryInt dao;

	@GetMapping
	public ORSResponse get() {
		ORSResponse res = new ORSResponse(true);
		res.addData("I am okay");
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable Long id) {

		ORSResponse res = new ORSResponse(true);

		Optional<RoleDTO> dtoo = dao.findById(id);

		RoleDTO dto = dtoo.get();

		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		ORSResponse res = new ORSResponse(true);
		Optional<RoleDTO> dtoo = dao.findById(id);
		RoleDTO dto = dtoo.get();
		dao.delete(dto);
		res.addData(dto);
		return res;
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody RoleForm form) {

		// Calculate next page number
		String operation = form.getOperation();
		int pageNo = form.getPageNo();

		if ("Next".equals(operation)) {
			pageNo++;
		} else if ("Previous".equals(operation)) {
			pageNo--;
		}

		pageNo = (pageNo < 1) ? 0 : pageNo;

		form.setPageNo(pageNo);

		RoleDTO dto = (RoleDTO) form.getDto();

		ORSResponse res = new ORSResponse(true);

		Criteria<RoleDTO> criteria = new Criteria<RoleDTO>("name", ":", "Admin");

		// Page<RoleDTO> page = dao.findAll(PageRequest.of(pageNo, 5));
		Page<RoleDTO> page = dao.findAll(criteria, PageRequest.of(pageNo, 5));

		res.addData(page.getContent());
		res.addResult("pageNo", pageNo);
		res.addResult("pages", page.getTotalPages());
		res.addResult("count", page.getTotalElements());

		return res;
	}

	@PostMapping("/save")
	public ORSResponse save(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {

		ORSResponse res = valiate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		RoleDTO existDTO = dao.findByName(form.getName());

		System.out.println("Saving" + existDTO);

		if (existDTO != null && existDTO.getId() != form.getId()) {
			res.setSuccess(false);
			res.addMessage("Role name already exist");
		}

		try {
			RoleDTO dto = (RoleDTO) form.getDto();
			dao.save(dto);
			res.addData(dto.getId());
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Gets input error messages and put into REST response
	 * 
	 * @param bindingResult
	 * @return
	 */
	public ORSResponse valiate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse(true);
		if (bindingResult.hasErrors()) {
			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();
			// Lambda expression Java 8 feature
			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputErrors(errors);
		}
		return res;

	}

}
