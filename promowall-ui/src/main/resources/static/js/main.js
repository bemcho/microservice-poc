var code = "";
var lastTime = 0;

function clearCode() {
	code = "";
}

function clearPersonalPromotions() {
	$('#personal-promotions').html('');
}

function buildPromotions(promos) {
	var personalPromotions = [];

	for (var i = 0; i < promos.length; i++) {
		personalPromotions.push($('<div></div>').html(
				'<div class="col-md-3 col-xs-12 text-center">'
						+ '<div class="thumbnail product">' + '<h5 class="product-title">'
						+ promos[i].product.name
						+ '</h5>'
						+ '<img src="img/'
						+ promos[i].product.url
						+ '" alt="'
						+ promos[i].product.name
						+ '"/>'
						+ '<div class="description">'
						+ promos[i].description
						+ '</div>'
						+ '<div class="prices">'
						+ '<div class="pricebox">'
						+ '<div class="discount">'
						+ '<strong>'
						+ promos[i].product.price
						+ '&#37; gespart</strong>'
						+ '</div>'
						+ '<div class="price"><small>&euro; '
						+ promos[i].discountedPrice
						+ '</small></div>'
						+ '</div>' + '</div>' + '</div>' + '</div>'));
	}

	clearPersonalPromotions();
	$('#personal-promotions').append(personalPromotions);
}

function requestPromotions(code) {
	var url = 'http://localhost:9000/personal-promotions/' + code;

	$.get(url).done(function(data) {
		buildPromotions(data);
	}).fail(function(error) {
		alert('This is not valid card');
	});
}

function collectKey(event) {
	var time = new Date().getTime();

	if (time - lastTime > 1000) {
		clearCode();
	}

	lastTime = time;

	code += String.fromCharCode(event.charCode);

	if (code.length >= 13) {
		requestPromotions(code);
		clearCode();
	}
}
