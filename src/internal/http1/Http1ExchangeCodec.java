/*     */ package okhttp3.internal.http1;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.net.ProtocolException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.HttpUrl;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.connection.RealConnection;
/*     */ import okhttp3.internal.http.ExchangeCodec;
/*     */ import okhttp3.internal.http.HttpHeaders;
/*     */ import okhttp3.internal.http.RequestLine;
/*     */ import okhttp3.internal.http.StatusLine;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import okio.BufferedSource;
/*     */ import okio.ForwardingTimeout;
/*     */ import okio.Sink;
/*     */ import okio.Source;
/*     */ import okio.Timeout;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\001\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\006\n\002\020\016\n\002\b\t\030\000 ?2\0020\001:\007<=>?@ABB'\022\b\020\002\032\004\030\0010\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t¢\006\002\020\nJ\b\020\033\032\0020\034H\026J\030\020\035\032\0020\0362\006\020\037\032\0020\0272\006\020 \032\0020!H\026J\020\020\"\032\0020\0342\006\020#\032\0020$H\002J\b\020%\032\0020\034H\026J\b\020&\032\0020\034H\026J\b\020'\032\0020\036H\002J\020\020(\032\0020)2\006\020*\032\0020+H\002J\020\020,\032\0020)2\006\020-\032\0020!H\002J\b\020.\032\0020\036H\002J\b\020/\032\0020)H\002J\020\0200\032\0020)2\006\0201\032\0020\031H\026J\022\0202\032\004\030\001032\006\0204\032\0020\020H\026J\020\0205\032\0020!2\006\0201\032\0020\031H\026J\016\0206\032\0020\0342\006\0201\032\0020\031J\b\020\024\032\0020\025H\026J\026\0207\032\0020\0342\006\0208\032\0020\0252\006\0209\032\0020:J\020\020;\032\0020\0342\006\020\037\032\0020\027H\026R\020\020\002\032\004\030\0010\003X\004¢\006\002\n\000R\024\020\004\032\0020\005X\004¢\006\b\n\000\032\004\b\013\020\fR\016\020\r\032\0020\016X\004¢\006\002\n\000R\021\020\017\032\0020\0208F¢\006\006\032\004\b\017\020\021R\016\020\b\032\0020\tX\004¢\006\002\n\000R\016\020\006\032\0020\007X\004¢\006\002\n\000R\016\020\022\032\0020\023X\016¢\006\002\n\000R\020\020\024\032\004\030\0010\025X\016¢\006\002\n\000R\030\020\026\032\0020\020*\0020\0278BX\004¢\006\006\032\004\b\026\020\030R\030\020\026\032\0020\020*\0020\0318BX\004¢\006\006\032\004\b\026\020\032¨\006C"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "connection", "Lokhttp3/internal/connection/RealConnection;", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokio/BufferedSource;Lokio/BufferedSink;)V", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "headersReader", "Lokhttp3/internal/http1/HeadersReader;", "isClosed", "", "()Z", "state", "", "trailers", "Lokhttp3/Headers;", "isChunked", "Lokhttp3/Request;", "(Lokhttp3/Request;)Z", "Lokhttp3/Response;", "(Lokhttp3/Response;)Z", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "contentLength", "", "detachTimeout", "timeout", "Lokio/ForwardingTimeout;", "finishRequest", "flushRequest", "newChunkedSink", "newChunkedSource", "Lokio/Source;", "url", "Lokhttp3/HttpUrl;", "newFixedLengthSource", "length", "newKnownLengthSink", "newUnknownLengthSource", "openResponseBodySource", "response", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "skipConnectBody", "writeRequest", "headers", "requestLine", "", "writeRequestHeaders", "AbstractSource", "ChunkedSink", "ChunkedSource", "Companion", "FixedLengthSource", "KnownLengthSink", "UnknownLengthSource", "okhttp"})
/*     */ public final class Http1ExchangeCodec
/*     */   implements ExchangeCodec
/*     */ {
/*     */   private int state;
/*     */   private final HeadersReader headersReader;
/*     */   private Headers trailers;
/*     */   private final OkHttpClient client;
/*     */   @NotNull
/*     */   private final RealConnection connection;
/*     */   private final BufferedSource source;
/*     */   private final BufferedSink sink;
/*     */   private static final long NO_CHUNK_YET = -1L;
/*     */   private static final int STATE_IDLE = 0;
/*     */   private static final int STATE_OPEN_REQUEST_BODY = 1;
/*     */   private static final int STATE_WRITING_REQUEST_BODY = 2;
/*     */   private static final int STATE_READ_RESPONSE_HEADERS = 3;
/*     */   private static final int STATE_OPEN_RESPONSE_BODY = 4;
/*     */   private static final int STATE_READING_RESPONSE_BODY = 5;
/*     */   private static final int STATE_CLOSED = 6;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public Http1ExchangeCodec(@Nullable OkHttpClient client, @NotNull RealConnection connection, @NotNull BufferedSource source, @NotNull BufferedSink sink) {
/*  64 */     this.client = client; this.connection = connection; this.source = source; this.sink = sink;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     this.headersReader = new HeadersReader(this.source);
/*     */   } @NotNull
/*     */   public RealConnection getConnection() { return this.connection; } private final boolean isChunked(Response $this$isChunked) {
/*  76 */     return StringsKt.equals("chunked", Response.header$default($this$isChunked, "Transfer-Encoding", null, 2, null), true);
/*     */   }
/*     */   private final boolean isChunked(Request $this$isChunked) {
/*  79 */     return StringsKt.equals("chunked", $this$isChunked.header("Transfer-Encoding"), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isClosed() {
/*  89 */     return (this.state == 6);
/*     */   } @NotNull
/*     */   public Sink createRequestBody(@NotNull Request request, long contentLength) {
/*  92 */     Intrinsics.checkNotNullParameter(request, "request");
/*  93 */     if (request.body() != null && request.body().isDuplex()) throw (Throwable)new ProtocolException(
/*  94 */           "Duplex connections are not supported for HTTP/1");
/*     */     
/*  96 */     if (contentLength != -1L) {  }
/*     */     else
/*  98 */     { throw (Throwable)new IllegalStateException(
/*  99 */           "Cannot stream a request body without chunked encoding or a known content length!"); }
/*     */     
/*     */     return isChunked(request) ? newChunkedSink() : (Sink)"JD-Core does not support Kotlin";
/*     */   }
/*     */   public void cancel() {
/* 104 */     getConnection().cancel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeRequestHeaders(@NotNull Request request) {
/* 118 */     Intrinsics.checkNotNullParameter(request, "request"); Intrinsics.checkNotNullExpressionValue(getConnection().route().proxy().type(), "connection.route().proxy.type()"); String requestLine = RequestLine.INSTANCE.get(request, getConnection().route().proxy().type());
/* 119 */     writeRequest(request.headers(), requestLine);
/*     */   }
/*     */   
/*     */   public long reportedContentLength(@NotNull Response response) {
/* 123 */     Intrinsics.checkNotNullParameter(response, "response"); return 
/* 124 */       !HttpHeaders.promisesBody(response) ? 0L : (
/* 125 */       isChunked(response) ? -1L : 
/* 126 */       Util.headersContentLength(response));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Source openResponseBodySource(@NotNull Response response) {
/* 131 */     Intrinsics.checkNotNullParameter(response, "response");
/*     */ 
/*     */ 
/*     */     
/* 135 */     long contentLength = Util.headersContentLength(response);
/* 136 */     return !HttpHeaders.promisesBody(response) ? newFixedLengthSource(0L) : (isChunked(response) ? newChunkedSource(response.request().url()) : ((contentLength != -1L) ? 
/* 137 */       newFixedLengthSource(contentLength) : 
/*     */       
/* 139 */       newUnknownLengthSource()));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Headers trailers()
/*     */   {
/* 146 */     boolean bool1 = (this.state == 6) ? true : false, bool2 = false, bool3 = false; if (!bool1)
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 498 */       int $i$a$-check-Http1ExchangeCodec$trailers$1 = 0; String str = "too early; can't read the trailers yet"; throw (Throwable)new IllegalStateException(str.toString()); }  if (this.trailers == null); return Util.EMPTY_HEADERS; } public void flushRequest() { this.sink.flush(); } public void finishRequest() { this.sink.flush(); } public final void writeRequest(@NotNull Headers headers, @NotNull String requestLine) { Intrinsics.checkNotNullParameter(headers, "headers"); Intrinsics.checkNotNullParameter(requestLine, "requestLine"); byte b = (this.state == 0) ? 1 : 0; int i = 0; boolean bool = false; if (!b) { int $i$a$-check-Http1ExchangeCodec$writeRequest$1 = 0; String str = "state: " + this.state; throw (Throwable)new IllegalStateException(str.toString()); }  this.sink.writeUtf8(requestLine).writeUtf8("\r\n"); for (b = 0, i = headers.size(); b < i; b++) this.sink.writeUtf8(headers.name(b)).writeUtf8(": ").writeUtf8(headers.value(b)).writeUtf8("\r\n");  this.sink.writeUtf8("\r\n"); this.state = 1; } @Nullable public Response.Builder readResponseHeaders(boolean expectContinue) { boolean bool1 = (this.state == 1 || this.state == 3) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$readResponseHeaders$1 = 0; String str = "state: " + this.state; throw (Throwable)new IllegalStateException(str.toString()); }  try { StatusLine statusLine = StatusLine.Companion.parse(this.headersReader.readLine()); Response.Builder responseBuilder = (new Response.Builder()).protocol(statusLine.protocol).code(statusLine.code).message(statusLine.message).headers(this.headersReader.readHeaders()); this.state = 3; this.state = 4; return (expectContinue && statusLine.code == 100) ? null : ((statusLine.code == 100) ? responseBuilder : responseBuilder); } catch (EOFException e) { String address = getConnection().route().address().url().redact(); throw (Throwable)new IOException("unexpected end of stream on " + address, (Throwable)e); }  } private final Sink newChunkedSink() { boolean bool1 = (this.state == 1) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$newChunkedSink$1 = 0; String str = "state: " + this.state; throw (Throwable)new IllegalStateException(str.toString()); }  this.state = 2; return new ChunkedSink(); } private final Sink newKnownLengthSink() { boolean bool1 = (this.state == 1) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$newKnownLengthSink$1 = 0; String str = "state: " + this.state; throw (Throwable)new IllegalStateException(str.toString()); }  this.state = 2; return new KnownLengthSink(); } private final Source newFixedLengthSource(long length) { boolean bool1 = (this.state == 4) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$newFixedLengthSource$1 = 0; String str = "state: " + this.state; throw (Throwable)new IllegalStateException(str.toString()); }  this.state = 5; return new FixedLengthSource(length); } private final Source newChunkedSource(HttpUrl url) { boolean bool1 = (this.state == 4) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$newChunkedSource$1 = 0; String str = "state: " + this.state; throw (Throwable)new IllegalStateException(str.toString()); }  this.state = 5; return new ChunkedSource(url); } private final Source newUnknownLengthSource() { boolean bool1 = (this.state == 4) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$newUnknownLengthSource$1 = 0; String str = "state: " + this.state; throw (Throwable)new IllegalStateException(str.toString()); }  this.state = 5; getConnection().noNewExchanges$okhttp(); return new UnknownLengthSource(); } private final void detachTimeout(ForwardingTimeout timeout) { Timeout oldDelegate = timeout.delegate(); timeout.setDelegate(Timeout.NONE); oldDelegate.clearDeadline(); oldDelegate.clearTimeout(); } public final void skipConnectBody(@NotNull Response response) { Intrinsics.checkNotNullParameter(response, "response"); long contentLength = Util.headersContentLength(response); if (contentLength == -1L) return;  Source body = newFixedLengthSource(contentLength); Util.skipAll(body, 2147483647, TimeUnit.MILLISECONDS); body.close(); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\b\004\030\0002\0020\001B\005¢\006\002\020\002J\b\020\007\032\0020\bH\026J\b\020\t\032\0020\bH\026J\b\020\005\032\0020\nH\026J\030\020\013\032\0020\b2\006\020\f\032\0020\r2\006\020\016\032\0020\017H\026R\016\020\003\032\0020\004X\016¢\006\002\n\000R\016\020\005\032\0020\006X\004¢\006\002\n\000¨\006\020"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", "flush", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"}) private final class KnownLengthSink implements Sink { private final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout()); private boolean closed; @NotNull public Timeout timeout() { return (Timeout)this.timeout; } public void write(@NotNull Buffer source, long byteCount) { Intrinsics.checkNotNullParameter(source, "source"); boolean bool1 = !this.closed ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$KnownLengthSink$write$1 = 0; String str = "closed"; throw (Throwable)new IllegalStateException(str.toString()); }  Util.checkOffsetAndCount(source.size(), 0L, byteCount); Http1ExchangeCodec.this.sink.write(source, byteCount); } public void flush() { if (this.closed) return;  Http1ExchangeCodec.this.sink.flush(); } public void close() { if (this.closed) return;  this.closed = true; Http1ExchangeCodec.this.detachTimeout(this.timeout); Http1ExchangeCodec.this.state = 3; } } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\b\004\030\0002\0020\001B\005¢\006\002\020\002J\b\020\007\032\0020\bH\026J\b\020\t\032\0020\bH\026J\b\020\005\032\0020\nH\026J\030\020\013\032\0020\b2\006\020\f\032\0020\r2\006\020\016\032\0020\017H\026R\016\020\003\032\0020\004X\016¢\006\002\n\000R\016\020\005\032\0020\006X\004¢\006\002\n\000¨\006\020"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", "flush", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"}) private final class ChunkedSink implements Sink { private final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout()); private boolean closed; @NotNull public Timeout timeout() { return (Timeout)this.timeout; } public synchronized void flush() { if (this.closed) return;  Http1ExchangeCodec.this.sink.flush(); } public void write(@NotNull Buffer source, long byteCount) { Intrinsics.checkNotNullParameter(source, "source"); boolean bool1 = !this.closed ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$ChunkedSink$write$1 = 0; String str = "closed"; throw (Throwable)new IllegalStateException(str.toString()); }  if (byteCount == 0L) return;  Http1ExchangeCodec.this.sink.writeHexadecimalUnsignedLong(byteCount); Http1ExchangeCodec.this.sink.writeUtf8("\r\n"); Http1ExchangeCodec.this.sink.write(source, byteCount); Http1ExchangeCodec.this.sink.writeUtf8("\r\n"); } public synchronized void close() { if (this.closed) return;  this.closed = true; Http1ExchangeCodec.this.sink.writeUtf8("0\r\n\r\n"); Http1ExchangeCodec.this.detachTimeout(this.timeout); Http1ExchangeCodec.this.state = 3; } } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\003\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\030\002\n\000\b¢\004\030\0002\0020\001B\005¢\006\002\020\002J\030\020\r\032\0020\0162\006\020\017\032\0020\0202\006\020\021\032\0020\016H\026J\006\020\022\032\0020\023J\b\020\t\032\0020\024H\026R\032\020\003\032\0020\004X\016¢\006\016\n\000\032\004\b\005\020\006\"\004\b\007\020\bR\024\020\t\032\0020\nX\004¢\006\b\n\000\032\004\b\013\020\f¨\006\025"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokio/Source;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "timeout", "Lokio/ForwardingTimeout;", "getTimeout", "()Lokio/ForwardingTimeout;", "read", "", "sink", "Lokio/Buffer;", "byteCount", "responseBodyComplete", "", "Lokio/Timeout;", "okhttp"}) private abstract class AbstractSource implements Source { @NotNull private final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.source.timeout()); private boolean closed; @NotNull protected final ForwardingTimeout getTimeout() { return this.timeout; } protected final boolean getClosed() { return this.closed; } protected final void setClosed(boolean <set-?>) { this.closed = <set-?>; } @NotNull public Timeout timeout() { return (Timeout)this.timeout; } public long read(@NotNull Buffer sink, long byteCount) { long l; Intrinsics.checkNotNullParameter(sink, "sink"); try { l = Http1ExchangeCodec.this.source.read(sink, byteCount); } catch (IOException e) { Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp(); responseBodyComplete(); throw (Throwable)e; }  return l; } public final void responseBodyComplete() { if (Http1ExchangeCodec.this.state == 6) return;  if (Http1ExchangeCodec.this.state != 5) throw (Throwable)new IllegalStateException("state: " + Http1ExchangeCodec.this.state);  Http1ExchangeCodec.this.detachTimeout(this.timeout); Http1ExchangeCodec.this.state = 6; } } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000&\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\002\b\004\030\0002\0060\001R\0020\002B\r\022\006\020\003\032\0020\004¢\006\002\020\005J\b\020\006\032\0020\007H\026J\030\020\b\032\0020\0042\006\020\t\032\0020\n2\006\020\013\032\0020\004H\026R\016\020\003\032\0020\004X\016¢\006\002\n\000¨\006\f"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "bytesRemaining", "", "(Lokhttp3/internal/http1/Http1ExchangeCodec;J)V", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}) private final class FixedLengthSource extends AbstractSource { private long bytesRemaining; public FixedLengthSource(long bytesRemaining) { this.bytesRemaining = bytesRemaining; if (this.bytesRemaining == 0L) responseBodyComplete();  } public long read(@NotNull Buffer sink, long byteCount) { Intrinsics.checkNotNullParameter(sink, "sink"); boolean bool1 = (byteCount >= 0L) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-require-Http1ExchangeCodec$FixedLengthSource$read$1 = 0; String str = "byteCount < 0: " + byteCount; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool1 = !getClosed() ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$FixedLengthSource$read$2 = 0; String str = "closed"; throw (Throwable)new IllegalStateException(str.toString()); }  if (this.bytesRemaining == 0L) return -1L;  long l1 = this.bytesRemaining; boolean bool4 = false; long read = super.read(sink, Math.min(l1, byteCount)); if (read == -1L) { Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp(); ProtocolException e = new ProtocolException("unexpected end of stream"); responseBodyComplete(); throw (Throwable)e; }  this.bytesRemaining -= read; if (this.bytesRemaining == 0L) responseBodyComplete();  return read; } public void close() { if (getClosed()) return;  if (this.bytesRemaining != 0L && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) { Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp(); responseBodyComplete(); }  setClosed(true); } } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\020\013\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\003\b\004\030\0002\0060\001R\0020\002B\r\022\006\020\003\032\0020\004¢\006\002\020\005J\b\020\n\032\0020\013H\026J\030\020\f\032\0020\0072\006\020\r\032\0020\0162\006\020\017\032\0020\007H\026J\b\020\020\032\0020\013H\002R\016\020\006\032\0020\007X\016¢\006\002\n\000R\016\020\b\032\0020\tX\016¢\006\002\n\000R\016\020\003\032\0020\004X\004¢\006\002\n\000¨\006\021"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "url", "Lokhttp3/HttpUrl;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "bytesRemainingInChunk", "", "hasMoreChunks", "", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "readChunkSize", "okhttp"}) private final class ChunkedSource extends AbstractSource { private long bytesRemainingInChunk; private boolean hasMoreChunks; private final HttpUrl url; public ChunkedSource(HttpUrl url) { this.url = url; this.bytesRemainingInChunk = -1L; this.hasMoreChunks = true; } public long read(@NotNull Buffer sink, long byteCount) { Intrinsics.checkNotNullParameter(sink, "sink"); boolean bool1 = (byteCount >= 0L) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-require-Http1ExchangeCodec$ChunkedSource$read$1 = 0; String str = "byteCount < 0: " + byteCount; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool1 = !getClosed() ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$ChunkedSource$read$2 = 0; String str = "closed"; throw (Throwable)new IllegalStateException(str.toString()); }  if (!this.hasMoreChunks) return -1L;  if (this.bytesRemainingInChunk == 0L || this.bytesRemainingInChunk == -1L) { readChunkSize(); if (!this.hasMoreChunks) return -1L;  }  long l1 = this.bytesRemainingInChunk; boolean bool4 = false; long read = super.read(sink, Math.min(byteCount, l1)); if (read == -1L) { Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp(); ProtocolException e = new ProtocolException("unexpected end of stream"); responseBodyComplete(); throw (Throwable)e; }  this.bytesRemainingInChunk -= read; return read; } private final void readChunkSize() { if (this.bytesRemainingInChunk != -1L) Http1ExchangeCodec.this.source.readUtf8LineStrict();  try { this.bytesRemainingInChunk = Http1ExchangeCodec.this.source.readHexadecimalUnsignedLong(); String str1 = Http1ExchangeCodec.this.source.readUtf8LineStrict(); boolean bool = false; if (str1 == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");  String extensions = StringsKt.trim(str1).toString(); if (this.bytesRemainingInChunk >= 0L) { CharSequence charSequence = extensions; bool = false; if (!((charSequence.length() > 0) ? 1 : 0) || StringsKt.startsWith$default(extensions, ";", false, 2, null)) { if (this.bytesRemainingInChunk == 0L) { this.hasMoreChunks = false; Http1ExchangeCodec.this.trailers = Http1ExchangeCodec.this.headersReader.readHeaders(); Intrinsics.checkNotNull(Http1ExchangeCodec.this.client); Intrinsics.checkNotNull(Http1ExchangeCodec.this.trailers); HttpHeaders.receiveHeaders(Http1ExchangeCodec.this.client.cookieJar(), this.url, Http1ExchangeCodec.this.trailers); responseBodyComplete(); }  return; }  }  throw (Throwable)new ProtocolException("expected chunk size and optional extensions" + " but was \"" + this.bytesRemainingInChunk + extensions + '"'); } catch (NumberFormatException e) { throw (Throwable)new ProtocolException(e.getMessage()); }  } public void close() { if (getClosed()) return;  if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) { Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp(); responseBodyComplete(); }  setClosed(true); } } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\020\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\b\004\030\0002\0060\001R\0020\002B\005¢\006\002\020\003J\b\020\006\032\0020\007H\026J\030\020\b\032\0020\t2\006\020\n\032\0020\0132\006\020\f\032\0020\tH\026R\016\020\004\032\0020\005X\016¢\006\002\n\000¨\006\r"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "inputExhausted", "", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}) private final class UnknownLengthSource extends AbstractSource { private boolean inputExhausted; public long read(@NotNull Buffer sink, long byteCount) { Intrinsics.checkNotNullParameter(sink, "sink"); boolean bool1 = (byteCount >= 0L) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-require-Http1ExchangeCodec$UnknownLengthSource$read$1 = 0; String str = "byteCount < 0: " + byteCount; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool1 = !getClosed() ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-check-Http1ExchangeCodec$UnknownLengthSource$read$2 = 0;
/*     */         String str = "closed";
/*     */         throw (Throwable)new IllegalStateException(str.toString()); }
/*     */       
/*     */       if (this.inputExhausted)
/*     */         return -1L; 
/*     */       long read = super.read(sink, byteCount);
/*     */       if (read == -1L) {
/*     */         this.inputExhausted = true;
/*     */         responseBodyComplete();
/*     */         return -1L;
/*     */       } 
/*     */       return read; }
/*     */ 
/*     */     
/*     */     public void close() {
/*     */       if (getClosed())
/*     */         return; 
/*     */       if (!this.inputExhausted)
/*     */         responseBodyComplete(); 
/*     */       setClosed(true);
/*     */     } }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\000\n\002\020\b\n\002\b\007\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\006XT¢\006\002\n\000R\016\020\007\032\0020\006XT¢\006\002\n\000R\016\020\b\032\0020\006XT¢\006\002\n\000R\016\020\t\032\0020\006XT¢\006\002\n\000R\016\020\n\032\0020\006XT¢\006\002\n\000R\016\020\013\032\0020\006XT¢\006\002\n\000R\016\020\f\032\0020\006XT¢\006\002\n\000¨\006\r"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$Companion;", "", "()V", "NO_CHUNK_YET", "", "STATE_CLOSED", "", "STATE_IDLE", "STATE_OPEN_REQUEST_BODY", "STATE_OPEN_RESPONSE_BODY", "STATE_READING_RESPONSE_BODY", "STATE_READ_RESPONSE_HEADERS", "STATE_WRITING_REQUEST_BODY", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http1/Http1ExchangeCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */