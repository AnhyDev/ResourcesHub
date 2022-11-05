/*     */ package okhttp3.internal.http2;
/*     */ 
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.ByteString;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\021\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\032\n\002\020\013\n\002\b\003\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\026\020\037\032\0020\0052\006\020 \032\0020\0132\006\020!\032\0020\013J\025\020\"\032\0020\0052\006\020 \032\0020\013H\000¢\006\002\b#J.\020$\032\0020\0052\006\020%\032\0020&2\006\020'\032\0020\0132\006\020(\032\0020\0132\006\020 \032\0020\0132\006\020!\032\0020\013R\026\020\003\032\b\022\004\022\0020\0050\004X\004¢\006\004\n\002\020\006R\020\020\007\032\0020\b8\006X\004¢\006\002\n\000R\030\020\t\032\n\022\006\022\004\030\0010\0050\004X\004¢\006\004\n\002\020\006R\016\020\n\032\0020\013XT¢\006\002\n\000R\016\020\f\032\0020\013XT¢\006\002\n\000R\016\020\r\032\0020\013XT¢\006\002\n\000R\016\020\016\032\0020\013XT¢\006\002\n\000R\016\020\017\032\0020\013XT¢\006\002\n\000R\016\020\020\032\0020\013XT¢\006\002\n\000R\016\020\021\032\0020\013XT¢\006\002\n\000R\016\020\022\032\0020\013XT¢\006\002\n\000R\026\020\023\032\b\022\004\022\0020\0050\004X\004¢\006\004\n\002\020\006R\016\020\024\032\0020\013XT¢\006\002\n\000R\016\020\025\032\0020\013XT¢\006\002\n\000R\016\020\026\032\0020\013XT¢\006\002\n\000R\016\020\027\032\0020\013XT¢\006\002\n\000R\016\020\030\032\0020\013XT¢\006\002\n\000R\016\020\031\032\0020\013XT¢\006\002\n\000R\016\020\032\032\0020\013XT¢\006\002\n\000R\016\020\033\032\0020\013XT¢\006\002\n\000R\016\020\034\032\0020\013XT¢\006\002\n\000R\016\020\035\032\0020\013XT¢\006\002\n\000R\016\020\036\032\0020\013XT¢\006\002\n\000¨\006)"}, d2 = {"Lokhttp3/internal/http2/Http2;", "", "()V", "BINARY", "", "", "[Ljava/lang/String;", "CONNECTION_PREFACE", "Lokio/ByteString;", "FLAGS", "FLAG_ACK", "", "FLAG_COMPRESSED", "FLAG_END_HEADERS", "FLAG_END_PUSH_PROMISE", "FLAG_END_STREAM", "FLAG_NONE", "FLAG_PADDED", "FLAG_PRIORITY", "FRAME_NAMES", "INITIAL_MAX_FRAME_SIZE", "TYPE_CONTINUATION", "TYPE_DATA", "TYPE_GOAWAY", "TYPE_HEADERS", "TYPE_PING", "TYPE_PRIORITY", "TYPE_PUSH_PROMISE", "TYPE_RST_STREAM", "TYPE_SETTINGS", "TYPE_WINDOW_UPDATE", "formatFlags", "type", "flags", "formattedType", "formattedType$okhttp", "frameLog", "inbound", "", "streamId", "length", "okhttp"})
/*     */ public final class Http2
/*     */ {
/*     */   static {
/*  21 */     Http2 http2 = new Http2(); } @JvmField
/*     */   @NotNull
/*  23 */   public static final ByteString CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
/*     */   
/*     */   public static final int INITIAL_MAX_FRAME_SIZE = 16384;
/*     */   
/*     */   public static final int TYPE_DATA = 0;
/*     */   
/*     */   public static final int TYPE_HEADERS = 1;
/*     */   
/*     */   public static final int TYPE_PRIORITY = 2;
/*     */   
/*     */   public static final int TYPE_RST_STREAM = 3;
/*     */   
/*     */   public static final int TYPE_SETTINGS = 4;
/*     */   public static final int TYPE_PUSH_PROMISE = 5;
/*     */   public static final int TYPE_PING = 6;
/*     */   public static final int TYPE_GOAWAY = 7;
/*     */   public static final int TYPE_WINDOW_UPDATE = 8;
/*     */   public static final int TYPE_CONTINUATION = 9;
/*     */   public static final int FLAG_NONE = 0;
/*     */   public static final int FLAG_ACK = 1;
/*     */   public static final int FLAG_END_STREAM = 1;
/*     */   public static final int FLAG_END_HEADERS = 4;
/*     */   public static final int FLAG_END_PUSH_PROMISE = 4;
/*     */   public static final int FLAG_PADDED = 8;
/*     */   public static final int FLAG_PRIORITY = 32;
/*     */   public static final int FLAG_COMPRESSED = 32;
/*  49 */   private static final String[] FRAME_NAMES = new String[] {
/*  50 */       "DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", 
/*  51 */       "WINDOW_UPDATE", "CONTINUATION"
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private static final String[] FLAGS = new String[64]; static { String[] arrayOfString;
/*  59 */     for (char c = 'Ā'; null < c; ) { byte b1 = null, b2 = null; String[] arrayOfString1 = arrayOfString; int $i$a$-<init>-Http2$BINARY$1 = 0;
/*  60 */       Intrinsics.checkNotNullExpressionValue(Integer.toBinaryString(b1), "Integer.toBinaryString(it)"); String str = StringsKt.replace$default(Util.format("%8s", new Object[] { Integer.toBinaryString(b1) }), ' ', '0', false, 4, null); arrayOfString1[b2] = str; null++; }  BINARY = arrayOfString;
/*     */ 
/*     */ 
/*     */     
/*  64 */     FLAGS[0] = "";
/*  65 */     FLAGS[1] = "END_STREAM";
/*     */     
/*  67 */     int[] prefixFlags = { 1 };
/*     */     
/*  69 */     FLAGS[8] = "PADDED";
/*  70 */     for (int prefixFlag : prefixFlags) {
/*  71 */       FLAGS[prefixFlag | 0x8] = Intrinsics.stringPlus(FLAGS[prefixFlag], "|PADDED");
/*     */     }
/*     */     
/*  74 */     FLAGS[4] = "END_HEADERS";
/*  75 */     FLAGS[32] = "PRIORITY";
/*  76 */     FLAGS[36] = "END_HEADERS|PRIORITY";
/*  77 */     int[] frameFlags = { 4, 32, 36 };
/*     */     
/*  79 */     for (int frameFlag : frameFlags) {
/*  80 */       for (int prefixFlag : prefixFlags) {
/*  81 */         FLAGS[prefixFlag | frameFlag] = FLAGS[prefixFlag] + "|" + FLAGS[frameFlag];
/*     */         
/*  83 */         FLAGS[prefixFlag | frameFlag | 0x8] = FLAGS[prefixFlag] + "|" + FLAGS[frameFlag] + "|PADDED";
/*     */       } 
/*     */     }  byte b;
/*     */     int i;
/*  87 */     for (b = 0, i = FLAGS.length; b < i; b++) {
/*  88 */       if (FLAGS[b] == null) FLAGS[b] = BINARY[b];
/*     */     
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String[] BINARY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final Http2 INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final String frameLog(boolean inbound, int streamId, int length, int type, int flags) {
/* 115 */     String formattedType = formattedType$okhttp(type);
/* 116 */     String formattedFlags = formatFlags(type, flags);
/* 117 */     String direction = inbound ? "<<" : ">>";
/* 118 */     return Util.format("%s 0x%08x %5d %-13s %s", new Object[] {
/* 119 */           direction, Integer.valueOf(streamId), Integer.valueOf(length), formattedType, formattedFlags });
/*     */   }
/*     */   @NotNull
/*     */   public final String formattedType$okhttp(int type) {
/* 123 */     return (type < FRAME_NAMES.length) ? FRAME_NAMES[type] : Util.format("0x%02x", new Object[] { Integer.valueOf(type) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final String formatFlags(int type, int flags) {
/* 131 */     if (flags == 0) return ""; 
/* 132 */     switch (type) { case 4:
/*     */       case 6:
/* 134 */         return (flags == 1) ? "ACK" : BINARY[flags];
/* 135 */       case 2: case 3: case 7: case 8: return BINARY[flags]; }
/*     */     
/* 137 */     Intrinsics.checkNotNull(FLAGS[flags]); String result = (flags < FLAGS.length) ? FLAGS[flags] : BINARY[flags];
/*     */     
/* 139 */     return (
/* 140 */       type == 5 && (flags & 0x4) != 0) ? 
/* 141 */       StringsKt.replace$default(result, "HEADERS", "PUSH_PROMISE", false, 4, null) : (
/*     */       
/* 143 */       (type == 0 && (flags & 0x20) != 0) ? 
/* 144 */       StringsKt.replace$default(result, "PRIORITY", "COMPRESSED", false, 4, null) : 
/*     */       
/* 146 */       result);
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Http2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */