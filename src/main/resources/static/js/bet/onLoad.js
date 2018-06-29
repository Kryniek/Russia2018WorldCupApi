var onLoad = function() {
	(function init() {
		addScores();

		addExactResultChart();
	})();

	function addScores() {
		var hometeamScoreFromDB = document
				.getElementById("hometeamScoreFromDB").textContent;
		var awayteamScoreFromDB = document
				.getElementById("awayteamScoreFromDB").textContent;

		if (!!awayteamScoreFromDB && !!hometeamScoreFromDB) {
			let hometeamScoreElement = document.getElementById("hometeamScore");
			let awayteamScoreElement = document.getElementById("awayteamScore");

			hometeamScoreElement.value = hometeamScoreFromDB;
			awayteamScoreElement.value = awayteamScoreFromDB;
		}
	}

	function addExactResultChart() {
		var usersBetsChartElement = document.getElementById("usersBetsChart");

		if (!!usersBetsChartElement) {
			let context = usersBetsChartElement.getContext('2d');

			let chartDataAndLabels = getExactResultChartDataAndLabels();

			let backgroundColors = getRandomColors(chartDataAndLabels.length);
			let data = chartDataAndLabels.map(function(element) {
				return element.data;
			});
			let labels = chartDataAndLabels.map(function(element) {
				return element.label;
			});
			let hoverBorderWidths = chartDataAndLabels.map(function(element) {
				return 5;
			});

			new Chart(context, {
				type : 'pie',
				data : {
					datasets : [ {
						data : data,
						backgroundColor : backgroundColors,
						hoverBorderWidth : hoverBorderWidths
					} ],
					labels : labels
				}
			});
		}
	}

	function getExactResultChartDataAndLabels() {
		var dataAndLabels = [];

		var matchScoreByBetCountElements = document
				.getElementsByClassName("matchScoreByBetCount");

		for ( let elementIndex in matchScoreByBetCountElements) {
			let element = matchScoreByBetCountElements[elementIndex];
			let isHtmlElement = element instanceof HTMLElement;

			if (isHtmlElement) {
				let elementText = element.textContent;
				let indexOfEqualSign = elementText.indexOf('=');

				dataAndLabels.push({
					label : elementText.substr(0, indexOfEqualSign),
					data : elementText.substr(indexOfEqualSign + 1)
				});
			}
		}

		return dataAndLabels;
	}
};