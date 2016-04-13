var imenik = angular.module('imenik', ['ngRoute','ngMaterial','angular.filter']);

imenik.run(function($rootScope) {
    //
});

imenik.config(function($routeProvider, $locationProvider) {
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

    //$locationProvider.html5Mode(true);  
});

imenik.controller('SearchListCtrl', [
    '$scope',
    '$routeParams',
    '$location',
    'Employees',
    'Storage',
    'croatianConstants',
    function ($scope, $routeParams, $location, Employees, Storage, croatianConstants) {

        $scope.letters = croatianConstants.letters.split(' ');
        $scope.usedLetters = [];
        $scope.employees = [];
        $scope.formatedEmployees = [];
        $scope.organizations = {};  

        $scope.input = {
            term : 'Dragan',
            letter: '',
            org: ''
        }

        // Fill Letters selectbox
        var counter = 0;

        angular.forEach($scope.letters, function(value){
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
                Storage.setSearchTerm(term);
                Employees.list(term, method, function(employees) {
                    $scope.employees = employees;
                    $scope.reformatResponse();
                });            
            }
        };

        $scope.reformatResponse = function() {

            $scope.formatedEmployees = [];
            var currentLetter = '';
            var lastLetter = '';

            var sortedByLetter = {
                letter: '',
                employee: []
            };

            angular.forEach($scope.employees, function(employee, index){

                if(index !== 0) {
                    lastLetter = currentLetter;
                }

                currentLetter = $scope.getFirstLetter(employee.lastName);

                if(lastLetter !== currentLetter) {

                    if(sortedByLetter.letter !== '') {
                        $scope.formatedEmployees.push(sortedByLetter);
                    }

                    sortedByLetter = {letter: '', employee: []};
                    sortedByLetter.letter = currentLetter;
                    sortedByLetter.employee.push(employee);

                } else {
                    sortedByLetter.employee.push(employee);
                }

                if($scope.employees.length == index + 1) {
                    $scope.formatedEmployees.push(sortedByLetter);
                }
            }); 
        }

        $scope.getFirstLetter = function(lastName) {
            switch (lastName.substring(0, 2).toUpperCase()) {
                case 'NJ':
                    return "NJ";
                    break;
                case 'LJ':
                    return "LJ";
                    break;
                case 'DŽ':
                    return "DZ";
                    break;
                default:
                    return lastName.charAt(0);
                    break;
            }
        }

        $scope.changePage = function(id) {
            $location.path('/zaposlenik/' + id);
        }

        // Reload data when route changes from EmployeeInfoCtrl to SearchListCtrl; data is reloaded from the Service Storage 
        if(!$scope.input.term){
            $scope.input.term = Storage.getSearchTerm();
        }    
        $scope.findEmployeesByTerm();
    }
]);

imenik.controller('EmployeeInfoCtrl', [
    '$scope',
    '$routeParams',
    'Employees',
    function ($scope, $routeParams, Employees) {

        $scope.showUnimportantFields = {
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
            
            /*$scope.showUnimportantFields.showTitleNeobavezno = ($scope.employee.facebook || $scope.employee.twitter || $scope.employee.linkedin || $scope.employee.email_private) ? true : false;
            $scope.showUnimportantFields.accountFacebook     = ($scope.employee.facebook) ? true : false;
            $scope.showUnimportantFields.accountTwitter      = ($scope.employee.twitter) ? true : false;
            $scope.showUnimportantFields.accountLinkedIn     = ($scope.employee.linkedin) ? true : false;
            $scope.showUnimportantFields.privateMail         = ($scope.employee.email_private) ? true : false;*/
        });

    }
]);

imenik.controller('HeaderCtrl', [
    '$scope',
    function ($scope, $routeParams) {

    }
]);

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
         
            var url = 'http://localhost:8080/employees/id/' + id;

            $http.get(url).success(function(data) {
                callback(data);
            });
        }
    };

});

imenik.factory('Storage', function() {
    
    Storage.search = {
        searchTerm : ''
    };
    
    return {
        getSearchTerm : function() {
            return Storage.search.searchTerm;
        },
        setSearchTerm : function(value) {
            Storage.search.searchTerm = value;
        }
    };    
});

/* TODO: Testirati da li je potrebno provjeravati i duljinu input stringa */
imenik.filter('formatMobile', function(croatianConstants){
    return function(input) {
        if (input && input.length > 0 && input !== 'NULL') {
            
            var formatMobileNumber = [input.substring(1).slice(0, 2), " ", input.substring(1).slice(2)].join('');
            formatMobileNumber = [formatMobileNumber.slice(0, 7), " ", formatMobileNumber.slice(7)].join('');
            
            return (croatianConstants.companyMobilePrefixPattern.test(input)) ? '2' + input.match(/...$/) + ' (' + croatianConstants.countryPrefix + formatMobileNumber + ')' : input;
        } else {
            return '-';
        }
    };
});

/* TODO: Testirati da li je potrebno provjeravati i duljinu input stringa */
imenik.filter('formatTelephone', function(croatianConstants){
    return function(input) {
        if (input && input.length > 0 && input !== 'NULL') {
            return (input.length === 4) ? input + ' (' + croatianConstants.countryPrefix + croatianConstants.companyTelephonePrefixPattern + input.substring(1) + ')' : input;
        } else {
            return '-';
        }
    };
});

imenik.filter('formatText', function($sce){
    return function(input, additionalText) {
        return $sce.trustAsHtml(input && input !== '-' ? additionalText + '<span class="bold">' + input + '</span> &nbsp;&nbsp;' : '');
    };
});

imenik.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });
 
                event.preventDefault();
            }
        });
    };
});