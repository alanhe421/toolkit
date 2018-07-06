# Toolkit-Website
FROM node:8

# Create app directory
RUN mkdir -p /var/www/toolkit
WORKDIR /var/www/toolkit

# Install app dependencies
COPY . /var/www/toolkit/
RUN npm install nrm -g && nrm use taobao && npm install

EXPOSE 3001
CMD npm run start