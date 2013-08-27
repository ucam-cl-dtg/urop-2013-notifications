function bindSaveSettingsListener() {
	$('#save-account-settings').on('click', function(e) {
		e.preventDefault();
		
		var signups = $('#signups-opt-in').is(':checked');
		var questions = $('#questions-opt-in').is(':checked');
		var handins = $('#handins-opt-in').is(':checked');
		
		var email = $('#notification-sends-email').is(':checked');
		
	    $.ajax({
	    	type: 'PUT',
	    	url: prepareURL("account?signups=" + signups + "&questions=" + questions + "&handins=" + handins + "&notificationSendsEmail=" + email),
	    	success: function(data) {
	    		if (data.errors) {
	    			errorNotification(data.errors);
	    		} else {
	    			successNotification("Successfully updated account settings");
	    			location.reload();
	    		}
	    	},
	    	error: function(jqXHR, textStatus, error) {
	    		errorNotification("Could not perform AJAX request: " + error);
	    	}
	    });
	});
}

function bindNewApiKeyListener() {
	$('#generate-new-api-key').on('click', function() {
		$.ajax({
	    	type: 'GET',
	    	url: prepareURL("keys/new"),
	    	success: function(data) {
	    		if (data.errors) {
	    			errorNotification(data.errors);
	    		} else {
	    			successNotification("Successfully created a new API key");
	    			$('.api-keys').append('<li><div class="fixed-width-icon"><i class="icon icon-lock"></i></div>' + data.key + '</li>');
	    		}
	    	},
	    	error: function(jqXHR, textStatus, error) {
	    		errorNotification("Could not perform AJAX request: " + error);
	    	}
	    });
	});
}