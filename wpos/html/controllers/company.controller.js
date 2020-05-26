mainApp.controller('TabsDemoCtrl', function ($scope, $window) {
//            $scope.tabs = [
//              { title:'Dynamic Title 1', content:'Dynamic content 1' },
//              { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
//            ];
//
//            $scope.alertMe = function() {
//              setTimeout(function() {
//                $window.alert('You\'ve selected the alert tab!');
//              });
//            };
//
//            $scope.model = {
//              name: 'Tabs'
//            };
          });

mainApp.controller('companyController', function ($scope,$http,API_URL) {
    
    $http.get(API_URL + "company")
        .success(function(response) {
            $scope.companies = response;
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
        $http.get(API_URL + "country").success(function(response) { $scope.country = response; });
        $http.get(API_URL + "companytype").success(function(response) { $scope.company_type = response;});
        switch (modalstate) {
            case 'add':
                $scope.form_title = "Add New Company";
                $scope.company = {};
                break;
            case 'edit':
                $scope.form_title = "Company Detail";
                $scope.id = id;
                $http.get(API_URL + 'company/detail?company_id=' + id)
                        .success(function(response) {
                            console.log(response);
                            $scope.company = response;
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
        var url = API_URL + "company";
        
        //append employee id to the URL if the form is in edit mode
        if (modalstate === 'edit'){
            url += "/update";
        }
        else
            url += "/add";
        
        //get all param values using jquery
        var data = $.param({
                company_id : $scope.company.company_id,
                company_code :"compancode",
                company_name :"lkjlk",
                address :"address",
                address2 :"address2",
                state: "aaaaa",
                city :"city",
                zip_code :"zipcode",
                country_id :3,
                province_code :"provincec",
                dictrict_code :"dictrict",
                wards_code :"wardscod",
                longitude :1,
                latitude :2,
                phone1 :"phone1",
                phone2 :"phone2",
                cell_phone :"cellphone",
                fax :"fax",
                email :"email",
                vat_number :"vatnumber",
                logo_path :"logo_path",
                parent_company_id :0,
                company_type_id :1,
                created_date :"2015-03-01",
                last_modified_date :"2015-03-01",
                created_by :1,
                last_modified_by :1
            });

        $http({
            method: 'POST',
            url: url,
            data: data,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(response) {
            console.log(response);
            alert("edited");
           //location.reload();
        }).error(function(response) {
            console.log(response);
            alert('An error has occured. Please check the log for details');
        });
    };
    
    //list branch
    $scope.listbranch = function(id) {
        $http.get(API_URL + "company/branch?company_id=" + id)
        .success(function(response) {
            $scope.branches = response;
            $scope.currentPage = 1;
            $scope.pageSize = 10;
        });
        
        $('#listBranch').modal('show');
    };

    //delete record
    $scope.confirmDelete = function(id) {
        var isConfirmDelete = confirm('Are you sure you want delete this record?');
        if (isConfirmDelete) {
            $http({
                method: 'POST',
                url: API_URL + 'company/del?company_id=' + id
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

mainApp.controller('PageController', function ($scope,$http,API_URL) {
   
  $scope.pageChangeHandler = function(num) {
    console.log('going to page ' + num);
  };

});




