(function() {
	injectFlagByTeamNameAndPage();
	onLoad();

	var forwardButton = document.getElementById("forwardButton");

	if (!!forwardButton) {
		forwardButton.addEventListener("click", onForwardButtonClick);
	}
})();