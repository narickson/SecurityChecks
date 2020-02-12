var exec = require('cordova/exec');

module.exports.SecurityPassFn = function(arg0, success, error) {
    exec(success, error, 'SecurityChecks', 'SecurityPassFn', [arg0]);
};