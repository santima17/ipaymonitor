package com.iwtg.ipaymonitor.facades.system.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.facades.datatypes.system.DataCardBrand;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataChannel;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataCountry;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;

public interface IPayMonitorSystemFacades {

	List<DataCountry> getAllCountries() throws IPayMonitorException;

	List<DataCardBrand> getAllCardBrands()throws IPayMonitorException;

	List<DataChannel> getAllChannels() throws IPayMonitorException;

}
