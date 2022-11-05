/*     */ package okhttp3;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.connection.Exchange;
/*     */ import okio.BufferedSource;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000p\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\007\n\002\020\013\n\002\b\013\n\002\020 \n\002\030\002\n\000\n\002\020\002\n\002\b\t\n\002\030\002\n\002\b\013\030\0002\0020\001:\001FB{\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t\022\b\020\n\032\004\030\0010\013\022\006\020\f\032\0020\r\022\b\020\016\032\004\030\0010\017\022\b\020\020\032\004\030\0010\000\022\b\020\021\032\004\030\0010\000\022\b\020\022\032\004\030\0010\000\022\006\020\023\032\0020\024\022\006\020\025\032\0020\024\022\b\020\026\032\004\030\0010\027¢\006\002\020\030J\017\020\016\032\004\030\0010\017H\007¢\006\002\b+J\r\020\032\032\0020\033H\007¢\006\002\b,J\017\020\021\032\004\030\0010\000H\007¢\006\002\b-J\f\020.\032\b\022\004\022\002000/J\b\0201\032\00202H\026J\r\020\b\032\0020\tH\007¢\006\002\b3J\017\020\n\032\004\030\0010\013H\007¢\006\002\b4J\036\0205\032\004\030\0010\0072\006\0206\032\0020\0072\n\b\002\0207\032\004\030\0010\007H\007J\r\020\f\032\0020\rH\007¢\006\002\b8J\024\020\f\032\b\022\004\022\0020\0070/2\006\0206\032\0020\007J\r\020\006\032\0020\007H\007¢\006\002\b9J\017\020\020\032\004\030\0010\000H\007¢\006\002\b:J\006\020;\032\0020<J\016\020=\032\0020\0172\006\020>\032\0020\024J\017\020\022\032\004\030\0010\000H\007¢\006\002\b?J\r\020\004\032\0020\005H\007¢\006\002\b@J\r\020\025\032\0020\024H\007¢\006\002\bAJ\r\020\002\032\0020\003H\007¢\006\002\bBJ\r\020\023\032\0020\024H\007¢\006\002\bCJ\b\020D\032\0020\007H\026J\006\020E\032\0020\rR\025\020\016\032\004\030\0010\0178\007¢\006\b\n\000\032\004\b\016\020\031R\021\020\032\032\0020\0338G¢\006\006\032\004\b\032\020\034R\025\020\021\032\004\030\0010\0008\007¢\006\b\n\000\032\004\b\021\020\035R\023\020\b\032\0020\t8\007¢\006\b\n\000\032\004\b\b\020\036R\030\020\026\032\004\030\0010\0278\001X\004¢\006\b\n\000\032\004\b\026\020\037R\025\020\n\032\004\030\0010\0138\007¢\006\b\n\000\032\004\b\n\020 R\023\020\f\032\0020\r8\007¢\006\b\n\000\032\004\b\f\020!R\021\020\"\032\0020#8F¢\006\006\032\004\b\"\020$R\021\020%\032\0020#8F¢\006\006\032\004\b%\020$R\020\020&\032\004\030\0010\033X\016¢\006\002\n\000R\023\020\006\032\0020\0078\007¢\006\b\n\000\032\004\b\006\020'R\025\020\020\032\004\030\0010\0008\007¢\006\b\n\000\032\004\b\020\020\035R\025\020\022\032\004\030\0010\0008\007¢\006\b\n\000\032\004\b\022\020\035R\023\020\004\032\0020\0058\007¢\006\b\n\000\032\004\b\004\020(R\023\020\025\032\0020\0248\007¢\006\b\n\000\032\004\b\025\020)R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020*R\023\020\023\032\0020\0248\007¢\006\b\n\000\032\004\b\023\020)¨\006G"}, d2 = {"Lokhttp3/Response;", "Ljava/io/Closeable;", "request", "Lokhttp3/Request;", "protocol", "Lokhttp3/Protocol;", "message", "", "code", "", "handshake", "Lokhttp3/Handshake;", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/ResponseBody;", "networkResponse", "cacheResponse", "priorResponse", "sentRequestAtMillis", "", "receivedResponseAtMillis", "exchange", "Lokhttp3/internal/connection/Exchange;", "(Lokhttp3/Request;Lokhttp3/Protocol;Ljava/lang/String;ILokhttp3/Handshake;Lokhttp3/Headers;Lokhttp3/ResponseBody;Lokhttp3/Response;Lokhttp3/Response;Lokhttp3/Response;JJLokhttp3/internal/connection/Exchange;)V", "()Lokhttp3/ResponseBody;", "cacheControl", "Lokhttp3/CacheControl;", "()Lokhttp3/CacheControl;", "()Lokhttp3/Response;", "()I", "()Lokhttp3/internal/connection/Exchange;", "()Lokhttp3/Handshake;", "()Lokhttp3/Headers;", "isRedirect", "", "()Z", "isSuccessful", "lazyCacheControl", "()Ljava/lang/String;", "()Lokhttp3/Protocol;", "()J", "()Lokhttp3/Request;", "-deprecated_body", "-deprecated_cacheControl", "-deprecated_cacheResponse", "challenges", "", "Lokhttp3/Challenge;", "close", "", "-deprecated_code", "-deprecated_handshake", "header", "name", "defaultValue", "-deprecated_headers", "-deprecated_message", "-deprecated_networkResponse", "newBuilder", "Lokhttp3/Response$Builder;", "peekBody", "byteCount", "-deprecated_priorResponse", "-deprecated_protocol", "-deprecated_receivedResponseAtMillis", "-deprecated_request", "-deprecated_sentRequestAtMillis", "toString", "trailers", "Builder", "okhttp"})
/*     */ public final class Response implements Closeable {
/*     */   private CacheControl lazyCacheControl;
/*     */   @NotNull
/*     */   private final Request request;
/*     */   @NotNull
/*     */   private final Protocol protocol;
/*     */   @NotNull
/*     */   private final String message;
/*     */   private final int code;
/*     */   @Nullable
/*     */   private final Handshake handshake;
/*     */   @NotNull
/*     */   private final Headers headers;
/*     */   @Nullable
/*     */   private final ResponseBody body;
/*     */   @Nullable
/*     */   private final Response networkResponse;
/*     */   @Nullable
/*     */   private final Response cacheResponse;
/*     */   @Nullable
/*     */   private final Response priorResponse;
/*     */   private final long sentRequestAtMillis;
/*     */   private final long receivedResponseAtMillis;
/*     */   @Nullable
/*     */   private final Exchange exchange;
/*     */   
/*     */   public Response(@NotNull Request request, @NotNull Protocol protocol, @NotNull String message, int code, @Nullable Handshake handshake, @NotNull Headers headers, @Nullable ResponseBody body, @Nullable Response networkResponse, @Nullable Response cacheResponse, @Nullable Response priorResponse, long sentRequestAtMillis, long receivedResponseAtMillis, @Nullable Exchange exchange) {
/*  40 */     this.request = request; this.protocol = protocol; this.message = message; this.code = code; this.handshake = handshake; this.headers = headers; this.body = body; this.networkResponse = networkResponse; this.cacheResponse = cacheResponse; this.priorResponse = priorResponse; this.sentRequestAtMillis = sentRequestAtMillis; this.receivedResponseAtMillis = receivedResponseAtMillis; this.exchange = exchange;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "request")
/*     */   @NotNull
/*     */   public final Request request() {
/*  50 */     return this.request;
/*     */   } @JvmName(name = "protocol")
/*     */   @NotNull
/*  53 */   public final Protocol protocol() { return this.protocol; } @JvmName(name = "message")
/*     */   @NotNull
/*     */   public final String message() {
/*  56 */     return this.message;
/*     */   } @JvmName(name = "code")
/*     */   public final int code() {
/*  59 */     return this.code;
/*     */   }
/*     */   
/*     */   @JvmName(name = "handshake")
/*     */   @Nullable
/*     */   public final Handshake handshake() {
/*  65 */     return this.handshake; } @JvmName(name = "headers")
/*     */   @NotNull
/*     */   public final Headers headers() {
/*  68 */     return this.headers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "body")
/*     */   @Nullable
/*     */   public final ResponseBody body() {
/*  78 */     return this.body;
/*     */   }
/*     */ 
/*     */   
/*     */   @JvmName(name = "networkResponse")
/*     */   @Nullable
/*     */   public final Response networkResponse() {
/*  85 */     return this.networkResponse;
/*     */   }
/*     */ 
/*     */   
/*     */   @JvmName(name = "cacheResponse")
/*     */   @Nullable
/*     */   public final Response cacheResponse() {
/*  92 */     return this.cacheResponse;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "priorResponse")
/*     */   @Nullable
/*     */   public final Response priorResponse() {
/* 100 */     return this.priorResponse;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "sentRequestAtMillis")
/*     */   public final long sentRequestAtMillis() {
/* 107 */     return this.sentRequestAtMillis;
/*     */   }
/*     */ 
/*     */   
/*     */   @JvmName(name = "receivedResponseAtMillis")
/*     */   public final long receivedResponseAtMillis()
/*     */   {
/* 114 */     return this.receivedResponseAtMillis; } @JvmName(name = "exchange")
/*     */   @Nullable
/* 116 */   public final Exchange exchange() { return this.exchange; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "request"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_request")
/*     */   @NotNull
/*     */   public final Request -deprecated_request() {
/* 126 */     return this.request;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "protocol"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_protocol")
/*     */   @NotNull
/*     */   public final Protocol -deprecated_protocol() {
/* 133 */     return this.protocol;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "code"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_code")
/*     */   public final int -deprecated_code() {
/* 140 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isSuccessful() {
/* 147 */     int i = this.code; if (200 > i) { 299; } else if (299 >= i) {  }  return false;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "message"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_message")
/*     */   @NotNull
/*     */   public final String -deprecated_message() {
/* 154 */     return this.message;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "handshake"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_handshake")
/*     */   @Nullable
/*     */   public final Handshake -deprecated_handshake() {
/* 161 */     return this.handshake;
/*     */   } @NotNull
/* 163 */   public final List<String> headers(@NotNull String name) { Intrinsics.checkNotNullParameter(name, "name"); return this.headers.values(name); } @JvmOverloads
/*     */   @Nullable
/*     */   public final String header(@NotNull String name, @Nullable String defaultValue) {
/* 166 */     Intrinsics.checkNotNullParameter(name, "name"); if (this.headers.get(name) == null) this.headers.get(name);  return defaultValue;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "headers"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_headers")
/*     */   @NotNull
/*     */   public final Headers -deprecated_headers() {
/* 173 */     return this.headers;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final Headers trailers() throws IOException
/*     */   {
/* 180 */     Exchange exchange = this.exchange; boolean bool1 = false, bool2 = false; if (exchange == null)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 456 */       int $i$a$-checkNotNull-Response$trailers$1 = 0; String str = "trailers not available"; throw (Throwable)new IllegalStateException(str.toString()); }  return exchange.trailers(); } @NotNull public final ResponseBody peekBody(long byteCount) throws IOException { Intrinsics.checkNotNull(this.body); BufferedSource peeked = this.body.source().peek(); Buffer buffer = new Buffer(); peeked.request(byteCount); long l = peeked.getBuffer().size(); boolean bool = false; buffer.write((Source)peeked, Math.min(byteCount, l)); return ResponseBody.Companion.create((BufferedSource)buffer, this.body.contentType(), buffer.size()); } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "body"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_body") @Nullable public final ResponseBody -deprecated_body() { return this.body; } @NotNull public final Builder newBuilder() { return new Builder(this); } public final boolean isRedirect() { switch (this.code) { case 300: case 301: case 302: case 303: case 307: case 308:  }  return false; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "networkResponse"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_networkResponse") @Nullable public final Response -deprecated_networkResponse() { return this.networkResponse; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cacheResponse"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_cacheResponse") @Nullable public final Response -deprecated_cacheResponse() { return this.cacheResponse; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "priorResponse"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_priorResponse") @Nullable public final Response -deprecated_priorResponse() { return this.priorResponse; } @NotNull public final List<Challenge> challenges() { switch (this.code) { case 401: case 407:  }  return CollectionsKt.emptyList(); } @JvmName(name = "cacheControl") @NotNull public final CacheControl cacheControl() { CacheControl result = this.lazyCacheControl; if (result == null) { result = CacheControl.Companion.parse(this.headers); this.lazyCacheControl = result; }  return result; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cacheControl"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_cacheControl") @NotNull public final CacheControl -deprecated_cacheControl() { return cacheControl(); } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "sentRequestAtMillis"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_sentRequestAtMillis") public final long -deprecated_sentRequestAtMillis() { return this.sentRequestAtMillis; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "receivedResponseAtMillis"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_receivedResponseAtMillis") public final long -deprecated_receivedResponseAtMillis() { return this.receivedResponseAtMillis; } public void close() { ResponseBody responseBody = this.body; boolean bool1 = false, bool2 = false; if (responseBody == null) { int $i$a$-checkNotNull-Response$close$1 = 0; String str = "response is not eligible for a body and must not be closed"; throw (Throwable)new IllegalStateException(str.toString()); }  responseBody.close(); } @NotNull public String toString() { return "Response{protocol=" + this.protocol + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}'; } @JvmOverloads @Nullable public final String header(@NotNull String name) { return header$default(this, name, null, 2, null); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000l\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\t\n\002\020\b\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\016\n\002\b\013\n\002\030\002\n\002\b\005\n\002\020\t\n\002\b\005\n\002\030\002\n\002\b\f\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\005\b\026\030\0002\0020\001B\007\b\026¢\006\002\020\002B\017\b\020\022\006\020\003\032\0020\004¢\006\002\020\005J\030\020I\032\0020\0002\006\020J\032\0020)2\006\020K\032\0020)H\026J\022\020\006\032\0020\0002\b\020\006\032\004\030\0010\007H\026J\b\020L\032\0020\004H\026J\022\020\f\032\0020\0002\b\020\f\032\004\030\0010\004H\026J\022\020M\032\0020N2\b\020\003\032\004\030\0010\004H\002J\032\020O\032\0020N2\006\020J\032\0020)2\b\020\003\032\004\030\0010\004H\002J\020\020\020\032\0020\0002\006\020\020\032\0020\021H\026J\022\020\034\032\0020\0002\b\020\034\032\004\030\0010\035H\026J\030\020P\032\0020\0002\006\020J\032\0020)2\006\020K\032\0020)H\026J\020\020\"\032\0020\0002\006\020\"\032\0020QH\026J\025\020R\032\0020N2\006\020S\032\0020\027H\000¢\006\002\bTJ\020\020(\032\0020\0002\006\020(\032\0020)H\026J\022\020.\032\0020\0002\b\020.\032\004\030\0010\004H\026J\022\0201\032\0020\0002\b\0201\032\004\030\0010\004H\026J\020\0204\032\0020\0002\006\0204\032\00205H\026J\020\020:\032\0020\0002\006\020:\032\0020;H\026J\020\020U\032\0020\0002\006\020J\032\0020)H\026J\020\020@\032\0020\0002\006\020@\032\0020AH\026J\020\020F\032\0020\0002\006\020F\032\0020;H\026R\034\020\006\032\004\030\0010\007X\016¢\006\016\n\000\032\004\b\b\020\t\"\004\b\n\020\013R\034\020\f\032\004\030\0010\004X\016¢\006\016\n\000\032\004\b\r\020\016\"\004\b\017\020\005R\032\020\020\032\0020\021X\016¢\006\016\n\000\032\004\b\022\020\023\"\004\b\024\020\025R\034\020\026\032\004\030\0010\027X\016¢\006\016\n\000\032\004\b\030\020\031\"\004\b\032\020\033R\034\020\034\032\004\030\0010\035X\016¢\006\016\n\000\032\004\b\036\020\037\"\004\b \020!R\032\020\"\032\0020#X\016¢\006\016\n\000\032\004\b$\020%\"\004\b&\020'R\034\020(\032\004\030\0010)X\016¢\006\016\n\000\032\004\b*\020+\"\004\b,\020-R\034\020.\032\004\030\0010\004X\016¢\006\016\n\000\032\004\b/\020\016\"\004\b0\020\005R\034\0201\032\004\030\0010\004X\016¢\006\016\n\000\032\004\b2\020\016\"\004\b3\020\005R\034\0204\032\004\030\00105X\016¢\006\016\n\000\032\004\b6\0207\"\004\b8\0209R\032\020:\032\0020;X\016¢\006\016\n\000\032\004\b<\020=\"\004\b>\020?R\034\020@\032\004\030\0010AX\016¢\006\016\n\000\032\004\bB\020C\"\004\bD\020ER\032\020F\032\0020;X\016¢\006\016\n\000\032\004\bG\020=\"\004\bH\020?¨\006V"}, d2 = {"Lokhttp3/Response$Builder;", "", "()V", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "body", "Lokhttp3/ResponseBody;", "getBody$okhttp", "()Lokhttp3/ResponseBody;", "setBody$okhttp", "(Lokhttp3/ResponseBody;)V", "cacheResponse", "getCacheResponse$okhttp", "()Lokhttp3/Response;", "setCacheResponse$okhttp", "code", "", "getCode$okhttp", "()I", "setCode$okhttp", "(I)V", "exchange", "Lokhttp3/internal/connection/Exchange;", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "setExchange$okhttp", "(Lokhttp3/internal/connection/Exchange;)V", "handshake", "Lokhttp3/Handshake;", "getHandshake$okhttp", "()Lokhttp3/Handshake;", "setHandshake$okhttp", "(Lokhttp3/Handshake;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "message", "", "getMessage$okhttp", "()Ljava/lang/String;", "setMessage$okhttp", "(Ljava/lang/String;)V", "networkResponse", "getNetworkResponse$okhttp", "setNetworkResponse$okhttp", "priorResponse", "getPriorResponse$okhttp", "setPriorResponse$okhttp", "protocol", "Lokhttp3/Protocol;", "getProtocol$okhttp", "()Lokhttp3/Protocol;", "setProtocol$okhttp", "(Lokhttp3/Protocol;)V", "receivedResponseAtMillis", "", "getReceivedResponseAtMillis$okhttp", "()J", "setReceivedResponseAtMillis$okhttp", "(J)V", "request", "Lokhttp3/Request;", "getRequest$okhttp", "()Lokhttp3/Request;", "setRequest$okhttp", "(Lokhttp3/Request;)V", "sentRequestAtMillis", "getSentRequestAtMillis$okhttp", "setSentRequestAtMillis$okhttp", "addHeader", "name", "value", "build", "checkPriorResponse", "", "checkSupportResponse", "header", "Lokhttp3/Headers;", "initExchange", "deferredTrailers", "initExchange$okhttp", "removeHeader", "okhttp"}) public static class Builder { @Nullable private Request request; @Nullable private Protocol protocol; @Nullable public final Request getRequest$okhttp() { return this.request; } public final void setRequest$okhttp(@Nullable Request <set-?>) { this.request = <set-?>; } @Nullable public final Protocol getProtocol$okhttp() { return this.protocol; } public final void setProtocol$okhttp(@Nullable Protocol <set-?>) { this.protocol = <set-?>; } private int code = -1; @Nullable private String message; @Nullable private Handshake handshake; @NotNull private Headers.Builder headers; @Nullable private ResponseBody body; @Nullable private Response networkResponse; @Nullable private Response cacheResponse; @Nullable private Response priorResponse; private long sentRequestAtMillis; private long receivedResponseAtMillis; @Nullable private Exchange exchange; public final int getCode$okhttp() { return this.code; } public final void setCode$okhttp(int <set-?>) { this.code = <set-?>; } @Nullable public final String getMessage$okhttp() { return this.message; } public final void setMessage$okhttp(@Nullable String <set-?>) { this.message = <set-?>; } @Nullable public final Handshake getHandshake$okhttp() { return this.handshake; } public final void setHandshake$okhttp(@Nullable Handshake <set-?>) { this.handshake = <set-?>; } @NotNull public final Headers.Builder getHeaders$okhttp() { return this.headers; } public final void setHeaders$okhttp(@NotNull Headers.Builder <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.headers = <set-?>; } @Nullable public final ResponseBody getBody$okhttp() { return this.body; } private final void checkSupportResponse(String name, Response response) { Response response1 = response; boolean bool1 = false, bool2 = false; Response $this$apply = response1; int $i$a$-apply-Response$Builder$checkSupportResponse$1 = 0; boolean bool3 = ($this$apply.body() == null) ? true : false, bool4 = false, bool5 = false; if (!bool3) { int $i$a$-require-Response$Builder$checkSupportResponse$1$1 = 0; String str = name + ".body != null"; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool3 = ($this$apply.networkResponse() == null) ? true : false; bool4 = false; bool5 = false; if (!bool3) { int $i$a$-require-Response$Builder$checkSupportResponse$1$2 = 0; String str = name + ".networkResponse != null"; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool3 = ($this$apply.cacheResponse() == null) ? true : false; bool4 = false; bool5 = false; if (!bool3) { int $i$a$-require-Response$Builder$checkSupportResponse$1$3 = 0; String str = name + ".cacheResponse != null"; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool3 = ($this$apply.priorResponse() == null) ? true : false; bool4 = false; bool5 = false; if (!bool3) { int $i$a$-require-Response$Builder$checkSupportResponse$1$4 = 0; String str = name + ".priorResponse != null"; throw (Throwable)new IllegalArgumentException(str.toString()); }  } public final void setBody$okhttp(@Nullable ResponseBody <set-?>) { this.body = <set-?>; } @Nullable public final Response getNetworkResponse$okhttp() { return this.networkResponse; } public final void setNetworkResponse$okhttp(@Nullable Response <set-?>) { this.networkResponse = <set-?>; } @Nullable public final Response getCacheResponse$okhttp() { return this.cacheResponse; } public final void setCacheResponse$okhttp(@Nullable Response <set-?>) { this.cacheResponse = <set-?>; } @Nullable public final Response getPriorResponse$okhttp() { return this.priorResponse; } public final void setPriorResponse$okhttp(@Nullable Response <set-?>) { this.priorResponse = <set-?>; } public final long getSentRequestAtMillis$okhttp() { return this.sentRequestAtMillis; } public final void setSentRequestAtMillis$okhttp(long <set-?>) { this.sentRequestAtMillis = <set-?>; } public final long getReceivedResponseAtMillis$okhttp() { return this.receivedResponseAtMillis; } public final void setReceivedResponseAtMillis$okhttp(long <set-?>) { this.receivedResponseAtMillis = <set-?>; } @Nullable public final Exchange getExchange$okhttp() { return this.exchange; } public final void setExchange$okhttp(@Nullable Exchange <set-?>) { this.exchange = <set-?>; } public Builder() { this.headers = new Headers.Builder(); } public Builder(@NotNull Response response) { this.request = response.request(); this.protocol = response.protocol(); this.code = response.code(); this.message = response.message(); this.handshake = response.handshake(); this.headers = response.headers().newBuilder(); this.body = response.body(); this.networkResponse = response.networkResponse(); this.cacheResponse = response.cacheResponse(); this.priorResponse = response.priorResponse(); this.sentRequestAtMillis = response.sentRequestAtMillis(); this.receivedResponseAtMillis = response.receivedResponseAtMillis(); this.exchange = response.exchange(); } @NotNull public Builder request(@NotNull Request request) { Intrinsics.checkNotNullParameter(request, "request"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$request$1 = 0; $this$apply.request = request; return builder1; } @NotNull public Builder protocol(@NotNull Protocol protocol) { Intrinsics.checkNotNullParameter(protocol, "protocol"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$protocol$1 = 0; $this$apply.protocol = protocol; return builder1; } @NotNull public Builder code(int code) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$code$1 = 0; $this$apply.code = code; return builder1; } @NotNull public Builder message(@NotNull String message) { Intrinsics.checkNotNullParameter(message, "message"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$message$1 = 0; $this$apply.message = message; return builder1; } @NotNull public Builder handshake(@Nullable Handshake handshake) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$handshake$1 = 0; $this$apply.handshake = handshake; return builder1; } @NotNull public Builder header(@NotNull String name, @NotNull String value) { Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(value, "value"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$header$1 = 0; $this$apply.headers.set(name, value); return builder1; } @NotNull public Builder addHeader(@NotNull String name, @NotNull String value) { Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(value, "value"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$addHeader$1 = 0; $this$apply.headers.add(name, value); return builder1; } @NotNull public Builder removeHeader(@NotNull String name) { Intrinsics.checkNotNullParameter(name, "name"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$removeHeader$1 = 0; $this$apply.headers.removeAll(name); return builder1; } @NotNull public Builder headers(@NotNull Headers headers) { Intrinsics.checkNotNullParameter(headers, "headers"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$headers$1 = 0; $this$apply.headers = headers.newBuilder(); return builder1; } @NotNull public Builder body(@Nullable ResponseBody body) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$body$1 = 0; $this$apply.body = body; return builder1; } @NotNull public Builder networkResponse(@Nullable Response networkResponse) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$networkResponse$1 = 0; $this$apply.checkSupportResponse("networkResponse", networkResponse); $this$apply.networkResponse = networkResponse; return builder1; } @NotNull public Builder cacheResponse(@Nullable Response cacheResponse) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$cacheResponse$1 = 0; $this$apply.checkSupportResponse("cacheResponse", cacheResponse); $this$apply.cacheResponse = cacheResponse; return builder1; } @NotNull public Builder priorResponse(@Nullable Response priorResponse) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$priorResponse$1 = 0; $this$apply.checkPriorResponse(priorResponse); $this$apply.priorResponse = priorResponse; return builder1; } private final void checkPriorResponse(Response response) { Response response1 = response; boolean bool1 = false, bool2 = false; Response $this$apply = response1; int $i$a$-apply-Response$Builder$checkPriorResponse$1 = 0; boolean bool3 = ($this$apply.body() == null) ? true : false, bool4 = false, bool5 = false; if (!bool3) { int $i$a$-require-Response$Builder$checkPriorResponse$1$1 = 0; String str = "priorResponse.body != null"; throw (Throwable)new IllegalArgumentException(str.toString()); }  } @NotNull public Builder sentRequestAtMillis(long sentRequestAtMillis) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$sentRequestAtMillis$1 = 0; $this$apply.sentRequestAtMillis = sentRequestAtMillis; return builder1; } @NotNull public Builder receivedResponseAtMillis(long receivedResponseAtMillis) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Response$Builder$receivedResponseAtMillis$1 = 0; $this$apply.receivedResponseAtMillis = receivedResponseAtMillis; return builder1; } public final void initExchange$okhttp(@NotNull Exchange deferredTrailers) { Intrinsics.checkNotNullParameter(deferredTrailers, "deferredTrailers"); this.exchange = deferredTrailers; } @NotNull public Response build() { boolean bool1 = (this.code >= 0) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Response$Builder$build$1 = 0; String str = "code < 0: " + this.code; throw (Throwable)new IllegalStateException(str.toString()); }  Request request1 = this.request; bool2 = false; bool3 = false; if (request1 == null) { int $i$a$-checkNotNull-Response$Builder$build$2 = 0; String str4 = "request == null", str3 = str4; throw (Throwable)new IllegalStateException(str3.toString()); }  Protocol protocol1 = this.protocol; bool2 = false; bool3 = false; if (protocol1 == null) { Request request = request1; int $i$a$-checkNotNull-Response$Builder$build$3 = 0; String str4 = "protocol == null", str3 = str4; throw (Throwable)new IllegalStateException(str3.toString()); }  String str1 = this.message; bool2 = false; bool3 = false; if (str1 == null) { Protocol protocol = protocol1; Request request = request; int $i$a$-checkNotNull-Response$Builder$build$4 = 0;
/*     */         String str4 = "message == null", str3 = str4;
/*     */         throw (Throwable)new IllegalStateException(str3.toString()); }
/*     */       
/*     */       Exchange exchange = this.exchange;
/*     */       long l1 = this.receivedResponseAtMillis, l2 = this.sentRequestAtMillis;
/*     */       Response response1 = this.priorResponse, response2 = this.cacheResponse, response3 = this.networkResponse;
/*     */       ResponseBody responseBody = this.body;
/*     */       Headers headers = this.headers.build();
/*     */       Handshake handshake = this.handshake;
/*     */       int i = this.code;
/*     */       String str2 = str1;
/*     */       Protocol protocol2 = protocol;
/*     */       Request request2 = request;
/*     */       return new Response(request2, protocol2, str2, i, handshake, headers, responseBody, response3, response2, response1, l2, l1, exchange); }
/*     */      }
/*     */ 
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Response.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */