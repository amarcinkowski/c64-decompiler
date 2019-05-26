BasicUpstart2(start)    // 10 sys$0810
start:
        lda #0
        lda #BLACK
        sta $d021
        sta $d020
        jmp start
