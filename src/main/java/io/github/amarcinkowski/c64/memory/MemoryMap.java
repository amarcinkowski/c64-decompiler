package io.github.amarcinkowski.c64.memory;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.amarcinkowski.c64.memory.MemoryAddress.*;

public enum MemoryMap {
	/**
	Processor port data direction register. Bits:
	Bit #x: 0 = Bit #x in processor port can only be read; 1 = Bit #x in processor port can be read and written.Default: $2F, %00101111.
	*/
	PROCESSOR_PORT_DATA_DIRECTION_REGISTER ($0000),
	/**
	Processor port. Bits:
	Bits #0-#2: Configuration for memory areas $A000-$BFFF, $D000-$DFFF and $E000-$FFFF. Values: %x00: RAM visible in all three areas. %x01: RAM visible at $A000-$BFFF and $E000-$FFFF. %x10: RAM visible at $A000-$BFFF; KERNAL ROM visible at $E000-$FFFF. %x11: BASIC ROM visible at $A000-$BFFF; KERNAL ROM visible at $E000-$FFFF. %0xx: Character ROM visible at $D000-$DFFF. (Except for the value %000, see above.) %1xx: I/O area visible at $D000-$DFFF. (Except for the value %100, see above.) Bit #3: Datasette output signal level. Bit #4: Datasette button status; 0 = One or more of PLAY, RECORD, F.FWD or REW pressed; 1 = No button is pressed. Bit #5: Datasette motor control; 0 = On; 1 = Off.Default: $37, %00110111.
	*/
	PROCESSOR_PORT ($0001),
	/**
	Unused.
	*/

	/**
	Unused.
	Default: $B1AA, execution address of routine converting floating point to integer.
	*/

	/**
	Unused.
	Default: $B391, execution address of routine converting integer to floating point.
	*/

	/**
	Byte being search for during various operations.
	Current digit of number being input.
	Low byte of first integer operand during AND and OR.
	Low byte of integer-format FAC during INT().
	*/
	BYTE_BEING_SEARCH_FOR_DURING_VARIOUS_OPERATIONS_LOW($0007),
	/**
	Byte being search for during various operations.
	Current byte of BASIC line during tokenization.
	High byte of first integer operand during AND and OR.
	*/
	BYTE_BEING_SEARCH_FOR_DURING_VARIOUS_OPERATIONS_HIGH($0008),
	/**
	Current column number during SPC() and TAB().
	*/
	CURRENT_COLUMN_NUMBER_DURING_SPC ($0009),
	/**
	LOAD/VERIFY switch. Values:
	$00: LOAD. $01-$FF: VERIFY.
	*/
	LOAD_VERIFY_SWITCH_A($000A),
	/**
	Current token during tokenization.
	Length of BASIC line during insertion of line.
	AND/OR switch; $00 = AND; $FF = OR.
	Number of dimensions during array operations.
	*/
	CURRENT_TOKEN_DURING_TOKENIZATION ($000B),
	/**
	Switch for array operations. Values:
	$00: Operation was not called by DIM. $40-$7F: Operation was called by DIM.
	*/
	SWITCH_FOR_ARRAY_OPERATIONS ($000C),
	/**
	Current expression type. Values:
	$00: Numerical. $FF: String.
	*/
	CURRENT_EXPRESSION_TYPE ($000D),
	/**
	Current numerical expression type. Bits:
	Bit #7: 0 = Floating point; 1 = Integer.
	*/
	CURRENT_NUMERICAL_EXPRESSION_TYPE ($000E),
	/**
	Quotation mode switch during tokenization. Bit #6: 0 = Normal mode; 1 = Quotation mode.
	Quotation mode switch during LIST; $01 = Normal mode; $FE = Quotation mode.
	Garbage collection indicator during memory allocation for string variable; $00-$7F = There was no garbage collection yet; $80 = Garbage collection already took place.
	*/
	QUOTATION_MODE_SWITCH_DURING_TOKENIZATION ($000F),
	/**
	Switch during fetch of variable name. Values:
	$00: Integer variables are accepted. $01-$FF: Integer variables are not accepted.
	*/
	SWITCH_DURING_FETCH_OF_VARIABLE_NAME ($0010),
	/**
	GET/INPUT/READ switch. Values:
	$00: INPUT. $40: GET. $98: READ.
	*/
	GET_INPUT_READ_SWITCH ($0011),
	/**
	Sign during SIN() and TAN(). Values:
	$00: Positive. $FF: Negative.
	*/
	SIGN_DURING_SIN ($0012),
	/**
	Current I/O device number.
	Default: $00, keyboard for input and screen for output.
	*/
	CURRENT_I_O_DEVICE_NUMBER ($0013),
	/**
	Line number during GOSUB, GOTO and RUN.
	Second line number during LIST.
	Memory address during PEEK, POKE, SYS and WAIT.
	*/
	LINE_NUMBER_DURING_GOSUB_GOTO_AND_RUN ($0014_$0015),
	/**
	Pointer to next expression in string stack. Values: $19; $1C; $1F; $22.
	Default: $19.
	*/
	POINTER_TO_NEXT_EXPRESSION_IN_STRING_STACK ($0016),
	/**
	Pointer to previous expression in string stack.
	*/
	POINTER_TO_PREVIOUS_EXPRESSION_IN_STRING_STACK ($0017_$0018),
	/**
	String stack, temporary area for processing string expressions (9 bytes, 3 entries).
	*/
	STRING_STACK_TEMPORARY_AREA_FOR_PROCESSING_STRING_EXPRESSIONS ($0019_$0021),
	/**
	Temporary area for various operations (4 bytes).
	*/
	TEMPORARY_AREA_FOR_VARIOUS_OPERATIONS_22($0022_$0025),
	/**
	Auxiliary arithmetical register for division and multiplication (4 bytes).
	*/
	AUXILIARY_ARITHMETICAL_REGISTER_FOR_DIVISION_AND_MULTIPLICATION ($0026_$0029),
	/**
	Unused.
	*/

	/**
	Pointer to beginning of BASIC area.
	Default: $0801, 2049.
	*/
	POINTER_TO_BEGINNING_OF_BASIC_AREA ($002B_$002C),
	/**
	Pointer to beginning of variable area. (End of program plus 1.)
	*/
	POINTER_TO_BEGINNING_OF_VARIABLE_AREA ($002D_$002E),
	/**
	Pointer to beginning of array variable area.
	*/
	POINTER_TO_BEGINNING_OF_ARRAY_VARIABLE_AREA ($002F_$0030),
	/**
	Pointer to end of array variable area.
	*/
	POINTER_TO_END_OF_ARRAY_VARIABLE_AREA ($0031_$0032),
	/**
	Pointer to beginning of string variable area. (Grows downwards from end of BASIC area.)
	*/
	POINTER_TO_BEGINNING_OF_STRING_VARIABLE_AREA ($0033_$0034),
	/**
	Pointer to memory allocated for current string variable.
	*/
	POINTER_TO_MEMORY_ALLOCATED_FOR_CURRENT_STRING_VARIABLE ($0035_$0036),
	/**
	Pointer to end of BASIC area.
	Default: $A000, 40960.
	*/
	POINTER_TO_END_OF_BASIC_AREA ($0037_$0038),
	/**
	Current BASIC line number. Values:
	$0000-$F9FF, 0-63999: Line number. $FF00-$FFFF: Direct mode, no BASIC program is being executed.
	*/
	CURRENT_BASIC_LINE_NUMBER ($0039_$003A),
	/**
	Current BASIC line number for CONT.
	*/
	CURRENT_BASIC_LINE_NUMBER_FOR_CONT ($003B_$003C),
	/**
	Pointer to next BASIC instruction for CONT. Values:
	$0000-$00FF: CONT'ing is not possible. $0100-$FFFF: Pointer to next BASIC instruction.
	*/
	POINTER_TO_NEXT_BASIC_INSTRUCTION_FOR_CONT ($003D_$003E),
	/**
	BASIC line number of current DATA item for READ.
	*/
	BASIC_LINE_NUMBER_OF_CURRENT_DATA_ITEM_FOR_READ ($003F_$0040),
	/**
	Pointer to next DATA item for READ.
	*/
	POINTER_TO_NEXT_DATA_ITEM_FOR_READ ($0041_$0042),
	/**
	Pointer to input result during GET, INPUT and READ.
	*/
	POINTER_TO_INPUT_RESULT_DURING_GET_INPUT_AND_READ ($0043_$0044),
	/**
	Name and type of current variable. Bits:
	$0045 bits #0-#6: First character of variable name. $0046 bits #0-#6: Second character of variable name; $00 = Variable name consists of only one character. $0045 bit #7 and $0046 bit #7: %00: Floating-point variable. %01: String variable. %10: FN function, created with DEF FN. %11: Integer variable.
	*/
	NAME_AND_TYPE_OF_CURRENT_VARIABLE ($0045_$0046),
	/**
	Pointer to value of current variable or FN function.
	*/
	POINTER_TO_VALUE_OF_CURRENT_VARIABLE_OR_FN_FUNCTION ($0047_$0048),
	/**
	Pointer to value of current variable during LET.
	Value of second and third parameter during WAIT.
	Logical number and device number during OPEN.
	$0049, 73: Logical number of CLOSE.
	Device number of LOAD, SAVE and VERIFY.
	*/
	POINTER_TO_VALUE_OF_CURRENT_VARIABLE_DURING_LET ($0049_$004A),
	/**
	Temporary area for saving original pointer to current BASIC instruction during GET, INPUT and READ.
	*/
	TEMPORARY_AREA_FOR_SAVING_ORIGINAL_POINTER_TO_CURRENT_BASIC_INSTRUCTION_DURING_GET_INPUT_AND_READ ($004B_$004C),
	/**
	Comparison operator indicator. Bits:
	Bit #1: 1 = ">" (greater than) is present in expression. Bit #2: 1 = "=" (equal to) is present in expression. Bit #3: 1 = "<" (less than) is present in expression.
	*/
	COMPARISON_OPERATOR_INDICATOR ($004D),
	/**
	Pointer to current FN function.
	*/
	POINTER_TO_CURRENT_FN_FUNCTION ($004E_$004F),
	/**
	Pointer to current string variable during memory allocation.
	*/
	POINTER_TO_CURRENT_STRING_VARIABLE_DURING_MEMORY_ALLOCATION ($0050_$0051),
	/**
	Unused.
	*/

