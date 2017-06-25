/**
 * Created by heqiang on 6/24/17.
 */
const express = require('express');
const router = express.Router();
const db = require('../db/schema');
const fs = require('fs');
const path = require('path');
const sharp = require('sharp');


router.get('/', function (req, res) {
    res.redirect('/tools/encode');
});
router.get('/encode', function (req, res) {
    res.render('tools', {menu: 'encode'});
});
router.get('/json', function (req, res) {
    res.render('json', {menu: 'json'});
});
router.get('/pic', function (req, res) {
    res.render('pic', {menu: 'pic'});
});
router.get('/apk', function (req, res) {
    res.render('apk', {menu: 'apk'});
});

router.get('/crop', function (req, res) {
    let fileName = req.query.name;
    let src = path.join(__dirname, '../static/uploads') + '/' + fileName;
    let dst = path.join(__dirname, '../static/downloads') + '/' + fileName;
    if (!fileName) {
        res.status(404).send('缺少参数');
    }
    console.log(fileName);
    let width = req.query.width ? +req.query.width : 300;
    let height = req.query.height ? +req.query.height : 300;
    sharp(src).resize(width, height).toFile(dst, function (err) {
            console.log(err);
            res.download(dst, fileName, function (err) {
                if (err) {
                    // Handle error, but keep in mind the response may be partially-sent
                    // so check res.headersSent
                } else {
                    // decrement a download credit, etc.
                }
            });
        }
    );
});
router.get('/js', function (req, res) {
    res.render('js', {menu: 'js'});
});
module.exports = router;