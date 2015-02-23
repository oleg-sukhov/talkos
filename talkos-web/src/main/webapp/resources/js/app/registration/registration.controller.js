angular.module('talkos')
    .controller('RegistrationController', ['$scope', 'UserService',
        function ($scope, UserService) {

            $scope.save = function() {
                var user = {
                    login: $scope.data.login,
                    password: $scope.data.password,
                    email: $scope.data.email,
                    firstname: $scope.data.first_name,
                    lastname: $scope.data.last_name
                };

                UserService.save(user);
            }
        }]);
