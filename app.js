/**
 * Created by He on 11/5/16.
 * 后端入口文件
 */
const express = require('express');
const app = express();
const session = require('express-session');
const cookieParser = require('cookie-parser');
const path = require('path');
const routes = require('./routes/index');
const conf = require('./config');
app.engine('art', require('express-art-template'));
app.set('view engine', 'art');
app.set('views', __dirname + '/views');
app.set('trust proxy', 1); // trust first proxy
app.use(cookieParser());
app.use(session({
    // store: redisClient,
    secret: 'toolkit',
    saveUninitialized: true,
    resave: false
}));

// mount the router on the app
app.use('/', routes);
app.use('/', express.static(path.join(__dirname, '/public')));

// var log = require('./config/log');
// log.use(app);
// respond with "hello world" when a GET request is made to the homepage

app.listen(conf.server.port, '0.0.0.0', function () {
    console.log(`toolkit app listening on port ${conf.server.port}!`);
});