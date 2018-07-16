Ext.define('ui.model.JobModel', {
	extend: 'ui.model.Base'
    , idProperty: 'id'
    , fields: [
        { name: 'id' }
        , { name: 'jobName' }
        , { name: 'batchFilePath' }        
        , { name: 'scheduleDate'}
        , { name: 'status', type: 'int'}
        , { name: 'date', convert: function (v, rec) {        	
            if (v) {
                v = Ext.isDate(v) ? v : new Date(v);
                v = Ext.Date.format(v, 'Y-m-d');
            }

            return v;
    	}}
        , { name: 'time', convert: function (v, rec) {        	
            if (v) {
                v = Ext.isDate(v) ? v : new Date(v);
                v = Ext.Date.format(v, 'H:i');
            }

            return v;
    	}}        
        , { name: 'runFrequency', defaultValue: 1, type: 'int', convert: function (v, rec) {
            return v == '1' ? 1 : (v == true ? 1 : 0);
        	} 
        }
        , { name: 'displayDate', convert: function (v, rec) {
        		v = rec.get('scheduleDate');
	            if (v) {
	                v = Ext.isDate(v) ? v : new Date(v);
	                v = Ext.Date.format(v, 'Y-m-d');
	            }
	
	            return v;
        	} 
        }
        , {
            name: 'displayTime', convert: function (v, rec) {	
                v = new Date(rec.get('scheduleDate'));                
                v = new Date(v.getTime() + (v.getTimezoneOffset() * 60000));
                                         	
                if (v) {                	
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