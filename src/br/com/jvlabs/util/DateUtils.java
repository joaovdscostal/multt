package br.com.jvlabs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {

	private static final String DATAHORA = "dd/MM/yyyy HH:mm:ss";
	private static final String DATA = "dd/MM/yyyy";
	private static final String DATAINTER = "yyyy-MM-dd";
	private static final String HORA = "HH:mm";
	private static final String DATA_GATEWAY = "dd/MM/yy";
	private static final String DATA_LOCAWEB = "ddMMyyyy";
	private static final String DATA_CLEARSALE = "yyyy-MM-dd HH:mm:ss";
	private static final String MSGDATA = "Data Invalida!";
	private final static int VALUE_ZERO_MINUTES = 0;
	private final static int VALUE_ONE_DAY = 1;
	private final static int VALUE_TWO_AM = 2;
	public final static int TEMPOANTESPERMITIDO = 10;

	public static String datetimeToString(Date data) {
		return formataDatetimeToString(data, DATAHORA);
	}

	public static String dateToString(Date data) {
		return formataDatetimeToString(data, DATA);
	}
	public static String dateToStringInternacional(Date data) {
		return formataDatetimeToString(data, DATAINTER);
	}

	public static String dateToStringForClearSale(Date data) {
		String date = formataDatetimeToString(data, DATA_CLEARSALE);
		return date.replace(" ", "T");
	}

	public static String dateToStringForLocaweb(Date data) {
		return formataDatetimeToString(data, DATA_LOCAWEB);
	}

	public static String dateGatewayToString(Date data) {
		return formataDatetimeToString(data, DATA_GATEWAY);
	}

	public static String timeToString(Date data) {
		return formataDatetimeToString(data, HORA);
	}

	public static String dateToString(Date data, String formatoData) {
		return formataDatetimeToString(data, formatoData);
	}

	public static Date stringToDate(String data) throws IllegalArgumentException {
		if(data == null)
			return null;
		return formataStringToDatetime(data, DATA, MSGDATA);
	}

	public static Date stringToDate(String data, String formato) throws IllegalArgumentException {
		if(data == null)
			return null;
		return formataStringToDatetime(data, formato, MSGDATA);
	}

	private static String formataDatetimeToString(Date data, String param) {

		String ret = null;
		if (data == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(param);
		ret = format.format(data);
		return ret;

	}

	private static Date formataStringToDatetime(String data, String param,String msg) throws IllegalArgumentException {

		Date _data = null;
		if (data == null || data.length() == 0) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat(param);

		try {
			format.setLenient(false);
			_data = format.parse(data);
		} catch (Exception pe) {
			throw new IllegalArgumentException("Data inv&aacute;lida");
		}

		return _data;
	}

	public static Date adicionaMesParaData(Date data, int meses) {
		Date _data = null;
		if (data != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			calendar.add(Calendar.MONTH, meses);
			_data = calendar.getTime();
		}
		return _data;
	}

	public static Date lowDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); //zera os parametros de hour,min,sec,milisec
		return aux.getTime();
	}

	public static Date highDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); //zera os parametros de hour,min,sec,milisec
		aux.add(Calendar.DATE, 1); // vai para o dia seguinte
		aux.roll(Calendar.MILLISECOND, false); // reduz 1 milisegundo
		return aux.getTime();
	}

	public static void toOnlyDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
	}

	public static void toOnlyDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public static Date firstDayOfYear(Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, year);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date lastDayOfYear(Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.YEAR, year);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date adicionaDiaParaData(Date data, int dias) {
		Date _data = null;
		if (data != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			calendar.add(Calendar.DATE, dias);
			_data = calendar.getTime();
		}
		return _data;
	}

	public static Calendar adicionaMinutosParaData(Calendar data, int minutos) {
		if (data != null) {
			data.add(Calendar.MINUTE, minutos);
		}
		return data;
	}

	public static boolean verificaSeDataNaoPassou(Date dataEvento) {
		Calendar calendar = new GregorianCalendar();
		toOnlyDate(calendar);
		Date dataAtual = calendar.getTime();
		return dataEvento.compareTo(dataAtual) >= 0;
	}

	public static String recuperaMesAtual() {
		GregorianCalendar date = new GregorianCalendar();
		int mes = date.get(GregorianCalendar.MONTH);
		mes++;
		if(mes<10)
			return "0"+String.valueOf(mes);
		return String.valueOf(mes);
	}

	public static int recuperaMesAtualInt() {
		GregorianCalendar date = new GregorianCalendar();
		int mes = date.get(GregorianCalendar.MONTH);
		return mes;
	}

	public static Date getTomorrowMorning2am(){
	    Calendar tomorrow = new GregorianCalendar();
	    tomorrow.add(Calendar.DATE, VALUE_ONE_DAY);
	    Calendar result = new GregorianCalendar(
	      tomorrow.get(Calendar.YEAR),
	      tomorrow.get(Calendar.MONTH),
	      tomorrow.get(Calendar.DATE),
	      VALUE_TWO_AM,
	      VALUE_ZERO_MINUTES
	    );
	    return result.getTime();
	  }

	public static Date getMorningTeste(){
	    Calendar tomorrow = new GregorianCalendar();
	    Calendar result = new GregorianCalendar(
	      tomorrow.get(Calendar.YEAR),
	      tomorrow.get(Calendar.MONTH),
	      tomorrow.get(Calendar.DATE),
	      15,
	      49
	    );
	    return result.getTime();
	  }

	public static Date getTomorrowMorning4am(){
	    Calendar tomorrow = new GregorianCalendar();
	    tomorrow.add(Calendar.DATE, VALUE_ONE_DAY);
	    Calendar result = new GregorianCalendar(
	      tomorrow.get(Calendar.YEAR),
	      tomorrow.get(Calendar.MONTH),
	      tomorrow.get(Calendar.DATE),
	      4,
	      VALUE_ZERO_MINUTES
	    );
	    return result.getTime();
	  }

	public static Date getTomorrowMorning3am() {
		Calendar tomorrow = new GregorianCalendar();
	    tomorrow.add(Calendar.DATE, VALUE_ONE_DAY);
	    Calendar result = new GregorianCalendar(
	      tomorrow.get(Calendar.YEAR),
	      tomorrow.get(Calendar.MONTH),
	      tomorrow.get(Calendar.DATE),
	      3,
	      VALUE_ZERO_MINUTES
	    );
	    return result.getTime();
	}

	public static Date dataIncialLonga() {
		Calendar date = new GregorianCalendar();
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.set(Calendar.MONTH, 1);
		date.set(Calendar.YEAR, 2000);
		return date.getTime();
	}

	public static Date dataFinalLonga() {
		Calendar date = new GregorianCalendar();
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.set(Calendar.MONTH, 1);
		date.set(Calendar.YEAR, 2100);
		return date.getTime();
	}

	public static boolean isDateBeforeOrEqualsToday(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		toOnlyDate(calendar);
		if(date.compareTo(calendar.getTime())<=0)
			return true;
		return false;
	}

	public static boolean isDateBeforeToday(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		toOnlyDate(calendar);
		toOnlyDate(date);
		if(date.compareTo(calendar.getTime())<0)
			return true;
		return false;
	}

	public static boolean isDataAfterToday(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		toOnlyDate(calendar);
		if(date.compareTo(calendar.getTime())>0)
			return true;
		return false;
	}

	public static Float diferencaDias(Date cInicio, Date cFim){
		GregorianCalendar calendarIni = new GregorianCalendar();
		calendarIni.setTime(cInicio);
		GregorianCalendar calendarFim = new GregorianCalendar();
		calendarFim.setTime(cFim);
		long ini = calendarIni.getTimeInMillis();
		long fim = calendarFim.getTimeInMillis();
		Float nroHoras = (float) ((fim-ini) / 1000 / 86400);
		return  nroHoras;
	}

	public static Float diferencaHoras(Date cInicio, Date cFim){
		GregorianCalendar calendarIni = new GregorianCalendar();
		calendarIni.setTime(cInicio);
		GregorianCalendar calendarFim = new GregorianCalendar();
		calendarFim.setTime(cFim);
		long ini = calendarIni.getTimeInMillis();
		long fim = calendarFim.getTimeInMillis();
		Float nroHoras = (float) ((fim-ini) / 1000 / 3600);
		return  nroHoras;
	}

	public static Float diferencaMinutos(Date cInicio, Date cFim){
		GregorianCalendar calendarIni = new GregorianCalendar();
		calendarIni.setTime(cInicio);
		GregorianCalendar calendarFim = new GregorianCalendar();
		calendarFim.setTime(cFim);
		long ini = calendarIni.getTimeInMillis();
		long fim = calendarFim.getTimeInMillis();
		Float nroHoras = (float) ((fim-ini) / 1000 / 60);
		return  nroHoras;
	}

	public static int calculaIdade(java.util.Date dataNasc){
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNasc);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        dateOfBirth.add(Calendar.YEAR, age);
        if (today.before(dateOfBirth)) {
            age--;
        }
        return age;

    }

	public static void toOnlyHour(Calendar date) {
		date.set(Calendar.YEAR, 2000);
		date.set(Calendar.MONTH, 5);
		date.set(Calendar.DAY_OF_MONTH, 1);
	}

	public static boolean jaPassouMinutosExcedentes(Date hora, Long minutosExcedentes) {
		Calendar calendarInicio = GregorianCalendar.getInstance();
		Calendar calendarFim = GregorianCalendar.getInstance();
		Calendar calendarAgora = GregorianCalendar.getInstance();

		toOnlyHour(calendarAgora);
		toOnlyHour(calendarInicio);
		toOnlyHour(calendarFim);

		calendarInicio = adicionaMinutosParaData(calendarInicio, -TEMPOANTESPERMITIDO);
		calendarFim = adicionaMinutosParaData(calendarFim, minutosExcedentes.intValue());
		return calendarAgora.compareTo(calendarInicio) > 0 && calendarAgora.compareTo(calendarFim)<=0;
	}

	public static Date alterarDia(Date data, Integer diaVencimento) {
		Calendar dataCalendar = GregorianCalendar.getInstance();
		dataCalendar.setTime(data);
		dataCalendar.set(Calendar.DAY_OF_MONTH, diaVencimento);
		return dataCalendar.getTime();
	}

	public static Date alterarDiaDaSemana(Date data, Integer diaVencimento) {
		Calendar dataCalendar = GregorianCalendar.getInstance();
		dataCalendar.setTime(data);
		dataCalendar.set(Calendar.DAY_OF_WEEK, diaVencimento);
		return dataCalendar.getTime();
	}

	public static Date alteraDiaDaSemanaParaDomingo(Date data) {
		return alterarDiaDaSemana(data, Calendar.SUNDAY);
	}

	public static Date alteraDiaDaSemanaParaSabado(Date data) {
		return alterarDiaDaSemana(data, Calendar.SATURDAY);
	}

	public static String dataAtualFormatada() {
		DateFormat dfmt = new SimpleDateFormat("EEEE, d MMMM yyyy");
        Date hoje = Calendar.getInstance(Locale.getDefault()).getTime();
        return dfmt.format(hoje);
	}

	public static int recuperaAnoAtual() {
		GregorianCalendar date = new GregorianCalendar();
		return date.get(GregorianCalendar.YEAR);
	}

	public static Date firstDayOfMonth(Date value) {
		Calendar date = Calendar.getInstance();
		date.setTime(value);
		date.set(Calendar.DAY_OF_MONTH, 1);
		return lowDateTime(date.getTime());
	}

	public static Date lastDayOfMonth(Date value) {
		Calendar date = Calendar.getInstance();
		date.setTime(value);
		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DATE));
		return highDateTime(date.getTime());
	}

}

