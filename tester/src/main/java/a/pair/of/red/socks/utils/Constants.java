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

	public static final long[/*square*/][/*linetype*/][/*upper or lower*/] LINE_ATTACKS=
			{{{00L,0x101010101010100L},{0x0L,0xfeL}},{{00L,0x0202020202020200L},{0x0L,0xfdL}},{{00L,0x0404040404040400L},{0x0L,0xfbL}},{{00L,0x101010101010100L},{0x0L,0xf7L}},{{00L,0x101010101010100L},{0x0L,0xefL}},{{00L,0x101010101010100L},{0x0L,0xdfL}},{{00L,0x101010101010100L},{0x0L,0xbfL}},{{00L,0x101010101010100L},{0x0L,0x7fL}},
			{{0x01L,0x101010101010000L},{0x0L,0xfe00L}},
			{{0x0101L,0x101010101000000L},{0x0L,0xfe0000L}},
			{{0x010101L,0x101010100000000L},{0x0L,0xfe000000L}},
			{{0x01010101L,0x101010000000000L},{0x0L,0xfe00000000L}},
			{{0x0101010101L,0x101000000000000L},{0x0L,0xfe0000000000L}},
			{{0x010101010101L,0x100000000000000L},{0x0L,0xfe000000000000L}},
			{{0x01010101010101L,00L},{0x0L,0xfe00000000000000L}}};

	public static final long[][][] FILE_ATTACK = {
			{{0x01010101010101L,0L},				{0x02020202020202L,0L},					{0x04040404040404L,0L},					{0x08080808080808L,0L},					{0x10101010101010L,0L},					{0x20202020202020L,0L},					{0x40404040404040L,0L},					{0x80808080808080L,0L}},
			{{0x010101010101L,0x100000000000000L},	{0x020202020202L,0x200000000000000L},	{0x040404040404L,0x400000000000000L},	{0x080808080808L,0x800000000000000L},	{0x101010101010L,0x1000000000000000L},	{0x202020202020L,0x2000000000000000L},	{0x404040404040L,0x4000000000000000L},	{0x808080808080L,0x8000000000000000L}},
			{{0x0101010101L,0x101000000000000L},	{0x0202020202L,0x202000000000000L},		{0x0404040404L,0x404000000000000L},		{0x0808080808L,0x808000000000000L},		{0x1010101010L,0x1010000000000000L},	{0x2020202020L,0x2020000000000000L},	{0x4040404040L,0x4040000000000000L},	{0x8080808080L,0x8080000000000000L}},
			{{0x01010101L,0x101010000000000L},		{0x02020202L,0x202020000000000L},		{0x04040404L,0x404040000000000L},		{0x08080808L,0x808080000000000L},		{0x10101010L,0x1010100000000000L},		{0x20202020L,0x2020200000000000L},		{0x40404040L,0x4040400000000000L},		{0x80808080L,0x8080800000000000L}},
			{{0x010101L,0x101010100000000L},		{0x020202L,0x202020200000000L},			{0x040404L,0x404040400000000L},			{0x080808L,0x808080800000000L},			{0x101010L,0x1010101000000000L},		{0x202020L,0x2020202000000000L},		{0x404040L,0x4040404000000000L},		{0x808080L,0x8080808000000000L}},
			{{0x0101L,0x101010101000000L},			{0x0202L,0x202020202000000L},			{0x0404L,0x404040404000000L},			{0x0808L,0x808080808000000L},			{0x1010L,0x1010101010000000L},			{0x2020L,0x2020202020000000L},			{0x4040L,0x4040404040000000L},			{0x8080L,0x8080808080000000L}},
			{{0x01L,0x101010101010000L},			{0x02L,0x202020202020000L},				{0x04L,0x404040404040000L},				{0x08L,0x808080808080000L},				{0x10L,0x1010101010100000L},			{0x20L,0x2020202020200000L},			{0x40L,0x4040404040400000L},			{0x80L,0x8080808080800000L}},
			{{0x0L,0x101010101010100L},				{0x0L,0x202020202020200L},				{0x0L,0x404040404040400L},				{0x0L,0x808080808080800L},				{0x0L,0x1010101010101000L},				{0x0L,0x2020202020202000L},				{0x0L,0x4040404040404000L},				{0x0L,0x8080808080808000L}}};
}
