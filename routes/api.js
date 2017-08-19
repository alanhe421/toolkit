/**
 * Created by He on 6/23/17.
 * API数据路由模块
 */
const express = require('express');
const router = express.Router();
const whois = require('whois');

//whois
router.get('/whois', function (req, res) {
    whois.lookup('alanhe.me', function (err, data) {
        res.send(data);
    })
});
module.exports = router;