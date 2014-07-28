Ext.define('App.controller.component.FormPanel', {
			extend : 'Deft.mvc.ViewController',
			control : {
				simpleCallButton : {
					'click' : function() {
						var me = this;
						formController.getRemark(function(result) {
									me.getView().getForm().setValues({
												remarks : result
											});
								}, this);
					}
				},
				formLoadButton : {
					'click' : function() {
						var me = this;
						me.getView().getForm().load();
					}
				},
				submitButton : {
					'click' : function() {
						var me = this;
						me.getView().getForm().submit({
									success : function(form, action) {
										me.getView().getForm().setValues({
													remarks : action.result.response
												});
									},
									scope : this
								});
					}
				}
			}
		});