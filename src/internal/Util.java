/*     */ package okhttp3.internal;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Unit;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.functions.Function0;
/*     */ import kotlin.jvm.functions.Function1;
/*     */ import kotlin.jvm.internal.InlineMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.jvm.internal.StringCompanionObject;
/*     */ import kotlin.text.Regex;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Call;
/*     */ import okhttp3.EventListener;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.HttpUrl;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.RequestBody;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.ResponseBody;
/*     */ import okhttp3.internal.http2.Header;
/*     */ import okhttp3.internal.io.FileSystem;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import okio.BufferedSource;
/*     */ import okio.ByteString;
/*     */ import okio.Options;
/*     */ import okio.Source;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\000¸\002\n\000\n\002\020\022\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\016\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\005\n\002\020\021\n\002\020\000\n\002\b\003\n\002\030\002\n\000\n\002\020 \n\002\b\007\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020!\n\002\b\003\n\002\020\005\n\000\n\002\020\n\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\005\n\002\020\f\n\002\b\004\n\002\030\002\n\002\b\003\n\002\020\034\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\t\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\020$\n\002\b\b\n\002\020\003\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\032 \020\023\032\0020\0242\006\020\025\032\0020\0212\006\020\026\032\0020\0272\b\020\030\032\004\030\0010\031\032\036\020\032\032\0020\0332\006\020\034\032\0020\0272\006\020\035\032\0020\0272\006\020\036\032\0020\027\032'\020\037\032\0020\0212\006\020\037\032\0020\0212\022\020 \032\n\022\006\b\001\022\0020\"0!\"\0020\"¢\006\002\020#\032\032\020$\032\0020\0332\f\020%\032\b\022\004\022\0020\0330&H\bø\001\000\032-\020'\032\b\022\004\022\002H)0(\"\004\b\000\020)2\022\020*\032\n\022\006\b\001\022\002H)0!\"\002H)H\007¢\006\002\020+\032\016\020,\032\0020\0172\006\020\025\032\0020\021\0321\020-\032\004\030\001H)\"\004\b\000\020)2\006\020.\032\0020\"2\f\020/\032\b\022\004\022\002H)002\006\0201\032\0020\021¢\006\002\0202\032\026\0203\032\002042\006\020\025\032\0020\0212\006\0205\032\0020\017\032\"\0206\032\0020\0332\006\020\025\032\0020\0212\f\020%\032\b\022\004\022\0020\0330&H\bø\001\000\032%\0207\032\0020\033\"\004\b\000\0208*\b\022\004\022\002H8092\006\020:\032\002H8H\000¢\006\002\020;\032\025\020<\032\0020\024*\0020=2\006\020>\032\0020\024H\004\032\025\020<\032\0020\027*\0020\0242\006\020>\032\0020\027H\004\032\025\020<\032\0020\024*\0020?2\006\020>\032\0020\024H\004\032\n\020@\032\0020A*\0020B\032\r\020C\032\0020\033*\0020\"H\b\032\r\020D\032\0020\033*\0020\"H\b\032\n\020E\032\0020\017*\0020\021\032\022\020F\032\0020\017*\0020G2\006\020H\032\0020G\032\n\020I\032\0020\033*\0020J\032\n\020I\032\0020\033*\0020K\032\n\020I\032\0020\033*\0020L\032#\020M\032\b\022\004\022\0020\0210!*\b\022\004\022\0020\0210!2\006\020N\032\0020\021¢\006\002\020O\032&\020P\032\0020\024*\0020\0212\006\020Q\032\0020R2\b\b\002\020S\032\0020\0242\b\b\002\020T\032\0020\024\032&\020P\032\0020\024*\0020\0212\006\020U\032\0020\0212\b\b\002\020S\032\0020\0242\b\b\002\020T\032\0020\024\032\032\020V\032\0020\017*\0020W2\006\020X\032\0020\0242\006\020Y\032\0020\031\032;\020Z\032\b\022\004\022\002H)0(\"\004\b\000\020)*\b\022\004\022\002H)0[2\027\020\\\032\023\022\004\022\002H)\022\004\022\0020\0170]¢\006\002\b^H\bø\001\000\0325\020_\032\0020\017*\b\022\004\022\0020\0210!2\016\020H\032\n\022\004\022\0020\021\030\0010!2\016\020`\032\n\022\006\b\000\022\0020\0210a¢\006\002\020b\032\n\020c\032\0020\027*\0020d\032+\020e\032\0020\024*\b\022\004\022\0020\0210!2\006\020N\032\0020\0212\f\020`\032\b\022\004\022\0020\0210a¢\006\002\020f\032\n\020g\032\0020\024*\0020\021\032\036\020h\032\0020\024*\0020\0212\b\b\002\020S\032\0020\0242\b\b\002\020T\032\0020\024\032\036\020i\032\0020\024*\0020\0212\b\b\002\020S\032\0020\0242\b\b\002\020T\032\0020\024\032\024\020j\032\0020\024*\0020\0212\b\b\002\020S\032\0020\024\0329\020k\032\b\022\004\022\0020\0210!*\b\022\004\022\0020\0210!2\f\020H\032\b\022\004\022\0020\0210!2\016\020`\032\n\022\006\b\000\022\0020\0210a¢\006\002\020l\032\022\020m\032\0020\017*\0020n2\006\020o\032\0020p\032\022\020q\032\0020\017*\0020L2\006\020r\032\0020s\032\r\020t\032\0020\033*\0020\"H\b\032\r\020u\032\0020\033*\0020\"H\b\032\n\020v\032\0020\024*\0020R\032\n\020w\032\0020\021*\0020L\032\022\020x\032\0020y*\0020s2\006\020z\032\0020y\032\n\020{\032\0020\024*\0020s\032\022\020|\032\0020\024*\0020}2\006\020~\032\0020=\032\032\020|\032\0020\017*\0020W2\006\020\026\032\0020\0242\006\020Y\032\0020\031\032\021\020\032\t\022\005\022\0030\0010(*\0020\003\032\022\020\001\032\0020\003*\t\022\005\022\0030\0010(\032\013\020\001\032\0020\021*\0020\024\032\013\020\001\032\0020\021*\0020\027\032\026\020\001\032\0020\021*\0020G2\t\b\002\020\001\032\0020\017\032\035\020\001\032\b\022\004\022\002H)0(\"\004\b\000\020)*\b\022\004\022\002H)0(\0327\020\001\032\021\022\005\022\003H\001\022\005\022\003H\0010\001\"\005\b\000\020\001\"\005\b\001\020\001*\021\022\005\022\003H\001\022\005\022\003H\0010\001\032\024\020\001\032\0020\027*\0020\0212\007\020\001\032\0020\027\032\026\020\001\032\0020\024*\004\030\0010\0212\007\020\001\032\0020\024\032\037\020\001\032\0020\021*\0020\0212\b\b\002\020S\032\0020\0242\b\b\002\020T\032\0020\024\032\016\020\001\032\0020\033*\0020\"H\b\032'\020\001\032\0030\001*\b0\001j\003`\0012\023\020\001\032\016\022\n\022\b0\001j\003`\0010(\032\025\020\001\032\0020\033*\0030\0012\007\020\001\032\0020\024\"\020\020\000\032\0020\0018\006X\004¢\006\002\n\000\"\020\020\002\032\0020\0038\006X\004¢\006\002\n\000\"\020\020\004\032\0020\0058\006X\004¢\006\002\n\000\"\020\020\006\032\0020\0078\006X\004¢\006\002\n\000\"\016\020\b\032\0020\tX\004¢\006\002\n\000\"\020\020\n\032\0020\0138\006X\004¢\006\002\n\000\"\016\020\f\032\0020\rX\004¢\006\002\n\000\"\020\020\016\032\0020\0178\000X\004¢\006\002\n\000\"\020\020\020\032\0020\0218\000X\004¢\006\002\n\000\"\016\020\022\032\0020\021XT¢\006\002\n\000\002\007\n\005\b20\001¨\006\001"}, d2 = {"EMPTY_BYTE_ARRAY", "", "EMPTY_HEADERS", "Lokhttp3/Headers;", "EMPTY_REQUEST", "Lokhttp3/RequestBody;", "EMPTY_RESPONSE", "Lokhttp3/ResponseBody;", "UNICODE_BOMS", "Lokio/Options;", "UTC", "Ljava/util/TimeZone;", "VERIFY_AS_IP_ADDRESS", "Lkotlin/text/Regex;", "assertionsEnabled", "", "okHttpName", "", "userAgent", "checkDuration", "", "name", "duration", "", "unit", "Ljava/util/concurrent/TimeUnit;", "checkOffsetAndCount", "", "arrayLength", "offset", "count", "format", "args", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "ignoreIoExceptions", "block", "Lkotlin/Function0;", "immutableListOf", "", "T", "elements", "([Ljava/lang/Object;)Ljava/util/List;", "isSensitiveHeader", "readFieldOrNull", "instance", "fieldType", "Ljava/lang/Class;", "fieldName", "(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "daemon", "threadName", "addIfAbsent", "E", "", "element", "(Ljava/util/List;Ljava/lang/Object;)V", "and", "", "mask", "", "asFactory", "Lokhttp3/EventListener$Factory;", "Lokhttp3/EventListener;", "assertThreadDoesntHoldLock", "assertThreadHoldsLock", "canParseAsIpAddress", "canReuseConnectionFor", "Lokhttp3/HttpUrl;", "other", "closeQuietly", "Ljava/io/Closeable;", "Ljava/net/ServerSocket;", "Ljava/net/Socket;", "concat", "value", "([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "delimiterOffset", "delimiter", "", "startIndex", "endIndex", "delimiters", "discard", "Lokio/Source;", "timeout", "timeUnit", "filterList", "", "predicate", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "hasIntersection", "comparator", "Ljava/util/Comparator;", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)Z", "headersContentLength", "Lokhttp3/Response;", "indexOf", "([Ljava/lang/String;Ljava/lang/String;Ljava/util/Comparator;)I", "indexOfControlOrNonAscii", "indexOfFirstNonAsciiWhitespace", "indexOfLastNonAsciiWhitespace", "indexOfNonWhitespace", "intersect", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)[Ljava/lang/String;", "isCivilized", "Lokhttp3/internal/io/FileSystem;", "file", "Ljava/io/File;", "isHealthy", "source", "Lokio/BufferedSource;", "notify", "notifyAll", "parseHexDigit", "peerName", "readBomAsCharset", "Ljava/nio/charset/Charset;", "default", "readMedium", "skipAll", "Lokio/Buffer;", "b", "toHeaderList", "Lokhttp3/internal/http2/Header;", "toHeaders", "toHexString", "toHostHeader", "includeDefaultPort", "toImmutableList", "toImmutableMap", "", "K", "V", "toLongOrDefault", "defaultValue", "toNonNegativeInt", "trimSubstring", "wait", "withSuppressed", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "suppressed", "writeMedium", "Lokio/BufferedSink;", "medium", "okhttp"})
/*     */ @JvmName(name = "Util")
/*     */ public final class Util {
/*     */   @JvmField
/*     */   @NotNull
/*  60 */   public static final byte[] EMPTY_BYTE_ARRAY = new byte[0]; @JvmField
/*     */   @NotNull
/*  62 */   public static final Headers EMPTY_HEADERS = Headers.Companion.of(new String[0]);
/*     */   @JvmField
/*     */   @NotNull
/*  65 */   public static final ResponseBody EMPTY_RESPONSE = ResponseBody.Companion.create$default(ResponseBody.Companion, EMPTY_BYTE_ARRAY, null, 1, null); @JvmField
/*     */   @NotNull
/*  67 */   public static final RequestBody EMPTY_REQUEST = RequestBody.Companion.create$default(RequestBody.Companion, EMPTY_BYTE_ARRAY, null, 0, 0, 7, null);
/*     */ 
/*     */   
/*  70 */   private static final Options UNICODE_BOMS = Options.Companion.of(new ByteString[] {
/*  71 */         ByteString.Companion.decodeHex("efbbbf"), 
/*  72 */         ByteString.Companion.decodeHex("feff"), 
/*  73 */         ByteString.Companion.decodeHex("fffe"), 
/*  74 */         ByteString.Companion.decodeHex("0000ffff"), 
/*  75 */         ByteString.Companion.decodeHex("ffff0000")
/*     */       });
/*     */   
/*     */   @JvmField
/*     */   @NotNull
/*  80 */   public static final TimeZone UTC = TimeZone.getTimeZone("GMT"); private static final Regex VERIFY_AS_IP_ADDRESS; static { Intrinsics.checkNotNull(TimeZone.getTimeZone("GMT")); }
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
/*     */   static {
/*  93 */     String str = "([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)"; boolean bool = false; VERIFY_AS_IP_ADDRESS = new Regex(str);
/*     */   }
/*     */   public static final void checkOffsetAndCount(long arrayLength, long offset, long count) {
/*  96 */     if ((offset | count) < 0L || offset > arrayLength || arrayLength - offset < count) {
/*  97 */       throw (Throwable)new ArrayIndexOutOfBoundsException();
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final ThreadFactory threadFactory(@NotNull String name, boolean daemon)
/*     */   {
/* 104 */     Intrinsics.checkNotNullParameter(name, "name"); return new Util$threadFactory$1(name, daemon); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\020\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\020\000\032\n \002*\004\030\0010\0010\0012\016\020\003\032\n \002*\004\030\0010\0040\004H\n¢\006\002\b\005"}, d2 = {"<anonymous>", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "runnable", "Ljava/lang/Runnable;", "newThread"})
/* 105 */   static final class Util$threadFactory$1 implements ThreadFactory { Util$threadFactory$1(String param1String, boolean param1Boolean) {} public final Thread newThread(Runnable runnable) { Thread thread1 = new Thread(runnable, this.$name); boolean bool1 = false, bool2 = false; Thread $this$apply = thread1; int $i$a$-apply-Util$threadFactory$1$1 = 0;
/* 106 */       $this$apply.setDaemon(this.$daemon); return thread1; } }
/*     */   
/*     */   public static final boolean hasIntersection(@NotNull String[] $this$hasIntersection, @Nullable String[] other, @NotNull Comparator<String> comparator) { Intrinsics.checkNotNullParameter($this$hasIntersection, "$this$hasIntersection"); Intrinsics.checkNotNullParameter(comparator, "comparator"); String[] arrayOfString = $this$hasIntersection; byte b = 0; if (!((arrayOfString.length == 0) ? 1 : 0) && other != null) { arrayOfString = other; b = 0; if (!((arrayOfString.length == 0) ? 1 : 0)) { for (String a : $this$hasIntersection) { for (String str : other) { if (comparator.compare(a, str) == 0)
/*     */               return true;  }  }  return false; }  }  return false; }
/*     */   @NotNull public static final String toHostHeader(@NotNull HttpUrl $this$toHostHeader, boolean includeDefaultPort) { Intrinsics.checkNotNullParameter($this$toHostHeader, "$this$toHostHeader"); String host = StringsKt.contains$default($this$toHostHeader.host(), ":", false, 2, null) ? ('[' + $this$toHostHeader.host() + ']') : $this$toHostHeader.host(); return (includeDefaultPort || $this$toHostHeader.port() != HttpUrl.Companion.defaultPort($this$toHostHeader.scheme())) ? (host + ':' + $this$toHostHeader.port()) : host; }
/*     */   public static final int indexOf(@NotNull String[] $this$indexOf, @NotNull String value, @NotNull Comparator<String> comparator) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: ldc '$this$indexOf'
/*     */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   6: aload_1
/*     */     //   7: ldc 'value'
/*     */     //   9: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   12: aload_2
/*     */     //   13: ldc 'comparator'
/*     */     //   15: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   18: aload_0
/*     */     //   19: astore_3
/*     */     //   20: iconst_0
/*     */     //   21: istore #4
/*     */     //   23: iconst_0
/*     */     //   24: istore #5
/*     */     //   26: aload_3
/*     */     //   27: arraylength
/*     */     //   28: istore #6
/*     */     //   30: iload #5
/*     */     //   32: iload #6
/*     */     //   34: if_icmpge -> 77
/*     */     //   37: aload_3
/*     */     //   38: iload #5
/*     */     //   40: aaload
/*     */     //   41: astore #7
/*     */     //   43: iconst_0
/*     */     //   44: istore #8
/*     */     //   46: aload_2
/*     */     //   47: aload #7
/*     */     //   49: aload_1
/*     */     //   50: invokeinterface compare : (Ljava/lang/Object;Ljava/lang/Object;)I
/*     */     //   55: ifne -> 62
/*     */     //   58: iconst_1
/*     */     //   59: goto -> 63
/*     */     //   62: iconst_0
/*     */     //   63: ifeq -> 71
/*     */     //   66: iload #5
/*     */     //   68: goto -> 78
/*     */     //   71: iinc #5, 1
/*     */     //   74: goto -> 30
/*     */     //   77: iconst_m1
/*     */     //   78: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #167	-> 18
/*     */     //   #643	-> 23
/*     */     //   #643	-> 30
/*     */     //   #644	-> 37
/*     */     //   #167	-> 46
/*     */     //   #645	-> 66
/*     */     //   #643	-> 71
/*     */     //   #648	-> 77
/*     */     //   #167	-> 78
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   43	20	7	it	Ljava/lang/String;
/*     */     //   46	17	8	$i$a$-indexOfFirst-Util$indexOf$1	I
/*     */     //   37	37	5	index$iv	I
/*     */     //   20	58	3	$this$indexOfFirst$iv	[Ljava/lang/Object;
/*     */     //   23	55	4	$i$f$indexOfFirst	I
/*     */     //   0	79	0	$this$indexOf	[Ljava/lang/String;
/*     */     //   0	79	1	value	Ljava/lang/String;
/*     */     //   0	79	2	comparator	Ljava/util/Comparator; }
/*     */   @NotNull public static final String[] concat(@NotNull String[] $this$concat, @NotNull String value) { Intrinsics.checkNotNullParameter($this$concat, "$this$concat"); Intrinsics.checkNotNullParameter(value, "value"); String[] arrayOfString1 = $this$concat; int i = $this$concat.length + 1; boolean bool = false; Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(arrayOfString1, i), "java.util.Arrays.copyOf(this, newSize)"); String[] result = Arrays.copyOf(arrayOfString1, i); result[ArraysKt.getLastIndex((Object[])result)] = value; if (result == null)
/*     */       throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");  return result; }
/*     */   public static final int indexOfFirstNonAsciiWhitespace(@NotNull String $this$indexOfFirstNonAsciiWhitespace, int startIndex, int endIndex) { Intrinsics.checkNotNullParameter($this$indexOfFirstNonAsciiWhitespace, "$this$indexOfFirstNonAsciiWhitespace"); for (int i = startIndex, j = endIndex; i < j; i++) { switch ($this$indexOfFirstNonAsciiWhitespace.charAt(i)) { case '\t': case '\n': case '\f': case '\r': case ' ': break;
/*     */         default: return i; }  }  return endIndex; }
/*     */   public static final int indexOfLastNonAsciiWhitespace(@NotNull String $this$indexOfLastNonAsciiWhitespace, int startIndex, int endIndex) { Intrinsics.checkNotNullParameter($this$indexOfLastNonAsciiWhitespace, "$this$indexOfLastNonAsciiWhitespace"); int i = endIndex - 1, j = startIndex; if (i >= j)
/*     */       while (true) { switch ($this$indexOfLastNonAsciiWhitespace.charAt(i)) { case '\t': case '\n': case '\f': case '\r': case ' ': break;
/* 118 */           default: return i + 1; }  if (i != j) { i--; continue; }  break; }   return startIndex; } @NotNull public static final String trimSubstring(@NotNull String $this$trimSubstring, int startIndex, int endIndex) { Intrinsics.checkNotNullParameter($this$trimSubstring, "$this$trimSubstring"); int start = indexOfFirstNonAsciiWhitespace($this$trimSubstring, startIndex, endIndex); int end = indexOfLastNonAsciiWhitespace($this$trimSubstring, start, endIndex); String str = $this$trimSubstring; boolean bool = false; Intrinsics.checkNotNullExpressionValue(str.substring(start, end), "(this as java.lang.Strin…ing(startIndex, endIndex)"); return str.substring(start, end); } @NotNull public static final String[] intersect(@NotNull String[] $this$intersect, @NotNull String[] other, @NotNull Comparator<String> comparator) { Intrinsics.checkNotNullParameter($this$intersect, "$this$intersect"); Intrinsics.checkNotNullParameter(other, "other"); Intrinsics.checkNotNullParameter(comparator, "comparator"); boolean bool = false; List<String> result = new ArrayList();
/* 119 */     for (String a : $this$intersect) {
/* 120 */       for (String b : other) {
/* 121 */         if (comparator.compare(a, b) == 0) {
/* 122 */           result.add(a);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 127 */     Collection<String> $this$toTypedArray$iv = result; int $i$f$toTypedArray = 0;
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
/*     */ 
/*     */ 
/*     */     
/* 641 */     Collection<String> thisCollection$iv = $this$toTypedArray$iv;
/* 642 */     if (thisCollection$iv.toArray(new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  return thisCollection$iv.toArray(new String[0]); } public static final int delimiterOffset(@NotNull String $this$delimiterOffset, @NotNull String delimiters, int startIndex, int endIndex) { Intrinsics.checkNotNullParameter($this$delimiterOffset, "$this$delimiterOffset"); Intrinsics.checkNotNullParameter(delimiters, "delimiters"); for (int i = startIndex, j = endIndex; i < j; i++) { if (StringsKt.contains$default(delimiters, $this$delimiterOffset.charAt(i), false, 2, null)) return i;  }  return endIndex; }
/*     */   public static final int delimiterOffset(@NotNull String $this$delimiterOffset, char delimiter, int startIndex, int endIndex) { Intrinsics.checkNotNullParameter($this$delimiterOffset, "$this$delimiterOffset"); for (int i = startIndex, j = endIndex; i < j; i++) { if ($this$delimiterOffset.charAt(i) == delimiter) return i;  }  return endIndex; }
/*     */   public static final int indexOfControlOrNonAscii(@NotNull String $this$indexOfControlOrNonAscii) { Intrinsics.checkNotNullParameter($this$indexOfControlOrNonAscii, "$this$indexOfControlOrNonAscii"); byte b; int i; for (b = 0, i = $this$indexOfControlOrNonAscii.length(); b < i; b++) { char c = $this$indexOfControlOrNonAscii.charAt(b); if (Intrinsics.compare(c, 31) <= 0 || Intrinsics.compare(c, 127) >= 0) return b;  }  return -1; }
/*     */   public static final boolean canParseAsIpAddress(@NotNull String $this$canParseAsIpAddress) { Intrinsics.checkNotNullParameter($this$canParseAsIpAddress, "$this$canParseAsIpAddress"); return VERIFY_AS_IP_ADDRESS.matches($this$canParseAsIpAddress); }
/*     */   public static final boolean isSensitiveHeader(@NotNull String name) { Intrinsics.checkNotNullParameter(name, "name"); return (StringsKt.equals(name, "Authorization", true) || StringsKt.equals(name, "Cookie", true) || StringsKt.equals(name, "Proxy-Authorization", true) || StringsKt.equals(name, "Set-Cookie", true)); }
/*     */   @NotNull public static final String format(@NotNull String format, @NotNull Object... args) { Intrinsics.checkNotNullParameter(format, "format"); Intrinsics.checkNotNullParameter(args, "args"); StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE; Locale locale = Locale.US; Object[] arrayOfObject = Arrays.copyOf(args, args.length); boolean bool = false; Intrinsics.checkNotNullExpressionValue(String.format(locale, format, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), "java.lang.String.format(locale, format, *args)"); return String.format(locale, format, Arrays.copyOf(arrayOfObject, arrayOfObject.length)); }
/*     */   @NotNull public static final Charset readBomAsCharset(@NotNull BufferedSource $this$readBomAsCharset, @NotNull Charset default) throws IOException { Intrinsics.checkNotNullParameter($this$readBomAsCharset, "$this$readBomAsCharset"); Intrinsics.checkNotNullParameter(default, "default"); switch ($this$readBomAsCharset.select(UNICODE_BOMS)) { case 0: Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_8, "UTF_8");case 1: Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_16BE, "UTF_16BE");case 2: Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_16LE, "UTF_16LE");case 3: case 4: case -1:  }  throw new AssertionError(); }
/* 649 */   public static final int checkDuration(@NotNull String name, long duration, @Nullable TimeUnit unit) { Intrinsics.checkNotNullParameter(name, "name"); boolean bool1 = (duration >= 0L) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Util$checkDuration$1 = 0; String str = name + " < 0"; throw (Throwable)new IllegalStateException(str.toString()); }  bool1 = (unit != null) ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-check-Util$checkDuration$2 = 0; String str = "unit == null"; throw (Throwable)new IllegalStateException(str.toString()); }  long millis = unit.toMillis(duration); bool3 = (millis <= 2147483647L) ? true : false; boolean bool4 = false, bool5 = false; if (!bool3) { int $i$a$-require-Util$checkDuration$3 = 0; String str = name + " too large."; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool3 = (millis != 0L || duration <= 0L) ? true : false; bool4 = false; bool5 = false; if (!bool3) { int $i$a$-require-Util$checkDuration$4 = 0; String str = name + " too small."; throw (Throwable)new IllegalArgumentException(str.toString()); }  return (int)millis; } public static final int parseHexDigit(char $this$parseHexDigit) { char c1 = $this$parseHexDigit; char c2 = c1; if ('0' > c2) { '9'; } else if ('9' >= c2) {  }  c2 = c1; if ('a' > c2) { 'f'; } else if ('f' >= c2) {  }  c2 = c1; if ('A' > c2) { 'F'; } else if ('F' >= c2) {  }  return -1; } @NotNull public static final Headers toHeaders(@NotNull List $this$toHeaders) { Intrinsics.checkNotNullParameter($this$toHeaders, "$this$toHeaders"); Headers.Builder builder = new Headers.Builder(); for (Header header : $this$toHeaders) { ByteString byteString1 = header.component1(), value = header.component2(); builder.addLenient$okhttp(byteString1.utf8(), value.utf8()); }  return builder.build(); }
/* 650 */   @NotNull public static final List<Header> toHeaderList(@NotNull Headers $this$toHeaderList) { Intrinsics.checkNotNullParameter($this$toHeaderList, "$this$toHeaderList"); Iterable $this$map$iv = (Iterable)RangesKt.until(0, $this$toHeaderList.size()); int $i$f$map = 0; Iterable iterable1 = $this$map$iv; Collection<Header> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)); int $i$f$mapTo = 0;
/* 651 */     for (Iterator iterator = iterable1.iterator(); iterator.hasNext(); header = new Header($this$toHeaderList.name(i), $this$toHeaderList.value(i)), collection.add(header)) { Header header; int item$iv$iv = ((IntIterator)iterator).nextInt();
/* 652 */       int i = item$iv$iv; Collection<Header> collection = destination$iv$iv; int $i$a$-map-Util$toHeaderList$1 = 0; }
/* 653 */      return (List<Header>)destination$iv$iv; }
/*     */ 
/*     */   
/*     */   public static final boolean canReuseConnectionFor(@NotNull HttpUrl $this$canReuseConnectionFor, @NotNull HttpUrl other) {
/*     */     Intrinsics.checkNotNullParameter($this$canReuseConnectionFor, "$this$canReuseConnectionFor");
/*     */     Intrinsics.checkNotNullParameter(other, "other");
/*     */     return (Intrinsics.areEqual($this$canReuseConnectionFor.host(), other.host()) && $this$canReuseConnectionFor.port() == other.port() && Intrinsics.areEqual($this$canReuseConnectionFor.scheme(), other.scheme()));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final EventListener.Factory asFactory(@NotNull EventListener $this$asFactory) {
/*     */     Intrinsics.checkNotNullParameter($this$asFactory, "$this$asFactory");
/*     */     return new Util$asFactory$1($this$asFactory);
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\020\000\032\0020\0012\006\020\002\032\0020\003H\n¢\006\002\b\004"}, d2 = {"<anonymous>", "Lokhttp3/EventListener;", "it", "Lokhttp3/Call;", "create"})
/*     */   static final class Util$asFactory$1 implements EventListener.Factory {
/*     */     @NotNull
/*     */     public final EventListener create(@NotNull Call it) {
/*     */       Intrinsics.checkNotNullParameter(it, "it");
/*     */       return this.$this_asFactory;
/*     */     }
/*     */     
/*     */     Util$asFactory$1(EventListener param1EventListener) {}
/*     */   }
/*     */   
/*     */   public static final int and(byte $this$and, int mask) {
/*     */     return $this$and & mask;
/*     */   }
/*     */   
/*     */   public static final int and(short $this$and, int mask) {
/*     */     return $this$and & mask;
/*     */   }
/*     */   
/*     */   public static final long and(int $this$and, long mask) {
/*     */     return $this$and & mask;
/*     */   }
/*     */   
/*     */   public static final void writeMedium(@NotNull BufferedSink $this$writeMedium, int medium) throws IOException {
/*     */     Intrinsics.checkNotNullParameter($this$writeMedium, "$this$writeMedium");
/*     */     $this$writeMedium.writeByte(medium >>> 16 & 0xFF);
/*     */     $this$writeMedium.writeByte(medium >>> 8 & 0xFF);
/*     */     $this$writeMedium.writeByte(medium & 0xFF);
/*     */   }
/*     */   
/*     */   public static final int readMedium(@NotNull BufferedSource $this$readMedium) throws IOException {
/*     */     Intrinsics.checkNotNullParameter($this$readMedium, "$this$readMedium");
/*     */     return and($this$readMedium.readByte(), 255) << 16 | and($this$readMedium.readByte(), 255) << 8 | and($this$readMedium.readByte(), 255);
/*     */   }
/*     */   
/*     */   public static final boolean skipAll(@NotNull Source $this$skipAll, int duration, @NotNull TimeUnit timeUnit) throws IOException {
/*     */     boolean bool1;
/*     */     Intrinsics.checkNotNullParameter($this$skipAll, "$this$skipAll");
/*     */     Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
/*     */     long nowNs = System.nanoTime();
/*     */     long originalDurationNs = $this$skipAll.timeout().hasDeadline() ? ($this$skipAll.timeout().deadlineNanoTime() - nowNs) : Long.MAX_VALUE;
/*     */     long l1 = timeUnit.toNanos(duration);
/*     */     boolean bool2 = false;
/*     */     $this$skipAll.timeout().deadlineNanoTime(nowNs + Math.min(originalDurationNs, l1));
/*     */     try {
/*     */       Buffer skipBuffer = new Buffer();
/*     */       while ($this$skipAll.read(skipBuffer, 8192L) != -1L)
/*     */         skipBuffer.clear(); 
/*     */       bool1 = true;
/*     */     } catch (InterruptedIOException _) {
/*     */       bool1 = false;
/*     */     } finally {
/*     */       if (originalDurationNs == Long.MAX_VALUE) {
/*     */         $this$skipAll.timeout().clearDeadline();
/*     */       } else {
/*     */         $this$skipAll.timeout().deadlineNanoTime(nowNs + originalDurationNs);
/*     */       } 
/*     */     } 
/*     */     return bool1;
/*     */   }
/*     */   
/*     */   public static final boolean discard(@NotNull Source $this$discard, int timeout, @NotNull TimeUnit timeUnit) {
/*     */     boolean bool;
/*     */     Intrinsics.checkNotNullParameter($this$discard, "$this$discard");
/*     */     Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
/*     */     try {
/*     */       bool = skipAll($this$discard, timeout, timeUnit);
/*     */     } catch (IOException _) {
/*     */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final String peerName(@NotNull Socket $this$peerName) {
/*     */     Intrinsics.checkNotNullParameter($this$peerName, "$this$peerName");
/*     */     SocketAddress address = $this$peerName.getRemoteSocketAddress();
/*     */     Intrinsics.checkNotNullExpressionValue(((InetSocketAddress)address).getHostName(), "address.hostName");
/*     */     return (address instanceof InetSocketAddress) ? ((InetSocketAddress)address).getHostName() : address.toString();
/*     */   }
/*     */   
/*     */   public static final boolean isHealthy(@NotNull Socket $this$isHealthy, @NotNull BufferedSource source) {
/*     */     boolean bool;
/*     */     Intrinsics.checkNotNullParameter($this$isHealthy, "$this$isHealthy");
/*     */     Intrinsics.checkNotNullParameter(source, "source");
/*     */     try {
/*     */       boolean bool1;
/*     */       bool = $this$isHealthy.getSoTimeout();
/*     */       try {
/*     */         $this$isHealthy.setSoTimeout(1);
/*     */         bool1 = !source.exhausted() ? true : false;
/*     */         $this$isHealthy.setSoTimeout(bool);
/*     */       } finally {
/*     */         $this$isHealthy.setSoTimeout(bool);
/*     */       } 
/*     */     } catch (SocketTimeoutException _) {
/*     */       bool = true;
/*     */     } catch (IOException _) {
/*     */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public static final void ignoreIoExceptions(@NotNull Function0 block) {
/*     */     int $i$f$ignoreIoExceptions = 0;
/*     */     Intrinsics.checkNotNullParameter(block, "block");
/*     */     try {
/*     */       block.invoke();
/*     */     } catch (IOException iOException) {}
/*     */   }
/*     */   
/*     */   public static final void threadName(@NotNull String name, @NotNull Function0 block) {
/*     */     int $i$f$threadName = 0;
/*     */     Intrinsics.checkNotNullParameter(name, "name");
/*     */     Intrinsics.checkNotNullParameter(block, "block");
/*     */     Thread currentThread = Thread.currentThread();
/*     */     Intrinsics.checkNotNullExpressionValue(currentThread, "currentThread");
/*     */     String oldName = currentThread.getName();
/*     */     currentThread.setName(name);
/*     */     try {
/*     */       block.invoke();
/*     */     } finally {
/*     */       InlineMarker.finallyStart(1);
/*     */       currentThread.setName(oldName);
/*     */       InlineMarker.finallyEnd(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final int skipAll(@NotNull Buffer $this$skipAll, byte b) {
/*     */     Intrinsics.checkNotNullParameter($this$skipAll, "$this$skipAll");
/*     */     int count = 0;
/*     */     while (!$this$skipAll.exhausted() && $this$skipAll.getByte(0L) == b) {
/*     */       count++;
/*     */       $this$skipAll.readByte();
/*     */     } 
/*     */     return count;
/*     */   }
/*     */   
/*     */   public static final int indexOfNonWhitespace(@NotNull String $this$indexOfNonWhitespace, int startIndex) {
/*     */     Intrinsics.checkNotNullParameter($this$indexOfNonWhitespace, "$this$indexOfNonWhitespace");
/*     */     for (int i = startIndex, j = $this$indexOfNonWhitespace.length(); i < j; i++) {
/*     */       char c = $this$indexOfNonWhitespace.charAt(i);
/*     */       if (c != ' ' && c != '\t')
/*     */         return i; 
/*     */     } 
/*     */     return $this$indexOfNonWhitespace.length();
/*     */   }
/*     */   
/*     */   public static final long headersContentLength(@NotNull Response $this$headersContentLength) {
/*     */     Intrinsics.checkNotNullParameter($this$headersContentLength, "$this$headersContentLength");
/*     */     $this$headersContentLength.headers().get("Content-Length");
/*     */     return ($this$headersContentLength.headers().get("Content-Length") != null) ? toLongOrDefault($this$headersContentLength.headers().get("Content-Length"), -1L) : -1L;
/*     */   }
/*     */   
/*     */   public static final long toLongOrDefault(@NotNull String $this$toLongOrDefault, long defaultValue) {
/*     */     long l;
/*     */     Intrinsics.checkNotNullParameter($this$toLongOrDefault, "$this$toLongOrDefault");
/*     */     try {
/*     */       String str = $this$toLongOrDefault;
/*     */       boolean bool = false;
/*     */       l = Long.parseLong(str);
/*     */     } catch (NumberFormatException _) {
/*     */       l = defaultValue;
/*     */     } 
/*     */     return l;
/*     */   }
/*     */   
/*     */   public static final int toNonNegativeInt(@Nullable String $this$toNonNegativeInt, int defaultValue) {
/*     */     // Byte code:
/*     */     //   0: nop
/*     */     //   1: aload_0
/*     */     //   2: dup
/*     */     //   3: ifnull -> 19
/*     */     //   6: astore #4
/*     */     //   8: iconst_0
/*     */     //   9: istore #5
/*     */     //   11: aload #4
/*     */     //   13: invokestatic parseLong : (Ljava/lang/String;)J
/*     */     //   16: goto -> 22
/*     */     //   19: pop
/*     */     //   20: iload_1
/*     */     //   21: ireturn
/*     */     //   22: lstore_2
/*     */     //   23: nop
/*     */     //   24: lload_2
/*     */     //   25: ldc_w 2147483647
/*     */     //   28: i2l
/*     */     //   29: lcmp
/*     */     //   30: ifle -> 39
/*     */     //   33: ldc_w 2147483647
/*     */     //   36: goto -> 51
/*     */     //   39: lload_2
/*     */     //   40: lconst_0
/*     */     //   41: lcmp
/*     */     //   42: ifge -> 49
/*     */     //   45: iconst_0
/*     */     //   46: goto -> 51
/*     */     //   49: lload_2
/*     */     //   50: l2i
/*     */     //   51: ireturn
/*     */     //   52: astore_2
/*     */     //   53: iload_1
/*     */     //   54: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #460	-> 0
/*     */     //   #461	-> 1
/*     */     //   #461	-> 19
/*     */     //   #461	-> 20
/*     */     //   #462	-> 23
/*     */     //   #463	-> 24
/*     */     //   #464	-> 39
/*     */     //   #465	-> 49
/*     */     //   #462	-> 51
/*     */     //   #467	-> 52
/*     */     //   #468	-> 53
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   23	29	2	value	J
/*     */     //   53	2	2	_	Ljava/lang/NumberFormatException;
/*     */     //   0	55	0	$this$toNonNegativeInt	Ljava/lang/String;
/*     */     //   0	55	1	defaultValue	I
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	52	52	java/lang/NumberFormatException
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final <T> List<T> toImmutableList(@NotNull List $this$toImmutableList) {
/*     */     Intrinsics.checkNotNullParameter($this$toImmutableList, "$this$toImmutableList");
/*     */     Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(CollectionsKt.toMutableList($this$toImmutableList)), "Collections.unmodifiableList(toMutableList())");
/*     */     return (List)Collections.unmodifiableList(CollectionsKt.toMutableList($this$toImmutableList));
/*     */   }
/*     */   
/*     */   @SafeVarargs
/*     */   @NotNull
/*     */   public static final <T> List<T> immutableListOf(@NotNull Object... elements) {
/*     */     Intrinsics.checkNotNullParameter(elements, "elements");
/*     */     Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(CollectionsKt.listOf(Arrays.copyOf((Object[])elements.clone(), ((Object[])elements.clone()).length))), "Collections.unmodifiable…istOf(*elements.clone()))");
/*     */     return (List)Collections.unmodifiableList(CollectionsKt.listOf(Arrays.copyOf((Object[])elements.clone(), ((Object[])elements.clone()).length)));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final <K, V> Map<K, V> toImmutableMap(@NotNull Map<?, ?> $this$toImmutableMap) {
/*     */     Intrinsics.checkNotNullParameter($this$toImmutableMap, "$this$toImmutableMap");
/*     */     Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableMap(new LinkedHashMap<>($this$toImmutableMap)), "Collections.unmodifiableMap(LinkedHashMap(this))");
/*     */     return $this$toImmutableMap.isEmpty() ? MapsKt.emptyMap() : (Map)Collections.unmodifiableMap(new LinkedHashMap<>($this$toImmutableMap));
/*     */   }
/*     */   
/*     */   public static final void closeQuietly(@NotNull Closeable $this$closeQuietly) {
/*     */     Intrinsics.checkNotNullParameter($this$closeQuietly, "$this$closeQuietly");
/*     */     try {
/*     */       $this$closeQuietly.close();
/*     */     } catch (RuntimeException rethrown) {
/*     */       throw (Throwable)rethrown;
/*     */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   public static final void closeQuietly(@NotNull Socket $this$closeQuietly) {
/*     */     Intrinsics.checkNotNullParameter($this$closeQuietly, "$this$closeQuietly");
/*     */     try {
/*     */       $this$closeQuietly.close();
/*     */     } catch (AssertionError e) {
/*     */       throw e;
/*     */     } catch (RuntimeException rethrown) {
/*     */       if (Intrinsics.areEqual(rethrown.getMessage(), "bio == null"))
/*     */         return; 
/*     */       throw (Throwable)rethrown;
/*     */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   public static final void closeQuietly(@NotNull ServerSocket $this$closeQuietly) {
/*     */     Intrinsics.checkNotNullParameter($this$closeQuietly, "$this$closeQuietly");
/*     */     try {
/*     */       $this$closeQuietly.close();
/*     */     } catch (RuntimeException rethrown) {
/*     */       throw (Throwable)rethrown;
/*     */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   public static final boolean isCivilized(@NotNull FileSystem $this$isCivilized, @NotNull File file) {
/*     */     Intrinsics.checkNotNullParameter($this$isCivilized, "$this$isCivilized");
/*     */     Intrinsics.checkNotNullParameter(file, "file");
/*     */     Closeable closeable = (Closeable)$this$isCivilized.sink(file);
/*     */     boolean bool1 = false, bool2 = false;
/*     */     Throwable throwable = (Throwable)null;
/*     */     try {
/*     */       Sink it = (Sink)closeable;
/*     */       int $i$a$-use-Util$isCivilized$1 = 0;
/*     */       try {
/*     */         $this$isCivilized.delete(file);
/*     */         return true;
/*     */       } catch (IOException iOException) {}
/*     */       Unit unit = Unit.INSTANCE;
/*     */     } catch (Throwable throwable1) {
/*     */       throwable = throwable1 = null;
/*     */       throw throwable1;
/*     */     } finally {
/*     */       CloseableKt.closeFinally(closeable, throwable);
/*     */     } 
/*     */     $this$isCivilized.delete(file);
/*     */     return false;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final String toHexString(long $this$toHexString) {
/*     */     Intrinsics.checkNotNullExpressionValue(Long.toHexString($this$toHexString), "java.lang.Long.toHexString(this)");
/*     */     return Long.toHexString($this$toHexString);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final String toHexString(int $this$toHexString) {
/*     */     Intrinsics.checkNotNullExpressionValue(Integer.toHexString($this$toHexString), "Integer.toHexString(this)");
/*     */     return Integer.toHexString($this$toHexString);
/*     */   }
/*     */   
/*     */   public static final void wait(@NotNull Object $this$wait) {
/*     */     int $i$f$wait = 0;
/*     */     Intrinsics.checkNotNullParameter($this$wait, "$this$wait");
/*     */     $this$wait.wait();
/*     */   }
/*     */   
/*     */   public static final void notify(@NotNull Object $this$notify) {
/*     */     int $i$f$notify = 0;
/*     */     Intrinsics.checkNotNullParameter($this$notify, "$this$notify");
/*     */     $this$notify.notify();
/*     */   }
/*     */   
/*     */   public static final void notifyAll(@NotNull Object $this$notifyAll) {
/*     */     int $i$f$notifyAll = 0;
/*     */     Intrinsics.checkNotNullParameter($this$notifyAll, "$this$notifyAll");
/*     */     $this$notifyAll.notifyAll();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static final <T> T readFieldOrNull(@NotNull Object instance, @NotNull Class<T> fieldType, @NotNull String fieldName) {
/*     */     Intrinsics.checkNotNullParameter(instance, "instance");
/*     */     Intrinsics.checkNotNullParameter(fieldType, "fieldType");
/*     */     Intrinsics.checkNotNullParameter(fieldName, "fieldName");
/*     */     Class<?> c = instance.getClass();
/*     */     while ((Intrinsics.areEqual(c, Object.class) ^ true) != 0) {
/*     */       try {
/*     */         Field field = c.getDeclaredField(fieldName);
/*     */         Intrinsics.checkNotNullExpressionValue(field, "field");
/*     */         field.setAccessible(true);
/*     */         Object value = field.get(instance);
/*     */         return !fieldType.isInstance(value) ? null : fieldType.cast(value);
/*     */       } catch (NoSuchFieldException noSuchFieldException) {
/*     */         Intrinsics.checkNotNullExpressionValue(c.getSuperclass(), "c.superclass");
/*     */         c = c.getSuperclass();
/*     */       } 
/*     */     } 
/*     */     if ((Intrinsics.areEqual(fieldName, "delegate") ^ true) != 0) {
/*     */       Object delegate = readFieldOrNull(instance, Object.class, "delegate");
/*     */       if (delegate != null)
/*     */         return readFieldOrNull(delegate, fieldType, fieldName); 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   
/*     */   public static final <E> void addIfAbsent(@NotNull List<Object> $this$addIfAbsent, Object element) {
/*     */     Intrinsics.checkNotNullParameter($this$addIfAbsent, "$this$addIfAbsent");
/*     */     if (!$this$addIfAbsent.contains(element))
/*     */       $this$addIfAbsent.add(element); 
/*     */   }
/*     */   
/*     */   @JvmField
/*     */   public static final boolean assertionsEnabled = OkHttpClient.class.desiredAssertionStatus();
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final String okHttpName = StringsKt.removeSuffix(StringsKt.removePrefix(OkHttpClient.class.getName(), "okhttp3."), "Client");
/*     */   @NotNull
/*     */   public static final String userAgent = "okhttp/4.9.3";
/*     */   
/*     */   static {
/*     */     Intrinsics.checkNotNullExpressionValue(OkHttpClient.class.getName(), "OkHttpClient::class.java.name");
/*     */   }
/*     */   
/*     */   public static final void assertThreadHoldsLock(@NotNull Object $this$assertThreadHoldsLock) {
/*     */     int $i$f$assertThreadHoldsLock = 0;
/*     */     Intrinsics.checkNotNullParameter($this$assertThreadHoldsLock, "$this$assertThreadHoldsLock");
/*     */     if (assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock)) {
/*     */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*     */       throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final void assertThreadDoesntHoldLock(@NotNull Object $this$assertThreadDoesntHoldLock) {
/*     */     int $i$f$assertThreadDoesntHoldLock = 0;
/*     */     Intrinsics.checkNotNullParameter($this$assertThreadDoesntHoldLock, "$this$assertThreadDoesntHoldLock");
/*     */     if (assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock)) {
/*     */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*     */       throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock);
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final Throwable withSuppressed(@NotNull Exception $this$withSuppressed, @NotNull List suppressed) {
/*     */     Intrinsics.checkNotNullParameter($this$withSuppressed, "$this$withSuppressed");
/*     */     Intrinsics.checkNotNullParameter(suppressed, "suppressed");
/*     */     Exception exception1 = $this$withSuppressed;
/*     */     boolean bool1 = false, bool2 = false;
/*     */     Exception $this$apply = exception1;
/*     */     int $i$a$-apply-Util$withSuppressed$1 = 0;
/*     */     if (suppressed.size() > 1) {
/*     */       List list = suppressed;
/*     */       boolean bool = false;
/*     */       System.out.println(list);
/*     */     } 
/*     */     for (Exception e : suppressed)
/*     */       ExceptionsKt.addSuppressed($this$apply, e); 
/*     */     return exception1;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static final <T> List<T> filterList(@NotNull Iterable $this$filterList, @NotNull Function1 predicate) {
/*     */     int $i$f$filterList = 0;
/*     */     Intrinsics.checkNotNullParameter($this$filterList, "$this$filterList");
/*     */     Intrinsics.checkNotNullParameter(predicate, "predicate");
/*     */     List<T> result = CollectionsKt.emptyList();
/*     */     for (Object i : $this$filterList) {
/*     */       if (((Boolean)predicate.invoke(i)).booleanValue()) {
/*     */         if (result.isEmpty()) {
/*     */           boolean bool = false;
/*     */           result = new ArrayList();
/*     */         } 
/*     */         if (result == null)
/*     */           throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableList<T>"); 
/*     */         TypeIntrinsics.asMutableList(result).add(i);
/*     */       } 
/*     */     } 
/*     */     return result;
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/Util.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */