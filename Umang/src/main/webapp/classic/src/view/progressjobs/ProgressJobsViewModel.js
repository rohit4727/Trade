/**
 * Author: Umang Goel
 * This class is the view model for the Main view of the application.
 */
Ext.define('ui.view.progressjobs.ProgressJobsViewModel', {
    extend: 'Ext.app.ViewModel'
    , alias: 'viewmodel.progressjobs'
	, links: {		
        progressJobsListModel: {
            type: 'ui.model.ProgressJobsModel'
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
        	progressJobsListStore: {
                model: 'ui.model.ProgressJobsModel'
                , autoLoad: true
                , storeId: 'progressJobsListStore'                
            	, proxy: {
                    type: 'ajax'
                    , api: {
                        read: '/TradeApp/getScheduleJobProgressList'
                    }
                }                
            }
        }
    }
});
