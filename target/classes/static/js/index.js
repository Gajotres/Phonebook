var imenik = angular.module('imenik', ['ngRoute','ngMaterial']);

imenik.run(function($rootScope) {
    //
});

imenik.config(function($routeProvider) {
    $routeProvider.
    when('/', {
        templateUrl: 'templates/search.html',
        controller: 'SearchListCtrl'
    })
    .when('/zaposlenik/:zaposlenikid', {
        templateUrl: 'templates/info.html',
        controller: 'EmployeeInfoCtrl'
    })
    .otherwise({
        redirectTo: '/'
    });
});

imenik.controller('SearchListCtrl', function($scope, $routeParams, Employees) {

});

imenik.controller('EmployeeInfoCtrl', function($scope, $routeParams, Employees) {
    
    /*$scope.showUnimportantFields = {
        showTitleNeobavezno : false,
        privateMail: false,
        accountFacebook: false,
        accountTwitter: false,
        accountLinkedIn: false,
        employeeSince: false,
        vacationFrom: false,
        vacationUntil: false
    };        
    
    Employees.find($routeParams.zaposlenikid, function(employee) {
        $scope.employee = employee;
        
        $scope.showUnimportantFields.showTitleNeobavezno = ($scope.employee.facebook || $scope.employee.twitter || $scope.employee.linkedin || $scope.employee.email_private) ? true : false;
        $scope.showUnimportantFields.accountFacebook     = ($scope.employee.facebook) ? true : false;
        $scope.showUnimportantFields.accountTwitter      = ($scope.employee.twitter) ? true : false;
        $scope.showUnimportantFields.accountLinkedIn     = ($scope.employee.linkedin) ? true : false;
        $scope.showUnimportantFields.privateMail         = ($scope.employee.email_private) ? true : false;
    });*/
});

imenik.controller('HeaderCtrl',function($scope){

});

imenik.factory('Employees', function($http) {
    
    var cachedEmployees;
    var cashedTerm;

    function getEmployeesData(term, method, callback) {

        if(cachedEmployees && cashedTerm === term) {
            callback(cachedEmployees);
        } else {
            var url = 'handler.php/' + method + '/' + Base64.encode(term);

            $http.get(url).success(function(data) {
                cachedEmployees = data;
                cashedTerm = term;
                
                callback(data);
            });
        }
    }

    return {
        list : getEmployeesData,
        find : function(id, callback) {
            if(cachedEmployees) {
                var employee = cachedEmployees.filter(function(entry) {
                    return entry.id == id;
                })[0];
                callback(employee);                 
            } else {             
                var url = 'handler.php/employee/' + Base64.encode(id);

                $http.get(url).success(function(data) {                      
                    callback(data[0]);
                });                
            }
        }
    };

});