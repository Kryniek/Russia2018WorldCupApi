var onLoad = function() {
	(function init() {
		var officialRankingElement = document.getElementById("officialRanking");
		var listGroupItemElements = officialRankingElement
				.getElementsByClassName("list-group-item");

		for ( let listGroupItemElementIndex in listGroupItemElements) {
			let listGroupItemElement = listGroupItemElements[listGroupItemElementIndex];
			let isHtmlElement = listGroupItemElement instanceof HTMLElement;

			if (isHtmlElement) {
				let indexElement = listGroupItemElement
						.getElementsByClassName("index")[0];

				if (indexElement.textContent === "1"
						|| indexElement.textContent === "2"
						|| indexElement.textContent === "3") {
					listGroupItemElement.classList
							.add("list-group-item-success");
				}

				if (indexElement.textContent === "3") {
					listGroupItemElement.classList.add("mb-4");
				}
			}
		}
	})();
};