package com.tool.portlet.internal.client;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.tool.config.CurrencyConversionConfiguration;
import com.tool.portlet.dto.v1_0.CurrencyConversion;
import com.tool.portlet.internal.constants.CurrenceyConversionRestKeys;

@Component(
        immediate = true,
        service = CurrencyConversionRestClient.class
)
public class CurrencyConversionRestClient {
	
	public JSONObject fetchCurrencyRatesFromExtAPI(CurrencyConversion currencyConversion) {
		
		log.debug("Rest call to get Conversion rates - https://api.fastforex.io/fetch-all");
		
		try {
			CurrencyConversionConfiguration currencyConversionConfiguration = configurationProvider.getCompanyConfiguration(CurrencyConversionConfiguration.class, currencyConversion.getCompanyId() );
			String conversionRatesApiEndpoint = currencyConversionConfiguration.currencyConversionApiEndpoint()+CurrenceyConversionRestKeys.QUE_PARAM + CurrenceyConversionRestKeys.API_KEY +currencyConversionConfiguration.currencyConversionApiSecretKey();
            
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> responseRates = restTemplate.exchange(conversionRatesApiEndpoint, HttpMethod.GET, entity, String.class);
            JSONObject responseRatesJson = JSONFactoryUtil.createJSONObject(responseRates.getBody());
            log.debug("### resJsonObject by Spring RestTemplate  " +responseRatesJson);
            return responseRatesJson;
            
        } catch (ConfigurationException cfException) {
			log.error("Error in getting currency rates from External API ConfigurationException :  " +cfException.getMessage(), cfException);
		} catch (JSONException jsonException) {
			log.error("Error in getting currency rates from External API JSONException :  " +jsonException.getMessage(), jsonException);
		}  catch (Exception exception) {
			log.error("Error in getting currency rates from External API Exception :  " +exception.getMessage(), exception);
		}
		return null;
    }
	
	private Log log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Reference
	private ConfigurationProvider configurationProvider;

}
