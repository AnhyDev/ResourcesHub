/*     */ package okhttp3.internal.http2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.ArraysKt;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.BufferedSink;
/*     */ import okio.BufferedSource;
/*     */ import okio.ByteString;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000F\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\025\n\000\n\002\020\022\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\020\b\n\002\b\004\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\bÆ\002\030\0002\0020\001:\001\032B\007\b\002¢\006\002\020\002J \020\t\032\0020\n2\006\020\013\032\0020\f2\006\020\r\032\0020\f2\006\020\016\032\0020\fH\002J\036\020\017\032\0020\n2\006\020\020\032\0020\0212\006\020\022\032\0020\0232\006\020\024\032\0020\025J\026\020\026\032\0020\n2\006\020\020\032\0020\0272\006\020\024\032\0020\025J\016\020\030\032\0020\f2\006\020\031\032\0020\027R\016\020\003\032\0020\004X\004¢\006\002\n\000R\016\020\005\032\0020\006X\004¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000¨\006\033"}, d2 = {"Lokhttp3/internal/http2/Huffman;", "", "()V", "CODES", "", "CODE_BIT_COUNTS", "", "root", "Lokhttp3/internal/http2/Huffman$Node;", "addCode", "", "symbol", "", "code", "codeBitCount", "decode", "source", "Lokio/BufferedSource;", "byteCount", "", "sink", "Lokio/BufferedSink;", "encode", "Lokio/ByteString;", "encodedLength", "bytes", "Node", "okhttp"})
/*     */ public final class Huffman
/*     */ {
/*     */   static {
/*  34 */     Huffman huffman = new Huffman();
/*     */   }
/*     */ 
/*     */   
/*  38 */   private static final int[] CODES = new int[] { 8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 
/*  39 */       268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 
/*  40 */       268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 
/*  41 */       268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 
/*  42 */       268435451, 20, 1016, 1017, 4090, 8185, 21, 248, 2042, 1018, 1019, 249, 
/*  43 */       2043, 250, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 
/*  44 */       92, 251, 32764, 32, 4091, 1020, 8186, 33, 93, 94, 95, 96, 97, 
/*  45 */       98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 
/*  46 */       112, 113, 114, 252, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 
/*  47 */       35, 4, 36, 5, 37, 38, 39, 6, 116, 117, 40, 41, 42, 7, 43, 
/*  48 */       118, 44, 8, 9, 45, 119, 120, 121, 122, 123, 32766, 2044, 16381, 8189, 
/*  49 */       268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 
/*  50 */       4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 
/*  51 */       16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 
/*  52 */       8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 
/*  53 */       4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 
/*  54 */       4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 
/*  55 */       2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 
/*  56 */       4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 
/*  57 */       1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 
/*  58 */       67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 
/*  59 */       67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 
/*  60 */       67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 
/*  61 */       1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 
/*  62 */       33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 
/*  63 */       67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 
/*  64 */       134217708, 134217709, 134217710, 134217711, 134217712, 67108846 };
/*     */ 
/*     */   
/*  67 */   private static final byte[] CODE_BIT_COUNTS = new byte[] { 13, 23, 28, 28, 28, 28, 28, 28, 28, 24, 30, 28, 28, 30, 28, 28, 28, 28, 28, 28, 
/*  68 */       28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 6, 10, 10, 12, 13, 6, 8, 11, 10, 10, 8, 
/*  69 */       11, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, 15, 6, 12, 10, 13, 6, 7, 7, 7, 7, 7, 
/*  70 */       7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 13, 19, 13, 14, 6, 15, 5, 6, 
/*  71 */       5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, 15, 11, 14, 13, 
/*  72 */       28, 20, 22, 20, 20, 22, 22, 22, 23, 22, 23, 23, 23, 23, 23, 24, 23, 24, 24, 22, 23, 24, 
/*  73 */       23, 23, 23, 23, 21, 22, 23, 22, 23, 23, 24, 22, 21, 20, 22, 22, 23, 23, 21, 23, 22, 22, 
/*  74 */       24, 21, 22, 23, 23, 21, 21, 22, 21, 23, 22, 23, 23, 20, 22, 22, 22, 23, 22, 22, 23, 26, 
/*  75 */       26, 20, 19, 22, 23, 22, 25, 26, 26, 26, 27, 27, 26, 24, 25, 19, 21, 26, 27, 27, 26, 27, 
/*  76 */       24, 21, 21, 26, 26, 28, 27, 27, 27, 20, 24, 20, 21, 22, 21, 21, 23, 22, 22, 25, 25, 24, 
/*  77 */       24, 26, 23, 26, 27, 26, 26, 27, 27, 27, 27, 27, 28, 27, 27, 27, 27, 27, 26 };
/*     */   
/*  79 */   private static final Node root = new Node(); public static final Huffman INSTANCE; static { byte b;
/*     */     int i;
/*  81 */     for (b = 0, i = CODE_BIT_COUNTS.length; b < i; b++) {
/*  82 */       huffman.addCode(b, CODES[b], CODE_BIT_COUNTS[b]);
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void encode(@NotNull ByteString source, @NotNull BufferedSink sink) throws IOException {
/*  88 */     Intrinsics.checkNotNullParameter(source, "source"); Intrinsics.checkNotNullParameter(sink, "sink"); long accumulator = 0L;
/*  89 */     int accumulatorBitCount = 0; byte b;
/*     */     int i;
/*  91 */     for (b = 0, i = source.size(); b < i; b++) {
/*  92 */       int symbol = Util.and(source.getByte(b), 255);
/*  93 */       int code = CODES[symbol];
/*  94 */       int codeBitCount = CODE_BIT_COUNTS[symbol];
/*     */       
/*  96 */       accumulator = accumulator << codeBitCount | code;
/*  97 */       accumulatorBitCount += codeBitCount;
/*     */       
/*  99 */       while (accumulatorBitCount >= 8) {
/* 100 */         accumulatorBitCount -= 8;
/* 101 */         sink.writeByte((int)(accumulator >> accumulatorBitCount));
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     if (accumulatorBitCount > 0) {
/* 106 */       accumulator <<= 8 - accumulatorBitCount;
/* 107 */       accumulator |= 255L >>> accumulatorBitCount;
/* 108 */       sink.writeByte((int)accumulator);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final int encodedLength(@NotNull ByteString bytes) {
/* 113 */     Intrinsics.checkNotNullParameter(bytes, "bytes"); long bitCount = 0L; byte b;
/*     */     int i;
/* 115 */     for (b = 0, i = bytes.size(); b < i; b++) {
/* 116 */       int byteIn = Util.and(bytes.getByte(b), 255);
/* 117 */       bitCount += CODE_BIT_COUNTS[byteIn];
/*     */     } 
/*     */     
/* 120 */     return (int)(bitCount + 7L >> 3L);
/*     */   }
/*     */   
/*     */   public final void decode(@NotNull BufferedSource source, long byteCount, @NotNull BufferedSink sink) {
/* 124 */     Intrinsics.checkNotNullParameter(source, "source"); Intrinsics.checkNotNullParameter(sink, "sink"); Node node = root;
/* 125 */     int accumulator = 0;
/* 126 */     int accumulatorBitCount = 0;
/* 127 */     for (long l1 = 0L, l2 = byteCount; l1 < l2; i = l1 + 1L) {
/* 128 */       long i; int byteIn = Util.and(source.readByte(), 255);
/* 129 */       accumulator = accumulator << 8 | byteIn;
/* 130 */       accumulatorBitCount += 8;
/* 131 */       while (accumulatorBitCount >= 8) {
/* 132 */         int childIndex = accumulator >>> accumulatorBitCount - 8 & 0xFF;
/* 133 */         Intrinsics.checkNotNull(node.getChildren()); Intrinsics.checkNotNull(node.getChildren()[childIndex]); node = node.getChildren()[childIndex];
/* 134 */         if (node.getChildren() == null) {
/*     */           
/* 136 */           sink.writeByte(node.getSymbol());
/* 137 */           accumulatorBitCount -= node.getTerminalBitCount();
/* 138 */           node = root;
/*     */           continue;
/*     */         } 
/* 141 */         accumulatorBitCount -= 8;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 146 */     while (accumulatorBitCount > 0) {
/* 147 */       int childIndex = accumulator << 8 - accumulatorBitCount & 0xFF;
/* 148 */       Intrinsics.checkNotNull(node.getChildren()); Intrinsics.checkNotNull(node.getChildren()[childIndex]); node = node.getChildren()[childIndex];
/* 149 */       if (node.getChildren() != null || node.getTerminalBitCount() > accumulatorBitCount) {
/*     */         break;
/*     */       }
/* 152 */       sink.writeByte(node.getSymbol());
/* 153 */       accumulatorBitCount -= node.getTerminalBitCount();
/* 154 */       node = root;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void addCode(int symbol, int code, int codeBitCount) {
/* 159 */     Node terminal = new Node(symbol, codeBitCount);
/*     */     
/* 161 */     int accumulatorBitCount = codeBitCount;
/* 162 */     Node node = root;
/* 163 */     while (accumulatorBitCount > 8) {
/* 164 */       accumulatorBitCount -= 8;
/* 165 */       int childIndex = code >>> accumulatorBitCount & 0xFF;
/* 166 */       Intrinsics.checkNotNull(node.getChildren()); Node[] children = node.getChildren();
/* 167 */       Node child = children[childIndex];
/* 168 */       if (child == null) {
/* 169 */         child = new Node();
/* 170 */         children[childIndex] = child;
/*     */       } 
/* 172 */       node = child;
/*     */     } 
/*     */     
/* 175 */     int shift = 8 - accumulatorBitCount;
/* 176 */     int start = code << shift & 0xFF;
/* 177 */     int end = 1 << shift;
/* 178 */     Intrinsics.checkNotNull(node.getChildren()); ArraysKt.fill((Object[])node.getChildren(), terminal, start, start + end);
/*     */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\003\n\002\020\021\n\002\b\b\b\002\030\0002\0020\001B\007\b\026¢\006\002\020\002B\027\b\026\022\006\020\003\032\0020\004\022\006\020\005\032\0020\004¢\006\002\020\006R\035\020\007\032\f\022\006\022\004\030\0010\000\030\0010\b¢\006\n\n\002\020\013\032\004\b\t\020\nR\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\f\020\rR\021\020\016\032\0020\004¢\006\b\n\000\032\004\b\017\020\r¨\006\020"}, d2 = {"Lokhttp3/internal/http2/Huffman$Node;", "", "()V", "symbol", "", "bits", "(II)V", "children", "", "getChildren", "()[Lokhttp3/internal/http2/Huffman$Node;", "[Lokhttp3/internal/http2/Huffman$Node;", "getSymbol", "()I", "terminalBitCount", "getTerminalBitCount", "okhttp"})
/*     */   private static final class Node { @Nullable
/*     */     private final Node[] children; @Nullable
/*     */     public final Node[] getChildren() {
/* 183 */       return this.children;
/*     */     } private final int symbol; private final int terminalBitCount;
/*     */     public final int getSymbol() {
/* 186 */       return this.symbol;
/*     */     }
/*     */     public final int getTerminalBitCount() {
/* 189 */       return this.terminalBitCount;
/*     */     }
/*     */     
/*     */     public Node() {
/* 193 */       this.children = new Node[256];
/* 194 */       this.symbol = 0;
/* 195 */       this.terminalBitCount = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public Node(int symbol, int bits) {
/* 200 */       this.children = (Node[])null;
/* 201 */       this.symbol = symbol;
/* 202 */       int b = bits & 0x7;
/* 203 */       this.terminalBitCount = (b == 0) ? 8 : b;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Huffman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */