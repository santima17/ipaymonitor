package com.iwtg.ipaymonitor.datalayer.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionSearchResult;

public interface IPayMonitorMySQLDAO {

	<T> Integer save(final T o);

	boolean delete(final Object object);

	<T> T get(final Class<T> type, final Integer id);
	
	<T> T get(final Class<T> type, final String id);

	<T> T merge(final T o);

	<T> T refresh(final T o);

	<T> boolean saveOrUpdate(final T o);

	<T> List<T> getAll(final Class<T> type);

	<T> List<T> getAllByExample(final Class<T> type, final T objectQuery);

	List<DataTransactionSearchResult> searchTransactions(DataSearchTransactionParameter createSearchParameter);

}
