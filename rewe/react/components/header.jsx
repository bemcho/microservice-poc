var Fluxxor = require('fluxxor'),
    React = require('react'),
    FluxMixin = Fluxxor.FluxMixin(React),
    StoreWatchMixin = Fluxxor.StoreWatchMixin,

    Header = React.createClass({
    
      render: function() {
        return <div>
          <a href="https://www.rewe.de/">
            <img className="rs-logo" src="/public/img/logo.png" alt="REWE Dein Supermarkt."/>
          </a>
          </div>;
      }
    });

module.exports = Header;