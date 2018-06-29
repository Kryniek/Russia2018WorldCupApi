var onLoad = function() {
	(function init() {
		addScores();
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
};