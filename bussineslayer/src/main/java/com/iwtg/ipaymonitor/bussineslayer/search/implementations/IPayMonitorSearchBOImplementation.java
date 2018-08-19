package com.iwtg.ipaymonitor.bussineslayer.search.implementations;

import java.util.List;

import com.iwtg.ipaymonitor.bussineslayer.search.interfaces.IPayMonitorSearchBO;
import com.iwtg.ipaymonitor.datalayer.context.IpayMonitorDAOContextLoader;
import com.iwtg.ipaymonitor.datalayer.implementations.IPayMonitorMySQLDAOImplementation;
import com.iwtg.ipaymonitor.datalayer.interfaces.IPayMonitorMySQLDAO;
import com.iwtg.ipaymonitor.datalayer.model.Audit;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionSearchResult;

public class IPayMonitorSearchBOImplementation implements IPayMonitorSearchBO{
	
	IPayMonitorMySQLDAO daoServices = (IPayMonitorMySQLDAOImplementation) IpayMonitorDAOContextLoader.contextLoader()
			.getBean("daoServices");

	public List<DataTransactionSearchResult> searchTransactions(final DataSearchTransactionParameter createSearchParameter) {
		return daoServices.searchTransactions(createSearchParameter);
	}

	public List<Audit> auditByTransaction(String idTransaction) {
		Audit exampleAudit = new Audit();
		exampleAudit.setNroTransaccion(idTransaction);
		return daoServices.getAllByExample(Audit.class, exampleAudit);
		
	}
	
	

}
