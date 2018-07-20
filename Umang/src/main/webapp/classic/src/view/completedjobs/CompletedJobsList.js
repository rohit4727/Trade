/**
 * Author: Umang Goel * 
 * This view is for Schedule job list.
 */

Ext.define('ui.view.completedjobs.CompletedJobsList', {
    extend: 'Ext.grid.Panel',
    xtype: 'completedjobslist',
    

    title: 'Completed Jobs'

    , dockedItems: [                
    	{
            xtype: 'toolbar'
            , dock: 'top'
            , items: [
            	{
                    xtype: 'button'
                    , iconCls: 'x-fa fa-refresh'
                    //, handler: 'onCompletedJobsListRefreshButtonClick'
                }
            ]
        }
    ]


	, reference: 'completedjobslist'
    , itemId: 'completedjobslist'
	, bind:{
		store:'{completedJobsListStore}'
	}

    , columns: [
    	{ text: 'Job Name',  dataIndex: 'jobName', flex: 0.75 },
        { text: 'Path',  dataIndex: 'batchFilePath', flex: 0.75 },
        { text: 'Date', dataIndex: 'displayDate', type: 'date' },
        { text: 'Time', dataIndex: 'displayTime', type: 'time' },
        { text: 'Status', dataIndex: 'status', flex: 0.5, renderer:function(v, md){
        		if(Ext.isEmpty(v)){return ''};
        		
        		if(v==0){return "In Progress";}
        		else if(v==1){return "Run Success";}
        		else if(v==2){return "Run Failed";}
        		else {return "Unknown";}
        	} 
        }
    ]
	, listeners: {
	    afterrender: 'onCompletedJobsListAfterRender'
	}
});
