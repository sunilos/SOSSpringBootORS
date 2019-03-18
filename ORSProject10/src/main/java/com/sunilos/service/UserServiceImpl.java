package com.sunilos.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sunilos.dao.UserDAOInt;
import com.sunilos.dto.RoleDTO;
import com.sunilos.dto.UserDTO;
import com.sunilos.exception.ApplicationException;
import com.sunilos.exception.DuplicateRecordException;
import com.sunilos.util.EmailBuilder;

public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserServiceInt {

	private JavaMailSender mailSender = null;

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public long registerUser(UserDTO dto) {

		long id = add(dto);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		MimeMessage msg = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg);
			helper.setTo(dto.getLogin());
			helper.setSubject("Registration is successful for ORS Project SUNRAYS Technologies.");
			// use the true flag to indicate the text included is HTML
			helper.setText(message, true);
			mailSender.send(msg);
		} catch (MessagingException e) {
			System.out.println("Mail Sending Failed");
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public UserDTO findByLogin(String login) {
		return baseDao.findByLogin(login);
	}

	@Override
	public boolean changePassword(Long id, String oldPassword, String newPassword) {
		UserDTO dto = findById(id);
		if (oldPassword.equals(dto.getPassword())) {
			dto.setPassword(newPassword);
			update(dto);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserDTO authenticate(UserDTO dto) {
		UserDTO dtoExist = findByLogin(dto.getLogin());
		if (dtoExist != null && dtoExist.getPassword().equals(dto.getPassword())) {
			// Set last login date
			dtoExist.setLastLogin(new Timestamp(new Date().getTime()));
			update(dtoExist);
			return dtoExist;
		}
		return dtoExist;
	}

	@Override
	public boolean lock(String login) {
		log.debug("Service lock Started");
		boolean flag = false;
		UserDTO dtoExist = null;
		dtoExist = findByLogin(login);
		if (dtoExist != null) {
			dtoExist.setLock(UserDTO.ACTIVE);
			update(dtoExist);
			flag = true;
		}
		log.debug("Service lock End");
		return flag;
	}

	@Override
	public boolean resetPassword(String login) throws ApplicationException {
		log.debug("Service resetPassword Started");
		boolean flag = false;
		UserDTO dtoExist = null;
		dtoExist = findByLogin(login);
		if (dtoExist != null) {
			String newPassword = String.valueOf(new Date().getTime()).substring(0, 4);
			dtoExist.setPassword(newPassword);
			update(dtoExist);

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", dtoExist.getLogin());
			map.put("password", dtoExist.getPassword());
			map.put("firstName", dtoExist.getFirstName());
			map.put("lastName", dtoExist.getLastName());
			String message = EmailBuilder.getForgetPasswordMessage(map);

			MimeMessage msg = mailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(msg, true);
				helper.setTo(dtoExist.getLogin());
				helper.setSubject("Password has been reset.");
				// use the true flag to indicate the text included is HTML
				helper.setText(message, true);
			} catch (MessagingException e) {
				System.out.println("Mail Sending Failed");
				e.printStackTrace();
			}
			mailSender.send(msg);

			flag = true;
		} else {
		}
		log.debug("Service restPassword End");
		return flag;
	}

	@Override
	public boolean forgetPassword(String login) throws ApplicationException {
		log.debug("Service forgetPassword Started");

		UserDTO dtoExist = findByLogin(login);

		if (dtoExist != null) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("firstName", dtoExist.getFirstName());
			map.put("lastName", dtoExist.getLastName());
			map.put("login", dtoExist.getLogin());
			map.put("password", dtoExist.getPassword());

			String message = EmailBuilder.getForgetPasswordMessage(map);

			MimeMessage msg = mailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg);
				helper.setTo(login);
				helper.setSubject("SunilOS ORS Password reset");
				// use the true flag to indicate the text included is HTML
				helper.setText(message, true);
				mailSender.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
				log.error("Error", e);
				return false;
			}
		} else {
			return false;
		}
		log.debug("Service forgetPassword End");
		return true;
	}

	@Override
	public RoleDTO getRole(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateAccess(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkDuplicate(UserDTO dto) {
		UserDTO dtoExist = findByLogin(dto.getLogin());
		if (dtoExist != null && dto.getId() != dtoExist.getId()) {
			throw new DuplicateRecordException("Login already exists");
		}
	}

}
