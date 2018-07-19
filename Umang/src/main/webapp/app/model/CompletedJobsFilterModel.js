Ext.define('ui.model.CompletedJobsFilterModel', {
	extend: 'ui.model.Base'
    , idProperty: 'id'
    , fields: [
    	// paging and sorting params
    	{ name: 'id' }
    	, { name: 'security', defaultValue: 'all'}
        , { name: 'sort' }
        , { name: 'dir' }
        , { name: 'total' }
        , { name: 'start', defaultValue: '0' }
        , { name: 'limit' , defaultValue: '20'}
    ]
});