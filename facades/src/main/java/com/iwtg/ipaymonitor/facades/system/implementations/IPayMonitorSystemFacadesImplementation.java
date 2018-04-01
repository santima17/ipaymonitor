package com.iwtg.ipaymonitor.facades.system.implementations;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.iwtg.ipaymonitor.datalayer.model.Canal;
import com.iwtg.ipaymonitor.datalayer.model.Mediopago;
import com.iwtg.ipaymonitor.datalayer.model.Pais;
import com.iwtg.ipaymonitor.facades.converters.system.SystemItemConverter;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataCardBrand;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataChannel;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataCountry;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.facades.system.interfaces.IPayMonitorSystemFacades;
import com.iwtg.ipaymonitor.servicelayer.context.IpayMonitorServicesContextLoader;
import com.iwtg.ipaymonitor.servicelayer.system.implementations.IPayMonitorSystemServiceImplementation;
import com.iwtg.ipaymonitor.servicelayer.system.interfaces.IPayMonitorSystemService;


public class IPayMonitorSystemFacadesImplementation implements IPayMonitorSystemFacades{
	
	SystemItemConverter systemItemConverter = new SystemItemConverter();
	
	IPayMonitorSystemService systemServices = (IPayMonitorSystemServiceImplementation) IpayMonitorServicesContextLoader
			.contextLoader().getBean("systemServices");


	public List<DataCountry> getAllCountries() throws IPayMonitorException {
		List<Pais> resultList = systemServices.getAllCountries();
		if (CollectionUtils.isNotEmpty(resultList)) {
			return systemItemConverter.convertAllCountries(resultList);
		} else {
			throw new IPayMonitorException("Result list is empty");
		}
	}

	public List<DataCardBrand> getAllCardBrands() throws IPayMonitorException {
		List<Mediopago> resultList = systemServices.getAllCardBrands();
		if (CollectionUtils.isNotEmpty(resultList)) {
			return systemItemConverter.convertAllCardBrands(resultList);
		} else {
			throw new IPayMonitorException("Result list is empty");
		}
	}

	public List<DataChannel> getAllChannels() throws IPayMonitorException {
		List<Canal> resultList = systemServices.getAllChannels();
		if (CollectionUtils.isNotEmpty(resultList)) {
			return systemItemConverter.convertAllChannels(resultList);
		} else {
			throw new IPayMonitorException("Result list is empty");
		}
	}

	
}
