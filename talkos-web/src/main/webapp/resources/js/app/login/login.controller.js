angular.module('talkos')
    .controller('LoginController', function ($scope, LoginService) {

        //LoginService.authenticate();
        $scope.credentials = {};
        $scope.login = function() {
            LoginService.login($scope);
        }
    })

    .factory("LoginService", function ($http, $rootScope, $location) {
        return {
            authenticate: function (callback) {

                $http.get('authenticate').success(function (data) {
                    $rootScope.authenticated = !!data.name;
                    callback && callback();
                }).error(function () {
                    $rootScope.authenticated = false;
                    callback && callback();
                });

            },

            login: function (controllerScope) {

                var data = this.prepareData(controllerScope);

                $http({
                    url: '/login',
                    method: "POST",
                    headers: {
                        "Content-type": "application/x-www-form-urlencoded"
                    },
                    data: data
                }).success(function (data, status, headers) {
                    $location.path("/home");
                    controllerScope.error = false;
                }).error(function (data, status, headers) {
                    $location.path("/login");
                    controllerScope.error = true;
                    controllerScope.loginErrorAlert = {
                        type: 'danger',
                        msg: 'Username or password was incorrect'
                    }
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