	/**
	Step size of garbage collection. Values: $03; $07.
	*/
	STEP_SIZE_OF_GARBAGE_COLLECTION ($0053),
	/**
	JMP ABS machine instruction, jump to current BASIC function.
	$0055-$0056, 85-86: Execution address of current BASIC function.
	*/
	JMP_ABS_MACHINE_INSTRUCTION_JUMP_TO_CURRENT_BASIC_FUNCTION ($0054_$0056),
	/**
	Arithmetic register #3 (5 bytes).
	*/
	ARITHMETIC_REGISTER_3 ($0057_$005B),
	/**
	Arithmetic register #4 (5 bytes).
	*/
	ARITHMETIC_REGISTER_4 ($005C_$0060),
	/**
	FAC, arithmetic register #1 (5 bytes).
	*/
	FAC_ARITHMETIC_REGISTER_1 ($0061_$0065),
	/**
	Sign of FAC. Bits:
	Bit #7: 0 = Positive; 1 = Negative.
	*/
	SIGN_OF_FAC ($0066),
	/**
	Number of degrees during polynomial evaluation.
	*/
	NUMBER_OF_DEGREES_DURING_POLYNOMIAL_EVALUATION ($0067),
	/**
	Temporary area for various operations.
	*/
	TEMPORARY_AREA_FOR_VARIOUS_OPERATIONS ($0068),
	/**
	ARG, arithmetic register #2 (5 bytes).
	*/
	ARG_ARITHMETIC_REGISTER_2 ($0069_$006D),
	/**
	Sign of ARG. Bits:
	Bit #7: 0 = Positive; 1 = Negative.
	*/
	SIGN_OF_ARG ($006E),
	/**
	Pointer to first string expression during string comparison.
	*/
	POINTER_TO_FIRST_STRING_EXPRESSION_DURING_STRING_COMPARISON ($006F_$0070),
	/**
	Auxiliary pointer during array operations.
	Temporary area for saving original pointer to current BASIC instruction during VAL().
	Pointer to current item of polynomial table during polynomial evaluation.
	*/
	AUXILIARY_POINTER_DURING_ARRAY_OPERATIONS ($0071_$0072),
	/**
	CHRGET. Machine code routine to read next byte from BASIC program or direct command (24 bytes).
	$0079, 121: CHRGOT. Read current byte from BASIC program or direct command.
	$007A-$007B, 122-123: Pointer to current byte in BASIC program or direct command.
	*/
	CHRGET ($0073_$008A),
	/**
	Previous result of RND().
	*/
	PREVIOUS_RESULT_OF_RND ($008B_$008F),
	/**
	Value of ST variable, device status for serial bus and datasette input/output. Serial bus bits:
	Bit #0: Transfer direction during which the timeout occured; 0 = Input; 1 = Output. Bit #1: 1 = Timeout occurred. Bit #4: 1 = VERIFY error occurred (only during VERIFY), the file read from the device did not match that in the memory. Bit #6: 1 = End of file has been reached. Bit #7: 1 = Device is not present.Datasette bits:
	Bit #2: 1 = Block is too short (shorter than 192 bytes). Bit #3: 1 = Block is too long (longer than 192 bytes). Bit #4: 1 = Not all bytes read with error during pass 1 could be corrected during pass 2, or a VERIFY error occurred, the file read from the device did not match that in the memory. Bit #5: 1 = Checksum error occurred. Bit #6: 1 = End of file has been reached (only during reading data files).
	*/
	VALUE_OF_ST_VARIABLE_DEVICE_STATUS_FOR_SERIAL_BUS_AND_DATASETTE_INPUT_OUTPUT ($0090),
	/**
	Stop key indicator. Values:
	$7F: Stop key is pressed. $FF: Stop key is not pressed.
	*/
	STOP_KEY_INDICATOR ($0091),
	/**
	Unknown. (Timing constant during datasette input.)
	*/

	/**
	LOAD/VERIFY switch. Values:
	$00: LOAD. $01-$FF: VERIFY.
	*/
	LOAD_VERIFY_SWITCH ($0093),
	/**
	Serial bus output cache status. Bits:
	Bit #7: 1 = Output cache dirty, must transfer cache contents upon next output to serial bus.
	*/
	SERIAL_BUS_OUTPUT_CACHE_STATUS ($0094),
	/**
	Serial bus output cache, previous byte to be sent to serial bus.
	*/
	SERIAL_BUS_OUTPUT_CACHE_PREVIOUS_BYTE_TO_BE_SENT_TO_SERIAL_BUS ($0095),
	/**
	Unknown. (End of tape indicator during datasette input/output.)
	*/

	/**
	Temporary area for saving original value of Y register during input from RS232.
	Temporary area for saving original value of X register during input from datasette.
	*/
	TEMPORARY_AREA_FOR_SAVING_ORIGINAL_VALUE_OF_Y_REGISTER_DURING_INPUT_FROM_RS232 ($0097),
	/**
	Number of files currently open. Values: $00-$0A, 0-10.
	*/
	NUMBER_OF_FILES_CURRENTLY_OPEN ($0098),
	/**
	Current input device number.
	Default: $00, keyboard.
	*/
	CURRENT_INPUT_DEVICE_NUMBER ($0099),
	/**
	Current output device number.
	Default: $03, screen.
	*/
	CURRENT_OUTPUT_DEVICE_NUMBER ($009A),
	/**
	Unknown. (Parity bit during datasette input/output.)
	*/

	/**
	Unknown. (Byte ready indicator during datasette input/output.)
	*/

	/**
	System error display switch. Bits:
	Bit #6: 0 = Suppress I/O error messages; 1 = Display them. Bit #7: 0 = Suppress system messages; 1 = Display them.
	*/
	SYSTEM_ERROR_DISPLAY_SWITCH ($009D),
	/**
	Byte to be put into output buffer during RS232 and datasette output.
	Block header type during datasette input/output.
	Length of file name during datasette input/output.
	Error counter during LOAD from datasette. Values: $00-$3E, 0-62.
	*/
	BYTE_TO_BE_PUT_INTO_OUTPUT_BUFFER_DURING_RS232_AND_DATASETTE_OUTPUT ($009E),
	/**
	Auxiliary counter for writing file name into datasette buffer.
	Auxiliary counter for comparing requested file name with file name read from datasette during datasette input.
	Error correction counter during LOAD from datasette. Values: $00-$3E, 0-62.
	*/
	AUXILIARY_COUNTER_FOR_WRITING_FILE_NAME_INTO_DATASETTE_BUFFER ($009F),
	/**
	Value of TI variable, time of day, increased by 1 every 1/60 second (on PAL machines). Values: $000000-$4F19FF, 0-518399 (on PAL machines).
	*/
	VALUE_OF_TI_VARIABLE_TIME_OF_DAY_INCREASED_BY_1_EVERY_1_60_SECOND ($00A0_$00A2),
	/**
	EOI switch during serial bus output. Bits:
	Bit #7: 0 = Send byte right after handshake; 1 = Do EOI delay first.Bit counter during datasette output.
	*/
	EOI_SWITCH_DURING_SERIAL_BUS_OUTPUT ($00A3),
	/**
	Byte buffer during serial bus input.
	Parity during datasette input/output.
	*/
	BYTE_BUFFER_DURING_SERIAL_BUS_INPUT ($00A4),
	/**
	Bit counter during serial bus input/output.
	Counter for sync mark during datasette output.
	*/
	BIT_COUNTER_DURING_SERIAL_BUS_INPUT_OUTPUT ($00A5),
	/**
	Offset of current byte in datasette buffer.
	*/
	OFFSET_OF_CURRENT_BYTE_IN_DATASETTE_BUFFER ($00A6),
	/**
	Bit buffer during RS232 input.
	*/
	BIT_BUFFER_DURING_RS232_INPUT ($00A7),
	/**
	Bit counter during RS232 input.
	*/
	BIT_COUNTER_DURING_RS232_INPUT ($00A8),
	/**
	Stop bit switch during RS232 input. Values:
	$00: Data bit. $01-$FF: Stop bit.
	*/
	STOP_BIT_SWITCH_DURING_RS232_INPUT ($00A9),
	/**
	Byte buffer during RS232 input.
	*/
	BYTE_BUFFER_DURING_RS232_INPUT ($00AA),
	/**
	Parity during RS232 input.
	Computed block checksum during datasette input.
	*/
	PARITY_DURING_RS232_INPUT ($00AB),
	/**
	Start address for SAVE to serial bus.
	Pointer to current byte during SAVE to serial bus or datasette.
	Pointer to line in screen memory to be scrolled during scrolling the screen.
	*/
	START_ADDRESS_FOR_SAVE_TO_SERIAL_BUS ($00AC_$00AD),
	/**
	Load address read from input file and pointer to current byte during LOAD/VERIFY from serial bus.
	End address after LOAD/VERIFY from serial bus or datasette.
	End address for SAVE to serial bus or datasette.
	Pointer to line in Color RAM to be scrolled during scrolling the screen.
	*/
	LOAD_ADDRESS_READ_FROM_INPUT_FILE_AND_POINTER_TO_CURRENT_BYTE_DURING_LOAD_VERIFY_FROM_SERIAL_BUS ($00AE_$00AF),
	/**
	Unknown.
	*/

