'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;
var chatId = null;
var keywordsList=null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();
    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({userId:username}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        	chatId = xhttp.responseText;
        	stompClient.subscribe('/topic/private/'+chatId, onMessageReceived);
        	stompClient.send("/app/chat.join",{},JSON.stringify({sender: username, type: 'JOIN', chatId: chatId}))
            connectingElement.classList.add('hidden');
        }
    };
    xhttp.open("GET", "http://localhost:9090/getChatId", true);
    xhttp.send();
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT',
            chatId: chatId
        };
        stompClient.send("/app/chat.conversation", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    
    messageElement.appendChild(textElement);
    
	if(message.keywords != null){
		keywordsList = message.keywords;
		var buttonElement = document.createElement('p');
		for (var i = 0; i < message.keywords.length; i++) {
			var button = document.createElement("button");
	    	button.innerHTML = message.keywords[i];
	    	button.setAttribute('class','button button2') ;
	    	button.setAttribute('onclick','press(this.innerHTML)') ;
	    	buttonElement.appendChild(button);
	    	messageElement.appendChild(buttonElement);
		}
	}

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function press(innerhtml){
	 if(stompClient) {
	        var chatMessage = {
	            sender: username,
	            content: innerhtml,
	            type: 'CHAT',
	            chatId: chatId
	        };
	        stompClient.send("/app/chat.conversation", {}, JSON.stringify(chatMessage));
	    }
}

function hasClass(elem, className) {
	alert(ele);
	alert(className)
    return elem.className.split(' ').indexOf(className) > -1;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
