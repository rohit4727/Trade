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
    	var view = this.getView();
    	
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
	         , me = this;
     
    	 jobItem.set('scheduleDate', new Date(jobItem.get('date') + ' ' + jobItem.get('time')));	
    	 
    	 
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
	        		 
	        		 
	        		 swal({
        			  title: "Error",
        			  text: resptext.message,
        			  icon: "error"
        			});	        		 
	        	 }
	             win.destroy();
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
});
