package br.com.jvlabs.datatables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Order;

import br.com.jvlabs.util.DateUtils;

public class Table {

	private long start;
	private long length;
	private String search;
	private List<String> filters = new ArrayList<>();
	private Order order;
	private List<Column> columns;
	private Map<String, String> parametros = new HashMap<>();

	public Table() {
		start = 0L;
		length = 10L;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getLength() {
		return length;
	}

	public Long getSize() {
		return getLength();
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public long getPageNumber() {
		long page = 0;

		if (start >= length) {
			page = start / length;
		}

		return page;
	}

	public long getOffset() {
		return getPageNumber() * length;
	}

	public List<String> getFilters() {
		return filters;
	}

	public void setFilters(List<String> filters) {
		this.filters = filters;
	}

	public void filters(String... params) {
		filters.addAll(Arrays.asList(params));
	}

	public Order getOrder() {
		return this.order;
	}

	public Order getOrderOrDefault(Order order) {
		if (this.order == null)
			setOrder(order);
		return this.getOrder();
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Column> getColumns() {
		return this.columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void filters(HttpServletRequest request, String... params) {
		for (String param : params) {
			parametros.put(param, request.getParameter(param));
		}
	}

	public boolean hasFilter(String param) {
		if (!parametros.containsKey(param))
			return false;
		String value = parametros.get(param);
		return value != null && !value.isEmpty();
	}

	public boolean hasSearch() {
		return search != null && !search.isEmpty();
	}

	public String getValue(String param) {
		return parametros.get(param);
	}

	public Long getValueLong(String param) {
		try {
			return Long.parseLong(getValue(param));
		} catch (NumberFormatException e) {
			return 0L;
		}
	}

	public Date getValueDate(String param) {
		return DateUtils.stringToDate(getValue(param));
	}

	public <E> E getValueEnum(String param, Class<E> classe) {

		if (classe.isEnum()) {
			for (E constant : classe.getEnumConstants()) {
				if (constant.toString().equals(getValue(param))) {
					return constant;
				}
			}
		}

		return null;
	}

	public Map<String, String> getFiltersMap() {
		return Collections.unmodifiableMap(parametros);
	}

	public boolean possuiAtributo(String atributo) {
		String valor = this.parametros.get(atributo);
		return valor != null && !valor.isEmpty();
	}

	public String get(String atributo) {
		return this.parametros.get(atributo);
	}

	public void ordernarPorParametroSenaoExistirOrdem(Order desc) {
		if(order == null) {
			this.order = desc;
		}
	}

}
