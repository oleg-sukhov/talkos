angular.module('talkos')
    .controller('HomeController', function ($scope, $controller) {
        var authController = $controller('AuthenticateController');
        $scope.logout = function() {
            authController.logout();
        };
    });