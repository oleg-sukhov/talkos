angular.module('talkos')
    .controller('RegistrationController', ['$scope', '$location', 'AccountService',
        function ($scope, $location, AccountService) {

            $scope.save = function() {
                AccountService.save($scope.data);
            };

            $scope.cancel = function() {
                $location.path('/home');
            }
        }]);
