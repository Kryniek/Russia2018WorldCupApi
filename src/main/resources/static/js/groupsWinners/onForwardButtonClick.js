var onForwardButtonClick = function() {
	(function init() {
		var isFormValid = allSelectsValuesHasBeenSet();

		if (isFormValid) {
			sendRequestToServer();
		} else {
			document.getElementById("notAllTeamsSelectedAlert").style.display = "block";
		}
	})();

	function allSelectsValuesHasBeenSet() {
		var isFormValid = true;
		var notValidOptionTexts = [ "Pierwsze miejsce", "Drugie miejsce" ];
		var selectElements = document.getElementsByClassName("customSelect");

		for ( let selectElementIndex in selectElements) {
			let selectElement = selectElements[selectElementIndex];
			let isHtmlElement = selectElement instanceof HTMLElement;

			if (isHtmlElement) {
				let selectedOption = selectElement.selectedOptions[0];
				let isSelectedOptionNotValid = notValidOptionTexts
						.includes(selectedOption.text);
				if (isSelectedOptionNotValid) {
					isFormValid = false;
					break;
				}
			}
		}

		return isFormValid;
	}

	function sendRequestToServer() {
		var betValues = getBetValues()[0];
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            url: "/bets/groups",
            data: JSON.stringify(betValues),
            success: function (result) {
                console.log(result);
                // do what ever you want with data
            }
        });
		// document.groupsWinnersForm.submit();
	}

	function getBetValues() {
		var betValues = [];

		var selectElements = document.getElementsByClassName("customSelect");

		for ( let selectElementIndex in selectElements) {
			let selectElement = selectElements[selectElementIndex];
			let isHtmlElement = selectElement instanceof HTMLElement;

			if (isHtmlElement) {
				let selectedOption = selectElement.selectedOptions[0];
				let selectedOptionText = selectedOption.text;
				let betValue = {
					groupName : "",
					firstPlace : "",
					secondPlace : ""
				};

				let groupName = getGroupNameFromHiddenP(selectedOption);

				betValue.groupName = groupName;

				let placaName = getPlaceNameFromHiddenOption(selectedOption);

				if (placaName === "Pierwsze miejsce") {
					betValue.firstPlace = selectedOptionText;
				} else if (placaName === "Drugie miejsce") {
					betValue.secondPlace = selectedOptionText;
				}

				if (!betValues.length) {
					betValues.push(betValue);
					continue;
				}

				let betValueIndex = betValues.map(function(betValue) {
					return betValue.groupName;
				}).indexOf(betValue.groupName);

				if (betValueIndex >= 0) {
					let firstPlace = betValue.firstPlace;
					let secondPlace = betValue.secondPlace;

					if (!!firstPlace) {
						betValues[betValueIndex].firstPlace = firstPlace;
						continue;
					} else if (!!secondPlace) {
						betValues[betValueIndex].secondPlace = secondPlace;
						continue;
					}
				} else {
					betValues.push(betValue);
					continue;
				}
			}
		}

		return betValues;
	}

	function getGroupNameFromHiddenP(selectedOption) {
		var groupName = null;
		var parentDivElement = selectedOption.parentElement.parentElement.parentElement;
		var parentDivElementChildren = parentDivElement.children;

		for ( let parentDivElementChildIndex in parentDivElementChildren) {
			let parentDivElementChild = parentDivElementChildren[parentDivElementChildIndex];
			let isHtmlElement = parentDivElementChild instanceof HTMLElement;

			if (isHtmlElement && parentDivElementChild.hidden
					&& parentDivElementChild.classList.contains("groupName")) {
				groupName = parentDivElementChild.textContent.trim();
				break;
			}
		}

		return groupName;
	}

	function getPlaceNameFromHiddenOption(selectedOption) {
		var optionPlaceName = null;
		var options = selectedOption.parentElement.options;

		for ( let optionIndex in options) {
			let option = options[optionIndex];
			let isHtmlElement = option instanceof HTMLElement;

			if (isHtmlElement && option.hidden) {
				optionPlaceName = option.text;
				break;
			}
		}

		return optionPlaceName;
	}
};