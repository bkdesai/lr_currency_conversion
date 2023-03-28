package com.tool.portlet.client.dto.v1_0;

import com.tool.portlet.client.function.UnsafeSupplier;
import com.tool.portlet.client.serdes.v1_0.CurrencyConversionSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author brijesh
 * @generated
 */
@Generated("")
public class CurrencyConversion implements Cloneable, Serializable {

	public static CurrencyConversion toDTO(String json) {
		return CurrencyConversionSerDes.toDTO(json);
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void setCompanyId(
		UnsafeSupplier<Long, Exception> companyIdUnsafeSupplier) {

		try {
			companyId = companyIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long companyId;

	public Double getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(Double fromAmount) {
		this.fromAmount = fromAmount;
	}

	public void setFromAmount(
		UnsafeSupplier<Double, Exception> fromAmountUnsafeSupplier) {

		try {
			fromAmount = fromAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Double fromAmount;

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public void setFromCurrency(
		UnsafeSupplier<String, Exception> fromCurrencyUnsafeSupplier) {

		try {
			fromCurrency = fromCurrencyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String fromCurrency;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setGroupId(
		UnsafeSupplier<Long, Exception> groupIdUnsafeSupplier) {

		try {
			groupId = groupIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long groupId;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setIpAddress(
		UnsafeSupplier<String, Exception> ipAddressUnsafeSupplier) {

		try {
			ipAddress = ipAddressUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String ipAddress;

	public Double getToAmount() {
		return toAmount;
	}

	public void setToAmount(Double toAmount) {
		this.toAmount = toAmount;
	}

	public void setToAmount(
		UnsafeSupplier<Double, Exception> toAmountUnsafeSupplier) {

		try {
			toAmount = toAmountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Double toAmount;

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public void setToCurrency(
		UnsafeSupplier<String, Exception> toCurrencyUnsafeSupplier) {

		try {
			toCurrency = toCurrencyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String toCurrency;

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public void setUserAgent(
		UnsafeSupplier<String, Exception> userAgentUnsafeSupplier) {

		try {
			userAgent = userAgentUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String userAgent;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserId(
		UnsafeSupplier<Long, Exception> userIdUnsafeSupplier) {

		try {
			userId = userIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long userId;

	@Override
	public CurrencyConversion clone() throws CloneNotSupportedException {
		return (CurrencyConversion)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CurrencyConversion)) {
			return false;
		}

		CurrencyConversion currencyConversion = (CurrencyConversion)object;

		return Objects.equals(toString(), currencyConversion.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return CurrencyConversionSerDes.toJSON(this);
	}

}