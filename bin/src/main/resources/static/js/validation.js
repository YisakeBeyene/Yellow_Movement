$(function () {
	
	$.validator.setDefaults({
	    errorClass: 'help-block',
	    highlight: function(element) {
	      $(element)
	        .closest('.form-group')
	        .addClass('has-error');
	    },
	    unhighlight: function(element) {
	      $(element)
	        .closest('.form-group')
	        .removeClass('has-error');
	    },
	    errorPlacement: function (error, element) {
	      if (element.prop('type') === 'checkbox') {
	        error.insertAfter(element.parent());
	      } else {
	        error.insertAfter(element);
	      }
	    }
	  });
	
    $('#loginForm').validate({

        rules:{
            loginEmail: {
                required: true,
                email: true
            },

            loginPassword:{
                required: true
            }
        },
        messages:{
            loginEmail:{
                required: 'Email is Required to Sign in.',
                email: 'Please enter a <em>valid</em> email.'
            },
            loginPassword:{
                required: 'Password is also Required to Sign in.'
            }
        }

    })
})
$(function(){
	
	$.validator.setDefaults({
	    errorClass: 'help-block',
	    highlight: function(element) {
	      $(element)
	        .closest('.form-group')
	        .addClass('has-error');
	    },
	    unhighlight: function(element) {
	      $(element)
	        .closest('.form-group')
	        .removeClass('has-error');
	    },
	    errorPlacement: function (error, element) {
	      if (element.prop('type') === 'checkbox') {
	        error.insertAfter(element.parent());
	      } else {
	        error.insertAfter(element);
	      }
	    }
	  });
	
	$.validator.addMethod('strongPassword',function(value, element){
		return this.optional(element)
		|| value.length >= 6
		&& /\d/.test(value)
		&& /[a-z]/i.test(value);
	},'Your password must be at least 6 characters long and contain at least one number\'.')
	
	$("#createAccountForm").validate({
		rules:{
			signUpEmail:{
				required:true,
				email:true
			
				
			},
			signUpName:{
				required:true,
				lettersonly:true
			},
			signUpPassword:{
				required:true,
				strongPassword:true
			},
			signUpConfirmPassword:{
				required:true,
				equalTo: "#signUpPassword"
			}
	
		},
		messages:{
			signUpEmail:{
				required:'Please enter an email address.',
				email: 'Please enter a <em>valid</em> email'
				
			}
		}
	})
})