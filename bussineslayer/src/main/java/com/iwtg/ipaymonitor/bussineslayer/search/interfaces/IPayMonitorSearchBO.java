package com.iwtg.ipaymonitor.bussineslayer.search.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Transaction;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;

public interface IPayMonitorSearchBO {

	List<Transaction> searchTransactions(final DataSearchTransactionParameter createSearchParameter);

}
