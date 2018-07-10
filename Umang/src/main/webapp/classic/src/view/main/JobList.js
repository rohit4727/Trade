/**
 * This view is an example list of people.
 */
Ext.define('ui.view.main.JobList', {
    extend: 'Ext.grid.Panel',
    xtype: 'joblist',

    requires: [
        'ui.store.Personnel'
    	, 'ui.view.main.JobListToolbar'
    ],

    title: 'Jobs'

    , dockedItems: [                
        {
            xtype: 'joblisttoolbar'
            , dock: 'top'
        }
    ]


	, reference: 'joblist'
    , itemId: 'joblist'
    , store: {
        type: 'personnel'
    },

    columns: [
        { text: 'Job Name',  dataIndex: 'name', flex: 1 },
        { text: 'Date', dataIndex: 'date', type: 'date' },
        { text: 'Status', dataIndex: 'status' }
    ],

    listeners: {
        select: 'onItemSelected'
    	//, afterrender: 'onJobListAfterRender'
    }
});
