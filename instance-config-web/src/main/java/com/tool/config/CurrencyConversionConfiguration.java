package com.tool.config;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
	category = "currency-conversion", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.tool.config.CurrencyConversionConfiguration",
	localization = "content/Language", name = "currency-conversion-configuration-name"
)
public interface CurrencyConversionConfiguration {

	@Meta.AD(deflt = "https://api.fastforex.io/fetch-all", required = false)
	public String currencyConversionApiEndpoint();

	@Meta.AD(deflt = "b5e9c6f543-d10b57d259-rrxjtf", required = false)
	public String currencyConversionApiSecretKey();

	@Meta.AD(deflt = "5", required = false)
	public int currencyConversionCacheInMinutes();

}