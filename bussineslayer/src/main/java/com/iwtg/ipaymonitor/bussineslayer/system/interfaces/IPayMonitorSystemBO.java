package com.iwtg.ipaymonitor.bussineslayer.system.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Canal;
import com.iwtg.ipaymonitor.datalayer.model.Mediopago;
import com.iwtg.ipaymonitor.datalayer.model.Pais;

public interface IPayMonitorSystemBO {

	List<Pais> getAllCountries();

	List<Mediopago> getAllCardBrands();

	List<Canal> getAllChannels();

}
