angular.module('talkos')
    .controller('RegistrationController', ['$scope', 'UserService',
        function ($scope, UserService) {

            $scope.save = function() {
                UserService.save($scope.data);
            }
        }]);
