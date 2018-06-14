var injectFlagByTeamNameAndPage = function() {
	(function init() {
		var htmlPageName = getHtmlPageName();

		if (htmlPageName === null || htmlPageName === "users"
				|| htmlPageName === "bet") {
			addFlagsSrcToHomePage();
		} else if (htmlPageName === "world-cup-winner") {
			addFlagsSrcToWorldCupWinnerPage();
		}
	})();

	function getHtmlPageName() {
		var htmlPageName = null;
		var htmlPathPageNames = window.location.pathname.split("/");

		for ( let pageNameIndex in htmlPathPageNames) {
			let pageName = htmlPathPageNames[pageNameIndex];

			if (pageName) {
				htmlPageName = pageName;
				break;
			}
		}

		return htmlPageName;
	}

	function addFlagsSrcToHomePage() {
		let figureElements = document.getElementsByTagName("figure");

		for ( let figureElementIndex in figureElements) {
			let figureElement = figureElements[figureElementIndex];
			let isHtmlElement = figureElement instanceof HTMLElement;

			if (isHtmlElement) {
				let figureElementChildren = figureElement.children;
				let teamName = null;

				for ( let figureElementChildIndex in figureElementChildren) {
					let figureElementChild = figureElementChildren[figureElementChildIndex];
					let isHtmlElement = figureElementChild instanceof HTMLElement;

					if (isHtmlElement) {
						if (figureElementChild.tagName === "DIV") {
							teamName = figureElementChild.textContent;
						}
					}
				}

				for ( let figureElementChildIndex in figureElementChildren) {
					let figureElementChild = figureElementChildren[figureElementChildIndex];
					let isHtmlElement = figureElementChild instanceof HTMLElement;

					if (isHtmlElement) {
						if (figureElementChild.tagName === "IMG") {
							let flag = getFlagByTeamName(teamName);

							if (flag) {
								figureElementChild.src = flag;
							}
						}
					}
				}
			}
		}
	}

	function addFlagsSrcToWorldCupWinnerPage() {
		let teamButtons = document.getElementsByClassName("teamButton");

		for ( let teamButtonIndex in teamButtons) {
			let teamButton = teamButtons[teamButtonIndex];
			let isHtmlElement = teamButton instanceof HTMLElement;

			if (isHtmlElement) {
				let teamButtonChildren = teamButton.children;
				let teamName = null;

				for ( let teamButtonChildIndex in teamButtonChildren) {
					let teamButtonElementChild = teamButtonChildren[teamButtonChildIndex];
					let isHtmlElement = teamButtonElementChild instanceof HTMLElement;

					if (isHtmlElement) {
						if (teamButtonElementChild.tagName === "DIV") {
							teamName = teamButtonElementChild.textContent;
						}
					}
				}

				for ( let teamButtonChildIndex in teamButtonChildren) {
					let teamButtonElementChild = teamButtonChildren[teamButtonChildIndex];
					let isHtmlElement = teamButtonElementChild instanceof HTMLElement;

					if (isHtmlElement) {
						if (teamButtonElementChild.tagName === "IMG") {
							let flag = getFlagByTeamName(teamName);

							if (flag) {
								teamButtonElementChild.src = flag;
							}
						}
					}
				}
			}
		}

		let yourTeamButton = document.getElementById("yourTeamButton");

		if (yourTeamButton) {
			let yourTeamButtonChildren = yourTeamButton.children;
			let teamName = null;

			for ( let yourTeamButtonChildIndex in yourTeamButtonChildren) {
				let yourTeamButtonChild = yourTeamButtonChildren[yourTeamButtonChildIndex];
				let isHtmlElement = yourTeamButtonChild instanceof HTMLElement;

				if (isHtmlElement) {
					if (yourTeamButtonChild.tagName === "DIV") {
						teamName = yourTeamButtonChild.textContent;
					}
				}
			}

			for ( let yourTeamButtonChildIndex in yourTeamButtonChildren) {
				let yourTeamButtonChild = yourTeamButtonChildren[yourTeamButtonChildIndex];
				let isHtmlElement = yourTeamButtonChild instanceof HTMLElement;

				if (isHtmlElement) {
					if (yourTeamButtonChild.tagName === "IMG") {
						let flag = getFlagByTeamName(teamName);

						if (flag) {
							yourTeamButtonChild.src = flag;
						}
					}
				}
			}
		}
	}

	function getFlagByTeamName(team) {
		var flagSrc = null;

		var defaultPreSrc = "../../../img/flag/";
		var flagsByTeamNamesJSON = flagsByTeamNames();

		for ( let flagByTeamNameIndex in flagsByTeamNamesJSON) {
			let flagByTeamName = flagsByTeamNamesJSON[flagByTeamNameIndex];

			if (flagByTeamName.polishTeamName === team) {
				flagSrc = flagByTeamName.flagSrc;
				break;
			}
		}

		if (!flagSrc) {
			return null;
		}

		return defaultPreSrc + flagSrc;
	}

	function flagsByTeamNames() {
		return [ {
			flagSrc : "Egypt.jpg",
			polishTeamName : "Egipt"
		}, {
			flagSrc : "Russia.jpg",
			polishTeamName : "Rosja"
		}, {
			flagSrc : "SaudiArabia.jpg",
			polishTeamName : "Arabia Saudyjska"
		}, {
			flagSrc : "Uruguay.jpg",
			polishTeamName : "Urugwaj"
		}, {
			flagSrc : "Iran.jpg",
			polishTeamName : "Iran"
		}, {
			flagSrc : "Morocco.jpg",
			polishTeamName : "Maroko"
		}, {
			flagSrc : "Portugal.jpg",
			polishTeamName : "Portugalia"
		}, {
			flagSrc : "Spain.jpg",
			polishTeamName : "Hiszpania"
		}, {
			flagSrc : "Australia.jpg",
			polishTeamName : "Australia"
		}, {
			flagSrc : "Denmark.jpg",
			polishTeamName : "Dania"
		}, {
			flagSrc : "France.jpg",
			polishTeamName : "Francja"
		}, {
			flagSrc : "Peru.jpg",
			polishTeamName : "Peru"
		}, {
			flagSrc : "Argentina.jpg",
			polishTeamName : "Argentyna"
		}, {
			flagSrc : "Croatia.jpg",
			polishTeamName : "Chorwacja"
		}, {
			flagSrc : "Iceland.jpg",
			polishTeamName : "Islandia"
		}, {
			flagSrc : "Nigeria.jpg",
			polishTeamName : "Nigeria"
		}, {
			flagSrc : "Brazil.jpg",
			polishTeamName : "Brazylia"
		}, {
			flagSrc : "CostaRica.jpg",
			polishTeamName : "Kostaryka"
		}, {
			flagSrc : "Serbia.jpg",
			polishTeamName : "Serbia"
		}, {
			flagSrc : "Switzerland.jpg",
			polishTeamName : "Szwajcaria"
		}, {
			flagSrc : "Germany.jpg",
			polishTeamName : "Niemcy"
		}, {
			flagSrc : "Mexico.jpg",
			polishTeamName : "Meksyk"
		}, {
			flagSrc : "SouthKorea.jpg",
			polishTeamName : "Korea Południowa"
		}, {
			flagSrc : "Sweden.jpg",
			polishTeamName : "Szwecja"
		}, {
			flagSrc : "Belgium.jpg",
			polishTeamName : "Belgia"
		}, {
			flagSrc : "England.jpg",
			polishTeamName : "Anglia"
		}, {
			flagSrc : "Panama.jpg",
			polishTeamName : "Panama"
		}, {
			flagSrc : "Tunisia.jpg",
			polishTeamName : "Tunezja"
		}, {
			flagSrc : "Colombia.jpg",
			polishTeamName : "Kolumbia"
		}, {
			flagSrc : "Japan.jpg",
			polishTeamName : "Japonia"
		}, {
			flagSrc : "Poland.jpg",
			polishTeamName : "Polska"
		}, {
			flagSrc : "Senegal.jpg",
			polishTeamName : "Senegal"
		} ];
	}
};