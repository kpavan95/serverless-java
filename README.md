# serverless-java
Demo of serverless framework using java for PYT talks

## Agenda
This repository aims to be a example template to create Serverless project with the combination of (Serverless Framework, Java 8, Maven & AWS)

The following events are configured to trigger a lambda which essentially manages a DynamoDB table for Users and each event performs some sort of CRUD operation on the Table

The events configured are :
- APIGateway
- S3
- scheduled job using cloudwatch

This project also gives insight into configuring IAM roles to manage the different resources needed for the project.

## Dependencies
- Java 8
- Maven
- serverless
- AWS Account

## Setup and Project deployment
- First you will need a AWS account. 
- Here you will have to create a user who has the bare minimum permissions to deploy the project into AWS.

### Creating Serverless-deployment User in AWS
Follow the tutorial as explained [Here](https://serverless.com/framework/docs/providers/aws/guide/credentials/)

or
- Create a User with programmatic Access
- While attaching the policy, create a new policy with permissions provided in [this link](https://gist.github.com/ServerlessBot/7618156b8671840a539f405dea2704c8)
- download the CSV of aws access key and aws secrect key with you
- In your terminal, configure AWS profile by typing the following command and provide the keys as input.

```
$ aws configure
AWS Access Key ID [None]: ************
AWS Secret Access Key [None]: *********************
Default region name [None]: ap-south-1
Default output format [None]: json
```

You may change the name of the profile by editing the following file
> ~/.aws/credentials

replace the keyword "default" with keyword of your choice.

> Note : you must update the new keyword in the serverless.yaml in the line "profile: ${opt:profile, 'NEW_KEYWORD'}"

Now you may clone this repo and first build the project
> mvn clean install -U

Update the created jar name in the serverless.yaml file under key package.artifact if you have edited the project name in the pom.

Deploy the build into your AWS account
> serverless deploy --profile NEW_KEYWORD -v

Congrats!! the project is up and running