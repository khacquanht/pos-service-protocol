//mainApp.controller('TabsDemoCtrl', function ($scope, $window) {
////            $scope.tabs = [
////              { title:'Dynamic Title 1', content:'Dynamic content 1' },
////              { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
////            ];
////
////            $scope.alertMe = function() {
////              setTimeout(function() {
////                $window.alert('You\'ve selected the alert tab!');
////              });
////            };
////
////            $scope.model = {
////              name: 'Tabs'
////            };
//          });

mainApp.controller('categoriesController', function ($scope,$http,API_URL) {
    
    $http.get(API_URL + "category")
        .success(function(response) {
            $scope.categories = response;
            $scope.currentPage = 1;
            $scope.pageSize = 10;
        });
  
    //sort
    $scope.sort = function(keyname){
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
    }
  
   //show modal form
    $scope.toggle = function(modalstate, id) {
        $scope.modalstate = modalstate;
        switch (modalstate) {
            case 'add':
                $scope.form_title = "Add New Categories Item";
                $scope.category = {};
                break;
            case 'edit':
                $scope.form_title = "Category Item Detail";
                $scope.id = id;
                $http.get(API_URL + 'category/detail?category_id=' + id)
                        .success(function(response) {
                            console.log(response);
                            $scope.category = response;
                        });
                break;
            default:
                break;
        }
        console.log(modalstate);
        $('#myModal').modal('show');
    };

    //save new record / update existing record
    $scope.save = function(modalstate, id) {
        var url = API_URL + "category";
        
        //append employee id to the URL if the form is in edit mode
        if (modalstate === 'edit'){
            url += "/update";
        }
        else
            url += "/add";
        
        //get all param values using jquery
        //var ngay = new Date();
        var data =             
                {
                "category_id:" : $scope.category.category_id,
                "category_name" : $scope.category.category_name,
                "status":1, 
                "description": $scope.category.description,               
                "parent_category_Id":0,
                "created_date":"2016-03-02",
                "last_modified_date": "2016-03-02",
                "created_by":1,
                "last_modified_by":1
                };          
        $http({
            method: 'POST',
            url: url,
            data: data,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(response) {
            console.log(response);
           // location.reload();
        }).error(function(response) {
            console.log(response);
            alert('An error has occured. Please check the log for details');
        });
    };
    


    
    
 
        
        
 
    

    //delete record
    $scope.confirmDelete = function(id) {
        var isConfirmDelete = confirm('Are you sure you want delete this record?');
        if (isConfirmDelete) {
            $http({
                method: 'POST',
                url: API_URL + 'category/del?category_id=' + id
            }).
                    success(function(data) {
                        console.log(data);
                       location.reload();
                    }).
                    error(function(data) {
                        console.log(data);
                        alert('Unable to delete this record');
                    });
        } else {
            return false;
        }
    };
    $scope.pageChangeHandler = function(num) {
      console.log('page changed to ' + num);
  };
  

});
//
//mainApp.controller('PageController', function ($scope,$http,API_URL) {
//   
//  $scope.pageChangeHandler = function(num) {
//    console.log('going to page ' + num);
//  };
//
//});




