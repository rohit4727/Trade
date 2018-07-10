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
    
    , onJobListRunJobButtonClick:function(){
    	var view = this.getView();
    	
    	view.add({
            xtype: 'runschedulejobpopup'
            , autoShow: true
            , renderTo: Ext.getBody()
            , width: 600
            , height:400
            , title: 'Run/Schedule Jobs!'
        	, modal: true
        	, mode: 'add'
        }).showBy(Ext.getBody());
    }
    
    , onRunScheduleJobGoButtonClick: function() {
    	 var viewModel = this.getViewModel()
	         , jobItem = viewModel.get('jobItem')
	         , win = this.lookupReference('runschedulejobpopup')
	         , me = this;
     
	     	
    	 jobItem[win.mode == 'add' ? 'create' : 'update']({
	         scope: this
	         , maskCmp: win
	         , callback: function () {
	             //me.loadCategoryPostList();
	             win.destroy();
	         }
	     });
	}
});
