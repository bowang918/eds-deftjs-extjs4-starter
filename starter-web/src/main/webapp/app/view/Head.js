Ext.define('App.view.Head', {
			extend : 'Ext.container.Container',
			alias : 'widget.appHead',
			itemId : 'appHead',
			height : 35,
			border : false,
			layout : {
				type : 'hbox',
				align : 'stretch'
			},
			items : [{
						html : 'Starter',
						cls : 'appHeader'
					}, {
						xtype : 'tbspacer',
						flex : 1
					}, {
						xtype : 'button',
						// glyph : 72,
						text : 'Admin',
						margins : {
							top : 5,
							right : 0,
							bottom : 5,
							left : 0
						},
						menu : {
							items : [{
										text : 'Settings',
										// glyph : 0xe809,
										itemId : 'settingsButton'
									}, {
										text : 'logout',
										// glyph : 0xe802,
										href : 'logout',
										hrefTarget : '_self'
									}]
						}
					}]
		});
