function verificarObrigatoriedade(xhr, status, args, dlg) {
	if (args.validationFailed) {
		PF(dlg).jq.effect("shake", {
			times : 5
		}, 100);
	} else {
		PF(dlg).hide();	
		//PF(tbl).clearFilters();
	}
}