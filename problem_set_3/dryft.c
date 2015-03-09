#include <stdio.h>
#include <stdlib.h>

int num_riders;
int num_drivers;
int time_window;

typedef struct
{
    int id;
}rider;

typedef struct
{
    int id;
}driver;

rider *riders;
driver *drivers;

int main(int argc, char **argv)
{
    if(argc != 4)
    {
        printf("Useage: dryft <number of riders> <number of drivers> <time window>\n");
        return 0;
    }

    num_riders = atoi(argv[0]);
    num_drivers = atoi(argv[1]);
    time_window = atoi(argv[2]);

    //dynamically allocate arrays
    riders = malloc(num_riders * sizeof(rider));

    int i;
    for(i = 0; i < num_riders; i++)
        riders[i].id = i;

    printf("%d\n",num_riders);
}
