var exec = require('cordova/exec');

module.exports.checkEmulator = function(arg0, success, error) {
    exec(success, error, 'SecurityChecks', 'checkEmulator', [arg0]);
};

module.exports.checkDebuggable = function(arg0, success, error) {
    exec(success, error, 'SecurityChecks', 'checkDebuggable', [arg0]);
};

module.exports.checkDownloadSource = function(arg0, success, error) {
    exec(success, error, 'SecurityChecks', 'checkDownloadSource', [arg0]);
};
