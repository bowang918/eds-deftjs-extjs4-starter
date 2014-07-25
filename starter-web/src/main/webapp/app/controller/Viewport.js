Ext.define('App.controller.Viewport', {
			extend : "Deft.mvc.ViewController",
			// inject : ["companyStore"],

			config : {},

			init : function() {
				return this.callParent(arguments);
			},
			control : {
				// Most common configuration, using an itemId and listener
				navigation : {
					selectionchange : 'selectionchange'
				},
				tabPanel : {
					tabchange : 'tabChange'
				}
			},

			selectionchange : function(tree, selected, eOpts) {
				var me = this;
				if (selected && selected[0]) {
					var node = selected[0], state = {
						view : node.raw.view,
						viewConfig : {
							navigationId : node.raw.id,
							treePath : node.getPath()
						}
					};
					var tab = me.getTabPanel().child('panel[navigationId=' + state.viewConfig.navigationId + ']');
					if (!tab) {
						Ext.syncRequire(state.view,function(){
							var viewObject = Ext.create(state.view, state.viewConfig);
							tab = me.getTabPanel().add(viewObject);
						});
					}
					me.getTabPanel().setActiveTab(tab);
				}
			},

			tabChange : function(tabPanel, newCard, oldCard, eOpts) {
				var me = this;
				if(newCard&&newCard.treePath){
					me.getNavigation().selectPath(newCard.treePath);
				}
			}
		});