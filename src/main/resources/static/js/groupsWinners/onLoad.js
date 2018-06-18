var onLoad = function() {
	(function init() {
		var userBetsDataElements = document
				.getElementsByClassName("userBetsData");

		if (!!userBetsDataElements.length) {
			for ( let userBetsDataElementIndex in userBetsDataElements) {
				let userBetsDataElement = userBetsDataElements[userBetsDataElementIndex];
				let isHtmlElement = userBetsDataElement instanceof HTMLElement;

				if (isHtmlElement) {
					selectFirstPlaceOption(userBetsDataElement);

					selectSecondPlaceOption(userBetsDataElement);
				}
			}
		}
	})();

	function selectFirstPlaceOption(userBetsDataElement) {
		let firstPlaceData = userBetsDataElement
				.getElementsByClassName("firstPlaceData")[0].textContent;
		let userBetsDataParentElement = userBetsDataElement.parentElement;

		let firstPlaceSelectElement = userBetsDataParentElement
				.getElementsByClassName("firstPlace")[0]
				.getElementsByTagName("div")[0].getElementsByTagName("select")[0];

		addSelectedAttributeToOptionByText(firstPlaceSelectElement.options,
				firstPlaceData);
	}

	function selectSecondPlaceOption(userBetsDataElement) {
		let secondPlaceData = userBetsDataElement
				.getElementsByClassName("secondPlaceData")[0].textContent;
		let userBetsDataParentElement = userBetsDataElement.parentElement;

		let secondPlaceSelectElement = userBetsDataParentElement
				.getElementsByClassName("secondPlace")[0]
				.getElementsByTagName("div")[0].getElementsByTagName("select")[0];

		addSelectedAttributeToOptionByText(secondPlaceSelectElement.options,
				secondPlaceData);
	}

	function addSelectedAttributeToOptionByText(options, text) {
		for ( let optionIndex in options) {
			let option = options[optionIndex];
			let isHtmlElement = option instanceof HTMLElement;

			if (isHtmlElement && option.text === text) {
				option.setAttribute("selected", true);
			}
		}
	}
};