package com.iwtg.ipaymonitor.servicelayer.system.implementations;

import java.util.List;

import com.iwtg.ipaymonitor.bussineslayer.context.IpayMonitorBussinesContextLoader;
import com.iwtg.ipaymonitor.bussineslayer.system.interfaces.IPayMonitorSystemBO;
import com.iwtg.ipaymonitor.bussineslayer.system.implementations.IPayMonitorSystemBOImplementation;
import com.iwtg.ipaymonitor.datalayer.model.Canal;
import com.iwtg.ipaymonitor.datalayer.model.Mediopago;
import com.iwtg.ipaymonitor.datalayer.model.Pais;
import com.iwtg.ipaymonitor.servicelayer.system.interfaces.IPayMonitorSystemService;

public class IPayMonitorSystemServiceImplementation implements IPayMonitorSystemService{
	
	IPayMonitorSystemBO systemBO = (IPayMonitorSystemBOImplementation) IpayMonitorBussinesContextLoader.contextLoader()
			.getBean("systemBO");

	public List<Pais> getAllCountries() {
		return systemBO.getAllCountries();
	}
	
	public List<Pais> getAllCountriesById(Integer id) {
		return systemBO.getAllCountriesById(id);
	}

	public List<Mediopago> getAllCardBrands() {
		return systemBO.getAllCardBrands();
	}
	
	public List<Mediopago> getAllCardBrandsById(Integer id) {
		return systemBO.getAllCardBrandsById(id);
	} 

	public List<Canal> getAllChannels() {
		return systemBO.getAllChannels();
	}

	public List<Canal> getAllChannelsById(Integer id) {
		return systemBO.getAllChannelsById(id);
	}



	

}
