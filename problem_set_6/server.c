#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <sys/select.h>

#include "socket_name.h"

int main()
{
    int server_sockfd, client_sockfd;
    int server_len, client_len;
    struct sockaddr_un server_address;
    struct sockaddr_un client_address;
    char message[255];

    // Remove any old socket and create an unnamed socket for the server.
    int i;
    for(i = 0; i < 3; i++)
    {
        unlink(SOCKET_NAMES[i]);
        server_sockfd = socket(AF_UNIX, SOCK_STREAM, 0);
    }


    // Name the socket.
    for(i = 0; i < 3; i++)
    {
        server_address.sun_family = AF_UNIX;
        strcpy(server_address.sun_path, SOCKET_NAMES[i]);
        server_len = sizeof(server_address);
        bind(server_sockfd, (struct sockaddr *)&server_address, server_len);
    }


    // Create a connection queue and wait for clients.
    listen(server_sockfd, 5);
    for (;;) {
        printf("Server waiting.\n");

        //  Accept a socket connection.
        client_len = sizeof(client_address);
        client_sockfd = accept(server_sockfd,
                              (struct sockaddr *) &client_address,
                              &client_len);

        // Read a character from the socket.
        //char ch;
        read(client_sockfd, message, 255);
        printf("Read string '%s'\n", message);

        // Increment the character and write it back to the socket.
        //ch++;
        printf("Writing string '%s'\n", message);
        write(client_sockfd, message, strlen(message));

        // Close the socket.
        close(client_sockfd);
    }
}

