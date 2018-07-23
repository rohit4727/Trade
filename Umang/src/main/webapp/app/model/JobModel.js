Ext.define('ui.model.JobModel', {
	extend: 'ui.model.Base'
    , idProperty: 'id'
    , fields: [
        { name: 'id' }
        , { name: 'jobName' }
        , { name: 'batchFilePath' }        
        , { name: 'scheduleDate'}
        , { name: 'status', type: 'int'}         
        , { name: 'runFrequency', mapping:'status', defaultValue: 1, type: 'int', convert: function (v, rec) {
            return v == '1' ? 1 : (v == true ? 1 : 0);
        	} 
        }
        , { name: 'displayDate', defaultValue: new Date(), convert: function (v, rec) {
        		v = rec.get('scheduleDate');
	            if (v) {
	                v = Ext.isDate(v) ? v : new Date(v);
	                v = Ext.Date.format(v, 'Y-m-d');
	            }
	
	            return v;
        	} 
        }
        , {
            name: 'displayTime', defaultValue: new Date(), convert: function (v, rec) {	
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
            create: '/TradeApp/scheduleOrRunJob'
            , update: '/TradeApp/updateJobScheduleDetails'
            , destroy:'/TradeApp/schedule_job_delete'
        }
    }
});