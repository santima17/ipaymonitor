package com.iwtg.ipaymonitor.monitor.controllers.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iwtg.ipaymonitor.facades.datatypes.system.DataCardBrand;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataChannel;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataCountry;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.facades.system.interfaces.IPayMonitorSystemFacades;
import com.iwtg.ipaymonitor.monitor.validators.user.UserValidator;

@RestController
@RequestMapping("/system")
public class SystemController {
	
	@Resource(name = "systemFacades")
	IPayMonitorSystemFacades systemFacades;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}
	
	@RequestMapping(value = "/countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllContruies() {
		try {
			ResponseEntity<List<DataCountry>> response = new ResponseEntity(systemFacades.getAllCountries(), HttpStatus.OK);
			return response;
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/cards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllCardBrands() {
		try {
			ResponseEntity<List<DataCardBrand>> response = new ResponseEntity(systemFacades.getAllCardBrands(), HttpStatus.OK);
			return response;
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/channels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllChannels() {
		try {
			ResponseEntity<List<DataChannel>> response = new ResponseEntity(systemFacades.getAllChannels(), HttpStatus.OK);
			return response;
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
