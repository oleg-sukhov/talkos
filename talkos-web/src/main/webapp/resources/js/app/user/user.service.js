angular.module('talkos')
    .service('UserService', ['$resource',
        function ($resource) {
            return {

                save: function(user) {
                    var userResource = $resource('/user', user, {'save': {method:'POST'}});
                    userResource.save();
                }
            };
        }]);
