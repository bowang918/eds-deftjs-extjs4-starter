Ext.define('App.controller.component.StorePanel', {
			extend : 'Deft.mvc.ViewController',
			requires : ['App.model.component.User'],
			control : {
				filtertextfield : {
					change : function() {
						var me = this, myStore = me.getView().getStore();
						if (newValue) {
							myStore.clearFilter(true);
							myStore.filter('filter', newValue);
						} else {
							myStore.clearFilter();
						}
					}
				},
				view : {
					itemclick : function() {
						this.getDeleteButton().enable();
					}
				},
				newButton : {
					click : function() {
						var me = this, view = me.getView(), store = view.getStore();
						var newUser = Ext.create('App.model.component.User', {
									lastName : 'New',
									firstName : 'Person',
									email : 'new@email.com'
								});

						store.insert(0, newUser);
						view.getPlugin('storePanelRowEditing').startEdit(0, 0);
					}
				},
				deleteButton : {
					click : function() {
						var me = this, store = me.getView().getStore();
						me.getDeleteButton().disable();
						var sm = me.getView().getSelectionModel();
						store.remove(sm.getSelection());
					}
				}
			}
		});