	/**
	Pointer to datasette buffer.
	Default: $033C, 828.
	*/
	POINTER_TO_DATASETTE_BUFFER ($00B2_$00B3),
	/**
	Bit counter and stop bit switch during RS232 output. Bits:
	Bits #0-#6: Bit count. Bit #7: 0 = Data bit; 1 = Stop bit.Bit counter during datasette input/output.
	*/
	BIT_COUNTER_AND_STOP_BIT_SWITCH_DURING_RS232_OUTPUT ($00B4),
	/**
	Bit buffer (in bit #2) during RS232 output.
	*/
	BIT_BUFFER_DURING_RS232_OUTPUT ($00B5),
	/**
	Byte buffer during RS232 output.
	*/
	BYTE_BUFFER_DURING_RS232_OUTPUT ($00B6),
	/**
	Length of file name or disk command; first parameter of LOAD, SAVE and VERIFY or fourth parameter of OPEN. Values:
	$00: No parameter. $01-$FF: Parameter length.
	*/
	LENGTH_OF_FILE_NAME_OR_DISK_COMMAND_FIRST_PARAMETER_OF_LOAD_SAVE_AND_VERIFY_OR_FOURTH_PARAMETER_OF_OPEN ($00B7),
	/**
	Logical number of current file.
	*/
	LOGICAL_NUMBER_OF_CURRENT_FILE ($00B8),
	/**
	Secondary address of current file.
	*/
	SECONDARY_ADDRESS_OF_CURRENT_FILE ($00B9),
	/**
	Device number of current file.
	*/
	DEVICE_NUMBER_OF_CURRENT_FILE ($00BA),
	/**
	Pointer to current file name or disk command; first parameter of LOAD, SAVE and VERIFY or fourth parameter of OPEN.
	*/
	POINTER_TO_CURRENT_FILE_NAME_OR_DISK_COMMAND_FIRST_PARAMETER_OF_LOAD_SAVE_AND_VERIFY_OR_FOURTH_PARAMETER_OF_OPEN ($00BB_$00BC),
	/**
	Parity during RS232 output.
	Byte buffer during datasette input/output.
	*/
	PARITY_DURING_RS232_OUTPUT ($00BD),
	/**
	Block counter during datasette input/output.
	*/
	BLOCK_COUNTER_DURING_DATASETTE_INPUT_OUTPUT ($00BE),
	/**
	Unknown.
	*/

	/**
	Datasette motor switch. Values:
	$00: No button was pressed, motor has been switched off. If a button is pressed on the datasette, must switch motor on. $01-$FF: Motor is on.
	*/
	DATASETTE_MOTOR_SWITCH ($00C0),
	/**
	Start address during SAVE to serial bus, LOAD and VERIFY from datasette and SAVE to datasette.
	Pointer to current byte during memory test.
	*/
	START_ADDRESS_DURING_SAVE_TO_SERIAL_BUS_LOAD_AND_VERIFY_FROM_DATASETTE_AND_SAVE_TO_DATASETTE ($00C1_$00C2),
	/**
	Start address for a secondary address of 0 for LOAD and VERIFY from serial bus or datasette.
	Pointer to ROM table of default vectors during initialization of I/O vectors.
	*/
	START_ADDRESS_FOR_A_SECONDARY_ADDRESS_OF_0_FOR_LOAD_AND_VERIFY_FROM_SERIAL_BUS_OR_DATASETTE ($00C3_$00C4),
	/**
	Matrix code of key previously pressed. Values:
	$00-$3F: Keyboard matrix code. $40: No key was pressed at the time of previous check.
	*/
	MATRIX_CODE_OF_KEY_PREVIOUSLY_PRESSED ($00C5),
	/**
	Length of keyboard buffer. Values:
	$00, 0: Buffer is empty. $01-$0A, 1-10: Buffer length.
	*/
	LENGTH_OF_KEYBOARD_BUFFER ($00C6),
	/**
	Reverse mode switch. Values:
	$00: Normal mode. $12: Reverse mode.
	*/
	REVERSE_MODE_SWITCH ($00C7),
	/**
	Length of line minus 1 during screen input. Values: $27, 39; $4F, 79.
	*/
	LENGTH_OF_LINE_MINUS_1_DURING_SCREEN_INPUT ($00C8),
	/**
	Cursor row during screen input. Values: $00-$18, 0-24.
	*/
	CURSOR_ROW_DURING_SCREEN_INPUT ($00C9),
	/**
	Cursor column during screen input. Values: $00-$27, 0-39.
	*/
	CURSOR_COLUMN_DURING_SCREEN_INPUT ($00CA),
	/**
	Matrix code of key currently being pressed. Values:
	$00-$3F: Keyboard matrix code. $40: No key is currently pressed.
	*/
	MATRIX_CODE_OF_KEY_CURRENTLY_BEING_PRESSED ($00CB),
	/**
	Cursor visibility switch. Values:
	$00: Cursor is on. $01-$FF: Cursor is off.
	*/
	CURSOR_VISIBILITY_SWITCH ($00CC),
	/**
	Delay counter for changing cursor phase. Values:
	$00, 0: Must change cursor phase. $01-$14, 1-20: Delay.
	*/
	DELAY_COUNTER_FOR_CHANGING_CURSOR_PHASE ($00CD),
	/**
	Screen code of character under cursor.
	*/
	SCREEN_CODE_OF_CHARACTER_UNDER_CURSOR ($00CE),
	/**
	Cursor phase switch. Values:
	$00: Cursor off phase, original character visible. $01: Cursor on phase, reverse character visible.
	*/
	CURSOR_PHASE_SWITCH ($00CF),
	/**
	End of line switch during screen input. Values:
	$00: Return character reached, end of line. $01-$FF: Still reading characters from line.
	*/
	END_OF_LINE_SWITCH_DURING_SCREEN_INPUT ($00D0),
	/**
	Pointer to current line in screen memory.
	*/
	POINTER_TO_CURRENT_LINE_IN_SCREEN_MEMORY ($00D1_$00D2),
	/**
	Current cursor column. Values: $00-$27, 0-39.
	*/
	CURRENT_CURSOR_COLUMN ($00D3),
	/**
	Quotation mode switch. Values:
	$00: Normal mode. $01: Quotation mode.
	*/
	QUOTATION_MODE_SWITCH ($00D4),
	/**
	Length of current screen line minus 1. Values: $27, 39; $4F, 79.
	*/
	LENGTH_OF_CURRENT_SCREEN_LINE_MINUS_1 ($00D5),
	/**
	Current cursor row. Values: $00-$18, 0-24.
	*/
	CURRENT_CURSOR_ROW ($00D6),
	/**
	PETSCII code of character during screen input/output.
	Bit buffer during datasette input.
	Block checksum during datasette output.
	*/
	PETSCII_CODE_OF_CHARACTER_DURING_SCREEN_INPUT_OUTPUT ($00D7),
	/**
	Number of insertions. Values:
	$00: No insertions made, normal mode, control codes change screen layout or behavior. $01-$FF: Number of insertions, when inputting this many character next, those must be turned into control codes, similarly to quotation mode.
	*/
	NUMBER_OF_INSERTIONS ($00D8),
	/**
	High byte of pointers to each line in screen memory (25 bytes). Values:
	$00-$7F: Pointer high byte. $80-$FF: No pointer, line is an extension of previous line on screen.
	*/
	HIGH_BYTE_OF_POINTERS_TO_EACH_LINE_IN_SCREEN_MEMORY ($00D9_$00F1),
	/**
	Temporary area during scrolling the screen.
	*/
	TEMPORARY_AREA_DURING_SCROLLING_THE_SCREEN ($00F2),
	/**
	Pointer to current line in Color RAM.
	*/
	POINTER_TO_CURRENT_LINE_IN_COLOR_RAM ($00F3_$00F4),
	/**
	Pointer to current conversion table during conversion from keyboard matrix codes to PETSCII codes.
	*/
	POINTER_TO_CURRENT_CONVERSION_TABLE_DURING_CONVERSION_FROM_KEYBOARD_MATRIX_CODES_TO_PETSCII_CODES ($00F5_$00F6),
	/**
	Pointer to RS232 input buffer. Values:
	$0000-$00FF: No buffer defined, a new buffer must be allocated upon RS232 input. $0100-$FFFF: Buffer pointer.
	*/
	POINTER_TO_RS232_INPUT_BUFFER ($00F7_$00F8),
	/**
	Pointer to RS232 output buffer. Values:
	$0000-$00FF: No buffer defined, a new buffer must be allocated upon RS232 output. $0100-$FFFF: Buffer pointer.
	*/
	POINTER_TO_RS232_OUTPUT_BUFFER ($00F9_$00FA),
	/**
	Unused (4 bytes).
	*/

	/**
	Buffer for conversion from floating point to string (12 bytes).
	*/
	BUFFER_FOR_CONVERSION_FROM_FLOATING_POINT_TO_STRING ($00FF_$010A),
	/**

	*/

	/**
	Pointers to bytes read with error during datasette input (62 bytes, 31 entries).
	*/
	POINTERS_TO_BYTES_READ_WITH_ERROR_DURING_DATASETTE_INPUT ($0100_$013D),
	/**
	Processor stack. Also used for storing data related to FOR and GOSUB.
	*/
	PROCESSOR_STACK ($0100_$01FF),
	/**

	*/

	/**
	Input buffer, storage area for data read from screen (89 bytes).
	*/
	INPUT_BUFFER_STORAGE_AREA_FOR_DATA_READ_FROM_SCREEN ($0200_$0258),
	/**
	Logical numbers assigned to files (10 bytes, 10 entries).
	*/
	LOGICAL_NUMBERS_ASSIGNED_TO_FILES ($0259_$0262),
	/**
	Device numbers assigned to files (10 bytes, 10 entries).
	*/
	DEVICE_NUMBERS_ASSIGNED_TO_FILES ($0263_$026C),
	/**
	Secondary addresses assigned to files (10 bytes, 10 entries).
	*/
	SECONDARY_ADDRESSES_ASSIGNED_TO_FILES ($026D_$0276),
	/**
	Keyboard buffer (10 bytes, 10 entries).
	*/
	KEYBOARD_BUFFER ($0277_$0280),
	/**
	Pointer to beginning of BASIC area after memory test.
	Default: $0800, 2048.
	*/
	POINTER_TO_BEGINNING_OF_BASIC_AREA_AFTER_MEMORY_TEST ($0281_$0282),
	/**
	Pointer to end of BASIC area after memory test.
	Default: $A000, 40960.
	*/
	POINTER_TO_END_OF_BASIC_AREA_AFTER_MEMORY_TEST ($0283_$0284),
	/**
	Unused. (Serial bus timeout.)
	*/

