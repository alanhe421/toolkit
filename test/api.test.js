/**
 * Created by He on 18/09/2017.
 * API请求测试
 */
var request = require("supertest");
var express = require("express");
var app = express();

describe('GET /api/whois', function () {
    it('whois????', function (done) {
        request(app)
            .get('/api/whois')
            .set('Accept', 'application/json')
            .expect('Content-Type','text/html; charset=utf-8')
            .expect(200)
            .end(function (err, res) {
                console.log(res);
                if (err) {
                    done(err);
                }
                res.json.should.be.eql('jerryc');
                done();
            })
    });
});