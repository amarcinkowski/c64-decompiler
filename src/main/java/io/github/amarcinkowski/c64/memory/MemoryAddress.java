package io.github.amarcinkowski.c64.memory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public enum MemoryAddress {

    $0000(0, 1, "$2F", "Processor port data direction register."), //
    $0001(1, 1, "$37", "Processor port."), //
    $0002(2, 1, null, "Unused."), //
    $0003_$0004(3, 2, "$B1AA", "Unused."), //
    $0005_$0006(5, 2, "$B391", "Unused."), //
    $0007(7, 1, null, "Byte being search for during various operations."), //
    $0008(8, 1, null, "Byte being search for during various operations."), //
    $0009(9, 1, null, "Current column number during SPC() and TAB()."), //
    $000A(10, 1, null, "LOAD/VERIFY switch."), //
    $000B(11, 1, null, "Current token during tokenization."), //
    $000C(12, 1, null, "Switch for array operations."), //
    $000D(13, 1, null, "Current expression type."), //
    $000E(14, 1, null, "Current numerical expression type."), //
    $000F(15, 1, null, "Quotation mode switch during tokenization."), //
    $0010(16, 1, null, "Switch during fetch of variable name."), //
    $0011(17, 1, null, "GET/INPUT/READ switch."), //
    $0012(18, 1, null, "Sign during SIN() and TAN()."), //
    $0013(19, 1, "$00", "Current I/O device number."), //
    $0014_$0015(20, 2, null, "Line number during GOSUB, GOTO and RUN."), //
    $0016(22, 1, "$19", "Pointer to next expression in string stack."), //
    $0017_$0018(23, 2, null, "Pointer to previous expression in string stack."), //
    $0019_$0021(25, 9, null, "String stack, temporary area for processing string expressions (9 bytes, 3 entries)."), //
    $0022_$0025(34, 4, null, "Temporary area for various operations (4 bytes)."), //
    $0026_$0029(38, 4, null, "Auxiliary arithmetical register for division and multiplication (4 bytes)."), //
    $002A(42, 1, null, "Unused."), //
    $002B_$002C(43, 2, "$0801", "Pointer to beginning of BASIC area."), //
    $002D_$002E(45, 2, null, "Pointer to beginning of variable area."), //
    $002F_$0030(47, 2, null, "Pointer to beginning of array variable area."), //
    $0031_$0032(49, 2, null, "Pointer to end of array variable area."), //
    $0033_$0034(51, 2, null, "Pointer to beginning of string variable area."), //
    $0035_$0036(53, 2, null, "Pointer to memory allocated for current string variable."), //
    $0037_$0038(55, 2, "$A000", "Pointer to end of BASIC area."), //
    $0039_$003A(57, 2, null, "Current BASIC line number."), //
    $003B_$003C(59, 2, null, "Current BASIC line number for CONT."), //
    $003D_$003E(61, 2, null, "Pointer to next BASIC instruction for CONT."), //
    $003F_$0040(63, 2, null, "BASIC line number of current DATA item for READ."), //
    $0041_$0042(65, 2, null, "Pointer to next DATA item for READ."), //
    $0043_$0044(67, 2, null, "Pointer to input result during GET, INPUT and READ."), //
    $0045_$0046(69, 2, null, "Name and type of current variable."), //
    $0047_$0048(71, 2, null, "Pointer to value of current variable or FN function."), //
    $0049_$004A(73, 2, null, "Pointer to value of current variable during LET."), //
    $004B_$004C(75, 2, null,
            "Temporary area for saving original pointer to current BASIC instruction during GET, INPUT and READ."), //
    $004D(77, 1, null, "Comparison operator indicator."), //
    $004E_$004F(78, 2, null, "Pointer to current FN function."), //
    $0050_$0051(80, 2, null, "Pointer to current string variable during memory allocation."), //
    $0052(82, 1, null, "Unused."), //
    $0053(83, 1, null, "Step size of garbage collection."), //
    $0054_$0056(84, 3, null, "JMP ABS machine instruction, jump to current BASIC function."), //
    $0057_$005B(87, 5, null, "Arithmetic register #3 (5 bytes)."), //
    $005C_$0060(92, 5, null, "Arithmetic register #4 (5 bytes)."), //
    $0061_$0065(97, 5, null, "FAC, arithmetic register #1 (5 bytes)."), //
    $0066(102, 1, null, "Sign of FAC."), //
    $0067(103, 1, null, "Number of degrees during polynomial evaluation."), //
    $0068(104, 1, null, "Temporary area for various operations."), //
    $0069_$006D(105, 5, null, "ARG, arithmetic register #2 (5 bytes)."), //
    $006E(110, 1, null, "Sign of ARG."), //
    $006F_$0070(111, 2, null, "Pointer to first string expression during string comparison."), //
    $0071_$0072(113, 2, null, "Auxiliary pointer during array operations."), //
    $0073_$008A(115, 24, null, "CHRGET."), //
    $008B_$008F(139, 5, null, "Previous result of RND()."), //
    $0090(144, 1, null, "Value of ST variable, device status for serial bus and datasette input/output."), //
    $0091(145, 1, null, "Stop key indicator."), //
    $0092(146, 1, null, "Unknown."), //
    $0093(147, 1, null, "LOAD/VERIFY switch."), //
    $0094(148, 1, null, "Serial bus output cache status."), //
    $0095(149, 1, null, "Serial bus output cache, previous byte to be sent to serial bus."), //
    $0096(150, 1, null, "Unknown."), //
    $0097(151, 1, null, "Temporary area for saving original value of Y register during input from RS232."), //
    $0098(152, 1, null, "Number of files currently open."), //
    $0099(153, 1, "$00", "Current input device number."), //
    $009A(154, 1, "$03", "Current output device number."), //
    $009B(155, 1, null, "Unknown."), //
    $009C(156, 1, null, "Unknown."), //
    $009D(157, 1, null, "System error display switch."), //
    $009E(158, 1, null, "Byte to be put into output buffer during RS232 and datasette output."), //
    $009F(159, 1, null, "Auxiliary counter for writing file name into datasette buffer."), //
    $00A0_$00A2(160, 3, null, "Value of TI variable, time of day, increased by 1 every 1/60 second (on PAL machines)."), //
    $00A3(163, 1, null, "EOI switch during serial bus output."), //
    $00A4(164, 1, null, "Byte buffer during serial bus input."), //
    $00A5(165, 1, null, "Bit counter during serial bus input/output."), //
    $00A6(166, 1, null, "Offset of current byte in datasette buffer."), //
    $00A7(167, 1, null, "Bit buffer during RS232 input."), //
    $00A8(168, 1, null, "Bit counter during RS232 input."), //
    $00A9(169, 1, null, "Stop bit switch during RS232 input."), //
    $00AA(170, 1, null, "Byte buffer during RS232 input."), //
    $00AB(171, 1, null, "Parity during RS232 input."), //
    $00AC_$00AD(172, 2, null, "Start address for SAVE to serial bus."), //
    $00AE_$00AF(174, 2, null,
            "Load address read from input file and pointer to current byte during LOAD/VERIFY from serial bus."), //
    $00B0_$00B1(176, 2, null, "Unknown."), //
    $00B2_$00B3(178, 2, "$033C", "Pointer to datasette buffer."), //
    $00B4(180, 1, null, "Bit counter and stop bit switch during RS232 output."), //
    $00B5(181, 1, null, "Bit buffer (in bit #2) during RS232 output."), //
    $00B6(182, 1, null, "Byte buffer during RS232 output."), //
    $00B7(183, 1, null,
            "Length of file name or disk command; first parameter of LOAD, SAVE and VERIFY or fourth parameter of OPEN."), //
    $00B8(184, 1, null, "Logical number of current file."), //
    $00B9(185, 1, null, "Secondary address of current file."), //
    $00BA(186, 1, null, "Device number of current file."), //
    $00BB_$00BC(187, 2, null,
            "Pointer to current file name or disk command; first parameter of LOAD, SAVE and VERIFY or fourth parameter of OPEN."), //
    $00BD(189, 1, null, "Parity during RS232 output."), //
    $00BE(190, 1, null, "Block counter during datasette input/output."), //
    $00BF(191, 1, null, "Unknown."), //
    $00C0(192, 1, null, "Datasette motor switch."), //
    $00C1_$00C2(193, 2, null,
            "Start address during SAVE to serial bus, LOAD and VERIFY from datasette and SAVE to datasette."), //
    $00C3_$00C4(195, 2, null,
            "Start address for a secondary address of 0 for LOAD and VERIFY from serial bus or datasette."), //
    $00C5(197, 1, null, "Matrix code of key previously pressed."), //
    $00C6(198, 1, null, "Length of keyboard buffer."), //
    $00C7(199, 1, null, "Reverse mode switch."), //
    $00C8(200, 1, null, "Length of line minus 1 during screen input."), //
    $00C9(201, 1, null, "Cursor row during screen input."), //
    $00CA(202, 1, null, "Cursor column during screen input."), //
    $00CB(203, 1, null, "Matrix code of key currently being pressed."), //
    $00CC(204, 1, null, "Cursor visibility switch."), //
    $00CD(205, 1, null, "Delay counter for changing cursor phase."), //
    $00CE(206, 1, null, "Screen code of character under cursor."), //
    $00CF(207, 1, null, "Cursor phase switch."), //
    $00D0(208, 1, null, "End of line switch during screen input."), //
    $00D1_$00D2(209, 2, null, "Pointer to current line in screen memory."), //
    $00D3(211, 1, null, "Current cursor column."), //
    $00D4(212, 1, null, "Quotation mode switch."), //
    $00D5(213, 1, null, "Length of current screen line minus 1."), //
    $00D6(214, 1, null, "Current cursor row."), //
    $00D7(215, 1, null, "PETSCII code of character during screen input/output."), //
    $00D8(216, 1, null, "Number of insertions."), //
    $00D9_$00F1(217, 25, null, "High byte of pointers to each line in screen memory (25 bytes)."), //
    $00F2(242, 1, null, "Temporary area during scrolling the screen."), //
    $00F3_$00F4(243, 2, null, "Pointer to current line in Color RAM."), //
    $00F5_$00F6(245, 2, null,
            "Pointer to current conversion table during conversion from keyboard matrix codes to PETSCII codes."), //
    $00F7_$00F8(247, 2, null, "Pointer to RS232 input buffer."), //
    $00F9_$00FA(249, 2, null, "Pointer to RS232 output buffer."), //
    $00FB_$00FE(251, 4, null, "Unused (4 bytes)."), //
    $00FF_$010A(255, 12, null, "Buffer for conversion from floating point to string (12 bytes."), //
    /* $0100-$01FF, 256-511 Processor stack */
    $0100_$013D(256, 62, null, "Pointers to bytes read with error during datasette input (62 bytes, 31 entries)."), //
    $0100_$01FF(256, 256, null, "Processor stack."), //
    /* $0200-$02FF, 512-767 */
    $0200_$0258(512, 89, null, "Input buffer, storage area for data read from screen (89 bytes)."), //
    $0259_$0262(601, 10, null, "Logical numbers assigned to files (10 bytes, 10 entries)."), //
    $0263_$026C(611, 10, null, "Device numbers assigned to files (10 bytes, 10 entries)."), //
    $026D_$0276(621, 10, null, "Secondary addresses assigned to files (10 bytes, 10 entries)."), //
    $0277_$0280(631, 10, null, "Keyboard buffer (10 bytes, 10 entries)."), //
    $0281_$0282(641, 2, "$0800", "Pointer to beginning of BASIC area after memory test."), //
    $0283_$0284(643, 2, "$A000", "Pointer to end of BASIC area after memory test."), //
    $0285(645, 1, null, "Unused."), //
    $0286(646, 1, null, "Current color, cursor color."), //
    $0287(647, 1, null, "Color of character under cursor."), //
    $0288(648, 1, "$04", "High byte of pointer to screen memory for screen input/output."), //
    $0289(649, 1, null, "Maximum length of keyboard buffer."), //
    $028A(650, 1, null, "Keyboard repeat switch."), //
    $028B(651, 1, null, "Delay counter during repeat sequence, for delaying between successive repeats."), //
    $028C(652, 1, null, "Repeat sequence delay counter, for delaying before first repetition."), //
    $028D(653, 1, null, "Shift key indicator."), //
    $028E(654, 1, null, "Previous value of shift key indicator."), //
    $028F_$0290(655, 2, "$EB48",
            "Execution address of routine that, based on the status of shift keys, sets the pointer at memory address $00F5-$00F6 to the appropriate conversion table for converting keyboard matrix codes to PETSCII codes."), //
    $0291(657, 1, null, "Commodore-Shift switch."), //
    $0292(658, 1, null, "Scroll direction switch during scrolling the screen."), //
    $0293(659, 1, null, "RS232 control register."), //
    $0294(660, 1, null, "RS232 command register."), //
    $0295_$0296(661, 2, null, "Default value of RS232 output timer, based on baud rate."), //
    $0297(663, 1, null, "Value of ST variable, device status for RS232 input/output."), //
    $0298(664, 1, null, "RS232 byte size, number of data bits per data byte, default value for bit counters."), //
    $0299_$029A(665, 2, null, "Default value of RS232 input timer, based on baud rate."), //
    $029B(667, 1, null, "Offset of byte received in RS232 input buffer."), //
    $029C(668, 1, null, "Offset of current byte in RS232 input buffer."), //
    $029D(669, 1, null, "Offset of byte to send in RS232 output buffer."), //
    $029E(670, 1, null, "Offset of current byte in RS232 output buffer."), //
    $029F_$02A0(671, 2, null,
            "Temporary area for saving pointer to original interrupt service routine during datasette input output."), //
    $02A1(673, 1, null,
            "Temporary area for saving original value of CIA#2 interrupt control register, at memory address $DD0D, during RS232 input/output."), //
    $02A2(674, 1, null,
            "Temporary area for saving original value of CIA#1 timer #1 control register, at memory address $DC0E, during datasette input/output."), //
    $02A3_$02A4(675, 2, null, "Unknown."), //
    $02A5(677, 1, null, "Number of line currently being scrolled during scrolling the screen."), //
    $02A6(678, 1, null, "PAL/NTSC switch, for selecting RS232 baud rate from the proper table."), //
    $02A7_$02FF(679, 89, null, "Unused (89 bytes)."), //
    /* $0300-$03FF, 768-1023 */
    $0300_$0301(768, 2, "$E38B",
            "Execution address of warm reset, displaying optional BASIC error message and entering BASIC idle loop."), //
    $0302_$0303(770, 2, "$A483", "Execution address of BASIC idle loop."), //
    $0304_$0305(772, 2, "$A57C", "Execution address of BASIC line tokenizater routine."), //
    $0306_$0307(774, 2, "$A71A", "Execution address of BASIC token decoder routine."), //
    $0308_$0309(776, 2, "$A7E4", "Execution address of BASIC instruction executor routine."), //
    $030A_$030B(778, 2, "$AE86", "Execution address of routine reading next item of BASIC expression."), //
    $030C(780, 1, null, "Default value of register A for SYS."), //
    $030D(781, 1, null, "Default value of register X for SYS."), //
    $030E(782, 1, null, "Default value of register Y for SYS."), //
    $030F(783, 1, null, "Default value of status register for SYS."), //
    $0310_$0312(784, 3, null, "JMP ABS machine instruction, jump to USR() function."), //
    $0313(787, 1, null, "Unused."), //
    $0314_$0315(788, 2, "$EA31", "Execution address of interrupt service routine."), //
    $0316_$0317(790, 2, "$FE66", "Execution address of BRK service routine."), //
    $0318_$0319(792, 2, "$FE47", "Execution address of non-maskable interrupt service routine."), //
    $031A_$031B(794, 2, "$F34A", "Execution address of OPEN, routine opening files."), //
    $031C_$031D(796, 2, "$F291", "Execution address of CLOSE, routine closing files."), //
    $031E_$031F(798, 2, "$F20E", "Execution address of CHKIN, routine defining file as default input."), //
    $0320_$0321(800, 2, "$F250", "Execution address of CHKOUT, routine defining file as default output."), //
    $0322_$0323(802, 2, "$F333", "Execution address of CLRCHN, routine initializating input/output."), //
    $0324_$0325(804, 2, "$F157",
            "Execution address of CHRIN, data input routine, except for keyboard and RS232 input."), //
    $0326_$0327(806, 2, "$F1CA", "Execution address of CHROUT, general purpose data output routine."), //
    $0328_$0329(808, 2, "$F6ED",
            "Execution address of STOP, routine checking the status of Stop key indicator, at memory address $0091."), //
    $032A_$032B(810, 2, "$F13E", "Execution address of GETIN, general purpose data input routine."), //
    $032C_$032D(812, 2, "$F32F",
            "Execution address of CLALL, routine initializing input/output and clearing all file assignment tables."), //
    $032E_$032F(814, 2, "$FE66", "Unused."), //
    $0330_$0331(816, 2, "$F4A5", "Execution address of LOAD, routine loading files."), //
    $0332_$0333(818, 2, "$F5ED", "Execution address of SAVE, routine saving files."), //
    $0334_$033B(820, 8, null, "Unused (8 bytes)."), //
    $033C_$03FB(828, 192, null, "Datasette buffer (192 bytes)."), //
    $03FC_$03FF(1020, 4, null, "Unused (4 bytes)."), //
    /* $0400-$07FF, 1024-2047 Default screen memory */
    $0400_$07E7(1024, 1000, null, "Default area of screen memory (1000 bytes)."), //
    $07E8_$07F7(2024, 16, null, "Unused (16 bytes)."), //
    $07F8_$07FF(2040, 8, null, "Default area for sprite pointers (8 bytes)."), //
    /* $0800-$9FFF, 2048-40959 BASIC area */
    $0800(2048, 1, null, "Unused."), //
    $0801_$9FFF(2049, 38911, null, "Default BASIC area (38911 bytes)."), //
    $8000_$9FFF(32768, 8192, null, "Optional cartridge ROM (8192 bytes)."), //
    /* $A000-$BFFF, 40960-49151 BASIC ROM */
    $A000_$BFFF(40960, 8192, null, "BASIC ROM or RAM area (8192 bytes)."), //
    /* $C000-$CFFF, 49152-53247 Upper RAM area */
    $C000_$CFFF(49152, 4096, null, "Upper RAM area (4096 bytes)."), //
    /* $D000-$DFFF, 53248-57343 I/O Area */
    $D000_$DFFF(53248, 4096, null, "I/O Area (memory mapped chip registers), Character ROM or RAM area (4096 bytes)."), //
    /* $D000-$DFFF, 53248-57343 Character ROM */
    $D000_$DFFF_(53248, 4096, null, "Character ROM, shape of characters (4096 bytes)."), //
    $D000_$D7FF(53248, 2048, null,
            "Shape of characters in uppercase/graphics character set (2048 bytes, 256 entries)."), //
    $D800_$DFFF(55295, 2049, null,
            "Shape of characters in lowercase/uppercase character set (2048 bytes, 256 entries)."), //
    /* $D000-$D3FF, 53248-54271 VIC-II; video display */
    $D000(53248, 1, null, "Sprite #0 X-coordinate (only bits #0-#7)."), //
    $D001(53249, 1, null, "Sprite #0 Y-coordinate."), //
    $D002(53250, 1, null, "Sprite #1 X-coordinate (only bits #0-#7)."), //
    $D003(53251, 1, null, "Sprite #1 Y-coordinate."), //
    $D004(53252, 1, null, "Sprite #2 X-coordinate (only bits #0-#7)."), //
    $D005(53253, 1, null, "Sprite #2 Y-coordinate."), //
    $D006(53254, 1, null, "Sprite #3 X-coordinate (only bits #0-#7)."), //
    $D007(53255, 1, null, "Sprite #3 Y-coordinate."), //
    $D008(53256, 1, null, "Sprite #4 X-coordinate (only bits #0-#7)."), //
    $D009(53257, 1, null, "Sprite #4 Y-coordinate."), //
    $D00A(53258, 1, null, "Sprite #5 X-coordinate (only bits #0-#7)."), //
    $D00B(53259, 1, null, "Sprite #5 Y-coordinate."), //
    $D00C(53260, 1, null, "Sprite #6 X-coordinate (only bits #0-#7)."), //
    $D00D(53261, 1, null, "Sprite #6 Y-coordinate."), //
    $D00E(53262, 1, null, "Sprite #7 X-coordinate (only bits #0-#7)."), //
    $D00F(53263, 1, null, "Sprite #7 Y-coordinate."), //
    $D010(53264, 1, null, "Sprite #0-#7 X-coordinates (bit #8)."), //
    $D011(53265, 1, "$1B", "Screen control register #1."), //
    $D012(53266, 1, null, "Read: Current raster line (bits #0-#7)."), //
    $D013(53267, 1, null, "Light pen X-coordinate (bits #1-#8)."), //
    $D014(53268, 1, null, "Light pen Y-coordinate."), //
    $D015(53269, 1, null, "Sprite enable register."), //
    $D016(53270, 1, "$C8", "Screen control register #2."), //
    $D017(53271, 1, null, "Sprite double height register."), //
    $D018(53272, 1, null, "Memory setup register."), //
    $D019(53273, 1, null, "Interrupt status register."), //
    $D01A(53274, 1, null, "Interrupt control register."), //
    $D01B(53275, 1, null, "Sprite priority register."), //
    $D01C(53276, 1, null, "Sprite multicolor mode register."), //
    $D01D(53277, 1, null, "Sprite double width register."), //
    $D01E(53278, 1, null, "Sprite-sprite collision register."), //
    $D01F(53279, 1, null, "Sprite-background collision register."), //
    $D020(53280, 1, null, "Border color (only bits #0-#3)."), //
    $D021(53281, 1, null, "Background color (only bits #0-#3)."), //
    $D022(53282, 1, null, "Extra background color #1 (only bits #0-#3)."), //
    $D023(53283, 1, null, "Extra background color #2 (only bits #0-#3)."), //
    $D024(53284, 1, null, "Extra background color #3 (only bits #0-#3)."), //
    $D025(53285, 1, null, "Sprite extra color #1 (only bits #0-#3)."), //
    $D026(53286, 1, null, "Sprite extra color #1 (only bits #0-#3)."), //
    $D027(53287, 1, null, "Sprite #0 color (only bits #0-#3)."), //
    $D028(53288, 1, null, "Sprite #1 color (only bits #0-#3)."), //
    $D029(53289, 1, null, "Sprite #2 color (only bits #0-#3)."), //
    $D02A(53290, 1, null, "Sprite #3 color (only bits #0-#3)."), //
    $D02B(53291, 1, null, "Sprite #4 color (only bits #0-#3)."), //
    $D02C(53292, 1, null, "Sprite #5 color (only bits #0-#3)."), //
    $D02D(53293, 1, null, "Sprite #6 color (only bits #0-#3)."), //
    $D02E(53294, 1, null, "Sprite #7 color (only bits #0-#3)."), //
    $D02F_$D03F(53295, 17, null, "Unusable (17 bytes)."), //
    $D040_$D3FF(53312, 960, null, "VIC-II register images (repeated every $40, 64 bytes)."), //
    /* $D400-$D7FF, 54272-55295 SID; audio */
    $D400_$D401(54272, 2, null, "Voice #1 frequency."), //
    $D402_$D403(54274, 2, null, "Voice #1 pulse width."), //
    $D404(54276, 1, null, "Voice #1 control register."), //
    $D405(54277, 1, null, "Voice #1 Attack and Decay length."), //
    $D406(54278, 1, null, "Voice #1 Sustain volume and Release length."), //
    $D407_$D408(54279, 2, null, "Voice #2 frequency."), //
    $D409_$D40A(54281, 2, null, "Voice #2 pulse width."), //
    $D40B(54283, 1, null, "Voice #2 control register."), //
    $D40C(54284, 1, null, "Voice #2 Attack and Decay length."), //
    $D40D(54285, 1, null, "Voice #2 Sustain volume and Release length."), //
    $D40E_$D40F(54286, 2, null, "Voice #3 frequency."), //
    $D410_$D411(54288, 2, null, "Voice #3 pulse width."), //
    $D412(54290, 1, null, "Voice #3 control register."), //
    $D413(54291, 1, null, "Voice #3 Attack and Decay length."), //
    $D414(54292, 1, null, "Voice #3 Sustain volume and Release length."), //
    $D415(54293, 1, null, "Filter cut off frequency (bits #0-#2)."), //
    $D416(54294, 1, null, "Filter cut off frequency (bits #3-#10)."), //
    $D417(54295, 1, null, "Filter control."), //
    $D418(54296, 1, null, "Volume and filter modes."), //
    $D419(54297, 1, null, "X value of paddle selected at memory address $DD00."), //
    $D41A(54298, 1, null, "Y value of paddle selected at memory address $DD00."), //
    $D41B(54299, 1, null, "Voice #3 waveform output."), //
    $D41C(54300, 1, null, "Voice #3 ADSR output."), //
    $D41D_$D41F(54301, 3, null, "Unusable (3 bytes)."), //
    $D420_$D7FF(54304, 992, null, "SID register images (repeated every $20, 32 bytes)."), //
    /* $D800-$DBFF, 55296-56319 Color RAM */
    $D800_$DBE7(55296, 1000, null, "Color RAM (1000 bytes, only bits #0-#3)."), //
    $DBE8_$DBFF(56296, 24, null, "Unused (24 bytes, only bits #0-#3)."), //
    /*
     * $DC00-$DCFF, 56320-56575 CIA#1; inputs (keyboard, joystick, mouse),
     * datasette, IRQ control
     */
    $DC00(56320, 1, null, "Port A, keyboard matrix columns and joystick #2."), //
    $DC01(56321, 1, null, "Port B, keyboard matrix rows and joystick #1."), //
    $DC02(56322, 1, null, "Port A data direction register."), //
    $DC03(56323, 1, null, "Port B data direction register."), //
    $DC04_$DC05(56324, 2, null, "Timer A."), //
    $DC06_$DC07(56326, 2, null, "Timer B."), //
    $DC08(56328, 1, null, "Time of Day, tenth seconds (in BCD)."), //
    $DC09(56329, 1, null, "Time of Day, seconds (in BCD)."), //
    $DC0A(56330, 1, null, "Time of Day, minutes (in BCD)."), //
    $DC0B(56331, 1, null, "Time of Day, hours (in BCD)."), //
    $DC0C(56332, 1, null, "Serial shift register."), //
    $DC0D(56333, 1, null, "Interrupt control and status register."), //
    $DC0E(56334, 1, null, "Timer A control register."), //
    $DC0F(56335, 1, null, "Timer B control register."), //
    $DC10_$DCFF(56336, 240, null, "CIA#1 register images (repeated every $10, 16 bytes)."), //
    /* $DD00-$DDFF, 56576-56831 CIA#2; serial bus, RS232, NMI control */
    $DD00(56576, 1, null, "Port A, serial bus access."), //
    $DD01(56577, 1, null, "Port B, RS232 access."), //
    $DD02(56578, 1, null, "Port A data direction register."), //
    $DD03(56579, 1, null, "Port B data direction register."), //
    $DD04_$DD05(56580, 2, null, "Timer A."), //
    $DD06_$DD07(56582, 2, null, "Timer B."), //
    $DD08(56584, 1, null, "Time of Day, tenth seconds (in BCD)."), //
    $DD09(56585, 1, null, "Time of Day, seconds (in BCD)."), //
    $DD0A(56586, 1, null, "Time of Day, minutes (in BCD)."), //
    $DD0B(56587, 1, null, "Time of Day, hours (in BCD)."), //
    $DD0C(56588, 1, null, "Serial shift register."), //
    $DD0D(56589, 1, null, "Interrupt control and status register."), //
    $DD0E(56590, 1, null, "Timer A control register."), //
    $DD0F(56591, 1, null, "Timer B control register."), //
    $DD10_$DDFF(56592, 240, null, "CIA#2 register images (repeated every $10, 16 bytes)."), //
    /* $DE00-$DEFF, 56832-57087 I/O Area #1 */
    $DE00_$DEFF(56832, 256, null,
            "I/O Area #1, memory mapped registers or machine code routines " +
                    "of optional external devices (256 bytes)."), //
    /* $DF00-$DFFF, 57088-57343 I/O Area #2 */
    $DF00_$DFFF(57088, 256, null,
            "I/O Area #2, memory mapped registers or machine code routines " +
                    "of optional external devices (256 bytes)."), //
    /* $E000-$FFFF, 57344-65535 KERNAL ROM */
    $E000_$FFFF(57344, 8192, null, "KERNAL ROM or RAM area (8192 bytes)."), //
    /* $FFFA-$FFFF, 65530-65535 Hardware vectors */
    $FFFA_$FFFB(65530, 2, "$FE43", "Execution address of non-maskable interrupt service routine."), //
    $FFFC_$FFFD(65532, 2, "$FCE2", "Execution address of cold reset."), //
    $FFFE_$FFFF(65534, 2, "$FF48", "Execution address of interrupt service routine."),//
    ;

    public static MemoryAddress[] getMemoryAdres(Integer adr) {
        System.out.print("\nadr " + adr);
        Object[] ma = Arrays.stream(MemoryAddress.values()).filter(p -> p.start <= adr && (p.start + p.size - 1 >= adr)).sorted().toArray();//.findFirst().orElse(null);
        for(Object o : ma) {
            System.out.print(" " + o);
            MemoryMap mm = Arrays.stream(MemoryMap.values()).filter(p -> p.getMemory() == o).findFirst().orElse(null);
            System.out.print(" " + mm);
        }
        return null;
    }

    private MemoryAddress(int start, int size, String def, String desc) {
        this.start = start;
        this.size = size;
        this.description = desc;
        this.defaultValue = def;
    }

    String description;
    String defaultValue;
    int size;
    int start;
}
