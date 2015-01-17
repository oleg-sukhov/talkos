angular.module('talkos')
    .controller('LoginController', function ($scope) {
        $scope.loginErrorAlert = { type: 'danger', msg: 'Username or password was incorrect!', visible: false };

        $scope.showLoginErrorAlert = function () {
            $scope.loginErrorAlert.visible = true;
        };

        $scope.hideLoginErrorAlert = function () {
            $scope.loginErrorAlert.visible = false;
        };

    });