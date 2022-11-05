/*     */ package okhttp3.internal.cache;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Cache;
/*     */ import okhttp3.Call;
/*     */ import okhttp3.EventListener;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.Interceptor;
/*     */ import okhttp3.Protocol;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.ResponseBody;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.connection.RealCall;
/*     */ import okhttp3.internal.http.HttpHeaders;
/*     */ import okhttp3.internal.http.HttpMethod;
/*     */ import okhttp3.internal.http.RealResponseBody;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import okio.BufferedSource;
/*     */ import okio.Okio;
/*     */ import okio.Sink;
/*     */ import okio.Source;
/*     */ import okio.Timeout;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\030\000 \0172\0020\001:\001\017B\017\022\b\020\002\032\004\030\0010\003¢\006\002\020\004J\032\020\007\032\0020\b2\b\020\t\032\004\030\0010\n2\006\020\013\032\0020\bH\002J\020\020\f\032\0020\b2\006\020\r\032\0020\016H\026R\026\020\002\032\004\030\0010\003X\004¢\006\b\n\000\032\004\b\005\020\006¨\006\020"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor;", "Lokhttp3/Interceptor;", "cache", "Lokhttp3/Cache;", "(Lokhttp3/Cache;)V", "getCache$okhttp", "()Lokhttp3/Cache;", "cacheWritingResponse", "Lokhttp3/Response;", "cacheRequest", "Lokhttp3/internal/cache/CacheRequest;", "response", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "okhttp"})
/*     */ public final class CacheInterceptor implements Interceptor {
/*     */   @Nullable
/*     */   private final Cache cache;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   @Nullable
/*     */   public final Cache getCache$okhttp() {
/*  42 */     return this.cache; } public CacheInterceptor(@Nullable Cache cache) { this.cache = cache; }
/*     */ 
/*     */   
/*     */   @NotNull
/*  46 */   public Response intercept(@NotNull Interceptor.Chain chain) throws IOException { Intrinsics.checkNotNullParameter(chain, "chain"); Call call = chain.call();
/*  47 */     Response cacheCandidate = (this.cache != null) ? this.cache.get$okhttp(chain.request()) : null;
/*     */     
/*  49 */     long now = System.currentTimeMillis();
/*     */     
/*  51 */     CacheStrategy strategy = (new CacheStrategy.Factory(now, chain.request(), cacheCandidate)).compute();
/*  52 */     Request networkRequest = strategy.getNetworkRequest();
/*  53 */     Response cacheResponse = strategy.getCacheResponse();
/*     */     
/*  55 */     if (this.cache != null) { this.cache.trackResponse$okhttp(strategy); } else {  }
/*  56 */      if (!(call instanceof RealCall)); if ((RealCall)null == null || ((RealCall)null).getEventListener$okhttp() == null) ((RealCall)null).getEventListener$okhttp();  EventListener listener = EventListener.NONE;
/*     */     
/*  58 */     if (cacheCandidate != null && cacheResponse == null)
/*     */     {
/*  60 */       if (cacheCandidate.body() != null) { Util.closeQuietly((Closeable)cacheCandidate.body()); } else { cacheCandidate.body(); }
/*     */     
/*     */     }
/*     */     
/*  64 */     if (networkRequest == null && cacheResponse == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  73 */       Response response1 = (new Response.Builder()).request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build(); boolean bool1 = false, bool2 = false; Response it = response1; int $i$a$-also-CacheInterceptor$intercept$1 = 0;
/*  74 */       listener.satisfactionFailure(call, it);
/*     */       
/*     */       return response1;
/*     */     } 
/*     */     
/*  79 */     if (networkRequest == null) {
/*  80 */       Intrinsics.checkNotNull(cacheResponse);
/*     */       
/*  82 */       Response response1 = cacheResponse.newBuilder().cacheResponse(Companion.stripBody(cacheResponse)).build(); boolean bool1 = false, bool2 = false; Response it = response1; int $i$a$-also-CacheInterceptor$intercept$2 = 0;
/*  83 */       listener.cacheHit(call, it);
/*     */       
/*     */       return response1;
/*     */     } 
/*  87 */     if (cacheResponse != null) {
/*  88 */       listener.cacheConditionalHit(call, cacheResponse);
/*  89 */     } else if (this.cache != null) {
/*  90 */       listener.cacheMiss(call);
/*     */     } 
/*     */     
/*  93 */     Response networkResponse = (Response)null;
/*     */     try {
/*  95 */       networkResponse = chain.proceed(networkRequest);
/*     */     } finally {
/*     */       
/*  98 */       if (cacheCandidate != null) {
/*  99 */         if (cacheCandidate.body() != null) { Util.closeQuietly((Closeable)cacheCandidate.body()); } else { cacheCandidate.body(); }
/*     */       
/*     */       }
/*     */     } 
/*     */     
/* 104 */     if (cacheResponse != null) {
/* 105 */       if (networkResponse != null) { if (networkResponse.code() == 304) {
/* 106 */           Response response1 = cacheResponse.newBuilder()
/* 107 */             .headers(Companion.combine(cacheResponse.headers(), networkResponse.headers()))
/* 108 */             .sentRequestAtMillis(networkResponse.sentRequestAtMillis())
/* 109 */             .receivedResponseAtMillis(networkResponse.receivedResponseAtMillis())
/* 110 */             .cacheResponse(Companion.stripBody(cacheResponse))
/* 111 */             .networkResponse(Companion.stripBody(networkResponse))
/* 112 */             .build();
/*     */           
/* 114 */           Intrinsics.checkNotNull(networkResponse.body()); networkResponse.body().close();
/*     */ 
/*     */ 
/*     */           
/* 118 */           Intrinsics.checkNotNull(this.cache); this.cache.trackConditionalCacheHit$okhttp();
/* 119 */           this.cache.update$okhttp(cacheResponse, response1);
/* 120 */           Response response2 = response1; boolean bool1 = false, bool2 = false; Response it = response2; int $i$a$-also-CacheInterceptor$intercept$3 = 0;
/* 121 */           listener.cacheHit(call, it); return response2;
/*     */         }  }
/*     */       else {  }
/* 124 */        if (cacheResponse.body() != null) { Util.closeQuietly((Closeable)cacheResponse.body()); } else { cacheResponse.body(); }
/*     */     
/*     */     } 
/*     */     
/* 128 */     Intrinsics.checkNotNull(networkResponse); Response response = networkResponse.newBuilder()
/* 129 */       .cacheResponse(Companion.stripBody(cacheResponse))
/* 130 */       .networkResponse(Companion.stripBody(networkResponse))
/* 131 */       .build();
/*     */     
/* 133 */     if (this.cache != null) {
/* 134 */       if (HttpHeaders.promisesBody(response) && CacheStrategy.Companion.isCacheable(response, networkRequest)) {
/*     */         
/* 136 */         CacheRequest cacheRequest = this.cache.put$okhttp(response);
/* 137 */         Response response1 = cacheWritingResponse(cacheRequest, response); boolean bool1 = false, bool2 = false; Response it = response1; int $i$a$-also-CacheInterceptor$intercept$4 = 0;
/* 138 */         if (cacheResponse != null)
/*     */         {
/* 140 */           listener.cacheMiss(call);
/*     */         }
/*     */         
/*     */         return response1;
/*     */       } 
/* 145 */       if (HttpMethod.INSTANCE.invalidatesCache(networkRequest.method())) {
/*     */         try {
/* 147 */           this.cache.remove$okhttp(networkRequest);
/* 148 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 154 */     return response; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Response cacheWritingResponse(CacheRequest cacheRequest, Response response) throws IOException {
/* 165 */     if (cacheRequest == null) return response; 
/* 166 */     Sink cacheBodyUnbuffered = cacheRequest.body();
/*     */     
/* 168 */     Intrinsics.checkNotNull(response.body()); BufferedSource source = response.body().source();
/* 169 */     BufferedSink cacheBody = Okio.buffer(cacheBodyUnbuffered);
/*     */     
/* 171 */     CacheInterceptor$cacheWritingResponse$cacheWritingSource$1 cacheWritingSource = new CacheInterceptor$cacheWritingResponse$cacheWritingSource$1(source, cacheRequest, cacheBody);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     String contentType = Response.header$default(response, "Content-Type", null, 2, null);
/* 214 */     long contentLength = response.body().contentLength();
/* 215 */     return response.newBuilder()
/* 216 */       .body((ResponseBody)new RealResponseBody(contentType, contentLength, Okio.buffer(cacheWritingSource)))
/* 217 */       .build();
/*     */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000+\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\004\032\0020\005H\026J\030\020\006\032\0020\0072\006\020\b\032\0020\t2\006\020\n\032\0020\007H\026J\b\020\013\032\0020\fH\026R\016\020\002\032\0020\003X\016¢\006\002\n\000¨\006\r"}, d2 = {"okhttp3/internal/cache/CacheInterceptor$cacheWritingResponse$cacheWritingSource$1", "Lokio/Source;", "cacheRequestClosed", "", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "okhttp"}) public static final class CacheInterceptor$cacheWritingResponse$cacheWritingSource$1 implements Source {
/*     */     private boolean cacheRequestClosed; CacheInterceptor$cacheWritingResponse$cacheWritingSource$1(BufferedSource $captured_local_variable$0, CacheRequest $captured_local_variable$1, BufferedSink $captured_local_variable$2) {} public long read(@NotNull Buffer sink, long byteCount) throws IOException { Intrinsics.checkNotNullParameter(sink, "sink"); long bytesRead = 0L; try { bytesRead = this.$source.read(sink, byteCount); } catch (IOException e) { if (!this.cacheRequestClosed) { this.cacheRequestClosed = true; this.$cacheRequest.abort(); }  throw (Throwable)e; }  if (bytesRead == -1L) { if (!this.cacheRequestClosed) { this.cacheRequestClosed = true; this.$cacheBody.close(); }  return -1L; }
/*     */        sink.copyTo(this.$cacheBody.getBuffer(), sink.size() - bytesRead, bytesRead); this.$cacheBody.emitCompleteSegments(); return bytesRead; } @NotNull public Timeout timeout() { return this.$source.timeout(); } public void close() throws IOException { if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) { this.cacheRequestClosed = true; this.$cacheRequest.abort(); }
/*     */        this.$source.close(); }
/*     */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\013\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\030\020\003\032\0020\0042\006\020\005\032\0020\0042\006\020\006\032\0020\004H\002J\020\020\007\032\0020\b2\006\020\t\032\0020\nH\002J\020\020\013\032\0020\b2\006\020\t\032\0020\nH\002J\024\020\f\032\004\030\0010\r2\b\020\016\032\004\030\0010\rH\002¨\006\017"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor$Companion;", "", "()V", "combine", "Lokhttp3/Headers;", "cachedHeaders", "networkHeaders", "isContentSpecificHeader", "", "fieldName", "", "isEndToEnd", "stripBody", "Lokhttp3/Response;", "response", "okhttp"}) public static final class Companion {
/* 223 */     private final Response stripBody(Response response) { return (((response != null) ? response.body() : null) != null) ? 
/* 224 */         response.newBuilder().body(null).build() : 
/*     */         
/* 226 */         response; }
/*     */ 
/*     */     
/*     */     private Companion() {}
/*     */     
/*     */     private final Headers combine(Headers cachedHeaders, Headers networkHeaders) {
/* 232 */       Headers.Builder result = new Headers.Builder();
/*     */       int i;
/* 234 */       for (byte b = 0; b < i; b++) {
/* 235 */         String fieldName = cachedHeaders.name(b);
/* 236 */         String value = cachedHeaders.value(b);
/* 237 */         if (!StringsKt.equals("Warning", fieldName, true) || !StringsKt.startsWith$default(value, "1", false, 2, null))
/*     */         {
/*     */ 
/*     */           
/* 241 */           if (isContentSpecificHeader(fieldName) || 
/* 242 */             !isEndToEnd(fieldName) || 
/* 243 */             networkHeaders.get(fieldName) == null) {
/* 244 */             result.addLenient$okhttp(fieldName, value);
/*     */           }
/*     */         }
/*     */       } 
/* 248 */       for (int index = 0; index < i; index++) {
/* 249 */         String fieldName = networkHeaders.name(index);
/* 250 */         if (!isContentSpecificHeader(fieldName) && isEndToEnd(fieldName)) {
/* 251 */           result.addLenient$okhttp(fieldName, networkHeaders.value(index));
/*     */         }
/*     */       } 
/*     */       
/* 255 */       return result.build();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final boolean isEndToEnd(String fieldName) {
/* 263 */       return (!StringsKt.equals("Connection", fieldName, true) && 
/* 264 */         !StringsKt.equals("Keep-Alive", fieldName, true) && 
/* 265 */         !StringsKt.equals("Proxy-Authenticate", fieldName, true) && 
/* 266 */         !StringsKt.equals("Proxy-Authorization", fieldName, true) && 
/* 267 */         !StringsKt.equals("TE", fieldName, true) && 
/* 268 */         !StringsKt.equals("Trailers", fieldName, true) && 
/* 269 */         !StringsKt.equals("Transfer-Encoding", fieldName, true) && 
/* 270 */         !StringsKt.equals("Upgrade", fieldName, true));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final boolean isContentSpecificHeader(String fieldName) {
/* 278 */       return (StringsKt.equals("Content-Length", fieldName, true) || 
/* 279 */         StringsKt.equals("Content-Encoding", fieldName, true) || 
/* 280 */         StringsKt.equals("Content-Type", fieldName, true));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/cache/CacheInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */