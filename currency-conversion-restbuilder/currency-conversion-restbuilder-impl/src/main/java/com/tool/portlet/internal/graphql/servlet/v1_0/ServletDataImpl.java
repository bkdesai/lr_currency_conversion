package com.tool.portlet.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import com.tool.portlet.internal.graphql.mutation.v1_0.Mutation;
import com.tool.portlet.internal.graphql.query.v1_0.Query;
import com.tool.portlet.internal.resource.v1_0.CurrencyConversionResourceImpl;
import com.tool.portlet.resource.v1_0.CurrencyConversionResource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author brijesh
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setCurrencyConversionResourceComponentServiceObjects(
			_currencyConversionResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "CurrencyConversionRestbuilder";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/currency-conversion-restbuilder-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#createCurrencyConversion",
						new ObjectValuePair<>(
							CurrencyConversionResourceImpl.class,
							"postCurrencyConversion"));
					put(
						"mutation#createCurrencyConversionBatch",
						new ObjectValuePair<>(
							CurrencyConversionResourceImpl.class,
							"postCurrencyConversionBatch"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<CurrencyConversionResource>
		_currencyConversionResourceComponentServiceObjects;

}