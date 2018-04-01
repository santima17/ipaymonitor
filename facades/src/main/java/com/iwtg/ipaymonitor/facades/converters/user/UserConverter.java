package com.iwtg.ipaymonitor.facades.converters.user;

import java.util.ArrayList;
import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Usuarios;
import com.iwtg.ipaymonitor.facades.converters.IConverter;
import com.iwtg.ipaymonitor.facades.datatypes.user.DataUser;

public class UserConverter implements IConverter<DataUser, Usuarios>{

	public DataUser converter(Usuarios source) {
		DataUser dtUser = new DataUser();
		dtUser.setDeleted(source.getBaja());
		dtUser.setDeleteReason(source.getMotivo());
		dtUser.setId(source.getIdUsuario());
		dtUser.setIsAdmin(source.getAdmin());
		dtUser.setLastName(source.getApellido());
		dtUser.setName(source.getNombre());
		dtUser.setPassword(source.getPass());
		dtUser.setUser(source.getUsuario());
		return dtUser;
	}

	public Usuarios deConverter(DataUser source) {
		Usuarios user = new Usuarios();
		user.setBaja(source.getDeleted());
		user.setMotivo(source.getDeleteReason());
		user.setIdUsuario(source.getId());
		user.setAdmin(source.getIsAdmin());
		user.setApellido(source.getLastName());
		user.setNombre(source.getName());
		user.setPass(source.getPassword());
		user.setUsuario(source.getUser());
		return user;
	}

	public List<DataUser> convertAll(List<Usuarios> source) {
		List<DataUser> userDataList = new ArrayList<DataUser>();
		for(Usuarios usuario : source) {
			userDataList.add(converter(usuario));
		}
		return userDataList;
	}

	public List<Usuarios> deConvertAll(List<DataUser> source) {
		// TODO Auto-generated method stub
		return null;
	}

}
