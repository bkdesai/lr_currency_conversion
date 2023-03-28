package com.tool.portlet.internal.resource.v1_0;

import java.io.Serializable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.tool.config.CurrencyConversionConfiguration;
import com.tool.portlet.dto.v1_0.CurrencyConversion;
import com.tool.portlet.internal.client.CurrencyConversionRestClient;
import com.tool.portlet.internal.constants.CurrenceyConversionRestKeys;
import com.tool.portlet.internal.util.CurrencyConversionUtil;
import com.tool.portlet.resource.v1_0.CurrencyConversionResource;

/**
 * @author brijesh
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/currency-conversion.properties",
	scope = ServiceScope.PROTOTYPE, service = CurrencyConversionResource.class
)
public class CurrencyConversionResourceImpl
	extends BaseCurrencyConversionResourceImpl {
	
	@Override
	public CurrencyConversion postCurrencyConversion(
			CurrencyConversion currencyConversion)
		throws Exception {
		try {
			Object cachedData = getCurrencyRatesCache(currencyConversion);
			CurrencyConversion currencyConversionResponse = new CurrencyConversion();
			if(Validator.isNotNull(cachedData) ) {
				currencyConversionResponse = currencyConversionUtil.convertCurrency((JSONObject) cachedData, currencyConversion);
				log.debug("toAmount :: " +currencyConversionResponse.getToAmount());
				addCurrencyConversionHistory(currencyConversionResponse);
			}
			return currencyConversionResponse;
		} catch (Exception exception) {
			log.error("Error in rest call for postCurrencyConversion  ::  " +exception.getMessage(), exception);
		}
		return null;
		
	}

	private Object getCurrencyRatesCache(CurrencyConversion currencyConversion) throws ConfigurationException {
		
		try {
			PortalCacheManager portalCacheManager = multiVMPool.getPortalCacheManager();
			PortalCache<String, Object> cache = portalCacheManager.getPortalCache(CurrenceyConversionRestKeys.CONVERSION_RATES_CACHE);
			Object cachedData = cache.get(CurrenceyConversionRestKeys.CONVERSTION_RATES_CACHE_KEY);
			if (cachedData == null) {
				log.info("Conversion rates not found in Portal Cache.. Calling external API to get conversion rates.. ");
				cachedData = currencyConversionRestClient.fetchCurrencyRatesFromExtAPI(currencyConversion);
			    CurrencyConversionConfiguration currencyConversionConfiguration = configurationProvider.getCompanyConfiguration(CurrencyConversionConfiguration.class,currencyConversion.getCompanyId());
				log.debug("Cache Duration  :: " +currencyConversionConfiguration.currencyConversionCacheInMinutes());
			    cache.put(CurrenceyConversionRestKeys.CONVERSTION_RATES_CACHE_KEY, cachedData, currencyConversionConfiguration.currencyConversionCacheInMinutes() * 60);
			}
			return cachedData;
		} catch (Exception exception) {
			log.error("Error in getting currency rates from cache" +exception.getMessage(), exception);
		}
		return null;
	}
	
	private void addCurrencyConversionHistory(CurrencyConversion currencyConversion) {

		try {
			ObjectDefinition objectDefinition =
					objectDefinitionLocalService.fetchObjectDefinition(currencyConversion.getCompanyId(), CurrenceyConversionRestKeys.LR_OBJECT_C_CURRENCY_CONVERSION);
			objectEntryLocalService.addObjectEntry(
					currencyConversion.getUserId(), 0, objectDefinition.getObjectDefinitionId(),
					HashMapBuilder.<String, Serializable>put(CurrenceyConversionRestKeys.FROM_AMOUNT, currencyConversion.getFromAmount()
					).put(CurrenceyConversionRestKeys.FROM_CURRENCY, currencyConversion.getFromCurrency().toUpperCase()
					).put(CurrenceyConversionRestKeys.TO_AMOUNT, currencyConversion.getToAmount()
					).put(CurrenceyConversionRestKeys.TO_CURRENCY, currencyConversion.getToCurrency().toUpperCase()
					).put(CurrenceyConversionRestKeys.IP_ADDRESS, currencyConversion.getIpAddress()
					).put(CurrenceyConversionRestKeys.USER_AGENT, currencyConversion.getUserAgent()
					).build(),
					new ServiceContext());
			log.debug("Currency Conversion transaction saved in Liferay Currency Conversion Object");
		} catch (PortalException exception) {
			log.error("Error in adding currency converstion history Liferay object " +exception.getMessage(), exception);
		}
	}
	
	private Log log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Reference
	private ConfigurationProvider configurationProvider;
	
	@Reference
	private ObjectDefinitionLocalService objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService objectEntryLocalService;
	
	@Reference
	private MultiVMPool multiVMPool;
	
	@Reference
	private CurrencyConversionRestClient currencyConversionRestClient;
	
	@Reference
	private CurrencyConversionUtil currencyConversionUtil;

	
}