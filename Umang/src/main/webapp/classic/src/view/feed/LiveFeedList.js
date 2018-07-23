/**
 * Author: Umang Goel * 
 * This view is for Schedule job list.
 */

Ext.define('ui.view.feed.LiveFeedList', {
    extend: 'Ext.grid.Panel',
    xtype: 'livefeedlist',
    

    title: 'Live Feed'

	//top toolbar filters
    , dockedItems: [                
    	{
            xtype: 'toolbar'
            , dock: 'top'
            , items: [            	
            	{
            		xtype:'combobox'
            	    ,fieldLabel: 'Select Security to Filter'
        	    	, labelStyle: 'font-weight:bold;'
    	    		, labelWidth: 160
    	    		, labelSeparator: ''
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
        { text: 'Currency', dataIndex: 'currency' }
    ]
	, listeners: {
	    afterrender: 'onLiveFeedListAfterRender'
	}
});
