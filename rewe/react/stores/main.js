'use strict';

var Fluxxor = require('fluxxor'),
    Constants = require('../constants').Main;

var MainStore = Fluxxor.createStore({
  initialize: function() {
    this.total = 0;

    this.bindActions(
      Constants.SEND, this.sendMessage,
      Constants.SEND_ERROR, this.sendMessageError,
      Constants.UPDATE, this.updateMessages,
      Constants.UPDATE_ERROR, this.updateMessagesError
    );
  },

  sendMessage: function(params) {

  },

  sendMessageError: function(data) {
    // console.log(data);
  },

  updateMessages: function(params) {
    // In this very simple example, this is called in order to instantiate
    // the internal React update

    this.total++;
    this.emit('change');
  },

  updateMessagesError: function(data) {
    // console.log(data);
  },
  
  getPromotions: function () {
	  $.get( "promotions")
	  .done(function( data ) {
	    alert( "Data Loaded: " + data );
	  });
  }
});

module.exports = MainStore;
