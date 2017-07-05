function DataService() {
	var restUrl = "//localhost:8080/api/";
	
	this.loadPromotions = loadPromotions;
	this.loadUserPromotions = loadUserPromotions;
	
	function loadPromotions (success, failure) {
	  $.get(restUrl + "promotions").done(function( data ) {
		  alert( "Data Loaded: " + data );
		  success(data);
	  }).fail(function (error) {
		  failure(error);
	  });
	}
	
	function loadUserPromotions(userId, success, failure) {
	  $.get(restUrl + "promotions/" + userId).done(function( data ) {
	    alert( "Data Loaded: " + data );
	      success(data);
		}).fail(function (error) {
		  failure(error);
	  });
	}
}

module.exports = new DataService();