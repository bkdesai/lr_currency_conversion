import React, { useState, useEffect } from 'react';
import ReactDOM from 'react-dom';
import { ClaySelect } from '@clayui/form';
import ClayForm, { ClayInput } from '@clayui/form';
import { dataListCur } from './currencyList.js';

function CurrencyConversionForm() {
	const [data, setData] = useState([]);
	const [fromAmount, setFromAmount] = useState('1');
	const [fromCurrency, setFromCurrency] = useState('USD');
	const [toCurrency, setToCurrency] = useState('USD');
	const [buttonClicked, setButtonClicked] = useState(false);

	const handleInputFromAmount = (event) => {
		setFromAmount(event.target.value);
	};
	const handleInputFromCurrency = (event) => {
		setFromCurrency(event.target.value);
	};
	const handleInputToCurrency = (event) => {
		setToCurrency(event.target.value);
	};

	useEffect(() => {
		if (buttonClicked) {
			fetchData();
		}
	}, []);

	const handleConvertCurrencyAction = () => {
		setButtonClicked(true);
		fetchData();
	};

	let browserName;

	if (Liferay.Browser.isFirefox()) {
		browserName = "Mozilla Firefox";
	} else if (Liferay.Browser.isOpera()) {
		browserName = "Opera";
	} else if (Liferay.Browser.isIe()) {
		browserName = "Microsoft Internet Explorer";
	} else if (Liferay.Browser.isEdge()) {
		browserName = "Microsoft Edge";
	} else if (Liferay.Browser.isChrome()) {
		browserName = "Google Chrome";
	} else if (Liferay.Browser.isSafari()) {
		browserName = "Apple Safari";
	} else {
		browserName = "unknown";
	}

	const fetchData = async () => {
		event.preventDefault();
		const clientIp = await fetch('https://api.ipify.org?format=json')
			.then(response => response.json())
			.then(data => data.ip)
			.catch(error => {
				console.error(error);
				return null;
			});

		const url = Liferay.LR_OBJ_CC_HISTORY_HEADLESS_API_ENDPOINT; //'/o/headless-cc-history/v1.0/currencyconversion';

		const data = {
			companyId: Liferay.ThemeDisplay.getCompanyId(),
			fromCurrency: fromCurrency.toLowerCase(),
			toCurrency: toCurrency.toLowerCase(),
			fromAmount: fromAmount,
			groupId: Liferay.ThemeDisplay.getScopeGroupId(),
			userId: Liferay.ThemeDisplay.getUserId(),
			userAgent: browserName,
			ipAddress: clientIp
		};

		const requestOptions = {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(data)
		};

		fetch(url, requestOptions)
			.then(response => response.json())
			.then(data => {
				setData(data);
				console.log("response data " + data.toAmount);
				Liferay.Portlet.refresh('#' + Liferay.LR_OBJ_CC_HISTORY_PORTLET_ID);
			})
			.catch(error => console.error(error));
	};

	return (
		<div>
			<ClayForm>
				<div className="sheet sheet-lg">
					<div className="sheet-header">
						<h2 className="sheet-title">Currency Conversion Page</h2>
					</div>
					<div className="sheet-section">
						<div className="form-group-autofit">
							<div className="form-group-item">
								<label htmlFor="fromAmount">Amount</label>
								<ClayInput
									id="fromAmount"
									placeholder="fromAmount"
									type="number"
									required
									onChange={handleInputFromAmount}
									defaultValue={fromAmount}
								/>
							</div>
						</div>
						<div className="form-group-autofit">
							<div className="form-group-item">
								<label htmlFor="fromCurrency">From Currency</label>
								<ClaySelect placeholder="From Currency" aria-label="From Currency" id="fromCurrency" onChange={handleInputFromCurrency} value={fromCurrency}>
									{dataListCur.map(item => (
										<ClaySelect.Option
											key={item}
											label={item}
											value={item}
										/>
									))}
								</ClaySelect>
							</div>
							<div className="form-group-item">
								<label htmlFor="toCurrency">To Currency</label>
								<ClaySelect placeholder="To Currency" aria-label="To Currency" id="toCurrency" onChange={handleInputToCurrency} value={toCurrency}>
									{dataListCur.map(item => (
										<ClaySelect.Option
											key={item}
											label={item}
											value={item}
										/>
									))}
								</ClaySelect>
							</div>
						</div>
						<div className="form-group-autofit">
							<div className="btn-group">
								<div className="btn-group-item">
									<button className="btn btn-primary" type="button" onClick={handleConvertCurrencyAction}>Convert Currency</button>
								</div>
							</div>
						</div>
						<div className="form-group-autofit">
							<div className="form-group-item">
								<h2 className="sheet-title">The Result is Amount : {data.toAmount} </h2>
							</div>
						</div>
					</div>
				</div>
			</ClayForm>
		</div>
	);
}
export default CurrencyConversionForm;