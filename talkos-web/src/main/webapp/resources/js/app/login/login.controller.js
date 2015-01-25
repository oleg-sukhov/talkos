angular.module('talkos')
    .controller('LoginController', function ($scope, LoginService) {

        $scope.login = function() {
            LoginService.login($scope.username, $scope.password);
        };

    })
    .factory("LoginService", function($http) {
        return {
            login: function(username, password) {
                $http.post("/authenticate", {
                    username: username,
                    password: password
                }).success(function(data) {
                    alert("OK!!!");
                }).fail(function() {

                });
            }
        }
    });