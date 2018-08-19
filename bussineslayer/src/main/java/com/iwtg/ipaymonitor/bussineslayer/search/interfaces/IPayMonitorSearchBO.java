package com.iwtg.ipaymonitor.bussineslayer.search.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Audit;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionSearchResult;

public interface IPayMonitorSearchBO {

	List<DataTransactionSearchResult> searchTransactions(final DataSearchTransactionParameter createSearchParameter);
	List<Audit> auditByTransaction(final String idTransaction);

}
