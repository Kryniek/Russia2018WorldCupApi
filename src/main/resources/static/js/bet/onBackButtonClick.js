var onBackButtonClick = function() {
	(function init() {
		var href = location.origin;
		var pathVariables = location.search;
		var parentViewPathUriStartIndex = pathVariables
				.indexOf(PARENT_VIEW_PATH_URI);
		var parentViewPathUriEndIndex = parentViewPathUriStartIndex
				+ +PARENT_VIEW_PATH_URI.length + 1;

		if (pathVariables.indexOf("&") === -1) {
			href += pathVariables.substr(parentViewPathUriEndIndex);
		}

		window.location.href = href;
	})();
};