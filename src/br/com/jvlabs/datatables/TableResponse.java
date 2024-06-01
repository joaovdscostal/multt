package br.com.jvlabs.datatables;

import java.util.Arrays;
import java.util.List;

public class TableResponse<T> {
	private long recordsTotal;
	private long recordsFiltered;


	private Object[] data;

	public TableResponse(List<T> list, long filtered, long total) {
		this.data = list.toArray();

		this.recordsTotal = total;
		this.recordsFiltered = filtered;
		//this.datatable = datatable;
	}

	public TableResponse(List<T> list, Long total) {
		this(list, total, total);
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Object[] getData() {
		return data;
	}
	public void setData(Object[] data) {
		this.data = data;
	}
	@SuppressWarnings("unchecked")
	public List<T> getDataList() {
		return (List<T>) Arrays.asList(data);
	}
}
