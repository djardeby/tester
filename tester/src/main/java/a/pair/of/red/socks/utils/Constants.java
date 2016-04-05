package a.pair.of.red.socks.utils;

public class Constants {
	public static final long RANK_1 = -72057594037927936L;
	public static final long RANK_2 = 71776119061217280L;
	public static final long RANK_3 = 280375465082880L;
	public static final long RANK_4 = 1095216660480L;
	public static final long RANK_5 = 4278190080L;
	public static final long RANK_6 = 16711680L;
	public static final long RANK_7 = 65280L;
	public static final long RANK_8 = 255L;
	public static final long FILE_A = 72340172838076673L;
	public static final long FILE_B = 144680345676153346L;
	public static final long FILE_C = 289360691352306692L;
	public static final long FILE_D = 578721382704613384L;
	public static final long FILE_E = 1157442765409226768L;
	public static final long FILE_F = 2314885530818453536L;
	public static final long FILE_G = 4629771061636907072L;
	public static final long FILE_H = -9187201950435737472L;
	public static final long FILE_GH = FILE_G | FILE_H;
	public static final long FILE_AB = FILE_A | FILE_B;
	public static final long RANK_78 = RANK_7 | RANK_8;
	public static final long RANK_12 = RANK_1 | RANK_2;
	public static final byte UPPER =0;
	public static final byte LOWER =1;
// http://cinnamonchess.altervista.org/bitboard_calculator/Calc.html

