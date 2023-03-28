package com.tool.portlet.client.serdes.v1_0;

import com.tool.portlet.client.dto.v1_0.CurrencyConversion;
import com.tool.portlet.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author brijesh
 * @generated
 */
@Generated("")
public class CurrencyConversionSerDes {

	public static CurrencyConversion toDTO(String json) {
		CurrencyConversionJSONParser currencyConversionJSONParser =
			new CurrencyConversionJSONParser();

		return currencyConversionJSONParser.parseToDTO(json);
	}

	public static CurrencyConversion[] toDTOs(String json) {
		CurrencyConversionJSONParser currencyConversionJSONParser =
			new CurrencyConversionJSONParser();

		return currencyConversionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(CurrencyConversion currencyConversion) {
		if (currencyConversion == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (currencyConversion.getCompanyId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"companyId\": ");

			sb.append(currencyConversion.getCompanyId());
		}

		if (currencyConversion.getFromAmount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fromAmount\": ");

			sb.append(currencyConversion.getFromAmount());
		}

		if (currencyConversion.getFromCurrency() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fromCurrency\": ");

			sb.append("\"");

			sb.append(_escape(currencyConversion.getFromCurrency()));

			sb.append("\"");
		}

		if (currencyConversion.getGroupId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"groupId\": ");

			sb.append(currencyConversion.getGroupId());
		}

		if (currencyConversion.getIpAddress() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ipAddress\": ");

			sb.append("\"");

			sb.append(_escape(currencyConversion.getIpAddress()));

			sb.append("\"");
		}

		if (currencyConversion.getToAmount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"toAmount\": ");

			sb.append(currencyConversion.getToAmount());
		}

		if (currencyConversion.getToCurrency() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"toCurrency\": ");

			sb.append("\"");

			sb.append(_escape(currencyConversion.getToCurrency()));

			sb.append("\"");
		}

		if (currencyConversion.getUserAgent() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userAgent\": ");

			sb.append("\"");

			sb.append(_escape(currencyConversion.getUserAgent()));

			sb.append("\"");
		}

		if (currencyConversion.getUserId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(currencyConversion.getUserId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CurrencyConversionJSONParser currencyConversionJSONParser =
			new CurrencyConversionJSONParser();

		return currencyConversionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		CurrencyConversion currencyConversion) {

		if (currencyConversion == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (currencyConversion.getCompanyId() == null) {
			map.put("companyId", null);
		}
		else {
			map.put(
				"companyId", String.valueOf(currencyConversion.getCompanyId()));
		}

		if (currencyConversion.getFromAmount() == null) {
			map.put("fromAmount", null);
		}
		else {
			map.put(
				"fromAmount",
				String.valueOf(currencyConversion.getFromAmount()));
		}

		if (currencyConversion.getFromCurrency() == null) {
			map.put("fromCurrency", null);
		}
		else {
			map.put(
				"fromCurrency",
				String.valueOf(currencyConversion.getFromCurrency()));
		}

		if (currencyConversion.getGroupId() == null) {
			map.put("groupId", null);
		}
		else {
			map.put("groupId", String.valueOf(currencyConversion.getGroupId()));
		}

		if (currencyConversion.getIpAddress() == null) {
			map.put("ipAddress", null);
		}
		else {
			map.put(
				"ipAddress", String.valueOf(currencyConversion.getIpAddress()));
		}

		if (currencyConversion.getToAmount() == null) {
			map.put("toAmount", null);
		}
		else {
			map.put(
				"toAmount", String.valueOf(currencyConversion.getToAmount()));
		}

		if (currencyConversion.getToCurrency() == null) {
			map.put("toCurrency", null);
		}
		else {
			map.put(
				"toCurrency",
				String.valueOf(currencyConversion.getToCurrency()));
		}

		if (currencyConversion.getUserAgent() == null) {
			map.put("userAgent", null);
		}
		else {
			map.put(
				"userAgent", String.valueOf(currencyConversion.getUserAgent()));
		}

		if (currencyConversion.getUserId() == null) {
			map.put("userId", null);
		}
		else {
			map.put("userId", String.valueOf(currencyConversion.getUserId()));
		}

		return map;
	}

	public static class CurrencyConversionJSONParser
		extends BaseJSONParser<CurrencyConversion> {

		@Override
		protected CurrencyConversion createDTO() {
			return new CurrencyConversion();
		}

		@Override
		protected CurrencyConversion[] createDTOArray(int size) {
			return new CurrencyConversion[size];
		}

		@Override
		protected void setField(
			CurrencyConversion currencyConversion, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "companyId")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setCompanyId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fromAmount")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setFromAmount(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fromCurrency")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setFromCurrency(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "groupId")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setGroupId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ipAddress")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setIpAddress(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "toAmount")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setToAmount(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "toCurrency")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setToCurrency(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userAgent")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setUserAgent(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userId")) {
				if (jsonParserFieldValue != null) {
					currencyConversion.setUserId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}