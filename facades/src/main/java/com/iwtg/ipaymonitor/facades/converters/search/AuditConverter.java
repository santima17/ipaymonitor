package com.iwtg.ipaymonitor.facades.converters.search;

import java.util.ArrayList;
import java.util.List;

import com.iwtg.ipaymonitor.datalayer.model.Audit;
import com.iwtg.ipaymonitor.facades.converters.IConverter;
import com.iwtg.ipaymonitor.generic.datatypes.DataTransactionAudit;

public class AuditConverter implements IConverter<DataTransactionAudit, Audit> {

	public DataTransactionAudit converter(Audit source) {
		DataTransactionAudit target = new DataTransactionAudit();
		target.setAccion(source.getAccion());
		target.setEstado(source.getEstado());
		target.setFecha(source.getFecha());
		target.setNroTransaccion(source.getNroTransaccion());
		return target;
	}

	public Audit deConverter(DataTransactionAudit source) {
		Audit target = new Audit();
		target.setAccion(source.getAccion());
		target.setEstado(source.getEstado());
		target.setFecha(source.getFecha());
		target.setNroTransaccion(source.getNroTransaccion());
		return target;
	}

	public List<DataTransactionAudit> convertAll(List<Audit> source) {
		List<DataTransactionAudit> dataAuditList = new ArrayList<DataTransactionAudit>();
		for (Audit auditList : source) {
			dataAuditList.add(converter(auditList));
		}
		return dataAuditList;
	}

	public List<Audit> deConvertAll(List<DataTransactionAudit> source) {
		List<Audit> auditList = new ArrayList<Audit>();
		for (DataTransactionAudit data : source) {
			auditList.add(deConverter(data));
		}
		return auditList;
	}

}
