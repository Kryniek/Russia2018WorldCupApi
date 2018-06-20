var gotoMatchBetForm = function(matchId) {
	(function init() {
		var betElClass = "#bet-" + matchId;
		var currentViewPath = location.pathname;
		var href = $(betElClass).attr('href') + "?" + PARENT_VIEW_PATH_URI
				+ "=" + currentViewPath;

		window.location.href = href;
	})();
};