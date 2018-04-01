package com.iwtg.ipaymonitor.servicelayer.users.implementations;

import java.util.List;

import com.iwtg.ipaymonitor.bussineslayer.users.interfaces.IPayMonitorUserBO;
import com.iwtg.ipaymonitor.bussineslayer.users.implementations.IPayMonitorUserBOImplementation;
import com.iwtg.ipaymonitor.bussineslayer.context.IpayMonitorBussinesContextLoader;
import com.iwtg.ipaymonitor.bussineslayer.system.implementations.IPayMonitorSystemBOImplementation;
import com.iwtg.ipaymonitor.bussineslayer.system.interfaces.IPayMonitorSystemBO;
import com.iwtg.ipaymonitor.datalayer.model.CanalHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.CanalHasUsuariosId;
import com.iwtg.ipaymonitor.datalayer.model.MediopagoHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.MediopagoHasUsuariosId;
import com.iwtg.ipaymonitor.datalayer.model.Pais;
import com.iwtg.ipaymonitor.datalayer.model.PaisHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.PaisHasUsuariosId;
import com.iwtg.ipaymonitor.datalayer.model.Usuarios;
import com.iwtg.ipaymonitor.servicelayer.users.interfaces.IPayMonitorUserService;

public class IPayMonitorUserServiceImplementation implements IPayMonitorUserService {

	IPayMonitorUserBO userBO = (IPayMonitorUserBOImplementation) IpayMonitorBussinesContextLoader.contextLoader()
			.getBean("userBO");

	IPayMonitorSystemBO systemBO = (IPayMonitorSystemBOImplementation) IpayMonitorBussinesContextLoader.contextLoader()
			.getBean("systemBO");

	public int saveUser(Usuarios user) {
		return userBO.saveUser(user);
	}

	public List<Usuarios> getAll() {
		return userBO.getAll();
	}

	public Usuarios getUserByNickname(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean findUser(String nickname, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateUser(Usuarios user) {
		return userBO.updateUser(user);
	}

	public boolean deleteUser(Integer id) {
		Usuarios user = userBO.getUserById(id);
		if (user != null) {
			return userBO.deleteUser(user);
		} else {
			return false;
		}
	}

	public Usuarios getUserById(Integer id) {
		return userBO.getUserById(id);
	}

	public boolean userExist(Usuarios user) {
		return userBO.userExist(user);
	}

	public boolean addCountryForUser(Integer userID, String countryID) {
		PaisHasUsuarios phu = new PaisHasUsuarios();
		PaisHasUsuariosId phuID = new PaisHasUsuariosId();
		phuID.setIdPais(countryID);
		phuID.setIdUsuario(userID.intValue());
		phu.setId(phuID);
		return userBO.addCountryForUser(phu);
	}

	public boolean addCardbrandForUser(Integer userID, String cardbrandID) {
		MediopagoHasUsuarios mhu = new MediopagoHasUsuarios();
		MediopagoHasUsuariosId mhuID = new MediopagoHasUsuariosId();
		mhuID.setIdMpago(cardbrandID);
		mhuID.setIdUsuario(userID.intValue());
		mhu.setId(mhuID);
		return userBO.addCardbrandForUser(mhu);
	}

	public boolean addChannelForUser(Integer userID, String channelID) {
		CanalHasUsuarios chu = new CanalHasUsuarios();
		CanalHasUsuariosId chuID = new CanalHasUsuariosId();
		chuID.setIdCanal(channelID);
		chuID.setIdUsuario(userID.intValue());
		chu.setId(chuID);
		return userBO.addChannelForUser(chu);
	}

	public boolean removeCountryForUser(Integer userID, String countryID) {
		PaisHasUsuarios phu = new PaisHasUsuarios();
		PaisHasUsuariosId phuID = new PaisHasUsuariosId();
		phuID.setIdPais(countryID);
		phuID.setIdUsuario(userID.intValue());
		phu.setId(phuID);
		return userBO.removeCountryForUser(phu);
	}

	public boolean removeChannelForUser(Integer userID, String channelID) {
		CanalHasUsuarios chu = new CanalHasUsuarios();
		CanalHasUsuariosId chuID = new CanalHasUsuariosId();
		chuID.setIdCanal(channelID);
		chuID.setIdUsuario(userID.intValue());
		chu.setId(chuID);
		return userBO.removeChannelForUser(chu);
	}

	public boolean removeCardbrandForUser(Integer userID, String cardbrandID) {
		MediopagoHasUsuarios mhu = new MediopagoHasUsuarios();
		MediopagoHasUsuariosId mhuID = new MediopagoHasUsuariosId();
		mhuID.setIdMpago(cardbrandID);
		mhuID.setIdUsuario(userID.intValue());
		mhu.setId(mhuID);
		return userBO.removeCardbrandForUser(mhu);
	}

}
