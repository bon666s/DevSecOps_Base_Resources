pipeline{ 
  agent{
    label 'master'
  }
  parameters{
    string name: 'mavenJDKVersion', trim: true, defaultValue: '1.17', description: 'Java Version'
  }
 
  options {
    ansiColor('xterm')
    parallelsAlwaysFailFast()
    durabilityHint 'PERFORMANCE_OPTIMIZED'
    copyArtifactPermission "/${env.AMB}/*"
    buildDiscarder logRotator(artifactDaysToKeepStr: '30', artifactNumToKeepStr: '90', daysToKeepStr: '90', numToKeepStr: '270')
  }
  stages{
    stage('Clean WorkSpace'){
      steps{
        cleanWs deleteDirs: true, notFailBuild: true, patterns: [[pattern: 'deploy_*', type: 'INCLUDE']]
      }
    }

    stage("Imprimir en consola") {
      steps {
        script {
          // Aquí puedes colocar cualquier comando que desees ejecutar en el shell
          sh 'echo "Hola mundo desde pipeline!"'
        }
      }
    }
  }
}