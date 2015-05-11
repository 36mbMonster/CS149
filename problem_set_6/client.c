#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>

#include "socket_name.h"

int main(int argc, char *argv[])
{
    int client_sockfd;
    int len;
    struct sockaddr_un address;
    int result;
    char ch = 'A';
    char *message;

    if(argc < 2)
    {
        printf("Usage: client <message string>\n");
        return 1;
    }

    message = argv[1];

    // Create a socket for the client.
    client_sockfd = socket(AF_UNIX, SOCK_STREAM, 0);

    // Name the socket.
    address.sun_family = AF_UNIX;
    strcpy(address.sun_path, SOCKET_NAMES[0]);
    len = sizeof(address);

    // Connect our socket to the server's socket.
    result = connect(client_sockfd, (struct sockaddr *)&address, len);
    if (result == -1) {
        perror("Error: client1");
        exit(1);
    }

    //  Write 'A' to the socket via sockfd.
    printf("Writing '%s' to the socket.\n", message);
    int byte_count = strlen(message);
    write(client_sockfd, message, byte_count);

    // Read what the server sent back.
    char *message2;
    read(client_sockfd, message2, byte_count);
    printf("Read '%s' from the server.\n", message2);

    close(client_sockfd);
    exit(0);
}
