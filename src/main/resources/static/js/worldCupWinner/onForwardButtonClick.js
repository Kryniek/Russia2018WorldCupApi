var onForwardButtonClick = function() {
	(function init() {
		var chosenTeam = window.localStorage
				.getItem(WORLD_CUP_WINNER_CHOSEN_TEAM_LOCAL_STORAGE);

		if (chosenTeam) {
			let teamNameFromLocalStorage = window.localStorage
					.getItem(WORLD_CUP_WINNER_CHOSEN_TEAM_LOCAL_STORAGE);

			document.worldCupWinnerForm.action += teamNameFromLocalStorage;
			document.worldCupWinnerForm.submit();

			window.localStorage
					.removeItem(WORLD_CUP_WINNER_CHOSEN_TEAM_LOCAL_STORAGE);
		} else {
			document.getElementById("notChosenTeamAlert").style.display = "block";
		}
	})();
};