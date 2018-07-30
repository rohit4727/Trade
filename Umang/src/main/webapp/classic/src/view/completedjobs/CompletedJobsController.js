/**
 * Author: Umang Goel
 * This class is the controller for the completed jobs view for the application. It is specified as
 * the "controller" of the completed jobs view class.
 */
Ext.define('ui.view.completedjobs.CompletedJobsController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.completedjobs'

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

	, onCompletedJobsListRefreshButtonClick:function(btn){
		var grid = btn.up('grid');
	    this.loadCompletedJobsList(grid);
	}
	
	, onCompletedJobsListFilterChange: function (combo, newValue, oldValue) {
        this.loadCompletedJobsList(combo.up('grid'));
    }
	
	, loadCompletedJobsList: function (grid, options) {
        var store = this.getViewModel().getStore('completedJobsListStore');
        store.read(options);
    }
	
	, onCompletedJobsJobNameFilterChange:function(jobNameField){
		var store = this.getViewModel().getStore('completedJobsListStore');
		
		store.filterBy(function(rec){
			return (rec.get('jobName').indexOf(jobNameField.getValue()) != -1);
		})
		
	}
	
	, onCompletedJobsJobPathFilterChange:function(jobPathField){
		var store = this.getViewModel().getStore('completedJobsListStore');
		
		store.filterBy(function(rec){
			return (rec.get('batchFilePath').indexOf(jobPathField.getValue()) != -1);
		});		
	}
	
	, onCompletedJobsDateFromFilterChange:function(df, nv, ov){
		var store = this.getViewModel().getStore('completedJobsListStore');
		
		nv = new Date(nv);
		
		store.filterBy(function(rec){
			return (new Date(rec.get('displayDate'))>= nv); 
		});
	}
	
	, onCompletedJobsDateToFilterChange:function(df, nv, ov){
		var store = this.getViewModel().getStore('completedJobsListStore');
		
		nv = new Date(nv);
		
		store.filterBy(function(rec){
			return (new Date(rec.get('displayDate'))<= nv); 
		});
	}
	
	, onCompletedJobsFilterReset:function(){
		var store = this.getViewModel().getStore('completedJobsListStore');
		store.clearFilter();
		this.resetFilterValues();
	}
	
	
	, resetFilterValues:function(){
		this.lookupReference('completedJobsListJobNameFilter').setValue('');
		this.lookupReference('completedJobsListJobPathFilter').setValue('');
	}
});
