/* global PF */
function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

function handleLoginRequest(xhr, status, args,dialogo) {
    if (args.validationFailed) {
        PF(dialogo).jq.effect("shake", {times: 5}, 100);
    } else {
        PF(dialogo).hide();
    }
}

/*
  Slidemenu
*/
(function() {
	var $body = document.body
	, $menu_trigger = $body.getElementsByClassName('menu-trigger')[0];

	if ( typeof $menu_trigger !== 'undefined' ) {
		$menu_trigger.addEventListener('click', function() {
			$body.className = ( $body.className == 'menu-active' )? '' : 'menu-active';
		});
    }

}).call(this);