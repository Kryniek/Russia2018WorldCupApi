var injectFlagByTeamNameAndPage = function() {
	(function init() {
		var htmlPageName = window.location.pathname.split("/").pop();

		replaceUKTeamsNamesToPolishTeamsNames(htmlPageName, getPolishTeamName);

		if (htmlPageName === "home") {
			addFlagsSrcToHomePage();
		}
	})();

	// TODO REMOVE
	function replaceUKTeamsNamesToPolishTeamsNames(htmlPageName,
			getPolishTeamNameFunc) {
		if (htmlPageName === "home") {
			let figureElements = document.getElementsByTagName("figure");

			for ( let figureElementIndex in figureElements) {
				let figureElement = figureElements[figureElementIndex];
				let isHtmlElement = figureElement instanceof HTMLElement;

				if (isHtmlElement) {
					let figureElementChildren = figureElement.children;

					for ( let figureElementChildIndex in figureElementChildren) {
						let figureElementChild = figureElementChildren[figureElementChildIndex];
						let isHtmlElement = figureElementChild instanceof HTMLElement;

						if (isHtmlElement) {
							if (figureElementChild.tagName === "P") {
								let polishTeamName = getPolishTeamNameFunc(figureElementChild.textContent);

								if (polishTeamName) {
									figureElementChild.textContent = polishTeamName;
								}
							}
						}
					}
				}
			}
		}
	};

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
						if (figureElementChild.tagName === "P") {
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
	};

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
		
		if(!flagSrc){
			return null;
		}

		return defaultPreSrc + flagSrc;
	};

	// TODO REMOVE
	function getPolishTeamName(team) {
		var polishTeamName = null;

		var defaultPreSrc = "../../../img/flag/";
		var flagsByTeamNamesJSON = flagsByTeamNames();

		for ( let flagByTeamNameIndex in flagsByTeamNamesJSON) {
			let flagByTeamName = flagsByTeamNamesJSON[flagByTeamNameIndex];

			if (flagByTeamName.team === team) {
				polishTeamName = flagByTeamName.polishTeamName;
				break;
			}
		}

		return polishTeamName;
	};

	function flagsByTeamNames() {
		return [ {
			team : "Egypt",
			flagSrc : "Egypt.jpg",
			polishTeamName : "Egipt"
		}, {
			team : "Russia",
			flagSrc : "Russia.jpg",
			polishTeamName : "Rosja"
		}, {
			team : "Saudi Arabia",
			flagSrc : "SaudiArabia.jpg",
			polishTeamName : "Arabia Saudyjska"
		}, {
			team : "Uruguay",
			flagSrc : "Uruguay.jpg",
			polishTeamName : "Urugwaj"
		}, {
			team : "Iran",
			flagSrc : "Iran.jpg",
			polishTeamName : "Iran"
		}, {
			team : "Morocco",
			flagSrc : "Morocco.jpg",
			polishTeamName : "Maroko"
		}, {
			team : "Portugal",
			flagSrc : "Portugal.jpg",
			polishTeamName : "Portugalia"
		}, {
			team : "Spain",
			flagSrc : "Spain.jpg",
			polishTeamName : "Hiszpania"
		}, {
			team : "Australia",
			flagSrc : "Australia.jpg",
			polishTeamName : "	Australia"
		}, {
			team : "Denmark",
			flagSrc : "Denmark.jpg",
			polishTeamName : "Dania"
		}, {
			team : "France",
			flagSrc : "France.jpg",
			polishTeamName : "Francja"
		}, {
			team : "Peru",
			flagSrc : "Peru.jpg",
			polishTeamName : "Peru"
		}, {
			team : "Argentina",
			flagSrc : "Argentina.jpg",
			polishTeamName : "Argentyna"
		}, {
			team : "Croatia",
			flagSrc : "Croatia.jpg",
			polishTeamName : "	Chorwacja"
		}, {
			team : "Iceland",
			flagSrc : "Iceland.jpg",
			polishTeamName : "Islandia"
		}, {
			team : "Nigeria",
			flagSrc : "Nigeria.jpg",
			polishTeamName : "Nigeria"
		}, {
			team : "Brazil",
			flagSrc : "Brazil.jpg",
			polishTeamName : "Brazylia"
		}, {
			team : "Costa Rica",
			flagSrc : "CostaRica.jpg",
			polishTeamName : "Kostaryka"
		}, {
			team : "Serbia",
			flagSrc : "Serbia.jpg",
			polishTeamName : "Serbia"
		}, {
			team : "Switzerland",
			flagSrc : "Switzerland.jpg",
			polishTeamName : "Szwajcaria"
		}, {
			team : "Germany",
			flagSrc : "Germany.jpg",
			polishTeamName : "Niemcy"
		}, {
			team : "Mexico",
			flagSrc : "Mexico.jpg",
			polishTeamName : "Meksyk"
		}, {
			team : "South Korea",
			flagSrc : "SouthKorea.jpg",
			polishTeamName : "Korea Południowa"
		}, {
			team : "Sweden",
			flagSrc : "Sweden.jpg",
			polishTeamName : "Szwecja"
		}, {
			team : "Belgium",
			flagSrc : "Belgium.jpg",
			polishTeamName : "Belgia"
		}, {
			team : "England",
			flagSrc : "England.jpg",
			polishTeamName : "Wielka Brytania"
		}, {
			team : "Panama",
			flagSrc : "Panama.jpg",
			polishTeamName : "Panama"
		}, {
			team : "Tunisia",
			flagSrc : "Tunisia.jpg",
			polishTeamName : "Tunezja"
		}, {
			team : "Colombia",
			flagSrc : "Colombia.jpg",
			polishTeamName : "Kolumbia"
		}, {
			team : "Japan",
			flagSrc : "Japan.jpg",
			polishTeamName : "Japonia"
		}, {
			team : "Poland",
			flagSrc : "Poland.jpg",
			polishTeamName : "Polska"
		}, {
			team : "Senegal",
			flagSrc : "Senegal.jpg",
			polishTeamName : "Senegal"
		} ];
	};
};