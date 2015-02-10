angular.module('talkos')
    .controller('AuthenticateController', function ($scope, AuthenticationService) {

        $scope.credentials = {};
        $scope.login = function() {
            AuthenticationService.login($scope, "/home");
        };

        $scope.logout = function() {
            AuthenticationService.logout($scope, "/login");
        }
    })

    .factory("AuthenticationService", function ($http, $rootScope, $location) {
        return {
            isAuthenticated: function (successCallback, failureCallback) {
                return $http({
                    url: '/isAuthenticated',
                    headers: {
                        'Content-Type': "application/json"
                    },
                    data: ''
                }).success(successCallback).error(failureCallback);
            },

            login: function (controllerScope, pathToRedirect) {
                var data = this.prepareData(controllerScope);
                $http({
                    url: '/login',
                    method: "POST",
                    headers: {
                        "Content-type": "application/x-www-form-urlencoded"
                    },
                    data: data
                }).success(function (data, status, headers) {
                    $location.path(pathToRedirect);
                    controllerScope.error = false;
                }).error(function (data, status, headers) {
                    $location.path("/login");
                    controllerScope.error = true;
                    controllerScope.loginErrorAlert = {
                        type: 'danger',
                        msg: 'Username or password was incorrect'
                    };
                })
            },

            logout: function (pathToRedirect) {
                $http({
                    url: '/logout',
                    method: "POST",
                    headers: {
                        "Content-type": "application/x-www-form-urlencoded"
                    },
                    data: ""
                }).success(function (data, status, headers) {
                    $location.path(pathToRedirect);
                }).error(function (data, status, headers) {
                    $location.path("/login");
                })
            },

            prepareData: function(controllerScope) {
                var data = [];
                data.push("username=");
                data.push(controllerScope.credentials.username);
                data.push("&password=");
                data.push(controllerScope.credentials.password);
                return data.join("");
            }
        }
    });