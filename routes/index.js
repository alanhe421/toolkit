/**
 * Created by He on 11/5/16.
 */
var express = require('express');
var router = express.Router();
var upload = require('../conf/upload');
router.get('/', function (req, res) {
    res.redirect('/tools');
});
const toolsRouter = require('./tools');
const testRouter = require('./test');
const apiRouter = require('./api');
router.use('/uploadImg', upload.fileHandler());
router.use('/tools', toolsRouter);
router.use('/test', testRouter);
router.use('/api',apiRouter);
// Handle 404
// router.use(function (req, res) {
//     res.status(400);
//     res.render('404');
// });
module.exports = router;