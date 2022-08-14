package com.oficina.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public LocalDate calcularVencimentoPor(LocalDate vencimentoInicial, int parcela) {
		Integer dia = vencimentoInicial.getDayOfMonth();
		Integer mes = vencimentoInicial.getMonthValue() - 1;
		Integer ano = vencimentoInicial.getYear();

		ano = ano + ((mes + parcela) / 12);
		mes = ((mes + parcela) % 12) + 1;

		Integer ultimoDia = LocalDate.of(ano, mes, 1).lengthOfMonth();
		if (dia > ultimoDia) {
			dia = ultimoDia;
		}

		return LocalDate.of(ano, mes, dia);
	}

	public Date stringToDate(String date) throws ParseException {
		String format = date.indexOf(":") > -1 && date.length() > 16 ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date1 = sdf.parse(date);
		return date1;
	}

	public Date addHora(Date data, int horas, int minutos, int segundos) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.HOUR_OF_DAY, horas);
		calendar.add(Calendar.MINUTE, minutos);
		calendar.add(Calendar.SECOND, segundos);
		return calendar.getTime();
	}

	/**
	 * Compara duas datas retornando: 1 se d1 > d2, 2 se d1 < d2 e 0 se d1 = d2
	 * 
	 * @param d1 String com data
	 * @param d2 String com data
	 * @return Integer
	 * @throws ParseException
	 */
	public Integer diff(String d1, String d2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = sdf.parse(d1);
		Date date2 = sdf.parse(d2);

		if (date1.after(date2)) {
			return 1;
		} else if (date1.before(date2)) {
			return -1;
		} else {
			return 0;
		}
	}

	public String dataDMA(LocalDate data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return data.format(formatter);
	}

	public String dataDMA(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}

	public Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public LocalDate hoje() {
		LocalDate hoje = LocalDate.now(ZoneId.systemDefault());
		return hoje;
	}

	public LocalTime horaAtual() {
		LocalTime horaAtual = LocalTime.now(ZoneId.systemDefault());
		return horaAtual;
	}

	public static LocalDateTime agora() {
		LocalDateTime agora = LocalDateTime.now(ZoneId.systemDefault());
		return agora;
	}

	public String dataAMD(LocalDate data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return data.format(formatter);
	}

	public String horaHHMM(LocalTime hora) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return hora.format(formatter);
	}

	public String dataDDMM(LocalDate data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMM");
		return data.format(formatter);
	}

	public String dataAAAAMM(LocalDate data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		return data.format(formatter);
	}

	public long diferenciaEmDias(LocalDate dataInicial, LocalDate dataFinal) {
		return ChronoUnit.DAYS.between(dataFinal, dataInicial);
	}

	public LocalDate dataDDMMAA(String data) {
		if (data.trim().length() == 0) {
			return null;
		}
		int dia = Integer.parseInt(data.substring(0, 2));
		int mes = Integer.parseInt(data.substring(2, 4));
		int ano = 2000 + Integer.parseInt(data.substring(4));
		return LocalDate.of(ano, mes, dia);
	}

}
