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
                //isAuthenticated:
            }

        }).
        otherwise({
            redirectTo: '/home'
        });
});

//talkos.run(function($rootScope, AuthenticationChecker) {
//    $rootScope.$on('$routeChangeStart', function(next, current) {
//        $rootScope.isDataLoaded = false;
//        $rootScope.isAuthenticated = false;
//        AuthenticationChecker.checkAuthenticate();
//    });
//
//    $rootScope.$on('$routeChangeSuccess', function(next, current) {
//        $rootScope.isDataLoaded = true;
//    });
//
//    $rootScope.$on('$routeChangeError', function(next, current) {
//        $rootScope.isDataLoaded = false;
//    });
//});
function AuthenticationChecker($scope) {
}

AuthenticationChecker.resolve = {
    isAuthenticate: function(Phone, $q) {
        // see: https://groups.google.com/forum/?fromgroups=#!topic/angular/DGf7yyD4Oc4
        var deferred = $q.defer();
        Phone.query(function(successData) {
            deferred.resolve(successData);
        }, function(errorData) {
            deferred.reject(); // you could optionally pass error data here
        });
        return deferred.promise;
    },
    delay: function($q, $defer) {
        var delay = $q.defer();
        $defer(delay.resolve, 1000);
        return delay.promise;
    }
};
talkos.provider("AuthenticationChecker", ["$q", "$rootScope", "$location", "$timeout", "AuthenticationService",
    function ($q, $rootScope, $location, $timeout, AuthenticationService) {
        return {
            checkAuthenticate: function () {
                var deferred = $q.defer();

                var successCallback = function (response) {
                    var isAuthenticated = response.authenticated;
                    $rootScope.isAuthenticated = isAuthenticated;
                    isAuthenticated ? deferred.resolve() : failureCallback(response);
                };

                var failureCallback = function (response) {
                    $rootScope.isAuthenticated = false;
                    $location.path("/login");
                    deferred.reject();
                };

                AuthenticationService.checkAuthenticate(successCallback, failureCallback);
                return deferred.promise;
            }
        };
    }]);
