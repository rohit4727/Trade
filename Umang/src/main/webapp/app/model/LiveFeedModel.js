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
        , { name: 'tradeDate', convert: function (v, rec) {
	            if (v) {
	                v = Ext.isDate(v) ? v : new Date(v);
	                v = Ext.Date.format(v, 'Y-m-d');
	            }
	
	            return v;
        	} 
        }
        , {
            name: 'tradeTime', convert: function (v, rec) {
                v = new Date(v);                
                v = new Date(v.getTime() + (v.getTimezoneOffset() * 60000));
                                         	
                if (v) {                	
                	v = Ext.Date.format(v, 'H:i');
                }

                return v;
            }
        }
    ]
});