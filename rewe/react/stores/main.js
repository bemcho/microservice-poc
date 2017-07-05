'use strict';

var Fluxxor = require('fluxxor'),
    Constants = require('../constants').Main;

var MainStore = Fluxxor.createStore({
  initialize: function() {
    this.loading = false;
    this.error = null;
    this.promotions = {};

    this.bindActions(
      Constants.LOAD_PROMOTIONS, this.onLoadPromotions,
      Constants.LOAD_PROMOTIONS_SUCCESS, this.onLoadPromotionsSuccess,
      Constants.LOAD_PROMOTIONS_FAIL, this.onLoadPromotionsFail
    );
  },
  
  onLoadPromotions: function() {
    this.loading = true;
    this.emit("change");
  },

  onLoadPromotionsSuccess: function(payload) {
    this.loading = false;
    this.error = null;

    this.promotions = payload;
    this.emit("change");
  },

  onLoadPromotionsFail: function(payload) {
    this.loading = false;
    this.error = payload.error;
    this.emit("change");
  }
});

module.exports = MainStore;
