var talkos = angular.module('talkos', [
    'ngRoute',
    'ui.bootstrap',
    'ngCookies'
]);

talkos.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/login', {
                templateUrl: 'resources/js/app/login/login.html',
                controller: 'LoginController'
            }).
            when('/home', {
                templateUrl: 'resources/js/app/home/home.html',
                controller: 'HomeController',
                resolve: {
                    factory: checkRouting
                }
            }).
            otherwise({
                redirectTo: '/home'
            });
    }]);

var checkRouting= function ($q, $rootScope, $location, LoginService) {
    if (LoginService.isAuthenticated()) {
        return true;
    } else {
        var defered = $q.defer();
        defered.reject();
        $location.path("/login");
        return defered.promise;
    }
};