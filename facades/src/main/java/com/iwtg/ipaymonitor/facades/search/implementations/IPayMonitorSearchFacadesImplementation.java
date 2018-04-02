package com.iwtg.ipaymonitor.facades.search.implementations;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Transaction;
import com.iwtg.ipaymonitor.facades.converters.search.SearchResultConverter;
import com.iwtg.ipaymonitor.facades.datatypes.search.DataSearchTransaction;
import com.iwtg.ipaymonitor.facades.datatypes.search.DataTransactionSearchResult;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.facades.search.interfaces.IPayMonitorSearchFacades;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;
import com.iwtg.ipaymonitor.servicelayer.context.IpayMonitorServicesContextLoader;
import com.iwtg.ipaymonitor.servicelayer.search.interfaces.IPayMonitorSearchService;
import com.iwtg.ipaymonitor.servicelayer.search.implementations.IPayMonitorSearchServiceImplementation;

public class IPayMonitorSearchFacadesImplementation implements IPayMonitorSearchFacades{

	private static final String SEARCH_ERROR = "Errror during the search, please try again or contact support team";

	SearchResultConverter searchResultConverter = new SearchResultConverter();

	IPayMonitorSearchService searchServices = (IPayMonitorSearchServiceImplementation) IpayMonitorServicesContextLoader
			.contextLoader().getBean("searchServices");

	
	public List<DataTransactionSearchResult> searchTransactions(final DataSearchTransaction dataSearchTransactionParameter)
			throws IPayMonitorException {
		try {
			List<Transaction> resultList = searchServices.searchTransactions(createSearchParameter(dataSearchTransactionParameter));
			return searchResultConverter.convertAll(resultList);
		}catch(Exception e) {
			throw new IPayMonitorException(SEARCH_ERROR);
		}
	}
	
	private DataSearchTransactionParameter createSearchParameter(final DataSearchTransaction dataSearchTransaction) {
		DataSearchTransactionParameter param = new DataSearchTransactionParameter();
		return param;
	}

}
