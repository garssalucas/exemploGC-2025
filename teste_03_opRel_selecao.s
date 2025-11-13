.text

#	 nome COMPLETO e matricula dos componentes do grupo...
#

.GLOBL _start


_start:
	PUSHL $30
	POPL %EDX
	MOVL %EDX, _p_idade
	PUSHL %EDX
	POPL %EDX
	PUSHL $80
	POPL %EDX
	MOVL %EDX, _p_peso
	PUSHL %EDX
	POPL %EDX
	MOVL $_str_0Len, %EDX
	MOVL $_str_0, %ECX
	CALL _writeLit
	PUSHL _p_idade
	POPL %EAX
	CALL _write
	CALL _writeln
	MOVL $_str_1Len, %EDX
	MOVL $_str_1, %ECX
	CALL _writeLit
	PUSHL _p_peso
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
	POPL %EDX
rot_04:
	PUSHL _i
	PUSHL $10
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EDX
	CMPL $0, %EAX
	JE rot_02
	JNE rot_03
rot_01:
	PUSHL _i
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _i
	POPL %EDX
	PUSHL %EDX
	JMP rot_04
rot_03:
	PUSHL _i
	PUSHL _i
	PUSHL $2
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	POPL %EAX
	IMULL $4, %EAX
	MOVL %EDX, _notas(,%EAX)
	PUSHL %EDX
	POPL %EDX
	JMP rot_01
rot_02:
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
	POPL %EDX
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _soma
	PUSHL %EDX
	POPL %EDX
rot_05:
	PUSHL _i
	PUSHL $10
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   # desvia se falso...
	CMPL $0, %EAX
	JE rot_06
	PUSHL _soma
	PUSHL _i
	POPL %EAX
	IMULL $4, %EAX
	PUSHL _notas(,%EAX)
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _soma
	PUSHL %EDX
	POPL %EDX
	PUSHL _i
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _i
	POPL %EDX
	PUSHL %EDX
	POPL %EDX
		# terminou o bloco...
	JMP rot_05   # terminou cmd na linha de cima
rot_06:
	MOVL $_str_2Len, %EDX
	MOVL $_str_2, %ECX
	CALL _writeLit
	PUSHL _soma
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL _p_idade
	PUSHL _p_peso
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETG  %AL
	PUSHL %EAX
	PUSHL _p_idade
	PUSHL _p_peso
	POPL %EAX   # exp3 (falso)
	POPL %EBX   # exp2 (verdadeiro)
	POPL %EDX   # cond
	CMPL $0, %EDX
	JE  rot_07
	PUSHL %EBX
	JMP rot_08
rot_07:
	PUSHL %EAX
rot_08:
	POPL %EDX
	MOVL %EDX, _maior
	PUSHL %EDX
	POPL %EDX
	MOVL $_str_3Len, %EDX
	MOVL $_str_3, %ECX
	CALL _writeLit
	PUSHL _maior
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
	POPL %EDX
rot_09:
	PUSHL _i
	PUSHL $5
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETE  %AL
	PUSHL %EAX
	POPL %EAX
	CMPL $0, %EAX
	JE rot_11
	PUSHL _i
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _i
	POPL %EDX
	PUSHL %EDX
	POPL %EDX
	JMP rot_09
		# terminou o bloco...
	JMP rot_12
rot_11:
rot_12:
	MOVL $_str_4Len, %EDX
	MOVL $_str_4, %ECX
	CALL _writeLit
	PUSHL _i
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL _i
	PUSHL $8
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETE  %AL
	PUSHL %EAX
	POPL %EAX
	CMPL $0, %EAX
	JE rot_13
	JMP rot_10
	JMP rot_14
rot_13:
rot_14:
	PUSHL _i
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _i
	POPL %EDX
	PUSHL %EDX
	POPL %EDX
		# terminou o bloco...
	PUSHL _i
	PUSHL $10
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX
	CMPL $1, %EAX
	JE rot_09
