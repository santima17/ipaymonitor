package com.iwtg.ipaymonitor.bussineslayer.users.implementations;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.iwtg.ipaymonitor.datalayer.interfaces.IPayMonitorMySQLDAO;
import com.iwtg.ipaymonitor.datalayer.implementations.IPayMonitorMySQLDAOImplementation;
import com.iwtg.ipaymonitor.bussineslayer.users.interfaces.IPayMonitorUserBO;
import com.iwtg.ipaymonitor.datalayer.context.IpayMonitorDAOContextLoader;
import com.iwtg.ipaymonitor.datalayer.model.CanalHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.MediopagoHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.PaisHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.Usuarios;
 
public class IPayMonitorUserBOImplementation implements IPayMonitorUserBO {

	IPayMonitorMySQLDAO daoServices = (IPayMonitorMySQLDAOImplementation) IpayMonitorDAOContextLoader.contextLoader()
			.getBean("daoServices");

	public int saveUser(Usuarios user) {
		
		return daoServices.save(user);
	}

	public List<Usuarios> getAll() {
		return daoServices.getAll(Usuarios.class);
	}

	public Usuarios getUserByNickname(String nickname) {
		Usuarios exampleUser = new Usuarios();
		exampleUser.setUsuario(nickname);
		List<Usuarios> resultList = daoServices.getAllByExample(Usuarios.class, exampleUser);
		if (CollectionUtils.isNotEmpty(resultList)) {
			return resultList.get(0);
		}else {
			return null;
		}
		
	}

	public boolean findUser(String nickname, String password) {
		Usuarios exampleUser = new Usuarios();
		exampleUser.setUsuario(nickname);
		exampleUser.setPass(password);	
		List<Usuarios> resultList = daoServices.getAllByExample(Usuarios.class, exampleUser);
		if (CollectionUtils.isNotEmpty(resultList)) {
			return true;
		}else {
			return false;
		}
	}

	public boolean updateUser(Usuarios user) {
		Usuarios storedUser = daoServices.get(Usuarios.class, user.getIdUsuario());
		storedUser.setAdmin(user.getAdmin());
		storedUser.setApellido(user.getApellido());
		storedUser.setBaja(user.getBaja());
		storedUser.setMotivo(user.getMotivo());
		storedUser.setNombre(user.getNombre());
		storedUser.setPass(user.getPass());
		storedUser.setUsuario(user.getUsuario());
		return daoServices.saveOrUpdate(storedUser);
	}

	public boolean deleteUser(Usuarios user) {
		user.setBaja(true);
		return daoServices.saveOrUpdate(user);
	}

	public Usuarios getUserById(Integer id) {
		return daoServices.get(Usuarios.class, id);
	}

	public boolean userExist(Usuarios user) {
		Usuarios exampleUser = new Usuarios();
		exampleUser.setUsuario(user.getUsuario());
		List result = daoServices.getAllByExample(Usuarios.class, exampleUser);
		return CollectionUtils.isNotEmpty(result);
	}

	public boolean addCountryForUser(PaisHasUsuarios phu) {
		daoServices.save(phu);
		return true;
	}

	public boolean addCardbrandForUser(MediopagoHasUsuarios mhu) {
		daoServices.save(mhu);
		return true;
	}

	public boolean addChannelForUser(CanalHasUsuarios chu) {
		daoServices.save(chu);
		return true;
	}

	public boolean removeCountryForUser(PaisHasUsuarios phu) {
		List<PaisHasUsuarios> resultList = daoServices.getAllByExample(PaisHasUsuarios.class, phu);
		if(CollectionUtils.isNotEmpty(resultList)) {
			daoServices.delete(resultList.get(0));
			return true;
		}else {
			return false;
		}
	}

	public boolean removeChannelForUser(CanalHasUsuarios chu) {
		daoServices.delete(chu);
		return true;
	}

	public boolean removeCardbrandForUser(MediopagoHasUsuarios mhu) {
		daoServices.delete(mhu);
		return true;
	}

}
