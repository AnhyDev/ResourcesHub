/*     */ package okhttp3;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.collections.MapsKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.JvmOverloads;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.http.HttpMethod;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000N\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020$\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\013\n\002\020 \n\002\b\002\n\002\030\002\n\002\b\b\030\0002\0020\001:\001*BA\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\b\020\b\032\004\030\0010\t\022\026\020\n\032\022\022\b\022\006\022\002\b\0030\f\022\004\022\0020\0010\013¢\006\002\020\rJ\017\020\b\032\004\030\0010\tH\007¢\006\002\b\033J\r\020\017\032\0020\020H\007¢\006\002\b\034J\020\020\035\032\004\030\0010\0052\006\020\036\032\0020\005J\r\020\006\032\0020\007H\007¢\006\002\b\037J\024\020\006\032\b\022\004\022\0020\0050 2\006\020\036\032\0020\005J\r\020\004\032\0020\005H\007¢\006\002\b!J\006\020\"\032\0020#J\b\020$\032\004\030\0010\001J#\020$\032\004\030\001H%\"\004\b\000\020%2\016\020&\032\n\022\006\b\001\022\002H%0\f¢\006\002\020'J\b\020(\032\0020\005H\026J\r\020\002\032\0020\003H\007¢\006\002\b)R\025\020\b\032\004\030\0010\t8\007¢\006\b\n\000\032\004\b\b\020\016R\021\020\017\032\0020\0208G¢\006\006\032\004\b\017\020\021R\023\020\006\032\0020\0078\007¢\006\b\n\000\032\004\b\006\020\022R\021\020\023\032\0020\0248F¢\006\006\032\004\b\023\020\025R\020\020\026\032\004\030\0010\020X\016¢\006\002\n\000R\023\020\004\032\0020\0058\007¢\006\b\n\000\032\004\b\004\020\027R$\020\n\032\022\022\b\022\006\022\002\b\0030\f\022\004\022\0020\0010\013X\004¢\006\b\n\000\032\004\b\030\020\031R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\032¨\006+"}, d2 = {"Lokhttp3/Request;", "", "url", "Lokhttp3/HttpUrl;", "method", "", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "tags", "", "Ljava/lang/Class;", "(Lokhttp3/HttpUrl;Ljava/lang/String;Lokhttp3/Headers;Lokhttp3/RequestBody;Ljava/util/Map;)V", "()Lokhttp3/RequestBody;", "cacheControl", "Lokhttp3/CacheControl;", "()Lokhttp3/CacheControl;", "()Lokhttp3/Headers;", "isHttps", "", "()Z", "lazyCacheControl", "()Ljava/lang/String;", "getTags$okhttp", "()Ljava/util/Map;", "()Lokhttp3/HttpUrl;", "-deprecated_body", "-deprecated_cacheControl", "header", "name", "-deprecated_headers", "", "-deprecated_method", "newBuilder", "Lokhttp3/Request$Builder;", "tag", "T", "type", "(Ljava/lang/Class;)Ljava/lang/Object;", "toString", "-deprecated_url", "Builder", "okhttp"})
/*     */ public final class Request {
/*     */   private CacheControl lazyCacheControl;
/*     */   @NotNull
/*     */   private final HttpUrl url;
/*     */   @NotNull
/*     */   private final String method;
/*     */   
/*  28 */   public Request(@NotNull HttpUrl url, @NotNull String method, @NotNull Headers headers, @Nullable RequestBody body, @NotNull Map<Class<?>, Object> tags) { this.url = url; this.method = method; this.headers = headers; this.body = body; this.tags = tags; } @NotNull private final Headers headers; @Nullable private final RequestBody body; @NotNull private final Map<Class<?>, Object> tags; @JvmName(name = "url") @NotNull
/*  29 */   public final HttpUrl url() { return this.url; } @JvmName(name = "method") @NotNull
/*  30 */   public final String method() { return this.method; } @JvmName(name = "headers") @NotNull
/*  31 */   public final Headers headers() { return this.headers; } @JvmName(name = "body") @Nullable
/*  32 */   public final RequestBody body() { return this.body; } @NotNull
/*  33 */   public final Map<Class<?>, Object> getTags$okhttp() { return this.tags; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isHttps() {
/*  39 */     return this.url.isHttps();
/*     */   } @Nullable
/*  41 */   public final String header(@NotNull String name) { Intrinsics.checkNotNullParameter(name, "name"); return this.headers.get(name); } @NotNull
/*     */   public final List<String> headers(@NotNull String name) {
/*  43 */     Intrinsics.checkNotNullParameter(name, "name"); return this.headers.values(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final Object tag() {
/*  53 */     return tag(Object.class);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final <T> T tag(@NotNull Class<T> type)
/*     */   {
/*  59 */     Intrinsics.checkNotNullParameter(type, "type"); return type.cast(this.tags.get(type)); } @NotNull
/*     */   public final Builder newBuilder() {
/*  61 */     return new Builder(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "cacheControl")
/*     */   @NotNull
/*     */   public final CacheControl cacheControl() {
/*  69 */     CacheControl result = this.lazyCacheControl;
/*  70 */     if (result == null) {
/*  71 */       result = CacheControl.Companion.parse(this.headers);
/*  72 */       this.lazyCacheControl = result;
/*     */     } 
/*  74 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "url"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_url")
/*     */   @NotNull
/*     */   public final HttpUrl -deprecated_url() {
/*  82 */     return this.url;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "method"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_method")
/*     */   @NotNull
/*     */   public final String -deprecated_method() {
/*  89 */     return this.method;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "headers"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_headers")
/*     */   @NotNull
/*     */   public final Headers -deprecated_headers() {
/*  96 */     return this.headers;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "body"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_body")
/*     */   @Nullable
/*     */   public final RequestBody -deprecated_body() {
/* 103 */     return this.body;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cacheControl"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_cacheControl")
/*     */   @NotNull
/*     */   public final CacheControl -deprecated_cacheControl() {
/* 110 */     return cacheControl();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_1
/*     */     //   2: iconst_0
/*     */     //   3: istore_2
/*     */     //   4: new java/lang/StringBuilder
/*     */     //   7: dup
/*     */     //   8: invokespecial <init> : ()V
/*     */     //   11: astore_2
/*     */     //   12: iconst_0
/*     */     //   13: istore_3
/*     */     //   14: iconst_0
/*     */     //   15: istore #4
/*     */     //   17: aload_2
/*     */     //   18: astore #5
/*     */     //   20: iconst_0
/*     */     //   21: istore #6
/*     */     //   23: aload #5
/*     */     //   25: ldc 'Request{method='
/*     */     //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   30: pop
/*     */     //   31: aload #5
/*     */     //   33: aload_0
/*     */     //   34: getfield method : Ljava/lang/String;
/*     */     //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   40: pop
/*     */     //   41: aload #5
/*     */     //   43: ldc ', url='
/*     */     //   45: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   48: pop
/*     */     //   49: aload #5
/*     */     //   51: aload_0
/*     */     //   52: getfield url : Lokhttp3/HttpUrl;
/*     */     //   55: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   58: pop
/*     */     //   59: aload_0
/*     */     //   60: getfield headers : Lokhttp3/Headers;
/*     */     //   63: invokevirtual size : ()I
/*     */     //   66: ifeq -> 222
/*     */     //   69: aload #5
/*     */     //   71: ldc ', headers=['
/*     */     //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   76: pop
/*     */     //   77: aload_0
/*     */     //   78: getfield headers : Lokhttp3/Headers;
/*     */     //   81: checkcast java/lang/Iterable
/*     */     //   84: astore #7
/*     */     //   86: iconst_0
/*     */     //   87: istore #8
/*     */     //   89: iconst_0
/*     */     //   90: istore #9
/*     */     //   92: aload #7
/*     */     //   94: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   99: astore #10
/*     */     //   101: aload #10
/*     */     //   103: invokeinterface hasNext : ()Z
/*     */     //   108: ifeq -> 213
/*     */     //   111: aload #10
/*     */     //   113: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   118: astore #11
/*     */     //   120: iload #9
/*     */     //   122: iinc #9, 1
/*     */     //   125: istore #12
/*     */     //   127: iconst_0
/*     */     //   128: istore #13
/*     */     //   130: iload #12
/*     */     //   132: ifge -> 138
/*     */     //   135: invokestatic throwIndexOverflow : ()V
/*     */     //   138: iload #12
/*     */     //   140: aload #11
/*     */     //   142: checkcast kotlin/Pair
/*     */     //   145: astore #14
/*     */     //   147: istore #15
/*     */     //   149: iconst_0
/*     */     //   150: istore #16
/*     */     //   152: aload #14
/*     */     //   154: invokevirtual component1 : ()Ljava/lang/Object;
/*     */     //   157: checkcast java/lang/String
/*     */     //   160: astore #17
/*     */     //   162: aload #14
/*     */     //   164: invokevirtual component2 : ()Ljava/lang/Object;
/*     */     //   167: checkcast java/lang/String
/*     */     //   170: astore #18
/*     */     //   172: iload #15
/*     */     //   174: ifle -> 185
/*     */     //   177: aload #5
/*     */     //   179: ldc ', '
/*     */     //   181: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   184: pop
/*     */     //   185: aload #5
/*     */     //   187: aload #17
/*     */     //   189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   192: pop
/*     */     //   193: aload #5
/*     */     //   195: bipush #58
/*     */     //   197: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*     */     //   200: pop
/*     */     //   201: aload #5
/*     */     //   203: aload #18
/*     */     //   205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   208: pop
/*     */     //   209: nop
/*     */     //   210: goto -> 101
/*     */     //   213: nop
/*     */     //   214: aload #5
/*     */     //   216: bipush #93
/*     */     //   218: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*     */     //   221: pop
/*     */     //   222: aload_0
/*     */     //   223: getfield tags : Ljava/util/Map;
/*     */     //   226: astore #7
/*     */     //   228: iconst_0
/*     */     //   229: istore #8
/*     */     //   231: aload #7
/*     */     //   233: invokeinterface isEmpty : ()Z
/*     */     //   238: ifne -> 245
/*     */     //   241: iconst_1
/*     */     //   242: goto -> 246
/*     */     //   245: iconst_0
/*     */     //   246: ifeq -> 267
/*     */     //   249: aload #5
/*     */     //   251: ldc ', tags='
/*     */     //   253: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   256: pop
/*     */     //   257: aload #5
/*     */     //   259: aload_0
/*     */     //   260: getfield tags : Ljava/util/Map;
/*     */     //   263: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   266: pop
/*     */     //   267: aload #5
/*     */     //   269: bipush #125
/*     */     //   271: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*     */     //   274: pop
/*     */     //   275: nop
/*     */     //   276: aload_2
/*     */     //   277: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   280: dup
/*     */     //   281: ldc 'StringBuilder().apply(builderAction).toString()'
/*     */     //   283: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   286: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #112	-> 0
/*     */     //   #113	-> 23
/*     */     //   #114	-> 31
/*     */     //   #115	-> 41
/*     */     //   #116	-> 49
/*     */     //   #117	-> 59
/*     */     //   #118	-> 69
/*     */     //   #119	-> 77
/*     */     //   #299	-> 89
/*     */     //   #300	-> 92
/*     */     //   #300	-> 101
/*     */     //   #300	-> 140
/*     */     //   #120	-> 172
/*     */     //   #121	-> 177
/*     */     //   #123	-> 185
/*     */     //   #124	-> 193
/*     */     //   #125	-> 201
/*     */     //   #126	-> 209
/*     */     //   #301	-> 213
/*     */     //   #127	-> 214
/*     */     //   #129	-> 222
/*     */     //   #129	-> 246
/*     */     //   #130	-> 249
/*     */     //   #131	-> 257
/*     */     //   #133	-> 267
/*     */     //   #134	-> 275
/*     */     //   #112	-> 276
/*     */     //   #134	-> 286
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   172	37	17	name	Ljava/lang/String;
/*     */     //   172	37	18	value	Ljava/lang/String;
/*     */     //   149	60	15	index	I
/*     */     //   149	60	14	$dstr$name$value	Lkotlin/Pair;
/*     */     //   152	57	16	$i$a$-forEachIndexed-Request$toString$1$1	I
/*     */     //   120	90	11	item$iv	Ljava/lang/Object;
/*     */     //   92	122	9	index$iv	I
/*     */     //   86	128	7	$this$forEachIndexed$iv	Ljava/lang/Iterable;
/*     */     //   89	125	8	$i$f$forEachIndexed	I
/*     */     //   20	256	5	$this$buildString	Ljava/lang/StringBuilder;
/*     */     //   23	253	6	$i$a$-buildString-Request$toString$1	I
/*     */     //   0	287	0	this	Lokhttp3/Request;
/*     */   }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000V\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\016\n\002\b\005\n\002\020%\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\t\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\b\n\002\030\002\n\000\b\026\030\0002\0020\001B\007\b\026¢\006\002\020\002B\017\b\020\022\006\020\003\032\0020\004¢\006\002\020\005J\030\020%\032\0020\0002\006\020&\032\0020\0232\006\020'\032\0020\023H\026J\b\020(\032\0020\004H\026J\020\020)\032\0020\0002\006\020)\032\0020*H\026J\024\020+\032\0020\0002\n\b\002\020\006\032\004\030\0010\007H\027J\b\020,\032\0020\000H\026J\b\020-\032\0020\000H\026J\030\020.\032\0020\0002\006\020&\032\0020\0232\006\020'\032\0020\023H\026J\020\020\f\032\0020\0002\006\020\f\032\0020/H\026J\032\020\022\032\0020\0002\006\020\022\032\0020\0232\b\020\006\032\004\030\0010\007H\026J\020\0200\032\0020\0002\006\020\006\032\0020\007H\026J\020\0201\032\0020\0002\006\020\006\032\0020\007H\026J\020\0202\032\0020\0002\006\020\006\032\0020\007H\026J\020\0203\032\0020\0002\006\020&\032\0020\023H\026J-\0204\032\0020\000\"\004\b\000\02052\016\0206\032\n\022\006\b\000\022\002H50\0322\b\0204\032\004\030\001H5H\026¢\006\002\0207J\022\0204\032\0020\0002\b\0204\032\004\030\0010\001H\026J\020\020\037\032\0020\0002\006\020\037\032\00208H\026J\020\020\037\032\0020\0002\006\020\037\032\0020\023H\026J\020\020\037\032\0020\0002\006\020\037\032\0020 H\026R\034\020\006\032\004\030\0010\007X\016¢\006\016\n\000\032\004\b\b\020\t\"\004\b\n\020\013R\032\020\f\032\0020\rX\016¢\006\016\n\000\032\004\b\016\020\017\"\004\b\020\020\021R\032\020\022\032\0020\023X\016¢\006\016\n\000\032\004\b\024\020\025\"\004\b\026\020\027R*\020\030\032\022\022\b\022\006\022\002\b\0030\032\022\004\022\0020\0010\031X\016¢\006\016\n\000\032\004\b\033\020\034\"\004\b\035\020\036R\034\020\037\032\004\030\0010 X\016¢\006\016\n\000\032\004\b!\020\"\"\004\b#\020$¨\0069"}, d2 = {"Lokhttp3/Request$Builder;", "", "()V", "request", "Lokhttp3/Request;", "(Lokhttp3/Request;)V", "body", "Lokhttp3/RequestBody;", "getBody$okhttp", "()Lokhttp3/RequestBody;", "setBody$okhttp", "(Lokhttp3/RequestBody;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "method", "", "getMethod$okhttp", "()Ljava/lang/String;", "setMethod$okhttp", "(Ljava/lang/String;)V", "tags", "", "Ljava/lang/Class;", "getTags$okhttp", "()Ljava/util/Map;", "setTags$okhttp", "(Ljava/util/Map;)V", "url", "Lokhttp3/HttpUrl;", "getUrl$okhttp", "()Lokhttp3/HttpUrl;", "setUrl$okhttp", "(Lokhttp3/HttpUrl;)V", "addHeader", "name", "value", "build", "cacheControl", "Lokhttp3/CacheControl;", "delete", "get", "head", "header", "Lokhttp3/Headers;", "patch", "post", "put", "removeHeader", "tag", "T", "type", "(Ljava/lang/Class;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "Ljava/net/URL;", "okhttp"})
/*     */   public static class Builder
/*     */   {
/*     */     @Nullable
/*     */     private HttpUrl url;
/*     */     @NotNull
/*     */     private String method;
/*     */     @NotNull
/*     */     private Headers.Builder headers;
/*     */     @Nullable
/*     */     private RequestBody body;
/*     */     @NotNull
/*     */     private Map<Class<?>, Object> tags;
/*     */     
/*     */     @Nullable
/*     */     public final HttpUrl getUrl$okhttp()
/*     */     {
/* 137 */       return this.url; } public final void setUrl$okhttp(@Nullable HttpUrl <set-?>) { this.url = <set-?>; } @NotNull
/* 138 */     public final String getMethod$okhttp() { return this.method; } public final void setMethod$okhttp(@NotNull String <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.method = <set-?>; } @NotNull
/* 139 */     public final Headers.Builder getHeaders$okhttp() { return this.headers; } public final void setHeaders$okhttp(@NotNull Headers.Builder <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.headers = <set-?>; } @Nullable
/* 140 */     public final RequestBody getBody$okhttp() { return this.body; } public final void setBody$okhttp(@Nullable RequestBody <set-?>) { this.body = <set-?>; }
/*     */     
/*     */     @NotNull
/* 143 */     public final Map<Class<?>, Object> getTags$okhttp() { return this.tags; } public final void setTags$okhttp(@NotNull Map<Class<?>, Object> <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.tags = <set-?>; } public Builder() { boolean bool = false; this.tags = new LinkedHashMap<>();
/*     */ 
/*     */       
/* 146 */       this.method = "GET";
/* 147 */       this.headers = new Headers.Builder(); }
/*     */      public Builder(@NotNull Request request) {
/*     */       boolean bool = false;
/*     */       this.tags = new LinkedHashMap<>();
/* 151 */       this.url = request.url();
/* 152 */       this.method = request.method();
/* 153 */       this.body = request.body();
/*     */       
/* 155 */       bool = false; this.tags = request.getTags$okhttp().isEmpty() ? new LinkedHashMap<>() : 
/*     */         
/* 157 */         MapsKt.toMutableMap(request.getTags$okhttp());
/*     */       
/* 159 */       this.headers = request.headers().newBuilder();
/*     */     } @NotNull
/*     */     public Builder url(@NotNull HttpUrl url) {
/* 162 */       Intrinsics.checkNotNullParameter(url, "url"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Request$Builder$url$1 = 0;
/* 163 */       $this$apply.url = url;
/*     */       return builder1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder url(@NotNull String url) {
/* 174 */       Intrinsics.checkNotNullParameter(url, "url");
/*     */       
/* 176 */       String str1 = url; byte b = 3; boolean bool = false; Intrinsics.checkNotNullExpressionValue(str1.substring(b), "(this as java.lang.String).substring(startIndex)");
/*     */ 
/*     */       
/* 179 */       str1 = url; b = 4; bool = false; Intrinsics.checkNotNullExpressionValue(str1.substring(b), "(this as java.lang.String).substring(startIndex)"); String finalUrl = StringsKt.startsWith(url, "ws:", true) ? ("http:" + str1.substring(b)) : (StringsKt.startsWith(url, "wss:", true) ? ("https:" + str1.substring(b)) : 
/*     */         
/* 181 */         url);
/*     */ 
/*     */       
/* 184 */       return url(HttpUrl.Companion.get(finalUrl));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder url(@NotNull URL url) {
/* 192 */       Intrinsics.checkNotNullParameter(url, "url"); Intrinsics.checkNotNullExpressionValue(url.toString(), "url.toString()"); return url(HttpUrl.Companion.get(url.toString()));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder header(@NotNull String name, @NotNull String value) {
/* 198 */       Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(value, "value"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Request$Builder$header$1 = 0;
/* 199 */       $this$apply.headers.set(name, value);
/*     */       return builder1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder addHeader(@NotNull String name, @NotNull String value) {
/* 209 */       Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(value, "value"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Request$Builder$addHeader$1 = 0;
/* 210 */       $this$apply.headers.add(name, value);
/*     */       return builder1;
/*     */     } @NotNull
/*     */     public Builder removeHeader(@NotNull String name) {
/* 214 */       Intrinsics.checkNotNullParameter(name, "name"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Request$Builder$removeHeader$1 = 0;
/* 215 */       $this$apply.headers.removeAll(name);
/*     */       return builder1;
/*     */     } @NotNull
/*     */     public Builder headers(@NotNull Headers headers) {
/* 219 */       Intrinsics.checkNotNullParameter(headers, "headers"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Request$Builder$headers$1 = 0;
/* 220 */       $this$apply.headers = headers.newBuilder();
/*     */       return builder1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder cacheControl(@NotNull CacheControl cacheControl) {
/* 229 */       Intrinsics.checkNotNullParameter(cacheControl, "cacheControl"); String value = cacheControl.toString();
/*     */       
/* 231 */       String str1 = value; boolean bool = false; return ((str1.length() == 0)) ? removeHeader("Cache-Control") : 
/* 232 */         header("Cache-Control", value);
/*     */     }
/*     */     
/*     */     @NotNull
/* 236 */     public Builder get() { return method("GET", null); }
/*     */     @NotNull
/* 238 */     public Builder head() { return method("HEAD", null); }
/*     */     @NotNull
/* 240 */     public Builder post(@NotNull RequestBody body) { Intrinsics.checkNotNullParameter(body, "body"); return method("POST", body); }
/*     */     @JvmOverloads
/*     */     @NotNull
/* 243 */     public Builder delete(@Nullable RequestBody body) { return method("DELETE", body); }
/*     */     @NotNull
/* 245 */     public Builder put(@NotNull RequestBody body) { Intrinsics.checkNotNullParameter(body, "body"); return method("PUT", body); }
/*     */     @NotNull
/* 247 */     public Builder patch(@NotNull RequestBody body) { Intrinsics.checkNotNullParameter(body, "body"); return method("PATCH", body); } @NotNull
/*     */     public Builder method(@NotNull String method, @Nullable RequestBody body) {
/* 249 */       Intrinsics.checkNotNullParameter(method, "method"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Request$Builder$method$1 = 0;
/* 250 */       String str = method; boolean bool3 = false; boolean bool = (str.length() > 0); bool3 = false; boolean bool4 = false; if (!bool) { int $i$a$-require-Request$Builder$method$1$1 = 0; String str1 = 
/* 251 */           "method.isEmpty() == true"; throw (Throwable)new IllegalArgumentException(str1.toString()); }
/*     */       
/* 253 */       if (body == null) {
/* 254 */         bool = !HttpMethod.requiresRequestBody(method); bool3 = false; bool4 = false; if (!bool) { int $i$a$-require-Request$Builder$method$1$2 = 0; String str1 = 
/* 255 */             "method " + method + " must have a request body."; throw (Throwable)new IllegalArgumentException(str1.toString()); }
/*     */       
/*     */       } else {
/* 258 */         bool = HttpMethod.permitsRequestBody(method); bool3 = false; bool4 = false; if (!bool) { int $i$a$-require-Request$Builder$method$1$3 = 0; String str1 = 
/* 259 */             "method " + method + " must not have a request body."; throw (Throwable)new IllegalArgumentException(str1.toString()); }
/*     */       
/*     */       } 
/* 262 */       $this$apply.method = method;
/* 263 */       $this$apply.body = body;
/*     */       return builder1;
/*     */     } @NotNull
/*     */     public Builder tag(@Nullable Object tag) {
/* 267 */       return tag(Object.class, tag);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public <T> Builder tag(@NotNull Class<?> type, @Nullable Object tag) {
/* 276 */       Intrinsics.checkNotNullParameter(type, "type"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Request$Builder$tag$1 = 0;
/* 277 */       if (tag == null) {
/* 278 */         $this$apply.tags.remove(type);
/*     */       } else {
/* 280 */         if ($this$apply.tags.isEmpty()) {
/* 281 */           boolean bool = false; $this$apply.tags = new LinkedHashMap<>();
/*     */         } 
/* 283 */         Intrinsics.checkNotNull(type.cast(tag)); $this$apply.tags.put(type, type.cast(tag));
/*     */       } 
/*     */       return builder1;
/*     */     }
/*     */     @NotNull
/*     */     public Request build() {
/* 289 */       HttpUrl httpUrl1 = this.url; boolean bool1 = false, bool2 = false; if (httpUrl1 == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 299 */         int $i$a$-checkNotNull-Request$Builder$build$1 = 0;
/*     */         String str2 = "url == null", str1 = str2;
/*     */         throw (Throwable)new IllegalStateException(str1.toString());
/*     */       } 
/*     */       Map<Class<?>, ? extends Object> map = Util.toImmutableMap(this.tags);
/*     */       RequestBody requestBody = this.body;
/*     */       Headers headers = this.headers.build();
/*     */       String str = this.method;
/*     */       HttpUrl httpUrl2 = httpUrl1;
/*     */       return new Request(httpUrl2, str, headers, requestBody, map);
/*     */     }
/*     */     
/*     */     @JvmOverloads
/*     */     @NotNull
/*     */     public final Builder delete() {
/*     */       return delete$default(this, null, 1, null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Request.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */