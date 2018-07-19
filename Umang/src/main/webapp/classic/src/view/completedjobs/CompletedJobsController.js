/**
 * Author: Umang Goel
 * This class is the controller for the completed jobs view for the application. It is specified as
 * the "controller" of the completed jobs view class.
 */
Ext.define('ui.view.completedjobs.CompletedJobsController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.completedjobs'
    
	, listen: {
        store: {
            '#completedJobsListStore': {
                beforeload: 'onCompletedJobsListBeforeLoad'
            }
        }
    }

	, onCompletedJobsListAfterRender: function (grid) {  
		var me = this;
		/*Ext.TaskManager.start({
		  run: function() { 
			  me.loadCompletedJobsList(grid);
		  },
		  interval: 1000
		});*/
		me.loadCompletedJobsList(grid);
	}
	, onCompletedJobsListBeforeLoad: function (store) {
	    var sorter = store.getSorters().getRange()[0]
	        , viewModel = this.getViewModel()
	        , completedJobsListFilterModel = viewModel.get('completedJobsListFilter')
	        , dir
	        , id;
	
	    if (sorter) {
	        dir = sorter.getDirection();
	        id = sorter.getId();
	
	        completedJobsListFilterModel.set('dir', dir);
	        completedJobsListFilterModel.set('sort', id.substr(0, id.indexOf('-') > -1 ? id.indexOf('-') : id));
	    }		
	    store.proxy.setExtraParams(completedJobsListFilterModel.getData());
	}
	
	, onCompletedJobsListFilterChange: function (combo, newValue, oldValue) {
        this.loadCompletedJobsList(combo.up('grid'));
    }
	
	, loadCompletedJobsList: function (grid, options) {
        var store = this.getViewModel().getStore('completedJobsListStore');
        store.read(options);
    }
});
