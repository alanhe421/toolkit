/**
 * Created by He on 11/5/16.
 */
var express = require('express');
var router = express.Router();

router.get('/', function (req, res) {
    res.redirect('/tools');
});

const toolsRouter = require('./tools');
const testRouter = require('./test');

router.use('/tools', toolsRouter);
router.use('/test', testRouter);

// Handle 404
// router.use(function (req, res) {
//     res.status(400);
//     res.render('404');
// });
module.exports = router;