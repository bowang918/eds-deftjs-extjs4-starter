Ext.define('App.view.component.FormPanel', {
			extend : 'Ext.form.Panel',
			itemId : 'formPanel',
			bodyPadding : 10,
			title : 'FORM_LOAD, FORM_POST and SIMPLE',

			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			controller : 'App.controller.component.FormPanel',

			closable : true,
			constructor : function(config) {
				var me = this;
				Ext.applyIf(me, {
							api : {
								load : formController.getFormData,
								submit : formController.handleFormSubmit
							},
							paramsAsHash : true
						});
				this.callParent(arguments);
			},

			initComponent : function() {
				var me = this;

				Ext.applyIf(me, {
							items : [{
										xtype : 'textfield',
										name : 'osName',
										fieldLabel : 'OS Name',
										allowBlank : false
									}, {
										xtype : 'textfield',
										name : 'osVersion',
										fieldLabel : 'OS Version'
									}, {
										xtype : 'numberfield',
										name : 'availableProcessors',
										fieldLabel : 'Available Processors'
									}, {
										xtype : 'datefield',
										name : 'date',
										fieldLabel : 'Date'
									}, {
										xtype : 'filefield',
										name : 'screenshot',
										fieldLabel : 'Screenshot'
									}, {
										xtype : 'textareafield',
										name : 'remarks',
										fieldLabel : 'Remarks',
										flex : 1
									}],

							buttons : [{
										xtype : 'button',
										action : 'simple',
										itemId : 'simpleCallButton',
										text : 'Call SIMPLE method'
									}, {
										xtype : 'button',
										text : 'Call FORM_LOAD method',
										itemId : 'formLoadButton',
										action : 'form_load'
									}, {
										text : 'Submit',
										action : 'submit',
										itemId : 'submitButton',
										disabled : true,
										formBind : true
									}]

						});

				me.callParent(arguments);
			}
		});