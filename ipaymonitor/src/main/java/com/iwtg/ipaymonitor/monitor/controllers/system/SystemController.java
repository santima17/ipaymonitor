package com.iwtg.ipaymonitor.monitor.controllers.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iwtg.ipaymonitor.facades.datatypes.system.DataCardBrand;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataChannel;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataCountry;
import com.iwtg.ipaymonitor.facades.datatypes.user.DataActiveSystemItem;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.facades.system.interfaces.IPayMonitorSystemFacades;
import com.iwtg.ipaymonitor.facades.users.interfaces.IPayMonitorUserFacades;
import com.iwtg.ipaymonitor.monitor.validators.user.UserValidator;

@RestController
@RequestMapping("/system")
public class SystemController {
	
	private static final String ERROR_ADD_COUNTRY = "Error during add country, try again";
	private static final String ERROR_ADD_CARD = "Error during add cardbrand, try again";
	private static final String ERROR_ADD_CHANNEL = "Error during add channel, try again";
	
	private static final String ERROR_REMOVE_COUNTRY = "Error during delete country, try again";
	private static final String ERROR_REMOVE_CARD = "Error during delete cardbrand, try again";
	private static final String ERROR_REMOVE_CHANNEL = "Error during delete channel, try again";
	
	@Resource(name = "systemFacades")
	IPayMonitorSystemFacades systemFacades;
	
	@Resource(name = "userFacades")
	IPayMonitorUserFacades userFacades;
	
	@RequestMapping(value = "/countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllCountries() {
		try {
			ResponseEntity<List<DataCountry>> response = new ResponseEntity(systemFacades.getAllCountries(), HttpStatus.OK);
			return response;
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/countries/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllCountriesByUser(@PathVariable Integer id) {
		try {
			ResponseEntity<List<DataCountry>> response = new ResponseEntity(systemFacades.getAllCountriesById(id), HttpStatus.OK);
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
	
	@RequestMapping(value = "/cards/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllCardBrandsByUser(@PathVariable Integer id) {
		try {
			ResponseEntity<List<DataCardBrand>> response = new ResponseEntity(systemFacades.getAllCardBrandsById(id), HttpStatus.OK);
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
	
	@RequestMapping(value = "/channels/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllChannelsByUser(@PathVariable Integer id) {
		try {
			ResponseEntity<List<DataChannel>> response = new ResponseEntity(systemFacades.getAllChannelsById(id), HttpStatus.OK);
			return response;
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
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
