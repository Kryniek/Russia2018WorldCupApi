var gotoMatchBetForm = function(matchId) {
	(function init() {
		var betElClass = "#bet-" + matchId;
		var href = $(betElClass).attr('href');

		window.location.href = href;
	})();
};