/*      */ package okhttp3;
/*      */ 
/*      */ import java.net.Proxy;
/*      */ import java.net.ProxySelector;
/*      */ import java.time.Duration;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import javax.net.SocketFactory;
/*      */ import javax.net.ssl.HostnameVerifier;
/*      */ import javax.net.ssl.SSLSocketFactory;
/*      */ import javax.net.ssl.X509TrustManager;
/*      */ import kotlin.Deprecated;
/*      */ import kotlin.DeprecationLevel;
/*      */ import kotlin.Metadata;
/*      */ import kotlin.ReplaceWith;
/*      */ import kotlin.collections.CollectionsKt;
/*      */ import kotlin.jvm.JvmName;
/*      */ import kotlin.jvm.functions.Function1;
/*      */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*      */ import kotlin.jvm.internal.Intrinsics;
/*      */ import okhttp3.internal.Util;
/*      */ import okhttp3.internal.concurrent.TaskRunner;
/*      */ import okhttp3.internal.connection.RealCall;
/*      */ import okhttp3.internal.connection.RouteDatabase;
/*      */ import okhttp3.internal.platform.Platform;
/*      */ import okhttp3.internal.tls.CertificateChainCleaner;
/*      */ import okhttp3.internal.tls.OkHostnameVerifier;
/*      */ import okhttp3.internal.ws.RealWebSocket;
/*      */ import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000î\001\n\002\030\002\n\002\020\032\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\023\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\n\n\002\020\002\n\002\b\004\b\026\030\000 y2\0020\0012\0020\0022\0020\003:\002xyB\007\b\026¢\006\002\020\004B\017\b\000\022\006\020\005\032\0020\006¢\006\002\020\007J\r\020\b\032\0020\tH\007¢\006\002\bSJ\017\020\013\032\004\030\0010\fH\007¢\006\002\bTJ\r\020\016\032\0020\017H\007¢\006\002\bUJ\r\020\024\032\0020\025H\007¢\006\002\bVJ\r\020\027\032\0020\017H\007¢\006\002\bWJ\r\020\030\032\0020\031H\007¢\006\002\bXJ\023\020\033\032\b\022\004\022\0020\0350\034H\007¢\006\002\bYJ\r\020\037\032\0020 H\007¢\006\002\bZJ\r\020\"\032\0020#H\007¢\006\002\b[J\r\020%\032\0020&H\007¢\006\002\b\\J\r\020(\032\0020)H\007¢\006\002\b]J\r\020+\032\0020,H\007¢\006\002\b^J\r\020.\032\0020,H\007¢\006\002\b_J\r\020/\032\00200H\007¢\006\002\b`J\023\0202\032\b\022\004\022\002030\034H\007¢\006\002\baJ\023\0207\032\b\022\004\022\002030\034H\007¢\006\002\bbJ\b\020c\032\0020\006H\026J\020\020d\032\0020e2\006\020f\032\0020gH\026J\030\020h\032\0020i2\006\020f\032\0020g2\006\020j\032\0020kH\026J\r\0208\032\0020\017H\007¢\006\002\blJ\023\0209\032\b\022\004\022\0020:0\034H\007¢\006\002\bmJ\017\020;\032\004\030\0010<H\007¢\006\002\bnJ\r\020>\032\0020\tH\007¢\006\002\boJ\r\020?\032\0020@H\007¢\006\002\bpJ\r\020B\032\0020\017H\007¢\006\002\bqJ\r\020C\032\0020,H\007¢\006\002\brJ\r\020H\032\0020IH\007¢\006\002\bsJ\r\020K\032\0020LH\007¢\006\002\btJ\b\020u\032\0020vH\002J\r\020O\032\0020\017H\007¢\006\002\bwR\023\020\b\032\0020\t8G¢\006\b\n\000\032\004\b\b\020\nR\025\020\013\032\004\030\0010\f8G¢\006\b\n\000\032\004\b\013\020\rR\023\020\016\032\0020\0178G¢\006\b\n\000\032\004\b\016\020\020R\025\020\021\032\004\030\0010\0228G¢\006\b\n\000\032\004\b\021\020\023R\023\020\024\032\0020\0258G¢\006\b\n\000\032\004\b\024\020\026R\023\020\027\032\0020\0178G¢\006\b\n\000\032\004\b\027\020\020R\023\020\030\032\0020\0318G¢\006\b\n\000\032\004\b\030\020\032R\031\020\033\032\b\022\004\022\0020\0350\0348G¢\006\b\n\000\032\004\b\033\020\036R\023\020\037\032\0020 8G¢\006\b\n\000\032\004\b\037\020!R\023\020\"\032\0020#8G¢\006\b\n\000\032\004\b\"\020$R\023\020%\032\0020&8G¢\006\b\n\000\032\004\b%\020'R\023\020(\032\0020)8G¢\006\b\n\000\032\004\b(\020*R\023\020+\032\0020,8G¢\006\b\n\000\032\004\b+\020-R\023\020.\032\0020,8G¢\006\b\n\000\032\004\b.\020-R\023\020/\032\002008G¢\006\b\n\000\032\004\b/\0201R\031\0202\032\b\022\004\022\002030\0348G¢\006\b\n\000\032\004\b2\020\036R\023\0204\032\002058G¢\006\b\n\000\032\004\b4\0206R\031\0207\032\b\022\004\022\002030\0348G¢\006\b\n\000\032\004\b7\020\036R\023\0208\032\0020\0178G¢\006\b\n\000\032\004\b8\020\020R\031\0209\032\b\022\004\022\0020:0\0348G¢\006\b\n\000\032\004\b9\020\036R\025\020;\032\004\030\0010<8G¢\006\b\n\000\032\004\b;\020=R\023\020>\032\0020\t8G¢\006\b\n\000\032\004\b>\020\nR\023\020?\032\0020@8G¢\006\b\n\000\032\004\b?\020AR\023\020B\032\0020\0178G¢\006\b\n\000\032\004\bB\020\020R\023\020C\032\0020,8G¢\006\b\n\000\032\004\bC\020-R\021\020D\032\0020E¢\006\b\n\000\032\004\bF\020GR\023\020H\032\0020I8G¢\006\b\n\000\032\004\bH\020JR\021\020K\032\0020L8G¢\006\006\032\004\bK\020MR\020\020N\032\004\030\0010LX\004¢\006\002\n\000R\023\020O\032\0020\0178G¢\006\b\n\000\032\004\bO\020\020R\025\020P\032\004\030\0010Q8G¢\006\b\n\000\032\004\bP\020R¨\006z"}, d2 = {"Lokhttp3/OkHttpClient;", "", "Lokhttp3/Call$Factory;", "Lokhttp3/WebSocket$Factory;", "()V", "builder", "Lokhttp3/OkHttpClient$Builder;", "(Lokhttp3/OkHttpClient$Builder;)V", "authenticator", "Lokhttp3/Authenticator;", "()Lokhttp3/Authenticator;", "cache", "Lokhttp3/Cache;", "()Lokhttp3/Cache;", "callTimeoutMillis", "", "()I", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "certificatePinner", "Lokhttp3/CertificatePinner;", "()Lokhttp3/CertificatePinner;", "connectTimeoutMillis", "connectionPool", "Lokhttp3/ConnectionPool;", "()Lokhttp3/ConnectionPool;", "connectionSpecs", "", "Lokhttp3/ConnectionSpec;", "()Ljava/util/List;", "cookieJar", "Lokhttp3/CookieJar;", "()Lokhttp3/CookieJar;", "dispatcher", "Lokhttp3/Dispatcher;", "()Lokhttp3/Dispatcher;", "dns", "Lokhttp3/Dns;", "()Lokhttp3/Dns;", "eventListenerFactory", "Lokhttp3/EventListener$Factory;", "()Lokhttp3/EventListener$Factory;", "followRedirects", "", "()Z", "followSslRedirects", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "()Ljavax/net/ssl/HostnameVerifier;", "interceptors", "Lokhttp3/Interceptor;", "minWebSocketMessageToCompress", "", "()J", "networkInterceptors", "pingIntervalMillis", "protocols", "Lokhttp3/Protocol;", "proxy", "Ljava/net/Proxy;", "()Ljava/net/Proxy;", "proxyAuthenticator", "proxySelector", "Ljava/net/ProxySelector;", "()Ljava/net/ProxySelector;", "readTimeoutMillis", "retryOnConnectionFailure", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase", "()Lokhttp3/internal/connection/RouteDatabase;", "socketFactory", "Ljavax/net/SocketFactory;", "()Ljavax/net/SocketFactory;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "()Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactoryOrNull", "writeTimeoutMillis", "x509TrustManager", "Ljavax/net/ssl/X509TrustManager;", "()Ljavax/net/ssl/X509TrustManager;", "-deprecated_authenticator", "-deprecated_cache", "-deprecated_callTimeoutMillis", "-deprecated_certificatePinner", "-deprecated_connectTimeoutMillis", "-deprecated_connectionPool", "-deprecated_connectionSpecs", "-deprecated_cookieJar", "-deprecated_dispatcher", "-deprecated_dns", "-deprecated_eventListenerFactory", "-deprecated_followRedirects", "-deprecated_followSslRedirects", "-deprecated_hostnameVerifier", "-deprecated_interceptors", "-deprecated_networkInterceptors", "newBuilder", "newCall", "Lokhttp3/Call;", "request", "Lokhttp3/Request;", "newWebSocket", "Lokhttp3/WebSocket;", "listener", "Lokhttp3/WebSocketListener;", "-deprecated_pingIntervalMillis", "-deprecated_protocols", "-deprecated_proxy", "-deprecated_proxyAuthenticator", "-deprecated_proxySelector", "-deprecated_readTimeoutMillis", "-deprecated_retryOnConnectionFailure", "-deprecated_socketFactory", "-deprecated_sslSocketFactory", "verifyClientState", "", "-deprecated_writeTimeoutMillis", "Builder", "Companion", "okhttp"})
/*      */ public class OkHttpClient
/*      */   implements Cloneable, Call.Factory, WebSocket.Factory
/*      */ {
/*      */   @NotNull
/*      */   private final Dispatcher dispatcher;
/*      */   @NotNull
/*      */   private final ConnectionPool connectionPool;
/*      */   @NotNull
/*      */   private final List<Interceptor> interceptors;
/*      */   @NotNull
/*      */   private final List<Interceptor> networkInterceptors;
/*      */   @NotNull
/*      */   private final EventListener.Factory eventListenerFactory;
/*      */   private final boolean retryOnConnectionFailure;
/*      */   @NotNull
/*      */   private final Authenticator authenticator;
/*      */   private final boolean followRedirects;
/*      */   private final boolean followSslRedirects;
/*      */   @NotNull
/*      */   private final CookieJar cookieJar;
/*      */   @Nullable
/*      */   private final Cache cache;
/*      */   @NotNull
/*      */   private final Dns dns;
/*      */   @Nullable
/*      */   private final Proxy proxy;
/*      */   @NotNull
/*      */   private final ProxySelector proxySelector;
/*      */   @NotNull
/*      */   private final Authenticator proxyAuthenticator;
/*      */   @NotNull
/*      */   private final SocketFactory socketFactory;
/*      */   private final SSLSocketFactory sslSocketFactoryOrNull;
/*      */   @Nullable
/*      */   private final X509TrustManager x509TrustManager;
/*      */   @NotNull
/*      */   private final List<ConnectionSpec> connectionSpecs;
/*      */   @NotNull
/*      */   private final List<Protocol> protocols;
/*      */   @NotNull
/*      */   private final HostnameVerifier hostnameVerifier;
/*      */   @NotNull
/*      */   private final CertificatePinner certificatePinner;
/*      */   @Nullable
/*      */   private final CertificateChainCleaner certificateChainCleaner;
/*      */   private final int callTimeoutMillis;
/*      */   private final int connectTimeoutMillis;
/*      */   private final int readTimeoutMillis;
/*      */   private final int writeTimeoutMillis;
/*      */   private final int pingIntervalMillis;
/*      */   private final long minWebSocketMessageToCompress;
/*      */   @NotNull
/*      */   private final RouteDatabase routeDatabase;
/*      */   @NotNull
/*      */   private static final List<Protocol> DEFAULT_PROTOCOLS;
/*      */   @NotNull
/*      */   private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS;
/*      */   
/*      */   public OkHttpClient(@NotNull Builder builder) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: ldc_w 'builder'
/*      */     //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*      */     //   7: aload_0
/*      */     //   8: invokespecial <init> : ()V
/*      */     //   11: aload_0
/*      */     //   12: aload_1
/*      */     //   13: invokevirtual getDispatcher$okhttp : ()Lokhttp3/Dispatcher;
/*      */     //   16: putfield dispatcher : Lokhttp3/Dispatcher;
/*      */     //   19: aload_0
/*      */     //   20: aload_1
/*      */     //   21: invokevirtual getConnectionPool$okhttp : ()Lokhttp3/ConnectionPool;
/*      */     //   24: putfield connectionPool : Lokhttp3/ConnectionPool;
/*      */     //   27: aload_0
/*      */     //   28: aload_1
/*      */     //   29: invokevirtual getInterceptors$okhttp : ()Ljava/util/List;
/*      */     //   32: invokestatic toImmutableList : (Ljava/util/List;)Ljava/util/List;
/*      */     //   35: putfield interceptors : Ljava/util/List;
/*      */     //   38: aload_0
/*      */     //   39: aload_1
/*      */     //   40: invokevirtual getNetworkInterceptors$okhttp : ()Ljava/util/List;
/*      */     //   43: invokestatic toImmutableList : (Ljava/util/List;)Ljava/util/List;
/*      */     //   46: putfield networkInterceptors : Ljava/util/List;
/*      */     //   49: aload_0
/*      */     //   50: aload_1
/*      */     //   51: invokevirtual getEventListenerFactory$okhttp : ()Lokhttp3/EventListener$Factory;
/*      */     //   54: putfield eventListenerFactory : Lokhttp3/EventListener$Factory;
/*      */     //   57: aload_0
/*      */     //   58: aload_1
/*      */     //   59: invokevirtual getRetryOnConnectionFailure$okhttp : ()Z
/*      */     //   62: putfield retryOnConnectionFailure : Z
/*      */     //   65: aload_0
/*      */     //   66: aload_1
/*      */     //   67: invokevirtual getAuthenticator$okhttp : ()Lokhttp3/Authenticator;
/*      */     //   70: putfield authenticator : Lokhttp3/Authenticator;
/*      */     //   73: aload_0
/*      */     //   74: aload_1
/*      */     //   75: invokevirtual getFollowRedirects$okhttp : ()Z
/*      */     //   78: putfield followRedirects : Z
/*      */     //   81: aload_0
/*      */     //   82: aload_1
/*      */     //   83: invokevirtual getFollowSslRedirects$okhttp : ()Z
/*      */     //   86: putfield followSslRedirects : Z
/*      */     //   89: aload_0
/*      */     //   90: aload_1
/*      */     //   91: invokevirtual getCookieJar$okhttp : ()Lokhttp3/CookieJar;
/*      */     //   94: putfield cookieJar : Lokhttp3/CookieJar;
/*      */     //   97: aload_0
/*      */     //   98: aload_1
/*      */     //   99: invokevirtual getCache$okhttp : ()Lokhttp3/Cache;
/*      */     //   102: putfield cache : Lokhttp3/Cache;
/*      */     //   105: aload_0
/*      */     //   106: aload_1
/*      */     //   107: invokevirtual getDns$okhttp : ()Lokhttp3/Dns;
/*      */     //   110: putfield dns : Lokhttp3/Dns;
/*      */     //   113: aload_0
/*      */     //   114: aload_1
/*      */     //   115: invokevirtual getProxy$okhttp : ()Ljava/net/Proxy;
/*      */     //   118: putfield proxy : Ljava/net/Proxy;
/*      */     //   121: aload_0
/*      */     //   122: aload_1
/*      */     //   123: invokevirtual getProxy$okhttp : ()Ljava/net/Proxy;
/*      */     //   126: ifnull -> 138
/*      */     //   129: getstatic okhttp3/internal/proxy/NullProxySelector.INSTANCE : Lokhttp3/internal/proxy/NullProxySelector;
/*      */     //   132: checkcast java/net/ProxySelector
/*      */     //   135: goto -> 167
/*      */     //   138: aload_1
/*      */     //   139: invokevirtual getProxySelector$okhttp : ()Ljava/net/ProxySelector;
/*      */     //   142: dup
/*      */     //   143: ifnull -> 149
/*      */     //   146: goto -> 153
/*      */     //   149: pop
/*      */     //   150: invokestatic getDefault : ()Ljava/net/ProxySelector;
/*      */     //   153: dup
/*      */     //   154: ifnull -> 160
/*      */     //   157: goto -> 167
/*      */     //   160: pop
/*      */     //   161: getstatic okhttp3/internal/proxy/NullProxySelector.INSTANCE : Lokhttp3/internal/proxy/NullProxySelector;
/*      */     //   164: checkcast java/net/ProxySelector
/*      */     //   167: putfield proxySelector : Ljava/net/ProxySelector;
/*      */     //   170: aload_0
/*      */     //   171: aload_1
/*      */     //   172: invokevirtual getProxyAuthenticator$okhttp : ()Lokhttp3/Authenticator;
/*      */     //   175: putfield proxyAuthenticator : Lokhttp3/Authenticator;
/*      */     //   178: aload_0
/*      */     //   179: aload_1
/*      */     //   180: invokevirtual getSocketFactory$okhttp : ()Ljavax/net/SocketFactory;
/*      */     //   183: putfield socketFactory : Ljavax/net/SocketFactory;
/*      */     //   186: aload_0
/*      */     //   187: aload_1
/*      */     //   188: invokevirtual getConnectionSpecs$okhttp : ()Ljava/util/List;
/*      */     //   191: putfield connectionSpecs : Ljava/util/List;
/*      */     //   194: aload_0
/*      */     //   195: aload_1
/*      */     //   196: invokevirtual getProtocols$okhttp : ()Ljava/util/List;
/*      */     //   199: putfield protocols : Ljava/util/List;
/*      */     //   202: aload_0
/*      */     //   203: aload_1
/*      */     //   204: invokevirtual getHostnameVerifier$okhttp : ()Ljavax/net/ssl/HostnameVerifier;
/*      */     //   207: putfield hostnameVerifier : Ljavax/net/ssl/HostnameVerifier;
/*      */     //   210: aload_0
/*      */     //   211: aload_1
/*      */     //   212: invokevirtual getCallTimeout$okhttp : ()I
/*      */     //   215: putfield callTimeoutMillis : I
/*      */     //   218: aload_0
/*      */     //   219: aload_1
/*      */     //   220: invokevirtual getConnectTimeout$okhttp : ()I
/*      */     //   223: putfield connectTimeoutMillis : I
/*      */     //   226: aload_0
/*      */     //   227: aload_1
/*      */     //   228: invokevirtual getReadTimeout$okhttp : ()I
/*      */     //   231: putfield readTimeoutMillis : I
/*      */     //   234: aload_0
/*      */     //   235: aload_1
/*      */     //   236: invokevirtual getWriteTimeout$okhttp : ()I
/*      */     //   239: putfield writeTimeoutMillis : I
/*      */     //   242: aload_0
/*      */     //   243: aload_1
/*      */     //   244: invokevirtual getPingInterval$okhttp : ()I
/*      */     //   247: putfield pingIntervalMillis : I
/*      */     //   250: aload_0
/*      */     //   251: aload_1
/*      */     //   252: invokevirtual getMinWebSocketMessageToCompress$okhttp : ()J
/*      */     //   255: putfield minWebSocketMessageToCompress : J
/*      */     //   258: aload_0
/*      */     //   259: aload_1
/*      */     //   260: invokevirtual getRouteDatabase$okhttp : ()Lokhttp3/internal/connection/RouteDatabase;
/*      */     //   263: dup
/*      */     //   264: ifnull -> 270
/*      */     //   267: goto -> 278
/*      */     //   270: pop
/*      */     //   271: new okhttp3/internal/connection/RouteDatabase
/*      */     //   274: dup
/*      */     //   275: invokespecial <init> : ()V
/*      */     //   278: putfield routeDatabase : Lokhttp3/internal/connection/RouteDatabase;
/*      */     //   281: nop
/*      */     //   282: aload_0
/*      */     //   283: getfield connectionSpecs : Ljava/util/List;
/*      */     //   286: checkcast java/lang/Iterable
/*      */     //   289: astore_2
/*      */     //   290: iconst_0
/*      */     //   291: istore_3
/*      */     //   292: aload_2
/*      */     //   293: instanceof java/util/Collection
/*      */     //   296: ifeq -> 315
/*      */     //   299: aload_2
/*      */     //   300: checkcast java/util/Collection
/*      */     //   303: invokeinterface isEmpty : ()Z
/*      */     //   308: ifeq -> 315
/*      */     //   311: iconst_1
/*      */     //   312: goto -> 365
/*      */     //   315: aload_2
/*      */     //   316: invokeinterface iterator : ()Ljava/util/Iterator;
/*      */     //   321: astore #4
/*      */     //   323: aload #4
/*      */     //   325: invokeinterface hasNext : ()Z
/*      */     //   330: ifeq -> 364
/*      */     //   333: aload #4
/*      */     //   335: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   340: astore #5
/*      */     //   342: aload #5
/*      */     //   344: checkcast okhttp3/ConnectionSpec
/*      */     //   347: astore #6
/*      */     //   349: iconst_0
/*      */     //   350: istore #7
/*      */     //   352: aload #6
/*      */     //   354: invokevirtual isTls : ()Z
/*      */     //   357: ifeq -> 323
/*      */     //   360: iconst_0
/*      */     //   361: goto -> 365
/*      */     //   364: iconst_1
/*      */     //   365: ifeq -> 402
/*      */     //   368: aload_0
/*      */     //   369: aconst_null
/*      */     //   370: checkcast javax/net/ssl/SSLSocketFactory
/*      */     //   373: putfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
/*      */     //   376: aload_0
/*      */     //   377: aconst_null
/*      */     //   378: checkcast okhttp3/internal/tls/CertificateChainCleaner
/*      */     //   381: putfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   384: aload_0
/*      */     //   385: aconst_null
/*      */     //   386: checkcast javax/net/ssl/X509TrustManager
/*      */     //   389: putfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
/*      */     //   392: aload_0
/*      */     //   393: getstatic okhttp3/CertificatePinner.DEFAULT : Lokhttp3/CertificatePinner;
/*      */     //   396: putfield certificatePinner : Lokhttp3/CertificatePinner;
/*      */     //   399: goto -> 534
/*      */     //   402: aload_1
/*      */     //   403: invokevirtual getSslSocketFactoryOrNull$okhttp : ()Ljavax/net/ssl/SSLSocketFactory;
/*      */     //   406: ifnull -> 463
/*      */     //   409: aload_0
/*      */     //   410: aload_1
/*      */     //   411: invokevirtual getSslSocketFactoryOrNull$okhttp : ()Ljavax/net/ssl/SSLSocketFactory;
/*      */     //   414: putfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
/*      */     //   417: aload_0
/*      */     //   418: aload_1
/*      */     //   419: invokevirtual getCertificateChainCleaner$okhttp : ()Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   422: dup
/*      */     //   423: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */     //   426: putfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   429: aload_0
/*      */     //   430: aload_1
/*      */     //   431: invokevirtual getX509TrustManagerOrNull$okhttp : ()Ljavax/net/ssl/X509TrustManager;
/*      */     //   434: dup
/*      */     //   435: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */     //   438: putfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
/*      */     //   441: aload_0
/*      */     //   442: aload_1
/*      */     //   443: invokevirtual getCertificatePinner$okhttp : ()Lokhttp3/CertificatePinner;
/*      */     //   446: aload_0
/*      */     //   447: getfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   450: dup
/*      */     //   451: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */     //   454: invokevirtual withCertificateChainCleaner$okhttp : (Lokhttp3/internal/tls/CertificateChainCleaner;)Lokhttp3/CertificatePinner;
/*      */     //   457: putfield certificatePinner : Lokhttp3/CertificatePinner;
/*      */     //   460: goto -> 534
/*      */     //   463: aload_0
/*      */     //   464: getstatic okhttp3/internal/platform/Platform.Companion : Lokhttp3/internal/platform/Platform$Companion;
/*      */     //   467: invokevirtual get : ()Lokhttp3/internal/platform/Platform;
/*      */     //   470: invokevirtual platformTrustManager : ()Ljavax/net/ssl/X509TrustManager;
/*      */     //   473: putfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
/*      */     //   476: aload_0
/*      */     //   477: getstatic okhttp3/internal/platform/Platform.Companion : Lokhttp3/internal/platform/Platform$Companion;
/*      */     //   480: invokevirtual get : ()Lokhttp3/internal/platform/Platform;
/*      */     //   483: aload_0
/*      */     //   484: getfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
/*      */     //   487: dup
/*      */     //   488: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */     //   491: invokevirtual newSslSocketFactory : (Ljavax/net/ssl/X509TrustManager;)Ljavax/net/ssl/SSLSocketFactory;
/*      */     //   494: putfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
/*      */     //   497: aload_0
/*      */     //   498: getstatic okhttp3/internal/tls/CertificateChainCleaner.Companion : Lokhttp3/internal/tls/CertificateChainCleaner$Companion;
/*      */     //   501: aload_0
/*      */     //   502: getfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
/*      */     //   505: dup
/*      */     //   506: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */     //   509: invokevirtual get : (Ljavax/net/ssl/X509TrustManager;)Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   512: putfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   515: aload_0
/*      */     //   516: aload_1
/*      */     //   517: invokevirtual getCertificatePinner$okhttp : ()Lokhttp3/CertificatePinner;
/*      */     //   520: aload_0
/*      */     //   521: getfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   524: dup
/*      */     //   525: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */     //   528: invokevirtual withCertificateChainCleaner$okhttp : (Lokhttp3/internal/tls/CertificateChainCleaner;)Lokhttp3/CertificatePinner;
/*      */     //   531: putfield certificatePinner : Lokhttp3/CertificatePinner;
/*      */     //   534: aload_0
/*      */     //   535: invokespecial verifyClientState : ()V
/*      */     //   538: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #121	-> 7
/*      */     //   #125	-> 11
/*      */     //   #127	-> 19
/*      */     //   #135	-> 27
/*      */     //   #143	-> 38
/*      */     //   #146	-> 49
/*      */     //   #149	-> 57
/*      */     //   #151	-> 65
/*      */     //   #153	-> 73
/*      */     //   #155	-> 81
/*      */     //   #157	-> 89
/*      */     //   #159	-> 97
/*      */     //   #161	-> 105
/*      */     //   #163	-> 113
/*      */     //   #166	-> 121
/*      */     //   #168	-> 122
/*      */     //   #169	-> 138
/*      */     //   #169	-> 149
/*      */     //   #169	-> 160
/*      */     //   #166	-> 167
/*      */     //   #173	-> 170
/*      */     //   #175	-> 178
/*      */     //   #185	-> 186
/*      */     //   #187	-> 194
/*      */     //   #189	-> 202
/*      */     //   #199	-> 210
/*      */     //   #202	-> 218
/*      */     //   #205	-> 226
/*      */     //   #208	-> 234
/*      */     //   #211	-> 242
/*      */     //   #218	-> 250
/*      */     //   #220	-> 258
/*      */     //   #220	-> 270
/*      */     //   #224	-> 281
/*      */     //   #225	-> 282
/*      */     //   #1084	-> 292
/*      */     //   #1085	-> 315
/*      */     //   #1085	-> 323
/*      */     //   #225	-> 352
/*      */     //   #1086	-> 364
/*      */     //   #225	-> 365
/*      */     //   #226	-> 368
/*      */     //   #227	-> 376
/*      */     //   #228	-> 384
/*      */     //   #229	-> 392
/*      */     //   #230	-> 402
/*      */     //   #231	-> 409
/*      */     //   #232	-> 417
/*      */     //   #233	-> 429
/*      */     //   #234	-> 441
/*      */     //   #235	-> 441
/*      */     //   #234	-> 442
/*      */     //   #235	-> 446
/*      */     //   #237	-> 463
/*      */     //   #238	-> 476
/*      */     //   #239	-> 497
/*      */     //   #240	-> 515
/*      */     //   #241	-> 515
/*      */     //   #240	-> 516
/*      */     //   #241	-> 520
/*      */     //   #242	-> 534
/*      */     //   #244	-> 534
/*      */     //   #245	-> 538
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   349	8	6	it	Lokhttp3/ConnectionSpec;
/*      */     //   352	5	7	$i$a$-none-OkHttpClient$1	I
/*      */     //   342	22	5	element$iv	Ljava/lang/Object;
/*      */     //   290	75	2	$this$none$iv	Ljava/lang/Iterable;
/*      */     //   292	73	3	$i$f$none	I
/*      */     //   0	539	0	this	Lokhttp3/OkHttpClient;
/*      */     //   0	539	1	builder	Lokhttp3/OkHttpClient$Builder;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public Object clone() {
/*  121 */     return super.clone();
/*      */   }
/*      */   @JvmName(name = "dispatcher")
/*      */   @NotNull
/*  125 */   public final Dispatcher dispatcher() { return this.dispatcher; } @JvmName(name = "connectionPool")
/*      */   @NotNull
/*  127 */   public final ConnectionPool connectionPool() { return this.connectionPool; }
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "interceptors")
/*      */   @NotNull
/*      */   public final List<Interceptor> interceptors() {
/*  134 */     return this.interceptors;
/*      */   }
/*      */ 
/*      */   
/*      */   @JvmName(name = "networkInterceptors")
/*      */   @NotNull
/*      */   public final List<Interceptor> networkInterceptors()
/*      */   {
/*  142 */     return this.networkInterceptors; } @JvmName(name = "eventListenerFactory")
/*      */   @NotNull
/*      */   public final EventListener.Factory eventListenerFactory() {
/*  145 */     return this.eventListenerFactory;
/*      */   }
/*      */   @JvmName(name = "retryOnConnectionFailure")
/*  148 */   public final boolean retryOnConnectionFailure() { return this.retryOnConnectionFailure; }
/*      */   @JvmName(name = "authenticator")
/*      */   @NotNull
/*  151 */   public final Authenticator authenticator() { return this.authenticator; }
/*      */   @JvmName(name = "followRedirects")
/*  153 */   public final boolean followRedirects() { return this.followRedirects; }
/*      */   @JvmName(name = "followSslRedirects")
/*  155 */   public final boolean followSslRedirects() { return this.followSslRedirects; } @JvmName(name = "cookieJar")
/*      */   @NotNull
/*  157 */   public final CookieJar cookieJar() { return this.cookieJar; } @JvmName(name = "cache")
/*      */   @Nullable
/*  159 */   public final Cache cache() { return this.cache; } @JvmName(name = "dns")
/*      */   @NotNull
/*  161 */   public final Dns dns() { return this.dns; } @JvmName(name = "proxy")
/*      */   @Nullable
/*  163 */   public final Proxy proxy() { return this.proxy; } @JvmName(name = "proxySelector")
/*      */   @NotNull
/*  165 */   public final ProxySelector proxySelector() { return this.proxySelector; }
/*      */ 
/*      */   
/*      */   @JvmName(name = "proxyAuthenticator")
/*      */   @NotNull
/*      */   public final Authenticator proxyAuthenticator()
/*      */   {
/*  172 */     return this.proxyAuthenticator; } @JvmName(name = "socketFactory")
/*      */   @NotNull
/*      */   public final SocketFactory socketFactory() {
/*  175 */     return this.socketFactory;
/*      */   }
/*      */   
/*      */   @JvmName(name = "sslSocketFactory")
/*      */   @NotNull
/*  180 */   public final SSLSocketFactory sslSocketFactory() { if (this.sslSocketFactoryOrNull != null) return this.sslSocketFactoryOrNull;  throw (Throwable)new IllegalStateException("CLEARTEXT-only client"); } @JvmName(name = "x509TrustManager")
/*      */   @Nullable
/*  182 */   public final X509TrustManager x509TrustManager() { return this.x509TrustManager; } @JvmName(name = "connectionSpecs")
/*      */   @NotNull
/*  184 */   public final List<ConnectionSpec> connectionSpecs() { return this.connectionSpecs; }
/*      */   @JvmName(name = "protocols")
/*      */   @NotNull
/*  187 */   public final List<Protocol> protocols() { return this.protocols; } @JvmName(name = "hostnameVerifier")
/*      */   @NotNull
/*  189 */   public final HostnameVerifier hostnameVerifier() { return this.hostnameVerifier; } @JvmName(name = "certificatePinner")
/*      */   @NotNull
/*  191 */   public final CertificatePinner certificatePinner() { return this.certificatePinner; } @JvmName(name = "certificateChainCleaner")
/*      */   @Nullable
/*  193 */   public final CertificateChainCleaner certificateChainCleaner() { return this.certificateChainCleaner; }
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "callTimeoutMillis")
/*      */   public final int callTimeoutMillis() {
/*  199 */     return this.callTimeoutMillis;
/*      */   } @JvmName(name = "connectTimeoutMillis")
/*      */   public final int connectTimeoutMillis() {
/*  202 */     return this.connectTimeoutMillis;
/*      */   } @JvmName(name = "readTimeoutMillis")
/*      */   public final int readTimeoutMillis() {
/*  205 */     return this.readTimeoutMillis;
/*      */   } @JvmName(name = "writeTimeoutMillis")
/*      */   public final int writeTimeoutMillis() {
/*  208 */     return this.writeTimeoutMillis;
/*      */   } @JvmName(name = "pingIntervalMillis")
/*      */   public final int pingIntervalMillis() {
/*  211 */     return this.pingIntervalMillis;
/*      */   }
/*      */ 
/*      */   
/*      */   @JvmName(name = "minWebSocketMessageToCompress")
/*      */   public final long minWebSocketMessageToCompress()
/*      */   {
/*  218 */     return this.minWebSocketMessageToCompress; } @NotNull
/*      */   public final RouteDatabase getRouteDatabase() {
/*  220 */     return this.routeDatabase;
/*      */   } public OkHttpClient() {
/*  222 */     this(new Builder());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void verifyClientState() {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield interceptors : Ljava/util/List;
/*      */     //   4: dup
/*      */     //   5: ifnonnull -> 18
/*      */     //   8: new java/lang/NullPointerException
/*      */     //   11: dup
/*      */     //   12: ldc 'null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>'
/*      */     //   14: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   17: athrow
/*      */     //   18: aconst_null
/*      */     //   19: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   24: ifne -> 31
/*      */     //   27: iconst_1
/*      */     //   28: goto -> 32
/*      */     //   31: iconst_0
/*      */     //   32: istore_1
/*      */     //   33: iconst_0
/*      */     //   34: istore_2
/*      */     //   35: iconst_0
/*      */     //   36: istore_3
/*      */     //   37: iload_1
/*      */     //   38: ifne -> 82
/*      */     //   41: iconst_0
/*      */     //   42: istore #4
/*      */     //   44: new java/lang/StringBuilder
/*      */     //   47: dup
/*      */     //   48: invokespecial <init> : ()V
/*      */     //   51: ldc 'Null interceptor: '
/*      */     //   53: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   56: aload_0
/*      */     //   57: getfield interceptors : Ljava/util/List;
/*      */     //   60: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*      */     //   63: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   66: astore_3
/*      */     //   67: new java/lang/IllegalStateException
/*      */     //   70: dup
/*      */     //   71: aload_3
/*      */     //   72: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   75: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   78: checkcast java/lang/Throwable
/*      */     //   81: athrow
/*      */     //   82: aload_0
/*      */     //   83: getfield networkInterceptors : Ljava/util/List;
/*      */     //   86: dup
/*      */     //   87: ifnonnull -> 100
/*      */     //   90: new java/lang/NullPointerException
/*      */     //   93: dup
/*      */     //   94: ldc 'null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>'
/*      */     //   96: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   99: athrow
/*      */     //   100: aconst_null
/*      */     //   101: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   106: ifne -> 113
/*      */     //   109: iconst_1
/*      */     //   110: goto -> 114
/*      */     //   113: iconst_0
/*      */     //   114: istore_1
/*      */     //   115: iconst_0
/*      */     //   116: istore_2
/*      */     //   117: iconst_0
/*      */     //   118: istore_3
/*      */     //   119: iload_1
/*      */     //   120: ifne -> 164
/*      */     //   123: iconst_0
/*      */     //   124: istore #4
/*      */     //   126: new java/lang/StringBuilder
/*      */     //   129: dup
/*      */     //   130: invokespecial <init> : ()V
/*      */     //   133: ldc 'Null network interceptor: '
/*      */     //   135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   138: aload_0
/*      */     //   139: getfield networkInterceptors : Ljava/util/List;
/*      */     //   142: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*      */     //   145: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   148: astore_3
/*      */     //   149: new java/lang/IllegalStateException
/*      */     //   152: dup
/*      */     //   153: aload_3
/*      */     //   154: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   157: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   160: checkcast java/lang/Throwable
/*      */     //   163: athrow
/*      */     //   164: aload_0
/*      */     //   165: getfield connectionSpecs : Ljava/util/List;
/*      */     //   168: checkcast java/lang/Iterable
/*      */     //   171: astore_1
/*      */     //   172: iconst_0
/*      */     //   173: istore_2
/*      */     //   174: aload_1
/*      */     //   175: instanceof java/util/Collection
/*      */     //   178: ifeq -> 197
/*      */     //   181: aload_1
/*      */     //   182: checkcast java/util/Collection
/*      */     //   185: invokeinterface isEmpty : ()Z
/*      */     //   190: ifeq -> 197
/*      */     //   193: iconst_1
/*      */     //   194: goto -> 244
/*      */     //   197: aload_1
/*      */     //   198: invokeinterface iterator : ()Ljava/util/Iterator;
/*      */     //   203: astore_3
/*      */     //   204: aload_3
/*      */     //   205: invokeinterface hasNext : ()Z
/*      */     //   210: ifeq -> 243
/*      */     //   213: aload_3
/*      */     //   214: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   219: astore #4
/*      */     //   221: aload #4
/*      */     //   223: checkcast okhttp3/ConnectionSpec
/*      */     //   226: astore #5
/*      */     //   228: iconst_0
/*      */     //   229: istore #6
/*      */     //   231: aload #5
/*      */     //   233: invokevirtual isTls : ()Z
/*      */     //   236: ifeq -> 204
/*      */     //   239: iconst_0
/*      */     //   240: goto -> 244
/*      */     //   243: iconst_1
/*      */     //   244: ifeq -> 441
/*      */     //   247: aload_0
/*      */     //   248: getfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
/*      */     //   251: ifnonnull -> 258
/*      */     //   254: iconst_1
/*      */     //   255: goto -> 259
/*      */     //   258: iconst_0
/*      */     //   259: istore_1
/*      */     //   260: iconst_0
/*      */     //   261: istore_2
/*      */     //   262: iconst_0
/*      */     //   263: istore_3
/*      */     //   264: iconst_0
/*      */     //   265: istore_3
/*      */     //   266: iconst_0
/*      */     //   267: istore #4
/*      */     //   269: iload_1
/*      */     //   270: ifne -> 296
/*      */     //   273: iconst_0
/*      */     //   274: istore #5
/*      */     //   276: ldc 'Check failed.'
/*      */     //   278: astore #4
/*      */     //   280: new java/lang/IllegalStateException
/*      */     //   283: dup
/*      */     //   284: aload #4
/*      */     //   286: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   289: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   292: checkcast java/lang/Throwable
/*      */     //   295: athrow
/*      */     //   296: aload_0
/*      */     //   297: getfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   300: ifnonnull -> 307
/*      */     //   303: iconst_1
/*      */     //   304: goto -> 308
/*      */     //   307: iconst_0
/*      */     //   308: istore_1
/*      */     //   309: iconst_0
/*      */     //   310: istore_2
/*      */     //   311: iconst_0
/*      */     //   312: istore_3
/*      */     //   313: iconst_0
/*      */     //   314: istore_3
/*      */     //   315: iconst_0
/*      */     //   316: istore #4
/*      */     //   318: iload_1
/*      */     //   319: ifne -> 345
/*      */     //   322: iconst_0
/*      */     //   323: istore #5
/*      */     //   325: ldc 'Check failed.'
/*      */     //   327: astore #4
/*      */     //   329: new java/lang/IllegalStateException
/*      */     //   332: dup
/*      */     //   333: aload #4
/*      */     //   335: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   338: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   341: checkcast java/lang/Throwable
/*      */     //   344: athrow
/*      */     //   345: aload_0
/*      */     //   346: getfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
/*      */     //   349: ifnonnull -> 356
/*      */     //   352: iconst_1
/*      */     //   353: goto -> 357
/*      */     //   356: iconst_0
/*      */     //   357: istore_1
/*      */     //   358: iconst_0
/*      */     //   359: istore_2
/*      */     //   360: iconst_0
/*      */     //   361: istore_3
/*      */     //   362: iconst_0
/*      */     //   363: istore_3
/*      */     //   364: iconst_0
/*      */     //   365: istore #4
/*      */     //   367: iload_1
/*      */     //   368: ifne -> 394
/*      */     //   371: iconst_0
/*      */     //   372: istore #5
/*      */     //   374: ldc 'Check failed.'
/*      */     //   376: astore #4
/*      */     //   378: new java/lang/IllegalStateException
/*      */     //   381: dup
/*      */     //   382: aload #4
/*      */     //   384: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   387: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   390: checkcast java/lang/Throwable
/*      */     //   393: athrow
/*      */     //   394: aload_0
/*      */     //   395: getfield certificatePinner : Lokhttp3/CertificatePinner;
/*      */     //   398: getstatic okhttp3/CertificatePinner.DEFAULT : Lokhttp3/CertificatePinner;
/*      */     //   401: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*      */     //   404: istore_1
/*      */     //   405: iconst_0
/*      */     //   406: istore_2
/*      */     //   407: iconst_0
/*      */     //   408: istore_3
/*      */     //   409: iconst_0
/*      */     //   410: istore_3
/*      */     //   411: iconst_0
/*      */     //   412: istore #4
/*      */     //   414: iload_1
/*      */     //   415: ifne -> 543
/*      */     //   418: iconst_0
/*      */     //   419: istore #5
/*      */     //   421: ldc 'Check failed.'
/*      */     //   423: astore #4
/*      */     //   425: new java/lang/IllegalStateException
/*      */     //   428: dup
/*      */     //   429: aload #4
/*      */     //   431: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   434: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   437: checkcast java/lang/Throwable
/*      */     //   440: athrow
/*      */     //   441: aload_0
/*      */     //   442: getfield sslSocketFactoryOrNull : Ljavax/net/ssl/SSLSocketFactory;
/*      */     //   445: astore_1
/*      */     //   446: iconst_0
/*      */     //   447: istore_2
/*      */     //   448: iconst_0
/*      */     //   449: istore_3
/*      */     //   450: aload_1
/*      */     //   451: ifnonnull -> 475
/*      */     //   454: iconst_0
/*      */     //   455: istore #4
/*      */     //   457: ldc 'sslSocketFactory == null'
/*      */     //   459: astore_3
/*      */     //   460: new java/lang/IllegalStateException
/*      */     //   463: dup
/*      */     //   464: aload_3
/*      */     //   465: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   468: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   471: checkcast java/lang/Throwable
/*      */     //   474: athrow
/*      */     //   475: aload_0
/*      */     //   476: getfield certificateChainCleaner : Lokhttp3/internal/tls/CertificateChainCleaner;
/*      */     //   479: astore_1
/*      */     //   480: iconst_0
/*      */     //   481: istore_2
/*      */     //   482: iconst_0
/*      */     //   483: istore_3
/*      */     //   484: aload_1
/*      */     //   485: ifnonnull -> 509
/*      */     //   488: iconst_0
/*      */     //   489: istore #4
/*      */     //   491: ldc 'certificateChainCleaner == null'
/*      */     //   493: astore_3
/*      */     //   494: new java/lang/IllegalStateException
/*      */     //   497: dup
/*      */     //   498: aload_3
/*      */     //   499: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   502: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   505: checkcast java/lang/Throwable
/*      */     //   508: athrow
/*      */     //   509: aload_0
/*      */     //   510: getfield x509TrustManager : Ljavax/net/ssl/X509TrustManager;
/*      */     //   513: astore_1
/*      */     //   514: iconst_0
/*      */     //   515: istore_2
/*      */     //   516: iconst_0
/*      */     //   517: istore_3
/*      */     //   518: aload_1
/*      */     //   519: ifnonnull -> 543
/*      */     //   522: iconst_0
/*      */     //   523: istore #4
/*      */     //   525: ldc 'x509TrustManager == null'
/*      */     //   527: astore_3
/*      */     //   528: new java/lang/IllegalStateException
/*      */     //   531: dup
/*      */     //   532: aload_3
/*      */     //   533: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   536: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   539: checkcast java/lang/Throwable
/*      */     //   542: athrow
/*      */     //   543: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #248	-> 0
/*      */     //   #249	-> 44
/*      */     //   #248	-> 66
/*      */     //   #251	-> 82
/*      */     //   #252	-> 126
/*      */     //   #251	-> 148
/*      */     //   #255	-> 164
/*      */     //   #1080	-> 174
/*      */     //   #1081	-> 197
/*      */     //   #1081	-> 204
/*      */     //   #255	-> 231
/*      */     //   #1082	-> 243
/*      */     //   #255	-> 244
/*      */     //   #256	-> 247
/*      */     //   #257	-> 296
/*      */     //   #258	-> 345
/*      */     //   #259	-> 394
/*      */     //   #261	-> 441
/*      */     //   #1083	-> 454
/*      */     //   #261	-> 457
/*      */     //   #261	-> 459
/*      */     //   #262	-> 475
/*      */     //   #1083	-> 488
/*      */     //   #262	-> 491
/*      */     //   #262	-> 493
/*      */     //   #263	-> 509
/*      */     //   #1083	-> 522
/*      */     //   #263	-> 525
/*      */     //   #263	-> 527
/*      */     //   #264	-> 543
/*      */     //   #265	-> 543
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   44	22	4	$i$a$-check-OkHttpClient$verifyClientState$1	I
/*      */     //   126	22	4	$i$a$-check-OkHttpClient$verifyClientState$2	I
/*      */     //   228	8	5	it	Lokhttp3/ConnectionSpec;
/*      */     //   231	5	6	$i$a$-none-OkHttpClient$verifyClientState$3	I
/*      */     //   221	22	4	element$iv	Ljava/lang/Object;
/*      */     //   172	72	1	$this$none$iv	Ljava/lang/Iterable;
/*      */     //   174	70	2	$i$f$none	I
/*      */     //   457	2	4	$i$a$-checkNotNull-OkHttpClient$verifyClientState$4	I
/*      */     //   491	2	4	$i$a$-checkNotNull-OkHttpClient$verifyClientState$5	I
/*      */     //   525	2	4	$i$a$-checkNotNull-OkHttpClient$verifyClientState$6	I
/*      */     //   0	544	0	this	Lokhttp3/OkHttpClient;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Call newCall(@NotNull Request request) {
/*  268 */     Intrinsics.checkNotNullParameter(request, "request"); return (Call)new RealCall(this, request, false);
/*      */   }
/*      */   @NotNull
/*      */   public WebSocket newWebSocket(@NotNull Request request, @NotNull WebSocketListener listener) {
/*  272 */     Intrinsics.checkNotNullParameter(request, "request"); Intrinsics.checkNotNullParameter(listener, "listener"); RealWebSocket webSocket = new RealWebSocket(
/*  273 */         TaskRunner.INSTANCE, 
/*  274 */         request, 
/*  275 */         listener, 
/*  276 */         new Random(), 
/*  277 */         this.pingIntervalMillis, 
/*  278 */         null, 
/*  279 */         this.minWebSocketMessageToCompress);
/*      */     
/*  281 */     webSocket.connect(this);
/*  282 */     return (WebSocket)webSocket;
/*      */   } @NotNull
/*      */   public Builder newBuilder() {
/*  285 */     return new Builder(this);
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "dispatcher"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_dispatcher")
/*      */   @NotNull
/*      */   public final Dispatcher -deprecated_dispatcher() {
/*  292 */     return this.dispatcher;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "connectionPool"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_connectionPool")
/*      */   @NotNull
/*      */   public final ConnectionPool -deprecated_connectionPool() {
/*  299 */     return this.connectionPool;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "interceptors"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_interceptors")
/*      */   @NotNull
/*      */   public final List<Interceptor> -deprecated_interceptors() {
/*  306 */     return this.interceptors;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "networkInterceptors"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_networkInterceptors")
/*      */   @NotNull
/*      */   public final List<Interceptor> -deprecated_networkInterceptors() {
/*  313 */     return this.networkInterceptors;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "eventListenerFactory"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_eventListenerFactory")
/*      */   @NotNull
/*      */   public final EventListener.Factory -deprecated_eventListenerFactory() {
/*  320 */     return this.eventListenerFactory;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "retryOnConnectionFailure"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_retryOnConnectionFailure")
/*      */   public final boolean -deprecated_retryOnConnectionFailure() {
/*  327 */     return this.retryOnConnectionFailure;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "authenticator"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_authenticator")
/*      */   @NotNull
/*      */   public final Authenticator -deprecated_authenticator() {
/*  334 */     return this.authenticator;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "followRedirects"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_followRedirects")
/*      */   public final boolean -deprecated_followRedirects() {
/*  341 */     return this.followRedirects;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "followSslRedirects"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_followSslRedirects")
/*      */   public final boolean -deprecated_followSslRedirects() {
/*  348 */     return this.followSslRedirects;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cookieJar"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_cookieJar")
/*      */   @NotNull
/*      */   public final CookieJar -deprecated_cookieJar() {
/*  355 */     return this.cookieJar;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cache"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_cache")
/*      */   @Nullable
/*      */   public final Cache -deprecated_cache() {
/*  362 */     return this.cache;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "dns"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_dns")
/*      */   @NotNull
/*      */   public final Dns -deprecated_dns() {
/*  369 */     return this.dns;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "proxy"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_proxy")
/*      */   @Nullable
/*      */   public final Proxy -deprecated_proxy() {
/*  376 */     return this.proxy;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "proxySelector"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_proxySelector")
/*      */   @NotNull
/*      */   public final ProxySelector -deprecated_proxySelector() {
/*  383 */     return this.proxySelector;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "proxyAuthenticator"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_proxyAuthenticator")
/*      */   @NotNull
/*      */   public final Authenticator -deprecated_proxyAuthenticator() {
/*  390 */     return this.proxyAuthenticator;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "socketFactory"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_socketFactory")
/*      */   @NotNull
/*      */   public final SocketFactory -deprecated_socketFactory() {
/*  397 */     return this.socketFactory;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "sslSocketFactory"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_sslSocketFactory")
/*      */   @NotNull
/*      */   public final SSLSocketFactory -deprecated_sslSocketFactory() {
/*  404 */     return sslSocketFactory();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "connectionSpecs"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_connectionSpecs")
/*      */   @NotNull
/*      */   public final List<ConnectionSpec> -deprecated_connectionSpecs() {
/*  411 */     return this.connectionSpecs;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "protocols"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_protocols")
/*      */   @NotNull
/*      */   public final List<Protocol> -deprecated_protocols() {
/*  418 */     return this.protocols;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "hostnameVerifier"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_hostnameVerifier")
/*      */   @NotNull
/*      */   public final HostnameVerifier -deprecated_hostnameVerifier() {
/*  425 */     return this.hostnameVerifier;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "certificatePinner"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_certificatePinner")
/*      */   @NotNull
/*      */   public final CertificatePinner -deprecated_certificatePinner() {
/*  432 */     return this.certificatePinner;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "callTimeoutMillis"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_callTimeoutMillis")
/*      */   public final int -deprecated_callTimeoutMillis() {
/*  439 */     return this.callTimeoutMillis;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "connectTimeoutMillis"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_connectTimeoutMillis")
/*      */   public final int -deprecated_connectTimeoutMillis() {
/*  446 */     return this.connectTimeoutMillis;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "readTimeoutMillis"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_readTimeoutMillis")
/*      */   public final int -deprecated_readTimeoutMillis() {
/*  453 */     return this.readTimeoutMillis;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "writeTimeoutMillis"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_writeTimeoutMillis")
/*      */   public final int -deprecated_writeTimeoutMillis() {
/*  460 */     return this.writeTimeoutMillis;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "pingIntervalMillis"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_pingIntervalMillis")
/*      */   public final int -deprecated_pingIntervalMillis() {
/*  467 */     return this.pingIntervalMillis;
/*      */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000ø\001\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\b\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\005\n\002\020 \n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\b\n\002\030\002\n\002\b\005\n\002\020!\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\n\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\006\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\006\030\0002\0020\001B\017\b\020\022\006\020\002\032\0020\003¢\006\002\020\004B\005¢\006\002\020\005J?\020\001\032\0020\0002*\b\004\020\001\032#\022\027\022\0250¡\001¢\006\017\b¢\001\022\n\b£\001\022\005\b\b(¤\001\022\005\022\0030¥\0010 \001H\bø\001\000¢\006\003\b¦\001J\020\020\001\032\0020\0002\007\020§\001\032\0020]J?\020¨\001\032\0020\0002*\b\004\020\001\032#\022\027\022\0250¡\001¢\006\017\b¢\001\022\n\b£\001\022\005\b\b(¤\001\022\005\022\0030¥\0010 \001H\bø\001\000¢\006\003\b©\001J\020\020¨\001\032\0020\0002\007\020§\001\032\0020]J\016\020\006\032\0020\0002\006\020\006\032\0020\007J\007\020ª\001\032\0020\003J\020\020\f\032\0020\0002\b\020\f\032\004\030\0010\rJ\022\020\022\032\0020\0002\b\020«\001\032\0030¬\001H\007J\031\020\022\032\0020\0002\007\020­\001\032\0020`2\b\020®\001\032\0030¯\001J\016\020\036\032\0020\0002\006\020\036\032\0020\037J\022\020$\032\0020\0002\b\020«\001\032\0030¬\001H\007J\031\020$\032\0020\0002\007\020­\001\032\0020`2\b\020®\001\032\0030¯\001J\016\020'\032\0020\0002\006\020'\032\0020(J\024\020-\032\0020\0002\f\020-\032\b\022\004\022\0020/0.J\016\0204\032\0020\0002\006\0204\032\00205J\016\020:\032\0020\0002\006\020:\032\0020;J\016\020@\032\0020\0002\006\020@\032\0020AJ\021\020°\001\032\0020\0002\b\020°\001\032\0030±\001J\016\020F\032\0020\0002\006\020F\032\0020GJ\016\020L\032\0020\0002\006\020L\032\0020MJ\017\020R\032\0020\0002\007\020²\001\032\0020MJ\016\020U\032\0020\0002\006\020U\032\0020VJ\f\020[\032\b\022\004\022\0020]0\\J\017\020_\032\0020\0002\007\020³\001\032\0020`J\f\020e\032\b\022\004\022\0020]0\\J\022\020g\032\0020\0002\b\020«\001\032\0030¬\001H\007J\031\020g\032\0020\0002\007\020´\001\032\0020`2\b\020®\001\032\0030¯\001J\024\020j\032\0020\0002\f\020j\032\b\022\004\022\0020k0.J\020\020n\032\0020\0002\b\020n\032\004\030\0010oJ\016\020t\032\0020\0002\006\020t\032\0020\007J\016\020w\032\0020\0002\006\020w\032\0020xJ\022\020}\032\0020\0002\b\020«\001\032\0030¬\001H\007J\031\020}\032\0020\0002\007\020­\001\032\0020`2\b\020®\001\032\0030¯\001J\020\020\001\032\0020\0002\007\020\001\032\0020MJ\021\020\001\032\0020\0002\b\020\001\032\0030\001J\023\020µ\001\032\0020\0002\b\020µ\001\032\0030\001H\007J\033\020µ\001\032\0020\0002\b\020µ\001\032\0030\0012\b\020¶\001\032\0030\001J\023\020\001\032\0020\0002\b\020«\001\032\0030¬\001H\007J\032\020\001\032\0020\0002\007\020­\001\032\0020`2\b\020®\001\032\0030¯\001R\032\020\006\032\0020\007X\016¢\006\016\n\000\032\004\b\b\020\t\"\004\b\n\020\013R\034\020\f\032\004\030\0010\rX\016¢\006\016\n\000\032\004\b\016\020\017\"\004\b\020\020\021R\032\020\022\032\0020\023X\016¢\006\016\n\000\032\004\b\024\020\025\"\004\b\026\020\027R\034\020\030\032\004\030\0010\031X\016¢\006\016\n\000\032\004\b\032\020\033\"\004\b\034\020\035R\032\020\036\032\0020\037X\016¢\006\016\n\000\032\004\b \020!\"\004\b\"\020#R\032\020$\032\0020\023X\016¢\006\016\n\000\032\004\b%\020\025\"\004\b&\020\027R\032\020'\032\0020(X\016¢\006\016\n\000\032\004\b)\020*\"\004\b+\020,R \020-\032\b\022\004\022\0020/0.X\016¢\006\016\n\000\032\004\b0\0201\"\004\b2\0203R\032\0204\032\00205X\016¢\006\016\n\000\032\004\b6\0207\"\004\b8\0209R\032\020:\032\0020;X\016¢\006\016\n\000\032\004\b<\020=\"\004\b>\020?R\032\020@\032\0020AX\016¢\006\016\n\000\032\004\bB\020C\"\004\bD\020ER\032\020F\032\0020GX\016¢\006\016\n\000\032\004\bH\020I\"\004\bJ\020KR\032\020L\032\0020MX\016¢\006\016\n\000\032\004\bN\020O\"\004\bP\020QR\032\020R\032\0020MX\016¢\006\016\n\000\032\004\bS\020O\"\004\bT\020QR\032\020U\032\0020VX\016¢\006\016\n\000\032\004\bW\020X\"\004\bY\020ZR\032\020[\032\b\022\004\022\0020]0\\X\004¢\006\b\n\000\032\004\b^\0201R\032\020_\032\0020`X\016¢\006\016\n\000\032\004\ba\020b\"\004\bc\020dR\032\020e\032\b\022\004\022\0020]0\\X\004¢\006\b\n\000\032\004\bf\0201R\032\020g\032\0020\023X\016¢\006\016\n\000\032\004\bh\020\025\"\004\bi\020\027R \020j\032\b\022\004\022\0020k0.X\016¢\006\016\n\000\032\004\bl\0201\"\004\bm\0203R\034\020n\032\004\030\0010oX\016¢\006\016\n\000\032\004\bp\020q\"\004\br\020sR\032\020t\032\0020\007X\016¢\006\016\n\000\032\004\bu\020\t\"\004\bv\020\013R\034\020w\032\004\030\0010xX\016¢\006\016\n\000\032\004\by\020z\"\004\b{\020|R\032\020}\032\0020\023X\016¢\006\016\n\000\032\004\b~\020\025\"\004\b\020\027R\035\020\001\032\0020MX\016¢\006\020\n\000\032\005\b\001\020O\"\005\b\001\020QR\"\020\001\032\005\030\0010\001X\016¢\006\022\n\000\032\006\b\001\020\001\"\006\b\001\020\001R \020\001\032\0030\001X\016¢\006\022\n\000\032\006\b\001\020\001\"\006\b\001\020\001R\"\020\001\032\005\030\0010\001X\016¢\006\022\n\000\032\006\b\001\020\001\"\006\b\001\020\001R\035\020\001\032\0020\023X\016¢\006\020\n\000\032\005\b\001\020\025\"\005\b\001\020\027R\"\020\001\032\005\030\0010\001X\016¢\006\022\n\000\032\006\b\001\020\001\"\006\b\001\020\001\002\007\n\005\b20\001¨\006·\001"}, d2 = {"Lokhttp3/OkHttpClient$Builder;", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "()V", "authenticator", "Lokhttp3/Authenticator;", "getAuthenticator$okhttp", "()Lokhttp3/Authenticator;", "setAuthenticator$okhttp", "(Lokhttp3/Authenticator;)V", "cache", "Lokhttp3/Cache;", "getCache$okhttp", "()Lokhttp3/Cache;", "setCache$okhttp", "(Lokhttp3/Cache;)V", "callTimeout", "", "getCallTimeout$okhttp", "()I", "setCallTimeout$okhttp", "(I)V", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "setCertificateChainCleaner$okhttp", "(Lokhttp3/internal/tls/CertificateChainCleaner;)V", "certificatePinner", "Lokhttp3/CertificatePinner;", "getCertificatePinner$okhttp", "()Lokhttp3/CertificatePinner;", "setCertificatePinner$okhttp", "(Lokhttp3/CertificatePinner;)V", "connectTimeout", "getConnectTimeout$okhttp", "setConnectTimeout$okhttp", "connectionPool", "Lokhttp3/ConnectionPool;", "getConnectionPool$okhttp", "()Lokhttp3/ConnectionPool;", "setConnectionPool$okhttp", "(Lokhttp3/ConnectionPool;)V", "connectionSpecs", "", "Lokhttp3/ConnectionSpec;", "getConnectionSpecs$okhttp", "()Ljava/util/List;", "setConnectionSpecs$okhttp", "(Ljava/util/List;)V", "cookieJar", "Lokhttp3/CookieJar;", "getCookieJar$okhttp", "()Lokhttp3/CookieJar;", "setCookieJar$okhttp", "(Lokhttp3/CookieJar;)V", "dispatcher", "Lokhttp3/Dispatcher;", "getDispatcher$okhttp", "()Lokhttp3/Dispatcher;", "setDispatcher$okhttp", "(Lokhttp3/Dispatcher;)V", "dns", "Lokhttp3/Dns;", "getDns$okhttp", "()Lokhttp3/Dns;", "setDns$okhttp", "(Lokhttp3/Dns;)V", "eventListenerFactory", "Lokhttp3/EventListener$Factory;", "getEventListenerFactory$okhttp", "()Lokhttp3/EventListener$Factory;", "setEventListenerFactory$okhttp", "(Lokhttp3/EventListener$Factory;)V", "followRedirects", "", "getFollowRedirects$okhttp", "()Z", "setFollowRedirects$okhttp", "(Z)V", "followSslRedirects", "getFollowSslRedirects$okhttp", "setFollowSslRedirects$okhttp", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "getHostnameVerifier$okhttp", "()Ljavax/net/ssl/HostnameVerifier;", "setHostnameVerifier$okhttp", "(Ljavax/net/ssl/HostnameVerifier;)V", "interceptors", "", "Lokhttp3/Interceptor;", "getInterceptors$okhttp", "minWebSocketMessageToCompress", "", "getMinWebSocketMessageToCompress$okhttp", "()J", "setMinWebSocketMessageToCompress$okhttp", "(J)V", "networkInterceptors", "getNetworkInterceptors$okhttp", "pingInterval", "getPingInterval$okhttp", "setPingInterval$okhttp", "protocols", "Lokhttp3/Protocol;", "getProtocols$okhttp", "setProtocols$okhttp", "proxy", "Ljava/net/Proxy;", "getProxy$okhttp", "()Ljava/net/Proxy;", "setProxy$okhttp", "(Ljava/net/Proxy;)V", "proxyAuthenticator", "getProxyAuthenticator$okhttp", "setProxyAuthenticator$okhttp", "proxySelector", "Ljava/net/ProxySelector;", "getProxySelector$okhttp", "()Ljava/net/ProxySelector;", "setProxySelector$okhttp", "(Ljava/net/ProxySelector;)V", "readTimeout", "getReadTimeout$okhttp", "setReadTimeout$okhttp", "retryOnConnectionFailure", "getRetryOnConnectionFailure$okhttp", "setRetryOnConnectionFailure$okhttp", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase$okhttp", "()Lokhttp3/internal/connection/RouteDatabase;", "setRouteDatabase$okhttp", "(Lokhttp3/internal/connection/RouteDatabase;)V", "socketFactory", "Ljavax/net/SocketFactory;", "getSocketFactory$okhttp", "()Ljavax/net/SocketFactory;", "setSocketFactory$okhttp", "(Ljavax/net/SocketFactory;)V", "sslSocketFactoryOrNull", "Ljavax/net/ssl/SSLSocketFactory;", "getSslSocketFactoryOrNull$okhttp", "()Ljavax/net/ssl/SSLSocketFactory;", "setSslSocketFactoryOrNull$okhttp", "(Ljavax/net/ssl/SSLSocketFactory;)V", "writeTimeout", "getWriteTimeout$okhttp", "setWriteTimeout$okhttp", "x509TrustManagerOrNull", "Ljavax/net/ssl/X509TrustManager;", "getX509TrustManagerOrNull$okhttp", "()Ljavax/net/ssl/X509TrustManager;", "setX509TrustManagerOrNull$okhttp", "(Ljavax/net/ssl/X509TrustManager;)V", "addInterceptor", "block", "Lkotlin/Function1;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/ParameterName;", "name", "chain", "Lokhttp3/Response;", "-addInterceptor", "interceptor", "addNetworkInterceptor", "-addNetworkInterceptor", "build", "duration", "Ljava/time/Duration;", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "eventListener", "Lokhttp3/EventListener;", "followProtocolRedirects", "bytes", "interval", "sslSocketFactory", "trustManager", "okhttp"})
/*      */   public static final class Builder { @NotNull
/*  470 */     private Dispatcher dispatcher = new Dispatcher(); @NotNull public final Dispatcher getDispatcher$okhttp() { return this.dispatcher; } public final void setDispatcher$okhttp(@NotNull Dispatcher <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.dispatcher = <set-?>; } @NotNull
/*  471 */     private ConnectionPool connectionPool = new ConnectionPool(); @NotNull private final List<Interceptor> interceptors; @NotNull private final List<Interceptor> networkInterceptors; @NotNull private EventListener.Factory eventListenerFactory; private boolean retryOnConnectionFailure; @NotNull private Authenticator authenticator; private boolean followRedirects; private boolean followSslRedirects; @NotNull private CookieJar cookieJar; @Nullable private Cache cache; @NotNull private Dns dns; @Nullable private Proxy proxy; @Nullable private ProxySelector proxySelector; @NotNull private Authenticator proxyAuthenticator; @NotNull private SocketFactory socketFactory; @Nullable private SSLSocketFactory sslSocketFactoryOrNull; @Nullable private X509TrustManager x509TrustManagerOrNull; @NotNull private List<ConnectionSpec> connectionSpecs; @NotNull private List<? extends Protocol> protocols; @NotNull private HostnameVerifier hostnameVerifier; @NotNull private CertificatePinner certificatePinner; @Nullable private CertificateChainCleaner certificateChainCleaner; private int callTimeout; private int connectTimeout; private int readTimeout; private int writeTimeout; private int pingInterval; private long minWebSocketMessageToCompress; @Nullable private RouteDatabase routeDatabase; @NotNull public final ConnectionPool getConnectionPool$okhttp() { return this.connectionPool; } public final void setConnectionPool$okhttp(@NotNull ConnectionPool <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.connectionPool = <set-?>; } @NotNull
/*  472 */     public final List<Interceptor> getInterceptors$okhttp() { return this.interceptors; } public Builder() { boolean bool = false; this.interceptors = new ArrayList<>();
/*  473 */       bool = false; this.networkInterceptors = new ArrayList<>();
/*  474 */       this.eventListenerFactory = Util.asFactory(EventListener.NONE);
/*  475 */       this.retryOnConnectionFailure = true;
/*  476 */       this.authenticator = Authenticator.NONE;
/*  477 */       this.followRedirects = true;
/*  478 */       this.followSslRedirects = true;
/*  479 */       this.cookieJar = CookieJar.NO_COOKIES;
/*      */       
/*  481 */       this.dns = Dns.SYSTEM;
/*      */ 
/*      */       
/*  484 */       this.proxyAuthenticator = Authenticator.NONE;
/*  485 */       Intrinsics.checkNotNullExpressionValue(SocketFactory.getDefault(), "SocketFactory.getDefault()"); this.socketFactory = SocketFactory.getDefault();
/*      */ 
/*      */       
/*  488 */       this.connectionSpecs = OkHttpClient.Companion.getDEFAULT_CONNECTION_SPECS$okhttp();
/*  489 */       this.protocols = OkHttpClient.Companion.getDEFAULT_PROTOCOLS$okhttp();
/*  490 */       this.hostnameVerifier = (HostnameVerifier)OkHostnameVerifier.INSTANCE;
/*  491 */       this.certificatePinner = CertificatePinner.DEFAULT;
/*      */ 
/*      */       
/*  494 */       this.connectTimeout = 10000;
/*  495 */       this.readTimeout = 10000;
/*  496 */       this.writeTimeout = 10000;
/*      */       
/*  498 */       this.minWebSocketMessageToCompress = 1024L; } @NotNull public final List<Interceptor> getNetworkInterceptors$okhttp() { return this.networkInterceptors; } @NotNull public final EventListener.Factory getEventListenerFactory$okhttp() { return this.eventListenerFactory; } public final void setEventListenerFactory$okhttp(@NotNull EventListener.Factory <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.eventListenerFactory = <set-?>; } public final boolean getRetryOnConnectionFailure$okhttp() { return this.retryOnConnectionFailure; } public final void setRetryOnConnectionFailure$okhttp(boolean <set-?>) { this.retryOnConnectionFailure = <set-?>; } @NotNull public final Authenticator getAuthenticator$okhttp() { return this.authenticator; } public final void setAuthenticator$okhttp(@NotNull Authenticator <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.authenticator = <set-?>; } public final boolean getFollowRedirects$okhttp() { return this.followRedirects; } public final void setFollowRedirects$okhttp(boolean <set-?>) { this.followRedirects = <set-?>; } public final boolean getFollowSslRedirects$okhttp() { return this.followSslRedirects; } public final void setFollowSslRedirects$okhttp(boolean <set-?>) { this.followSslRedirects = <set-?>; } @NotNull public final CookieJar getCookieJar$okhttp() { return this.cookieJar; } public final void setCookieJar$okhttp(@NotNull CookieJar <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.cookieJar = <set-?>; } @Nullable public final Cache getCache$okhttp() { return this.cache; } public final void setCache$okhttp(@Nullable Cache <set-?>) { this.cache = <set-?>; } @NotNull public final Dns getDns$okhttp() { return this.dns; } public final void setDns$okhttp(@NotNull Dns <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.dns = <set-?>; } @Nullable public final Proxy getProxy$okhttp() { return this.proxy; } public final void setProxy$okhttp(@Nullable Proxy <set-?>) { this.proxy = <set-?>; } @Nullable public final ProxySelector getProxySelector$okhttp() { return this.proxySelector; } public final void setProxySelector$okhttp(@Nullable ProxySelector <set-?>) { this.proxySelector = <set-?>; } @NotNull public final Authenticator getProxyAuthenticator$okhttp() { return this.proxyAuthenticator; } public final void setProxyAuthenticator$okhttp(@NotNull Authenticator <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.proxyAuthenticator = <set-?>; } @NotNull public final SocketFactory getSocketFactory$okhttp() { return this.socketFactory; } public final long getMinWebSocketMessageToCompress$okhttp() { return this.minWebSocketMessageToCompress; } public final void setSocketFactory$okhttp(@NotNull SocketFactory <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.socketFactory = <set-?>; } @Nullable public final SSLSocketFactory getSslSocketFactoryOrNull$okhttp() { return this.sslSocketFactoryOrNull; } public final void setSslSocketFactoryOrNull$okhttp(@Nullable SSLSocketFactory <set-?>) { this.sslSocketFactoryOrNull = <set-?>; } @Nullable public final X509TrustManager getX509TrustManagerOrNull$okhttp() { return this.x509TrustManagerOrNull; } public final void setX509TrustManagerOrNull$okhttp(@Nullable X509TrustManager <set-?>) { this.x509TrustManagerOrNull = <set-?>; } @NotNull public final List<ConnectionSpec> getConnectionSpecs$okhttp() { return this.connectionSpecs; } public final void setConnectionSpecs$okhttp(@NotNull List<ConnectionSpec> <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.connectionSpecs = <set-?>; } @NotNull public final List<Protocol> getProtocols$okhttp() { return (List)this.protocols; } public final void setProtocols$okhttp(@NotNull List<? extends Protocol> <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.protocols = <set-?>; } @NotNull public final HostnameVerifier getHostnameVerifier$okhttp() { return this.hostnameVerifier; } public final void setHostnameVerifier$okhttp(@NotNull HostnameVerifier <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.hostnameVerifier = <set-?>; } @NotNull public final CertificatePinner getCertificatePinner$okhttp() { return this.certificatePinner; } public final void setCertificatePinner$okhttp(@NotNull CertificatePinner <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.certificatePinner = <set-?>; } @Nullable public final CertificateChainCleaner getCertificateChainCleaner$okhttp() { return this.certificateChainCleaner; } public final void setCertificateChainCleaner$okhttp(@Nullable CertificateChainCleaner <set-?>) { this.certificateChainCleaner = <set-?>; } public final int getCallTimeout$okhttp() { return this.callTimeout; } public final void setCallTimeout$okhttp(int <set-?>) { this.callTimeout = <set-?>; } public final int getConnectTimeout$okhttp() { return this.connectTimeout; } public final void setConnectTimeout$okhttp(int <set-?>) { this.connectTimeout = <set-?>; } public final int getReadTimeout$okhttp() { return this.readTimeout; } public final void setReadTimeout$okhttp(int <set-?>) { this.readTimeout = <set-?>; } public final int getWriteTimeout$okhttp() { return this.writeTimeout; } public final void setWriteTimeout$okhttp(int <set-?>) { this.writeTimeout = <set-?>; } public final int getPingInterval$okhttp() { return this.pingInterval; } public final void setPingInterval$okhttp(int <set-?>) { this.pingInterval = <set-?>; } public final void setMinWebSocketMessageToCompress$okhttp(long <set-?>) { this.minWebSocketMessageToCompress = <set-?>; } @Nullable
/*  499 */     public final RouteDatabase getRouteDatabase$okhttp() { return this.routeDatabase; } public final void setRouteDatabase$okhttp(@Nullable RouteDatabase <set-?>) { this.routeDatabase = <set-?>; }
/*      */      public Builder(@NotNull OkHttpClient okHttpClient) {
/*  501 */       this();
/*  502 */       this.dispatcher = okHttpClient.dispatcher();
/*  503 */       this.connectionPool = okHttpClient.connectionPool();
/*  504 */       List<Interceptor> list1 = this.interceptors, list2 = okHttpClient.interceptors(); boolean bool = false; CollectionsKt.addAll(list1, list2);
/*  505 */       list1 = this.networkInterceptors; list2 = okHttpClient.networkInterceptors(); bool = false; CollectionsKt.addAll(list1, list2);
/*  506 */       this.eventListenerFactory = okHttpClient.eventListenerFactory();
/*  507 */       this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure();
/*  508 */       this.authenticator = okHttpClient.authenticator();
/*  509 */       this.followRedirects = okHttpClient.followRedirects();
/*  510 */       this.followSslRedirects = okHttpClient.followSslRedirects();
/*  511 */       this.cookieJar = okHttpClient.cookieJar();
/*  512 */       this.cache = okHttpClient.cache();
/*  513 */       this.dns = okHttpClient.dns();
/*  514 */       this.proxy = okHttpClient.proxy();
/*  515 */       this.proxySelector = okHttpClient.proxySelector();
/*  516 */       this.proxyAuthenticator = okHttpClient.proxyAuthenticator();
/*  517 */       this.socketFactory = okHttpClient.socketFactory();
/*  518 */       this.sslSocketFactoryOrNull = okHttpClient.sslSocketFactoryOrNull;
/*  519 */       this.x509TrustManagerOrNull = okHttpClient.x509TrustManager();
/*  520 */       this.connectionSpecs = okHttpClient.connectionSpecs();
/*  521 */       this.protocols = okHttpClient.protocols();
/*  522 */       this.hostnameVerifier = okHttpClient.hostnameVerifier();
/*  523 */       this.certificatePinner = okHttpClient.certificatePinner();
/*  524 */       this.certificateChainCleaner = okHttpClient.certificateChainCleaner();
/*  525 */       this.callTimeout = okHttpClient.callTimeoutMillis();
/*  526 */       this.connectTimeout = okHttpClient.connectTimeoutMillis();
/*  527 */       this.readTimeout = okHttpClient.readTimeoutMillis();
/*  528 */       this.writeTimeout = okHttpClient.writeTimeoutMillis();
/*  529 */       this.pingInterval = okHttpClient.pingIntervalMillis();
/*  530 */       this.minWebSocketMessageToCompress = okHttpClient.minWebSocketMessageToCompress();
/*  531 */       this.routeDatabase = okHttpClient.getRouteDatabase();
/*      */     }
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder dispatcher(@NotNull Dispatcher dispatcher) {
/*  537 */       Intrinsics.checkNotNullParameter(dispatcher, "dispatcher"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$dispatcher$1 = 0;
/*  538 */       $this$apply.dispatcher = dispatcher;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder connectionPool(@NotNull ConnectionPool connectionPool) {
/*  546 */       Intrinsics.checkNotNullParameter(connectionPool, "connectionPool"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$connectionPool$1 = 0;
/*  547 */       $this$apply.connectionPool = connectionPool;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final List<Interceptor> interceptors()
/*      */     {
/*  555 */       return this.interceptors; } @NotNull
/*      */     public final Builder addInterceptor(@NotNull Interceptor interceptor) {
/*  557 */       Intrinsics.checkNotNullParameter(interceptor, "interceptor"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$addInterceptor$1 = 0;
/*  558 */       List<Interceptor> list = $this$apply.interceptors; Interceptor interceptor1 = interceptor; boolean bool3 = false; list.add(interceptor1);
/*      */       return builder1;
/*      */     }
/*      */     @JvmName(name = "-addInterceptor")
/*      */     @NotNull
/*  563 */     public final Builder -addInterceptor(@NotNull Function1 block) { int $i$f$-addInterceptor = 0; Intrinsics.checkNotNullParameter(block, "block"); return addInterceptor(new OkHttpClient$Builder$addInterceptor$2(block)); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\020\000\032\0020\0012\006\020\002\032\0020\003H\n¢\006\002\b\004"}, d2 = {"<anonymous>", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "intercept"}) public static final class OkHttpClient$Builder$addInterceptor$2 implements Interceptor { @NotNull public final Response intercept(@NotNull Interceptor.Chain chain) { Intrinsics.checkNotNullParameter(chain, "chain"); return (Response)this.$block.invoke(chain); }
/*      */ 
/*      */       
/*      */       public OkHttpClient$Builder$addInterceptor$2(Function1 param2Function1) {} }
/*      */ 
/*      */     
/*      */     @NotNull
/*  570 */     public final List<Interceptor> networkInterceptors() { return this.networkInterceptors; } @NotNull
/*      */     public final Builder addNetworkInterceptor(@NotNull Interceptor interceptor) {
/*  572 */       Intrinsics.checkNotNullParameter(interceptor, "interceptor"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$addNetworkInterceptor$1 = 0;
/*  573 */       List<Interceptor> list = $this$apply.networkInterceptors; Interceptor interceptor1 = interceptor; boolean bool3 = false; list.add(interceptor1);
/*      */       return builder1;
/*      */     }
/*      */     @JvmName(name = "-addNetworkInterceptor")
/*      */     @NotNull
/*  578 */     public final Builder -addNetworkInterceptor(@NotNull Function1 block) { int $i$f$-addNetworkInterceptor = 0; Intrinsics.checkNotNullParameter(block, "block"); return addNetworkInterceptor(new OkHttpClient$Builder$addNetworkInterceptor$2(block)); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\020\000\032\0020\0012\006\020\002\032\0020\003H\n¢\006\002\b\004"}, d2 = {"<anonymous>", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "intercept"}) public static final class OkHttpClient$Builder$addNetworkInterceptor$2 implements Interceptor { @NotNull public final Response intercept(@NotNull Interceptor.Chain chain) { Intrinsics.checkNotNullParameter(chain, "chain"); return (Response)this.$block.invoke(chain); }
/*      */ 
/*      */       
/*      */       public OkHttpClient$Builder$addNetworkInterceptor$2(Function1 param2Function1) {} }
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder eventListener(@NotNull EventListener eventListener) {
/*  586 */       Intrinsics.checkNotNullParameter(eventListener, "eventListener"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$eventListener$1 = 0;
/*  587 */       $this$apply.eventListenerFactory = Util.asFactory(eventListener);
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder eventListenerFactory(@NotNull EventListener.Factory eventListenerFactory) {
/*  596 */       Intrinsics.checkNotNullParameter(eventListenerFactory, "eventListenerFactory"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$eventListenerFactory$1 = 0;
/*  597 */       $this$apply.eventListenerFactory = eventListenerFactory;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
/*  618 */       Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$retryOnConnectionFailure$1 = 0;
/*  619 */       $this$apply.retryOnConnectionFailure = retryOnConnectionFailure;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder authenticator(@NotNull Authenticator authenticator) {
/*  628 */       Intrinsics.checkNotNullParameter(authenticator, "authenticator"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$authenticator$1 = 0;
/*  629 */       $this$apply.authenticator = authenticator;
/*      */       return builder1;
/*      */     } @NotNull
/*      */     public final Builder followRedirects(boolean followRedirects) {
/*  633 */       Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$followRedirects$1 = 0;
/*  634 */       $this$apply.followRedirects = followRedirects;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder followSslRedirects(boolean followProtocolRedirects) {
/*  643 */       Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$followSslRedirects$1 = 0;
/*  644 */       $this$apply.followSslRedirects = followProtocolRedirects;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder cookieJar(@NotNull CookieJar cookieJar) {
/*  653 */       Intrinsics.checkNotNullParameter(cookieJar, "cookieJar"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$cookieJar$1 = 0;
/*  654 */       $this$apply.cookieJar = cookieJar;
/*      */       return builder1;
/*      */     } @NotNull
/*      */     public final Builder cache(@Nullable Cache cache) {
/*  658 */       Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$cache$1 = 0;
/*  659 */       $this$apply.cache = cache;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder dns(@NotNull Dns dns) {
/*  667 */       Intrinsics.checkNotNullParameter(dns, "dns"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$dns$1 = 0;
/*  668 */       if ((Intrinsics.areEqual(dns, $this$apply.dns) ^ true) != 0) {
/*  669 */         $this$apply.routeDatabase = (RouteDatabase)null;
/*      */       }
/*  671 */       $this$apply.dns = dns;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder proxy(@Nullable Proxy proxy) {
/*  679 */       Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$proxy$1 = 0;
/*  680 */       if ((Intrinsics.areEqual(proxy, $this$apply.proxy) ^ true) != 0) {
/*  681 */         $this$apply.routeDatabase = (RouteDatabase)null;
/*      */       }
/*  683 */       $this$apply.proxy = proxy;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder proxySelector(@NotNull ProxySelector proxySelector) {
/*  693 */       Intrinsics.checkNotNullParameter(proxySelector, "proxySelector"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$proxySelector$1 = 0;
/*  694 */       if ((Intrinsics.areEqual(proxySelector, $this$apply.proxySelector) ^ true) != 0) {
/*  695 */         $this$apply.routeDatabase = (RouteDatabase)null;
/*      */       }
/*      */       
/*  698 */       $this$apply.proxySelector = proxySelector;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder proxyAuthenticator(@NotNull Authenticator proxyAuthenticator) {
/*  707 */       Intrinsics.checkNotNullParameter(proxyAuthenticator, "proxyAuthenticator"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$proxyAuthenticator$1 = 0;
/*  708 */       if ((Intrinsics.areEqual(proxyAuthenticator, $this$apply.proxyAuthenticator) ^ true) != 0) {
/*  709 */         $this$apply.routeDatabase = (RouteDatabase)null;
/*      */       }
/*      */       
/*  712 */       $this$apply.proxyAuthenticator = proxyAuthenticator;
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder socketFactory(@NotNull SocketFactory socketFactory) {
/*  722 */       Intrinsics.checkNotNullParameter(socketFactory, "socketFactory"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-OkHttpClient$Builder$socketFactory$1 = 0;
/*  723 */       boolean bool3 = !(socketFactory instanceof SSLSocketFactory) ? true : false, bool4 = false, bool5 = false; if (!bool3) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1080 */         int $i$a$-require-OkHttpClient$Builder$socketFactory$1$1 = 0;
/*      */         String str = "socketFactory instanceof SSLSocketFactory";
/*      */         throw (Throwable)new IllegalArgumentException(str.toString());
/*      */       } 
/*      */       if ((Intrinsics.areEqual(socketFactory, $this$apply.socketFactory) ^ true) != 0)
/*      */         $this$apply.routeDatabase = (RouteDatabase)null; 
/*      */       $this$apply.socketFactory = socketFactory;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @Deprecated(message = "Use the sslSocketFactory overload that accepts a X509TrustManager.", level = DeprecationLevel.ERROR)
/*      */     @NotNull
/*      */     public final Builder sslSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
/*      */       Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$sslSocketFactory$1 = 0;
/*      */       if ((Intrinsics.areEqual(sslSocketFactory, $this$apply.sslSocketFactoryOrNull) ^ true) != 0)
/*      */         $this$apply.routeDatabase = (RouteDatabase)null; 
/*      */       $this$apply.sslSocketFactoryOrNull = sslSocketFactory;
/*      */       if (Platform.Companion.get().trustManager(sslSocketFactory) != null) {
/*      */         $this$apply.x509TrustManagerOrNull = Platform.Companion.get().trustManager(sslSocketFactory);
/*      */         Intrinsics.checkNotNull($this$apply.x509TrustManagerOrNull);
/*      */         $this$apply.certificateChainCleaner = Platform.Companion.get().buildCertificateChainCleaner($this$apply.x509TrustManagerOrNull);
/*      */         return builder1;
/*      */       } 
/*      */       Platform.Companion.get().trustManager(sslSocketFactory);
/*      */       throw (Throwable)new IllegalStateException("Unable to extract the trust manager on " + Platform.Companion.get() + ", " + "sslSocketFactory is " + sslSocketFactory.getClass());
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder sslSocketFactory(@NotNull SSLSocketFactory sslSocketFactory, @NotNull X509TrustManager trustManager) {
/*      */       Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
/*      */       Intrinsics.checkNotNullParameter(trustManager, "trustManager");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$sslSocketFactory$2 = 0;
/*      */       if ((Intrinsics.areEqual(sslSocketFactory, $this$apply.sslSocketFactoryOrNull) ^ true) != 0 || (Intrinsics.areEqual(trustManager, $this$apply.x509TrustManagerOrNull) ^ true) != 0)
/*      */         $this$apply.routeDatabase = (RouteDatabase)null; 
/*      */       $this$apply.sslSocketFactoryOrNull = sslSocketFactory;
/*      */       $this$apply.certificateChainCleaner = CertificateChainCleaner.Companion.get(trustManager);
/*      */       $this$apply.x509TrustManagerOrNull = trustManager;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder connectionSpecs(@NotNull List connectionSpecs) {
/*      */       Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$connectionSpecs$1 = 0;
/*      */       if ((Intrinsics.areEqual(connectionSpecs, $this$apply.connectionSpecs) ^ true) != 0)
/*      */         $this$apply.routeDatabase = (RouteDatabase)null; 
/*      */       $this$apply.connectionSpecs = Util.toImmutableList(connectionSpecs);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder protocols(@NotNull List protocols) {
/*      */       Intrinsics.checkNotNullParameter(protocols, "protocols");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$protocols$1 = 0;
/*      */       List<?> protocolsCopy = CollectionsKt.toMutableList(protocols);
/*      */       boolean bool3 = (protocolsCopy.contains(Protocol.H2_PRIOR_KNOWLEDGE) || protocolsCopy.contains(Protocol.HTTP_1_1)) ? true : false, bool4 = false, bool5 = false;
/*      */       if (!bool3) {
/*      */         int $i$a$-require-OkHttpClient$Builder$protocols$1$1 = 0;
/*      */         String str = "protocols must contain h2_prior_knowledge or http/1.1: " + protocolsCopy;
/*      */         throw (Throwable)new IllegalArgumentException(str.toString());
/*      */       } 
/*      */       bool3 = (!protocolsCopy.contains(Protocol.H2_PRIOR_KNOWLEDGE) || protocolsCopy.size() <= 1) ? true : false;
/*      */       bool4 = false;
/*      */       bool5 = false;
/*      */       if (!bool3) {
/*      */         int $i$a$-require-OkHttpClient$Builder$protocols$1$2 = 0;
/*      */         String str = "protocols containing h2_prior_knowledge cannot use other protocols: " + protocolsCopy;
/*      */         throw (Throwable)new IllegalArgumentException(str.toString());
/*      */       } 
/*      */       bool3 = !protocolsCopy.contains(Protocol.HTTP_1_0) ? true : false;
/*      */       bool4 = false;
/*      */       bool5 = false;
/*      */       if (!bool3) {
/*      */         int $i$a$-require-OkHttpClient$Builder$protocols$1$3 = 0;
/*      */         String str = "protocols must not contain http/1.0: " + protocolsCopy;
/*      */         throw (Throwable)new IllegalArgumentException(str.toString());
/*      */       } 
/*      */       if (protocolsCopy == null)
/*      */         throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<okhttp3.Protocol?>"); 
/*      */       bool3 = !protocolsCopy.contains(null) ? true : false;
/*      */       bool4 = false;
/*      */       bool5 = false;
/*      */       if (!bool3) {
/*      */         int $i$a$-require-OkHttpClient$Builder$protocols$1$4 = 0;
/*      */         String str = "protocols must not contain null";
/*      */         throw (Throwable)new IllegalArgumentException(str.toString());
/*      */       } 
/*      */       protocolsCopy.remove(Protocol.SPDY_3);
/*      */       if ((Intrinsics.areEqual(protocolsCopy, $this$apply.protocols) ^ true) != 0)
/*      */         $this$apply.routeDatabase = (RouteDatabase)null; 
/*      */       Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(protocolsCopy), "Collections.unmodifiableList(protocolsCopy)");
/*      */       $this$apply.protocols = Collections.unmodifiableList(protocolsCopy);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder hostnameVerifier(@NotNull HostnameVerifier hostnameVerifier) {
/*      */       Intrinsics.checkNotNullParameter(hostnameVerifier, "hostnameVerifier");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$hostnameVerifier$1 = 0;
/*      */       if ((Intrinsics.areEqual(hostnameVerifier, $this$apply.hostnameVerifier) ^ true) != 0)
/*      */         $this$apply.routeDatabase = (RouteDatabase)null; 
/*      */       $this$apply.hostnameVerifier = hostnameVerifier;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder certificatePinner(@NotNull CertificatePinner certificatePinner) {
/*      */       Intrinsics.checkNotNullParameter(certificatePinner, "certificatePinner");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$certificatePinner$1 = 0;
/*      */       if ((Intrinsics.areEqual(certificatePinner, $this$apply.certificatePinner) ^ true) != 0)
/*      */         $this$apply.routeDatabase = (RouteDatabase)null; 
/*      */       $this$apply.certificatePinner = certificatePinner;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder callTimeout(long timeout, @NotNull TimeUnit unit) {
/*      */       Intrinsics.checkNotNullParameter(unit, "unit");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$callTimeout$1 = 0;
/*      */       $this$apply.callTimeout = Util.checkDuration("timeout", timeout, unit);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @IgnoreJRERequirement
/*      */     @NotNull
/*      */     public final Builder callTimeout(@NotNull Duration duration) {
/*      */       Intrinsics.checkNotNullParameter(duration, "duration");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$callTimeout$2 = 0;
/*      */       $this$apply.callTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder connectTimeout(long timeout, @NotNull TimeUnit unit) {
/*      */       Intrinsics.checkNotNullParameter(unit, "unit");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$connectTimeout$1 = 0;
/*      */       $this$apply.connectTimeout = Util.checkDuration("timeout", timeout, unit);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @IgnoreJRERequirement
/*      */     @NotNull
/*      */     public final Builder connectTimeout(@NotNull Duration duration) {
/*      */       Intrinsics.checkNotNullParameter(duration, "duration");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$connectTimeout$2 = 0;
/*      */       $this$apply.connectTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder readTimeout(long timeout, @NotNull TimeUnit unit) {
/*      */       Intrinsics.checkNotNullParameter(unit, "unit");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$readTimeout$1 = 0;
/*      */       $this$apply.readTimeout = Util.checkDuration("timeout", timeout, unit);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @IgnoreJRERequirement
/*      */     @NotNull
/*      */     public final Builder readTimeout(@NotNull Duration duration) {
/*      */       Intrinsics.checkNotNullParameter(duration, "duration");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$readTimeout$2 = 0;
/*      */       $this$apply.readTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder writeTimeout(long timeout, @NotNull TimeUnit unit) {
/*      */       Intrinsics.checkNotNullParameter(unit, "unit");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$writeTimeout$1 = 0;
/*      */       $this$apply.writeTimeout = Util.checkDuration("timeout", timeout, unit);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @IgnoreJRERequirement
/*      */     @NotNull
/*      */     public final Builder writeTimeout(@NotNull Duration duration) {
/*      */       Intrinsics.checkNotNullParameter(duration, "duration");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$writeTimeout$2 = 0;
/*      */       $this$apply.writeTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder pingInterval(long interval, @NotNull TimeUnit unit) {
/*      */       Intrinsics.checkNotNullParameter(unit, "unit");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$pingInterval$1 = 0;
/*      */       $this$apply.pingInterval = Util.checkDuration("interval", interval, unit);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @IgnoreJRERequirement
/*      */     @NotNull
/*      */     public final Builder pingInterval(@NotNull Duration duration) {
/*      */       Intrinsics.checkNotNullParameter(duration, "duration");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$pingInterval$2 = 0;
/*      */       $this$apply.pingInterval(duration.toMillis(), TimeUnit.MILLISECONDS);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder minWebSocketMessageToCompress(long bytes) {
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-OkHttpClient$Builder$minWebSocketMessageToCompress$1 = 0;
/*      */       boolean bool3 = (bytes >= 0L) ? true : false, bool4 = false, bool5 = false;
/*      */       if (!bool3) {
/*      */         int $i$a$-require-OkHttpClient$Builder$minWebSocketMessageToCompress$1$1 = 0;
/*      */         String str = "minWebSocketMessageToCompress must be positive: " + bytes;
/*      */         throw (Throwable)new IllegalArgumentException(str.toString());
/*      */       } 
/*      */       $this$apply.minWebSocketMessageToCompress = bytes;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final OkHttpClient build() {
/*      */       return new OkHttpClient(this);
/*      */     } }
/*      */   
/*      */   public static final Companion Companion = new Companion(null);
/*      */   
/*      */   static {
/*      */     DEFAULT_PROTOCOLS = Util.immutableListOf((Object[])new Protocol[] { Protocol.HTTP_2, Protocol.HTTP_1_1 });
/*      */     DEFAULT_CONNECTION_SPECS = Util.immutableListOf((Object[])new ConnectionSpec[] { ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT });
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\032\020\003\032\b\022\004\022\0020\0050\004X\004¢\006\b\n\000\032\004\b\006\020\007R\032\020\b\032\b\022\004\022\0020\t0\004X\004¢\006\b\n\000\032\004\b\n\020\007¨\006\013"}, d2 = {"Lokhttp3/OkHttpClient$Companion;", "", "()V", "DEFAULT_CONNECTION_SPECS", "", "Lokhttp3/ConnectionSpec;", "getDEFAULT_CONNECTION_SPECS$okhttp", "()Ljava/util/List;", "DEFAULT_PROTOCOLS", "Lokhttp3/Protocol;", "getDEFAULT_PROTOCOLS$okhttp", "okhttp"})
/*      */   public static final class Companion {
/*      */     private Companion() {}
/*      */     
/*      */     @NotNull
/*      */     public final List<Protocol> getDEFAULT_PROTOCOLS$okhttp() {
/*      */       return OkHttpClient.DEFAULT_PROTOCOLS;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final List<ConnectionSpec> getDEFAULT_CONNECTION_SPECS$okhttp() {
/*      */       return OkHttpClient.DEFAULT_CONNECTION_SPECS;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/OkHttpClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */