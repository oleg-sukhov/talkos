var talkos = angular.module('talkos', [
    'ngRoute', 'ui.bootstrap', 'ngCookies', 'flow', 'ngResource']);

talkos.config(['$routeProvider',
    function ($routeProvider) {

        $routeProvider.
            when('/login', {
                templateUrl: 'resources/js/app/login/login.html',
                controller: 'AuthenticateController'
            }).
            when('/home', {
                templateUrl: 'resources/js/app/home/home.html',
                controller: 'HomeController',
                resolve: {
                    isAuthenticated: function(AuthenticationChecker) {
                        return AuthenticationChecker.isAuthenticated();
                    }
                }

            }).
            when('/registration', {
                templateUrl: 'resources/js/app/registration/registration.html',
                controller: 'RegistrationController'
            }).
            otherwise({
                redirectTo: '/home'
            });
    }]);

angular.module('talkos')
    .factory('AuthenticationChecker', ['AuthenticationService', function (AuthenticationService) {
        return {
            isAuthenticated: function () {
                return AuthenticationService.isAuthenticated();
            }
        };
    }]);