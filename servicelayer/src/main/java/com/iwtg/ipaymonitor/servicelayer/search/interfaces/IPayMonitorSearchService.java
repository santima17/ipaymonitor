package com.iwtg.ipaymonitor.servicelayer.search.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Transaction;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionSearchResult;

public interface IPayMonitorSearchService {

	List<DataTransactionSearchResult> searchTransactions(final DataSearchTransactionParameter createSearchParameter);

}
