(function() {
	var listGroupItems = document.getElementsByClassName("list-group-item");

	for ( let listGroupItemIndex in listGroupItems) {
		let listGroupItem = listGroupItems[listGroupItemIndex];
		let isHtmlElement = listGroupItem instanceof HTMLElement;

		if (isHtmlElement) {
			listGroupItem.addEventListener("click", onListGroupItemClick);
		}
	}

	document.getElementById("forwardButton").addEventListener("click",
			onForwardButtonClick);
})();