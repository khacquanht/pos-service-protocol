var mainApp = angular.module('mainApp', ['ngRoute', 'ngAnimate', 'ui.bootstrap','angularUtils.directives.dirPagination']);
mainApp.constant('API_URL', 'http://localhost:83/api/'); //define CONST API_URL

// configure our routes
mainApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
       // route for the home page
        .when('/home', {
            templateUrl : 'views/company.html',
            controller  : 'companyController'
        })
         .when('/categories', {
            templateUrl : 'views/category.html',
            controller  : 'categoriesController'
        })
        .when('/currency', {
            templateUrl : 'views/currency.html',
            controller  : 'categoriesController'
        })
        // route for the about page
        .when('/about', {
            templateUrl : 'views/about.html',
            controller  : 'companyController'
        })

        // route for the contact page
        .when('/contact', {
            templateUrl : 'views/contact.html',
            controller  : 'contactController'
        });
  }]);
