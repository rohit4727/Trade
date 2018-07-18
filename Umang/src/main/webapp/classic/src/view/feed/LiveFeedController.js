/**
 * Author: Umang Goel
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 */
Ext.define('ui.view.feed.LiveFeedController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.livefeed'
    
	, listen: {
        store: {
            '#liveFeedListStore': {
                beforeload: 'onLiveFeedListBeforeLoad'
            }
        }
    }

	, onLiveFeedListAfterRender: function (grid) {
	    var view = grid.getView();
	
	    if (view) {
	        grid.mon(view, 'scrollend', function (scroller) {
	            this.onLiveFeedListScrollEnd(scroller, grid)
	        }, this);
	    }
	
	    this.initializeSearch(grid);
	    this.loadLiveFeedList(grid);
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
	
	        waitingIntroductionLettersFilterModel.set('dir', dir);
	        waitingIntroductionLettersFilterModel.set('sort', id.substr(0, id.indexOf('-') > -1 ? id.indexOf('-') : id));
	    }
	
	    store.proxy.setParams(liveFeedListFilterModel.getData());
	}
	
	, onLiveFeedListFilterChange: function (combo, newValue, oldValue) {
        this.loadLiveFeedList(combo.up('grid'));
    }
	
	, loadLiveFeedList: function (grid, options) {
        var store = this.getViewModel().getStore(grid.storeName);
        store.read(options);
    }
});
