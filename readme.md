# Simple secured API project

This project is a simple secured API with Spring boot and Spring security with Json Web Token (JWT)

## Run the project

### Generate KeyPair

**Create a directory for KeyPair**
```bash
$ mkdir -P /path/to/security
$ cd /path/to/security
```
**Generate a Private Key 2048-bit RSA**
```bash
$ openssl genrsa -out private_key.pem 2048
```
**Convert the Private Key to PKCS#8 Format**
```bash
$ openssl pkcs8 -topk8 -inform PEM -outform DER -in private_key.pem -out private_key.der -nocrypt
```
**Generate the Public Key**
```bash
$ openssl rsa -in private_key.pem -pubout -outform DER -out public_key.der
```

### Env file

Create a .env file with the following content:

```
SRV_SECURITY_DIR=/path/to/security
```

### Build the project

```bash
$ cd simple-secured-api-sample
$ mvn install
```

### Docker

#### Install Docker

To be able to run this project with docker, you need at first to install Docker and Docker compose
* [Check this](https://docs.docker.com/engine/installation/linux/ubuntu/) to install docker
* [Check this](https://docs.docker.com/compose/install/) to install docker-compose

#### Build the image

Now that docker is installed, we need to build the image before run it:

```bash
$ cd simple-secured-api-sample
$ sudo docker-compose build . -t secured-api
```

#### Run the image

```bash
$ sudo docker-compose up -d
```

The `-d` stands for daemon
