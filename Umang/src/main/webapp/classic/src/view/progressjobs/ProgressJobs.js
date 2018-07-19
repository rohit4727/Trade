/**
 * Author: Umang Goel
 * This class is the Completed Jobs view for the application.
 */

Ext.define('ui.view.progressjobs.ProgressJobs', {
    extend: 'Ext.panel.Panel',
    xtype: 'progressjobs',
    
    requires: [        
        'ui.view.progressjobs.ProgressJobsController',
        'ui.view.progressjobs.ProgressJobsViewModel',
        'ui.view.progressjobs.ProgressJobsList'
    ],

    controller: 'progressjobs',
    viewModel: 'progressjobs',

    items: [{
    	xtype:'progressjobslist'
		, reference: 'progressjobslist'
        , itemId: 'progressjobslist'
    }]
});
