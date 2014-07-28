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

Ext.define("App.Application", {
			extend : "Deft.mvc.Application",
			requires : ["App.view.Viewport"
//						, "App.store.CompanyStore",
//						"App.store.CompanyService"
					],

			init : function() {
				Ext.fly('circle').destroy();
				//Sets the default font-family to use for components that support a glyph config
				Ext.setGlyphFontFamily('custome');
				// Configure the DeftJS IoC container
				Deft.Injector.configure({
//							companyStore : "App.store.CompanyStore",
//							companyService : "App.store.CompanyService"
						});
						
				// Set up QuickTips and create the Viewport
				Ext.tip.QuickTipManager.init();
				Ext.create("App.view.Viewport");
			}
		});

Ext.onReady(function() {
			Ext.create("App.Application");
		});
