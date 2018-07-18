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
            		xtype:'label'
        			, text: 'Security Name'
    				, style: 'font-weight:bold;'
            	},
                {
                	xtype:'textfield'
            		, hideLabel	: true
    				, reference: 'livefeedfiltertextfield' 
					, bind:{
						value: '{liveFeedListFilter.securityName}'
					}
                }
            ]
        }
    ]


	, reference: 'livefeedlist'
    , itemId: 'livefeedlist'
	/*, bind:{
		store:'{liveFeedListStore}'
	}*/

    , columns: [
        { text: 'Security Name',  dataIndex: 'jobName', flex: 0.75 },
        { text: 'Instrument Type',  dataIndex: 'batchFilePath', flex: 0.75 },
        { text: 'Date', dataIndex: 'displayDate', type: 'date' },
        { text: 'Time', dataIndex: 'displayTime', type: 'time' },
        { text: 'Broker', dataIndex: 'displayDate', type: 'date' },
        { text: 'Trade Price', dataIndex: 'displayTime', type: 'time' },
        { text: 'Change', dataIndex: 'status', flex: 0.2, renderer:function(v, md){        	
        	
    		md.style = 'background-color: #ff6262;color:#FFF;';  //green = #458445
        	
        	return '<span class="fa fa-arrow-down" style="color:#FFF;"></span> 3.5'
        	} 
        }
    ]
	, listeners: {
	    afterrender: 'onLiveFeedListAfterRender'
	}
});
