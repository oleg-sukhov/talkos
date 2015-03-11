angular.module('talkos')
    .service('AccountService', ['$resource',
        function ($resource) {
            return {

                save: function(account) {
                    var accountResource = $resource('/register', {'save': {method:'POST'}});
                    accountResource.save(account);
                }
            };
        }]);
