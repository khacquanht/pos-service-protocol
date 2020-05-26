pipeline {
    agent any 
	stages('build  BizServicesWahSiS') {
      	stage('Checkout') {
            steps{
        		checkout scm
          	}
        }

        stage('Check out develop'){
            when {
                branch 'develop'
            }
            steps{	
                dir('./lib/pos-biz-layer') {
                    git branch :'develop' ,url:'http://code.wahsis.net/scm/wpos/pos-biz-layer.git',credentialsId:'id-code-wahsis-net-key'
                    echo "checkout develop success"
                }
            }
        }
      	stage('Check out develop-jenkinsfile-update'){
            when {
                branch 'develop_jenkinsfile_update'
            }
            steps{	
                dir('./lib/pos-biz-layer') {
                    git branch :'develop' ,url:'http://code.wahsis.net/scm/wpos/pos-biz-layer.git',credentialsId:'id-code-wahsis-net-key'
                    echo "checkout develop_jenkinsfile_update success"
                }
            }
        }
        
        stage('Check out staging') {
          	when {
                branch 'staging'
            }
        	steps {		
                dir('./lib/pos-biz-layer') {
                    git branch :'staging' ,url:'http://code.wahsis.net/scm/wpos/pos-biz-layer.git',credentialsId:'id-code-wahsis-net-key'
                    echo "checkout staging success"
                }	
            }
        }
        
        stage('Check out master') {
  		  	when {
            		branch 'master'
            }
            steps {		
                dir('./lib/pos-biz-layer'){
                    git branch :'master' ,url:'http://code.wahsis.net/scm/wpos/pos-biz-layer.git',credentialsId:'id-code-wahsis-net-key'
                    echo "checkout master success"
                }	
            }
        } 


        stage('Build in local') {
            steps {
                withAnt(installation: 'jenkins-ant') {
                    sh "ant  -f ./lib/pos-biz-layer/BizServicesWahSiS -Dnb.internal.action.name=rebuild clean jar"
                    sh "ant  -f ./lib/pos-biz-layer/BizServices -Dnb.internal.action.name=rebuild clean jar"
                    sh "cp ./lib/pos-biz-layer/BizServicesWahSiS/dist/BizServicesWahSiS.jar ./APIGatewayWahSis/lib/"
                    sh "cp ./lib/pos-biz-layer/BizServices/dist/BizServices.jar ./APIGatewayWahSis/lib/"
                    sh "ant -f ./APIGatewayWahSis -Dnb.internal.action.name=rebuild clean jar"
                }
            }
        }
 
        stage('Backup data SERVER DEV'){
          	when {
                branch 'develop'
            }
          	steps{
            	echo "Backup data SERVER DEV begin"
            	//  sh "ssh root@10.9.12.141 '/mnt/sdb/docker/backup/script/java/pms-service.sh' "
            	echo "Backup data SERVER STAGING implement not yet"
            	//  sh "ssh root@10.9.12.142 '/mnt/sdb/docker/backup/script/java/pms-service.sh' "
            	echo "Backup data SERVER DEV end"
           }
        }
         stage('Backup data SERVER STAGING'){
           when{
           		branch 'staging'
           }
           steps{
              echo "Backup data SERVER STAGING begin"

              echo "Backup data SERVER STAGING implement not yet"
              sh "ssh root@10.9.12.141 '/mnt/sdb/docker/backup/script/java/core-service.sh' "
              sh "ssh root@10.9.12.142 '/mnt/sdb/docker/backup/script/java/core-service.sh' "
              echo "Backup data SERVER STAGING end"
           }
        }
         stage('Backup data SERVER LIVE'){
           when{
           		branch 'master'
           }
           steps{
                echo "Backup data SERVER LIVE begin"
                sh "ssh root@10.9.12.141 '/mnt/sdb/docker/backup/script/java/core-service.sh' "
                sh "ssh root@10.9.12.142 '/mnt/sdb/docker/backup/script/java/core-service.sh' "
                echo "Backup data SERVER LIVE end"
           }
        }

        stage('Copy Binaries to Server DEV') {
            when {
                branch 'develop'
            }
            steps {
              	//  sh "ls -la"
                sh "scp ./APIGatewayWahSis/lib/BizServices.jar root@10.9.13.204:/root/dev/wahsis_service/wahsis_gateway/dist/lib/"
                sh "scp ./APIGatewayWahSis/lib/BizServicesWahSiS.jar root@10.9.13.204:/root/dev/wahsis_service/wahsis_gateway/dist/lib/"
                sh "scp ./APIGatewayWahSis/dist/wahsis_gateway.jar root@10.9.13.204:/root/dev/wahsis_service/wahsis_gateway/dist/"
            }
        }
 
        stage('Copy Binaries to Server STAGING') {
            when {
                branch 'staging'
            }
            steps {
                sh "rsync -zave ssh ./APIGatewayWahSis/lib/* root@10.9.12.141:/mnt/sdb/docker/java/core-service/dist/lib/"
                sh "scp ./APIGatewayWahSis/dist/wahsis_gateway.jar root@10.9.12.141:/mnt/sdb/docker/java/core-service/dist/"
                sh "rsync -zave ssh ./APIGatewayWahSis/lib/* root@10.9.12.142:/mnt/sdb/docker/java/core-service/dist/lib/"
                sh "scp ./APIGatewayWahSis/dist/wahsis_gateway.jar root@10.9.12.142:/mnt/sdb/docker/java/core-service/dist/"
            }
        }	
        stage('Copy Binaries to Server MASTER') {
            when{
                branch 'master'
            }
            steps{
                sh "rsync -zave ssh ./APIGatewayWahSis/lib/* root@10.9.12.141:/mnt/sdb/docker/java/core-service/dist/lib/"
                sh "scp ./APIGatewayWahSis/dist/wahsis_gateway.jar root@10.9.12.141:/mnt/sdb/docker/java/core-service/dist/"

                sh "rsync -zave ssh ./APIGatewayWahSis/lib/* root@10.9.12.142:/mnt/sdb/docker/java/core-service/dist/lib/"
                sh "scp ./APIGatewayWahSis/dist/wahsis_gateway.jar root@10.9.12.142:/mnt/sdb/docker/java/core-service/dist/"
            }
        }	
      	  	
        stage('Update Service on Server DEV') {
            when {
                branch 'develop'
            }
            steps {
				sh "ssh root@10.9.13.204  '/root/dev/wahsis_service/wahsis_gateway/runserver restart' "
            }
        }

        stage('Update Service on Server STAGING') {
            when{
            	branch 'staging'
            }
            steps{
                  echo "Update Service on Server STAGING begin"
              	  sh "ssh root@10.9.12.141  'docker restart core-service' "
                  sh "ssh root@10.9.12.142  'docker restart core-service' "
                  echo "Update Service on Server STAGING end"
				
            }
        }
        
        stage('Update Service on Server MASTER') {
            when {
            	branch 'master'
            }
            steps {
              	  echo "Update Service on Server MASTER begin"
              	  sh "ssh root@10.9.12.141  'docker restart core-service' "
                  sh "ssh root@10.9.12.142  'docker restart core-service' "
                  echo "Update Service on Server MASTER end"
              
            }
        }	
    }
  	post {
    		always {
              	
				emailext (
              		to: "tuanhuyhcmus@gmail.com,huy.nguyen@wahsis.com,phuc.tran@wahsis.com,khanh.nguyen@wahsis.com,triet.truong@wahsis.com",
              		body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                	subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
                )
            }
		}  
}

