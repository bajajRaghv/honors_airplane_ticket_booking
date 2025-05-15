pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = '13.200.243.122'
        JAR_NAME = 'demo-0.0.1-SNAPSHOT.jar'
    }

    stages {
        stage('Build Project') {
            steps {
                echo "Building project with Maven..."
                bat 'mvn clean package -DskipTests=true'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running test cases..."
                // Add test execution command here
            }
        }

        stage('Deploy to EC2') {
            steps {
                echo "Deploying ${JAR_NAME} to EC2..."
                withCredentials([sshUserPrivateKey(credentialsId: '1ff4987a-2ec1-421d-b4b1-3f13dc0ff9f8', keyFileVariable: 'SSH_KEY')]) {
                    bat """
                        echo Fixing SSH key permissions...
                        icacls "%SSH_KEY%" /inheritance:r
                        icacls "%SSH_KEY%" /grant:r "%USERNAME%:R"
                        icacls "%SSH_KEY%" /remove "BUILTIN\\Users"

                        echo Copying JAR to EC2...
                        scp -i %SSH_KEY% -o StrictHostKeyChecking=no target\\${JAR_NAME} ${EC2_USER}@${EC2_HOST}:/home/${EC2_USER}/${JAR_NAME}
                    """
                }
            }
        }


        stage('Run JAR on EC2') {
            steps {
                echo "Running Spring Boot application on EC2..."
                withCredentials([sshUserPrivateKey(credentialsId: '1ff4987a-2ec1-421d-b4b1-3f13dc0ff9f8', keyFileVariable: 'SSH_KEY')]) {
                    bat """
                        ssh -i %SSH_KEY% -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} ^
                        "pkill -f ${JAR_NAME} || echo 'No process to kill'; ^
                        nohup java -jar /home/${EC2_USER}/${JAR_NAME} > app.log 2>&1 &"
                    """
                }
            }
        }
    }
}
