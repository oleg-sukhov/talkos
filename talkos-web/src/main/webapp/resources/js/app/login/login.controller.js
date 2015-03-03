angular.module('talkos')
    .controller('AuthenticateController', ['$scope', '$location', 'AuthenticationService',
        function ($scope, $location, AuthenticationService) {

            $scope.credentials = {};
            $scope.login = function () {
                AuthenticationService.login($scope, '/home');
            };
            $scope.logout = function () {
                AuthenticationService.logout($scope, '/login');
            };
            $scope.registration = function () {
                $location.path("/registration");
            };

            $scope.$watch('credentials.username', function (newVal, oldVal) {
                $scope.signInState = !!newVal;
                $scope.tooltip = {
                    title: "Username can't be empty",
                    placement: 'right'
                };
            });
    }]);