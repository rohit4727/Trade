/**
 * Author: Umang Goel
 * This class is the view model for the Main view of the application.
 */
Ext.define('ui.view.main.MainModel', {
    extend: 'Ext.app.ViewModel'
    , alias: 'viewmodel.main'
	, links: {
        jobItem: {
            type: 'ui.model.JobModel'
             , create: true
        }
    }
    , constructor: function (config) {
        config.stores = this.initStores();

        this.callParent(arguments);
    }
    , initStores: function () {
        return {            
            scheduleJobListStore: {
                model: 'ui.model.JobModel'
                , autoLoad: true
                , storeId: 'scheduleJobListStore'                
            	, proxy: {
                    type: 'ajax'
                    , api: {
                        read: '/getAllJobScheduleDetails'
                    }
                }                
            },
            scheduleListChartStore:{
                storeId: 'scheduleListChartStore' 
            	, fields: ['type', 'count' ]
            	, data:[]	            
            }
        }
    }
    });
