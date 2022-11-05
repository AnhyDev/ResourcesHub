/*     */ package okhttp3;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Proxy;
/*     */ import java.util.List;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000z\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020\t\n\002\b\004\n\002\030\002\n\002\b\t\n\002\030\002\n\002\b\004\b&\030\000 ?2\0020\001:\002?@B\005¢\006\002\020\002J\030\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\bH\026J\030\020\t\032\0020\0042\006\020\005\032\0020\0062\006\020\n\032\0020\bH\026J\020\020\013\032\0020\0042\006\020\005\032\0020\006H\026J\020\020\f\032\0020\0042\006\020\005\032\0020\006H\026J\030\020\r\032\0020\0042\006\020\005\032\0020\0062\006\020\016\032\0020\017H\026J\020\020\020\032\0020\0042\006\020\005\032\0020\006H\026J\020\020\021\032\0020\0042\006\020\005\032\0020\006H\026J*\020\022\032\0020\0042\006\020\005\032\0020\0062\006\020\023\032\0020\0242\006\020\025\032\0020\0262\b\020\027\032\004\030\0010\030H\026J2\020\031\032\0020\0042\006\020\005\032\0020\0062\006\020\023\032\0020\0242\006\020\025\032\0020\0262\b\020\027\032\004\030\0010\0302\006\020\016\032\0020\017H\026J \020\032\032\0020\0042\006\020\005\032\0020\0062\006\020\023\032\0020\0242\006\020\025\032\0020\026H\026J\030\020\033\032\0020\0042\006\020\005\032\0020\0062\006\020\034\032\0020\035H\026J\030\020\036\032\0020\0042\006\020\005\032\0020\0062\006\020\034\032\0020\035H\026J+\020\037\032\0020\0042\006\020\005\032\0020\0062\006\020 \032\0020!2\021\020\"\032\r\022\t\022\0070$¢\006\002\b%0#H\026J\030\020&\032\0020\0042\006\020\005\032\0020\0062\006\020 \032\0020!H\026J+\020'\032\0020\0042\006\020\005\032\0020\0062\006\020(\032\0020)2\021\020*\032\r\022\t\022\0070\026¢\006\002\b%0#H\026J\030\020+\032\0020\0042\006\020\005\032\0020\0062\006\020(\032\0020)H\026J\030\020,\032\0020\0042\006\020\005\032\0020\0062\006\020-\032\0020.H\026J\020\020/\032\0020\0042\006\020\005\032\0020\006H\026J\030\0200\032\0020\0042\006\020\005\032\0020\0062\006\020\016\032\0020\017H\026J\030\0201\032\0020\0042\006\020\005\032\0020\0062\006\0202\032\00203H\026J\020\0204\032\0020\0042\006\020\005\032\0020\006H\026J\030\0205\032\0020\0042\006\020\005\032\0020\0062\006\020-\032\0020.H\026J\020\0206\032\0020\0042\006\020\005\032\0020\006H\026J\030\0207\032\0020\0042\006\020\005\032\0020\0062\006\020\016\032\0020\017H\026J\030\0208\032\0020\0042\006\020\005\032\0020\0062\006\020\n\032\0020\bH\026J\020\0209\032\0020\0042\006\020\005\032\0020\006H\026J\030\020:\032\0020\0042\006\020\005\032\0020\0062\006\020\n\032\0020\bH\026J\032\020;\032\0020\0042\006\020\005\032\0020\0062\b\020<\032\004\030\0010=H\026J\020\020>\032\0020\0042\006\020\005\032\0020\006H\026¨\006A"}, d2 = {"Lokhttp3/EventListener;", "", "()V", "cacheConditionalHit", "", "call", "Lokhttp3/Call;", "cachedResponse", "Lokhttp3/Response;", "cacheHit", "response", "cacheMiss", "callEnd", "callFailed", "ioe", "Ljava/io/IOException;", "callStart", "canceled", "connectEnd", "inetSocketAddress", "Ljava/net/InetSocketAddress;", "proxy", "Ljava/net/Proxy;", "protocol", "Lokhttp3/Protocol;", "connectFailed", "connectStart", "connectionAcquired", "connection", "Lokhttp3/Connection;", "connectionReleased", "dnsEnd", "domainName", "", "inetAddressList", "", "Ljava/net/InetAddress;", "Lkotlin/jvm/JvmSuppressWildcards;", "dnsStart", "proxySelectEnd", "url", "Lokhttp3/HttpUrl;", "proxies", "proxySelectStart", "requestBodyEnd", "byteCount", "", "requestBodyStart", "requestFailed", "requestHeadersEnd", "request", "Lokhttp3/Request;", "requestHeadersStart", "responseBodyEnd", "responseBodyStart", "responseFailed", "responseHeadersEnd", "responseHeadersStart", "satisfactionFailure", "secureConnectEnd", "handshake", "Lokhttp3/Handshake;", "secureConnectStart", "Companion", "Factory", "okhttp"})
/*     */ public abstract class EventListener
/*     */ {
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final EventListener NONE;
/*     */   
/*     */   public void callStart(@NotNull Call call) {
/*  72 */     Intrinsics.checkNotNullParameter(call, "call");
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
/*     */   public void proxySelectStart(@NotNull Call call, @NotNull HttpUrl url) {
/*  86 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(url, "url");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void proxySelectEnd(@NotNull Call call, @NotNull HttpUrl url, @NotNull List proxies) {
/* 108 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(url, "url"); Intrinsics.checkNotNullParameter(proxies, "proxies");
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
/*     */   public void dnsStart(@NotNull Call call, @NotNull String domainName) {
/* 123 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(domainName, "domainName");
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
/*     */   public void dnsEnd(@NotNull Call call, @NotNull String domainName, @NotNull List inetAddressList) {
/* 135 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(domainName, "domainName"); Intrinsics.checkNotNullParameter(inetAddressList, "inetAddressList");
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
/*     */   public void connectStart(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy) {
/* 150 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress"); Intrinsics.checkNotNullParameter(proxy, "proxy");
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
/*     */   
/*     */   public void secureConnectStart(@NotNull Call call) {
/* 167 */     Intrinsics.checkNotNullParameter(call, "call");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void secureConnectEnd(@NotNull Call call, @Nullable Handshake handshake) {
/* 178 */     Intrinsics.checkNotNullParameter(call, "call");
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
/*     */   public void connectEnd(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol) {
/* 192 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress"); Intrinsics.checkNotNullParameter(proxy, "proxy");
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
/*     */   public void connectFailed(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, @NotNull IOException ioe) {
/* 208 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress"); Intrinsics.checkNotNullParameter(proxy, "proxy"); Intrinsics.checkNotNullParameter(ioe, "ioe");
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
/*     */   public void connectionAcquired(@NotNull Call call, @NotNull Connection connection) {
/* 220 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(connection, "connection");
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
/*     */   public void connectionReleased(@NotNull Call call, @NotNull Connection connection) {
/* 234 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(connection, "connection");
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
/*     */   public void requestHeadersStart(@NotNull Call call) {
/* 247 */     Intrinsics.checkNotNullParameter(call, "call");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestHeadersEnd(@NotNull Call call, @NotNull Request request) {
/* 258 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(request, "request");
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
/*     */   public void requestBodyStart(@NotNull Call call) {
/* 272 */     Intrinsics.checkNotNullParameter(call, "call");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestBodyEnd(@NotNull Call call, long byteCount) {
/* 283 */     Intrinsics.checkNotNullParameter(call, "call");
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
/*     */   public void requestFailed(@NotNull Call call, @NotNull IOException ioe) {
/* 295 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(ioe, "ioe");
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
/*     */   public void responseHeadersStart(@NotNull Call call) {
/* 311 */     Intrinsics.checkNotNullParameter(call, "call");
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
/*     */   public void responseHeadersEnd(@NotNull Call call, @NotNull Response response) {
/* 325 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(response, "response");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void responseBodyStart(@NotNull Call call) {
/* 347 */     Intrinsics.checkNotNullParameter(call, "call");
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
/*     */ 
/*     */   
/*     */   public void responseBodyEnd(@NotNull Call call, long byteCount) {
/* 365 */     Intrinsics.checkNotNullParameter(call, "call");
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
/*     */   public void responseFailed(@NotNull Call call, @NotNull IOException ioe) {
/* 380 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(ioe, "ioe");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callEnd(@NotNull Call call) {
/* 391 */     Intrinsics.checkNotNullParameter(call, "call");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callFailed(@NotNull Call call, @NotNull IOException ioe) {
/* 402 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(ioe, "ioe");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void canceled(@NotNull Call call) {
/* 423 */     Intrinsics.checkNotNullParameter(call, "call");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void satisfactionFailure(@NotNull Call call, @NotNull Response response) {
/* 430 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(response, "response");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cacheHit(@NotNull Call call, @NotNull Response response) {
/* 439 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(response, "response");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cacheMiss(@NotNull Call call) {
/* 448 */     Intrinsics.checkNotNullParameter(call, "call");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cacheConditionalHit(@NotNull Call call, @NotNull Response cachedResponse) {
/* 458 */     Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(cachedResponse, "cachedResponse");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/EventListener$Companion;", "", "()V", "NONE", "Lokhttp3/EventListener;", "okhttp"})
/*     */   public static final class Companion
/*     */   {
/*     */     private Companion() {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public static final Companion Companion = new Companion(null); static { NONE = new EventListener$Companion$NONE$1(); }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\013\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001¨\006\002"}, d2 = {"okhttp3/EventListener$Companion$NONE$1", "Lokhttp3/EventListener;", "okhttp"})
/*     */   public static final class EventListener$Companion$NONE$1 extends EventListener {}
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\bæ\001\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H&¨\006\006"}, d2 = {"Lokhttp3/EventListener$Factory;", "", "create", "Lokhttp3/EventListener;", "call", "Lokhttp3/Call;", "okhttp"})
/*     */   public static interface Factory {
/*     */     @NotNull
/*     */     EventListener create(@NotNull Call param1Call);
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/EventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */