pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = '13.200.243.122'
        SSH_CREDENTIALS_ID = '1ff4987a-2ec1-421d-b4b1-3f13dc0ff9f8'
        JAR_NAME = 'demo-0.0.1-SNAPSHOT.jar'
    }

    stages {
        stage('Build Project') {
            steps {
                echo "Building project with Maven..."
                bat  'mvn clean package -DskipTests=false'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running test cases..."

            }
        }

        stage('Deploy to EC2') {
            steps {
                echo "Deploying ${JAR_NAME} to EC2..."
                sshagent (credentials: ["${SSH_CREDENTIALS_ID}"]) {
                    bat  """
                        scp -o StrictHostKeyChecking=no target/${JAR_NAME} ${EC2_USER}@${EC2_HOST}:/home/${EC2_USER}/${JAR_NAME}
                    """
                }
            }
        }

        stage('Run JAR on EC2') {
            steps {
                echo "Running Spring Boot application on EC2..."
                sshagent (credentials: ["${SSH_CREDENTIALS_ID}"]) {
                    bat  """
                        ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} '
                            echo "Killing existing app if running..."
                            pkill -f ${JAR_NAME} || true
                            echo "Starting new instance..."
                            nohup java -jar /home/${EC2_USER}/${JAR_NAME} > app.log 2>&1 &
                        '
                    """
                }
            }
        }
    }
}
