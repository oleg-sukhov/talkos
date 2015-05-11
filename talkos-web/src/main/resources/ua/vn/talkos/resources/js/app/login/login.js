angular.module('talkos')
    .service('AuthenticationService', function ($q, $http, $rootScope, $location) {
        return {
            isAuthenticated: function () {
                var deferred = $q.defer();

                var successCallback = function (response) {
                    var isAuthenticated = response.authenticated;
                    $rootScope.isAuthenticated = isAuthenticated;
                    $rootScope.loggedUser = response.loggedAccount;

                    isAuthenticated ? deferred.resolve() : failureCallback(response);
                };

                var failureCallback = function () {
                    $rootScope.isAuthenticated = false;
                    $location.path('/login');
                    deferred.reject();
                };

                this.checkAuthenticate(successCallback, failureCallback);

                return deferred.promise;
            },

            checkAuthenticate: function (successCallback, failureCallback) {
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
                    method: 'POST',
                    headers: {
                        'Content-type': 'application/x-www-form-urlencoded'
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
                    method: 'POST',
                    headers: {
                        'Content-type': 'application/x-www-form-urlencoded'
                    },
                    data: ""
                }).success(function (data, status, headers) {
                    $location.path(pathToRedirect);
                }).error(function (data, status, headers) {
                    $location.path('/login');
                })
            },

            prepareData: function(controllerScope) {
                var data = [];
                data.push('username=');
                data.push(controllerScope.credentials.username);
                data.push('&password=');
                data.push(controllerScope.credentials.password);
                return data.join('');
            }
        }
    });