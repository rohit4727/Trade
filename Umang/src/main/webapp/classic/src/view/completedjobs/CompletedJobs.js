/**
 * Author: Umang Goel
 * This class is the Completed Jobs view for the application.
 */

Ext.define('ui.view.completedjobs.CompletedJobs', {
    extend: 'Ext.panel.Panel',
    xtype: 'completedjobs',
    
    requires: [        
        'ui.view.completedjobs.CompletedJobsController',
        'ui.view.completedjobs.CompletedJobsViewModel',
        'ui.view.completedjobs.CompletedJobsList'
    ],

    controller: 'completedjobs',
    viewModel: 'completedjobs',

    items: [{
    	xtype:'completedjobslist'
		, reference: 'completedjobslist'
        , itemId: 'completedjobslist'
    	, height: Ext.getBody().getHeight()-20
    }]
});
