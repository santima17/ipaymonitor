package com.iwtg.ipaymonitor.facades.search.implementations;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Transaction;
import com.iwtg.ipaymonitor.facades.datatypes.search.DataSearchTransaction;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.facades.search.interfaces.IPayMonitorSearchFacades;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionSearchResult;
import com.iwtg.ipaymonitor.servicelayer.context.IpayMonitorServicesContextLoader;
import com.iwtg.ipaymonitor.servicelayer.search.interfaces.IPayMonitorSearchService;
import com.iwtg.ipaymonitor.servicelayer.search.implementations.IPayMonitorSearchServiceImplementation;

public class IPayMonitorSearchFacadesImplementation implements IPayMonitorSearchFacades{

	private static final String SEARCH_ERROR = "Errror during the search, please try again or contact support team";

	IPayMonitorSearchService searchServices = (IPayMonitorSearchServiceImplementation) IpayMonitorServicesContextLoader
			.contextLoader().getBean("searchServices");

	
	public List<DataTransactionSearchResult> searchTransactions(final DataSearchTransaction dataSearchTransactionParameter)
			throws IPayMonitorException {
		try {
			return searchServices.searchTransactions(createSearchParameter(dataSearchTransactionParameter));
		}catch(Exception e) {
			throw new IPayMonitorException(SEARCH_ERROR);
		}
	}
	
	private DataSearchTransactionParameter createSearchParameter(final DataSearchTransaction dataSearchTransaction) {
		DataSearchTransactionParameter param = new DataSearchTransactionParameter();
		param.setBank(dataSearchTransaction.getBank());
		param.setCardBrand(dataSearchTransaction.getCardBrand());
		param.setChannel(dataSearchTransaction.getChannel());
		param.setCountry(dataSearchTransaction.getCountry());
		param.setCurrency(dataSearchTransaction.getCurrency());
		param.setDateFrom(dataSearchTransaction.getDateFrom());
		param.setDateTo(dataSearchTransaction.getDateTo());
		param.setDocument(dataSearchTransaction.getDocument());
		param.setStatus(dataSearchTransaction.getStatus());
		param.setTransactionID(dataSearchTransaction.getTransactionID());
		return param;
	}

}
