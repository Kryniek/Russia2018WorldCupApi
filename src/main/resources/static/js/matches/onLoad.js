var onLoad = function() {
	(function init() {
		var cardHeaderIdElements = document
				.getElementsByClassName("cardHeaderId");

		for ( let cardHeaderIdElementIndex in cardHeaderIdElements) {
			let cardHeaderIdElement = cardHeaderIdElements[cardHeaderIdElementIndex];
			let isHtmlElement = cardHeaderIdElement instanceof HTMLElement;

			if (isHtmlElement) {
				let cardHeaderId = cardHeaderIdElement.textContent;

				let cardElementChildren = cardHeaderIdElement.parentElement.children;

				for ( let cardElementChildIndex in cardElementChildren) {
					let cardElementChild = cardElementChildren[cardElementChildIndex];
					let isHtmlElement = cardElementChild instanceof HTMLElement;

					let headerDateId = "header-" + cardHeaderId;
					let bodyDateId = "body-" + cardHeaderId;

					if (isHtmlElement) {
						let isCardHeaderElement = cardElementChild.classList
								.contains("card-header");

						if (isCardHeaderElement) {
							decorateCardHeader(cardElementChild, cardHeaderId,
									headerDateId, bodyDateId);
						}

						let isBodyParentElement = cardElementChild.classList
								.contains("collapse");

						if (isBodyParentElement) {
							decorateCardBody(cardElementChild, headerDateId,
									bodyDateId);
						}
					}
				}
			}
		}
	})();

	function decorateCardHeader(cardHeaderElement, cardHeaderId, headerDateId,
			bodyDateId) {
		cardHeaderElement.setAttribute("id", headerDateId);

		var cardHeaderElementChildren = cardHeaderElement.children;

		for ( let cardHeaderElementChildIndex in cardHeaderElementChildren) {
			let cardHeaderElementChild = cardHeaderElementChildren[cardHeaderElementChildIndex];
			let isHtmlElement = cardHeaderElementChild instanceof HTMLElement;

			if (isHtmlElement && cardHeaderElementChild.tagName === "H5") {
				let h5ElementChildren = cardHeaderElementChild.children;

				for ( let h5ElementChildIndex in h5ElementChildren) {
					let h5ElementChild = h5ElementChildren[h5ElementChildIndex];
					let isHtmlElement = h5ElementChild instanceof HTMLElement;

					if (isHtmlElement && h5ElementChild.text === cardHeaderId) {
						h5ElementChild.setAttribute("href", "#" + bodyDateId);
						h5ElementChild
								.setAttribute("aria-controls", bodyDateId);
					}
				}
			}
		}
	}

	function decorateCardBody(cardHeaderElement, headerDateId, bodyDateId) {
		cardHeaderElement.setAttribute("id", bodyDateId);
		cardHeaderElement.setAttribute("aria-labelledby", headerDateId);
	}
};