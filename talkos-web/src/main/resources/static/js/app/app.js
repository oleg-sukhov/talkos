var talkos = angular.module('talkos', [
    'ngRoute'
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