var talkos = angular.module('talkos', [
    'ngRoute',
    'ui.bootstrap',
    'ngCookies'
]);

talkos.config(function ($routeProvider, AuthenticationChecker) {
        $routeProvider.
            when('/login', {
                templateUrl: 'resources/js/app/login/login.html',
                controller: 'AuthenticateController'
            }).
            when('/home', {
                templateUrl: 'resources/js/app/home/home.html',
                controller: 'HomeController',
                resolve: {
                    isAuthenticated: AuthenticationChecker.isAuthenticate()
                }
            }).
            otherwise({
                redirectTo: '/home'
            });
    }
);

//talkos.run(function ($rootScope, $location, AuthenticationChecker) {
//    $rootScope.$on("$routeChangeStart", function (event, next, current) {
//        if (!isLoginPageUrl(next.templateUrl) && !AuthenticationChecker.isAuthenticate()) {
//            $location.path("/login");
//        }
//    });
//
//    var isLoginPageUrl = function(url) {
//        return url == "resources/js/app/login/login.html";
//    }
//});

talkos.service('AuthenticationChecker', ["$q", "$rootScope", "$location", "$timeout", "AuthenticationService",
    function ($q, $rootScope, $location, $timeout, AuthenticationService) {
        return {
            isAuthenticate: function () {
                var deferred = $q.defer();

                var successCallback = function (response) {
                    var isAuthenticated = response.authenticated;
                    isAuthenticated ? deferred.resolve() : failureCallback(response);
                };

                var failureCallback = function (response) {
                    //$location.path("/login");
                    deferred.reject();
                };

                AuthenticationService.isAuthenticated(successCallback, failureCallback);
                return deferred.promise;
            }
        };
    }]);
