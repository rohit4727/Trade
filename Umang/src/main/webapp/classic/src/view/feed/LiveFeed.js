/**
 * Author: Umang Goel
 * This class is the main view for the application. It is specified in app.js
 */

Ext.define('ui.view.feed.LiveFeed', {
    extend: 'Ext.panel.Panel',
    xtype: 'live-feed',
    
    requires: [        
        'ui.view.feed.LiveFeedController',
        'ui.view.feed.LiveFeedViewModel',
        'ui.view.feed.LiveFeedList'
    ],

    controller: 'livefeed',
    viewModel: 'livefeed',

    items: [{
    	xtype:'livefeedlist'
		, reference: 'livefeedlist'
        , itemId: 'livefeedlist'
    	, height: Ext.getBody().getHeight()-20
    }]
});
