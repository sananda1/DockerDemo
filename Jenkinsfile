
node('ubuntu-slave-1') {

def hostport ="${hostport}";
def environment ="${environment}";
def ecr_repo ="${ecr_repo}";

stage 'Checkout'
    checkout poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '457a670b-4706-4912-8639-f80446cbb39e', url: 'https://github.com/sananda1/DockerDemo.git']]]
stage 'Unit Testing'
    
stage 'Code Analysis'
    
stage 'build docker image'
    sh 'docker build -t site .'
    
stage 'Tag and push the docker image to ECR'
    sh 'docker login -u AWS -p eyJwYXlsb2FkIjoiVVZ5cXFtRXYvWEVrd2xPaVRrSnhlUDhmQlB6RlJHUXFYZitVSDEvd3dUOVdiMUQ0U241MVVrekM5SmhTRmZaUkx2bEF5L1RtMmdnaXRRWklobklRV3pralRjVGhJOFhFWXgvY04zL3hRL0piY2lYWnE0cXkvSHd6VXhNRFh1WlR6Zk1QK25UaUdlNnVIZm9oTDhJYnBOaDBUS3JubzJWaDNXQ3NLSmtyMjI1QVhFbklMYUVWVFB2TUZ4S2tyQ2dmZS8zalNyVzBkaHh2b3NyR2pnczI4NWcvMngwRFFnTnZGbUtjY3lIWVRvRzNuQ2pPaFVpdTd6cDFxQW1DdmJ3ck5xVzQzbSsvWGVGdERSdndibjAwNVRZbmZ0NVhHdVpNQ2ZaT3RIMzAwL1kwTnkzUEJlL1NoV216dGRQQnY5eHhYU1ovalZGaXV6YUxRRmdqTmVJWXMweGhxR3c4N2pEUzk0dWRQOXJYUlNHRUhFMjhOakhkcjcwa1NwZjB2NkR4OWZuTkRNZVlvbVpkcFhGdk5nYTAzeS90SHBHKzU1NGNQUytLbkluY1lwcUJTRDFpamg0L1paY1Y2d2hGSStlQ2o4MnMzNHNkd3BOOGRCRnBKeWRRY3dXb2RaMjZ0K0NLTGZUQlZwZmN2bWJUMUgvOGVTS3kyU3NtTzg5WWpENmZZN1FTaVNrRTY3SHp0KzAzcDlCa3FTblEzT2t4a0VuazZsZnpkQ1pjWTY1eXlkRGpTNWd4NWhhN3RCTDErZGRkbExXTk9VUXdEbGdmQ0ppWUF6ZkRuSjVwUS9jazdUOGF4QzFzRE5DNVUrenJEREhnSDFoZ0RzYW1YaEw1VTFpL0RYQkwyTmFTdHR2dVVuMzJKTkxoa1ZmY0xGQUNzZXdzRDhEQytkZXBXaUVQenE1R01vVW03dmVRdE1tSXY0U0pCRWgzdW1QYnQ2WHdVQ2Fuak9jd1lqdmdyUWY3NTB1YUs1VG9UeTc0Mm5HNXlEQXBFeG5HOW1NUVZjei9VWk1vU3pNODBMTmJwVGxHOGNiRGJ5Q1luQTVGbzYrdEh5MXNsamoxallFZUp3YlJITU56MXNLRW9BSVlDUXJ1YVk5cTVyS0dEQjU2WHh1eHJ6azE2K29DT1owV3ZBYkJQekFQd1E1d0NZMjVnaFZxa3JtTzlMNkk1YWxqRFdyTno5WHJKSlgvS2x5WUhiY284RXBhTzBiekF6VFR0NHBKOUhzZXNkTWNGWFRvYy90VjNLTlQyc29ZTW1ObUh3YkZtQ0FtVm1tTTduaXFPdlRFM3Q5Z1Q4REpOV250Nit0ZHFXSzJzN015TVpQRWZXbXRmcjRNSzBTRUJMWTBRZmdYOGZYUEtZVTk0YTcvbng3eFNoTnJ4MkZRaTB4bElFWXFDNTdyakRVSUFaRHBSbHJ3Wk85WVVjakV3R0hUTmQ5MSIsImRhdGFrZXkiOiJBUUVCQUhod20wWWFJU0plUnRKbTVuMUc2dXFlZWtYdW9YWFBlNVVGY2U5UnE4LzE0d0FBQUg0d2ZBWUpLb1pJaHZjTkFRY0dvRzh3YlFJQkFEQm9CZ2txaGtpRzl3MEJCd0V3SGdZSllJWklBV1VEQkFFdU1CRUVES0MrR1BDdjJyRmlkeC9qVlFJQkVJQTd0M3ZlcnppWmhIWmxPVkZSZFcyTGJySSsvcHo1TzJMZWVCZ3YwZFNzNkxzaC83Z2NNeHpGRzRGMHVsOTgwV1NveHM4OVFDMHF5MTBJL1lrPSIsInZlcnNpb24iOiIxIiwidHlwZSI6IkRBVEFfS0VZIn0= https://602062455022.dkr.ecr.us-east-1.amazonaws.com'
    sh "docker tag site:latest ${ecr_repo}:${environment}"
    sh 'docker images'
    sh "docker push ${ecr_repo}:${environment}"

stage 'Pull the docker image to docker agent'
sh '''
            sudo ssh -oStrictHostKeyChecking=no -i /home/jenkins/.ssh/atb-rotation.pem ubuntu@172.31.50.169 "docker pull ${ecr_repo}:${environment};
            
            "'''
          
stage 'Run/Deploy the docker container'
    sh 'sudo ssh -oStrictHostKeyChecking=no -i /home/jenkins/.ssh/atb-rotation.pem ubuntu@172.31.50.169 "sudo docker run --env environment=${environment} -d -p ${hostport}:3000 ${ecr_repo}:${environment};"'

}
