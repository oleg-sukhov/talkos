var talkos = angular.module('talkos', [
    'ngRoute',
    'ui.bootstrap'
]);

talkos.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/login', {
                templateUrl: 'js/app/login/login.html',
                controller: 'LoginController'
            }).
            otherwise({
                redirectTo: '/login'
            });
    }]);