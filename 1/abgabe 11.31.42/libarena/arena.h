#ifndef _ARENA_H
#define _ARENA_H

#define BLOCKSIZE 40
#define NUM_BLOCKS 1024
extern uint8_t  arena[BLOCKSIZE*NUM_BLOCKS];
extern uint16_t allocated_map[NUM_BLOCKS/16];

#endif
