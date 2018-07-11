Ext.define('ui.model.JobModel', {
	extend: 'ui.model.Base'
    , idProperty: 'id'
    , fields: [
        { name: 'id' }
        , { name: 'jobName' }
        , { name: 'batchFilePath' }
        , { name: 'runFrequency' }
        , { name: 'date' }
        , { name: 'time' } 
        , { name: 'status'}
        , { name: 'scheduleDate'}
    ]
    , proxy: {
        type: 'ajax'
        , api: {
            create: '/scheduleJob'
            , update: {
            	url: '/schedule_job_update'
            }
            , destroy: {
            	url: '/schedule_job_delete'
            }
        }
    }
});