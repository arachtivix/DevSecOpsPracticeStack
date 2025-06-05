#!usr/bin/bash

# Register environment template
aws proton create-environment-template \
  --name "base-infra" \
  --display-name "Base Infrastructure" \
  --description "VPC and ECS cluster setup"

# Create template version
aws proton create-environment-template-version \
  --template-name "base-infra" \
  --source-s3-bucket "proton-cli-templates-650354402179" \
  --source-s3-key "environment-templates/base-infra/v1.tar.gz"



# Register pipeline component template
aws proton create-component-template \
  --name "pipeline" \
  --display-name "CI/CD Pipeline" \
  --description "CodePipeline for Spring Boot services"

# Create component template version
aws proton create-component-template-version \
  --template-name "pipeline" \
  --source-s3-bucket "proton-cli-templates-650354402179" \
  --source-s3-key "component-templates/pipeline/v1.tar.gz"