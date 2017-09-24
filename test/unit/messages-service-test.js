var messages = require('../../services/messages-service.js');
var config = require('konphyg')(__dirname + "/../../config");
var nock = require('nock');
var expect = require('chai').expect;
var test = require('tap').test;

test('getMessageOfTheDay', function(t) {

  //use endpoint from config even for tests
  var apiServer = config("properties").apiServerUrl;

  var expectedMOTD = {
      date: 123456789075445423,
      message: "I am the message"
  };

  // test a 200 success
  var server = nock(apiServer, {})
        .get('/messages')
        .reply(200, expectedMOTD);

  server;
  messages.getMessageOfTheDay(function(error, results) {
    server.done();
    expect(error).to.be.a('null');
    expect(results).to.eql(expectedMOTD);
  });
  t.end();
});




