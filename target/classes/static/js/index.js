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

imenik.controller('SearchListCtrl', function($scope, $routeParams, Employees, croatianConstants) {

    $scope.letters = [];
    $scope.organizations = {};  

    $scope.input = {
        term : '',
        letter: '',
        org: ''
    }

    // Fill Letters selectbox
    var counter = 0;

    angular.forEach(croatianConstants.letters.split(" "), function(value){
         $scope.letters.push({id: counter++, name : value});
    });    
    $scope.selectedLetter = { id: 1, name: 'A' };

    $scope.findEmployeesByTerm = function() {
        if($scope.input.term.length > 2) {
            $scope.findEmployees($scope.input.term, 'term');
        }
    };

    $scope.findEmployees = function(term, method) {
        if(term) {
            Employees.list(term, method, function(employees) {
                $scope.employees = employees;
            });            
        }
    };    
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

imenik.constant("croatianConstants", {
    "letters": "A B C Ć Č D DŽ Đ E F G H I J K L LJ M N NJ O P R S Š T U V Z Ž",
    "countryPrefix" : "+385",
    "companyMobilePrefixPattern" : /^0914501/,
    "companyTelephonePrefixPattern" : " 1 4600",
    "dummy_image" : "dummy_user.jpg"
});

imenik.factory('Employees', function($http) {
    
    var cachedEmployees;
    var cashedTerm;

    function getEmployeesData(term, method, callback) {

        if(cachedEmployees && cashedTerm === term) {
            callback(cachedEmployees);
        } else {
            var url = 'http://localhost:8080/employees/' + method + '/' + term;

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
                var url = 'http://localhost:8080/employees/id/' + id;

                $http.get(url).success(function(data) {                      
                    callback(data[0]);
                });                
            }
        }
    };

});