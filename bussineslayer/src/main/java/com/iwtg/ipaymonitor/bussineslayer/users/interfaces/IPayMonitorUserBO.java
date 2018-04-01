package com.iwtg.ipaymonitor.bussineslayer.users.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.CanalHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.MediopagoHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.PaisHasUsuarios;
import com.iwtg.ipaymonitor.datalayer.model.Usuarios;

public interface IPayMonitorUserBO {

	int saveUser(final Usuarios user);

	List<Usuarios> getAll();

	Usuarios getUserByNickname(final String nickname);

	boolean findUser(final String nickname, final String password);

	boolean updateUser(final Usuarios user);

	boolean deleteUser(final Usuarios user);

	Usuarios getUserById(final Integer id);

	boolean userExist(final Usuarios user);

	boolean addCountryForUser(final PaisHasUsuarios phu);

	boolean addCardbrandForUser(final MediopagoHasUsuarios mhu);
	
	boolean addChannelForUser(final CanalHasUsuarios chu);

	boolean removeCountryForUser(PaisHasUsuarios phu);

	boolean removeChannelForUser(CanalHasUsuarios chu);

	boolean removeCardbrandForUser(MediopagoHasUsuarios mhu);

}
