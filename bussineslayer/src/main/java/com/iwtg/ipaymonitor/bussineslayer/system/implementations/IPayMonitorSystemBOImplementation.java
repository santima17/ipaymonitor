package com.iwtg.ipaymonitor.bussineslayer.system.implementations;

import java.util.ArrayList;
import java.util.List;

import com.iwtg.ipaymonitor.bussineslayer.system.interfaces.IPayMonitorSystemBO;
import com.iwtg.ipaymonitor.datalayer.context.IpayMonitorDAOContextLoader;
import com.iwtg.ipaymonitor.datalayer.implementations.IPayMonitorMySQLDAOImplementation;
import com.iwtg.ipaymonitor.datalayer.interfaces.IPayMonitorMySQLDAO;
import com.iwtg.ipaymonitor.datalayer.model.Canal;
import com.iwtg.ipaymonitor.datalayer.model.CanalHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.CanalHasUsuariosId;
import com.iwtg.ipaymonitor.datalayer.model.Mediopago;
import com.iwtg.ipaymonitor.datalayer.model.MediopagoHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.MediopagoHasUsuariosId;
import com.iwtg.ipaymonitor.datalayer.model.Pais;
import com.iwtg.ipaymonitor.datalayer.model.PaisHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.PaisHasUsuariosId;

public class IPayMonitorSystemBOImplementation implements IPayMonitorSystemBO{
	
	IPayMonitorMySQLDAO daoServices = (IPayMonitorMySQLDAOImplementation) IpayMonitorDAOContextLoader.contextLoader()
			.getBean("daoServices");
 
	public List<Pais> getAllCountries() {
		return daoServices.getAll(Pais.class);
	}

	public List<Pais> getAllCountriesById(Integer id) {
		PaisHasUsuarios phu = new PaisHasUsuarios();
		PaisHasUsuariosId phuID = new PaisHasUsuariosId();
		phuID.setIdUsuario(id.intValue());
		phu.setId(phuID);
		List<PaisHasUsuarios> paises = daoServices.getAllByExample(PaisHasUsuarios.class, phu);
		List<Pais> newList = new ArrayList<Pais>();
		for(PaisHasUsuarios pais : paises) {
			if(pais.getId().getIdUsuario() == id) {
				newList.add(daoServices.get(Pais.class, pais.getId().getIdPais()));
			}
		}
		return newList;
	}
	public List<Mediopago> getAllCardBrands() {
		return daoServices.getAll(Mediopago.class);
	}
	
	
	public List<Mediopago> getAllCardBrandsById(Integer id) {
		MediopagoHasUsuarios mhu = new MediopagoHasUsuarios();
		MediopagoHasUsuariosId mhuId = new MediopagoHasUsuariosId();
		mhuId.setIdUsuario(id.intValue());
		mhu.setId(mhuId);
		List<MediopagoHasUsuarios> cards = daoServices.getAllByExample(MediopagoHasUsuarios.class, mhu);
		List<Mediopago> newList = new ArrayList<Mediopago>();
		for(MediopagoHasUsuarios card : cards) {
			if(card.getId().getIdUsuario() == id) {
				newList.add(daoServices.get(Mediopago.class, card.getId().getIdMpago()));
			}
		}
		return newList;
	}


	public List<Canal> getAllChannels() {
		return daoServices.getAll(Canal.class);
	}

	public List<Canal> getAllChannelsById(Integer id) {
		CanalHasUsuarios chu = new CanalHasUsuarios();
		CanalHasUsuariosId chuId = new CanalHasUsuariosId();
		chuId.setIdUsuario(id.intValue());
		chu.setId(chuId);
		List<CanalHasUsuarios> channels = daoServices.getAllByExample(CanalHasUsuarios.class, chu);
		List<Canal> newList = new ArrayList<Canal>();
		for(CanalHasUsuarios channel : channels) {
			if(channel.getId().getIdUsuario() == id) {
				newList.add(daoServices.get(Canal.class, channel.getId().getIdCanal()));
			}
		}
		return newList;
	}
}
