
Ext.define('ui.view.main.RunScheduleJobPopUp', {
    extend: 'Ext.window.Window'
    , xtype: 'runschedulejobpopup'
	, reference: 'runschedulejobpopup'
	, modal: true
	, width:800
	, autoHeight: true
    , defaults: {
        labelSeparator: ''
    	, labelAlign: 'top'
        , labelStyle: 'font-weight:bold;'
    }
	, initComponent: function () {
	    this.items = this.buildItems();
	
	    this.callParent();
	}	
	
	
	, buildItems:function(){
		var me = this;
	
		return [	
			{
 			   xtype:'box'
 			   , html:'<i class="fa fa-play-circle" aria-hidden="true" style="vertical-align: middle;margin-right: 5px;"></i>'+ (me.mode=='add'? 'You may run now or schedule a job for later.':'Update Your Schedule Job')
 			   , style: 'padding:10px;background: #e6e6d8;font-weight:bold;margin: -20px -20px 10px -20px;'
 		   	},
	        {
	            xtype: 'form'
	            , bodyStyle: 'background-color:transparent;padding:10px;'
	            , submitEmptyText: false
	            , autoScroll: true
	            , name: 'runScheduleJobForm'
	            , reference: 'runScheduleJobForm'
	            , fieldDefaults: {
	                labelSeparator: ''
	                , labelWidth: 200
	                , width: 450
	                , labelAlign: 'top'
	                , labelStyle: 'font-weight:bold;'
	            }
	            , dockedItems: [
	                {
	                    xtype: 'toolbar'
	                    , dock: 'bottom'
	                    , items: [
	                        '->',
	                        {
	                            xtype: 'button'
	                            , iconCls: 'x-fa fa-save'
	                            , text: 'Go'
	                            , handler: 'onRunScheduleJobWindowGoButtonClick'
	                            , formBind: true
	                        },
	                        {
	                            xtype: 'button'
	                            , text: 'Close'
	                            , iconCls: 'x-fa fa-ban'
	                            , handler: 'onRunScheduleJobWindowCancelButtonClick'
	                        }
	                    ]
	                }
	            ]
	            , items: [	     		   
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
	    	    					value: '{jobItem.jobName}'	    					
	    	    				}
	    	    				, style: 'margin-right:20px;'
	        					, allowBlank: false
	    	    		    },
	    	    		    {
	    	    		    	xtype:'textfield'
	    	    				, fieldLabel: 'Job Path'
	    	    				, flex: 1
	    	    				, bind:{
	    	    					value: '{jobItem.batchFilePath}'	    					
	    	    				}
	    	    		    	, allowBlank: false
	    	    		    }
	    	    		]
	    		    },		   
	    		    {
	    		        xtype: 'radiogroup'
	    		        , fieldLabel: 'Choose any one'
	    		        , columns: 1
	    		        , items: [
	    		        	{ 
	    		        		boxLabel: 'Run Now'
	    	        			, bind:{
	    	    					value: '{jobItem.runFrequency}'	    					
	    	    				}
	            				, inputValue: 1
	            				, checked: me.mode=='add'
	        					, disabled: me.mode=='edit'
	        					, reference: 'RunRadio'
	    					},		        	
	    		            { 
	    						boxLabel: 'Schedule for later'
	    						, inputValue: 0
	    						, checked: me.mode=='edit'										            
	    		            }
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
	    		        	, flex: 1
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
	    	    				, style: 'margin-right:20px;'
	        					, allowBlank: false
	    	    		    },
	    	    		    {
	    	    		        xtype: 'timefield'
	    	    		        , name: 'ScheduleTime'
	    	    		        , fieldLabel: 'Time'
	    	    		        , increment: 30
	    	    		        , bind:{
	        						value: '{jobItem.time}'
	    							, disabled:'{RunRadio.checked}'
	        					}
	    	    		    	, allowBlank: false
	    	    		    }
	    	    		]
	    		    }		    
	    	   ]
	        }
	    ]
	}
});