package com.iwtg.ipaymonitor.datalayer.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import com.iwtg.ipaymonitor.datalayer.context.DBHibernateUtil;
import com.iwtg.ipaymonitor.datalayer.interfaces.IPayMonitorMySQLDAO;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;

public class IPayMonitorMySQLDAOImplementation implements IPayMonitorMySQLDAO {

	public <T> Integer save(final T o) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		Transaction tx = dbSession.getTransaction();
		Object id = "";
		try {
			tx.begin();
			id = dbSession.save(o);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			return null;
		}
		if (id instanceof Integer) {
			return (Integer) id;
		} else {
			return 0;
		}
	}

	public boolean delete(final Object object) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		Transaction tx = dbSession.getTransaction();
		try {
			tx.begin();
			dbSession.delete(object);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			return false;
		}
	}

	public <T> T get(final Class<T> type, final Integer id) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		return dbSession.get(type, id);
	}

	public <T> T merge(final T o) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		return (T) dbSession.merge(o);
	}

	public <T> T refresh(final T o) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		dbSession.refresh(o);
		return o;
	}

	public <T> boolean saveOrUpdate(final T o) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		Transaction tx = dbSession.getTransaction();
		try {
			tx.begin();
			dbSession.update(o);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			return false;
		}
		return true;
	}

	public <T> List<T> getAll(final Class<T> type) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		final Criteria crit = dbSession.createCriteria(type);
		return crit.list();
	}

	public <T> List<T> getAllByExample(final Class<T> type, final T objectQuery) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		Example objectExample = Example.create(objectQuery);
		final Criteria crit = dbSession.createCriteria(type).add(objectExample);
		return crit.list();
	}

	public List<com.iwtg.ipaymonitor.datalayer.model.Transaction> searchTransactions(
			DataSearchTransactionParameter createSearchParameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
