/**
 * Author: Umang Goel * 
 * toolbar for Schedule job list.
 */

Ext.define('ui.view.main.JobListToolbar', {
    extend: 'Ext.toolbar.Toolbar'
    , xtype: 'joblisttoolbar'
    , defaults: {
        labelSeparator: ''
        , labelWidth: 30
        , labelStyle: 'font-weight:bold;'
    }
    , items: [   
        {
            xtype: 'button'
            , iconCls: 'x-fa fa-refresh'
            , handler: 'onScheduleJobListRefreshButtonClick'
        },
        '->',
        {
            xtype: 'button'
            , iconCls: 'x-fa fa-play-circle'
            , text: 'Run/Schedule Jobs'
            , handler: 'onJobListRunOrScheduleJobBtnClick'
        }
    ]    
});