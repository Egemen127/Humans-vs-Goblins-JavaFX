package com.example.demo4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.demo4.HelloController.TILE_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    Human h = new Human(5,5,3,3);
@Test
void move_up(){

    assertEquals(4*TILE_SIZE,h.move_right());
    assertEquals(4*TILE_SIZE,h.move_down());
    assertEquals(2*TILE_SIZE,h.move_up());
    assertEquals(2*TILE_SIZE,h.move_left());
}
}