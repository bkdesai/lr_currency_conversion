package com.tool.portlet.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author brijesh
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false",
		"osgi.jaxrs.application.base=/currency-conversion-restbuilder",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=CurrencyConversionRestbuilder"
	},
	service = Application.class
)
@Generated("")
public class CurrencyConversionRestbuilderApplication extends Application {
}