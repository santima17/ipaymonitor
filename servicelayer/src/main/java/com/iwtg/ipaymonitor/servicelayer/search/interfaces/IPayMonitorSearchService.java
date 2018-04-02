package com.iwtg.ipaymonitor.servicelayer.search.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Transaction;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;

public interface IPayMonitorSearchService {

	List<Transaction> searchTransactions(final DataSearchTransactionParameter createSearchParameter);

}
