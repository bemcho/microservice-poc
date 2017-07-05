'use strict';

var Constants = require('../constants').Main;
var DataService = require('../services/dataService');

// All actions to have the simple signature of (type, params) for consistency
// with server-side event delegation

var MainActions = {
  loadPromotions: function(params) {
    this.dispatch(Constants.LOAD_PROMOTIONS, params);

    DataService.loadPromotions(function(promos) {
      this.dispatch(Constants.LOAD_PROMOTIONS_SUCCESS, {promos: promos});
    }.bind(this), function(error) {
      this.dispatch(Constants.LOAD_PROMOTIONS_FAIL, {error: error});
    }.bind(this));
  },

  loadUserPromotions: function(params) {    
    this.dispatch(Constants.LOAD_USER_PROMOTIONS, params);

    DataService.loadUserPromotions(function(userPromos) {
      this.dispatch(Constants.LOAD_USER_PROMOTIONS_SUCCESS, {userPromos: userPromos});
    }.bind(this), function(error) {
      this.dispatch(Constants.LOAD_USER_PROMOTIONS_FAIL, {error: error});
    }.bind(this));
  }
};

module.exports = MainActions;
