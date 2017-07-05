var Fluxxor = require('fluxxor'),
    React = require('react'),
    Header = require('./header.jsx'),
    Promotions = require('./promotions.jsx'),
    Scanner = require('./scanner.jsx'),
    FluxMixin = Fluxxor.FluxMixin(React),
    StoreWatchMixin = Fluxxor.StoreWatchMixin,

    Main = React.createClass({
      mixins: [FluxMixin, StoreWatchMixin("main")],

      getStateFromFlux: function() {
        var store = this.getFlux().store("main");
        return {
          promotions: store.promotions
        };
      },

      componentDidMount: function() {
        this.getFlux().actions.main.loadPromotions();
      },
     
      render: function() {
        return <content className="principal">
        <Header/>
        <Promotions flux={this.getFlux()}/>
        <Scanner/>
        </content>;
      }
    });

module.exports = Main;
