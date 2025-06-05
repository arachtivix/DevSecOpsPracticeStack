# Potato Catalog AWS Proton Project

This project contains AWS Proton templates for deploying a Potato Catalog service with:
- Base environment with VPC and foundational resources
- ECS-based deployment environments for Spring Boot 3 applications
- CI/CD pipeline components

## Project Structure
- `/environment-templates` - Contains the base environment template with VPC and ECS cluster
- `/service-templates` - Contains the Potato Catalog service template for ECS deployment and pipeline components
- `/sample-app/potato-catalog` - Contains the Spring Boot 3 Potato Catalog application

## Prerequisites
- AWS CLI configured with appropriate permissions
- AWS Proton service enabled in your account
- GitHub repository for your infrastructure and application code
- Docker installed locally for testing

### Setting up AWS Proton Service Role

Before you can use AWS Proton, you need to create and configure the AWS Proton service role. This role allows Proton to create and manage resources on your behalf. Here's how to set it up:

1. Create the service role:
```bash
aws iam create-role \
  --role-name AWSProtonServiceRole \
  --assume-role-policy-document '{
    "Version": "2012-10-17",
    "Statement": [
      {
        "Effect": "Allow",
        "Principal": {
          "Service": "proton.amazonaws.com"
        },
        "Action": "sts:AssumeRole"
      }
    ]
  }'
```

2. Attach the required managed policies:
```bash
# Attach the AWS Proton Service Role policy
aws iam attach-role-policy \
  --role-name AWSProtonServiceRole \
  --policy-arn arn:aws:iam::aws:policy/service-role/AWSProtonServiceRolePolicyForProton

# Attach the AWSServiceCatalogAdminFullAccess policy (if using Service Catalog)
aws iam attach-role-policy \
  --role-name AWSProtonServiceRole \
  --policy-arn arn:aws:iam::aws:policy/AWSServiceCatalogAdminFullAccess
```

3. Register the role with AWS Proton:
```bash
aws proton update-account-settings \
  --region your-region \
  --pipeline-service-role-arn arn:aws:iam::your-account-id:role/AWSProtonServiceRole
```

Note: Replace `your-region` and `your-account-id` with your actual AWS region and account ID.


## Template Details

### Base Infrastructure Template
- Creates a VPC with public and private subnets
- Sets up NAT Gateways for private subnet internet access
- Creates an ECS cluster for running services

### Service Template
- Defines ECS Task Definition and Service
- Sets up logging and IAM roles
- Configures security groups and networking
- Includes pipeline component configuration for CI/CD
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