	public static final long[/*square*/][/*linetype*/][/*upper or lower*/] LINE_ATTACKS={
			{{0x00000000000000L,0x101010101010100L},{0x0L,0x00000000000000feL},{0x0L,0x8040201008040200L},{0L,0L}},	{{0x00000000000000L,0x0202020202020200L},{0x000000000000001L,0x00000000000000fcL},{0x0080402010080400L,0x0000000000000L},{0L,0L}},								{{00L,0x0404040404040400L},				{0x3L,0xf8L},{0L,0L},{0L,0L},{0L,0L},{0L,0L}},								{{00L,0x0808080808080800L},				{0x7L,0xf0L},{0L,0L},{0L,0L}},								{{00L,0x1010101010101000L},				{0xfL,0xe0L},{0L,0L},{0L,0L}},								{{00L,0x2020202020202000L},				{0x1fL,0xc0L},{0L,0L},{0L,0L}},								{{00L,0x4040404040404000L},					{0x3fL,0x80L},{0L,0L},{0L,0L}},								{{00L,0x8080808080808000L},				{0x7fL,0x00L},{0L,0L},{0L,0L}},
			{{0x00000000000001L,0x101010101010000L},{0x0L,0x000000000000fe00L},{0x0L,0x4020100804020000L},{0L,0L}},	{{0x00000000000002L,0x0202020202020000L},{0x000000000000100L,0x000000000000fc00L},{0x8040201008040000L,0x0000000000001L},{0L,0L}},							{{0x04L,0x0404040404040000L},			{0x300L,0xfb00L},{0L,0L},{0L,0L}},							{{0x08L,0x0808080808080000L},			{0x700L,0xf000L},{0L,0L},{0L,0L}},							{{0x10L,0x1010101010100000L},			{0xf00L,0xe000L},{0L,0L},{0L,0L}},							{{0x20L,0x2020202020200000L},			{0x1f00L,0xc000L},{0L,0L},{0L,0L}},							{{0x40L,0x4040404040400000L},				{0x3f00L,0x8000L},{0L,0L},{0L,0L}},							{{0x80L,0x8080808080800000L},			{0x7f00L,0x00L},{0L,0L},{0L,0L}},
			{{0x00000000000101L,0x101010101000000L},{0x0L,0x0000000000fe0000L},{0x0L,0x2010080402000000L},{0L,0L}},	{{0x00000000000202L,0x0202020202000000L},{0x000000000010000L,0x0000000000fc0000L},{0x4020100804000000L,0x0000000000100L},{0L,0L}},						{{0x0404L,0x0404040404000000L},			{0x30000L,0xfb0000L},{0L,0L},{0L,0L}},						{{0x0808L,0x0808080808000000L},			{0x70000L,0xf00000L},{0L,0L},{0L,0L}},						{{0x1010L,0x1010101010000000L},			{0xf0000L,0xe00000L},{0L,0L},{0L,0L}},						{{0x2020L,0x2020202020000000L},			{0x1f0000L,0xc00000L},{0L,0L},{0L,0L}},						{{0x4040L,0x4040404040000000L},				{0x3f0000L,0x800000L},{0L,0L},{0L,0L}},						{{0x8080L,0x8080808080000000L},			{0x7f0000L,0x0000L},{0L,0L},{0L,0L}},
			{{0x00000000010101L,0x101010100000000L},{0x0L,0x00000000fe000000L},{0x0L,0x1008040200000000L},{0L,0L}},	{{0x00000000020202L,0x0202020200000000L},{0x000000001000000L,0x00000000fc000000L},{0x2010080400000000L,0x0000000010000L},{0L,0L}},					{{0x040404L,0x0404040400000000L},		{0x3000000L,0xfb000000L},{0L,0L},{0L,0L}},					{{0x080808L,0x0808080800000000L},		{0x7000000L,0xf0000000L},{0L,0L},{0L,0L}},					{{0x101010L,0x1010101000000000L},		{0xf000000L,0xe0000000L},{0L,0L},{0L,0L}},					{{0x202020L,0x2020202000000000L},		{0x1f000000L,0xc0000000L},{0L,0L},{0L,0L}},					{{0x404040L,0x4040404000000000L},			{0x3f000000L,0x80000000L},{0L,0L},{0L,0L}},					{{0x808080L,0x8080808000000000L},		{0x7f000000L,0x000000L},{0L,0L},{0L,0L}},
			{{0x00000001010101L,0x101010000000000L},{0x0L,0x000000fe00000000L},{0x0L,0x0804020000000000L},{0L,0L}},	{{0x00000002020202L,0x0202020000000000L},{0x000000100000000L,0x000000fc00000000L},{0x1008040000000000L,0x0000001000000L},{0L,0L}},				{{0x04040404L,0x0404040000000000L},		{0x300000000L,0xfb00000000L},{0L,0L},{0L,0L}},				{{0x08080808L,0x0808080000000000L},		{0x700000000L,0xf000000000L},{0L,0L},{0L,0L}},				{{0x10101010L,0x1010100000000000L},		{0xf00000000L,0xe000000000L},{0L,0L},{0L,0L}},				{{0x20202020L,0x2020200000000000L},		{0x1f00000000L,0xc000000000L},{0L,0L},{0L,0L}},				{{0x40404040L,0x4040400000000000L},			{0x3f00000000L,0x8000000000L},{0L,0L},{0L,0L}},				{{0x80808080L,0x8080800000000000L},		{0x7f00000000L,0x00000000L},{0L,0L},{0L,0L}},
			{{0x00000101010101L,0x101000000000000L},{0x0L,0x0000fe0000000000L},{0x0L,0x0402000000000000L},{0L,0L}},	{{0x00000202020202L,0x0202000000000000L},{0x000010000000000L,0x0000fc0000000000L},{0x0804000000000000L,0x0000100000000L},{0L,0L}},			{{0x0404040404L,0x0404000000000000L},	{0x30000000000L,0xfb0000000000L},{0L,0L},{0L,0L}},			{{0x0808080808L,0x0808000000000000L},	{0x70000000000L,0xf00000000000L},{0L,0L},{0L,0L}},			{{0x1010101010L,0x1010000000000000L},	{0xf0000000000L,0xe00000000000L},{0L,0L},{0L,0L}},			{{0x2020202020L,0x2020000000000000L},	{0x1f0000000000L,0xc00000000000L},{0L,0L},{0L,0L}},			{{0x4040404040L,0x4040000000000000L},		{0x3f0000000000L,0x800000000000L},{0L,0L},{0L,0L}},			{{0x8080808080L,0x8080000000000000L},	{0x7f0000000000L,0x0000000000L},{0L,0L},{0L,0L}},
			{{0x00010101010101L,0x100000000000000L},{0x0L,0x00fe000000000000L},{0x0L,0x0200000000000000L},{0L,0L}},	{{0x00020202020202L,0x0200000000000000L},{0x001000000000000L,0x00fc000000000000L},{0x0400000000000000L,0x0010000000000L},{0L,0L}},		{{0x040404040404L,0x0400000000000000L},	{0x3000000000000L,0xfb000000000000L},{0L,0L},{0L,0L}},		{{0x080808080808L,0x0800000000000000L},	{0x7000000000000L,0xf0000000000000L},{0L,0L},{0L,0L}},		{{0x101010101010L,0x1000000000000000L},	{0xf000000000000L,0xe0000000000000L},{0L,0L},{0L,0L}},		{{0x202020202020L,0x2000000000000000L},	{0x1f000000000000L,0xc0000000000000L},{0L,0L},{0L,0L}},		{{0x404040404040L,0x4000000000000000L},		{0x3f000000000000L,0x80000000000000L},{0L,0L},{0L,0L}},		{{0x808080808080L,0x8000000000000000L},	{0x7f000000000000L,0x000000000000L},{0L,0L},{0L,0L}},
			{{0x01010101010101L,0x000000000000000L},{0x0L,0xfe00000000000000L},{0x0L,0x0000000000000000L},{0L,0L}},	{{0x02020202020202L,0x0000000000000000L},{0x100000000000000L,0xfc00000000000000L},{0x0000000000000000L,0x1000000000000L},{0L,0L}},	{{0x04040404040404L,00L},				{0x300000000000000L,0xfb00000000000000L},{0L,0L},{0L,0L}},	{{0x08080808080808L,00L},				{0x700000000000000L,0xf000000000000000L},{0L,0L},{0L,0L}},	{{0x10101010101010L,00L},				{0xf00000000000000L,0xe000000000000000L},{0L,0L},{0L,0L}},	{{0x20202020202020L,00L},				{0x1f00000000000000L,0xc000000000000000L},{0L,0L},{0L,0L}},	{{0x40404040404040L,00L},					{0x3f00000000000000L,0x8000000000000000L},{0L,0L},{0L,0L}},	{{0x80808080808080L,00L},				{0x7f00000000000000L,0x00L},{0L,0L},{0L,0L}}};

}
