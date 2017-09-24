var express = require('express');
var router = express.Router();
var messagesController = require('../controllers/messages-controller.js');

module.exports = function (app) {

    app.use('/', router.get('/', messagesController.displayMessageOfTheDay));
    app.use('/week', router.get('/week', messagesController.displayMessageOfTheWeek));
    app.use('/', router.get('/date', messagesController.getMessageByDate));

}
