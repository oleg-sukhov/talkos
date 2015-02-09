angular.module('talkos')
    .controller('LoginController', function ($scope, LoginService) {

        //LoginService.authenticate();
        $scope.credentials = {};
        $scope.login = function() {
            LoginService.login($scope, "/home");
        }
    })

    .factory("LoginService", function ($http, $rootScope, $location) {
        return {
            isAuthenticated: function (callback) {
                $http.get('/isAuth').success(function (data) {
                    return true;
                }).error(function () {
                    return false;
                });
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