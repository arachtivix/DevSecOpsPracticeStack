# Potato Catalog AWS Proton Project

This project contains AWS Proton templates for deploying a Potato Catalog service with:
- Base environment with VPC and foundational resources
- ECS-based deployment environments for Spring Boot 3 applications
- CI/CD pipeline components

## Project Structure
- `/environment-templates` - Contains the base environment template with VPC and ECS cluster
- `/service-templates` - Contains the Potato Catalog service template for ECS deployment
- `/component-templates` - Contains the CI/CD pipeline component template
- `/sample-app/potato-catalog` - Contains the Spring Boot 3 Potato Catalog application

## Prerequisites
- AWS CLI configured with appropriate permissions
- AWS Proton service enabled in your account
- GitHub repository for your application code
- Docker installed locally for testing

## Getting Started

### 1. Register Templates with AWS Proton

First, register the templates with AWS Proton:

```bash
# Register environment template
aws proton create-environment-template \
  --name "base-infra" \
  --display-name "Base Infrastructure" \
  --description "VPC and ECS cluster setup"

# Create template version
aws proton create-environment-template-version \
  --template-name "base-infra" \
  --source-s3-bucket "YOUR_BUCKET" \
  --source-s3-key "environment-templates/base-infra/v1.tar.gz"

# Register service template
aws proton create-service-template \
  --name "springboot-service" \
  --display-name "Spring Boot Service" \
  --description "ECS-based Spring Boot service"

# Create service template version
aws proton create-service-template-version \
  --template-name "springboot-service" \
  --source-s3-bucket "YOUR_BUCKET" \
  --source-s3-key "service-templates/springboot-service/v1.tar.gz"

# Register pipeline component template
aws proton create-component-template \
  --name "pipeline" \
  --display-name "CI/CD Pipeline" \
  --description "CodePipeline for Spring Boot services"

# Create component template version
aws proton create-component-template-version \
  --template-name "pipeline" \
  --source-s3-bucket "YOUR_BUCKET" \
  --source-s3-key "component-templates/pipeline/v1.tar.gz"
```

### 2. Create Base Environment

Create the base environment that will host your services:

```bash
aws proton create-environment \
  --name "development" \
  --template-name "base-infra" \
  --template-major-version "1" \
  --spec file://specs/environment-spec.yaml
```

### 3. Deploy Sample Application

1. Create a GitHub repository and push the sample application:

```bash
# Push sample app to your GitHub repository
cd sample-app
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
git push -u origin main
```

2. Create a GitHub connection in AWS:

```bash
# Create GitHub connection
aws codestar-connections create-connection \
  --provider-type GitHub \
  --connection-name my-github-connection
```

Follow the AWS console prompts to complete the GitHub connection authorization.

2. Create a service instance:

```bash
aws proton create-service \
  --name "my-spring-app" \
  --template-name "springboot-service" \
  --template-major-version "1" \
  --spec file://specs/service-spec.yaml \
  --environment-name "development"
```

3. Create the pipeline component:

```bash
aws proton create-component \
  --name "my-spring-app-pipeline" \
  --template-name "pipeline" \
  --template-major-version "1" \
  --service-name "my-spring-app" \
  --spec file://specs/pipeline-spec.yaml
```

## Template Details

### Base Infrastructure Template
- Creates a VPC with public and private subnets
- Sets up NAT Gateways for private subnet internet access
- Creates an ECS cluster for running services

### Spring Boot Service Template
- Defines ECS Task Definition and Service
- Sets up logging and IAM roles
- Configures security groups and networking

### Pipeline Component Template
- Creates CodePipeline with source and build stages
- Sets up CodeBuild project for building Spring Boot apps
- Configures necessary IAM roles and S3 bucket for artifacts

## Sample Application
The sample application in `/sample-app` is a basic Spring Boot 3 web service that:
- Uses Spring Boot 3.1.0
- Includes a simple REST endpoint
- Has a multi-stage Dockerfile for efficient builds
- Includes a buildspec.yml for AWS CodeBuild

## Contributing
Feel free to submit issues and enhancement requests!