angular.module('talkos')
    .service('UserService', ['$resource',
        function ($resource) {
            return {

                save: function(user) {
                    var userResource = $resource('/register', {'save': {method:'POST'}});
                    userResource.save(user);
                }
            };
        }]);
