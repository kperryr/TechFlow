const socket = new SockJS('/ws'); //establishes Websocket connection to server
    const stompClient = Stomp.over(socket); //stompclient that handles STOMP over WS connection
    const pathname = window.location.pathname;
    let ticketId;

   // getting/creating elements from DOM
    const notificationDiv = document.getElementById('notification');
    const messageElement = document.createElement('div');
    const closeButton = document.getElementById('notification-close');

//connect to WS server
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        //names a STOMP dest. - topic/notifs
            stompClient.subscribe('/topic/notifications', function (notification) {

            //when notif is received check if current page is not the edit page for that ticket
                ticketId = notification.body.split("ID ")[1];
                if (!pathname.includes("tickets/edit/" + ticketId)) {
                    displayNotification(notification.body);
                }
            });
    });

    closeButton.addEventListener("click", close);

//displays notif
function displayNotification(notification) {
        messageElement.textContent = notification;
        notificationDiv.insertBefore(messageElement, notificationDiv.firstChild);
        notificationDiv.classList.remove('hidden');
        messageElement.classList.add('inline');
        notificationDiv.classList.add('inline');
    }

//hides notif after click the "X" button
    function close() {
        notificationDiv.classList.add('hidden');
        messageElement.remove();

    }