rot_10:
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _x
	PUSHL %EDX
	POPL %EDX
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
	POPL %EDX
rot_18:
	PUSHL _i
	PUSHL $5
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EDX
	CMPL $0, %EAX
	JE rot_16
	JNE rot_17
rot_15:
	PUSHL _i
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _i
	POPL %EDX
	PUSHL %EDX
	JMP rot_18
rot_17:
	PUSHL _i
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _i
	POPL %EDX
	PUSHL %EDX
	POPL %EDX
	PUSHL _x
	PUSHL %EDX
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _x
	PUSHL _x
	POPL %EDX
	MOVL $_str_5Len, %EDX
	MOVL $_str_5, %ECX
	CALL _writeLit
	PUSHL _x
	POPL %EAX
	CALL _write
	CALL _writeln
		# terminou o bloco...
	JMP rot_15
rot_16:
	MOVL $_str_6Len, %EDX
	MOVL $_str_6, %ECX
	CALL _writeLit
	PUSHL _x
	POPL %EAX
	CALL _write
	CALL _writeln



#
# devolve o controle para o SO (final da main)
#
	mov $0, %ebx
	mov $1, %eax
	int $0x80


#
# Funcoes da biblioteca (IO)
#


_writeln:
	MOVL $__fim_msg, %ECX
	DECL %ECX
	MOVB $10, (%ECX)
	MOVL $1, %EDX
	JMP _writeLit
_write:
	MOVL $__fim_msg, %ECX
	MOVL $0, %EBX
	CMPL $0, %EAX
	JGE _write3
	NEGL %EAX
	MOVL $1, %EBX
_write3:
	PUSHL %EBX
	MOVL $10, %EBX
_divide:
	MOVL $0, %EDX
	IDIVL %EBX
	DECL %ECX
	ADD $48, %DL
	MOVB %DL, (%ECX)
	CMPL $0, %EAX
	JNE _divide
	POPL %EBX
	CMPL $0, %EBX
	JE _print
	DECL %ECX
	MOVB $'-', (%ECX)
_print:
	MOVL $__fim_msg, %EDX
	SUBL %ECX, %EDX
_writeLit:
	MOVL $1, %EBX
	MOVL $4, %EAX
	int $0x80
	RET
_read:
	MOVL $15, %EDX
	MOVL $__msg, %ECX
	MOVL $0, %EBX
	MOVL $3, %EAX
	int $0x80
	MOVL $0, %EAX
	MOVL $0, %EBX
	MOVL $0, %EDX
	MOVL $__msg, %ECX
	CMPB $'-', (%ECX)
	JNE _reading
	INCL %ECX
	INC %BL
_reading:
	MOVB (%ECX), %DL
	CMP $10, %DL
	JE _fimread
	SUB $48, %DL
	IMULL $10, %EAX
	ADDL %EDX, %EAX
	INCL %ECX
	JMP _reading
_fimread:
	CMPB $1, %BL
	JNE _fimread2
	NEGL %EAX
_fimread2:
	RET



#
# area de dados
#
.data
#
# variaveis globais
#
_notas:	.zero 40
_i:	.zero 4
_soma:	.zero 4
_p_idade:	.zero 4
_p_peso:	.zero 4
_maior:	.zero 4
_x:	.zero 4

#
# area de literais
#
__msg:
	.zero 30
__fim_msg:
	.byte 0


_str_0:
	 .ascii "Idade: "
_str_0Len = . - _str_0
_str_1:
	 .ascii "Peso: "
_str_1Len = . - _str_1
_str_2:
	 .ascii "Soma = "
_str_2Len = . - _str_2
_str_3:
	 .ascii "Maior idade/peso = "
_str_3Len = . - _str_3
_str_4:
	 .ascii "i = "
_str_4Len = . - _str_4
_str_5:
	 .ascii "Parcial de x = "
_str_5Len = . - _str_5
_str_6:
	 .ascii "Final x = "
_str_6Len = . - _str_6
