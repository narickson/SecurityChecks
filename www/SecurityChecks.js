var exec = require('cordova/exec');

module.exports.validate = function(arg0, success, error) {
    exec(success, error, 'SecurityChecks', 'validate', [arg0]);
};
