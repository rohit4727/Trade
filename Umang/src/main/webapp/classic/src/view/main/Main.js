/**
 * Author: Umang Goel
 * This class is the main view for the application. It is specified in app.js
 */

Ext.define('ui.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'app-main',
    
    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'ui.view.main.MainController',
        'ui.view.main.MainModel',
        'ui.view.main.JobList',
        'ui.view.main.ScheduleJobSummaryPanel'
    ],

    controller: 'main',
    viewModel: 'main',
    ui: 'navigation',

    tabBarHeaderPosition: 1,
    titleRotation: 0,
    tabRotation: 0,

    header: {
        layout: {
            align: 'stretchmax'
        },
        title: {
            bind: {
                text: 'Trade!'
            },
            flex: 0
        },
        iconCls: 'fa fa-line-chart'
    },

    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        }
    },

    responsiveConfig: {
        tall: {
            headerPosition: 'top'
        },
        wide: {
            headerPosition: 'left'
        }
    },

    defaults: {
        bodyPadding: 20,
        tabConfig: {
            plugins: 'responsive',
            responsiveConfig: {
                wide: {
                    iconAlign: 'left',
                    textAlign: 'left'
                },
                tall: {
                    iconAlign: 'top',
                    textAlign: 'center',
                    width: 120
                }
            }
        }
    },

    items: [{
        title: 'Run/Schedule Jobs',
        iconCls: 'fa-home',
        items: [
        	{
        		xtype:'schedulejobsummarypanel'
        	},
        	{
	            xtype: 'joblist'
	        	, height: Ext.getBody().getHeight()-20
        	}
    	]
    }, {
        title: 'Trade',
        iconCls: 'fa-user',
        bind: {
            html: '{loremIpsum}'
        }
    }]
});
