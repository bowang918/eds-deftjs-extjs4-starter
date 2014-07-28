/* <debug> */
Ext.Loader.setConfig({
			enabled : true,
			paths : {
				'App' : 'app',
				'App.ux' : 'app/ux'
			}
		});
Ext.syncRequire(['Deft.mixin.Injectable', 'Deft.mixin.Controllable']);
/* </debug> */

//add direct api method
Ext.require('Ext.direct.*', function() {
			Ext.app.REMOTING_API.timeout = 600000; // 10 minutes.
			Ext.app.REMOTING_API.maxRetries = 0; // setting to zer0. no need
			// to call the service again
			Ext.direct.Manager.addProvider(Ext.app.REMOTING_API);
		});
Ext.define("App.Application", {
			extend : "Deft.mvc.Application",
			requires : ["App.view.Viewport"],

			init : function() {
				Ext.fly('circle').destroy();
				// Sets the default font-family to use for components that
				// support a glyph config
				Ext.setGlyphFontFamily('custome');
				// Configure the DeftJS IoC container
				Deft.Injector.configure({
						// companyStore : "App.store.CompanyStore",
						// companyService : "App.store.CompanyService"
						});

				// Set up QuickTips and create the Viewport
				Ext.tip.QuickTipManager.init();
				Ext.create("App.view.Viewport");
			}
		});

Ext.onReady(function() {
			Ext.create("App.Application");
		});
