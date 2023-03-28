package com.tool.portlet.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import com.tool.portlet.client.dto.v1_0.CurrencyConversion;
import com.tool.portlet.client.http.HttpInvoker;
import com.tool.portlet.client.pagination.Page;
import com.tool.portlet.client.resource.v1_0.CurrencyConversionResource;
import com.tool.portlet.client.serdes.v1_0.CurrencyConversionSerDes;

import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author brijesh
 * @generated
 */
@Generated("")
public abstract class BaseCurrencyConversionResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_currencyConversionResource.setContextCompany(testCompany);

		CurrencyConversionResource.Builder builder =
			CurrencyConversionResource.builder();

		currencyConversionResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		CurrencyConversion currencyConversion1 = randomCurrencyConversion();

		String json = objectMapper.writeValueAsString(currencyConversion1);

		CurrencyConversion currencyConversion2 = CurrencyConversionSerDes.toDTO(
			json);

		Assert.assertTrue(equals(currencyConversion1, currencyConversion2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		CurrencyConversion currencyConversion = randomCurrencyConversion();

		String json1 = objectMapper.writeValueAsString(currencyConversion);
		String json2 = CurrencyConversionSerDes.toJSON(currencyConversion);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		CurrencyConversion currencyConversion = randomCurrencyConversion();

		currencyConversion.setFromCurrency(regex);
		currencyConversion.setIpAddress(regex);
		currencyConversion.setToCurrency(regex);
		currencyConversion.setUserAgent(regex);

		String json = CurrencyConversionSerDes.toJSON(currencyConversion);

		Assert.assertFalse(json.contains(regex));

		currencyConversion = CurrencyConversionSerDes.toDTO(json);

		Assert.assertEquals(regex, currencyConversion.getFromCurrency());
		Assert.assertEquals(regex, currencyConversion.getIpAddress());
		Assert.assertEquals(regex, currencyConversion.getToCurrency());
		Assert.assertEquals(regex, currencyConversion.getUserAgent());
	}

	@Test
	public void testPostCurrencyConversion() throws Exception {
		CurrencyConversion randomCurrencyConversion =
			randomCurrencyConversion();

		CurrencyConversion postCurrencyConversion =
			testPostCurrencyConversion_addCurrencyConversion(
				randomCurrencyConversion);

		assertEquals(randomCurrencyConversion, postCurrencyConversion);
		assertValid(postCurrencyConversion);
	}

	protected CurrencyConversion
			testPostCurrencyConversion_addCurrencyConversion(
				CurrencyConversion currencyConversion)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		CurrencyConversion currencyConversion,
		List<CurrencyConversion> currencyConversions) {

		boolean contains = false;

		for (CurrencyConversion item : currencyConversions) {
			if (equals(currencyConversion, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			currencyConversions + " does not contain " + currencyConversion,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		CurrencyConversion currencyConversion1,
		CurrencyConversion currencyConversion2) {

		Assert.assertTrue(
			currencyConversion1 + " does not equal " + currencyConversion2,
			equals(currencyConversion1, currencyConversion2));
	}

	protected void assertEquals(
		List<CurrencyConversion> currencyConversions1,
		List<CurrencyConversion> currencyConversions2) {

		Assert.assertEquals(
			currencyConversions1.size(), currencyConversions2.size());

		for (int i = 0; i < currencyConversions1.size(); i++) {
			CurrencyConversion currencyConversion1 = currencyConversions1.get(
				i);
			CurrencyConversion currencyConversion2 = currencyConversions2.get(
				i);

			assertEquals(currencyConversion1, currencyConversion2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<CurrencyConversion> currencyConversions1,
		List<CurrencyConversion> currencyConversions2) {

		Assert.assertEquals(
			currencyConversions1.size(), currencyConversions2.size());

		for (CurrencyConversion currencyConversion1 : currencyConversions1) {
			boolean contains = false;

			for (CurrencyConversion currencyConversion2 :
					currencyConversions2) {

				if (equals(currencyConversion1, currencyConversion2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				currencyConversions2 + " does not contain " +
					currencyConversion1,
				contains);
		}
	}

	protected void assertValid(CurrencyConversion currencyConversion)
		throws Exception {

		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("companyId", additionalAssertFieldName)) {
				if (currencyConversion.getCompanyId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("fromAmount", additionalAssertFieldName)) {
				if (currencyConversion.getFromAmount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("fromCurrency", additionalAssertFieldName)) {
				if (currencyConversion.getFromCurrency() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("groupId", additionalAssertFieldName)) {
				if (currencyConversion.getGroupId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("ipAddress", additionalAssertFieldName)) {
				if (currencyConversion.getIpAddress() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("toAmount", additionalAssertFieldName)) {
				if (currencyConversion.getToAmount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("toCurrency", additionalAssertFieldName)) {
				if (currencyConversion.getToCurrency() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userAgent", additionalAssertFieldName)) {
				if (currencyConversion.getUserAgent() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userId", additionalAssertFieldName)) {
				if (currencyConversion.getUserId() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<CurrencyConversion> page) {
		boolean valid = false;

		java.util.Collection<CurrencyConversion> currencyConversions =
			page.getItems();

		int size = currencyConversions.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.tool.portlet.dto.v1_0.CurrencyConversion.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		CurrencyConversion currencyConversion1,
		CurrencyConversion currencyConversion2) {

		if (currencyConversion1 == currencyConversion2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("companyId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getCompanyId(),
						currencyConversion2.getCompanyId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("fromAmount", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getFromAmount(),
						currencyConversion2.getFromAmount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("fromCurrency", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getFromCurrency(),
						currencyConversion2.getFromCurrency())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("groupId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getGroupId(),
						currencyConversion2.getGroupId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("ipAddress", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getIpAddress(),
						currencyConversion2.getIpAddress())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("toAmount", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getToAmount(),
						currencyConversion2.getToAmount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("toCurrency", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getToCurrency(),
						currencyConversion2.getToCurrency())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userAgent", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getUserAgent(),
						currencyConversion2.getUserAgent())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						currencyConversion1.getUserId(),
						currencyConversion2.getUserId())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		Stream<java.lang.reflect.Field> stream = Stream.of(
			ReflectionUtil.getDeclaredFields(clazz));

		return stream.filter(
			field -> !field.isSynthetic()
		).toArray(
			java.lang.reflect.Field[]::new
		);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_currencyConversionResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_currencyConversionResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator,
		CurrencyConversion currencyConversion) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("companyId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("fromAmount")) {
			sb.append(String.valueOf(currencyConversion.getFromAmount()));

			return sb.toString();
		}

		if (entityFieldName.equals("fromCurrency")) {
			sb.append("'");
			sb.append(String.valueOf(currencyConversion.getFromCurrency()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("groupId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("ipAddress")) {
			sb.append("'");
			sb.append(String.valueOf(currencyConversion.getIpAddress()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("toAmount")) {
			sb.append(String.valueOf(currencyConversion.getToAmount()));

			return sb.toString();
		}

		if (entityFieldName.equals("toCurrency")) {
			sb.append("'");
			sb.append(String.valueOf(currencyConversion.getToCurrency()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("userAgent")) {
			sb.append("'");
			sb.append(String.valueOf(currencyConversion.getUserAgent()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("userId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected CurrencyConversion randomCurrencyConversion() throws Exception {
		return new CurrencyConversion() {
			{
				companyId = RandomTestUtil.randomLong();
				fromAmount = RandomTestUtil.randomDouble();
				fromCurrency = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				groupId = RandomTestUtil.randomLong();
				ipAddress = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				toAmount = RandomTestUtil.randomDouble();
				toCurrency = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				userAgent = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				userId = RandomTestUtil.randomLong();
			}
		};
	}

	protected CurrencyConversion randomIrrelevantCurrencyConversion()
		throws Exception {

		CurrencyConversion randomIrrelevantCurrencyConversion =
			randomCurrencyConversion();

		return randomIrrelevantCurrencyConversion;
	}

	protected CurrencyConversion randomPatchCurrencyConversion()
		throws Exception {

		return randomCurrencyConversion();
	}

	protected CurrencyConversionResource currencyConversionResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected static class BeanTestUtil {

		public static void copyProperties(Object source, Object target)
			throws Exception {

			Class<?> sourceClass = _getSuperClass(source.getClass());

			Class<?> targetClass = target.getClass();

			for (java.lang.reflect.Field field :
					sourceClass.getDeclaredFields()) {

				if (field.isSynthetic()) {
					continue;
				}

				Method getMethod = _getMethod(
					sourceClass, field.getName(), "get");

				Method setMethod = _getMethod(
					targetClass, field.getName(), "set",
					getMethod.getReturnType());

				setMethod.invoke(target, getMethod.invoke(source));
			}
		}

		public static boolean hasProperty(Object bean, String name) {
			Method setMethod = _getMethod(
				bean.getClass(), "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod != null) {
				return true;
			}

			return false;
		}

		public static void setProperty(Object bean, String name, Object value)
			throws Exception {

			Class<?> clazz = bean.getClass();

			Method setMethod = _getMethod(
				clazz, "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod == null) {
				throw new NoSuchMethodException();
			}

			Class<?>[] parameterTypes = setMethod.getParameterTypes();

			setMethod.invoke(bean, _translateValue(parameterTypes[0], value));
		}

		private static Method _getMethod(Class<?> clazz, String name) {
			for (Method method : clazz.getMethods()) {
				if (name.equals(method.getName()) &&
					(method.getParameterCount() == 1) &&
					_parameterTypes.contains(method.getParameterTypes()[0])) {

					return method;
				}
			}

			return null;
		}

		private static Method _getMethod(
				Class<?> clazz, String fieldName, String prefix,
				Class<?>... parameterTypes)
			throws Exception {

			return clazz.getMethod(
				prefix + StringUtil.upperCaseFirstLetter(fieldName),
				parameterTypes);
		}

		private static Class<?> _getSuperClass(Class<?> clazz) {
			Class<?> superClass = clazz.getSuperclass();

			if ((superClass == null) || (superClass == Object.class)) {
				return clazz;
			}

			return superClass;
		}

		private static Object _translateValue(
			Class<?> parameterType, Object value) {

			if ((value instanceof Integer) &&
				parameterType.equals(Long.class)) {

				Integer intValue = (Integer)value;

				return intValue.longValue();
			}

			return value;
		}

		private static final Set<Class<?>> _parameterTypes = new HashSet<>(
			Arrays.asList(
				Boolean.class, Date.class, Double.class, Integer.class,
				Long.class, Map.class, String.class));

	}

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseCurrencyConversionResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.tool.portlet.resource.v1_0.CurrencyConversionResource
		_currencyConversionResource;

}