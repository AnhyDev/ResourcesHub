/*     */ package okhttp3.internal.http;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.net.ProtocolException;
/*     */ import java.net.Proxy;
/*     */ import java.util.List;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.Regex;
/*     */ import okhttp3.HttpUrl;
/*     */ import okhttp3.Interceptor;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.RequestBody;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.Route;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.connection.Exchange;
/*     */ import okhttp3.internal.connection.RealCall;
/*     */ import okhttp3.internal.connection.RouteException;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000R\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\003\030\000 \0362\0020\001:\001\036B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\032\020\005\032\004\030\0010\0062\006\020\007\032\0020\b2\006\020\t\032\0020\nH\002J\034\020\013\032\004\030\0010\0062\006\020\007\032\0020\b2\b\020\f\032\004\030\0010\rH\002J\020\020\016\032\0020\b2\006\020\017\032\0020\020H\026J\030\020\021\032\0020\0222\006\020\023\032\0020\0242\006\020\025\032\0020\022H\002J(\020\026\032\0020\0222\006\020\023\032\0020\0242\006\020\027\032\0020\0302\006\020\031\032\0020\0062\006\020\025\032\0020\022H\002J\030\020\032\032\0020\0222\006\020\023\032\0020\0242\006\020\031\032\0020\006H\002J\030\020\033\032\0020\0342\006\020\007\032\0020\b2\006\020\035\032\0020\034H\002R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\037"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "buildRedirectRequest", "Lokhttp3/Request;", "userResponse", "Lokhttp3/Response;", "method", "", "followUpRequest", "exchange", "Lokhttp3/internal/connection/Exchange;", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "isRecoverable", "", "e", "Ljava/io/IOException;", "requestSendStarted", "recover", "call", "Lokhttp3/internal/connection/RealCall;", "userRequest", "requestIsOneShot", "retryAfter", "", "defaultDelay", "Companion", "okhttp"})
/*     */ public final class RetryAndFollowUpInterceptor
/*     */   implements Interceptor
/*     */ {
/*     */   private final OkHttpClient client;
/*     */   private static final int MAX_FOLLOW_UPS = 20;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public RetryAndFollowUpInterceptor(@NotNull OkHttpClient client) {
/*  54 */     this.client = client;
/*     */   }
/*     */   @NotNull
/*     */   public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
/*  58 */     Intrinsics.checkNotNullParameter(chain, "chain"); RealInterceptorChain realChain = (RealInterceptorChain)chain;
/*  59 */     Request request = ((RealInterceptorChain)chain).getRequest$okhttp();
/*  60 */     RealCall call = realChain.getCall$okhttp();
/*  61 */     int followUpCount = 0;
/*  62 */     Response priorResponse = (Response)null;
/*  63 */     boolean newExchangeFinder = true;
/*  64 */     boolean bool = false; List recoveredFailures = CollectionsKt.emptyList();
/*     */     while (true) {
/*  66 */       call.enterNetworkInterceptorExchange(request, newExchangeFinder);
/*     */       
/*  68 */       Response response = null;
/*  69 */       boolean closeActiveExchange = true;
/*     */       
/*  71 */       try { if (call.isCanceled()) {
/*  72 */           throw (Throwable)new IOException("Canceled");
/*     */         }
/*     */ 
/*     */         
/*  76 */         try { response = realChain.proceed(request);
/*  77 */           newExchangeFinder = true; }
/*  78 */         catch (RouteException e)
/*     */         
/*  80 */         { if (!recover(e.getLastConnectException(), call, request, false)) {
/*  81 */             throw Util.withSuppressed(e.getFirstConnectException(), recoveredFailures);
/*     */           }
/*  83 */           recoveredFailures = CollectionsKt.plus(recoveredFailures, e.getFirstConnectException());
/*     */           
/*  85 */           newExchangeFinder = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 133 */           call.exitNetworkInterceptorExchange$okhttp(closeActiveExchange); continue; } catch (IOException e) { if (!recover(e, call, request, !(e instanceof okhttp3.internal.http2.ConnectionShutdownException))) throw Util.withSuppressed(e, recoveredFailures);  recoveredFailures = CollectionsKt.plus(recoveredFailures, e); newExchangeFinder = false; call.exitNetworkInterceptorExchange$okhttp(closeActiveExchange); continue; }  if (priorResponse != null) response = response.newBuilder().priorResponse(priorResponse.newBuilder().body(null).build()).build();  Exchange exchange = call.getInterceptorScopedExchange$okhttp(); Request followUp = followUpRequest(response, exchange); if (followUp == null) { if (exchange != null && exchange.isDuplex$okhttp()) call.timeoutEarlyExit();  closeActiveExchange = false; return response; }  RequestBody followUpBody = followUp.body(); if (followUpBody != null && followUpBody.isOneShot()) { closeActiveExchange = false; return response; }  if (response.body() != null) { Util.closeQuietly((Closeable)response.body()); } else { response.body(); }  if (++followUpCount > 20) throw (Throwable)new ProtocolException("Too many follow-up requests: " + followUpCount);  request = followUp; priorResponse = response; } finally { call.exitNetworkInterceptorExchange$okhttp(closeActiveExchange); }
/*     */     
/*     */     } 
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
/*     */ 
/*     */   
/*     */   private final boolean recover(IOException e, RealCall call, Request userRequest, boolean requestSendStarted) {
/* 151 */     if (!this.client.retryOnConnectionFailure()) return false;
/*     */ 
/*     */     
/* 154 */     if (requestSendStarted && requestIsOneShot(e, userRequest)) return false;
/*     */ 
/*     */     
/* 157 */     if (!isRecoverable(e, requestSendStarted)) return false;
/*     */ 
/*     */     
/* 160 */     if (!call.retryAfterFailure()) return false;
/*     */ 
/*     */     
/* 163 */     return true;
/*     */   }
/*     */   
/*     */   private final boolean requestIsOneShot(IOException e, Request userRequest) {
/* 167 */     RequestBody requestBody = userRequest.body();
/* 168 */     return 
/* 169 */       ((requestBody != null && requestBody.isOneShot()) || e instanceof java.io.FileNotFoundException);
/*     */   }
/*     */ 
/*     */   
/*     */   private final boolean isRecoverable(IOException e, boolean requestSendStarted) {
/* 174 */     if (e instanceof ProtocolException) {
/* 175 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (e instanceof java.io.InterruptedIOException) {
/* 181 */       return (e instanceof java.net.SocketTimeoutException && !requestSendStarted);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 186 */     if (e instanceof javax.net.ssl.SSLHandshakeException)
/*     */     {
/*     */       
/* 189 */       if (e.getCause() instanceof java.security.cert.CertificateException) {
/* 190 */         return false;
/*     */       }
/*     */     }
/* 193 */     if (e instanceof javax.net.ssl.SSLPeerUnverifiedException)
/*     */     {
/* 195 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 200 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private final Request followUpRequest(Response userResponse, Exchange exchange) throws IOException {
/*     */     Proxy selectedProxy;
/*     */     RequestBody requestBody1;
/*     */     Response priorResponse;
/*     */     RequestBody requestBody;
/*     */     Response response1;
/* 210 */     exchange.getConnection$okhttp(); Route route = (exchange != null && exchange.getConnection$okhttp() != null) ? exchange.getConnection$okhttp().route() : null;
/* 211 */     int responseCode = userResponse.code();
/*     */     
/* 213 */     String method = userResponse.request().method();
/* 214 */     switch (responseCode) {
/*     */       case 407:
/* 216 */         Intrinsics.checkNotNull(route); selectedProxy = route.proxy();
/* 217 */         if (selectedProxy.type() != Proxy.Type.HTTP) {
/* 218 */           throw (Throwable)new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
/*     */         }
/* 220 */         return this.client.proxyAuthenticator().authenticate(route, userResponse);
/*     */       
/*     */       case 401:
/* 223 */         return this.client.authenticator().authenticate(route, userResponse);
/*     */       case 300: case 301: case 302: case 303: case 307:
/*     */       case 308:
/* 226 */         return buildRedirectRequest(userResponse, method);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 408:
/* 233 */         if (!this.client.retryOnConnectionFailure())
/*     */         {
/* 235 */           return null;
/*     */         }
/*     */         
/* 238 */         requestBody1 = userResponse.request().body();
/* 239 */         if (requestBody1 != null && requestBody1.isOneShot()) {
/* 240 */           return null;
/*     */         }
/* 242 */         response1 = userResponse.priorResponse();
/* 243 */         if (response1 != null && response1.code() == 408)
/*     */         {
/* 245 */           return null;
/*     */         }
/*     */         
/* 248 */         if (retryAfter(userResponse, 0) > 0) {
/* 249 */           return null;
/*     */         }
/*     */         
/* 252 */         return userResponse.request();
/*     */ 
/*     */       
/*     */       case 503:
/* 256 */         priorResponse = userResponse.priorResponse();
/* 257 */         if (priorResponse != null && priorResponse.code() == 503)
/*     */         {
/* 259 */           return null;
/*     */         }
/*     */         
/* 262 */         if (retryAfter(userResponse, 2147483647) == 0)
/*     */         {
/* 264 */           return userResponse.request();
/*     */         }
/*     */         
/* 267 */         return null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 421:
/* 274 */         requestBody = userResponse.request().body();
/* 275 */         if (requestBody != null && requestBody.isOneShot()) {
/* 276 */           return null;
/*     */         }
/*     */         
/* 279 */         if (exchange == null || !exchange.isCoalescedConnection$okhttp()) {
/* 280 */           return null;
/*     */         }
/*     */         
/* 283 */         exchange.getConnection$okhttp().noCoalescedConnections$okhttp();
/* 284 */         return userResponse.request();
/*     */     } 
/*     */     
/* 287 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final Request buildRedirectRequest(Response userResponse, String method) {
/* 293 */     if (!this.client.followRedirects()) return null;
/*     */     
/* 295 */     if (Response.header$default(userResponse, "Location", null, 2, null) != null) { String location = Response.header$default(userResponse, "Location", null, 2, null);
/*     */       
/* 297 */       if (userResponse.request().url().resolve(location) != null) { HttpUrl url = userResponse.request().url().resolve(location);
/*     */ 
/*     */         
/* 300 */         boolean sameScheme = Intrinsics.areEqual(url.scheme(), userResponse.request().url().scheme());
/* 301 */         if (!sameScheme && !this.client.followSslRedirects()) return null;
/*     */ 
/*     */         
/* 304 */         Request.Builder requestBuilder = userResponse.request().newBuilder();
/* 305 */         if (HttpMethod.permitsRequestBody(method)) {
/* 306 */           int responseCode = userResponse.code();
/* 307 */           boolean maintainBody = (HttpMethod.INSTANCE.redirectsWithBody(method) || responseCode == 308 || responseCode == 307);
/*     */ 
/*     */           
/* 310 */           if (HttpMethod.INSTANCE.redirectsToGet(method) && responseCode != 308 && responseCode != 307) {
/* 311 */             requestBuilder.method("GET", null);
/*     */           } else {
/* 313 */             RequestBody requestBody = maintainBody ? userResponse.request().body() : null;
/* 314 */             requestBuilder.method(method, requestBody);
/*     */           } 
/* 316 */           if (!maintainBody) {
/* 317 */             requestBuilder.removeHeader("Transfer-Encoding");
/* 318 */             requestBuilder.removeHeader("Content-Length");
/* 319 */             requestBuilder.removeHeader("Content-Type");
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 326 */         if (!Util.canReuseConnectionFor(userResponse.request().url(), url)) {
/* 327 */           requestBuilder.removeHeader("Authorization");
/*     */         }
/*     */         
/* 330 */         return requestBuilder.url(url).build(); }
/*     */        userResponse.request().url().resolve(location); return null; }
/*     */     
/*     */     Response.header$default(userResponse, "Location", null, 2, null);
/* 334 */     return null; } private final int retryAfter(Response userResponse, int defaultDelay) { if (Response.header$default(userResponse, "Retry-After", null, 2, null) != null) { String header = Response.header$default(userResponse, "Retry-After", null, 2, null);
/*     */ 
/*     */ 
/*     */       
/* 338 */       CharSequence charSequence = header; String str1 = "\\d+"; boolean bool = false; Regex regex = new Regex(str1); bool = false; if (regex.matches(charSequence)) {
/* 339 */         Intrinsics.checkNotNullExpressionValue(Integer.valueOf(header), "Integer.valueOf(header)"); return Integer.valueOf(header).intValue();
/*     */       } 
/* 341 */       return Integer.MAX_VALUE; }
/*     */     
/*     */     Response.header$default(userResponse, "Retry-After", null, 2, null);
/*     */     return defaultDelay; }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor$Companion;", "", "()V", "MAX_FOLLOW_UPS", "", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/RetryAndFollowUpInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */