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
                controller: 'HomeController',
                resolve: {
                    factory: checkAuthenticated
                }
            }).
            otherwise({
                redirectTo: '/home'
            });

        $httpProvider.interceptors.push('AuthenticateInterceptor');
    }
);

var checkAuthenticated = function ($q, $rootScope, $location, AuthenticationService) {
    var deferred = $q.defer();

    var successCallback = function(response) {
        var isAuthenticated = response.authenticated;
        isAuthenticated ? deferred.resolve() : failureCallback(response);
    };

    var failureCallback = function(response) {
        deferred.reject();
        $location.url('/login');
    };

    AuthenticationService.isAuthenticated(successCallback, failureCallback);

    return deferred.promise;
};