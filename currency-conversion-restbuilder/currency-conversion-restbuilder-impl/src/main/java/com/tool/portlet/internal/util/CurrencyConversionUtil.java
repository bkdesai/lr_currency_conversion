package com.tool.portlet.internal.util;

import java.text.DecimalFormat;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.tool.portlet.dto.v1_0.CurrencyConversion;
import com.tool.portlet.internal.constants.CurrenceyConversionRestKeys;



@Component(
        immediate = true,
        service = CurrencyConversionUtil.class
)
public class CurrencyConversionUtil {
	
	public CurrencyConversion convertCurrency(JSONObject conversionRatesCache, CurrencyConversion currencyConversion) {
		try {
			double fromCurrencyRates = conversionRatesCache.getJSONObject(CurrenceyConversionRestKeys.RESULTS).getDouble(currencyConversion.getFromCurrency().toUpperCase()); 
			double toCurrencyRates = conversionRatesCache.getJSONObject(CurrenceyConversionRestKeys.RESULTS).getDouble(currencyConversion.getToCurrency().toUpperCase()); 
			double fromAmount =  currencyConversion.getFromAmount();
			double fromBaseCurrencyUSD = fromAmount / fromCurrencyRates ;
			DecimalFormat df = new DecimalFormat(CurrenceyConversionRestKeys.DECIMAL_PATTERN);
			double toAmount = Double.parseDouble(df.format(toCurrencyRates * fromBaseCurrencyUSD));
			currencyConversion.setToAmount(toAmount);
			return currencyConversion;
		} catch (Exception exception) {
			log.error("Error in converting currency from convertCurrency " +exception.getMessage(), exception);
		}
		return null;
	}
	
	private Log log = LogFactoryUtil.getLog(this.getClass().getName());

}
