package com.iwtg.ipaymonitor.servicelayer.system.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Canal;
import com.iwtg.ipaymonitor.datalayer.model.Mediopago;
import com.iwtg.ipaymonitor.datalayer.model.Pais;




public interface IPayMonitorSystemService {

	List<Pais> getAllCountries();

	List<Mediopago> getAllCardBrands();

	List<Canal> getAllChannels();

}
