var Fluxxor = require('fluxxor'),
    React = require('react'),
    Product = require('./product.jsx'),
    FluxMixin = Fluxxor.FluxMixin(React),
    StoreWatchMixin = Fluxxor.StoreWatchMixin,

    Promotions = React.createClass({
    
    render: function() {
      return <div className="promotions-container text-center">
               <h2>Promotions Container</h2>
               <div className="row">
               <Product/>
               <Product/>
               <Product/>
               <Product/>
             </div>
          </div>;
      }
    });

module.exports = Promotions;