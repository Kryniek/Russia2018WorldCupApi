//http://www.aropupu.fi/bracket/
var initBracket = function() {
	(function init() {
		$('.container').bracket(
				{
					teamWidth : 140,
					scoreWidth : 43,
					matchMargin : 56,
					roundMargin : 86,
					init : getBracketInitialData(),
					decorator : {
						edit : function() {
						}, // must be
						render : function(container, data, score, state) {
							if (data) {
								container.append('<img class="teamFlag" /> ')
										.append(data.name);
							}
							return;
						}
					}
				})
	})();

	function getBracketInitialData() {
		return {
			teams : getTeams(),
			results : getResults()
		};
	}

	function getTeams() {
		return [ [ {
			name : "Urugwaj"
		}, {
			name : "Portugalia"
		} ], [ {
			name : "Francja"
		}, {
			name : "Polska"
		} ], [ {
			name : "Hiszpania"
		}, {
			name : "Rosja"
		} ], [ {
			name : "Nigeria"
		}, {
			name : "Argentyna"
		} ], [ {
			name : "Arabia Saudyjska"
		}, {
			name : "Egipt"
		} ], [ {
			name : "Peru"
		}, {
			name : "Australia"
		} ], [ {
			name : "Serbia"
		}, {
			name : "Kostaryka"
		} ], [ {
			name : "Iran"
		}, {
			name : "Maroko"
		} ] ];
	}

	function getResults() {
		var firstStageResults = [ [ 2, 1 ], [ 3, 4 ], [ 0, 2 ], [ 1, 0 ],
				[ 2, 1 ], [ 3, 4 ], [ 0, 2 ], [ 1, 0 ] ];

		var secondStageResults = [ [ 4, 6 ], [ 2, 1 ], [ 4, 6 ], [ 2, 1 ] ];

		var thirdStageResults = [ [ 2, 1 ], [ 3, 4 ] ];

		var finalResult = [ 2, 1 ];

		var thirdPlaceResult = [ 3, 4 ];

		var finalStageResults = [ finalResult, thirdPlaceResult ];

		return [ firstStageResults, secondStageResults, thirdStageResults,
				finalStageResults ];
	}
};