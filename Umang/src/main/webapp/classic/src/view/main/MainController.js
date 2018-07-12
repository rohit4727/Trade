/**
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 */
Ext.define('ui.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
    
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
    		 jobItem.set('scheduleDate', new Date(jobItem.get('date') + ' ' + jobItem.get('time')));	
    		 jobItem.set('status', 0);
    	 }
    	 
    	 jobItem[win.mode == 'add' ? 'create' : 'update']({
	         scope: this
	         , maskCmp: win	        
	         , callback: function (records, operation, success) {
	        	 //var data = Ext.decode(operation.response.responseText);
	        	 //console.log(operation.response.responseText);
	             //me.loadCategoryPostList();
	        	 
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
	        			 viewModel.set('jobItem', Ext.create(ui.model.JobModel, {}));
	        			 me.showToast(response.message);	        			 
	        		 }	        		 
	        	 }	             
	         }
	     });
	}
    , onRunScheduleJobWindowCancelButtonClick: function (btn) {
        var win = btn.up('window');
        win.close();
    }
    
    , onScheduleJobListEditBtnClick:function(grid, ri){
    	var rec = grid.getStore().getAt(ri)
    		, viewModel = this.getViewModel();
    	
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
    , showToast: function(s) {
    	Ext.MessageBox.alert('', [	        				 
			 '<p><i class="fa fa-times fa-3x" aria-hidden="true" style="vertical-align: middle;margin-right: 5px;color:#bf6c6c;"></i>'
			 , s
			 , '</p>'
		].join(''));    	
    }
});
