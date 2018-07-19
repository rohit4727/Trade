Ext.define('ui.model.LiveFeedModel', {
	extend: 'ui.model.Base'
    , idProperty: 'tradeId'
    , fields: [
        { name: 'tradeId' }
        , { name: 'security' }
        , { name: 'instrumentType' }  
        , { name: 'currency'} 
        , { name: 'broker' }
        , { name: 'tradePrice' }  
        , { name: 'tradeDate', defaultValue: new Date(), convert: function (v, rec) {
        		v = rec.get('scheduleDate');
	            if (v) {
	                v = Ext.isDate(v) ? v : new Date(v);
	                v = Ext.Date.format(v, 'Y-m-d');
	            }
	
	            return v;
        	} 
        }
        , {
            name: 'tradeTime', defaultValue: new Date(), convert: function (v, rec) {	
                v = new Date(rec.get('scheduleDate'));                
                v = new Date(v.getTime() + (v.getTimezoneOffset() * 60000));
                                         	
                if (v) {                	
                	v = Ext.Date.format(v, 'H:i');
                }

                return v;
            }
        }
    ]
});