/*     */ package okhttp3.internal.connection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.Reference;
/*     */ import java.net.ConnectException;
/*     */ import java.net.ProtocolException;
/*     */ import java.net.Proxy;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketException;
/*     */ import java.net.UnknownServiceException;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.net.ssl.SSLPeerUnverifiedException;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.functions.Function0;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.jvm.internal.Lambda;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Address;
/*     */ import okhttp3.Call;
/*     */ import okhttp3.CertificatePinner;
/*     */ import okhttp3.Connection;
/*     */ import okhttp3.ConnectionSpec;
/*     */ import okhttp3.EventListener;
/*     */ import okhttp3.Handshake;
/*     */ import okhttp3.HttpUrl;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.Protocol;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.Route;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.concurrent.TaskRunner;
/*     */ import okhttp3.internal.http.ExchangeCodec;
/*     */ import okhttp3.internal.http.RealInterceptorChain;
/*     */ import okhttp3.internal.http1.Http1ExchangeCodec;
/*     */ import okhttp3.internal.http2.ErrorCode;
/*     */ import okhttp3.internal.http2.Http2Connection;
/*     */ import okhttp3.internal.http2.Http2ExchangeCodec;
/*     */ import okhttp3.internal.http2.Http2Stream;
/*     */ import okhttp3.internal.http2.Settings;
/*     */ import okhttp3.internal.http2.StreamResetException;
/*     */ import okhttp3.internal.platform.Platform;
/*     */ import okhttp3.internal.tls.OkHostnameVerifier;
/*     */ import okhttp3.internal.ws.RealWebSocket;
/*     */ import okio.BufferedSink;
/*     */ import okio.BufferedSource;
/*     */ import okio.Okio;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000ì\001\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020!\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\005\n\002\020\013\n\002\b\b\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\030\002\n\000\n\002\020 \n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\016\n\002\b\005\030\000 {2\0020\0012\0020\002:\001{B\025\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006¢\006\002\020\007J\006\0205\032\00206J\030\0207\032\0020\0352\006\0208\032\002092\006\020\022\032\0020\023H\002J>\020:\032\002062\006\020;\032\0020\t2\006\020<\032\0020\t2\006\020=\032\0020\t2\006\020>\032\0020\t2\006\020?\032\0020\0352\006\020@\032\0020A2\006\020B\032\0020CJ%\020D\032\002062\006\020E\032\0020F2\006\020G\032\0020\0062\006\020H\032\0020IH\000¢\006\002\bJJ(\020K\032\002062\006\020;\032\0020\t2\006\020<\032\0020\t2\006\020@\032\0020A2\006\020B\032\0020CH\002J\020\020L\032\002062\006\020M\032\0020NH\002J0\020O\032\002062\006\020;\032\0020\t2\006\020<\032\0020\t2\006\020=\032\0020\t2\006\020@\032\0020A2\006\020B\032\0020CH\002J*\020P\032\004\030\0010Q2\006\020<\032\0020\t2\006\020=\032\0020\t2\006\020R\032\0020Q2\006\0208\032\00209H\002J\b\020S\032\0020QH\002J(\020T\032\002062\006\020M\032\0020N2\006\020>\032\0020\t2\006\020@\032\0020A2\006\020B\032\0020CH\002J\n\020\022\032\004\030\0010\023H\026J\r\020U\032\00206H\000¢\006\002\bVJ%\020W\032\0020\0352\006\020X\032\0020Y2\016\020Z\032\n\022\004\022\0020\006\030\0010[H\000¢\006\002\b\\J\016\020]\032\0020\0352\006\020^\032\0020\035J\035\020_\032\0020`2\006\020E\032\0020F2\006\020a\032\0020bH\000¢\006\002\bcJ\025\020d\032\0020e2\006\020f\032\0020gH\000¢\006\002\bhJ\r\020 \032\00206H\000¢\006\002\biJ\r\020!\032\00206H\000¢\006\002\bjJ\030\020k\032\002062\006\020l\032\0020\0252\006\020m\032\0020nH\026J\020\020o\032\002062\006\020p\032\0020qH\026J\b\020%\032\0020&H\026J\b\020\005\032\0020\006H\026J\026\020r\032\0020\0352\f\020s\032\b\022\004\022\0020\0060[H\002J\b\0201\032\0020(H\026J\020\020t\032\002062\006\020>\032\0020\tH\002J\020\020u\032\0020\0352\006\0208\032\00209H\002J\b\020v\032\0020wH\026J\037\020x\032\002062\006\020@\032\0020\r2\b\020y\032\004\030\0010IH\000¢\006\002\bzR\016\020\b\032\0020\tX\016¢\006\002\n\000R\035\020\n\032\016\022\n\022\b\022\004\022\0020\r0\f0\013¢\006\b\n\000\032\004\b\016\020\017R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\020\020\021R\020\020\022\032\004\030\0010\023X\016¢\006\002\n\000R\020\020\024\032\004\030\0010\025X\016¢\006\002\n\000R\032\020\026\032\0020\027X\016¢\006\016\n\000\032\004\b\030\020\031\"\004\b\032\020\033R\024\020\034\032\0020\0358@X\004¢\006\006\032\004\b\036\020\037R\016\020 \032\0020\035X\016¢\006\002\n\000R\032\020!\032\0020\035X\016¢\006\016\n\000\032\004\b\"\020\037\"\004\b#\020$R\020\020%\032\004\030\0010&X\016¢\006\002\n\000R\020\020'\032\004\030\0010(X\016¢\006\002\n\000R\016\020)\032\0020\tX\016¢\006\002\n\000R\016\020\005\032\0020\006X\004¢\006\002\n\000R\032\020*\032\0020\tX\016¢\006\016\n\000\032\004\b+\020,\"\004\b-\020.R\020\020/\032\004\030\00100X\016¢\006\002\n\000R\020\0201\032\004\030\0010(X\016¢\006\002\n\000R\020\0202\032\004\030\00103X\016¢\006\002\n\000R\016\0204\032\0020\tX\016¢\006\002\n\000¨\006|"}, d2 = {"Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "Lokhttp3/Connection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "(Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Route;)V", "allocationLimit", "", "calls", "", "Ljava/lang/ref/Reference;", "Lokhttp3/internal/connection/RealCall;", "getCalls", "()Ljava/util/List;", "getConnectionPool", "()Lokhttp3/internal/connection/RealConnectionPool;", "handshake", "Lokhttp3/Handshake;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "idleAtNs", "", "getIdleAtNs$okhttp", "()J", "setIdleAtNs$okhttp", "(J)V", "isMultiplexed", "", "isMultiplexed$okhttp", "()Z", "noCoalescedConnections", "noNewExchanges", "getNoNewExchanges", "setNoNewExchanges", "(Z)V", "protocol", "Lokhttp3/Protocol;", "rawSocket", "Ljava/net/Socket;", "refusedStreamCount", "routeFailureCount", "getRouteFailureCount$okhttp", "()I", "setRouteFailureCount$okhttp", "(I)V", "sink", "Lokio/BufferedSink;", "socket", "source", "Lokio/BufferedSource;", "successCount", "cancel", "", "certificateSupportHost", "url", "Lokhttp3/HttpUrl;", "connect", "connectTimeout", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "call", "Lokhttp3/Call;", "eventListener", "Lokhttp3/EventListener;", "connectFailed", "client", "Lokhttp3/OkHttpClient;", "failedRoute", "failure", "Ljava/io/IOException;", "connectFailed$okhttp", "connectSocket", "connectTls", "connectionSpecSelector", "Lokhttp3/internal/connection/ConnectionSpecSelector;", "connectTunnel", "createTunnel", "Lokhttp3/Request;", "tunnelRequest", "createTunnelRequest", "establishProtocol", "incrementSuccessCount", "incrementSuccessCount$okhttp", "isEligible", "address", "Lokhttp3/Address;", "routes", "", "isEligible$okhttp", "isHealthy", "doExtensiveChecks", "newCodec", "Lokhttp3/internal/http/ExchangeCodec;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "newCodec$okhttp", "newWebSocketStreams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "exchange", "Lokhttp3/internal/connection/Exchange;", "newWebSocketStreams$okhttp", "noCoalescedConnections$okhttp", "noNewExchanges$okhttp", "onSettings", "connection", "settings", "Lokhttp3/internal/http2/Settings;", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "routeMatchesAny", "candidates", "startHttp2", "supportsUrl", "toString", "", "trackFailure", "e", "trackFailure$okhttp", "Companion", "okhttp"})
/*     */ public final class RealConnection extends Http2Connection.Listener implements Connection {
/*     */   private Socket rawSocket;
/*     */   private Socket socket;
/*     */   private Handshake handshake;
/*     */   private Protocol protocol;
/*     */   private Http2Connection http2Connection;
/*     */   private BufferedSource source;
/*     */   private BufferedSink sink;
/*     */   private boolean noNewExchanges;
/*     */   private boolean noCoalescedConnections;
/*     */   private int routeFailureCount;
/*     */   private int successCount;
/*     */   private int refusedStreamCount;
/*     */   private int allocationLimit;
/*     */   @NotNull
/*     */   private final List<Reference<RealCall>> calls;
/*     */   private long idleAtNs;
/*     */   @NotNull
/*     */   private final RealConnectionPool connectionPool;
/*     */   private final Route route;
/*     */   private static final String NPE_THROW_WITH_NULL = "throw with null exception";
/*     */   private static final int MAX_TUNNEL_ATTEMPTS = 21;
/*     */   public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public RealConnection(@NotNull RealConnectionPool connectionPool, @NotNull Route route) {
/*  88 */     this.connectionPool = connectionPool; this.route = route;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     this.allocationLimit = 1;
/*     */ 
/*     */     
/* 140 */     boolean bool = false; this.calls = new ArrayList<>();
/*     */ 
/*     */     
/* 143 */     this.idleAtNs = Long.MAX_VALUE;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\f\n\000\n\002\020 \n\002\030\002\n\000\020\000\032\b\022\004\022\0020\0020\001H\n¢\006\002\b\003"}, d2 = {"<anonymous>", "", "Ljava/security/cert/Certificate;", "invoke"})
/*     */   static final class RealConnection$connectTls$1
/*     */     extends Lambda
/*     */     implements Function0<List<? extends Certificate>>
/*     */   {
/*     */     @NotNull
/*     */     public final List<Certificate> invoke() {
/* 405 */       Intrinsics.checkNotNull(this.$certificatePinner.getCertificateChainCleaner$okhttp()); return this.$certificatePinner.getCertificateChainCleaner$okhttp().clean(this.$unverifiedHandshake.peerCertificates(), 
/* 406 */           this.$address.url().host());
/*     */     } RealConnection$connectTls$1(CertificatePinner param1CertificatePinner, Handshake param1Handshake, Address param1Address) { super(0); } }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\f\n\000\n\002\020 \n\002\030\002\n\000\020\000\032\b\022\004\022\0020\0020\001H\n¢\006\002\b\003"}, d2 = {"<anonymous>", "", "Ljava/security/cert/X509Certificate;", "invoke"})
/*     */   static final class RealConnection$connectTls$2 extends Lambda implements Function0<List<? extends X509Certificate>> { RealConnection$connectTls$2() { super(0); }
/*     */     @NotNull
/* 411 */     public final List<X509Certificate> invoke() { Intrinsics.checkNotNull(RealConnection.this.handshake); Iterable $this$map$iv = RealConnection.this.handshake.peerCertificates(); int $i$f$map = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 766 */       Iterable iterable1 = $this$map$iv; Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)); int $i$f$mapTo = 0;
/* 767 */       for (Object item$iv$iv : iterable1)
/* 768 */       { Certificate certificate = (Certificate)item$iv$iv; Collection collection = destination$iv$iv; int $i$a$-map-RealConnection$connectTls$2$1 = 0; if (certificate == null)
/* 769 */           throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");  }  return (List<X509Certificate>)destination$iv$iv; } }
/*     */   
/*     */   @NotNull public final RealConnectionPool getConnectionPool() { return this.connectionPool; }
/*     */   public final boolean getNoNewExchanges() { return this.noNewExchanges; }
/*     */   public final void setNoNewExchanges(boolean <set-?>) { this.noNewExchanges = <set-?>; }
/* 774 */   public final int getRouteFailureCount$okhttp() { return this.routeFailureCount; } public final void setRouteFailureCount$okhttp(int <set-?>) { this.routeFailureCount = <set-?>; } @NotNull public final List<Reference<RealCall>> getCalls() { return this.calls; } public final long getIdleAtNs$okhttp() { return this.idleAtNs; } public final void setIdleAtNs$okhttp(long <set-?>) { this.idleAtNs = <set-?>; } public final boolean isMultiplexed$okhttp() { return (this.http2Connection != null); } public final synchronized void noNewExchanges$okhttp() { this.noNewExchanges = true; } public final synchronized void noCoalescedConnections$okhttp() { this.noCoalescedConnections = true; } private final boolean supportsUrl(HttpUrl url) { Object $this$assertThreadHoldsLock$iv = this; int $i$f$assertThreadHoldsLock = 0; if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
/* 775 */     { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); }  HttpUrl routeUrl = this.route.address().url(); if (url.port() != routeUrl.port()) return false;  if (Intrinsics.areEqual(url.host(), routeUrl.host())) return true;  Intrinsics.checkNotNull(this.handshake); return (!this.noCoalescedConnections && this.handshake != null && certificateSupportHost(url, this.handshake)); }
/*     */   public final synchronized void incrementSuccessCount$okhttp() { int i; this.successCount = (i = this.successCount) + 1; }
/*     */   public final void connect(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, @NotNull Call call, @NotNull EventListener eventListener) { Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(eventListener, "eventListener"); boolean bool1 = (this.protocol == null) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-RealConnection$connect$1 = 0; String str = "already connected"; throw (Throwable)new IllegalStateException(str.toString()); }  RouteException routeException = (RouteException)null; List<ConnectionSpec> connectionSpecs = this.route.address().connectionSpecs(); ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(connectionSpecs); if (this.route.address().sslSocketFactory() == null) { if (!connectionSpecs.contains(ConnectionSpec.CLEARTEXT)) throw (Throwable)new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));  String host = this.route.address().url().host(); if (!Platform.Companion.get().isCleartextTrafficPermitted(host)) throw (Throwable)new RouteException(new UnknownServiceException("CLEARTEXT communication to " + host + " not permitted by network security policy"));  } else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) { throw (Throwable)new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS")); }  while (true) { try { if (this.route.requiresTunnel()) { connectTunnel(connectTimeout, readTimeout, writeTimeout, call, eventListener); if (this.rawSocket == null) break;  } else { connectSocket(connectTimeout, readTimeout, call, eventListener); }  establishProtocol(connectionSpecSelector, pingIntervalMillis, call, eventListener); eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), this.protocol); break; } catch (IOException e) { if (this.socket != null) { Util.closeQuietly(this.socket); } else {  }  if (this.rawSocket != null) { Util.closeQuietly(this.rawSocket); } else {  }  this.socket = (Socket)null; this.rawSocket = (Socket)null; this.source = (BufferedSource)null; this.sink = (BufferedSink)null; this.handshake = (Handshake)null; this.protocol = (Protocol)null; this.http2Connection = (Http2Connection)null; this.allocationLimit = 1; eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), null, e); if (routeException == null) { routeException = new RouteException(e); } else { routeException.addConnectException(e); }  if (!connectionRetryEnabled || !connectionSpecSelector.connectionFailed(e)) throw (Throwable)routeException;  }  }  if (this.route.requiresTunnel() && this.rawSocket == null) throw (Throwable)new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));  this.idleAtNs = System.nanoTime(); } private final void connectTunnel(int connectTimeout, int readTimeout, int writeTimeout, Call call, EventListener eventListener) throws IOException { Request tunnelRequest = createTunnelRequest(); HttpUrl url = tunnelRequest.url(); for (byte b1 = 0, b2 = 21; b1 < b2; ) { connectSocket(connectTimeout, readTimeout, call, eventListener); if (createTunnel(readTimeout, writeTimeout, tunnelRequest, url) != null) { tunnelRequest = createTunnel(readTimeout, writeTimeout, tunnelRequest, url); if (this.rawSocket != null) { Util.closeQuietly(this.rawSocket); } else {  }  this.rawSocket = (Socket)null; this.sink = (BufferedSink)null; this.source = (BufferedSource)null; eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null); b1++; }  createTunnel(readTimeout, writeTimeout, tunnelRequest, url); }  } private final void connectSocket(int connectTimeout, int readTimeout, Call call, EventListener eventListener) throws IOException { Proxy proxy = this.route.proxy(); Address address = this.route.address(); if (proxy.type() == null) proxy.type();  switch (RealConnection$WhenMappings.$EnumSwitchMapping$0[proxy.type().ordinal()]) { case 1: case 2: Intrinsics.checkNotNull(address.socketFactory().createSocket());default: break; }  Socket rawSocket = new Socket(proxy); this.rawSocket = rawSocket; eventListener.connectStart(call, this.route.socketAddress(), proxy); rawSocket.setSoTimeout(readTimeout); try { Platform.Companion.get().connectSocket(rawSocket, this.route.socketAddress(), connectTimeout); } catch (ConnectException e) { ConnectException connectException1 = new ConnectException("Failed to connect to " + this.route.socketAddress()); boolean bool1 = false, bool2 = false; ConnectException $this$apply = connectException1; int $i$a$-apply-RealConnection$connectSocket$1 = 0; $this$apply.initCause(e); throw (Throwable)connectException1; }  try { this.source = Okio.buffer(Okio.source(rawSocket)); this.sink = Okio.buffer(Okio.sink(rawSocket)); } catch (NullPointerException npe) { if (Intrinsics.areEqual(npe.getMessage(), "throw with null exception")) throw (Throwable)new IOException((Throwable)npe);  }  } private final void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int pingIntervalMillis, Call call, EventListener eventListener) throws IOException { if (this.route.address().sslSocketFactory() == null) { if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) { this.socket = this.rawSocket; this.protocol = Protocol.H2_PRIOR_KNOWLEDGE; startHttp2(pingIntervalMillis); return; }  this.socket = this.rawSocket; this.protocol = Protocol.HTTP_1_1; return; }  eventListener.secureConnectStart(call); connectTls(connectionSpecSelector); eventListener.secureConnectEnd(call, this.handshake); if (this.protocol == Protocol.HTTP_2) startHttp2(pingIntervalMillis);  } private final void startHttp2(int pingIntervalMillis) throws IOException { Intrinsics.checkNotNull(this.socket); Socket socket = this.socket; Intrinsics.checkNotNull(this.source); BufferedSource source = this.source; Intrinsics.checkNotNull(this.sink); BufferedSink sink = this.sink; socket.setSoTimeout(0); Http2Connection http2Connection = (new Http2Connection.Builder(true, TaskRunner.INSTANCE)).socket(socket, this.route.address().url().host(), source, sink).listener(this).pingIntervalMillis(pingIntervalMillis).build(); this.http2Connection = http2Connection; this.allocationLimit = Http2Connection.Companion.getDEFAULT_SETTINGS().getMaxConcurrentStreams(); Http2Connection.start$default(http2Connection, false, null, 3, null); } private final void connectTls(ConnectionSpecSelector connectionSpecSelector) throws IOException { Address address = this.route.address(); SSLSocketFactory sslSocketFactory = address.sslSocketFactory(); boolean success = false; SSLSocket sslSocket = (SSLSocket)null; try { Intrinsics.checkNotNull(sslSocketFactory); if (sslSocketFactory.createSocket(this.rawSocket, address.url().host(), address.url().port(), true) == null) throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");  sslSocket = (SSLSocket)sslSocketFactory.createSocket(this.rawSocket, address.url().host(), address.url().port(), true); ConnectionSpec connectionSpec = connectionSpecSelector.configureSecureSocket(sslSocket); if (connectionSpec.supportsTlsExtensions()) Platform.Companion.get().configureTlsExtensions(sslSocket, address.url().host(), address.protocols());  sslSocket.startHandshake(); SSLSession sslSocketSession = sslSocket.getSession(); Intrinsics.checkNotNullExpressionValue(sslSocketSession, "sslSocketSession"); Handshake unverifiedHandshake = Handshake.Companion.get(sslSocketSession); Intrinsics.checkNotNull(address.hostnameVerifier()); if (!address.hostnameVerifier().verify(address.url().host(), sslSocketSession)) { List peerCertificates = unverifiedHandshake.peerCertificates(); List list1 = peerCertificates; boolean bool = false; if (!list1.isEmpty()) { if (peerCertificates.get(0) == null) throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");  X509Certificate cert = (X509Certificate)peerCertificates.get(0); Intrinsics.checkNotNullExpressionValue(cert.getSubjectDN(), "cert.subjectDN"); throw (Throwable)new SSLPeerUnverifiedException(StringsKt.trimMargin$default("\n              |Hostname " + address.url().host() + " not verified:\n              |    certificate: " + CertificatePinner.Companion.pin(cert) + "\n              |    DN: " + cert.getSubjectDN().getName() + "\n              |    subjectAltNames: " + OkHostnameVerifier.INSTANCE.allSubjectAltNames(cert) + "\n              ", null, 1, null)); }  throw (Throwable)new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified (no certificates)"); }  Intrinsics.checkNotNull(address.certificatePinner()); CertificatePinner certificatePinner = address.certificatePinner(); this.handshake = new Handshake(unverifiedHandshake.tlsVersion(), unverifiedHandshake.cipherSuite(), unverifiedHandshake.localCertificates(), new RealConnection$connectTls$1(certificatePinner, unverifiedHandshake, address)); certificatePinner.check$okhttp(address.url().host(), new RealConnection$connectTls$2()); String maybeProtocol = connectionSpec.supportsTlsExtensions() ? Platform.Companion.get().getSelectedProtocol(sslSocket) : null; this.socket = sslSocket; this.source = Okio.buffer(Okio.source(sslSocket)); this.sink = Okio.buffer(Okio.sink(sslSocket)); this.protocol = (maybeProtocol != null) ? Protocol.Companion.get(maybeProtocol) : Protocol.HTTP_1_1; } finally { if (sslSocket != null) Platform.Companion.get().afterHandshake(sslSocket);  if (sslSocket != null) { Util.closeQuietly(sslSocket); } else {  }  }  } private final Request createTunnel(int readTimeout, int writeTimeout, Request tunnelRequest, HttpUrl url) throws IOException { Response response; Request nextRequest = tunnelRequest; String requestLine = "CONNECT " + Util.toHostHeader(url, true) + " HTTP/1.1"; while (true) { Intrinsics.checkNotNull(this.source); BufferedSource source = this.source; Intrinsics.checkNotNull(this.sink); BufferedSink sink = this.sink; Http1ExchangeCodec tunnelCodec = new Http1ExchangeCodec(null, this, source, sink); source.timeout().timeout(readTimeout, TimeUnit.MILLISECONDS); sink.timeout().timeout(writeTimeout, TimeUnit.MILLISECONDS); tunnelCodec.writeRequest(nextRequest.headers(), requestLine); tunnelCodec.finishRequest(); Intrinsics.checkNotNull(tunnelCodec.readResponseHeaders(false)); response = tunnelCodec.readResponseHeaders(false).request(nextRequest).build(); tunnelCodec.skipConnectBody(response); switch (response.code()) { case 200: if (!source.getBuffer().exhausted() || !sink.getBuffer().exhausted()) throw (Throwable)new IOException("TLS tunnel buffered too many bytes!");  return null;case 407: if (this.route.address().proxyAuthenticator().authenticate(this.route, response) != null) { nextRequest = this.route.address().proxyAuthenticator().authenticate(this.route, response); if (StringsKt.equals("close", Response.header$default(response, "Connection", null, 2, null), true)) return nextRequest;  continue; }  this.route.address().proxyAuthenticator().authenticate(this.route, response); throw (Throwable)new IOException("Failed to authenticate with proxy"); }  break; }  throw (Throwable)new IOException("Unexpected response code for CONNECT: " + response.code()); } private final Request createTunnelRequest() throws IOException { Request proxyConnectRequest = (new Request.Builder()).url(this.route.address().url()).method("CONNECT", null).header("Host", Util.toHostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", "okhttp/4.9.3").build(); Response fakeAuthChallengeResponse = (new Response.Builder()).request(proxyConnectRequest).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header("Proxy-Authenticate", "OkHttp-Preemptive").build(); Request authenticatedRequest = this.route.address().proxyAuthenticator().authenticate(this.route, fakeAuthChallengeResponse); if (authenticatedRequest == null); return proxyConnectRequest; } public final boolean isEligible$okhttp(@NotNull Address address, @Nullable List<Route> routes) { Intrinsics.checkNotNullParameter(address, "address"); Object $this$assertThreadHoldsLock$iv = this; int $i$f$assertThreadHoldsLock = 0; if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); }  if (this.calls.size() >= this.allocationLimit || this.noNewExchanges) return false;  if (!this.route.address().equalsNonHost$okhttp(address)) return false;  if (Intrinsics.areEqual(address.url().host(), route().address().url().host())) return true;  if (this.http2Connection == null) return false;  if (routes == null || !routeMatchesAny(routes)) return false;  if (address.hostnameVerifier() != OkHostnameVerifier.INSTANCE) return false;  if (!supportsUrl(address.url())) return false;  try { Intrinsics.checkNotNull(address.certificatePinner()); Intrinsics.checkNotNull(handshake()); address.certificatePinner().check(address.url().host(), handshake().peerCertificates()); } catch (SSLPeerUnverifiedException _) { return false; }  return true; } private final boolean routeMatchesAny(List candidates) { // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: checkcast java/lang/Iterable
/*     */     //   4: astore_2
/*     */     //   5: iconst_0
/*     */     //   6: istore_3
/*     */     //   7: aload_2
/*     */     //   8: instanceof java/util/Collection
/*     */     //   11: ifeq -> 30
/*     */     //   14: aload_2
/*     */     //   15: checkcast java/util/Collection
/*     */     //   18: invokeinterface isEmpty : ()Z
/*     */     //   23: ifeq -> 30
/*     */     //   26: iconst_0
/*     */     //   27: goto -> 128
/*     */     //   30: aload_2
/*     */     //   31: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   36: astore #4
/*     */     //   38: aload #4
/*     */     //   40: invokeinterface hasNext : ()Z
/*     */     //   45: ifeq -> 127
/*     */     //   48: aload #4
/*     */     //   50: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   55: astore #5
/*     */     //   57: aload #5
/*     */     //   59: checkcast okhttp3/Route
/*     */     //   62: astore #6
/*     */     //   64: iconst_0
/*     */     //   65: istore #7
/*     */     //   67: aload #6
/*     */     //   69: invokevirtual proxy : ()Ljava/net/Proxy;
/*     */     //   72: invokevirtual type : ()Ljava/net/Proxy$Type;
/*     */     //   75: getstatic java/net/Proxy$Type.DIRECT : Ljava/net/Proxy$Type;
/*     */     //   78: if_acmpne -> 119
/*     */     //   81: aload_0
/*     */     //   82: getfield route : Lokhttp3/Route;
/*     */     //   85: invokevirtual proxy : ()Ljava/net/Proxy;
/*     */     //   88: invokevirtual type : ()Ljava/net/Proxy$Type;
/*     */     //   91: getstatic java/net/Proxy$Type.DIRECT : Ljava/net/Proxy$Type;
/*     */     //   94: if_acmpne -> 119
/*     */     //   97: aload_0
/*     */     //   98: getfield route : Lokhttp3/Route;
/*     */     //   101: invokevirtual socketAddress : ()Ljava/net/InetSocketAddress;
/*     */     //   104: aload #6
/*     */     //   106: invokevirtual socketAddress : ()Ljava/net/InetSocketAddress;
/*     */     //   109: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   112: ifeq -> 119
/*     */     //   115: iconst_1
/*     */     //   116: goto -> 120
/*     */     //   119: iconst_0
/*     */     //   120: ifeq -> 38
/*     */     //   123: iconst_1
/*     */     //   124: goto -> 128
/*     */     //   127: iconst_0
/*     */     //   128: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #574	-> 0
/*     */     //   #771	-> 7
/*     */     //   #772	-> 30
/*     */     //   #772	-> 38
/*     */     //   #575	-> 67
/*     */     //   #576	-> 67
/*     */     //   #577	-> 67
/*     */     //   #575	-> 67
/*     */     //   #576	-> 81
/*     */     //   #773	-> 127
/*     */     //   #574	-> 128
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   64	56	6	it	Lokhttp3/Route;
/*     */     //   67	53	7	$i$a$-any-RealConnection$routeMatchesAny$1	I
/*     */     //   57	70	5	element$iv	Ljava/lang/Object;
/*     */     //   5	123	2	$this$any$iv	Ljava/lang/Iterable;
/*     */     //   7	121	3	$i$f$any	I
/*     */     //   0	129	0	this	Lokhttp3/internal/connection/RealConnection;
/*     */     //   0	129	1	candidates	Ljava/util/List; } private final boolean certificateSupportHost(HttpUrl url, Handshake handshake) { List peerCertificates = handshake.peerCertificates(); List list1 = peerCertificates; boolean bool = false; if (!list1.isEmpty()) { if (peerCertificates.get(0) == null) throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");  if (OkHostnameVerifier.INSTANCE.verify(url.host(), (X509Certificate)peerCertificates.get(0))); }  return false; } @NotNull public final ExchangeCodec newCodec$okhttp(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) throws SocketException { Intrinsics.checkNotNullParameter(client, "client"); Intrinsics.checkNotNullParameter(chain, "chain"); Intrinsics.checkNotNull(this.socket); Socket socket = this.socket; Intrinsics.checkNotNull(this.source); BufferedSource source = this.source; Intrinsics.checkNotNull(this.sink); BufferedSink sink = this.sink; Http2Connection http2Connection = this.http2Connection; socket.setSoTimeout(chain.readTimeoutMillis()); source.timeout().timeout(chain.getReadTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS); sink.timeout().timeout(chain.getWriteTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS); return (http2Connection != null) ? (ExchangeCodec)new Http2ExchangeCodec(client, this, chain, http2Connection) : (ExchangeCodec)new Http1ExchangeCodec(client, this, source, sink); } @NotNull public final RealWebSocket.Streams newWebSocketStreams$okhttp(@NotNull Exchange exchange) throws SocketException { Intrinsics.checkNotNullParameter(exchange, "exchange"); Intrinsics.checkNotNull(this.socket); Socket socket = this.socket; Intrinsics.checkNotNull(this.source); BufferedSource source = this.source; Intrinsics.checkNotNull(this.sink); BufferedSink sink = this.sink; socket.setSoTimeout(0); noNewExchanges$okhttp(); return new RealConnection$newWebSocketStreams$1(exchange, source, sink, true, source, sink); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\021\n\000\n\002\030\002\n\000\n\002\020\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026¨\006\004"}, d2 = {"okhttp3/internal/connection/RealConnection$newWebSocketStreams$1", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "close", "", "okhttp"}) public static final class RealConnection$newWebSocketStreams$1 extends RealWebSocket.Streams {
/* 778 */     RealConnection$newWebSocketStreams$1(Exchange $captured_local_variable$0, BufferedSource $captured_local_variable$1, BufferedSink $captured_local_variable$2, boolean $super_call_param$3, BufferedSource $super_call_param$4, BufferedSink $super_call_param$5) { super($super_call_param$3, $super_call_param$4, $super_call_param$5); } public void close() { this.$exchange.bodyComplete(-1L, true, true, null); } } @NotNull public Route route() { return this.route; } public final void cancel() { if (this.rawSocket != null) { Util.closeQuietly(this.rawSocket); } else {  }  } @NotNull public Socket socket() { Intrinsics.checkNotNull(this.socket); return this.socket; } public final boolean isHealthy(boolean doExtensiveChecks) { Object $this$assertThreadDoesntHoldLock$iv = this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
/* 779 */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv);
/*     */     } 
/*     */     long nowNs = System.nanoTime();
/*     */     Intrinsics.checkNotNull(this.rawSocket);
/*     */     Socket rawSocket = this.rawSocket;
/*     */     Intrinsics.checkNotNull(this.socket);
/*     */     Socket socket = this.socket;
/*     */     Intrinsics.checkNotNull(this.source);
/*     */     BufferedSource source = this.source;
/*     */     if (rawSocket.isClosed() || socket.isClosed() || socket.isInputShutdown() || socket.isOutputShutdown())
/*     */       return false; 
/*     */     Http2Connection http2Connection = this.http2Connection;
/*     */     if (http2Connection != null)
/*     */       return http2Connection.isHealthy(nowNs); 
/*     */     RealConnection realConnection = this;
/*     */     boolean bool = false;
/*     */     synchronized (false) {
/*     */       int $i$a$-synchronized-RealConnection$isHealthy$idleDurationNs$1 = 0;
/*     */       long l = nowNs - this.idleAtNs;
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=null} */
/*     */     } 
/*     */     long idleDurationNs = l;
/*     */     if (idleDurationNs >= 10000000000L && doExtensiveChecks)
/*     */       return Util.isHealthy(socket, source); 
/*     */     return true; }
/*     */ 
/*     */   
/*     */   public void onStream(@NotNull Http2Stream stream) throws IOException {
/*     */     Intrinsics.checkNotNullParameter(stream, "stream");
/*     */     stream.close(ErrorCode.REFUSED_STREAM, null);
/*     */   }
/*     */   
/*     */   public synchronized void onSettings(@NotNull Http2Connection connection, @NotNull Settings settings) {
/*     */     Intrinsics.checkNotNullParameter(connection, "connection");
/*     */     Intrinsics.checkNotNullParameter(settings, "settings");
/*     */     this.allocationLimit = settings.getMaxConcurrentStreams();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Handshake handshake() {
/*     */     return this.handshake;
/*     */   }
/*     */   
/*     */   public final void connectFailed$okhttp(@NotNull OkHttpClient client, @NotNull Route failedRoute, @NotNull IOException failure) {
/*     */     Intrinsics.checkNotNullParameter(client, "client");
/*     */     Intrinsics.checkNotNullParameter(failedRoute, "failedRoute");
/*     */     Intrinsics.checkNotNullParameter(failure, "failure");
/*     */     if (failedRoute.proxy().type() != Proxy.Type.DIRECT) {
/*     */       Address address = failedRoute.address();
/*     */       address.proxySelector().connectFailed(address.url().uri(), failedRoute.proxy().address(), failure);
/*     */     } 
/*     */     client.getRouteDatabase().failed(failedRoute);
/*     */   }
/*     */   
/*     */   public final synchronized void trackFailure$okhttp(@NotNull RealCall call, @Nullable IOException e) {
/*     */     Intrinsics.checkNotNullParameter(call, "call");
/*     */     if (e instanceof StreamResetException) {
/*     */       if (((StreamResetException)e).errorCode == ErrorCode.REFUSED_STREAM) {
/*     */         int i;
/*     */         this.refusedStreamCount = (i = this.refusedStreamCount) + 1;
/*     */         if (this.refusedStreamCount > 1) {
/*     */           this.noNewExchanges = true;
/*     */           this.routeFailureCount = (i = this.routeFailureCount) + 1;
/*     */         } 
/*     */       } else if (((StreamResetException)e).errorCode != ErrorCode.CANCEL || !call.isCanceled()) {
/*     */         this.noNewExchanges = true;
/*     */         int i;
/*     */         this.routeFailureCount = (i = this.routeFailureCount) + 1;
/*     */       } 
/*     */     } else if (!isMultiplexed$okhttp() || e instanceof okhttp3.internal.http2.ConnectionShutdownException) {
/*     */       this.noNewExchanges = true;
/*     */       if (this.successCount == 0) {
/*     */         if (e != null)
/*     */           connectFailed$okhttp(call.getClient(), this.route, e); 
/*     */         int i;
/*     */         this.routeFailureCount = (i = this.routeFailureCount) + 1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Protocol protocol() {
/*     */     Intrinsics.checkNotNull(this.protocol);
/*     */     return this.protocol;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/*     */     if (this.handshake == null || this.handshake.cipherSuite() == null)
/*     */       this.handshake.cipherSuite(); 
/*     */     return "Connection{" + this.route.address().url().host() + ':' + this.route.address().url().port() + ',' + " proxy=" + this.route.proxy() + " hostAddress=" + this.route.socketAddress() + " cipherSuite=" + "none" + " protocol=" + this.protocol + '}';
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\000\n\002\020\b\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J&\020\t\032\0020\n2\006\020\013\032\0020\f2\006\020\r\032\0020\0162\006\020\017\032\0020\0202\006\020\021\032\0020\004R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\006XT¢\006\002\n\000R\016\020\007\032\0020\bXT¢\006\002\n\000¨\006\022"}, d2 = {"Lokhttp3/internal/connection/RealConnection$Companion;", "", "()V", "IDLE_CONNECTION_HEALTHY_NS", "", "MAX_TUNNEL_ATTEMPTS", "", "NPE_THROW_WITH_NULL", "", "newTestConnection", "Lokhttp3/internal/connection/RealConnection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "socket", "Ljava/net/Socket;", "idleAtNs", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @NotNull
/*     */     public final RealConnection newTestConnection(@NotNull RealConnectionPool connectionPool, @NotNull Route route, @NotNull Socket socket, long idleAtNs) {
/*     */       Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
/*     */       Intrinsics.checkNotNullParameter(route, "route");
/*     */       Intrinsics.checkNotNullParameter(socket, "socket");
/*     */       RealConnection result = new RealConnection(connectionPool, route);
/*     */       result.socket = socket;
/*     */       result.setIdleAtNs$okhttp(idleAtNs);
/*     */       return result;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/RealConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */