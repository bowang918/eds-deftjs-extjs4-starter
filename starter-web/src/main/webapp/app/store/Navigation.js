Ext.define('App.store.Navigation', {
			extend : 'Ext.data.TreeStore',
			root : {
				text : 'root',
				children : [{
							text : 'Form View',
							icon : '',
							id : 'formPanel',
							leaf : true,
							view : 'App.view.component.FormPanel'
						}, {
							text : 'Grid View',
							icon : '',
							id : 'storepanel',
							leaf : true,
							view : 'App.view.component.StorePanel'
						}]
			}
		});