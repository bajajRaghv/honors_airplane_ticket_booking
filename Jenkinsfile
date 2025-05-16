pipeline {
  agent { label 'linux' }

  environment {
    EC2_USER            = 'ec2-user'
    EC2_HOST            = '13.200.243.122'
    JAR_NAME            = 'demo-0.0.1-SNAPSHOT.jar'
    SSH_CREDENTIALS_ID  = '1ff4987a-2ec1-421d-b4b1-3f13dc0ff9f8'
  }

  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }

    stage('Deploy & Run') {
      steps {
        ssh agent (credentials: [SSH_CREDENTIALS_ID]) {
          sh """
            scp -o StrictHostKeyChecking=no target/${JAR_NAME} ${EC2_USER}@${EC2_HOST}:/home/${EC2_USER}/
            ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} \\
                'pkill -f ${JAR_NAME} || true; nohup java -jar /home/${EC2_USER}/${JAR_NAME} > app.log 2>&1 &'
          """
        }
      }
    }
  }
}
