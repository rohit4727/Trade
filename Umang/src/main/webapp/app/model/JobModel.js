Ext.define('ui.model.JobModel', {
	extend: 'ui.model.Base'
    , idProperty: 'guid'
    , fields: [
        { name: 'guid' }
        , { name: 'job_name' }
        , { name: 'path' }
        , { name: 'run_frequency' }
        , { name: 'date' }
        , { name: 'time' }        
    ]
    , proxy: {
        type: 'ajax'
        , api: {
            create: '/run_schedule_job'
            , update: {
            	url: '/schedule_job_update'
            }
            , destroy: {
            	url: '/schedule_job_delete'
            }
        }
    }
});