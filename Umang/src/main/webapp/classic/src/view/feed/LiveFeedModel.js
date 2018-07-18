/**
 * Author: Umang Goel
 * This class is the view model for the Main view of the application.
 */
Ext.define('ui.view.feed.LiveFeedModel', {
    extend: 'Ext.app.ViewModel'
    , alias: 'viewmodel.livefeed'
	, links: {
        
    }
    , constructor: function (config) {
        config.stores = this.initStores();

        this.callParent(arguments);
    }
    , initStores: function () {
        return {            
            /*liveFeedListStore: {
                model: 'ui.model.LiveFeedModel'
                , autoLoad: false
                , storeId: 'liveFeedListStore'                
            	, proxy: {
                    type: 'ajax'
                    , api: {
                        read: '/liveFeedlist'
                    }
                }                
            }*/
        }
    }
    });
