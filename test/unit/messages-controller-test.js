var test = require('tap').test;
var expect = require('chai').expect;
var proxyquire = require('proxyquire');

test('displayMessageOfTheDay', function(t) {

  var expectedMOTD = {
    date: new Date().getTime(),
    message: "I am the message"
  };

  // mock out our collaborators (i.e. the required libraries) so that we can verify behavior of our controller
  var controller = proxyquire('../../controllers/messages-controller.js',
  {
    "../services/messages-service": {
      getMessageOfTheDay: function (callback) {
        callback(null, expectedMOTD);
      }
    },
    "../logger": {
      debug: function (message) {
        // do nothing
      }
    }
  });

  var req = {};
  var res = {
      render : function(page, content) {
        expect(page).to.eql('motd');
        expect(content.title).to.eql('Message of the Day');
        expect(content.message).to.eql(expectedMOTD.message);
      }
  };

  controller.displayMessageOfTheDay(req, res, null);

  t.end();
});

test('displayMessageOfTheWeek', function(t) {

  var expectedMOTD = {
    messages: ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]
  };

  // mock out our collaborators (i.e. the required libraries) so that we can verify behavior of our controller
  var controller = proxyquire('../../controllers/messages-controller.js',{});

  var req = {};
  var res = {
      render : function(page, content) {
        expect(page).to.eql('motd-week');
        expect(content.title).to.eql('Message of the Week');
        console.log(content.message);
        expect(content.messages).to.eql(expectedMOTD.messages);
      }
  };

  controller.displayMessageOfTheWeek(req, res, null);

  t.end();
});
