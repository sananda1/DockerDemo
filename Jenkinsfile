
node('ubuntu-slave-1') {

def hostport ="${hostport}";
def environment ="${environment}";
def ecr_repo ="${ecr_repo}";

stage 'Checkout'
    checkout poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '457a670b-4706-4912-8639-f80446cbb39e', url: 'https://github.com/sananda1/DockerDemo.git']]]

stage 'build docker image'
    sh 'docker build -t site .'
    
stage 'Tag and push the docker image to ECR'
    sh 'docker login -u AWS -p eyJwYXlsb2FkIjoid1k0amd2cjEwYk5XckJRQVhmZEd6Ykt3TkxoRTdBQTZBNVRWYTdFdGJPN1BsbXNweGhGNFV3R0ljTTFsUVFieWFOVnlZeWFBcE9SdzRMVFpwNjZOQjBnYTVKOStKcnVmTEhycFlVNWsxcHhXZ1BnNllsU2FzbHo3dmVDMkliRUFRTEliUTliSDdxc2JuKzdwa2ZHc0sxN1lLUnBacUx2TG9pUDdaSit3RkRJajVLcFkyUC9XakM4UnhtZ0lmUjJIOTd1cmFZbkdXR2RTZWVkd2hNVWVaNzZLcHhscXg1NkE2ZlRuK0U0QVJiZFBGbkVMaGxza2hOVFJrQmVLMDFJVGdKUmhIK2tRZjhjQ0JzNmtmUzlId3VHanRmY3F2NEszcmZMdDI4U01Ecm9HMWcydFBpcXdPaS9GeGMxT1RpTXVmeXA5ck5EYnJ1MUw4Z0xyWGxwOGtaZEVPR1BQWXdpR3pRNThyT3ZLSVUzQUNLZUVaUTNLeGtpUVZGNXBuakxkY2xRcHpVQ3JCQnBiRWM0bWVQcElXd0kyNGkxSHRaNHc5cHVPcTYyaklXTUo1eXBjNkx4QTNtNnNhdklnSVV5MUZFdjlwT0k2cE94Q0J1dmgycGJIMlZET05SK3dMTkpXeEVLaEk3eGF5enZxd3YwbDhUUUs4S01OUTNZTXlOaEdqNWpOaVptUExVOGdEMDBaQjY2eUhXTEZZakhIaDVldGl0NDVtMkJyS00rdkdMQzJnZHAzWm1mVTl4ZkI2QnB3MVVyT2ZVVjkrL2prQXdoUTlmeE92ekZ3dFRLU0dXVlFiK0k1alJWdXV0eUN4cjQ4TWk3em04MzV4SUNETVhOVnBtejNDeXNRbEE2b3pheFBYWHE2U0pCS0owZ1FoeEpyd2tTMllnK2JRbGRXUTJEdFArK0dJalp3NVlXVitaZEJqL3RhdnprRzVWTS9TbFUzS3hTb3Q2L09KaHVHUS9pc20yTml5VmRuK01oRHoyaWdSb25nM2kyenpNSW9BckkrdHIxd1U4UGRiMEZEYnIxNjBuUWVwN2lseHhnZHhOa1RYVmZjRFB2a2ZhVGtlNmlBYTBsOEwyUDhjN3ZVMGhQNGpoZkc5RnF4WC9XcTZ5ZHViNnRPODREWVNSSG1VL3JTVlEwNVRsbTU0OGFHZHp1bDUyeitFdTNYZDRZMmM4RWE2bDViRDR4eHFIWEJxZFBDTFBZV01YdmNQZ0VwZ2JxT1RUUW41dXZjUEZ0OFAwUHpua29Dall3ZWMvNGtiKzlobXU3RzAyYWllYnc4UHlsL1hVTVZKb1V3WEU2N1B6enNsWjNOZGZJeDVnanB3Vzc1SndFU2tRYXJKc09QU3FNTDZ6NDZMVklmZUVKVXlzekVEQWQ2WC9PdU9SR09BL0lNUzZTbWhnaGVXN2RlWXM5Tm4zdFFkUEpBaG9KbSIsImRhdGFrZXkiOiJBUUVCQUhod20wWWFJU0plUnRKbTVuMUc2dXFlZWtYdW9YWFBlNVVGY2U5UnE4LzE0d0FBQUg0d2ZBWUpLb1pJaHZjTkFRY0dvRzh3YlFJQkFEQm9CZ2txaGtpRzl3MEJCd0V3SGdZSllJWklBV1VEQkFFdU1CRUVEQVN1YVZsYnVMcElCenArVFFJQkVJQTdOSnZPMG0wSmtMVGZDRjFyKzNXdVlyb0Rrbjl1c2tpa0MzTW9QVjlGWDM1NkQwdi8zbGJPRm0yKzF4RUl1NWtVcFZGYlNrd01Vd3c4Z2RZPSIsInZlcnNpb24iOiIxIiwidHlwZSI6IkRBVEFfS0VZIn0= https://602062455022.dkr.ecr.us-east-1.amazonaws.com'
    sh "docker tag site:latest ${ecr_repo}:${environment}"
    sh 'docker images'
    sh "docker push ${ecr_repo}:${environment}"

stage 'Pull the docker image to docker agent'
sh '''
            sudo ssh -oStrictHostKeyChecking=no -i /home/jenkins/.ssh/atb-rotation.pem ubuntu@172.31.50.169 "docker pull ${ecr_repo}:${environment};
            docker rm -f $(docker ps -a | grep ${ecr_repo}:${environment})
            "'''
          
stage 'Run/Deploy the docker container'
    sh 'sudo ssh -oStrictHostKeyChecking=no -i /home/jenkins/.ssh/atb-rotation.pem ubuntu@172.31.50.169 "sudo docker run --env environment=${environment} -d -p ${hostport}:3000 ${ecr_repo}:${environment};"'

}