	/**
	Current color, cursor color. Values: $00-$0F, 0-15.
	*/
	CURRENT_COLOR_CURSOR_COLOR ($0286),
	/**
	Color of character under cursor. Values: $00-$0F, 0-15.
	*/
	COLOR_OF_CHARACTER_UNDER_CURSOR ($0287),
	/**
	High byte of pointer to screen memory for screen input/output.
	Default: $04, $0400, 1024.
	*/
	HIGH_BYTE_OF_POINTER_TO_SCREEN_MEMORY_FOR_SCREEN_INPUT_OUTPUT ($0288),
	/**
	Maximum length of keyboard buffer. Values:
	$00, 0: No buffer. $01-$0F, 1-15: Buffer size.
	*/
	MAXIMUM_LENGTH_OF_KEYBOARD_BUFFER ($0289),
	/**
	Keyboard repeat switch. Bits:
	Bits #6-#7: %00 = Only cursor up/down, cursor left/right, Insert/Delete and Space repeat; %01 = No key repeats; %1x = All keys repeat.
	*/
	KEYBOARD_REPEAT_SWITCH ($028A),
	/**
	Delay counter during repeat sequence, for delaying between successive repeats. Values:
	$00, 0: Must repeat key. $01-$04, 1-4: Delay repetition.
	*/
	DELAY_COUNTER_DURING_REPEAT_SEQUENCE_FOR_DELAYING_BETWEEN_SUCCESSIVE_REPEATS ($028B),
	/**
	Repeat sequence delay counter, for delaying before first repetition. Values:
	$00, 0: Must start repeat sequence. $01-$10, 1-16: Delay repeat sequence.
	*/
	REPEAT_SEQUENCE_DELAY_COUNTER_FOR_DELAYING_BEFORE_FIRST_REPETITION ($028C),
	/**
	Shift key indicator. Bits:
	Bit #0: 1 = One or more of left Shift, right Shift or Shift Lock is currently being pressed or locked. Bit #1: 1 = Commodore is currently being pressed. Bit #2: 1 = Control is currently being pressed.
	*/
	SHIFT_KEY_INDICATOR ($028D),
	/**
	Previous value of shift key indicator. Bits:
	Bit #0: 1 = One or more of left Shift, right Shift or Shift Lock was pressed or locked at the time of previous check. Bit #1: 1 = Commodore was pressed at the time of previous check. Bit #2: 1 = Control was pressed at the time of previous check.
	*/
	PREVIOUS_VALUE_OF_SHIFT_KEY_INDICATOR ($028E),
	/**
	Execution address of routine that, based on the status of shift keys, sets the pointer at memory address $00F5-$00F6 to the appropriate conversion table for converting keyboard matrix codes to PETSCII codes.
	Default: $EB48.
	*/
	EXECUTION_ADDRESS_OF_ROUTINE_THAT_BASED_ON_THE_STATUS_OF_SHIFT_KEYS_SETS_THE_POINTER_AT_MEMORY_ADDRESS_00F5_00F6_TO_THE_APPROPRIATE_CONVERSION_TABLE_FOR_CONVERTING_KEYBOARD_MATRIX_CODES_TO_PETSCII_CODES ($028F_$0290),
	/**
	Commodore-Shift switch. Bits:
	Bit #7: 0 = Commodore-Shift is disabled; 1 = Commodore-Shift is enabled, the key combination will toggle between the uppercase/graphics and lowercase/uppercase character set.
	*/
	COMMODORE_SHIFT_SWITCH ($0291),
	/**
	Scroll direction switch during scrolling the screen. Values:
	$00: Insertion of line before current line, current line and all lines below it must be scrolled 1 line downwards. $01-$FF: Bottom of screen reached, complete screen must be scrolled 1 line upwards.
	*/
	SCROLL_DIRECTION_SWITCH_DURING_SCROLLING_THE_SCREEN ($0292),
	/**
	RS232 control register. Bits:
	Bits #0-#3: Baud rate, transfer speed. Values: %0000: User specified. %0001: 50 bit/s. %0010: 75 bit/s. %0011: 110 bit/s. %0100: 150 bit/s. %0101: 300 bit/s. %0110: 600 bit/s. %0111: 1200 bit/s. %1000: 2400 bit/s. %1001: 1800 bit/s. %1010: 2400 bit/s. %1011: 3600 bit/s. %1100: 4800 bit/s. %1101: 7200 bit/s. %1110: 9600 bit/s. %1111: 19200 bit/s. Bits #5-#6: Byte size, number of data bits per byte; %00 = 8; %01 = 7, %10 = 6; %11 = 5. Bit #7: Number of stop bits; 0 = 1 stop bit; 1 = 2 stop bits.
	*/
	RS232_CONTROL_REGISTER ($0293),
	/**
	RS232 command register. Bits:
	Bit #0: Synchronization type; 0 = 3 lines; 1 = X lines. Bit #4: Transmission type; 0 = Duplex; 1 = Half duplex. Bits #5-#7: Parity mode. Values: %xx0: No parity check, bit #7 does not exist. %001: Odd parity. %011: Even parity. %101: No parity check, bit #7 is always 1. %111: No parity check, bit #7 is always 0.
	*/
	RS232_COMMAND_REGISTER ($0294),
	/**
	Default value of RS232 output timer, based on baud rate. (Must be filled with actual value before RS232 input/output if baud rate is "user specified" in RS232 control register, memory address $0293.)
	*/
	DEFAULT_VALUE_OF_RS232_OUTPUT_TIMER_BASED_ON_BAUD_RATE ($0295_$0296),
	/**
	Value of ST variable, device status for RS232 input/output. Bits:
	Bit #0: 1 = Parity error occurred. Bit #1: 1 = Frame error, a stop bit with the value of 0, occurred. Bit #2: 1 = Input buffer underflow occurred, too much data has arrived but it has not been read from the buffer in time. Bit #3: 1 = Input buffer is empty, nothing to read. Bit #4: 0 = Sender is Clear To Send; 1 = Sender is not ready to send data to receiver. Bit #6: 0 = Receiver reports Data Set Ready; 1 = Receiver is not ready to receive data. Bit #7: 1 = Carrier loss, a stop bit and a data byte both with the value of 0, detected.
	*/
	VALUE_OF_ST_VARIABLE_DEVICE_STATUS_FOR_RS232_INPUT_OUTPUT ($0297),
	/**
	RS232 byte size, number of data bits per data byte, default value for bit counters.
	*/
	RS232_BYTE_SIZE_NUMBER_OF_DATA_BITS_PER_DATA_BYTE_DEFAULT_VALUE_FOR_BIT_COUNTERS ($0298),
	/**
	Default value of RS232 input timer, based on baud rate. (Calculated automatically from default value of RS232 output timer, at memory address $0295-$0296.)
	*/
	DEFAULT_VALUE_OF_RS232_INPUT_TIMER_BASED_ON_BAUD_RATE ($0299_$029A),
	/**
	Offset of byte received in RS232 input buffer.
	*/
	OFFSET_OF_BYTE_RECEIVED_IN_RS232_INPUT_BUFFER ($029B),
	/**
	Offset of current byte in RS232 input buffer.
	*/
	OFFSET_OF_CURRENT_BYTE_IN_RS232_INPUT_BUFFER ($029C),
	/**
	Offset of byte to send in RS232 output buffer.
	*/
	OFFSET_OF_BYTE_TO_SEND_IN_RS232_OUTPUT_BUFFER ($029D),
	/**
	Offset of current byte in RS232 output buffer.
	*/
	OFFSET_OF_CURRENT_BYTE_IN_RS232_OUTPUT_BUFFER ($029E),
	/**
	Temporary area for saving pointer to original interrupt service routine during datasette input output. Values:
	$0000-$00FF: No datasette input/output took place yet or original pointer has been already restored. $0100-$FFFF: Original pointer, datasette input/output currently in progress.
	*/
	TEMPORARY_AREA_FOR_SAVING_POINTER_TO_ORIGINAL_INTERRUPT_SERVICE_ROUTINE_DURING_DATASETTE_INPUT_OUTPUT ($029F_$02A0),
	/**
	Temporary area for saving original value of CIA#2 interrupt control register, at memory address $DD0D, during RS232 input/output.
	*/
	TEMPORARY_AREA_FOR_SAVING_ORIGINAL_VALUE_OF_CIA_2_INTERRUPT_CONTROL_REGISTER_AT_MEMORY_ADDRESS_DD0D_DURING_RS232_INPUT_OUTPUT ($02A1),
	/**
	Temporary area for saving original value of CIA#1 timer #1 control register, at memory address $DC0E, during datasette input/output.
	*/
	TEMPORARY_AREA_FOR_SAVING_ORIGINAL_VALUE_OF_CIA_1_TIMER_1_CONTROL_REGISTER_AT_MEMORY_ADDRESS_DC0E_DURING_DATASETTE_INPUT_OUTPUT ($02A2),
	/**
	Unknown.
	*/

	/**
	Number of line currently being scrolled during scrolling the screen.
	*/
	NUMBER_OF_LINE_CURRENTLY_BEING_SCROLLED_DURING_SCROLLING_THE_SCREEN ($02A5),
	/**
	PAL/NTSC switch, for selecting RS232 baud rate from the proper table. Values:
	$00: NTSC. $01: PAL.
	*/
	PAL_NTSC_SWITCH_FOR_SELECTING_RS232_BAUD_RATE_FROM_THE_PROPER_TABLE ($02A6),
	/**
	Unused (89 bytes).
	*/

	/**

	*/

