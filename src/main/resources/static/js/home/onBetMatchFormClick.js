var onBetMatchFormClick = function() {
	(function init() {
		var matchIdElements = document.getElementsByClassName("getMatchId");

		for ( let matchIdElementIndex in matchIdElements) {
			let matchIdElement = matchIdElements[matchIdElementIndex];
			let isHtmlElement = matchIdElement instanceof HTMLElement;

			if (isHtmlElement) {
				let matchId = matchIdElement.textContent;
				let betMatchFormName = "betMatchForm-" + matchId;
				let matchIdElementParentChildren = matchIdElement.parentElement.children;

				for ( let matchIdElementParentChildIndex in matchIdElementParentChildren) {
					let matchIdElementParentChild = matchIdElementParentChildren[matchIdElementParentChildIndex];
					let isHtmlElement = matchIdElementParentChild instanceof HTMLElement;

					if (isHtmlElement
							&& matchIdElementParentChild.tagName === 'BUTTON'
							&& matchIdElementParentChild.classList
									.contains('betMatchFormButton')) {
						matchIdElementParentChild
								.setAttribute("value", matchId);
						matchIdElementParentChild.addEventListener("click",
								onBetMatchFormButtonClick);
					}
				}
			}
		}
	})();

	function onBetMatchFormButtonClick() {
		var me = this;
		var matchId = me.getAttribute("value");
		var betMatchFormName = "betMatchForm-" + matchId;
		var betMatchForm = document.getElementsByName(betMatchFormName)[0];

		betMatchForm.action += matchId;
		betMatchForm.submit();
	}
};