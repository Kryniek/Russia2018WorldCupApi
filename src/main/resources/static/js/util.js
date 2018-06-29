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