#include <fcntl.h>
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
    int client_filefd;
    int len;
    struct sockaddr_un address;
    int result;
    char ch = 'A';
    char *message;
    char output[255];

    if(argc < 3)
    {
        printf("Usage: client <message string> <client number>\n");
        return 1;
    }

    message = argv[1];

    // Create a socket for the client.
    client_sockfd = socket(AF_UNIX, SOCK_STREAM, 0);

    //Open file for writing.
    char filename[11];
    sprintf(filename, "client%d.txt",argv[2]);
    client_filefd = open(filename, O_WRONLY | O_CREAT, 755);

    //Make sure the file was able to open
	if(client_filefd == -1)
	{
        perror("unable to open");
        return 2;
    }

    // Name the socket.
    address.sun_family = AF_UNIX;
    strcpy(address.sun_path, SOCKET_NAMES[argv[2]]);
    len = sizeof(address);

    // Connect our socket to the server's socket.
    result = connect(client_sockfd, (struct sockaddr *)&address, len);
    if (result == -1) {
        perror("Error: client1");
        exit(1);
    }

    //  Write a string to the socket via sockfd.
    printf("Writing '%s' to the socket.\n", message);

    const int SIZE = sprintf(output,"Client 1 user message: \"%s\" to the socket %s\n",message,SOCKET_NAMES[argv[2]]);
    write(client_sockfd, output, SIZE);

    //  Write the same string to a file.
    write(client_filefd, output, SIZE);

    // Read what the server sent back.
    char message2[SIZE];
    read(client_sockfd, message2, SIZE);
    printf("Read '%s' from the server.\n", message2);

    close(client_sockfd);
    close(client_filefd);
    exit(0);
}
