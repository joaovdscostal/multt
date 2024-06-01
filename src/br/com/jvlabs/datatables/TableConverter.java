package br.com.jvlabs.datatables;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Order;

import br.com.caelum.iogi.parameters.Parameter;
import br.com.caelum.iogi.parameters.Parameters;
import br.com.caelum.iogi.reflection.Target;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.caelum.vraptor.http.iogi.VRaptorInstantiator;

@Convert(Table.class)
@ApplicationScoped
public class TableConverter implements Converter<Table>{

	@Inject private HttpServletRequest request;
	@Inject private VRaptorInstantiator vRaptorInstantiator;

	@Override
	public Table convert(String value, Class<? extends Table> type) {

		Long start = 0L;
		Long length = 10L;

		try {
			start = Long.parseLong(this.request.getParameter("start"));
		}catch (NumberFormatException  e) {}

		try {
			length = Long.parseLong(this.request.getParameter("length"));
		}catch (NumberFormatException  e) {}


		String search = this.request.getParameter("search[value]");
		//String searchRegex =this.request.getParameter("search[regex]");
		List<Column> columns = getColumns();
		Order order = getOrder(columns);


		Table table = new Table();

		table.setStart(start);
		table.setLength(length);
		table.setSearch(search);
		table.setOrder(order);
		table.setColumns(columns);

		return table;
	}

	private Order getOrder(List<Column> columns) {

		try {
			String orderDir = this.request.getParameter("order[0][dir]");
			int orderColumn = Integer.parseInt(this.request.getParameter("order[0][column]"));

			Column column = columns.get(orderColumn);
			String data = column.getData();

			if(data != null && !data.isEmpty() && !data.equals("null")) {
				if("desc".equals(orderDir)) {
					return Order.desc(column.getData());
				}else {
					return Order.asc(column.getData());
				}
			}
		}catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {}

		return Order.desc("id");
	}

	@SuppressWarnings("unchecked")
	private List<Column> getColumns() {
		Map<String, String[]> parametersMap = request.getParameterMap();

		List<Parameter> parameters = new ArrayList<>();

		for (Entry<String, String[]> parameter : parametersMap.entrySet()) {
			if(parameter.getKey().startsWith("columns")) {
				Matcher m = Pattern.compile("columns\\[(\\d*)\\](.*)").matcher(parameter.getKey());
				if (m.find()) {
					String t = m.group(2).replaceAll("\\]\\[", ".").replaceAll("(\\]|\\[)", "");

					String name = "columns["+m.group(1)+"]."+t;
					String objeto = "";

					if(parameter.getValue().length > 0)
						objeto = parameter.getValue()[0];

					parameters.add(new Parameter(name, objeto));
				}
			}
		}

		Parameters parameters2 = new Parameters(parameters);

		List<Column> columns = new ArrayList<>();
		try {
			Type listOfColumn = ContainsList.class.getDeclaredField("listOfColumn").getGenericType();
			Target<List<Column>> target = new Target<List<Column>>(listOfColumn, "columns");
			columns = (List<Column>) vRaptorInstantiator.instantiate(target, parameters2);
		} catch (NoSuchFieldException | SecurityException e) {}
		return columns;
	}

}

class ContainsList {
	public List<Column> listOfColumn;
}
