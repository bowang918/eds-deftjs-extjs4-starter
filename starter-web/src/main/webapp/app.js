/* <debug> */
Ext.Loader.setConfig({
			enabled : true,
			paths : {
				'App' : 'app',
				'App.ux' : 'app/ux',
				'Ext.ux' :'resources/extjs-gpl/4.2.2/ux'
			}
		});
Ext.syncRequire(['Deft.mixin.Injectable', 'Deft.mixin.Controllable']);
/* </debug> */

// add direct api method
Ext.require('Ext.direct.*', function() {
			Ext.app.REMOTING_API.timeout = 600000; // 10 minutes.
			Ext.app.REMOTING_API.maxRetries = 0; // setting to zer0. no need to call the service again
			var heartbeat = new Ext.direct.PollingProvider({
					type : 'polling',
					interval : 60 * 1000, // 1 minutes
					url : Ext.app.POLLING_URLS.heartbeat
				});
				
				Ext.direct.Manager.addProvider(Ext.app.REMOTING_API, heartbeat);
		});
		
Ext.define('App.Application', {
			extend : 'Deft.mvc.Application',
			requires : ['App.view.Viewport','App.ux.window.Notification'],

			init : function() {
				var me = this;
				Ext.fly('circle').destroy();
				// Sets the default font-family to use for components that
				// support a glyph config
				Ext.setGlyphFontFamily('custome');
				// Configure the DeftJS IoC container
				Deft.Injector.configure({
//						 companyStore : 'App.store.CompanyStore'
						// companyService : 'App.store.CompanyService'
						});

				// Set up QuickTips and create the Viewport
				Ext.tip.QuickTipManager.init();
				
				Ext.direct.Manager.on('event',function( event, provider, eOpts ){
					if (event.code && event.code === 'parse') {
						window.location.reload();
					}
				});
				
				Ext.direct.Manager.on('exception', function(event, eOpts) {
					Ext.create('widget.uxNotification', {
						title : 'Notification',
						position : 'br',
						iconCls : 'ux-notification-icon-error',
						autoCloseDelay : 5000,
						spacing : 20,
						html : event.message
					}).show();
				});
				
				me.setGlobalErrorHandler();
				Ext.create('App.view.Viewport');
			},
			setGlobalErrorHandler : function() {
				var me = this, gOldOnError = window.onerror;
				if(Ext.isFunction(gOldOnError)){
					window.onerror = Ext.Function.createSequence(gOldOnError, me.gloableErrorHandler);
				}else{
					window.onerror = me.gloableErrorHandler;
				}
			},
			gloableErrorHandler: function(errorMsg,url,lineNumber){
				var message = errorMsg + "-->" + url + "::" + lineNumber;
				logController.error(message);
			}
		});

Ext.onReady(function() {
			Ext.create('App.Application');
		});
