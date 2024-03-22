pipeline{ 
  
  agent{
    label 'master'
  }
  tools {
    jdk 'Java17'
  }
  environment {
    MAVEN_OPTS = '-Xmx3072m'
}
  parameters{
    string name: 'mavenJDKVersion', trim: true, defaultValue: '1.17', description: 'Java Version'
  }
 
  options {
    ansiColor('xterm')
    parallelsAlwaysFailFast()
    durabilityHint 'PERFORMANCE_OPTIMIZED'
    buildDiscarder logRotator(artifactDaysToKeepStr: '30', artifactNumToKeepStr: '90', daysToKeepStr: '90', numToKeepStr: '270')
  }
  stages{
    stage('Clean WorkSpace'){
      steps{
        cleanWs deleteDirs: true, notFailBuild: true, patterns: [[pattern: 'deploy_*', type: 'INCLUDE']]
      }
    }
    
    stage('Maven Test') {
      steps {
              // Paso para ejecutar pruebas con Maven
              sh 'MAVEN_OPTS="-Xmx3072m" mvn clean package'
              stash includes: 'target/**', name: 'artifact'
            }
            
      post {
        always {
          junit '**/surefire-reports/TEST-*.xml'
        }
      }
    }
    
    stage("Imprimir en consola") {
      steps {
        script {
          // Aquí puedes colocar cualquier comando que desees ejecutar en el shell
          sh 'java --version'
        }
      }
    }
  stage("Construir contenedor Quarkus") {
  agent {
    label 'Host Subyacente Docker' 
  }
  steps {
    script {
       unstash 'artifact'
      // Define las variables de la imagen Docker
      //def dockerImageName = "devops-quarkus" // Nombre de la imagen Docker
      //def dockerImageTag = "latest" // Etiqueta de la imagen Docker

      // Construye la imagen Docker utilizando el Dockerfile proporcionado
      //sh "docker build -t ${dockerImageName}:${dockerImageTag} -f src/main/docker/Dockerfile.jvm ."
      bat "docker ps"
      bat "docker run --privileged --name some-docker -d --network some-network --network-alias docker -e DOCKER_TLS_CERTDIR=/certs -v some-docker-certs-ca:/certs/ca -v some-docker-certs-client:/certs/client docker:dind"
      bat "docker run --network some-network -e DOCKER_TLS_CERTDIR=/certs -v some-docker-certs-client:/certs/client:ro -v C:/Jenkins/Agent/workspace/CI_Quarkus/:/datos docker:latest sh -c 'ls'"
      bat "docker ps"
    }
  }
}
  }
}
