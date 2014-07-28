Ext.define('App.controller.component.StorePanel', {
			extend : 'Deft.mvc.ViewController',
			stores : ['component.Users'],
			models : ['component.User'],
			control : {
				filtertextfield : {
					change : function() {
						var myStore = this.getComponentUsersStore();
						if (newValue) {
							myStore.clearFilter(true);
							myStore.filter('filter', newValue);
						} else {
							myStore.clearFilter();
						}
					}
				},
				storepanel : {
					itemclick : function() {
						this.getDeleteButton().enable();
					}
				},
				newButton : {
					click : function() {
						var newUser = this.getComponentUserModel().create({
									lastName : 'New',
									firstName : 'Person',
									email : 'new@email.com'
								});

						this.getComponentUsersStore().insert(0, newUser);
						this.getComponentUsersStore().getPlugin('storePanelRowEditing')
								.startEdit(0, 0);
					}
				},
				deleteButton : {
					click : function() {
						this.getDeleteButton().disable();
						var sm = this.getView().getSelectionModel();
						this.getComponentUsersStore().remove(sm.getSelection());
					}
				}
			}
		});