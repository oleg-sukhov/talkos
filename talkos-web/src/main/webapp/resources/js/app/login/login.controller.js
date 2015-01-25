angular.module('talkos')
    .controller('LoginController', function ($scope, LoginService) {

        LoginService.authenticate();
        $scope.credentials = {};
        $scope.login = function() {
            LoginService.login($scope);
        }
    })

    .factory("LoginService", function ($http, $rootScope) {
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

                $http.post('login', {credentials: controllerScope.credentials}, {
                    headers: {
                        "content-type": "application/x-www-form-urlencoded"
                    }
                }).success(function (data) {
                    this.authenticate(function () {
                        if ($rootScope.authenticated) {
                            $location.path("/");
                            controllerScope.error = false;
                        } else {
                            $location.path("/login");
                            controllerScope.error = true;
                        }
                    });
                }).error(function (data) {
                    $location.path("/login");
                    controllerScope.error = true;
                    $rootScope.authenticated = false;
                })
            }
        }
    });