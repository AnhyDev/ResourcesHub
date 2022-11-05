/*     */ package okhttp3.internal.http;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Cookie;
/*     */ import okhttp3.CookieJar;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.Interceptor;
/*     */ import okhttp3.MediaType;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.RequestBody;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.ResponseBody;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.GzipSource;
/*     */ import okio.Okio;
/*     */ import okio.Source;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000.\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\026\020\005\032\0020\0062\f\020\007\032\b\022\004\022\0020\t0\bH\002J\020\020\n\032\0020\0132\006\020\f\032\0020\rH\026R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\016"}, d2 = {"Lokhttp3/internal/http/BridgeInterceptor;", "Lokhttp3/Interceptor;", "cookieJar", "Lokhttp3/CookieJar;", "(Lokhttp3/CookieJar;)V", "cookieHeader", "", "cookies", "", "Lokhttp3/Cookie;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"})
/*     */ public final class BridgeInterceptor
/*     */   implements Interceptor
/*     */ {
/*     */   private final CookieJar cookieJar;
/*     */   
/*     */   public BridgeInterceptor(@NotNull CookieJar cookieJar) {
/*  34 */     this.cookieJar = cookieJar;
/*     */   }
/*     */   @NotNull
/*     */   public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
/*  38 */     Intrinsics.checkNotNullParameter(chain, "chain"); Request userRequest = chain.request();
/*  39 */     Request.Builder requestBuilder = userRequest.newBuilder();
/*     */     
/*  41 */     RequestBody body = userRequest.body();
/*  42 */     if (body != null) {
/*  43 */       MediaType contentType = body.contentType();
/*  44 */       if (contentType != null) {
/*  45 */         requestBuilder.header("Content-Type", contentType.toString());
/*     */       }
/*     */       
/*  48 */       long contentLength = body.contentLength();
/*  49 */       if (contentLength != -1L) {
/*  50 */         requestBuilder.header("Content-Length", String.valueOf(contentLength));
/*  51 */         requestBuilder.removeHeader("Transfer-Encoding");
/*     */       } else {
/*  53 */         requestBuilder.header("Transfer-Encoding", "chunked");
/*  54 */         requestBuilder.removeHeader("Content-Length");
/*     */       } 
/*     */     } 
/*     */     
/*  58 */     if (userRequest.header("Host") == null) {
/*  59 */       requestBuilder.header("Host", Util.toHostHeader$default(userRequest.url(), false, 1, null));
/*     */     }
/*     */     
/*  62 */     if (userRequest.header("Connection") == null) {
/*  63 */       requestBuilder.header("Connection", "Keep-Alive");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  68 */     boolean transparentGzip = false;
/*  69 */     if (userRequest.header("Accept-Encoding") == null && userRequest.header("Range") == null) {
/*  70 */       transparentGzip = true;
/*  71 */       requestBuilder.header("Accept-Encoding", "gzip");
/*     */     } 
/*     */     
/*  74 */     List<Cookie> cookies = this.cookieJar.loadForRequest(userRequest.url());
/*  75 */     List<Cookie> list1 = cookies; boolean bool = false; if (!list1.isEmpty()) {
/*  76 */       requestBuilder.header("Cookie", cookieHeader(cookies));
/*     */     }
/*     */     
/*  79 */     if (userRequest.header("User-Agent") == null) {
/*  80 */       requestBuilder.header("User-Agent", "okhttp/4.9.3");
/*     */     }
/*     */     
/*  83 */     Response networkResponse = chain.proceed(requestBuilder.build());
/*     */     
/*  85 */     HttpHeaders.receiveHeaders(this.cookieJar, userRequest.url(), networkResponse.headers());
/*     */     
/*  87 */     Response.Builder responseBuilder = networkResponse.newBuilder()
/*  88 */       .request(userRequest);
/*     */ 
/*     */ 
/*     */     
/*  92 */     if (transparentGzip && StringsKt.equals("gzip", Response.header$default(networkResponse, "Content-Encoding", null, 2, null), true) && HttpHeaders.promisesBody(networkResponse)) {
/*  93 */       ResponseBody responseBody = networkResponse.body();
/*  94 */       if (responseBody != null) {
/*  95 */         GzipSource gzipSource = new GzipSource((Source)responseBody.source());
/*  96 */         Headers strippedHeaders = networkResponse.headers().newBuilder()
/*  97 */           .removeAll("Content-Encoding")
/*  98 */           .removeAll("Content-Length")
/*  99 */           .build();
/* 100 */         responseBuilder.headers(strippedHeaders);
/* 101 */         String contentType = Response.header$default(networkResponse, "Content-Type", null, 2, null);
/* 102 */         responseBuilder.body(new RealResponseBody(contentType, -1L, Okio.buffer((Source)gzipSource)));
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     return responseBuilder.build();
/*     */   }
/*     */   
/*     */   private final String cookieHeader(List cookies) {
/* 110 */     boolean bool1 = false, bool2 = false; StringBuilder stringBuilder1 = new StringBuilder(); boolean bool3 = false, bool4 = false; StringBuilder $this$buildString = stringBuilder1; int $i$a$-buildString-BridgeInterceptor$cookieHeader$1 = 0;
/* 111 */     Iterable $this$forEachIndexed$iv = cookies; int $i$f$forEachIndexed = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     int index$iv = 0;
/* 119 */     Iterator iterator = $this$forEachIndexed$iv.iterator(); if (iterator.hasNext()) { Object item$iv = iterator.next(); int i = index$iv++; boolean bool = false; if (i < 0) CollectionsKt.throwIndexOverflow();  Cookie cookie = (Cookie)item$iv; int index = i, $i$a$-forEachIndexed-BridgeInterceptor$cookieHeader$1$1 = 0;
/*     */       if (index > 0)
/*     */         $this$buildString.append("; ");  }
/*     */     
/*     */     Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
/*     */     return stringBuilder1.toString();
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/BridgeInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */