angular.module('talkos')
    .config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'js/app/login/login.html',
                        controller: 'LoginController'
                    }
                }

            });
    });