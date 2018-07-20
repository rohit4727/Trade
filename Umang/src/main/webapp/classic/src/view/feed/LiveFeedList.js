/**
 * Author: Umang Goel * 
 * This view is for Schedule job list.
 */

Ext.define('ui.view.feed.LiveFeedList', {
    extend: 'Ext.grid.Panel',
    xtype: 'livefeedlist',
    

    title: 'Live Feed'

    , dockedItems: [                
    	{
            xtype: 'toolbar'
            , dock: 'top'
            , items: [            	
            	{
            		xtype:'combobox'
            	    ,fieldLabel: 'Select Security to Filter'
        	    	, labelStyle: 'font-weight:bold;'
        	    	, reference: 'livefeedsecurityfilter' 
            	    , store: []
            	    , bind:{
						value: '{liveFeedListFilter.security}'
					}
            		, listeners:{
            			change: 'onLiveFeedSecurityChange'
            		}
            	}
            ]
        }
    ]


	, reference: 'livefeedlist'
    , itemId: 'livefeedlist'
	, bind:{
		store:'{liveFeedListStore}'
	}

    , columns: [
        { text: 'Security Name',  dataIndex: 'security', flex: 0.75 },
        { text: 'Instrument Type',  dataIndex: 'instrumentType', flex: 0.75 },
        { text: 'Date', dataIndex: 'tradeDate', type: 'date' },
        { text: 'Time', dataIndex: 'tradeTime', type: 'time' },
        { text: 'Broker', dataIndex: 'broker' },
        { text: 'Trade Price', dataIndex: 'tradePrice' },
        /*{ text: 'Change', dataIndex: 'status', flex: 0.2, renderer:function(v, md){        	
        	
    		md.style = 'background-color: #ff6262;color:#FFF;';  //green = #458445
        	
        	return '<span class="fa fa-arrow-down" style="color:#FFF;"></span> 3.5'
        	} 
        },*/
        { text: 'Currency', dataIndex: 'currency' }
    ]
	, listeners: {
	    afterrender: 'onLiveFeedListAfterRender'
	}
});
