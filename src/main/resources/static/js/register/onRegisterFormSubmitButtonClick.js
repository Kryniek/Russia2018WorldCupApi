var onRegisterFormSubmitButtonClick = function() {
	(function init() {
		var isLoginValid = isLoginInputValid();

		if (isLoginValid) {
			var isPassordValid = isPasswordInputValid();

			if (isPassordValid) {
				var isNameValid = isNameInputValid();

				if (isNameValid) {
					var isSurnameValid = isSurnameInputValid();

					if (isSurnameValid) {
						document.registerForm.submit();
					}
				}
			}
		}
	})();

	function isLoginInputValid() {
		var loginInput = document.getElementById("username");
		var loginInputText = loginInput.value;

		if (!loginInputText) {
			showErrorMessage("Nieprawidłowy login");
			return false;
		}

		if (loginInputText.length < 5) {
			showErrorMessage("Login musi być dłuższy niż 4 znaki");
			return false;
		}

		return true;
	}

	function isPasswordInputValid() {
		var passwordInput = document.getElementById("password");
		var passwordInputText = passwordInput.value;

		if (!passwordInputText) {
			showErrorMessage("Nieprawidłowe hasło");
			return false;
		}

		if (passwordInputText.length < 6) {
			showErrorMessage("Hasło musi być dłuższe niż 5 znaków");
			return false;
		}

		let itHasANumber = false;
		let itHasACharacter = false;

		for ( let characterIndex in passwordInputText) {
			let character = passwordInputText[characterIndex];

			if (!isNaN(character)) {
				itHasANumber = true;
				continue;
			} else {
				itHasACharacter = true;
				continue;
			}
		}

		if (!itHasANumber || !itHasACharacter) {
			showErrorMessage("Hasło musi zawierać przynajmniej jedną literę i jedną cyfrę.");
			return false;
		}

		return true;
	}

	function isNameInputValid() {
		var nameInput = document.getElementById("name");
		var nameInputText = nameInput.value;

		if (!nameInputText) {
			showErrorMessage("Nieprawidłowe imię");
			return false;
		}

		if (nameInputText.length < 3) {
			showErrorMessage("Imię musi być dłuższe niż 2 znaki");
			return false;
		}

		return true;
	}

	function isSurnameInputValid() {
		var surnameInput = document.getElementById("surname");
		var surnameInputText = surnameInput.value;

		if (!surnameInputText) {
			showErrorMessage("Nieprawidłowe nazwisko");
			return false;
		}

		if (surnameInputText.length < 3) {
			showErrorMessage("Nazwisko musi być dłuższe niż 2 znaki");
			return false;
		}

		return true;
	}

	function showErrorMessage(message) {
		var errorAlertElement = document.getElementById("errorAlert");

		errorAlertElement.textContent = message;
		errorAlertElement.style.setProperty("display", "block");
	}
};