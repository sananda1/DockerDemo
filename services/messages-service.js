var request = require('request');
var logger = require('../logger');
var config = require('konphyg')(__dirname + '/../config');

getMessageOfTheDay = function(callback) {
    logger.debug("Getting the message of the day");

    var targetURL = config("properties").apiServerUrl + '/messages';
    request({
        method: 'GET',
        url: targetURL
    }, function (error, response, body) {
        if (error) {
            return callback( error, null);
        }
        else if (!response) {
            return callback(new Error("No response from 'messages' API"));
        }
        else if (response.statusCode != 200) {
            return callback(new Error("'messages' API returned status " + response.statusCode), null);
        }
        else if (body){
            var messageObject = JSON.parse(body);
            if (messageObject.message){
                return callback(null, messageObject);
            }
        }
        else {
            return callback(new Error("Undetermined error calling 'messages' API"), null);
        }
    })
};

getMessageByDate = function(date, callback) {
    logger.debug("Getting the message of the day for " + date);

    var targetURL = config("properties").apiServerUrl + '/messages/' + date;
    request({
        method: 'GET',
        url: targetURL
    }, function (error, response, body) {
        if (error) {
            return callback(error, null);
        }
        else if (!response) {
            return callback(new Error("No response from 'messages' API"));
        }
        else if (response.statusCode != 200) {
            return callback(new Error("'messages' API returned status " + response.statusCode),
                            null);
        }
        else if (body) {
            var messageObject = JSON.parse(body);
            if (messageObject.message) {
                return callback(null, messageObject);
            }
        }
        else {
            return callback(new Error("Undetermined error calling 'messages' API"), null);
        }
    })
}

module.exports = {
    getMessageOfTheDay: getMessageOfTheDay,
    getMessageByDate: getMessageByDate
}