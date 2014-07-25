Ext.define('App.view.Content', {
			extend : 'Ext.tab.Panel',
			alias : 'widget.mainContent',
			itemId : 'tabPanel',
			activeTab : 0,
			items : {
				title : 'Default Tab',
				html : 'The first tab\'s content. Others may be added dynamically'
			}
		});