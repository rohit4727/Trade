/**
 * Author: Umang Goel * 
 * This view is for Schedule job list.
 */

Ext.define('ui.view.progressjobs.ProgressJobsList', {
    extend: 'Ext.grid.Panel',
    xtype: 'progressjobslist',    

    title: 'Progress Jobs'

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
    	{ text: 'Job Name',  dataIndex: 'jobName', flex: 0.75 },
        { text: 'Path',  dataIndex: 'batchFilePath', flex: 0.75 },
        { text: 'Date', dataIndex: 'displayDate', type: 'date' },
        { text: 'Time', dataIndex: 'displayTime' },
        
        // Below cell returns progressbar (processed records vs total records ratio in percentage)
        {
        	text: 'Progress',
            dataIndex: 'progress',
            flex: 0.5,
            renderer: function (v, m, r) {
            	var total = r.get('totalLineCount')
            		, processed = r.get('writerLineCount');
            	
                var id = Ext.id();
                Ext.defer(function () {
                    Ext.widget('progressbar', {
                        renderTo: id,
                        value: processed / total
                    });
                }, 50);
                return Ext.String.format('<div id="{0}"></div>', id);
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
	    //afterrender: 'onProgressJobsListAfterRender'
	}
});
