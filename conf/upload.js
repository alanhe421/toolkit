/**
 * Created by He on 6/24/17.
 * 上传配置
 */
var upload = require('jquery-file-upload-middleware');
var path = require('path');

// configure upload middleware
upload.configure({
    uploadDir: path.join(__dirname, '../static/uploads'),
    uploadUrl: '/uploads',
    imageVersions: {
        thumbnail: {
            width: 80,
            height: 80
        }
    }
});
module.exports = upload;