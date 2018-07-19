/**
 * Author: Umang Goel
 * This class is the view model for the Main view of the application.
 */
Ext.define('ui.view.completedjobs.CompletedJobsViewModel', {
    extend: 'Ext.app.ViewModel'
    , alias: 'viewmodel.completedjobs'
	, links: {		
        completedJobsListModel: {
            type: 'ui.model.CompletedJobsModel'
             , create: true
        }
    }
    , constructor: function (config) {
        config.stores = this.initStores();

        this.callParent(arguments);
    }
    , initStores: function () {
        return {            
            completedJobsListStore: {
                model: 'ui.model.CompletedJobsModel'
                , autoLoad: false
                , storeId: 'completedJobsListStore'                
            	, proxy: {
                    type: 'ajax'
                    , api: {
                        read: '/TradeApp/getCompletedJobsData'
                    }
                }                
            }
        }
    }
    });
