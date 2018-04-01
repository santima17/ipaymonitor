package com.iwtg.ipaymonitor.monitor.controllers.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.iwtg.ipaymonitor.facades.datatypes.user.DataActiveSystemItem;
import com.iwtg.ipaymonitor.facades.datatypes.user.DataUser;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.facades.users.interfaces.IPayMonitorUserFacades;
import com.iwtg.ipaymonitor.monitor.validators.user.UserValidator;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final String ERROR_ADD_COUNTRY = "Error during add country, try again";
	private static final String ERROR_ADD_CARD = "Error during add cardbrand, try again";
	private static final String ERROR_ADD_CHANNEL = "Error during add channel, try again";
	
	private static final String ERROR_REMOVE_COUNTRY = "Error during delete country, try again";
	private static final String ERROR_REMOVE_CARD = "Error during delete cardbrand, try again";
	private static final String ERROR_REMOVE_CHANNEL = "Error during delete channel, try again";
	
	private static final String USER_ALREADY_EXIST = " already exist";
	private static final String USER_ID_IS_REQUIRED = "user id is required";
	private static final String USER_NOT_MODIFIED = "user not modified";
	private static final String USER_MODIFIED = "user modified";
	
	@Resource(name = "userFacades")
	IPayMonitorUserFacades userFacades;

//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new UserValidator());
//	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllUsers() {
		try {
			ResponseEntity<List<DataUser>> response = new ResponseEntity(userFacades.getAllUsers(), HttpStatus.OK);
			return response;
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getUser(@PathVariable Integer id) {
		try {
			ResponseEntity<DataUser> response = new ResponseEntity(userFacades.getUser(id), HttpStatus.OK);
			return response;
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity insertUser(@RequestBody @Valid DataUser user, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ResponseEntity<List>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (!userFacades.userExist(user)) {
				int userID = userFacades.saveUser(user);
				return new ResponseEntity<String>("newUserID:" + userID, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>(user.getUser() + USER_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
			}
		}

	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity modifyUser(@RequestBody @Valid DataUser user, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ResponseEntity<List>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (user.getId() != null) {
				boolean modified = userFacades.updateUser(user);
				if (modified) {
					return new ResponseEntity<String>(USER_MODIFIED, HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(USER_NOT_MODIFIED, HttpStatus.NOT_MODIFIED);
				}
			} else {
				return new ResponseEntity<String>(USER_ID_IS_REQUIRED, HttpStatus.BAD_REQUEST);
			}
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteUser(@PathVariable Integer id) {
		try {
			boolean modified = userFacades.deleteUser(id);
			if (modified) {
				return new ResponseEntity<String>(USER_MODIFIED, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(USER_NOT_MODIFIED, HttpStatus.NOT_MODIFIED);
			}
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/addCountry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addCountryForUser(@RequestBody DataActiveSystemItem item) {
		if (userFacades.addCountryForUser(item.getUserID(), item.getItemID())) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(ERROR_ADD_COUNTRY, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/addChannel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addChannelForUser(@RequestBody DataActiveSystemItem item) {
		if (userFacades.addChannelForUser(item.getUserID(), item.getItemID())) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(ERROR_ADD_CHANNEL, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/addCard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addCardbrandForUser(@RequestBody DataActiveSystemItem item) {
		if (userFacades.addCardbrandForUser(item.getUserID(), item.getItemID())) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(ERROR_ADD_CARD, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/removeCountry", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity removeCountryForUser(@RequestBody DataActiveSystemItem item) {
		if (userFacades.removeCountryForUser(item.getUserID(), item.getItemID())) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(ERROR_REMOVE_COUNTRY, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/removeChannel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity removeChannelForUser(@RequestBody DataActiveSystemItem item) {
		if (userFacades.removeChannelForUser(item.getUserID(), item.getItemID())) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(ERROR_REMOVE_CHANNEL, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/removeCard", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity removeCardbrandForUser(@RequestBody DataActiveSystemItem item) {
		if (userFacades.removeCardbrandForUser(item.getUserID(), item.getItemID())) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(ERROR_REMOVE_CARD, HttpStatus.BAD_REQUEST);
		}
	}

}
