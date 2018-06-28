var onLoad = function() {
	(function init() {
		decorateTeamContainerElements();
	})();

	function decorateTeamContainerElements() {
		var teamContainerElements = document
				.getElementsByClassName("teamContainer");

		for ( let teamContainerElementIndex in teamContainerElements) {
			let teamContainerElement = teamContainerElements[teamContainerElementIndex];
			let isHtmlElement = teamContainerElement instanceof HTMLElement;

			if (isHtmlElement) {
				teamContainerElement.classList.add("card");
			}
		}
	}
};