#include "arena.h"
#include "allocate.h"
#include <stdlib.h>
#include <stdio.h>

void * allocate()
{

	int adress = 0;
	int i = 0;
	for(i = 0;i<NUM_BLOCKS;i++)
	{
		if((allocated_map[i]&0xFFFF)!=0xFFFF)
		{
			
			int inner_adress = 0;
			int k;
			for(k=15;k>=0;k--)
			{
				uint16_t mask = 1<<k;
				if((allocated_map[i]&mask)!=mask)
				{
					inner_adress = k;
					allocated_map[i]|=mask;
					break;

				}
			}
			adress = i*16*40 + inner_adress*40;
			break;
		}

	}
	if(adress<=BLOCKSIZE*NUM_BLOCKS)
	{
		return &arena[adress];
	}	
	else
	{
		return 0;
	}
}

void deallocate(void * data)
{
	int index = ((uint8_t *)data - arena)/BLOCKSIZE;
	int outer_adress = index /16;
	int inner_adress = index %16;
	allocated_map[outer_adress] &= ~(1<<inner_adress);
}
