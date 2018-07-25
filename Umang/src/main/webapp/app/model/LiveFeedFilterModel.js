Ext.define('ui.model.LiveFeedFilterModel', {
	extend: 'ui.model.Base'
    , idProperty: 'id'
    , fields: [
    	// paging and sorting params
    	{ name: 'id' }
    	, { name: 'security', defaultValue: 'All'}
    ]
});