window.jsf = {};

jsf.hideOnSuccess = function (dlg, args) {
    if (args && !args.validationFailed) {
        PF(dlg).hide();
    }
};

jsf.alert = function (msg) {
    alert(msg);
};