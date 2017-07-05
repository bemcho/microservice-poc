var Fluxxor = require('fluxxor'),
    React = require('react'),

    Scanner = React.createClass({
      code: "",
	  lastTime: 0,
	  
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
        return  <div>
          <input id="code" autoFocus onKeyPress={this.collectKey} />
          </div>;
      }
    });

module.exports = Scanner;