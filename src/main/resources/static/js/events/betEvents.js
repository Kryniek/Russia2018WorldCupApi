(function() {
	injectFlagByTeamNameAndPage();
	onLoad();

	var forwardButton = document.getElementById("forwardButton");

	if (!!forwardButton) {
		forwardButton.addEventListener("click", onForwardButtonClick);
	}

	var statisticsButton = document.getElementById("statisticsButton");

	if (!!statisticsButton) {
		statisticsButton.addEventListener("click", onStatisticsButtonClick);
	}
})();