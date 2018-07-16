
Ext.define('ui.view.main.ScheduleJobSummaryPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'schedulejobsummarypanel'
	, reference: 'schedulejobsummarypanel'
	, title: 'Schedule Jobs Summary'
	, collapsible: true
	, collapsed: true
	, layout: 'column'
	, initComponent: function () {
	    this.items = this.buildItems();
	
	    this.callParent();
	}	
	
	, requires: [
	    'Ext.chart.PolarChart'
	]
	
	, buildItems:function(){
		var me = this;
	
		return [
			{
		        xtype: 'polar',
		        reference: 'chart',
		        innerPadding: 40,
		        width: 500,
		        height: 400,
		        bind:{
		        	store:'scheduleListChartStore'
		        },
		        interactions: ['itemhighlight', 'rotatePie3d'],
		        legend: {
		            docked: 'bottom'
		        },
		        series: [
		            {
		                type: 'pie3d',
		                angleField: 'count',
		                donut: 40,
		                distortion: 0.6,
		                highlight: {
		                    margin: 10
		                },
		                label: {
		                    field: 'type'
		                },
		                tooltip: {
		                    trackMouse: true,
		                    renderer: 'onSeriesTooltipRender'
		                }
		            }
		        ]
		    },
		    {
		    	xtype:'container'
	    		, style: 'margin:60px 20px 10px 30px;'
	    		, defaults:{
    		        labelStyle: 'font-weight:bold;'
    		        , xtype:'displayfield'
		        	, labelWidth: 200
	    		}			    			
	    		, items:[
	    			{
	    				reference: 'totalJobs'
						, fieldLabel: 'Total Jobs'    							
	    			},
	    			{
	    				reference: 'failedJobs'
						, fieldLabel: 'Jobs - Run Failed'    							
	    			},
	    			{
	    				reference: 'scheduledJobs'
						, fieldLabel: 'Jobs - Scheduled'    							
	    			},
	    			{
	    				reference: 'successJobs'
						, fieldLabel: 'Jobs - Run Success'    							
	    			}
	    			
	    		]
		    }
		]
	}
});