/**
 * Created by He on 4/2/17.
 * 测试路由模块
 */
const express = require('express');
const router = express.Router();
const db = require('../db/schema');
const fs = require('fs');
const path = require('path');
const Base64 = require('js-base64').Base64;

router.get('/css', function (req, res, next) {
    res.render('css');
});

router.get('/db', function (req, res) {
    db.test(function (err, data) {
        res.send(data);
    });
});

router.get('/read', function (req, res) {
    console.log(__dirname);
    fs.readFile(path.join(__dirname, '../js/file/groups.json'), function (err, data) {
        try {
            let rs = JSON.parse(data);//wk推荐配置
            // console.log(rs);
            for (let group in rs) {
                for (let item of rs[group]) {
                    console.log(Base64.decode(item['TEXT']));
                }
            }
            res.render('read', {data: rs, Base64: Base64});
        } catch (e) {
            console.log(e);
            res.render('read', {data: [], Base64: Base64});
        }
    });

});

router.get('/dex', function (req, res) {
    const exec = require('child_process').exec;
    exec('bin/jadx -d out ../uploads/com.qq.reader.apk',
        {
            cwd: path.join(__dirname, '../static/jadx-0.6.1'),
            maxBuffer: 500 * 1024
        }, (error, stdout, stderr) => {
            if (error) {
                console.error(`exec error: ${error}`);
                return;
            }
            console.log('反编译完毕!');
        });
});
module.exports = router;