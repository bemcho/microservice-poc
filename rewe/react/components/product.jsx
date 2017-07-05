var Fluxxor = require('fluxxor'),
    React = require('react'),
    FluxMixin = Fluxxor.FluxMixin(React),
    StoreWatchMixin = Fluxxor.StoreWatchMixin,

    Product = React.createClass({
    
      render: function() {
        return <div className="col-md-6 col-xs-12 text-center">
        <div className="thumbnail">
          <h1>Product Item</h1>
          <img className="product-img" src="/public/img/banana.jpg" alt="water" />
          <div>
          	<h4>Product title</h4>
          	<div>description</div>
          </div> 
          <div className="pricebox"> 
	          <div className="discount">
	          	<strong>15% gespart</strong>
	          </div>
	          <div className="prize">
	          	<strong>1.50</strong>
	          </div>
           </div>
             </div>
          </div>;
      }
    });

module.exports = Product;