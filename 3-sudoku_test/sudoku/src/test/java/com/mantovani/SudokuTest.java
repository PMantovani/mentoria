package com.mantovani;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SudokuTest {

    @Test
    public void testSimpleGame() {
        String sampleGame = "37xx62xxxx291xx7xx5x1xxx9288xx4961x7x4xx1xx961x6753xx4984xxxxxxxxxxxxx3x61xx2x54x";
        assertEquals(0, Main.main(new String[]{ sampleGame }));
    }

    @Test
    public void testComplexGame() {
        String sampleGame = "9x4xxx2x6x5xx49xxxxxxxxxxxx3xxxx2x1xx61xxxxx9xxxx85xxxxxxxxxx5x7xx218xxx19x7xxxxx";
        assertEquals(0, Main.main(new String[]{ sampleGame }));
    }

    @Test
    public void testUnsolvableGame() {
        String sampleGame = "994xxx2x6x5xx49xxxxxxxxxxxx3xxxx2x1xx61xxxxx9xxxx85xxxxxxxxxx5x7xx218xxx19x7xxxxx";
        assertEquals(-1, Main.main(new String[]{ sampleGame }));
    }

    @Test
    public void testUnparsableGame() {
        String sampleGame = "Ax4xxx2x6x5xx49xxxxxxxxxxxx3xxxx2x1xx61xxxxx9xxxx85xxxxxxxxxx5x7xx218xxx19x7xxxxx";
        assertEquals(-1, Main.main(new String[]{ sampleGame }));
    }
}
