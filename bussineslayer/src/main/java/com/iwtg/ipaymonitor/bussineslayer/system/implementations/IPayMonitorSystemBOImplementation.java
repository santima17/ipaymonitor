package com.iwtg.ipaymonitor.bussineslayer.system.implementations;

import java.util.List;

import com.iwtg.ipaymonitor.bussineslayer.system.interfaces.IPayMonitorSystemBO;
import com.iwtg.ipaymonitor.datalayer.context.IpayMonitorDAOContextLoader;
import com.iwtg.ipaymonitor.datalayer.implementations.IPayMonitorMySQLDAOImplementation;
import com.iwtg.ipaymonitor.datalayer.interfaces.IPayMonitorMySQLDAO;
import com.iwtg.ipaymonitor.datalayer.model.Canal;
import com.iwtg.ipaymonitor.datalayer.model.Mediopago;
import com.iwtg.ipaymonitor.datalayer.model.Pais;

public class IPayMonitorSystemBOImplementation implements IPayMonitorSystemBO{
	
	IPayMonitorMySQLDAO daoServices = (IPayMonitorMySQLDAOImplementation) IpayMonitorDAOContextLoader.contextLoader()
			.getBean("daoServices");

	public List<Pais> getAllCountries() {
		return daoServices.getAll(Pais.class);
	}

	public List<Mediopago> getAllCardBrands() {
		return daoServices.getAll(Mediopago.class);
	}

	public List<Canal> getAllChannels() {
		return daoServices.getAll(Canal.class);
	}

}
