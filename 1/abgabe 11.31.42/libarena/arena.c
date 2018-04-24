#include <stdint.h>
#include "arena.h"

uint8_t  arena[BLOCKSIZE*NUM_BLOCKS] = {};
uint16_t allocated_map[NUM_BLOCKS/16] = {};
