
Ext.define('ui.view.main.RunScheduleJobPopUp', {
    extend: 'Ext.window.Window'
    , xtype: 'runschedulejobpopup'
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
	                //, handler: 'onJobListRunJobButtonClick'
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
		        xtype: 'tagfield'
		        , fieldLabel: 'Select Job(s)'	        	
		        , displayField: 'name'
		        , valueField: 'id'
		        , queryMode: 'local'
	        	, labelWidth: 200
	        	, width: '100%'
		        , filterPickList: true
		        , store: new Ext.create('Ext.data.Store', {
		            fields: ['id','name'],
		            data: [
		                {id: 1, name: 'Job 1'}
		                , {id: 2, name: 'Job 2'}
		                , {id: 3, name: 'Job 3'}
		            ]
		        })
		    },
		    {
		        xtype: 'radiogroup'
		        , fieldLabel: 'Choose any one'
		        , columns: 1
		        
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
	    				, flex: 1
	    				, style: 'margin-right:20px;'
	    		    },
	    		    {
	    		        xtype: 'timefield'
	    		        , name: 'ScheduleTime'
	    		        , fieldLabel: 'Time'
	    		        , increment: 30
	    		        , flex: 1
	    		    }
	    		]
		    }		    
	   ]
	}
});