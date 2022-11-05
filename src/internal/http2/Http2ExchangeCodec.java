/*     */ package okhttp3.internal.http2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ProtocolException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.Protocol;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.connection.RealConnection;
/*     */ import okhttp3.internal.http.ExchangeCodec;
/*     */ import okhttp3.internal.http.HttpHeaders;
/*     */ import okhttp3.internal.http.RealInterceptorChain;
/*     */ import okhttp3.internal.http.RequestLine;
/*     */ import okhttp3.internal.http.StatusLine;
/*     */ import okio.Sink;
/*     */ import okio.Source;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000n\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\030\000 (2\0020\001:\001(B%\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t¢\006\002\020\nJ\b\020\023\032\0020\024H\026J\030\020\025\032\0020\0262\006\020\027\032\0020\0302\006\020\031\032\0020\032H\026J\b\020\033\032\0020\024H\026J\b\020\034\032\0020\024H\026J\020\020\035\032\0020\0362\006\020\037\032\0020 H\026J\022\020!\032\004\030\0010\"2\006\020#\032\0020\fH\026J\020\020$\032\0020\0322\006\020\037\032\0020 H\026J\b\020%\032\0020&H\026J\020\020'\032\0020\0242\006\020\027\032\0020\030H\026R\016\020\013\032\0020\fX\016¢\006\002\n\000R\016\020\006\032\0020\007X\004¢\006\002\n\000R\024\020\004\032\0020\005X\004¢\006\b\n\000\032\004\b\r\020\016R\016\020\b\032\0020\tX\004¢\006\002\n\000R\016\020\017\032\0020\020X\004¢\006\002\n\000R\020\020\021\032\004\030\0010\022X\016¢\006\002\n\000¨\006)"}, d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "connection", "Lokhttp3/internal/connection/RealConnection;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokhttp3/internal/http/RealInterceptorChain;Lokhttp3/internal/http2/Http2Connection;)V", "canceled", "", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "protocol", "Lokhttp3/Protocol;", "stream", "Lokhttp3/internal/http2/Http2Stream;", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "contentLength", "", "finishRequest", "flushRequest", "openResponseBodySource", "Lokio/Source;", "response", "Lokhttp3/Response;", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "trailers", "Lokhttp3/Headers;", "writeRequestHeaders", "Companion", "okhttp"})
/*     */ public final class Http2ExchangeCodec implements ExchangeCodec {
/*     */   private volatile Http2Stream stream;
/*     */   private final Protocol protocol;
/*     */   private volatile boolean canceled;
/*     */   @NotNull
/*     */   private final RealConnection connection;
/*     */   private final RealInterceptorChain chain;
/*     */   private final Http2Connection http2Connection;
/*     */   private static final String CONNECTION = "connection";
/*     */   private static final String HOST = "host";
/*     */   private static final String KEEP_ALIVE = "keep-alive";
/*     */   private static final String PROXY_CONNECTION = "proxy-connection";
/*     */   private static final String TRANSFER_ENCODING = "transfer-encoding";
/*     */   private static final String TE = "te";
/*     */   private static final String ENCODING = "encoding";
/*     */   private static final String UPGRADE = "upgrade";
/*     */   private static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS;
/*     */   private static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS;
/*     */   
/*     */   public Http2ExchangeCodec(@NotNull OkHttpClient client, @NotNull RealConnection connection, @NotNull RealInterceptorChain chain, @NotNull Http2Connection http2Connection) {
/*  50 */     this.connection = connection; this.chain = chain; this.http2Connection = http2Connection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.protocol = client.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? 
/*  59 */       Protocol.H2_PRIOR_KNOWLEDGE : 
/*     */       
/*  61 */       Protocol.HTTP_2;
/*     */   } @NotNull
/*     */   public RealConnection getConnection() {
/*     */     return this.connection;
/*     */   }
/*     */   @NotNull
/*     */   public Sink createRequestBody(@NotNull Request request, long contentLength) {
/*  68 */     Intrinsics.checkNotNullParameter(request, "request"); Intrinsics.checkNotNull(this.stream); return this.stream.getSink();
/*     */   }
/*     */   
/*     */   public void writeRequestHeaders(@NotNull Request request) {
/*  72 */     Intrinsics.checkNotNullParameter(request, "request"); if (this.stream != null)
/*     */       return; 
/*  74 */     boolean hasRequestBody = (request.body() != null);
/*  75 */     List<Header> requestHeaders = Companion.http2HeadersList(request);
/*  76 */     this.stream = this.http2Connection.newStream(requestHeaders, hasRequestBody);
/*     */ 
/*     */     
/*  79 */     if (this.canceled) {
/*  80 */       Intrinsics.checkNotNull(this.stream); this.stream.closeLater(ErrorCode.CANCEL);
/*  81 */       throw (Throwable)new IOException("Canceled");
/*     */     } 
/*  83 */     Intrinsics.checkNotNull(this.stream); this.stream.readTimeout().timeout(this.chain.getReadTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
/*  84 */     Intrinsics.checkNotNull(this.stream); this.stream.writeTimeout().timeout(this.chain.getWriteTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
/*     */   }
/*     */   
/*     */   public void flushRequest() {
/*  88 */     this.http2Connection.flush();
/*     */   }
/*     */   
/*     */   public void finishRequest() {
/*  92 */     Intrinsics.checkNotNull(this.stream); this.stream.getSink().close();
/*     */   }
/*     */   @Nullable
/*     */   public Response.Builder readResponseHeaders(boolean expectContinue) {
/*  96 */     Intrinsics.checkNotNull(this.stream); Headers headers = this.stream.takeHeaders();
/*  97 */     Response.Builder responseBuilder = Companion.readHttp2HeadersList(headers, this.protocol);
/*  98 */     return (expectContinue && responseBuilder.getCode$okhttp() == 100) ? 
/*  99 */       null : 
/*     */       
/* 101 */       responseBuilder;
/*     */   }
/*     */ 
/*     */   
/*     */   public long reportedContentLength(@NotNull Response response) {
/* 106 */     Intrinsics.checkNotNullParameter(response, "response"); return 
/* 107 */       !HttpHeaders.promisesBody(response) ? 0L : 
/* 108 */       Util.headersContentLength(response);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Source openResponseBodySource(@NotNull Response response) {
/* 113 */     Intrinsics.checkNotNullParameter(response, "response"); Intrinsics.checkNotNull(this.stream); return this.stream.getSource$okhttp();
/*     */   }
/*     */   @NotNull
/*     */   public Headers trailers() {
/* 117 */     Intrinsics.checkNotNull(this.stream); return this.stream.trailers();
/*     */   }
/*     */   
/*     */   public void cancel() {
/* 121 */     this.canceled = true;
/* 122 */     if (this.stream != null) { this.stream.closeLater(ErrorCode.CANCEL); }
/*     */     else
/*     */     {  }
/*     */   
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
/* 136 */   public static final Companion Companion = new Companion(null); static { HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableListOf((Object[])new String[] { 
/* 137 */           "connection", 
/* 138 */           "host", 
/* 139 */           "keep-alive", 
/* 140 */           "proxy-connection", 
/* 141 */           "te", 
/* 142 */           "transfer-encoding", 
/* 143 */           "encoding", 
/* 144 */           "upgrade", 
/* 145 */           ":method", 
/* 146 */           ":path", 
/* 147 */           ":scheme", 
/* 148 */           ":authority" });
/* 149 */     HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableListOf((Object[])new String[] {
/* 150 */           "connection", 
/* 151 */           "host", 
/* 152 */           "keep-alive", 
/* 153 */           "proxy-connection", 
/* 154 */           "te", 
/* 155 */           "transfer-encoding", 
/* 156 */           "encoding", 
/* 157 */           "upgrade" }); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000:\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\003\n\002\020 \n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\024\020\017\032\b\022\004\022\0020\0200\b2\006\020\021\032\0020\022J\026\020\023\032\0020\0242\006\020\025\032\0020\0262\006\020\027\032\0020\030R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\016\020\006\032\0020\004XT¢\006\002\n\000R\024\020\007\032\b\022\004\022\0020\0040\bX\004¢\006\002\n\000R\024\020\t\032\b\022\004\022\0020\0040\bX\004¢\006\002\n\000R\016\020\n\032\0020\004XT¢\006\002\n\000R\016\020\013\032\0020\004XT¢\006\002\n\000R\016\020\f\032\0020\004XT¢\006\002\n\000R\016\020\r\032\0020\004XT¢\006\002\n\000R\016\020\016\032\0020\004XT¢\006\002\n\000¨\006\031"}, d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec$Companion;", "", "()V", "CONNECTION", "", "ENCODING", "HOST", "HTTP_2_SKIPPED_REQUEST_HEADERS", "", "HTTP_2_SKIPPED_RESPONSE_HEADERS", "KEEP_ALIVE", "PROXY_CONNECTION", "TE", "TRANSFER_ENCODING", "UPGRADE", "http2HeadersList", "Lokhttp3/internal/http2/Header;", "request", "Lokhttp3/Request;", "readHttp2HeadersList", "Lokhttp3/Response$Builder;", "headerBlock", "Lokhttp3/Headers;", "protocol", "Lokhttp3/Protocol;", "okhttp"})
/*     */   public static final class Companion { @NotNull
/*     */     public final List<Header> http2HeadersList(@NotNull Request request) {
/* 160 */       Intrinsics.checkNotNullParameter(request, "request"); Headers headers = request.headers();
/* 161 */       ArrayList<Header> result = new ArrayList(headers.size() + 4);
/* 162 */       result.add(new Header(Header.TARGET_METHOD, request.method()));
/* 163 */       result.add(new Header(Header.TARGET_PATH, RequestLine.INSTANCE.requestPath(request.url())));
/* 164 */       String host = request.header("Host");
/* 165 */       if (host != null) {
/* 166 */         result.add(new Header(Header.TARGET_AUTHORITY, host));
/*     */       }
/* 168 */       result.add(new Header(Header.TARGET_SCHEME, request.url().scheme())); byte b;
/*     */       int i;
/* 170 */       for (b = 0, i = headers.size(); b < i; b++) {
/*     */         
/* 172 */         String str1 = headers.name(b); Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US"); Locale locale = Locale.US; boolean bool = false; if (str1 == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(locale), "(this as java.lang.String).toLowerCase(locale)"); String name = str1.toLowerCase(locale);
/* 173 */         if (!Http2ExchangeCodec.HTTP_2_SKIPPED_REQUEST_HEADERS.contains(name) || (Intrinsics.areEqual(name, "te") && 
/* 174 */           Intrinsics.areEqual(headers.value(b), "trailers"))) {
/* 175 */           result.add(new Header(name, headers.value(b)));
/*     */         }
/*     */       } 
/* 178 */       return result;
/*     */     }
/*     */     private Companion() {}
/*     */     @NotNull
/*     */     public final Response.Builder readHttp2HeadersList(@NotNull Headers headerBlock, @NotNull Protocol protocol) {
/* 183 */       Intrinsics.checkNotNullParameter(headerBlock, "headerBlock"); Intrinsics.checkNotNullParameter(protocol, "protocol"); StatusLine statusLine = (StatusLine)null;
/* 184 */       Headers.Builder headersBuilder = new Headers.Builder(); byte b; int i;
/* 185 */       for (b = 0, i = headerBlock.size(); b < i; b++) {
/* 186 */         String name = headerBlock.name(b);
/* 187 */         String value = headerBlock.value(b);
/* 188 */         if (Intrinsics.areEqual(name, ":status")) {
/* 189 */           statusLine = StatusLine.Companion.parse("HTTP/1.1 " + value);
/* 190 */         } else if (!Http2ExchangeCodec.HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(name)) {
/* 191 */           headersBuilder.addLenient$okhttp(name, value);
/*     */         } 
/*     */       } 
/* 194 */       if (statusLine == null) throw (Throwable)new ProtocolException("Expected ':status' header not present");
/*     */       
/* 196 */       return (new Response.Builder())
/* 197 */         .protocol(protocol)
/* 198 */         .code(statusLine.code)
/* 199 */         .message(statusLine.message)
/* 200 */         .headers(headersBuilder.build());
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Http2ExchangeCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */