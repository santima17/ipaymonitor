package com.iwtg.ipaymonitor.servicelayer.users.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Usuarios;

public interface IPayMonitorUserService {
	
	int saveUser(final Usuarios user);

	List<Usuarios> getAll();

	Usuarios getUserByNickname(final String nickname);

	boolean findUser(final String nickname, final String password);

	boolean updateUser(final Usuarios user);

	boolean deleteUser(final Integer id);

	Usuarios getUserById(final Integer id);

	boolean userExist(final Usuarios user);

	boolean addCountryForUser(final Integer userID, final String countryID);

	boolean addCardbrandForUser(final Integer userID, final String cardbrandID);

	boolean addChannelForUser(final Integer userID, final String channelID);

	boolean removeCountryForUser(final Integer userID, final String countryID);

	boolean removeChannelForUser(final Integer userID, final String channelID);

	boolean removeCardbrandForUser(final Integer userID, final String cardbrandID);

	boolean login(final String userName, final String passEncrptyed);

}