	/**
	Execution address of warm reset, displaying optional BASIC error message and entering BASIC idle loop.
	Default: $E38B.
	*/
	EXECUTION_ADDRESS_OF_WARM_RESET_DISPLAYING_OPTIONAL_BASIC_ERROR_MESSAGE_AND_ENTERING_BASIC_IDLE_LOOP ($0300_$0301),
	/**
	Execution address of BASIC idle loop.
	Default: $A483.
	*/
	EXECUTION_ADDRESS_OF_BASIC_IDLE_LOOP ($0302_$0303),
	/**
	Execution address of BASIC line tokenizater routine.
	Default: $A57C.
	*/
	EXECUTION_ADDRESS_OF_BASIC_LINE_TOKENIZATER_ROUTINE ($0304_$0305),
	/**
	Execution address of BASIC token decoder routine.
	Default: $A71A.
	*/
	EXECUTION_ADDRESS_OF_BASIC_TOKEN_DECODER_ROUTINE ($0306_$0307),
	/**
	Execution address of BASIC instruction executor routine.
	Default: $A7E4.
	*/
	EXECUTION_ADDRESS_OF_BASIC_INSTRUCTION_EXECUTOR_ROUTINE ($0308_$0309),
	/**
	Execution address of routine reading next item of BASIC expression.
	Default: $AE86.
	*/
	EXECUTION_ADDRESS_OF_ROUTINE_READING_NEXT_ITEM_OF_BASIC_EXPRESSION ($030A_$030B),
	/**
	Default value of register A for SYS.
	Value of register A after SYS.
	*/
	DEFAULT_VALUE_OF_REGISTER_A_FOR_SYS ($030C),
	/**
	Default value of register X for SYS.
	Value of register X after SYS.
	*/
	DEFAULT_VALUE_OF_REGISTER_X_FOR_SYS ($030D),
	/**
	Default value of register Y for SYS.
	Value of register Y after SYS.
	*/
	DEFAULT_VALUE_OF_REGISTER_Y_FOR_SYS ($030E),
	/**
	Default value of status register for SYS.
	Value of status register after SYS.
	*/
	DEFAULT_VALUE_OF_STATUS_REGISTER_FOR_SYS ($030F),
	/**
	JMP ABS machine instruction, jump to USR() function.
	$0311-$0312, 785-786: Execution address of USR() function.
	*/
	JMP_ABS_MACHINE_INSTRUCTION_JUMP_TO_USR_FUNCTION ($0310_$0312),
	/**
	Unused.
	*/

	/**
	Execution address of interrupt service routine.
	Default: $EA31.
	*/
	EXECUTION_ADDRESS_OF_INTERRUPT_SERVICE_ROUTINE ($0314_$0315),
	/**
	Execution address of BRK service routine.
	Default: $FE66.
	*/
	EXECUTION_ADDRESS_OF_BRK_SERVICE_ROUTINE ($0316_$0317),
	/**
	Execution address of non-maskable interrupt service routine.
	Default: $FE47.
	*/
	EXECUTION_ADDRESS_OF_NON_MASKABLE_INTERRUPT_SERVICE_ROUTINE ($0318_$0319),
	/**
	Execution address of OPEN, routine opening files.
	Default: $F34A.
	*/
	EXECUTION_ADDRESS_OF_OPEN_ROUTINE_OPENING_FILES ($031A_$031B),
	/**
	Execution address of CLOSE, routine closing files.
	Default: $F291.
	*/
	EXECUTION_ADDRESS_OF_CLOSE_ROUTINE_CLOSING_FILES ($031C_$031D),
	/**
	Execution address of CHKIN, routine defining file as default input.
	Default: $F20E.
	*/
	EXECUTION_ADDRESS_OF_CHKIN_ROUTINE_DEFINING_FILE_AS_DEFAULT_INPUT ($031E_$031F),
	/**
	Execution address of CHKOUT, routine defining file as default output.
	Default: $F250.
	*/
	EXECUTION_ADDRESS_OF_CHKOUT_ROUTINE_DEFINING_FILE_AS_DEFAULT_OUTPUT ($0320_$0321),
	/**
	Execution address of CLRCHN, routine initializating input/output.
	Default: $F333.
	*/
	EXECUTION_ADDRESS_OF_CLRCHN_ROUTINE_INITIALIZATING_INPUT_OUTPUT ($0322_$0323),
	/**
	Execution address of CHRIN, data input routine, except for keyboard and RS232 input.
	Default: $F157.
	*/
	EXECUTION_ADDRESS_OF_CHRIN_DATA_INPUT_ROUTINE_EXCEPT_FOR_KEYBOARD_AND_RS232_INPUT ($0324_$0325),
	/**
	Execution address of CHROUT, general purpose data output routine.
	Default: $F1CA.
	*/
	EXECUTION_ADDRESS_OF_CHROUT_GENERAL_PURPOSE_DATA_OUTPUT_ROUTINE ($0326_$0327),
	/**
	Execution address of STOP, routine checking the status of Stop key indicator, at memory address $0091.
	Default: $F6ED.
	*/
	EXECUTION_ADDRESS_OF_STOP_ROUTINE_CHECKING_THE_STATUS_OF_STOP_KEY_INDICATOR_AT_MEMORY_ADDRESS_0091 ($0328_$0329),
	/**
	Execution address of GETIN, general purpose data input routine.
	Default: $F13E.
	*/
	EXECUTION_ADDRESS_OF_GETIN_GENERAL_PURPOSE_DATA_INPUT_ROUTINE ($032A_$032B),
	/**
	Execution address of CLALL, routine initializing input/output and clearing all file assignment tables.
	Default: $F32F.
	*/
	EXECUTION_ADDRESS_OF_CLALL_ROUTINE_INITIALIZING_INPUT_OUTPUT_AND_CLEARING_ALL_FILE_ASSIGNMENT_TABLES ($032C_$032D),
	/**
	Unused.
	Default: $FE66.
	*/

	/**
	Execution address of LOAD, routine loading files.
	Default: $F4A5.
	*/
	EXECUTION_ADDRESS_OF_LOAD_ROUTINE_LOADING_FILES ($0330_$0331),
	/**
	Execution address of SAVE, routine saving files.
	Default: $F5ED.
	*/
	EXECUTION_ADDRESS_OF_SAVE_ROUTINE_SAVING_FILES ($0332_$0333),
	/**
	Unused (8 bytes).
	*/

	/**
	Datasette buffer (192 bytes).
	*/
	DATASETTE_BUFFER ($033C_$03FB),
	/**
	Unused (4 bytes).
	*/

	/**

	*/

	/**
	Default area of screen memory (1000 bytes).
	*/
	DEFAULT_AREA_OF_SCREEN_MEMORY ($0400_$07E7),
	/**
	Unused (16 bytes).
	*/

	/**
	Default area for sprite pointers (8 bytes).
	*/
	DEFAULT_AREA_FOR_SPRITE_POINTERS ($07F8_$07FF),
	/**

	*/

	/**
	Unused. (Must contain a value of 0 so that the BASIC program can be RUN.)
	*/

	/**
	Default BASIC area (38911 bytes).
	*/
	DEFAULT_BASIC_AREA ($0801_$9FFF),
	/**
	Optional cartridge ROM (8192 bytes).
	$8000-$8001, 32768-32769: Execution address of cold reset.
	$8002-$8003, 32770-32771: Execution address of non-maskable interrupt service routine.
	$8004-$8008, 32772-32776: Cartridge signature. If contains the uppercase PETSCII string "CBM80" ($C3,$C2,$CD,$38,$30) then the routine vectors are accepted by the KERNAL.
	*/
	OPTIONAL_CARTRIDGE_ROM ($8000_$9FFF),
	/**

	*/

	/**
	BASIC ROM or RAM area (8192 bytes). depends on the value of bits #0-#2 of the processor port at memory address $0001:%x00, %x01 or %x10: RAM area. %x11: BASIC ROM.
	*/
	BASIC_ROM_OR_RAM_AREA ($A000_$BFFF),
	/**

	*/

	/**
	Upper RAM area (4096 bytes).
	*/
	UPPER_RAM_AREA ($C000_$CFFF),
	/**

	*/

	/**
	I/O Area (memory mapped chip registers), Character ROM or RAM area (4096 bytes). depends on the value of bits #0-#2 of the processor port at memory address $0001:%x00: RAM area. %0xx: Character ROM. (Except for the value %000, see above.) %1xx: I/O Area. (Except for the value %100, see above.)
	*/
	I_O_AREA ($D000_$DFFF),
	/**

	*/

	/**
	Character ROM, shape of characters (4096 bytes).
	*/
	CHARACTER_ROM_SHAPE_OF_CHARACTERS ($D000_$DFFF_),
	/**
	Shape of characters in uppercase/graphics character set (2048 bytes, 256 entries).
	*/
	SHAPE_OF_CHARACTERS_IN_UPPERCASE_GRAPHICS_CHARACTER_SET ($D000_$D7FF),
	/**
	Shape of characters in lowercase/uppercase character set (2048 bytes, 256 entries).
	*/
	SHAPE_OF_CHARACTERS_IN_LOWERCASE_UPPERCASE_CHARACTER_SET ($D800_$DFFF),
	/**

	*/

