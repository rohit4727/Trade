Ext.define('ui.model.LiveFeedModel', {
	extend: 'ui.model.Base'
    , idProperty: 'tradeId'
    , fields: [
        { name: 'tradeId' }
        , { name: 'security' }
        , { name: 'instrumentType' }  
        , { name: 'currency'} 
        , { name: 'broker' }
        , { name: 'direction' }
        , { name: 'tradePrice' }  
        , { name: 'tradeDate', convert: function (v, rec) {
	            if (v) {
	                v = Ext.isDate(v) ? v : new Date(v);
	                v = Ext.Date.format(v, 'm-d-Y');
	            }
	
	            return v;
        	} 
        }
        ,{
            name: 'tradeTime', convert: function (v, rec) {     
            	var date = new Date(v);
            	
            	if (isNaN(date.getTime())) {  
            	    return v;
            	} 
            	else {   
                	v = Ext.Date.format(v, 'H:i:s');
            	}
            	
                return v;
            	
            }
        }
    ]
});