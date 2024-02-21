
var ws = new WebSocket('wss://' + location.host + '/player');
var video;
var state = null;
var isSeekable = false;

var I_CAN_START = 0;
var I_CAN_STOP = 1;
var I_AM_STARTING = 2;

window.onload = function() {
	console = new Console();
	video = document.getElementById('video');
	setState(I_CAN_START);
}

window.onbeforeunload = function() {
	ws.close();
}

ws.onmessage = function(message) {
	var parsedMessage = JSON.parse(message.data);
	console.info('Received message: ' + message.data);

	switch (parsedMessage.id) {
	case 'plateDetected':
		plateDetected(parsedMessage);
		break;	
	case 'error':
		if (state == I_AM_STARTING) {
			setState(I_CAN_START);
		}
		onError('Error message from server: ' + parsedMessage.message);
		break;
	default:
		if (state == I_AM_STARTING) {
			setState(I_CAN_START);
		}
		onError('Unrecognized message', parsedMessage);
	}
}

function start() {
	// Disable start button
	setState(I_CAN_STOP);
	showSpinner(video);
	console.log("Dajeeeee");

	// Ottenere l'URL del video dall'input
	const videoUrl = document.getElementById('videourl').value;

	// Impostare la sorgente del video con l'URL specificato
	video.src = videoUrl;

	// Avviare la riproduzione del video
	video.play();

	var message = {
		id : 'start',
		videourl : videoUrl
	}

	sendMessage(message);
}


function onError(error) {
	console.error(error);
}


function plateDetected(message) {
	console.log("License plate detected " + message.plate);
}

function pause() {
	togglePause()
	console.log('Pausing video ...');
	var message = {
		id : 'pause'
	}
	sendMessage(message);
	video.pause();
	
}

function resume() {
	togglePause()
	console.log('Resuming video ...');
	var message = {
		id : 'resume'
	}
	sendMessage(message);
	video.play();
}

function stop() {
	console.log('Stopping video ...');
	setState(I_CAN_START);
	var message = {
		id : 'stop'
	}
	sendMessage(message);
	video.load();
	hideSpinner(video);
}


function setState(nextState) {
	switch (nextState) {
	case I_CAN_START:
		enableButton('#start', 'start()');
		disableButton('#pause');
		disableButton('#stop');
		enableButton('#videourl');
		break;

	case I_CAN_STOP:
		disableButton('#start');
		enableButton('#pause', 'pause()');
		enableButton('#stop', 'stop()');
		disableButton('#videourl');
		break;

	case I_AM_STARTING:
		disableButton('#start');
		disableButton('#pause');
		disableButton('#stop');
		disableButton('#videourl');
		break;

	default:
		onError('Unknown state ' + nextState);
		return;
	}
	state = nextState;
}

function sendMessage(message) {
	var jsonMessage = JSON.stringify(message);
	console.log('Sending message: ' + jsonMessage);
	ws.send(jsonMessage);
}

function togglePause() {
	var pauseText = $("#pause-text").text();
	if (pauseText == " Resume ") {
		$("#pause-text").text(" Pause ");
		$("#pause-icon").attr('class', 'glyphicon glyphicon-pause');
		$("#pause").attr('onclick', "pause()");
	} else {
		$("#pause-text").text(" Resume ");
		$("#pause-icon").attr('class', 'glyphicon glyphicon-play');
		$("#pause").attr('onclick', "resume()");
	}
}

function disableButton(id) {
	$(id).attr('disabled', true);
	$(id).removeAttr('onclick');
}

function enableButton(id, functionName) {
	$(id).attr('disabled', false);
	if (functionName) {
		$(id).attr('onclick', functionName);
	}
}

function showSpinner() {
	for (var i = 0; i < arguments.length; i++) {
		arguments[i].poster = './img/transparent-1px.png';
		arguments[i].style.background = "center transparent url('./img/spinner.gif') no-repeat";
	}
}

function hideSpinner() {
	for (var i = 0; i < arguments.length; i++) {
		arguments[i].src = '';
		arguments[i].poster = './img/webrtc.png';
		arguments[i].style.background = '';
	}
}

/**
 * Lightbox utility (to display media pipeline image in a modal dialog)
 
$(document).delegate('*[data-toggle="lightbox"]', 'click', function(event) {
	event.preventDefault();
	$(this).ekkoLightbox();
});
*/