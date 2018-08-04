package com.iwtg.ipaymonitor.facades.users.implementations;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.iwtg.ipaymonitor.datalayer.model.Usuarios;
import com.iwtg.ipaymonitor.facades.converters.user.UserConverter;
import com.iwtg.ipaymonitor.facades.datatypes.user.DataUser;
import com.iwtg.ipaymonitor.facades.exceptions.IPayMonitorException;
import com.iwtg.ipaymonitor.facades.users.interfaces.IPayMonitorUserFacades;
import com.iwtg.ipaymonitor.servicelayer.users.interfaces.IPayMonitorUserService;
import com.iwtg.ipaymonitor.servicelayer.users.implementations.IPayMonitorUserServiceImplementation;
import com.iwtg.ipaymonitor.servicelayer.context.IpayMonitorServicesContextLoader;

public class IPayMonitorUserFacadesImplementation implements IPayMonitorUserFacades {

	UserConverter userConverter = new UserConverter();

	IPayMonitorUserService userServices = (IPayMonitorUserServiceImplementation) IpayMonitorServicesContextLoader
			.contextLoader().getBean("userServices");

	public int saveUser(DataUser user) {
		return userServices.saveUser(userConverter.deConverter(user));

	}

	public List<DataUser> getAllUsers() throws IPayMonitorException {
		List<Usuarios> resultList = userServices.getAll();
		if (CollectionUtils.isNotEmpty(resultList)) {
			return userConverter.convertAll(resultList);
		} else {
			throw new IPayMonitorException("Result list is empty");
		}
	}

	public DataUser getUser(Integer id) throws IPayMonitorException {
		Usuarios modelUser = userServices.getUserById(id);
		if (modelUser != null) {
			return userConverter.converter(userServices.getUserById(id));
		} else {
			throw new IPayMonitorException("User not found with id:" + id);
		}
	}
	
	public DataUser getUser(String username) throws IPayMonitorException {
		Usuarios modelUser = userServices.getUserByNickname(username);
		if (modelUser != null) {
			return userConverter.converter(modelUser);
		} else {
			throw new IPayMonitorException("User not found with username:" + username);
		}
	}

	public boolean updateUser(DataUser user) {
		return userServices.updateUser(userConverter.deConverter(user));
	}

	public boolean deleteUser(Integer id) throws IPayMonitorException {
		try {
			return userServices.deleteUser(id);
		} catch (Exception e) {
			throw new IPayMonitorException("Error trying remove user");
		}
	}

	public boolean userExist(DataUser user) {
		return userServices.userExist(userConverter.deConverter(user));
	}

	public boolean addCountryForUser(Integer userID, String countryID) {
		try {
			return userServices.addCountryForUser(userID, countryID);
		} catch (Exception e) {
			return false;
		}
	} 

	public boolean addCardbrandForUser(Integer userID, String cardbrandID) {
		try {
			return userServices.addCardbrandForUser(userID, cardbrandID);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addChannelForUser(Integer userID, String channelID) {
		try {
			return userServices.addChannelForUser(userID, channelID);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeCountryForUser(Integer userID, String countryID) {
		try {
			return userServices.removeCountryForUser(userID, countryID);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeChannelForUser(Integer userID, String cardbrandID) {
		try {
			return userServices.removeChannelForUser(userID, cardbrandID);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeCardbrandForUser(Integer userID, String channelID) {
		try {
			return userServices.removeCardbrandForUser(userID, channelID);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean login(String userName, String passEncrptyed) {
		return userServices.login(userName, passEncrptyed);
	}

}