	/**
	Sprite #0 X-coordinate (only bits #0-#7).
	*/
	SPRITE_0_X_COORDINATE ($D000),
	/**
	Sprite #0 Y-coordinate.
	*/
	SPRITE_0_Y_COORDINATE ($D001),
	/**
	Sprite #1 X-coordinate (only bits #0-#7).
	*/
	SPRITE_1_X_COORDINATE ($D002),
	/**
	Sprite #1 Y-coordinate.
	*/
	SPRITE_1_Y_COORDINATE ($D003),
	/**
	Sprite #2 X-coordinate (only bits #0-#7).
	*/
	SPRITE_2_X_COORDINATE ($D004),
	/**
	Sprite #2 Y-coordinate.
	*/
	SPRITE_2_Y_COORDINATE ($D005),
	/**
	Sprite #3 X-coordinate (only bits #0-#7).
	*/
	SPRITE_3_X_COORDINATE ($D006),
	/**
	Sprite #3 Y-coordinate.
	*/
	SPRITE_3_Y_COORDINATE ($D007),
	/**
	Sprite #4 X-coordinate (only bits #0-#7).
	*/
	SPRITE_4_X_COORDINATE ($D008),
	/**
	Sprite #4 Y-coordinate.
	*/
	SPRITE_4_Y_COORDINATE ($D009),
	/**
	Sprite #5 X-coordinate (only bits #0-#7).
	*/
	SPRITE_5_X_COORDINATE ($D00A),
	/**
	Sprite #5 Y-coordinate.
	*/
	SPRITE_5_Y_COORDINATE ($D00B),
	/**
	Sprite #6 X-coordinate (only bits #0-#7).
	*/
	SPRITE_6_X_COORDINATE ($D00C),
	/**
	Sprite #6 Y-coordinate.
	*/
	SPRITE_6_Y_COORDINATE ($D00D),
	/**
	Sprite #7 X-coordinate (only bits #0-#7).
	*/
	SPRITE_7_X_COORDINATE ($D00E),
	/**
	Sprite #7 Y-coordinate.
	*/
	SPRITE_7_Y_COORDINATE ($D00F),
	/**
	Sprite #0-#7 X-coordinates (bit #8). Bits:
	Bit #x: Sprite #x X-coordinate bit #8.
	*/
	SPRITE_0_7_X_COORDINATES ($D010),
	/**
	Screen control register #1. Bits:
	Bits #0-#2: Vertical raster scroll. Bit #3: Screen height; 0 = 24 rows; 1 = 25 rows. Bit #4: 0 = Screen off, complete screen is covered by border; 1 = Screen on, normal screen contents are visible. Bit #5: 0 = Text mode; 1 = Bitmap mode. Bit #6: 1 = Extended background mode on. Bit #7: Read: Current raster line (bit #8). Write: Raster line to generate interrupt at (bit #8).Default: $1B, %00011011.
	*/
	SCREEN_CONTROL_REGISTER_1 ($D011),
	/**
	Read: Current raster line (bits #0-#7).
	Write: Raster line to generate interrupt at (bits #0-#7).
	*/
	READ_CURRENT_RASTER_LINE ($D012),
	/**
	Light pen X-coordinate (bits #1-#8).
	Read-only.
	*/
	LIGHT_PEN_X_COORDINATE ($D013),
	/**
	Light pen Y-coordinate.
	Read-only.
	*/
	LIGHT_PEN_Y_COORDINATE ($D014),
	/**
	Sprite enable register. Bits:
	Bit #x: 1 = Sprite #x is enabled, drawn onto the screen.
	*/
	SPRITE_ENABLE_REGISTER ($D015),
	/**
	Screen control register #2. Bits:
	Bits #0-#2: Horizontal raster scroll. Bit #3: Screen width; 0 = 38 columns; 1 = 40 columns. Bit #4: 1 = Multicolor mode on.Default: $C8, %11001000.
	*/
	SCREEN_CONTROL_REGISTER_2 ($D016),
	/**
	Sprite double height register. Bits:
	Bit #x: 1 = Sprite #x is stretched to double height.
	*/
	SPRITE_DOUBLE_HEIGHT_REGISTER ($D017),
	/**
	Memory setup register. Bits:
	Bits #1-#3: In text mode, pointer to character memory (bits #11-#13), relative to VIC bank, memory address $DD00. Values: %000, 0: $0000-$07FF, 0-2047. %001, 1: $0800-$0FFF, 2048-4095. %010, 2: $1000-$17FF, 4096-6143. %011, 3: $1800-$1FFF, 6144-8191. %100, 4: $2000-$27FF, 8192-10239. %101, 5: $2800-$2FFF, 10240-12287. %110, 6: $3000-$37FF, 12288-14335. %111, 7: $3800-$3FFF, 14336-16383. Values %010 and %011 in VIC bank #0 and #2 select Character ROM instead. In bitmap mode, pointer to bitmap memory (bit #13), relative to VIC bank, memory address $DD00. Values: %0xx, 0: $0000-$1FFF, 0-8191. %1xx, 4: $2000-$3FFF, 8192-16383. Bits #4-#7: Pointer to screen memory (bits #10-#13), relative to VIC bank, memory address $DD00. Values: %0000, 0: $0000-$03FF, 0-1023. %0001, 1: $0400-$07FF, 1024-2047. %0010, 2: $0800-$0BFF, 2048-3071. %0011, 3: $0C00-$0FFF, 3072-4095. %0100, 4: $1000-$13FF, 4096-5119. %0101, 5: $1400-$17FF, 5120-6143. %0110, 6: $1800-$1BFF, 6144-7167. %0111, 7: $1C00-$1FFF, 7168-8191. %1000, 8: $2000-$23FF, 8192-9215. %1001, 9: $2400-$27FF, 9216-10239. %1010, 10: $2800-$2BFF, 10240-11263. %1011, 11: $2C00-$2FFF, 11264-12287. %1100, 12: $3000-$33FF, 12288-13311. %1101, 13: $3400-$37FF, 13312-14335. %1110, 14: $3800-$3BFF, 14336-15359. %1111, 15: $3C00-$3FFF, 15360-16383.
	*/
	MEMORY_SETUP_REGISTER ($D018),
	/**
	Interrupt status register. Read bits:
	Bit #0: 1 = Current raster line is equal to the raster line to generate interrupt at. Bit #1: 1 = Sprite-background collision occurred. Bit #2: 1 = Sprite-sprite collision occurred. Bit #3: 1 = Light pen signal arrived. Bit #7: 1 = An event (or more events), that may generate an interrupt, occurred and it has not been (not all of them have been) acknowledged yet.Write bits:
	Bit #0: 1 = Acknowledge raster interrupt. Bit #1: 1 = Acknowledge sprite-background collision interrupt. Bit #2: 1 = Acknowledge sprite-sprite collision interrupt. Bit #3: 1 = Acknowledge light pen interrupt.
	*/
	INTERRUPT_STATUS_REGISTER ($D019),
	/**
	Interrupt control register. Bits:
	Bit #0: 1 = Raster interrupt enabled. Bit #1: 1 = Sprite-background collision interrupt enabled. Bit #2: 1 = Sprite-sprite collision interrupt enabled. Bit #3: 1 = Light pen interrupt enabled.
	*/
	INTERRUPT_CONTROL_REGISTER ($D01A),
	/**
	Sprite priority register. Bits:
	Bit #x: 0 = Sprite #x is drawn in front of screen contents; 1 = Sprite #x is behind screen contents.
	*/
	SPRITE_PRIORITY_REGISTER ($D01B),
	/**
	Sprite multicolor mode register. Bits:
	Bit #x: 0 = Sprite #x is single color; 1 = Sprite #x is multicolor.
	*/
	SPRITE_MULTICOLOR_MODE_REGISTER ($D01C),
	/**
	Sprite double width register. Bits:
	Bit #x: 1 = Sprite #x is stretched to double width.
	*/
	SPRITE_DOUBLE_WIDTH_REGISTER ($D01D),
	/**
	Sprite-sprite collision register. Read bits:
	Bit #x: 1 = Sprite #x collided with another sprite.Write: Enable further detection of sprite-sprite collisions.
	*/
	SPRITE_SPRITE_COLLISION_REGISTER ($D01E),
	/**
	Sprite-background collision register. Read bits:
	Bit #x: 1 = Sprite #x collided with background.Write: Enable further detection of sprite-background collisions.
	*/
	SPRITE_BACKGROUND_COLLISION_REGISTER ($D01F),
	/**
	Border color (only bits #0-#3).
	*/
	BORDER_COLOR ($D020),
	/**
	Background color (only bits #0-#3).
	*/
	BACKGROUND_COLOR ($D021),
	/**
	Extra background color #1 (only bits #0-#3).
	*/
	EXTRA_BACKGROUND_COLOR_1 ($D022),
	/**
	Extra background color #2 (only bits #0-#3).
	*/
	EXTRA_BACKGROUND_COLOR_2 ($D023),
	/**
	Extra background color #3 (only bits #0-#3).
	*/
	EXTRA_BACKGROUND_COLOR_3 ($D024),
	/**
	Sprite extra color #1 (only bits #0-#3).
	*/
	SPRITE_EXTRA_COLOR_1 ($D025),
	/**
	Sprite extra color #2 (only bits #0-#3).
	*/
	SPRITE_EXTRA_COLOR_2 ($D026),
	/**
	Sprite #0 color (only bits #0-#3).
	*/
	SPRITE_0_COLOR ($D027),
	/**
	Sprite #1 color (only bits #0-#3).
	*/
	SPRITE_1_COLOR ($D028),
	/**
	Sprite #2 color (only bits #0-#3).
	*/
	SPRITE_2_COLOR ($D029),
	/**
	Sprite #3 color (only bits #0-#3).
	*/
	SPRITE_3_COLOR ($D02A),
	/**
	Sprite #4 color (only bits #0-#3).
	*/
	SPRITE_4_COLOR ($D02B),
	/**
	Sprite #5 color (only bits #0-#3).
	*/
	SPRITE_5_COLOR ($D02C),
	/**
	Sprite #6 color (only bits #0-#3).
	*/
	SPRITE_6_COLOR ($D02D),
	/**
	Sprite #7 color (only bits #0-#3).
	*/
	SPRITE_7_COLOR ($D02E),
	/**
	Unusable (17 bytes).
	*/

	/**
	VIC-II register images (repeated every $40, 64 bytes).
	*/
	VIC_II_REGISTER_IMAGES ($D040_$D3FF),
	/**

	*/

