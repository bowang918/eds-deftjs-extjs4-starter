Ext.define('App.view.Navigation', {
			extend : 'Ext.tree.Panel',
			alias : 'widget.navigation',
			collapsible : true,
			title : i18n.navigation,
			width : 150,
			itemId : 'navigation',
			layout : 'fit',
			minWidth : 100,
			maxWidth : 200,
			border : true,
			rootVisible : false,
			animate : false,
			store : Ext.create('App.store.Navigation')
		});