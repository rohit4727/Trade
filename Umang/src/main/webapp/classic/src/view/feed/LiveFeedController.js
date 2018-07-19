/**
 * Author: Umang Goel
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 */
Ext.define('ui.view.feed.LiveFeedController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.livefeed'
    , firstTimeLoad: false
	, listen: {
        store: {
            '#liveFeedListStore': {
                beforeload: 'onLiveFeedListBeforeLoad'
            	, load: 'onLiveFeedListLoad'
            }
        }
    }

	, onLiveFeedListLoad:function(store, records){
		if(!records || this.firstTimeLoad==true){return;}
		
		this.firstTimeLoad =true;
		
		var len = records.length
			, security = ['All'];
		
		for(var i=0, current;i<len;i++){
			security.push(records[i]['data']['security']);
		}
		
		this.lookupReference('livefeedsecurityfilter').store.loadData(security.map(function(item){ return [item]; }));
	}
	
	, onLiveFeedListAfterRender: function (grid) {  
		var me = this;
		/*Ext.TaskManager.start({
		  run: function() { 
			  me.loadLiveFeedList(grid);
		  },
		  interval: 1000
		});*/
		me.loadLiveFeedList(grid);
	}
	, onLiveFeedListBeforeLoad: function (store) {
	    var sorter = store.getSorters().getRange()[0]
	        , viewModel = this.getViewModel()
	        , liveFeedListFilterModel = viewModel.get('liveFeedListFilter')
	        , dir
	        , id;
	
	    if (sorter) {
	        dir = sorter.getDirection();
	        id = sorter.getId();
	
	        liveFeedListFilterModel.set('dir', dir);
	        liveFeedListFilterModel.set('sort', id.substr(0, id.indexOf('-') > -1 ? id.indexOf('-') : id));
	    }		
	    //store.proxy.setExtraParams(liveFeedListFilterModel.getData());
	    
	    var securityName = liveFeedListFilterModel.get('security');
	    store.proxy.api.read = "/TradeApp/getLiveFeedData/" + (Ext.isEmpty(securityName)?'all':securityName);
	}
	
	, onLiveFeedListFilterChange: function (combo, newValue, oldValue) {
        this.loadLiveFeedList(combo.up('grid'));
    }
	
	, loadLiveFeedList: function (grid, options) {
        var store = this.getViewModel().getStore('liveFeedListStore');
        store.read(options);
    }
	
	, onLiveFeedSecurityChange: function (tf) {
        var grid = tf.up('grid');
        this.loadLiveFeedList(grid);
    }
});
