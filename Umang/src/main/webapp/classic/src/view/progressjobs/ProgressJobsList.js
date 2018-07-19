/**
 * Author: Umang Goel * 
 * This view is for Schedule job list.
 */

Ext.define('ui.view.progressjobs.ProgressJobsList', {
    extend: 'Ext.grid.Panel',
    xtype: 'progressjobslist',    

    title: 'Progress Jobs'

    , dockedItems: [                
    	{
            xtype: 'toolbar'
            , dock: 'top'
            , items: [
            	{
            		xtype:'label'
        			, text: 'Job Name'
    				, style: 'font-weight:bold;'
            	},
                {
                	xtype:'textfield'
            		, hideLabel	: true
    				, reference: 'progressjobsfiltertextfield' 					
                }
            ]
        }
    ]


	, reference: 'progressjobslist'
    , itemId: 'progressjobslist'
	, bind:{
		store:'{progressJobsListStore}'
	}

    , columns: [
    	{ text: 'Job Name',  dataIndex: 'jobName', flex: 0.75 },
        { text: 'Path',  dataIndex: 'batchFilePath', flex: 0.75 },
        { text: 'Date', dataIndex: 'displayDate', type: 'date' },
        { text: 'Time', dataIndex: 'displayTime', type: 'time' },
        { text: 'Status', dataIndex: 'status', flex: 0.5, renderer:function(v, md){
        		if(Ext.isEmpty(v)){return ''};
        		
        		if(v==0){return "Run Scheduled";}
        		else if(v==1){return "Run Success";}
        		else if(v==2){return "Run Failed";}
        		else {return "Unknown";}
        	} 
        }
    ]
});
