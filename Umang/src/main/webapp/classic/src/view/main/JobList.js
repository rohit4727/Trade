/**
 * Author: Umang Goel * 
 * This view is for Schedule job list.
 */

Ext.define('ui.view.main.JobList', {
    extend: 'Ext.grid.Panel',
    xtype: 'joblist',
    
    requires: [
        'ui.view.main.JobListToolbar'
    ],

    title: 'Scheduled Jobs'

    , dockedItems: [                
        {
            xtype: 'joblisttoolbar'
            , dock: 'top'
        }
    ]


	, reference: 'joblist'
    , itemId: 'joblist'
	, bind:{
		store:'{scheduleJobListStore}'
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
        },        
        {
            xtype:'actioncolumn'
            , width:60
            , items: [
            	{
	                iconCls: 'x-fa fa-pencil'
	                , tooltip: 'Edit'
                	, handler: 'onScheduleJobListEditBtnClick'
            		, isDisabled: function(view, rowIndex, colIndex, item, record) {
            			return record.get('status')!=0;
                    }
	            },
	            {
	            	iconCls: 'x-fa fa-times'
	                , tooltip: 'Delete'
                	, style:'float:right'
            		, isDisabled: function(view, rowIndex, colIndex, item, record) {
            			return record.get('status')!=0;
                    }
	            }
            ]
        }
    ]
});
