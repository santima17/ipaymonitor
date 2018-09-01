package com.iwtg.ipaymonitor.datalayer.implementations;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.mysql.jdbc.Connection;

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
	
	public <T> T get(final Class<T> type, final String id) {
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
		if (StringUtils.isNotEmpty(searchParameter.getTransactionID())) {
			List<DataTransactionSearchResult> result = new ArrayList<DataTransactionSearchResult>();
			com.iwtg.ipaymonitor.datalayer.model.Transaction transaction = getTransaction(
					searchParameter.getTransactionID());
			if (transaction != null) {
				CierreLote cierre = getCierre(transaction.getCodigoRes());
				result.add(convertResult(transaction, cierre));
			}
			return result;
		} else {
			Session dbSession = DBHibernateUtil.getSessionFactoryMain();
			Query query = dbSession.createSQLQuery(makeSearchQuery(searchParameter));
			try {
				xlsExecute(makeXLSSearchQuery(searchParameter));
			}catch(Exception e) {
				System.out.println("ERROR EN XLS" + e.getMessage());	
			}
			List<Object[]> resultList = query.list();
			return convertResult(resultList);
		}
	}
	
	
	private void  xlsExecute(String query) throws Exception {
		
		File file = new File("/var/www/html/ipay/consulta.xls");
    	
		if(file.delete()){
			System.out.println(file.getName() + " is deleted!");
		}else{
			System.out.println("Delete operation is failed.");
		}
		
	    String driverName = "com.mysql.jdbc.Drive";
	    String url = "jdbc:mysql://localhost:3306/web_aa?zeroDateTimeBehavior=convertToNull";
	    String username = "root";
	    String password = "apagon23";
	    Connection connection = (Connection) DriverManager.getConnection(url, username, password);
	    
	    Statement stmt = connection.createStatement();
	    stmt.execute(query);
	    stmt.close();
	    connection.close();

	  }

	private List<DataTransactionSearchResult> convertResult(List<Object[]> resultList) {
		List<DataTransactionSearchResult> resultDataList = new ArrayList<DataTransactionSearchResult>();
		if(CollectionUtils.isNotEmpty(resultList)) {
			for(Object[] row :resultList) {
				DataTransactionSearchResult dataResult = new DataTransactionSearchResult();
				dataResult.setCodigoRes((String)row[0]);
				dataResult.setCanal((String)row[1]);
				dataResult.setPais((String)row[2]);
				dataResult.setMedioPago((String)row[3]);
				dataResult.setMoneda((String)row[4]);
				dataResult.setEstado((String)row[5]);
				dataResult.setFecha((Date)row[6]);
				dataResult.setNombre((String)row[7]);
				dataResult.setApellido((String)row[8]);
				dataResult.setMail((String)row[9]);
				dataResult.setDocumento((String)row[10]);
				dataResult.setMonto((String)row[11]);
				dataResult.setCodCard((String)row[12]);
				dataResult.setTarjeta((String)row[15]);
				dataResult.setAutorizacion((String)row[16]);
				dataResult.setCodcomercio((String)row[17]);
				resultDataList.add(dataResult);
			}
		}
		return resultDataList;
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

	private String makeSearchQuery(DataSearchTransactionParameter searchParameter) {
		String query = "select t.*, c.tarjeta, c.autorizacion, c.codcomercio from transaction t join cierre_lote c on c.codigoRes = t.codigoRes ";
		String where = "where ";
		int cont = 0;
		
		if (StringUtils.isNotEmpty(searchParameter.getDocument())) {
			if (cont == 1) {
				where = where + " and documento = '" + searchParameter.getDocument() + "'";
			} else {
				where = where + "documento = '" + searchParameter.getDocument() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getStatus().equals("all")) {
			if (cont == 1) {
				if (searchParameter.getStatus().equals("Autorizado")) {
					where = where + " and estado like '%000%'";
				} else if (searchParameter.getStatus().equals("Cancelado")) {
					where = where + " and estado = 'C'";
				} else if (searchParameter.getStatus().equals("Devuelto")) {
					where = where + " and estado = 'D'";
				} else {
					where = where
							+ " and (estado like '%001%' OR estado like '%010%' OR estado like '%011%' OR estado like '%100%' OR estado like '%110%' OR estado like '%111%')";
				}
			} else if (searchParameter.getStatus().equals("Autorizado")) {
				where = where + " estado like '%000%'";
			} else if (searchParameter.getStatus().equals("Cancelado")) {
				where = where + "estado = 'C'";
			} else if (searchParameter.getStatus().equals("Devuelto")) {
				where = where + "estado = 'D'";
			} else {
				where = where
						+ " (estado like '%001%' OR estado like '%010%' OR estado like '%011%' OR estado like '%100%' OR estado like '%110%' OR estado like '%111%')";
			}

			cont = 1;
		}
		if (!searchParameter.getCountry().equals("all")) {
			

			
			if(searchParameter.getCountry().contains("#")) {
				String countryParameter = StringUtils.EMPTY;
				String[] list = searchParameter.getCountry().split("#");
				for(int i = 0; i < list.length; i++) {
					countryParameter += list[i];
					if(i < list.length -1) {
						countryParameter += "' or pais = '"; 
					}
				}
				searchParameter.setCountry(countryParameter);
			}
			
			if (cont == 1) {
				where = where + " and ( pais = '" + searchParameter.getCountry() + "' )";
			} else {
				where = where + " ( pais = '" + searchParameter.getCountry() + "' )";
			}
			cont = 1;
			
		}
		if (!searchParameter.getChannel().equals("all")) {
			
			if(searchParameter.getChannel().contains("#")) {
				String channelParameter = StringUtils.EMPTY;
				String[] list = searchParameter.getChannel().split("#");
				
				for(int i = 0; i < list.length; i++) {
					channelParameter += list[i];
					if(i < list.length -1) {
						channelParameter += "' or canal = '"; 
					}
				}
				searchParameter.setChannel(channelParameter);
			}
			
			if (cont == 1) {
				where = where + " and ( canal = '" + searchParameter.getChannel() + "' )";
			} else {
				where = where + " ( canal = '" + searchParameter.getChannel() + "' )";
			}
			cont = 1;
		}
		if (!searchParameter.getCurrency().equals("all")) {
			if (cont == 1) {
				where = where + " and moneda = '" + searchParameter.getCurrency() + "'";
			} else {
				where = where + "moneda = '" + searchParameter.getCurrency() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getCardBrand().equals("all")) {
			
			if(searchParameter.getCardBrand().contains("#")) {
				String cardParameter = StringUtils.EMPTY;
				String[] list = searchParameter.getCardBrand().split("#");
				
				for(int i = 0; i < list.length; i++) {
					cardParameter += list[i];
					if(i < list.length -1) {
						cardParameter += "' or medioPago = '"; 
					}
				}
				searchParameter.setCardBrand(cardParameter);
			}
			
			if (cont == 1) {
				where = where + " and  ( medioPago = '" + searchParameter.getCardBrand() + "' )";
			} else {
				where = where + " ( medioPago = '" + searchParameter.getCardBrand() + "' )";
			}
			cont = 1;
		}

		SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat formatterTo = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

		if (cont == 1) {
			where = where + " and (t.fecha >= '" + formatterFrom.format(searchParameter.getDateFrom()) + "' and t.fecha <= '"
					+ formatterTo.format(searchParameter.getDateTo()) + "')";
		} else {
			where = where + "(t.fecha >= '" + formatterFrom.format(searchParameter.getDateFrom()) + "' and t.fecha <= '"
					+ formatterTo.format(searchParameter.getDateTo()) + "')";
		}

		System.out.println("QUERY" + query + where);
		return query + where;
	}
	
	private String makeXLSSearchQuery(DataSearchTransactionParameter searchParameter) {
		
		String query = "select t.*, c.tarjeta, c.autorizacion, c.codcomercio INTO OUTFILE '/var/www/html/ipay/consulta.xls' from transaction t join cierre_lote c on c.codigoRes = t.codigoRes ";
		String where = "where ";
		int cont = 0;
		
		if (StringUtils.isNotEmpty(searchParameter.getDocument())) {
			if (cont == 1) {
				where = where + " and documento = '" + searchParameter.getDocument() + "'";
			} else {
				where = where + "documento = '" + searchParameter.getDocument() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getStatus().equals("all")) {
			if (cont == 1) {
				if (searchParameter.getStatus().equals("Autorizado")) {
					where = where + " and estado like '%000%'";
				} else if (searchParameter.getStatus().equals("Cancelado")) {
					where = where + " and estado = 'C'";
				} else if (searchParameter.getStatus().equals("Devuelto")) {
					where = where + " and estado = 'D'";
				} else {
					where = where
							+ " and (estado like '%001%' OR estado like '%010%' OR estado like '%011%' OR estado like '%100%' OR estado like '%110%' OR estado like '%111%')";
				}
			} else if (searchParameter.getStatus().equals("Autorizado")) {
				where = where + " estado like '%000%'";
			} else if (searchParameter.getStatus().equals("Cancelado")) {
				where = where + "estado = 'C'";
			} else if (searchParameter.getStatus().equals("Devuelto")) {
				where = where + "estado = 'D'";
			} else {
				where = where
						+ " (estado like '%001%' OR estado like '%010%' OR estado like '%011%' OR estado like '%100%' OR estado like '%110%' OR estado like '%111%')";
			}

			cont = 1;
		}
		if (!searchParameter.getCountry().equals("all")) {
			if (cont == 1) {
				where = where + " and pais = '" + searchParameter.getCountry() + "'";
			} else {
				where = where + "pais = '" + searchParameter.getCountry() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getChannel().equals("all")) {
			if (cont == 1) {
				where = where + " and canal = '" + searchParameter.getChannel() + "'";
			} else {
				where = where + "canal = '" + searchParameter.getChannel() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getCurrency().equals("all")) {
			if (cont == 1) {
				where = where + " and moneda = '" + searchParameter.getCurrency() + "'";
			} else {
				where = where + "moneda = '" + searchParameter.getCurrency() + "'";
			}
			cont = 1;
		}
		if (!searchParameter.getCardBrand().equals("all")) {
			if (cont == 1) {
				where = where + " and medioPago = '" + searchParameter.getCardBrand() + "'";
			} else {
				where = where + "medioPago = '" + searchParameter.getCardBrand() + "'";
			}
			cont = 1;
		}

		SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat formatterTo = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

		if (cont == 1) {
			where = where + " and (t.fecha >= '" + formatterFrom.format(searchParameter.getDateFrom()) + "' and t.fecha <= '"
					+ formatterTo.format(searchParameter.getDateTo()) + "')";
		} else {
			where = where + "(t.fecha >= '" + formatterFrom.format(searchParameter.getDateFrom()) + "' and t.fecha <= '"
					+ formatterTo.format(searchParameter.getDateTo()) + "')";
		}

		System.out.println("QUERY" + query + where);
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
