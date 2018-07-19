/**
 * Author: Umang Goel
 * This class is the controller for the completed jobs view for the application. It is specified as
 * the "controller" of the completed jobs view class.
 */
Ext.define('ui.view.progressjobs.ProgressJobsController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.progressjobs'
    
	, onProgressJobsListRefreshButtonClick: function (btn) {
	    var grid = btn.up('grid');
	    this.loadProgressJobsList(grid);
	}
	
	, onProgressJobsListFilterChange: function (combo, newValue, oldValue) {
        this.loadProgressJobsList(combo.up('grid'));
    }
	
	, loadProgressJobsList: function (grid, options) {
        var store = this.getViewModel().getStore('progressJobsListStore');
        store.read(options);
    }
});
