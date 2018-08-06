/**
 * Author: Umang Goel * 
 * This view is for Schedule job list.
 */

Ext.define('ui.view.progressjobs.ProgressJobsList', {
    extend: 'Ext.grid.Panel',
    xtype: 'progressjobslist',    

    title: 'Progress Jobs'
    	
	, viewConfig: {        
        loadMask: false    
    }

	//toolbar for progress job list
    , dockedItems: [                
    	{
            xtype: 'toolbar'
            , dock: 'top'
            , items: [
            	{
                    xtype: 'button'
                    , iconCls: 'x-fa fa-refresh'
                    , handler: 'onProgressJobsListRefreshButtonClick'
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
    	{ text: 'Job Name',  dataIndex: 'jobName', flex: 0.5 },
        { text: 'Path',  dataIndex: 'batchFilePath', flex: 0.5 },
        { text: 'Date', dataIndex: 'displayDate', type: 'date' },
        { text: 'Time', dataIndex: 'displayTime' },
        { text: 'Total', dataIndex: 'totalLineCount' },
        { text: 'Processed', dataIndex: 'writerLineCount' },
        
        // Below cell returns progressbar (processed records vs total records ratio in percentage)
        {
        	text: 'Progress',
            dataIndex: 'progress',
            flex: 0.5,
            renderer: function (v, m, r) {
            	var total = r.get('totalLineCount')
            		, processed = r.get('writerLineCount')
                		, value= (processed / total)*100;
                	
            	return ['<div style="background-color: #f5f5f5;border-width: 0;height: 20px;border-color: #157fcc;border-style: solid;width:200px;position: relative;">'
            	    , '<div style="padding-top:3px;width: 100%;z-index:1;text-align: center;position: absolute;font-weight: bold;color: #666;">'+value+'%</div>'
            	    , '<div style="width:'+value+'%;background: #c2ddf2;height: 20px;"></div></div>'
            	].join('');
            }
        },
        { text: 'Status', dataIndex: 'jobProgressStatus', flex: 0.5, renderer:function(v, md){
        		if(Ext.isEmpty(v)){return ''};
        		
        		if(v==0){return "In Progress";}
        		else if(v==1){return "Run Success";}
        		else if(v==2){return "Run Failed";}
        		else {return "Unknown";}
        	} 
        }        
    ]
    , listeners: {
	    afterrender: 'onProgressJobsListAfterRender'
	}
});
