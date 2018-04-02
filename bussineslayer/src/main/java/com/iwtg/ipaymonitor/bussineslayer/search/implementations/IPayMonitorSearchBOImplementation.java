package com.iwtg.ipaymonitor.bussineslayer.search.implementations;

import java.util.List;

import com.iwtg.ipaymonitor.bussineslayer.search.interfaces.IPayMonitorSearchBO;
import com.iwtg.ipaymonitor.datalayer.context.IpayMonitorDAOContextLoader;
import com.iwtg.ipaymonitor.datalayer.implementations.IPayMonitorMySQLDAOImplementation;
import com.iwtg.ipaymonitor.datalayer.interfaces.IPayMonitorMySQLDAO;
import com.iwtg.ipaymonitor.datalayer.model.Transaction;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;

public class IPayMonitorSearchBOImplementation implements IPayMonitorSearchBO{
	
	IPayMonitorMySQLDAO daoServices = (IPayMonitorMySQLDAOImplementation) IpayMonitorDAOContextLoader.contextLoader()
			.getBean("daoServices");

	public List<Transaction> searchTransactions(final DataSearchTransactionParameter createSearchParameter) {
		return daoServices.searchTransactions(createSearchParameter);
	}

}
