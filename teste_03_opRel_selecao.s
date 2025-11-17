.text

#	 nome COMPLETO e matricula dos componentes do grupo...
#

.GLOBL _start


_start:
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
	POPL %EDX
rot_01:
	PUSHL _i
	PUSHL $3
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   # desvia se falso...
	CMPL $0, %EAX
	JE rot_02
	PUSHL _i
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	POPL %EAX
	IMULL $4, %EAX
	MOVL %EDX, _a_notas(,%EAX)
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
	JMP rot_01   # terminou cmd na linha de cima
rot_02:
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _soma
	PUSHL %EDX
	POPL %EDX
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
	POPL %EDX
rot_03:
	PUSHL _i
	PUSHL $3
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   # desvia se falso...
	CMPL $0, %EAX
	JE rot_04
	PUSHL _soma
	PUSHL _i
	POPL %EAX
	IMULL $4, %EAX
	PUSHL _a_notas(,%EAX)
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
	JMP rot_03   # terminou cmd na linha de cima
rot_04:
	MOVL $_str_0Len, %EDX
	MOVL $_str_0, %ECX
	CALL _writeLit
	PUSHL _soma
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
	POPL %EDX
rot_05:
	PUSHL _i
	PUSHL $2
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   # desvia se falso...
	CMPL $0, %EAX
	JE rot_06
	PUSHL _i
	PUSHL _i
	POPL %EDX
	POPL %EAX
	IMULL $4, %EAX
	MOVL %EDX, _t_id(,%EAX)
	PUSHL %EDX
	POPL %EDX
	PUSHL _i
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	PUSHL $10
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	POPL %EAX
	IMULL $4, %EAX
	MOVL %EDX, _t_pontos(,%EAX)
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
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _soma
	PUSHL %EDX
	POPL %EDX
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
	POPL %EDX
rot_07:
	PUSHL _i
	PUSHL $2
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   # desvia se falso...
	CMPL $0, %EAX
	JE rot_08
	PUSHL _soma
	PUSHL _i
	POPL %EAX
	IMULL $4, %EAX
	PUSHL _t_pontos(,%EAX)
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
	JMP rot_07   # terminou cmd na linha de cima
rot_08:
	MOVL $_str_1Len, %EDX
	MOVL $_str_1, %ECX
	CALL _writeLit
	PUSHL _soma
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
_i:	.zero 4
_soma:	.zero 4
_a_matricula:	.zero 4
_a_notas:	.zero 12
_t_id:	.zero 8
_t_pontos:	.zero 8

#
# area de literais
#
__msg:
	.zero 30
__fim_msg:
	.byte 0


_str_0:
	 .ascii "Soma notas de a = "
_str_0Len = . - _str_0
_str_1:
	 .ascii "Soma pontos dos times = "
_str_1Len = . - _str_1
