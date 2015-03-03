angular.module('talkos')
    .controller('RegistrationController', ['$scope', '$location', 'UserService',
        function ($scope, $location, UserService) {

            $scope.save = function() {
                UserService.save($scope.data);
            };

            $scope.cancel = function() {
                $location.path('/home');
            }
        }]);
