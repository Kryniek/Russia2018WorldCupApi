(function() {
	injectFlagByTeamNameAndPage();
	onLoad();

	document.getElementById("forwardButton").addEventListener("click",
			onForwardButtonClick);
})();