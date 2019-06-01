BasicUpstart2(start)    // 10 sys$0810
start:
        lda #BLUE
        sta $d021
//        lda $21     //load second number into accumulator
//        cmp $20     //compare the numbers
//        bcs done    //done if first is less than or equal to second.
//        ldx $20
//        sta $20     //otherwise swap them.
//        stx $21
//        nop
//        nop
//done:   lda #RED
//        sta $d021
        lda #BLACK
        sta $d020
        jmp start
