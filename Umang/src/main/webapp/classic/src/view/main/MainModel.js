/**
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
            scheduleJobList: {
                model: 'ui.model.JobModel'
                , autoLoad: false
                , proxy: {
                    type: 'ajax'
                    , api: {
                        read: {
                            url: '/scheduled_job_list'
                        }
                    }
                }
            }
        }
    }
    });