	/**
	Voice #1 frequency.
	Write-only.
	*/
	VOICE_1_FREQUENCY ($D400_$D401),
	/**
	Voice #1 pulse width.
	Write-only.
	*/
	VOICE_1_PULSE_WIDTH ($D402_$D403),
	/**
	Voice #1 control register. Bits:
	Bit #0: 0 = Voice off, Release cycle; 1 = Voice on, Attack-Decay-Sustain cycle. Bit #1: 1 = Synchronization enabled. Bit #2: 1 = Ring modulation enabled. Bit #3: 1 = Disable voice, reset noise generator. Bit #4: 1 = Triangle waveform enabled. Bit #5: 1 = Saw waveform enabled. Bit #6: 1 = Rectangle waveform enabled. Bit #7: 1 = Noise enabled.Write-only.
	*/
	VOICE_1_CONTROL_REGISTER ($D404),
	/**
	Voice #1 Attack and Decay length. Bits:
	Bits #0-#3: Decay length. Values: %0000, 0: 6 ms. %0001, 1: 24 ms. %0010, 2: 48 ms. %0011, 3: 72 ms. %0100, 4: 114 ms. %0101, 5: 168 ms. %0110, 6: 204 ms. %0111, 7: 240 ms. %1000, 8: 300 ms. %1001, 9: 750 ms. %1010, 10: 1.5 s. %1011, 11: 2.4 s. %1100, 12: 3 s. %1101, 13: 9 s. %1110, 14: 15 s. %1111, 15: 24 s. Bits #4-#7: Attack length. Values: %0000, 0: 2 ms. %0001, 1: 8 ms. %0010, 2: 16 ms. %0011, 3: 24 ms. %0100, 4: 38 ms. %0101, 5: 56 ms. %0110, 6: 68 ms. %0111, 7: 80 ms. %1000, 8: 100 ms. %1001, 9: 250 ms. %1010, 10: 500 ms. %1011, 11: 800 ms. %1100, 12: 1 s. %1101, 13: 3 s. %1110, 14: 5 s. %1111, 15: 8 s.Write-only.
	*/
	VOICE_1_ATTACK_AND_DECAY_LENGTH ($D405),
	/**
	Voice #1 Sustain volume and Release length. Bits:
	Bits #0-#3: Release length. Values: %0000, 0: 6 ms. %0001, 1: 24 ms. %0010, 2: 48 ms. %0011, 3: 72 ms. %0100, 4: 114 ms. %0101, 5: 168 ms. %0110, 6: 204 ms. %0111, 7: 240 ms. %1000, 8: 300 ms. %1001, 9: 750 ms. %1010, 10: 1.5 s. %1011, 11: 2.4 s. %1100, 12: 3 s. %1101, 13: 9 s. %1110, 14: 15 s. %1111, 15: 24 s. Bits #4-#7: Sustain volume.Write-only.
	*/
	VOICE_1_SUSTAIN_VOLUME_AND_RELEASE_LENGTH ($D406),
	/**
	Voice #2 frequency.
	Write-only.
	*/
	VOICE_2_FREQUENCY ($D407_$D408),
	/**
	Voice #2 pulse width.
	Write-only.
	*/
	VOICE_2_PULSE_WIDTH ($D409_$D40A),
	/**
	Voice #2 control register.
	Write-only.
	*/
	VOICE_2_CONTROL_REGISTER ($D40B),
	/**
	Voice #2 Attack and Decay length.
	Write-only.
	*/
	VOICE_2_ATTACK_AND_DECAY_LENGTH ($D40C),
	/**
	Voice #2 Sustain volume and Release length.
	Write-only.
	*/
	VOICE_2_SUSTAIN_VOLUME_AND_RELEASE_LENGTH ($D40D),
	/**
	Voice #3 frequency.
	Write-only.
	*/
	VOICE_3_FREQUENCY ($D40E_$D40F),
	/**
	Voice #3 pulse width.
	Write-only.
	*/
	VOICE_3_PULSE_WIDTH ($D410_$D411),
	/**
	Voice #3 control register.
	Write-only.
	*/
	VOICE_3_CONTROL_REGISTER ($D412),
	/**
	Voice #3 Attack and Decay length.
	Write-only.
	*/
	VOICE_3_ATTACK_AND_DECAY_LENGTH ($D413),
	/**
	Voice #3 Sustain volume and Release length.
	Write-only.
	*/
	VOICE_3_SUSTAIN_VOLUME_AND_RELEASE_LENGTH ($D414),
	/**
	Filter cut off frequency (bits #0-#2).
	Write-only.
	*/
	FILTER_CUT_OFF_FREQUENCY_0_2($D415),
	/**
	Filter cut off frequency (bits #3-#10).
	Write-only.
	*/
	FILTER_CUT_OFF_FREQUENCY_3_10($D416),
	/**
	Filter control. Bits:
	Bit #0: 1 = Voice #1 filtered. Bit #1: 1 = Voice #2 filtered. Bit #2: 1 = Voice #3 filtered. Bit #3: 1 = External voice filtered. Bits #4-#7: Filter resonance.Write-only.
	*/
	FILTER_CONTROL ($D417),
	/**
	Volume and filter modes. Bits:
	Bits #0-#3: Volume. Bit #4: 1 = Low pass filter enabled. Bit #5: 1 = Band pass filter enabled. Bit #6: 1 = High pass filter enabled. Bit #7: 1 = Voice #3 disabled.Write-only.
	*/
	VOLUME_AND_FILTER_MODES ($D418),
	/**
	X value of paddle selected at memory address $DD00. (Updates at every 512 system cycles.)
	Read-only.
	*/
	X_VALUE_OF_PADDLE_SELECTED_AT_MEMORY_ADDRESS_DD00 ($D419),
	/**
	Y value of paddle selected at memory address $DD00. (Updates at every 512 system cycles.)
	Read-only.
	*/
	Y_VALUE_OF_PADDLE_SELECTED_AT_MEMORY_ADDRESS_DD00 ($D41A),
	/**
	Voice #3 waveform output.
	Read-only.
	*/
	VOICE_3_WAVEFORM_OUTPUT ($D41B),
	/**
	Voice #3 ADSR output.
	Read-only.
	*/
	VOICE_3_ADSR_OUTPUT ($D41C),
	/**
	Unusable (3 bytes).
	*/

	/**
	SID register images (repeated every $20, 32 bytes).
	*/
	SID_REGISTER_IMAGES ($D420_$D7FF),
	/**

	*/

	/**
	Color RAM (1000 bytes, only bits #0-#3).
	*/
	COLOR_RAM ($D800_$DBE7),
	/**
	Unused (24 bytes, only bits #0-#3).
	*/

	/**

	*/

	/**
	Port A, keyboard matrix columns and joystick #2. Read bits:
	Bit #0: 0 = Port 2 joystick up pressed. Bit #1: 0 = Port 2 joystick down pressed. Bit #2: 0 = Port 2 joystick left pressed. Bit #3: 0 = Port 2 joystick right pressed. Bit #4: 0 = Port 2 joystick fire pressed.Write bits:
	Bit #x: 0 = Select keyboard matrix column #x. Bits #6-#7: Paddle selection; %01 = Paddle #1; %10 = Paddle #2.
	*/
	PORT_A_KEYBOARD_MATRIX_COLUMNS_AND_JOYSTICK_2 ($DC00),
	/**
	Port B, keyboard matrix rows and joystick #1. Bits:
	Bit #x: 0 = A key is currently being pressed in keyboard matrix row #x, in the column selected at memory address $DC00. Bit #0: 0 = Port 1 joystick up pressed. Bit #1: 0 = Port 1 joystick down pressed. Bit #2: 0 = Port 1 joystick left pressed. Bit #3: 0 = Port 1 joystick right pressed. Bit #4: 0 = Port 1 joystick fire pressed.
	*/
	PORT_B_KEYBOARD_MATRIX_ROWS_AND_JOYSTICK_1 ($DC01),
	/**
	Port A data direction register.
	Bit #x: 0 = Bit #x in port A can only be read; 1 = Bit #x in port A can be read and written.
	*/
	PORT_A_DATA_DIRECTION_REGISTER ($DC02),
	/**
	Port B data direction register.
	Bit #x: 0 = Bit #x in port B can only be read; 1 = Bit #x in port B can be read and written.
	*/
	PORT_B_DATA_DIRECTION_REGISTER ($DC03),
	/**
	Timer A. Read: Current timer value.
	Write: Set timer start value.
	*/
	TIMER_A ($DC04_$DC05),
	/**
	Timer B. Read: Current timer value.
	Write: Set timer start value.
	*/
	TIMER_B ($DC06_$DC07),
	/**
	Time of Day, tenth seconds (in BCD). Values: $00-$09. Read: Current TOD value.
	Write: Set TOD or alarm time.
	*/
	TIME_OF_DAY_TENTH_SECONDS ($DC08),
	/**
	Time of Day, seconds (in BCD). Values: $00-$59. Read: Current TOD value.
	Write: Set TOD or alarm time.
	*/
	TIME_OF_DAY_SECONDS ($DC09),
	/**
	Time of Day, minutes (in BCD). Values: $00-$59. Read: Current TOD value.
	Write: Set TOD or alarm time.
	*/
	TIME_OF_DAY_MINUTES ($DC0A),
	/**
	Time of Day, hours (in BCD). Read bits:
	Bits #0-#5: Hours. Bit #7: 0 = AM; 1 = PM.Write: Set TOD or alarm time.
	*/
	TIME_OF_DAY_HOURS ($DC0B),
	/**
	Serial shift register. (Bits are read and written upon every positive edge of the CNT pin.)
	*/
	SERIAL_SHIFT_REGISTER ($DC0C),
	/**
	Interrupt control and status register. Read bits:
	Bit #0: 1 = Timer A underflow occurred. Bit #1: 1 = Timer B underflow occurred. Bit #2: 1 = TOD is equal to alarm time. Bit #3: 1 = A complete byte has been received into or sent from serial shift register. Bit #4: Signal level on FLAG pin, datasette input. Bit #7: An interrupt has been generated.Write bits:
	Bit #0: 1 = Enable interrupts generated by timer A underflow. Bit #1: 1 = Enable interrupts generated by timer B underflow. Bit #2: 1 = Enable TOD alarm interrupt. Bit #3: 1 = Enable interrupts generated by a byte having been received/sent via serial shift register. Bit #4: 1 = Enable interrupts generated by positive edge on FLAG pin. Bit #7: Fill bit; bits #0-#6, that are set to 1, get their values from this bit; bits #0-#6, that are set to 0, are left unchanged.
	*/
	INTERRUPT_CONTROL_AND_STATUS_REGISTER ($DC0D),
	/**
	Timer A control register. Bits:
	Bit #0: 0 = Stop timer; 1 = Start timer. Bit #1: 1 = Indicate timer underflow on port B bit #6. Bit #2: 0 = Upon timer underflow, invert port B bit #6; 1 = upon timer underflow, generate a positive edge on port B bit #6 for 1 system cycle. Bit #3: 0 = Timer restarts upon underflow; 1 = Timer stops upon underflow. Bit #4: 1 = Load start value into timer. Bit #5: 0 = Timer counts system cycles; 1 = Timer counts positive edges on CNT pin. Bit #6: Serial shift register direction; 0 = Input, read; 1 = Output, write. Bit #7: TOD speed; 0 = 60 Hz; 1 = 50 Hz.
	*/
	TIMER_A_CONTROL_REGISTER ($DC0E),
	/**
	Timer B control register. Bits:
	Bit #0: 0 = Stop timer; 1 = Start timer. Bit #1: 1 = Indicate timer underflow on port B bit #7. Bit #2: 0 = Upon timer underflow, invert port B bit #7; 1 = upon timer underflow, generate a positive edge on port B bit #7 for 1 system cycle. Bit #3: 0 = Timer restarts upon underflow; 1 = Timer stops upon underflow. Bit #4: 1 = Load start value into timer. Bits #5-#6: %00 = Timer counts system cycles; %01 = Timer counts positive edges on CNT pin; %10 = Timer counts underflows of timer A; %11 = Timer counts underflows of timer A occurring along with a positive edge on CNT pin. Bit #7: 0 = Writing into TOD registers sets TOD; 1 = Writing into TOD registers sets alarm time.
	*/
	TIMER_B_CONTROL_REGISTER ($DC0F),
	/**
	CIA#1 register images (repeated every $10, 16 bytes).
	*/
	CIA_1_REGISTER_IMAGES ($DC10_$DCFF),
	/**

	*/

