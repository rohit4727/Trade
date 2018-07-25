/**
 * Author: Umang Goel
 * This class is the view model for the Main view of the application.
 */
Ext.define('ui.view.feed.LiveFeedViewModel', {
    extend: 'Ext.app.ViewModel'
    , alias: 'viewmodel.livefeed'
	, links: {
		liveFeedListFilter: {
            type: 'ui.model.LiveFeedFilterModel'
             , create: true
        },
        liveFeedListModel: {
            type: 'ui.model.LiveFeedModel'
             , create: true
        }
    }
    , constructor: function (config) {
        config.stores = this.initStores();

        this.callParent(arguments);
    }
    
    //initialization of view stores
    , initStores: function () {
        return {            
            liveFeedListStore: {
                model: 'ui.model.LiveFeedModel'
                , autoLoad: false
                , storeId: 'liveFeedListStore'
            	, proxy: {
                    type: 'ajax'
                    , api: {
                        read: '/TradeApp/getLiveFeedData'
                    }
                }                
            },
            liveFeedSecurityStore: {
                fields: ['security']
                , autoLoad: false
                , storeId: 'liveFeedSecurityStore'
            	, proxy: {
                    type: 'ajax'
                    , api: {
                        read: '/TradeApp/getSecurityList'
                    }
                }                
            }
        }
    }
    });
