package com.iwtg.ipaymonitor.monitor.controllers.search;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iwtg.ipaymonitor.facades.datatypes.search.DataSearchTransaction;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.facades.search.interfaces.IPayMonitorSearchFacades;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionSearchResult;

@RestController
@RequestMapping("/search")
public class SearchController{
	
	@Resource(name = "searchFacades")
	IPayMonitorSearchFacades searchFacades;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity searchTransactions(@RequestBody DataSearchTransaction dataSearchTransaction, BindingResult result,
			HttpServletRequest request) {
		try {
			ResponseEntity<List<DataTransactionSearchResult>> response = new ResponseEntity(searchFacades.searchTransactions(dataSearchTransaction), HttpStatus.OK);
			return response;
		} catch (IPayMonitorException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
