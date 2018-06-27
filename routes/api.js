/**
 * Created by He on 6/23/17.
 * API数据路由模块
 */
const express = require('express');
const router = express.Router();
const whois = require('whois');
const crypto = require('crypto');
const multer = require('multer');
const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'static/uploads')
    },
    filename: function (req, file, cb) {
        var fileformat = (file.originalname).split('.');
        cb(null, file.fieldname + '-' + Date.now() + '.' + fileformat[fileformat.length - 1]);
    }
});
const upload = multer({storage: storage});
const exec = require('child_process').exec;

//whois
router.get('/whois', function (req, res) {
        whois.lookup('alanhe.me', function (err, data) {
            res.json({data:data});
        })
    });

router.get('/aes', function (req, res) {
    const text = req.query.text;
    const SECRET_KEY = req.query.secret;
    var cipher = crypto.createCipher('aes-256-cbc', SECRET_KEY);
    var crypted = cipher.update(text, 'utf8', 'hex');
    crypted += cipher.final('hex');//AES对称加密
    res.json({
        data: crypted
    });
});
// 上传
router.post('/upload', upload.array('file', 20), function (req, res, next) {
    res.json({status: 'ok'})
});

//上传APK文件
router.post('/upload/apk', upload.array('file', 20), function (req, res, next) {
    exec('cd build/jadx/ bin/jadx -d out lib/test.apk', (error, stdout, stderr) => {
        if (error) {
            console.error(`exec error: ${error}`);
            return;
        }
        console.log(`stdout: ${stdout}`);
        console.log(`stderr: ${stderr}`);
    });
    res.json({status: 'ok'})
});

module.exports = router;