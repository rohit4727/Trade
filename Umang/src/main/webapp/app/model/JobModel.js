Ext.define('ui.model.JobModel', {
	extend: 'ui.model.Base'
    , idProperty: 'id'
    , fields: [
        { name: 'id' }
        , { name: 'jobName' }
        , { name: 'batchFilePath' }
        , { name: 'status', defaultValue: 0, mapping: 'runFrequency'}
        , { name: 'scheduleDate'}
        , { name: 'runFrequency', defaultValue: 1, type: 'int', convert: function (v, rec) {
            return v == '1' ? 1 : (v == true ? 1 : 0);
        	} 
        }        
        , { name: 'date', mapping: 'scheduleDate', convert: function (v, rec) {
	            if (v) {
	                v = Ext.isDate(v) ? v : (Ext.Date.parse(v, 'Y-m-dTH:i:s') || Ext.Date.parse(v, 'Y-m-d'));
	                v = Ext.Date.format(v, 'Y-m-d');
	            }
	
	            return v;
        	} 
        }
        , {
            name: 'time', mapping: 'scheduleDate', convert: function (v, rec) {
                if (v) {
                    v = Ext.isDate(v) ? v : (Ext.Date.parse(v, 'Y-m-dTH:i:s') || Ext.Date.parse(v, 'Y-m-d'));
                    v = Ext.Date.format(v, 'H:i');
                }

                return v;
            }
        }
    ]
    , proxy: {
        type: 'ajax'
    	, reader: {
            type: 'json'
    	}
        , api: {
            create: '/scheduleOrRunJob'
            , update: {
            	url: '/schedule_job_update'
            }
            , destroy: {
            	url: '/schedule_job_delete'
            }
        }
    }
});