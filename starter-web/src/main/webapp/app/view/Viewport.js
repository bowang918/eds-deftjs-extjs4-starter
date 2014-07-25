/**
 * 
 */
Ext.define('App.view.Viewport', {
			extend : 'Ext.Viewport',
			requires : ['App.view.Head', 'App.view.Navigation',
					'App.view.Content'],
			layout : 'border',
			items : [{
						region : 'north',
						xtype : 'appHead'
					}, {
						region : 'west',
						xtype : 'navigation'
					}, {
						region : 'center',
						xtype : 'mainContent'
					}]
		});