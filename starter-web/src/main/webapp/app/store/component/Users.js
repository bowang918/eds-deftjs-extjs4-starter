Ext.define('App.store.component.Users', {
			extend : 'Ext.data.Store',
			model : 'App.model.component.User',
			autoLoad : true,
			pageSize : 25,
			remoteSort : true,
			remoteFilter : true,
			autoSync : true,
			proxy : {
				type : 'direct',
				api : {
					read : 'gridController.read',
					create : 'gridController.create',
					update : 'gridController.update',
					destroy : 'gridController.destroy'
				},
				reader : {
					root : 'records'
				}
			},
			sorters : [{
						property : 'lastName',
						direction : 'ASC'
					}]
		});