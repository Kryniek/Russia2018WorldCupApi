var injectFlagByTeamNameAndPage = function() {
	(function init() {
		var htmlPageName = getHtmlPageName();

		if (htmlPageName === null || htmlPageName === "users"
				|| htmlPageName === "bet") {
			addFlagsSrcToHomePage();
		} else if (htmlPageName === "world-cup-winner") {
			addFlagsSrcToWorldCupWinnerPage();
		} else if (htmlPageName === "matches") {
			addFlagsSrcToMatchesPage();
		} else if (htmlPageName === "user-bets") {
			addFlagSrcToUserBetsPage();
		} else if (htmlPageName === "play-offs") {
			addFlagSrcToPlayOffsPage();
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

	function addFlagsSrcToMatchesPage() {
		var matchRowElements = document.getElementsByClassName("matchRow");

		for ( let matchRowElementIndex in matchRowElements) {
			let matchRowElement = matchRowElements[matchRowElementIndex];
			let isHtmlElement = matchRowElement instanceof HTMLElement;

			if (isHtmlElement) {
				let matchRowElementChildren = matchRowElement.children;

				for ( let matchRowElementChildIndex in matchRowElementChildren) {
					let matchRowElementChild = matchRowElementChildren[matchRowElementChildIndex];
					let isHtmlElement = matchRowElementChild instanceof HTMLElement;

					if (isHtmlElement
							&& matchRowElementChild.classList.contains("row")) {
						let rowElementChildren = matchRowElementChild.children;
						let hometeamName = null;
						let awayteamName = null;

						for ( let rowElementChildIndex in rowElementChildren) {
							let rowElementChild = rowElementChildren[rowElementChildIndex];
							let isHtmlElement = rowElementChild instanceof HTMLElement;

							if (isHtmlElement) {
								let isHometeamName = rowElementChild.classList
										.contains("hometeamName");
								let isAwayteamName = rowElementChild.classList
										.contains("awayteamName");

								if (isHometeamName) {
									hometeamName = rowElementChild.textContent;
								} else if (isAwayteamName) {
									awayteamName = rowElementChild.textContent;
								}
							}
						}

						for ( let rowElementChildIndex in rowElementChildren) {
							let rowElementChild = rowElementChildren[rowElementChildIndex];
							let isHtmlElement = rowElementChild instanceof HTMLElement;

							if (isHtmlElement) {
								let isHometeamFlag = rowElementChild.classList
										.contains("hometeamFlag");
								let isAwayteamFlag = rowElementChild.classList
										.contains("awayteamFlag");

								if (isHometeamFlag) {
									let rowElementChildChildren = rowElementChild.children;

									for ( let secondDepthChildIndex in rowElementChildChildren) {
										let secondDepthChild = rowElementChildChildren[secondDepthChildIndex];
										let isHtmlElement = secondDepthChild instanceof HTMLElement;

										if (isHtmlElement
												&& secondDepthChild.classList
														.contains("teamFlag")) {
											let flag = getFlagByTeamName(hometeamName);

											if (flag) {
												secondDepthChild.setAttribute(
														"src", flag);
												break;
											}
											break;
										}
									}
								} else if (isAwayteamFlag) {
									let rowElementChildChildren = rowElementChild.children;

									for ( let secondDepthChildIndex in rowElementChildChildren) {
										let secondDepthChild = rowElementChildChildren[secondDepthChildIndex];
										let isHtmlElement = secondDepthChild instanceof HTMLElement;

										if (isHtmlElement
												&& secondDepthChild.classList
														.contains("teamFlag")) {
											let flag = getFlagByTeamName(awayteamName);

											if (flag) {
												secondDepthChild.setAttribute(
														"src", flag);
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	function addFlagSrcToUserBetsPage() {
		var matchRowElements = document.getElementsByClassName("matchRow");

		for ( let matchRowElementIndex in matchRowElements) {
			let matchRowElement = matchRowElements[matchRowElementIndex];
			let isHtmlElement = matchRowElement instanceof HTMLElement;

			if (isHtmlElement) {
				let hometeamName = matchRowElement
						.getElementsByClassName("hometeamName")[0].textContent;
				let awayteamName = matchRowElement
						.getElementsByClassName("awayteamName")[0].textContent;

				let hometeamFlagElement = matchRowElement
						.getElementsByClassName("hometeamFlag")[0];
				let awayteamFlagElement = matchRowElement
						.getElementsByClassName("awayteamFlag")[0];

				let hometeamFlagSrc = getFlagByTeamName(hometeamName);
				let awayteamFlagSrc = getFlagByTeamName(awayteamName);

				if (hometeamFlagSrc) {
					hometeamFlagElement.getElementsByClassName("teamFlag")[0]
							.setAttribute("src", hometeamFlagSrc);
				}

				if (awayteamFlagSrc) {
					awayteamFlagElement.getElementsByClassName("teamFlag")[0]
							.setAttribute("src", awayteamFlagSrc);
				}
			}
		}
	}

	function addFlagSrcToPlayOffsPage() {
		var labelElements = document.getElementsByClassName("label");

		for ( let labelElementIndex in labelElements) {
			let labelElement = labelElements[labelElementIndex];
			let isHtmlElement = labelElement instanceof HTMLElement;

			if (isHtmlElement) {
				let teamName = labelElement.textContent.trim();

				if (!!teamName) {
					let teamFlagSrc = getFlagByTeamName(teamName);

					labelElement.getElementsByClassName("teamFlag")[0]
							.setAttribute("src", teamFlagSrc);
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