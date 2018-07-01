function getRandomColor(opacity) {
	var red = Math.floor(Math.random() * 256);
	var green = Math.floor(Math.random() * 256);
	var blue = Math.floor(Math.random() * 256);

	opacity = (!!opacity) ? opacity : '1';

	return 'rgba(' + red + ', ' + green + ', ' + blue + ', ' + opacity + ')';
}

function getRandomColors(length) {
	var randomColors = [];

	for (let counter = 0; counter < length; counter++) {
		randomColors.push(getRandomColor('0.9'));
	}

	return randomColors;
}

function mapElementsToJson(mapElements) {
	var jsonMap = [];

	for ( let elementIndex in mapElements) {
		let element = mapElements[elementIndex];
		let isHtmlElement = element instanceof HTMLElement;

		if (isHtmlElement) {
			let elementText = element.textContent;
			let indexOfEqualSign = elementText.indexOf('=');

			jsonMap.push({
				key : elementText.substr(0, indexOfEqualSign),
				value : elementText.substr(indexOfEqualSign + 1)
			});
		}
	}

	return jsonMap;
}