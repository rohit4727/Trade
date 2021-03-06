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
        	, defaults:{
        		labelStyle: 'font-weight:bold;'
	    		, labelWidth: 80
	    		, labelSeparator: ''
        	}
            , items: [
            	{
                    xtype: 'button'
                    , iconCls: 'x-fa fa-refresh'
                    , handler: 'onCompletedJobsListRefreshButtonClick'
                },
                {
                	xtype:'textfield'
            		, fieldLabel: 'Job Name'
        			, enableKeyEvents: true
        			, reference: 'completedJobsListJobNameFilter'
        			, listeners:{
        				'keyup': 'onCompletedJobsJobNameFilterChange'
        			}
                },
                {
                	xtype:'textfield'
            		, fieldLabel: 'Path Name'
        			, enableKeyEvents: true
        			, reference: 'completedJobsListJobPathFilter'
        			, listeners:{
        				'keyup': 'onCompletedJobsJobPathFilterChange'
        			}
                },
                {
                	xtype:'datefield'
            		, fieldLabel: 'Date Range'
            		, reference: 'completedJobsListDateFromFilter'
        			, listeners:{
        				'change': 'onCompletedJobsDateFromFilterChange'
        			}
                },
                {
                	xtype:'datefield'
            		, hideLabel: true
            		, reference: 'completedJobsListDateToFilter'
        			, listeners:{
        				'change': 'onCompletedJobsDateToFilterChange'
        			}
                },
                {
                	xtype:'button'
            		, text: 'Reset Filter'
        			, handler: 'onCompletedJobsFilterReset'
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
        { text: 'Time', dataIndex: 'displayTime' },
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
	    afterrender: 'onCompletedJobsListAfterRender'
	}
});
