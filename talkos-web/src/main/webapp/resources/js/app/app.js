var talkos = angular.module('talkos', [
    'ngRoute',
    'ui.bootstrap',
    'ngCookies'
]);

talkos.config(function ($routeProvider) {
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
        otherwise({
            redirectTo: '/home'
        });
});

angular.module('talkos')
    .factory('AuthenticationChecker', ['AuthenticationService', function (AuthenticationService) {
            return {
                isAuthenticated: function () {
                    return AuthenticationService.isAuthenticated();
                }
            };
        }]);