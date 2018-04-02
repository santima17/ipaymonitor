package com.iwtg.ipaymonitor.facades.search.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.facades.datatypes.search.DataSearchTransaction;
import com.iwtg.ipaymonitor.facades.datatypes.search.DataTransactionSearchResult;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;

public interface IPayMonitorSearchFacades {

	List<DataTransactionSearchResult> searchTransactions(final DataSearchTransaction dataSearchTransaction) throws IPayMonitorException;

}
