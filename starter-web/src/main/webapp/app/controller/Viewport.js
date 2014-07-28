Ext.define('App.controller.Viewport', {
			extend : "Deft.mvc.ViewController",
			// inject : ["companyStore"],

			config : {},

			init : function() {
				var me = this;
				// Bind to StateChange Event
			    History.Adapter.bind(window,'statechange',function(){ // Note: We are using statechange instead of popstate
			        var state = History.getState(); // Note: We are using History.getState() instead of event.state
			        me.showTab(state);
			        
			    });
			    me.showTab(History.getState());
				return this.callParent(arguments);
			},
			control : {
				// Most common configuration, using an itemId and listener
				navigation : {
					selectionchange : 'onSelectionchange'
				},
				tabPanel : {
					tabchange : 'onTabChange',
					remove : 'onRemove'
				}
			},

			onSelectionchange : function(tree, selected, eOpts) {
				var me = this;
				if (selected && selected[0]) {
					var node = selected[0], state = {
						view : node.raw.view,
						viewConfig : {
							navigationId : node.raw.id,
							treePath : node.getPath()
						}
					};
					History.pushState(state, node.raw.text, "?vid=" + node.raw.id);
				}
			},
			
			showTab: function(state){
				var me = this;
				if(state&&state.data){
					var tab = me.getTabPanel().child('panel[navigationId=' + state.data.viewConfig.navigationId + ']');
					if (!tab) {
						Ext.syncRequire(state.data.view, function() {
									var viewObject = Ext.create(state.data.view, state.data.viewConfig);
									tab = me.getTabPanel().add(viewObject);
								});
					}
					me.getTabPanel().setActiveTab(tab);
				}
			},

			onTabChange : function(tabPanel, newCard, oldCard, eOpts) {
				var me = this;
				if (newCard && newCard.treePath) {
					me.getNavigation().selectPath(newCard.treePath);
				}
			},
			onRemove : function() {
				var me = this;
				if (me.getTabPanel().items.length === 0) {
					me.getNavigation().getSelectionModel().deselectAll();
				}
			}
		});
