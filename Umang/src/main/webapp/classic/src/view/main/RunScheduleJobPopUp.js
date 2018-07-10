
Ext.define('ui.view.main.RunScheduleJobPopUp', {
    extend: 'Ext.window.Window'
    , xtype: 'runschedulejobpopup'
	, reference: 'runschedulejobpopup'
	, autoScroll: true
    , defaults: {
        labelSeparator: ''
    	, labelAlign: 'top'
        , labelStyle: 'font-weight:bold;'
    }
	, initComponent: function () {
	    this.items = this.buildItems();
	
	    this.callParent();
	}
	, dockedItems: [
		{
	        xtype: 'toolbar',
	        dock: 'bottom',
	        items: [
	        	'->',
	        	{
	                xtype: 'button'
	                , iconCls: 'x-fa fa-arrow-right'
	                , text: 'Go'
	                , handler: 'onRunScheduleJobGoButtonClick'
	            },
	            {
	                xtype: 'button'
	                , iconCls: 'x-fa fa-times'
	                , text: 'Close'
	            	, scope: this
	                , handler: function(){
	                	this.close();            	
	                }
	            }
	        ]
	    }
	]
	, buildItems: function () {
	   return [
		   {
			   xtype:'box'
			   , html:'<i class="fa fa-play-circle" aria-hidden="true" style="vertical-align: middle;margin-right: 5px;"></i>You may run or schedule multiple jobs.'
			   , style: 'padding:10px;background: #e6e6d8;font-weight:bold;margin: -20px -20px 10px -20px;'
		   },
		   {
		    	xtype: 'container'
	    		, layout: 'hbox'
	   			, defaults: {
	   		        labelSeparator: ''
	   		    	, labelAlign: 'top'
	   		        , labelStyle: 'font-weight:bold;'
	   		    }
	    		, items: [
	    			{
	    		    	xtype:'textfield'
	    				, fieldLabel: 'Job Name'
	    				, flex: 0.75
	    				, bind:{
	    					value: '{jobItem.job_name}'	    					
	    				}
	    				, style: 'margin-right:20px;'
	    		    },
	    		    {
	    		    	xtype:'textfield'
	    				, fieldLabel: 'Job Path'
	    				, flex: 1
	    				, bind:{
	    					value: '{jobItem.path}'	    					
	    				}
	    		    }
	    		]
		    },		   
		    {
		        xtype: 'radiogroup'
		        , fieldLabel: 'Choose any one'
		        , columns: 1
		        , bind:{
					value: '{jobItem.run_frequency}'	    					
				}
		        , items: [
		        	{ boxLabel: 'Run Now', name: 'rb', inputValue: 1, checked: true, reference: 'RunRadio'},
		            { boxLabel: 'Schedule for later', name: 'rb', inputValue: 2 }		            
		        ]
		    },
		    {
		    	xtype: 'container'
	    		, layout: 'hbox'
    			, bind:{
    				hidden: '{RunRadio.checked}'
    			}
    			, defaults: {
    		        labelSeparator: ''
    		    	, labelAlign: 'top'
    		        , labelStyle: 'font-weight:bold;'
    		    }
	    		, items: [
	    			{
	    		    	xtype:'datefield'
	    	    		, name: 'ScheduleDate'
	    		    	, minValue: new Date()
	    				, fieldLabel: 'Date'
    					, bind:{
    						value: '{jobItem.date}'
							, disabled:'{RunRadio.checked}'
    					}
	    				, flex: 1
	    				, style: 'margin-right:20px;'
	    		    },
	    		    {
	    		        xtype: 'timefield'
	    		        , name: 'ScheduleTime'
	    		        , fieldLabel: 'Time'
	    		        , increment: 30
	    		        , flex: 1
	    		        , bind:{
    						value: '{jobItem.time}'
							, disabled:'{RunRadio.checked}'
    					}
	    		    }
	    		]
		    }		    
	   ]
	}
});