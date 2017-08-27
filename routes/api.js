/**
 * Created by He on 6/23/17.
 * API数据路由模块
 */
const express = require('express');
const router = express.Router();
const whois = require('whois');
const crypto = require('crypto');

//whois
router.get('/whois', function (req, res) {
    whois.lookup('alanhe.me', function (err, data) {
        res.send(data);
    })
});

router.get('/aes', function (req, res) {
    const text = req.query.text;
    const SECRET_KEY = req.query.secret;
    var cipher = crypto.createCipher('aes-256-cbc', SECRET_KEY);
    var crypted = cipher.update(text, 'utf8', 'hex');
    crypted += cipher.final('hex');//AES对称加密
    res.jsonp({
        data: crypted
    });
});



module.exports = router;