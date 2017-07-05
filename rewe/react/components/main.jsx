var Fluxxor = require('fluxxor'),
    React = require('react'),
    Header = require('./header.jsx'),
    Promotions = require('./promotions.jsx'),
    FluxMixin = Fluxxor.FluxMixin(React),
    StoreWatchMixin = Fluxxor.StoreWatchMixin,

    Main = React.createClass({
      mixins: [FluxMixin, StoreWatchMixin("main")],
      code: "",
	  lastTime: 0,
	  
      getStateFromFlux: function() {
        var store = this.getFlux().store("main");
        return {
          total: store.total
        };
      },

      sendMessage: function() {
        this.getFlux().actions.main.sendMessage();
      },
      
      clearCode: function () {
		this.code = "";
	  },
      
      requestPromotions: function (code) {
        alert(code);
      },
      
      collectKey: function (event) {
      	var time = new Date().getTime();

		if (time - this.lastTime > 1000) {
			this.clearCode();
		}

		this.lastTime = time;

		this.code += String.fromCharCode(event.charCode);

		if (this.code.length >= 13) {
			this.requestPromotions(this.code); //send request
			this.clearCode();
		}
      },
     

      render: function() {
        return <content className="principal">
        <Header/>
        <Promotions/>
          <div>
          <input id="code" autoFocus onKeyPress={this.collectKey} />
          </div>
        </content>;
      }
    });

module.exports = Main;
