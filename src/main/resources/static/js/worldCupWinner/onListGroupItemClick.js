var onListGroupItemClick = function(mouseEvent) {
	var LIST_GROUP_ITEM_SUCCESS_CLASS = "list-group-item-success";
	
	(function init() {
		removeSuccessClassFromAllListGroupItems();

		addSuccessClass();
		
		addChoosenTeamToLocalStorage(mouseEvent);
	})();
	
	function removeSuccessClassFromAllListGroupItems(){
		var listGroupItems = mouseEvent.srcElement.parentElement.children;
		
		for(let listGroupItemIndex in listGroupItems){
			let listGroupItem = listGroupItems[listGroupItemIndex];
			let isHtmlElement = listGroupItem instanceof HTMLElement;
			
			if(isHtmlElement){
				let listGroupItemClasses = listGroupItem.classList;
				
				if(listGroupItemClasses.contains(LIST_GROUP_ITEM_SUCCESS_CLASS)){
					listGroupItemClasses.remove(LIST_GROUP_ITEM_SUCCESS_CLASS);
				}
			}
		}
	};
	
	function addSuccessClass(){
		mouseEvent.srcElement.classList.add(LIST_GROUP_ITEM_SUCCESS_CLASS);
	};
	
	function addChoosenTeamToLocalStorage(mouseEvent){
		window.localStorage.setItem(WORLD_CUP_WINNER_CHOSEN_TEAM_LOCAL_STORAGE, mouseEvent.srcElement.textContent.trim());
	};
};