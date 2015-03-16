angular.module('talkos')
    .controller('HomeController', ['AuthenticationService',
        function ($scope, $controller, AuthenticationService) {
            //this.resolve = {
            //    delay: function ($q, $defer) {
            //        var delay = $q.defer();
            //        $defer(delay.resolve, 1000);
            //        return delay.promise;
            //    }
            //};
        }]);