package com.iwtg.ipaymonitor.datalayer.implementations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import com.iwtg.ipaymonitor.datalayer.context.DBHibernateUtil;
import com.iwtg.ipaymonitor.datalayer.interfaces.IPayMonitorMySQLDAO;
import com.iwtg.ipaymonitor.datalayer.model.CierreLote;
import com.iwtg.ipaymonitor.generic.datatypes.DataSearchTransactionParameter;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionSearchResult;

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

	public List<DataTransactionSearchResult> searchTransactions(DataSearchTransactionParameter searchParameter) {
		if (searchParameter.getTransactionID() != null) {
			List<DataTransactionSearchResult> result = new ArrayList<DataTransactionSearchResult>();
			com.iwtg.ipaymonitor.datalayer.model.Transaction transaction = getTransaction(searchParameter.getTransactionID());
			if(transaction != null) {
				CierreLote cierre = getCierre(transaction.getCodigoRes());
				result.add(convertResult(transaction, cierre));
			}
			return result;
		} else {
			Session dbSession = DBHibernateUtil.getSessionFactoryMain();
			Query query = dbSession.createSQLQuery(construirQueryBusqueda(searchParameter, false));
			List<Object[]> resultList = query.list();
			return null;
		}
	}

	private DataTransactionSearchResult convertResult(com.iwtg.ipaymonitor.datalayer.model.Transaction transaction,
			CierreLote cierre) {
		DataTransactionSearchResult dataResult = new DataTransactionSearchResult();
		dataResult.setApellido(transaction.getApellido());
		dataResult.setAutorizacion(cierre.getAutorizacion());
		dataResult.setCanal(transaction.getCanal());
		dataResult.setCodCard(transaction.getCodCard());
		dataResult.setCodcomercio(cierre.getCodcomercio());
		dataResult.setCodigoRes(transaction.getCodigoRes());
		dataResult.setDocumento(transaction.getDocumento());
		dataResult.setEstado(transaction.getEstado());
		dataResult.setFecha(transaction.getFecha());
		dataResult.setMail(transaction.getMail());
		dataResult.setMedioPago(transaction.getMedioPago());
		dataResult.setMoneda(transaction.getMoneda());
		dataResult.setMonto(transaction.getMonto());
		dataResult.setNombre(transaction.getNombre());
		dataResult.setPais(transaction.getPais());
		dataResult.setTarjeta(cierre.getTarjeta());
		return dataResult;
	}

	private String construirQueryBusqueda(DataSearchTransactionParameter searchParameter, boolean isXLS) {
		String query = "SELECT t.*, b.* FROM transaction t JOIN batch_closure b ON t.reservationNumber = b.reservationNumber ";
		if (isXLS) {
			query = "SELECT t.*, b.* INTO OUTFILE '/opt/tomcat-latest/webapps/monitor/consulta.xls' FROM transaction t JOIN batch_closure b ON t.reservationNumber = b.reservationNumber ";
		}
		String where = "where ";
		int cont = 0;

		if (!searchParameter.getDocument().equals("")) {
			if (cont == 1) {
				where = where + " and creditCardNumber = '" + searchParameter.getDocument() + "'";
			} else {
				where = where + "creditCardNumber = '" + searchParameter.getDocument() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getDocument().equals("Todos")) {
			if (cont == 1) {
				if (searchParameter.getDocument().equals("Autorizado")) {
					where = where + " and transactionStatusCode like '%000%'";
				} else if (searchParameter.getDocument().equals("Cancelado")) {
					where = where + " and transactionStatusCode = '112'";
				} else if (searchParameter.getDocument().equals("Devuelto")) {
					where = where + " and transactionStatusCode = 'D'";
				} else if (searchParameter.getDocument().equals("Check. Manual")) {
					where = where + " and transactionStatusCode = 'CHK'";
				} else {
					where = where
							+ " and (transactionStatusCode like '%001%' OR transactionStatusCode like '%010%' OR transactionStatusCode like '%011%' OR transactionStatusCode like '%100%' OR transactionStatusCode like '%110%' OR transactionStatusCode like '%111%' OR transactionStatusCode like '%114%')";
				}
			} else if (searchParameter.getDocument().equals("Autorizado")) {
				where = where + " transactionStatusCode like '%000%'";
			} else if (searchParameter.getDocument().equals("Cancelado")) {
				where = where + "transactionStatusCode = '112'";
			} else if (searchParameter.getDocument().equals("Devuelto")) {
				where = where + "transactionStatusCode = 'D'";
			} else if (searchParameter.getDocument().equals("Check. Manual")) {
				where = where + "transactionStatusCode = 'CHK'";
			} else {
				where = where
						+ " (transactionStatusCode like '%001%' OR transactionStatusCode like '%010%' OR transactionStatusCode like '%011%' OR transactionStatusCode like '%100%' OR transactionStatusCode like '%110%' OR transactionStatusCode like '%111%' OR transactionStatusCode like '%114%')";
			}

			cont = 1;
		}

		if (!searchParameter.getBank().equals("Todos")) {
			if (cont == 1) {
				where = where + " and acquirerID = '" + searchParameter.getBank() + "'";
			} else {
				where = where + " acquirerID = '" + searchParameter.getBank() + "'";
			}
			cont = 1;
		}

		if (!searchParameter.getCountry().equals("Todos")) {
			if (cont == 1) {
				where = where + " and country = '" + searchParameter.getBank() + "'";
			} else {
				where = where + " country = '" + searchParameter.getBank() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getChannel().equals("Todos")) {
			if (cont == 1) {
				where = where + " and channel  = '" + searchParameter.getChannel() + "'";
			} else {
				where = where + " channel  = '" + searchParameter.getChannel() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getCurrency().equals("Todas")) {
			if (cont == 1) {
				where = where + " and currency = '" + searchParameter.getCurrency() + "'";
			} else {
				where = where + " currency = '" + searchParameter.getCurrency() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getCardBrand().equals("Todos")) {
			if (cont == 1) {
				where = where + " and creditCardBrand = '" + searchParameter.getCardBrand() + "'";
			} else {
				where = where + "creditCardBrand = '" + searchParameter.getCardBrand() + "'";
			}
			cont = 1;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		if (cont == 1) {
			where = where + " and (date >= '" + formatter.format(searchParameter.getDateFrom()) + "' and date <= '"
					+ formatter.format(searchParameter.getDateTo()) + "')";
		} else {
			where = where + "(date >= '" + formatter.format(searchParameter.getDateFrom()) + "' and date <= '"
					+ formatter.format(searchParameter.getDateTo()) + "')";
		}

		where = where + " and transactionStatusCode NOT like '%PPP%'  and transactionStatusCode <> '' ";

		return query + where;
	}

	public com.iwtg.ipaymonitor.datalayer.model.Transaction getTransaction(String transactionID) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		com.iwtg.ipaymonitor.datalayer.model.Transaction transaction = (com.iwtg.ipaymonitor.datalayer.model.Transaction) dbSession
				.createQuery("select T from Transaction T where T.codCard = :codCard")
				.setString("codCard", transactionID).uniqueResult();
		return transaction;
	}
	
	public CierreLote getCierre(String transactionIDLongNumber) {
		Session dbSession = DBHibernateUtil.getSessionFactoryMain();
		CierreLote cierre = (CierreLote) dbSession
				.createQuery("select C from CierreLote C where C.codigoRes = :codigoRes")
				.setString("codigoRes", transactionIDLongNumber).uniqueResult();
		return cierre;
	}

}
