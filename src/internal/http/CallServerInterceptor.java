/*     */ package okhttp3.internal.http;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ProtocolException;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Interceptor;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.RequestBody;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.connection.Exchange;
/*     */ import okio.BufferedSink;
/*     */ import okio.Okio;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\036\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\020\020\005\032\0020\0062\006\020\007\032\0020\bH\026R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\t"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"})
/*     */ public final class CallServerInterceptor
/*     */   implements Interceptor
/*     */ {
/*     */   private final boolean forWebSocket;
/*     */   
/*     */   public CallServerInterceptor(boolean forWebSocket) {
/*  26 */     this.forWebSocket = forWebSocket;
/*     */   }
/*     */   @NotNull
/*     */   public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
/*  30 */     Intrinsics.checkNotNullParameter(chain, "chain"); RealInterceptorChain realChain = (RealInterceptorChain)chain;
/*  31 */     Intrinsics.checkNotNull(realChain.getExchange$okhttp()); Exchange exchange = realChain.getExchange$okhttp();
/*  32 */     Request request = realChain.getRequest$okhttp();
/*  33 */     RequestBody requestBody = request.body();
/*  34 */     long sentRequestMillis = System.currentTimeMillis();
/*     */     
/*  36 */     exchange.writeRequestHeaders(request);
/*     */     
/*  38 */     boolean invokeStartEvent = true;
/*  39 */     Response.Builder responseBuilder = (Response.Builder)null;
/*  40 */     if (HttpMethod.permitsRequestBody(request.method()) && requestBody != null) {
/*     */ 
/*     */ 
/*     */       
/*  44 */       if (StringsKt.equals("100-continue", request.header("Expect"), true)) {
/*  45 */         exchange.flushRequest();
/*  46 */         responseBuilder = exchange.readResponseHeaders(true);
/*  47 */         exchange.responseHeadersStart();
/*  48 */         invokeStartEvent = false;
/*     */       } 
/*  50 */       if (responseBuilder == null) {
/*  51 */         if (requestBody.isDuplex()) {
/*     */           
/*  53 */           exchange.flushRequest();
/*  54 */           BufferedSink bufferedRequestBody = Okio.buffer(exchange.createRequestBody(request, true));
/*  55 */           requestBody.writeTo(bufferedRequestBody);
/*     */         } else {
/*     */           
/*  58 */           BufferedSink bufferedRequestBody = Okio.buffer(exchange.createRequestBody(request, false));
/*  59 */           requestBody.writeTo(bufferedRequestBody);
/*  60 */           bufferedRequestBody.close();
/*     */         } 
/*     */       } else {
/*  63 */         exchange.noRequestBody();
/*  64 */         if (!exchange.getConnection$okhttp().isMultiplexed$okhttp())
/*     */         {
/*     */ 
/*     */           
/*  68 */           exchange.noNewExchangesOnConnection();
/*     */         }
/*     */       } 
/*     */     } else {
/*  72 */       exchange.noRequestBody();
/*     */     } 
/*     */     
/*  75 */     if (requestBody == null || !requestBody.isDuplex()) {
/*  76 */       exchange.finishRequest();
/*     */     }
/*  78 */     if (responseBuilder == null) {
/*  79 */       Intrinsics.checkNotNull(exchange.readResponseHeaders(false)); responseBuilder = exchange.readResponseHeaders(false);
/*  80 */       if (invokeStartEvent) {
/*  81 */         exchange.responseHeadersStart();
/*  82 */         invokeStartEvent = false;
/*     */       } 
/*     */     } 
/*  85 */     Response response = responseBuilder
/*  86 */       .request(request)
/*  87 */       .handshake(exchange.getConnection$okhttp().handshake())
/*  88 */       .sentRequestAtMillis(sentRequestMillis)
/*  89 */       .receivedResponseAtMillis(System.currentTimeMillis())
/*  90 */       .build();
/*  91 */     int code = response.code();
/*  92 */     if (code == 100) {
/*     */ 
/*     */       
/*  95 */       Intrinsics.checkNotNull(exchange.readResponseHeaders(false)); responseBuilder = exchange.readResponseHeaders(false);
/*  96 */       if (invokeStartEvent) {
/*  97 */         exchange.responseHeadersStart();
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 104 */       response = responseBuilder.request(request).handshake(exchange.getConnection$okhttp().handshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
/* 105 */       code = response.code();
/*     */     } 
/*     */     
/* 108 */     exchange.responseHeadersEnd(response);
/*     */     
/* 110 */     response = (this.forWebSocket && code == 101) ? 
/*     */       
/* 112 */       response.newBuilder()
/* 113 */       .body(Util.EMPTY_RESPONSE)
/* 114 */       .build() : 
/*     */       
/* 116 */       response.newBuilder()
/* 117 */       .body(exchange.openResponseBody(response))
/* 118 */       .build();
/*     */     
/* 120 */     if (StringsKt.equals("close", response.request().header("Connection"), true) || 
/* 121 */       StringsKt.equals("close", Response.header$default(response, "Connection", null, 2, null), true)) {
/* 122 */       exchange.noNewExchangesOnConnection();
/*     */     }
/* 124 */     if (code == 204 || code == 205) { response.body(); if (((response.body() != null) ? response.body().contentLength() : -1L) > 0L) {
/*     */         
/* 126 */         response.body(); throw (Throwable)new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + ((response.body() != null) ? Long.valueOf(response.body().contentLength()) : null));
/*     */       }  }
/* 128 */      return response;
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/CallServerInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */