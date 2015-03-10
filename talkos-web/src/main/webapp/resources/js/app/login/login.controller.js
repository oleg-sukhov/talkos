angular.module('talkos')
    .controller('AuthenticateController', ['$scope', '$location', 'AuthenticationService',
        function ($scope, $location, AuthenticationService) {

            $scope.credentials = {};
            $scope.activeErrors = [];
            var validationMessages = {
                'usernameValidation': {
                    message: "Username can't be empty",
                    placement: 'right'
                },
                'passwordValidation': {
                    message: "Password can't be empty",
                    placement: 'right'
                }
            };

            $scope.login = function () {
                AuthenticationService.login($scope, '/home');
            };
            $scope.logout = function () {
                AuthenticationService.logout($scope, '/login');
            };
            $scope.registration = function () {
                $location.path("/registration");
            };

            $scope.$watch('credentials.username', function (value) {
                $scope.validate(value, 'usernameValidation');
            });

            $scope.$watch('credentials.password', function (value) {
                $scope.validate(value, 'passwordValidation');
            });

            $scope.validate = function(value, controlName) {
                if($scope.isInvalid(value)) {
                    $scope.activeErrors[controlName] = validationMessages[controlName];
                } else {
                    delete $scope.activeErrors[controlName];
                }
            };

            $scope.isInvalid = function(value) {
                return !value;
            };

            $scope.isAllControlIsInvalid = function() {
                return !!Object.keys($scope.activeErrors).length;
            }
    }]);