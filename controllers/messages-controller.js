// controller for "messages" related routes
var messagesService = require('../services/messages-service');
var logger = require('../logger');
var async = require('async');

module.exports = {

    displayMessageOfTheDay: function(req, res, next) {

        async.series({
            motd: function(callback) {
                messagesService.getMessageOfTheDay(function (error, messageOfTheDay) {
                    return callback(error, messageOfTheDay);
                });
            }
        }, function(error, results) {
            if (error){
                // let error go to default error handler
                return next(error);
            }

            logger.debug("Displaying the message of the day");
            res.render('motd',
                {
                    title: 'Message of the Day',
                    message: results.motd.message
                }
            );
        })
    },

    getMessageByDate: function(req, res, next) {

        async.series({
            motd: function(callback) {
                messagesService.getMessageByDate(req.query.timestamp, function (error, messageOfTheDay) {
                    return callback(error, messageOfTheDay);
                });
            }
        }, function(error, results) {
            if (error){
                // let error go to default error handler
                logger.error("Unable to get message by date", error);
                res.status(404).send();
                return;
            }

            logger.debug("Returning the message for timestamp " + req.query.timestamp);
            res.send(results.motd.message);
        })
    },

    displayMessageOfTheWeek: function(req, res, next) {
      res.render('motd-week',
          {
            title: 'Message of the Week',
            messages: ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]
          }
      );
    }

} // end module.exports
