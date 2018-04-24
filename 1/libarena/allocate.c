#include <stdint.h>
#include "arena.h"

void *allocate() {
	int i, n;
	uint16_t map;
	for (i = 0; i < NUM_BLOCKS/16; i++) {
		map = allocated_map[i];
		for (n = 0; n < 16; n++) {
			if ((map & 1) == 0) {
				allocated_map[i] |= (1 << n);
				return &arena[(i*16 + n) * BLOCKSIZE];
			}
			map = map >> 1;
		}
	}
	return 0;
}

void deallocate(void* data) {
	int i, n;
	for (i = 0; i < NUM_BLOCKS/16; i++) {
		for (n = 0; n < 16; n++) {
			if (&arena[(i*16 + n) * BLOCKSIZE] == data) {
				allocated_map[i] &= ~(1 << n);
				return;
			}
		}
	}
}