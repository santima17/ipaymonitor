package com.iwtg.ipaymonitor.facades.converters.system;

import java.util.ArrayList;
import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Canal;
import com.iwtg.ipaymonitor.datalayer.model.Pais;
import com.iwtg.ipaymonitor.datalayer.model.Mediopago;
import com.iwtg.ipaymonitor.facades.converters.IConverter;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataCardBrand;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataChannel;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataCountry;
import com.iwtg.ipaymonitor.facades.datatypes.system.DataSystemItem;

public class SystemItemConverter implements IConverter<DataSystemItem, Object> {
	
	public DataSystemItem converter(Object source) {
		if (source instanceof Pais) {
			DataCountry target = new DataCountry();
			target.setId(((Pais) source).getIdPais());
			target.setName(((Pais) source).getNombre());
			return target;
		} else if (source instanceof Canal) {
			DataChannel target = new DataChannel();
			target.setId(((Canal) source).getIdCanal());
			target.setName(((Canal) source).getNombre());
			return target;
		} else {
			DataCardBrand target = new DataCardBrand();
			target.setId(((Mediopago) source).getIdMpago());
			target.setName(((Mediopago) source).getNombre());
			return target;
		}
	}

	public Object deConverter(DataSystemItem source) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DataSystemItem> convertAll(List<Object> source) {
		List<DataSystemItem> dataSystemItemList = new ArrayList<DataSystemItem>();
		for (Object systemItem : source) {
			dataSystemItemList.add(converter(systemItem));
		}
		return dataSystemItemList;
	}

	public List<Object> deConvertAll(List<DataSystemItem> source) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<DataCountry> convertAllCountries(List<Pais> source) {
		List<DataCountry> dataSystemItemList = new ArrayList<DataCountry>();
		for (Pais pais : source) {
			dataSystemItemList.add((DataCountry)converter(pais));
		}
		return dataSystemItemList;
	}
	
	public List<DataCardBrand> convertAllCardBrands(List<Mediopago> source) {
		List<DataCardBrand> dataSystemItemList = new ArrayList<DataCardBrand>();
		for (Mediopago mediopago : source) {
			dataSystemItemList.add((DataCardBrand)converter(mediopago));
		}
		return dataSystemItemList;
	}
	
	public List<DataChannel> convertAllChannels(List<Canal> source) {
		List<DataChannel> dataSystemItemList = new ArrayList<DataChannel>();
		for (Canal canal : source) {
			dataSystemItemList.add((DataChannel)converter(canal));
		}
		return dataSystemItemList;
	}
	

}
