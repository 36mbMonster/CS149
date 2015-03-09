#include <stdio.h>

int riders;
int drivers;
int time_window;

int main(int argc, char **argv)
{
    if(argc < 3)
    {
        printf("Useage: dryft <number of riders> <number of drivers> <time window>\n");
        return 0;
    }

    char *N = argv[0];
    char *M = argv[1];
    char *T = argv[2];

    riders = atoi(N);
    drivers = atoi(M);
    time_window = atoi(T);
}
