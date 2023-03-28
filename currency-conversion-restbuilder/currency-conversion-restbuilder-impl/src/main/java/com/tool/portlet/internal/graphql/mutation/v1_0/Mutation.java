package com.tool.portlet.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import com.tool.portlet.dto.v1_0.CurrencyConversion;
import com.tool.portlet.resource.v1_0.CurrencyConversionResource;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author brijesh
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setCurrencyConversionResourceComponentServiceObjects(
		ComponentServiceObjects<CurrencyConversionResource>
			currencyConversionResourceComponentServiceObjects) {

		_currencyConversionResourceComponentServiceObjects =
			currencyConversionResourceComponentServiceObjects;
	}

	@GraphQLField(description = "currency conversion create form fields")
	public CurrencyConversion createCurrencyConversion(
			@GraphQLName("currencyConversion") CurrencyConversion
				currencyConversion)
		throws Exception {

		return _applyComponentServiceObjects(
			_currencyConversionResourceComponentServiceObjects,
			this::_populateResourceContext,
			currencyConversionResource ->
				currencyConversionResource.postCurrencyConversion(
					currencyConversion));
	}

	@GraphQLField
	public Response createCurrencyConversionBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_currencyConversionResourceComponentServiceObjects,
			this::_populateResourceContext,
			currencyConversionResource ->
				currencyConversionResource.postCurrencyConversionBatch(
					callbackURL, object));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			CurrencyConversionResource currencyConversionResource)
		throws Exception {

		currencyConversionResource.setContextAcceptLanguage(_acceptLanguage);
		currencyConversionResource.setContextCompany(_company);
		currencyConversionResource.setContextHttpServletRequest(
			_httpServletRequest);
		currencyConversionResource.setContextHttpServletResponse(
			_httpServletResponse);
		currencyConversionResource.setContextUriInfo(_uriInfo);
		currencyConversionResource.setContextUser(_user);
		currencyConversionResource.setGroupLocalService(_groupLocalService);
		currencyConversionResource.setRoleLocalService(_roleLocalService);

		currencyConversionResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private static ComponentServiceObjects<CurrencyConversionResource>
		_currencyConversionResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;
	private VulcanBatchEngineImportTaskResource
		_vulcanBatchEngineImportTaskResource;

}