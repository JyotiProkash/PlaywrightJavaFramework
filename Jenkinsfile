pipeline 
{
    agent any
    
    tools{
    	maven 'M2_HOME'
        jdk 'JAVA_HOME'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 echo("build step done")
            }
            post 
            {
                success
                {
                    echo("build success")
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/JyotiProkash/PlaywrightJavaFramework.git'
                    bat 'mvn clean package'
                    
                }
            }
        }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'TestReports', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'TestNG Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
        
        
    }
}