var talkos = angular.module('talkos', [
    'ngRoute',
    'ui.bootstrap',
    'ngCookies'
]);

talkos.config(function ($routeProvider, $httpProvider) {
        $routeProvider.
            when('/login', {
                templateUrl: 'resources/js/app/login/login.html',
                controller: 'AuthenticateController'
            }).
            when('/home', {
                templateUrl: 'resources/js/app/home/home.html',
                controller: 'HomeController'
            }).
            otherwise({
                redirectTo: '/home'
            });
    }
);

talkos.run(function ($rootScope, $location, AuthenticationChecker) {
    $rootScope.$on("$routeChangeStart", function (event, next, current) {
        if (!AuthenticationChecker.isAuthenticate()) {
            if (next.templateUrl == "resources/js/app/login/login.html") {
            } else {
                $location.path("/login");
            }
        }
    });
});

talkos.factory('AuthenticationChecker', ["$q", "$rootScope", "$location", "$timeout", "AuthenticationService",
    function ($q, $rootScope, $location, $timeout, AuthenticationService) {
        return {
            isAuthenticate: function () {
                var deferred = $q.defer();

                var successCallback = function (response) {
                    var isAuthenticated = response.authenticated;
                    isAuthenticated ? deferred.resolve() : failureCallback(response);
                };

                var failureCallback = function (response) {
                    $location.path("/login");
                    deferred.reject();
                };

                AuthenticationService.isAuthenticated(successCallback, failureCallback);

                return $timeout(function() {
                    return deferred.promise;
                }, 2000);
                //return deferred.promise;
            }
        };
    }]);