	/**
	Port A, serial bus access. Bits:
	Bits #0-#1: VIC bank. Values: %00, 0: Bank #3, $C000-$FFFF, 49152-65535. %01, 1: Bank #2, $8000-$BFFF, 32768-49151. %10, 2: Bank #1, $4000-$7FFF, 16384-32767. %11, 3: Bank #0, $0000-$3FFF, 0-16383. Bit #2: RS232 TXD line, output bit. Bit #3: Serial bus ATN OUT; 0 = High; 1 = Low. Bit #4: Serial bus CLOCK OUT; 0 = High; 1 = Low. Bit #5: Serial bus DATA OUT; 0 = High; 1 = Low. Bit #6: Serial bus CLOCK IN; 0 = Low; 1 = High. Bit #7: Serial bus DATA IN; 0 = Low; 1 = High.
	*/
	PORT_A_SERIAL_BUS_ACCESS ($DD00),
	/**
	Port B, RS232 access. Read bits:
	Bit #0: RS232 RXD line, input bit. Bit #3: RS232 RI line. Bit #4: RS232 DCD line. Bit #5: User port H pin. Bit #6: RS232 CTS line; 1 = Sender is ready to send. Bit #7: RS232 DSR line; 1 = Receiver is ready to receive.Write bits:
	Bit #1: RS232 RTS line. 1 = Sender is ready to send. Bit #2: RS232 DTR line. 1 = Receiver is ready to receive. Bit #3: RS232 RI line. Bit #4: RS232 DCD line. Bit #5: User port H pin.
	*/
	PORT_B_RS232_ACCESS ($DD01),
	/**
	Port A data direction register.
	Bit #x: 0 = Bit #x in port A can only be read; 1 = Bit #x in port A can be read and written.
	*/
	PORT_A_DATA_DIRECTION_REGISTER_CIA2($DD02),
	/**
	Port B data direction register.
	Bit #x: 0 = Bit #x in port B can only be read; 1 = Bit #x in port B can be read and written.
	*/
	PORT_B_DATA_DIRECTION_REGISTER_CIA2($DD03),
	/**
	Timer A. Read: Current timer value.
	Write: Set timer start value.
	*/
	TIMER_A_CIA2($DD04_$DD05),
	/**
	Timer B. Read: Current timer value.
	Write: Set timer start value.
	*/
	TIMER_B_CIA2($DD06_$DD07),
	/**
	Time of Day, tenth seconds (in BCD). Values: $00-$09. Read: Current TOD value.
	Write: Set TOD or alarm time.
	*/
	TIME_OF_DAY_TENTH_SECONDS_CIA2($DD08),
	/**
	Time of Day, seconds (in BCD). Values: $00-$59. Read: Current TOD value.
	Write: Set TOD or alarm time.
	*/
	TIME_OF_DAY_SECONDS_CIA2($DD09),
	/**
	Time of Day, minutes (in BCD). Values: $00-$59. Read: Current TOD value.
	Write: Set TOD or alarm time.
	*/
	TIME_OF_DAY_MINUTES_CIA2($DD0A),
	/**
	Time of Day, hours (in BCD). Read bits:
	Bits #0-#5: Hours. Bit #7: 0 = AM; 1 = PM.Write: Set TOD or alarm time.
	*/
	TIME_OF_DAY_HOURS_CIA2($DD0B),
	/**
	Serial shift register. (Bits are read and written upon every positive edge of the CNT pin.)
	*/
	SERIAL_SHIFT_REGISTER_CIA2($DD0C),
	/**
	Interrupt control and status register. Read bits:
	Bit #0: 1 = Timer A underflow occurred. Bit #1: 1 = Timer B underflow occurred. Bit #2: 1 = TOD is equal to alarm time. Bit #3: 1 = A complete byte has been received into or sent from serial shift register. Bit #4: Signal level on FLAG pin. Bit #7: A non-maskable interrupt has been generated.Write bits:
	Bit #0: 1 = Enable non-maskable interrupts generated by timer A underflow. Bit #1: 1 = Enable non-maskable interrupts generated by timer B underflow. Bit #2: 1 = Enable TOD alarm non-maskable interrupt. Bit #3: 1 = Enable non-maskable interrupts generated by a byte having been received/sent via serial shift register. Bit #4: 1 = Enable non-maskable interrupts generated by positive edge on FLAG pin. Bit #7: Fill bit; bits #0-#6, that are set to 1, get their values from this bit; bits #0-#6, that are set to 0, are left unchanged.
	*/
	INTERRUPT_CONTROL_AND_STATUS_REGISTER_CIA2($DD0D),
	/**
	Timer A control register. Bits:
	Bit #0: 0 = Stop timer; 1 = Start timer. Bit #1: 1 = Indicate timer underflow on port B bit #6. Bit #2: 0 = Upon timer underflow, invert port B bit #6; 1 = upon timer underflow, generate a positive edge on port B bit #6 for 1 system cycle. Bit #3: 0 = Timer restarts upon underflow; 1 = Timer stops upon underflow. Bit #4: 1 = Load start value into timer. Bit #5: 0 = Timer counts system cycles; 1 = Timer counts positive edges on CNT pin. Bit #6: Serial shift register direction; 0 = Input, read; 1 = Output, write. Bit #7: TOD speed; 0 = 60 Hz; 1 = 50 Hz.
	*/
	TIMER_A_CONTROL_REGISTER_CIA2($DD0E),
	/**
	Timer B control register. Bits:
	Bit #0: 0 = Stop timer; 1 = Start timer. Bit #1: 1 = Indicate timer underflow on port B bit #7. Bit #2: 0 = Upon timer underflow, invert port B bit #7; 1 = upon timer underflow, generate a positive edge on port B bit #7 for 1 system cycle. Bit #3: 0 = Timer restarts upon underflow; 1 = Timer stops upon underflow. Bit #4: 1 = Load start value into timer. Bits #5-#6: %00 = Timer counts system cycles; %01 = Timer counts positive edges on CNT pin; %10 = Timer counts underflows of timer A; %11 = Timer counts underflows of timer A occurring along with a positive edge on CNT pin. Bit #7: 0 = Writing into TOD registers sets TOD; 1 = Writing into TOD registers sets alarm time.
	*/
	TIMER_B_CONTROL_REGISTER_CIA2($DD0F),
	/**
	CIA#2 register images (repeated every $10, 16 bytes).
	*/
	CIA_2_REGISTER_IMAGES ($DD10_$DDFF),
	/**

	*/

	/**
	I/O Area #1, memory mapped registers or machine code routines of optional external devices (256 bytes). Layout and contents depend on the actual device.
	*/
	I_O_AREA_1_MEMORY_MAPPED_REGISTERS_OR_MACHINE_CODE_ROUTINES_OF_OPTIONAL_EXTERNAL_DEVICES ($DE00_$DEFF),
	/**

	*/

	/**
	I/O Area #2, memory mapped registers or machine code routines of optional external devices (256 bytes). Layout and contents depend on the actual device.
	*/
	I_O_AREA_2_MEMORY_MAPPED_REGISTERS_OR_MACHINE_CODE_ROUTINES_OF_OPTIONAL_EXTERNAL_DEVICES ($DF00_$DFFF),
	/**

	*/

	/**
	KERNAL ROM or RAM area (8192 bytes). depends on the value of bits #0-#2 of the processor port at memory address $0001:%x0x: RAM area. %x1x: KERNAL ROM.
	*/
	KERNAL_ROM_OR_RAM_AREA ($E000_$FFFF),
	/**

	*/

	/**
	Execution address of non-maskable interrupt service routine.
	Default: $FE43.
	*/
	EXECUTION_ADDRESS_OF_NON_MASKABLE_INTERRUPT_SERVICE_ROUTINE_FF($FFFA_$FFFB),
	/**
	Execution address of cold reset.
	Default: $FCE2.
	*/
	EXECUTION_ADDRESS_OF_COLD_RESET ($FFFC_$FFFD),
	/**
	Execution address of interrupt service routine.
	Default: $FF48.
	*/
	EXECUTION_ADDRESS_OF_INTERRUPT_SERVICE_ROUTINE_FF($FFFE_$FFFF),

	;
	MemoryAddress m;

	public static MemoryMap getByAddress(MemoryAddress ma) {
		return Arrays.stream(MemoryMap.values()).filter(p -> p.getMemory() == ma).findFirst().orElse(null);
	}

	MemoryMap(MemoryAddress m) {
		this.m = m;
	}

	public MemoryAddress getMemory() {
		return m;
	}
}
