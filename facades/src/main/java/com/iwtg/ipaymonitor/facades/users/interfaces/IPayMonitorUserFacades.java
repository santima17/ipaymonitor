package com.iwtg.ipaymonitor.facades.users.interfaces;

import java.util.List;

import com.iwtg.ipaymonitor.facades.datatypes.user.DataUser;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;

public interface IPayMonitorUserFacades {
	
	int saveUser(DataUser user);

	List<DataUser> getAllUsers() throws IPayMonitorException;

	DataUser getUser(Integer id) throws IPayMonitorException;

	boolean updateUser(DataUser user);

	boolean deleteUser(Integer id) throws IPayMonitorException;

	boolean userExist(DataUser user);

	boolean addCountryForUser(Integer userID, String countryID);
	
	boolean addCardbrandForUser(Integer userID, String cardbrandID);
	
	boolean addChannelForUser(Integer userID, String channelID);

	boolean removeCountryForUser(Integer userID, String countryID);

	boolean removeChannelForUser(Integer userID, String channelID);

	boolean removeCardbrandForUser(Integer userID, String cardbrandID);

}
