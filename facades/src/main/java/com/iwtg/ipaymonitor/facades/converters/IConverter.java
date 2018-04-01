package com.iwtg.ipaymonitor.facades.converters;

import java.util.List;

public interface IConverter <D,M> {
	
	public D converter(M source);
	public M deConverter(D source);
	public List<D> convertAll(List<M> source);
	public List<M> deConvertAll(List<D> source);

}