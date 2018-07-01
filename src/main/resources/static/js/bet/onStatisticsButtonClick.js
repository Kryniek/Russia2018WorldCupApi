var onStatisticsButtonClick = function() {
	(function init() {
		addExactResultChart();

		addWinDrawChart();
	})();

	function addExactResultChart() {
		var usersBetsChartElement = document.getElementById("usersBetsChart");

		if (!!usersBetsChartElement) {
			let context = usersBetsChartElement.getContext('2d');

			let matchScoreByBetCountElements = document
					.getElementsByClassName("matchScoreByBetCount");

			let chartDataAndLabels = mapElementsToJson(matchScoreByBetCountElements);

			let backgroundColors = getRandomColors(chartDataAndLabels.length);
			let data = chartDataAndLabels.map(function(element) {
				return element.value;
			});
			let labels = chartDataAndLabels.map(function(element) {
				return element.key;
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

	function addWinDrawChart() {
		var usersBetsWinDrawChartElement = document
				.getElementById("usersBetsWinDrawChart");

		if (!!usersBetsWinDrawChartElement) {
			let context = usersBetsWinDrawChartElement.getContext('2d');

			let matchResultByBetCountElements = document
					.getElementsByClassName("matchResultByBetCount");

			let chartDataAndLabels = mapElementsToJson(matchResultByBetCountElements);

			let backgroundColors = getRandomColors(chartDataAndLabels.length);
			let data = chartDataAndLabels.map(function(element) {
				return element.value;
			});
			let labels = chartDataAndLabels.map(function(element) {
				return element.key;
			});
			let hoverBorderWidths = chartDataAndLabels.map(function(element) {
				return 5;
			});

			new Chart(context, {
				type : 'doughnut',
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
};