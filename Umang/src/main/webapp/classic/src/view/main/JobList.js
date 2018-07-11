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

    title: 'Scheduled Jobs'

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
        { text: 'Path',  dataIndex: 'path', flex: 1 },
        { text: 'Date', dataIndex: 'date', type: 'date' },
        { text: 'Time', dataIndex: 'time', type: 'time' },
        { text: 'Status', dataIndex: 'status' },
        {
            xtype:'actioncolumn'
            , width:60
            , items: [
            	{
	                iconCls: 'x-fa fa-pencil'
	                , tooltip: 'Edit'
                	, handler: 'onScheduleJobListEditBtnClick'
//	                , handler: function(grid, rowIndex, colIndex) {
//	                    var rec = grid.getStore().getAt(rowIndex);
//	                    alert("Edit " + rec.get('firstname'))
//	                }
	            },
	            {
	            	iconCls: 'x-fa fa-times'
	                , tooltip: 'Delete'
                	, style:'float:right'
	                , handler: function(grid, rowIndex, colIndex) {
//	                    var rec = grid.getStore().getAt(rowIndex);
//	                    alert("Terminate " + rec.get('firstname'));
	                }
	            }
            ]
        }
    ],

    listeners: {
        select: 'onItemSelected'
    	//, afterrender: 'onJobListAfterRender'
    }
});
