/**
 * Author: Umang Goel
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 */
Ext.define('ui.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main'
    
	, listen: {
        store: {
            '#scheduleJobListStore': {
                load: 'onScheduleJobListLoad'
            }     
        }
    }    	

	// Used to load chart data after schedule job list store has been loaded
	, onScheduleJobListLoad:function(store, records){
		if(!records){return;}
		
		var len = records.length
			, scheduled = []
			, failed = []
			, success = [];
		
		for(var i=0, current;i<len;i++){
			current = records[i]['data'];
		
			if(current.status==0){
				scheduled.push(current);
			}
			else if(current.status==1){
				success.push(current);
			}
			else{
				failed.push(current);
			}
		}
		
		var data = [
			{ type: 'Scheduled', count: scheduled.length },
            { type: 'Run Success', count: success.length },
            { type: 'Run Failed', count: failed.length }
		];
		
		var chartStore = this.getViewModel().getStore('scheduleListChartStore');
		chartStore.setData(data);
		
		// setting labels on chart panel
		this.lookupReference('totalJobs').setValue(len);
		this.lookupReference('failedJobs').setValue(failed.length);
		this.lookupReference('scheduledJobs').setValue(scheduled.length);
		this.lookupReference('successJobs').setValue(success.length);
	}
	
	, onScheduleJobListRefreshButtonClick: function (btn) {
        var grid = btn.up('grid');
        this.loadScheduleJobList(grid);
    } 
	
	//calls when user tried to schedule or run a new job
    , onJobListRunOrScheduleJobBtnClick:function(){
    	var view = this.getView()
    		, viewModel = this.getViewModel();
    	
    	viewModel.set('jobItem', Ext.create(ui.model.JobModel, {}));
    	
    	view.add({
            xtype: 'runschedulejobpopup'
            , autoShow: true
            , renderTo: Ext.getBody()            
            , title: 'Run/Schedule Jobs!'        	
        	, mode: 'add'
        }).showBy(Ext.getBody());
    }
    
    //run or schedule job window go button click, create or update record based on window mode property
    , onRunScheduleJobWindowGoButtonClick: function() {
    	 var viewModel = this.getViewModel()
	         , jobItem = viewModel.get('jobItem')
	         , win = this.lookupReference('runschedulejobpopup')
	         , runFrequency = jobItem.get('runFrequency')
	         , me = this;
     
    	 if(runFrequency==1){
    		 jobItem.set('scheduleDate', new Date());
    		 jobItem.set('status', 1);
    	 }
    	 else{
    		 var view = this.getView()
	             , dateField = view.lookupReference('scheduledDate')
	             , timeField = view.lookupReference('scheduledTime');
    		 
    		 jobItem.set('scheduleDate', new Date(Ext.util.Format.date(dateField.getValue(), 'Y-m-d') + ' ' + Ext.util.Format.date(timeField.getValue(), 'H:i')));	
    		 jobItem.set('status', 0);
    	 }
    	 
    	 jobItem[win.mode == 'add' ? 'create' : 'update']({
	         scope: this
	         , maskCmp: win	        
	         , callback: function (records, operation, success) {
	        	 if (!success) {
	        		 var error = operation.getError()
	        		 	, resptext = Ext.decode(error.response.responseText);
	        		 
	        		 me.showToast(resptext.message);
	        	 }
	        	 else{
	        		 var response = Ext.decode(operation._response.responseText);
	        		 if(response.statusCode=='200'){
	        			 swal({
		        			  title: "Success",
		        			  text: response.message,
		        			  icon: "success"
	        			});	
	        			 
	        			 win.destroy();
	        		 }
	        		 else{	   
	        			 if(win.mode == 'add'){
	        				 viewModel.set('jobItem', Ext.create(ui.model.JobModel, {}));
	        			 }
	        			 me.showToast(response.message);	        			 
	        		 }	        		 
	        	 }	 
	        	 me.loadScheduleJobList();
	         }
	     });
	}
    
    //run or schedule job window close button click
    , onRunScheduleJobWindowCancelButtonClick: function (btn) {
        var win = btn.up('window');
        win.close();
    }
    
    //update mode for schedule jobs
    , onScheduleJobListEditBtnClick:function(grid, ri){
    	var rec = grid.getStore().getAt(ri)
    		, viewModel = this.getViewModel()  
    	
    	viewModel.set('jobItem', rec);
    	
    	this.getView().add({
            xtype: 'runschedulejobpopup'
            , autoShow: true
            , renderTo: Ext.getBody()
            , title: 'Update Schedule Job!'
        	, modal: true
        	, mode: 'edit'
        }).showBy(Ext.getBody());
    }
    
    //delete mode for schedule jobs
    , onScheduleJobListEditBtnClick:function(grid, ri){
    	var rec = grid.getStore().getAt(ri)
    		, viewModel = this.getViewModel() 
    		, jobItem = viewModel.get('jobItem');
    	
    	viewModel.set('jobItem', rec);
    	
    	jobItem['destroy']({
	         scope: this
	         , maskCmp: win	        
	         , callback: function (records, operation, success) {
	        	 if (!success) {
	        		 var error = operation.getError()
	        		 	, resptext = Ext.decode(error.response.responseText);
	        		 
	        		 me.showToast(resptext.message);
	        	 }
	        	 else{
	        		 var response = Ext.decode(operation._response.responseText);
	        		 if(response.statusCode=='200'){
	        			 swal({
		        			  title: "Success",
		        			  text: response.message,
		        			  icon: "success"
	        			});	
	        		 }
	        		 else{	
	        			 me.showToast(response.message);	        			 
	        		 }	        		 
	        	 }	 
	        	 me.loadScheduleJobList();
	         }
	     });
    }
    
    
    , loadScheduleJobList: function (grid, options) {
        var store = this.getViewModel().getStore('scheduleJobListStore');
        
        store.read(options);
    }
    
    //used to display alert notifications to user in case of any error
    , showToast: function(s) {
    	Ext.MessageBox.alert('', [	        				 
			 '<p><i class="fa fa-times fa-3x" aria-hidden="true" style="vertical-align: middle;margin-right: 5px;color:#bf6c6c;"></i>'
			 , s
			 , '</p>'
		].join(''));    	
    }
    
    // CHART METHOD to render the tooltip whenever mouse is hovered on donut
    , onSeriesTooltipRender: function (tooltip, record, item) {
        tooltip.setHtml(record.get('type') + ': ' + record.get('count'));
    }
});
