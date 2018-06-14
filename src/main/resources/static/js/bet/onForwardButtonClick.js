var onForwardButtonClick = function() {
	(function init() {
		var isFormValid = validCustomNumberInputs(isNaturalNumber);

		if (isFormValid) {
			sendRequestToServer();
		} else {
			document.getElementById("wrongDataInInputs").style.display = "block";
		}
	})();

	function validCustomNumberInputs(isNaturalNumberFunc) {
		var isFormValid = true;
		var inputs = document.getElementsByClassName("customNumberInput");

		for ( let inputIndex in inputs) {
			let input = inputs[inputIndex];
			let isHtmlElement = input instanceof HTMLElement;

			if (isHtmlElement) {
				let isValidNumber = isNaturalNumberFunc(input.value);

				if (!isValidNumber) {
					isFormValid = false;
					break;
				}
			}
		}

		return isFormValid;
	}

	function isNaturalNumber(n) {
		n = n.toString();
		var n1 = Math.abs(n), n2 = parseInt(n, 10);

		return !isNaN(n1) && n2 === n1 && n1.toString() === n;
	}

	function sendRequestToServer() {
		var betValue = {
			matchId : document.getElementById("getMatchId").textContent,
			hometeamScore : document.getElementById("hometeamScore").value,
			awayteamScore : document.getElementById("awayteamScore").value
		};

		document.betMatchForm.action += betValue.matchId + "/"
				+ betValue.hometeamScore + "/" + betValue.awayteamScore;
		document.betMatchForm.submit();
	}
};