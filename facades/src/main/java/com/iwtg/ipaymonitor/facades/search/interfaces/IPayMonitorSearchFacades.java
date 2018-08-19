package com.iwtg.ipaymonitor.facades.search.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.facades.datatypes.search.DataSearchTransaction;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionAudit;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionSearchResult;

public interface IPayMonitorSearchFacades {

	List<DataTransactionSearchResult> searchTransactions(final DataSearchTransaction dataSearchTransaction) throws IPayMonitorException;
	List<DataTransactionAudit> auditByTransaction(final String idTransaction) throws IPayMonitorException;

}
