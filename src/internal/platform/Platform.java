/*     */ package okhttp3.internal.platform;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.KeyStore;
/*     */ import java.security.Security;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.TrustManagerFactory;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.Protocol;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.platform.android.AndroidLog;
/*     */ import okhttp3.internal.tls.BasicCertificateChainCleaner;
/*     */ import okhttp3.internal.tls.BasicTrustRootIndex;
/*     */ import okhttp3.internal.tls.CertificateChainCleaner;
/*     */ import okhttp3.internal.tls.TrustRootIndex;
/*     */ import okio.Buffer;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000t\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\005\n\002\020\013\n\002\b\004\n\002\020\003\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\005\b\026\030\000 /2\0020\001:\001/B\005¢\006\002\020\002J\020\020\003\032\0020\0042\006\020\005\032\0020\006H\026J\020\020\007\032\0020\b2\006\020\t\032\0020\nH\026J\020\020\013\032\0020\f2\006\020\t\032\0020\nH\026J-\020\r\032\0020\0042\006\020\005\032\0020\0062\b\020\016\032\004\030\0010\0172\021\020\020\032\r\022\t\022\0070\022¢\006\002\b\0230\021H\026J \020\024\032\0020\0042\006\020\025\032\0020\0262\006\020\027\032\0020\0302\006\020\031\032\0020\032H\026J\006\020\033\032\0020\017J\022\020\034\032\004\030\0010\0172\006\020\005\032\0020\006H\026J\022\020\035\032\004\030\0010\0012\006\020\036\032\0020\017H\026J\020\020\037\032\0020 2\006\020\016\032\0020\017H\026J&\020!\032\0020\0042\006\020\"\032\0020\0172\b\b\002\020#\032\0020\0322\n\b\002\020$\032\004\030\0010%H\026J\032\020&\032\0020\0042\006\020\"\032\0020\0172\b\020'\032\004\030\0010\001H\026J\b\020(\032\0020)H\026J\020\020*\032\0020+2\006\020\t\032\0020\nH\026J\b\020,\032\0020\nH\026J\b\020-\032\0020\017H\026J\022\020\t\032\004\030\0010\n2\006\020.\032\0020+H\026¨\0060"}, d2 = {"Lokhttp3/internal/platform/Platform;", "", "()V", "afterHandshake", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "buildTrustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "configureTlsExtensions", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "connectSocket", "socket", "Ljava/net/Socket;", "address", "Ljava/net/InetSocketAddress;", "connectTimeout", "", "getPrefix", "getSelectedProtocol", "getStackTraceForCloseable", "closer", "isCleartextTrafficPermitted", "", "log", "message", "level", "t", "", "logCloseableLeak", "stackTrace", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "newSslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "platformTrustManager", "toString", "sslSocketFactory", "Companion", "okhttp"})
/*     */ public class Platform
/*     */ {
/*     */   private static volatile Platform platform;
/*     */   public static final int INFO = 4;
/*     */   public static final int WARN = 5;
/*     */   private static final Logger logger;
/*     */   
/*     */   @NotNull
/*     */   public final String getPrefix() {
/*  73 */     return "OkHttp"; } @NotNull
/*     */   public SSLContext newSSLContext() {
/*  75 */     Intrinsics.checkNotNullExpressionValue(SSLContext.getInstance("TLS"), "SSLContext.getInstance(\"TLS\")"); return SSLContext.getInstance("TLS");
/*     */   } @NotNull
/*     */   public X509TrustManager platformTrustManager() {
/*  78 */     TrustManagerFactory factory = TrustManagerFactory.getInstance(
/*  79 */         TrustManagerFactory.getDefaultAlgorithm());
/*  80 */     factory.init((KeyStore)null);
/*  81 */     Intrinsics.checkNotNullExpressionValue(factory, "factory"); Intrinsics.checkNotNull(factory.getTrustManagers()); TrustManager[] trustManagers = factory.getTrustManagers();
/*  82 */     boolean bool1 = (trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Platform$platformTrustManager$1 = 0;
/*  83 */       TrustManager[] arrayOfTrustManager = trustManagers; boolean bool = false; Intrinsics.checkNotNullExpressionValue(Arrays.toString((Object[])arrayOfTrustManager), "java.util.Arrays.toString(this)"); String str = "Unexpected default trust managers: " + Arrays.toString((Object[])arrayOfTrustManager); throw (Throwable)new IllegalStateException(str.toString()); }
/*     */     
/*  85 */     if (trustManagers[0] == null) throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");  return (X509TrustManager)trustManagers[0];
/*     */   } @Nullable
/*     */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/*     */     X509TrustManager x509TrustManager;
/*  89 */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
/*     */ 
/*     */     
/*     */     try {
/*  93 */       x509TrustManager = (X509TrustManager)Class.forName("sun.security.ssl.SSLContextImpl");
/*  94 */       Intrinsics.checkNotNullExpressionValue(x509TrustManager, "sslContextClass"); if (Util.readFieldOrNull(sslSocketFactory, (Class)x509TrustManager, "context") != null) { Object context = Util.readFieldOrNull(sslSocketFactory, (Class)x509TrustManager, "context");
/*  95 */         return (X509TrustManager)Util.readFieldOrNull(context, X509TrustManager.class, "trustManager"); }  Util.readFieldOrNull(sslSocketFactory, (Class)x509TrustManager, "context"); return null;
/*  96 */     } catch (ClassNotFoundException e) {
/*  97 */       x509TrustManager = null;
/*  98 */     } catch (RuntimeException e) {
/*     */ 
/*     */       
/* 101 */       if ((Intrinsics.areEqual(e.getClass().getName(), "java.lang.reflect.InaccessibleObjectException") ^ true) != 0) {
/* 102 */         throw (Throwable)e;
/*     */       }
/*     */       
/* 105 */       x509TrustManager = null;
/*     */     } 
/*     */     return x509TrustManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
/* 117 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols");
/*     */   }
/*     */   
/*     */   public void afterHandshake(@NotNull SSLSocket sslSocket) {
/* 121 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
/*     */   } @Nullable
/*     */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/* 124 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); return null;
/*     */   }
/*     */   
/*     */   public void connectSocket(@NotNull Socket socket, @NotNull InetSocketAddress address, int connectTimeout) throws IOException {
/* 128 */     Intrinsics.checkNotNullParameter(socket, "socket"); Intrinsics.checkNotNullParameter(address, "address"); socket.connect(address, connectTimeout);
/*     */   }
/*     */   
/*     */   public void log(@NotNull String message, int level, @Nullable Throwable t) {
/* 132 */     Intrinsics.checkNotNullParameter(message, "message"); Level logLevel = (level == 5) ? Level.WARNING : Level.INFO;
/* 133 */     logger.log(logLevel, message, t);
/*     */   }
/*     */   public boolean isCleartextTrafficPermitted(@NotNull String hostname) {
/* 136 */     Intrinsics.checkNotNullParameter(hostname, "hostname"); return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Object getStackTraceForCloseable(@NotNull String closer) {
/* 144 */     Intrinsics.checkNotNullParameter(closer, "closer"); return 
/* 145 */       logger.isLoggable(Level.FINE) ? new Throwable(closer) : 
/* 146 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void logCloseableLeak(@NotNull String message, @Nullable Object stackTrace) {
/* 151 */     Intrinsics.checkNotNullParameter(message, "message"); String logMessage = message;
/* 152 */     if (stackTrace == null) {
/* 153 */       logMessage = logMessage + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
/*     */     }
/*     */     
/* 156 */     log(logMessage, 5, (Throwable)stackTrace);
/*     */   }
/*     */   @NotNull
/*     */   public CertificateChainCleaner buildCertificateChainCleaner(@NotNull X509TrustManager trustManager) {
/* 160 */     Intrinsics.checkNotNullParameter(trustManager, "trustManager"); return (CertificateChainCleaner)new BasicCertificateChainCleaner(buildTrustRootIndex(trustManager));
/*     */   } @NotNull
/*     */   public TrustRootIndex buildTrustRootIndex(@NotNull X509TrustManager trustManager) {
/* 163 */     Intrinsics.checkNotNullParameter(trustManager, "trustManager"); Intrinsics.checkNotNullExpressionValue(trustManager.getAcceptedIssuers(), "trustManager.acceptedIssuers"); return (TrustRootIndex)new BasicTrustRootIndex(Arrays.<X509Certificate>copyOf(trustManager.getAcceptedIssuers(), (trustManager.getAcceptedIssuers()).length));
/*     */   } @NotNull
/*     */   public SSLSocketFactory newSslSocketFactory(@NotNull X509TrustManager trustManager) {
/* 166 */     Intrinsics.checkNotNullParameter(trustManager, "trustManager"); try {
/* 167 */       SSLContext sSLContext1 = newSSLContext(); boolean bool1 = false, bool2 = false; SSLContext $this$apply = sSLContext1; int $i$a$-apply-Platform$newSslSocketFactory$1 = 0;
/* 168 */       $this$apply.init(null, new TrustManager[] { trustManager }, null); Intrinsics.checkNotNullExpressionValue(sSLContext1.getSocketFactory(), "newSSLContext().apply {\n…ll)\n      }.socketFactory");
/*     */       return sSLContext1.getSocketFactory();
/* 170 */     } catch (GeneralSecurityException e) {
/* 171 */       throw new AssertionError("No System TLS: " + e, (Throwable)e);
/*     */     } 
/*     */   } @NotNull
/*     */   public String toString() {
/* 175 */     Intrinsics.checkNotNullExpressionValue(getClass().getSimpleName(), "javaClass.simpleName"); return getClass().getSimpleName();
/*     */   }
/*     */   
/* 178 */   public static final Companion Companion = new Companion(null); static { platform = Companion.findPlatform();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     logger = Logger.getLogger(OkHttpClient.class.getName()); } @JvmStatic @NotNull public static final Platform get() { return Companion.get(); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000H\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\002\020\016\n\000\n\002\030\002\n\000\n\002\020\022\n\002\b\005\n\002\020\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\032\020\021\032\b\022\004\022\0020\0230\0222\f\020\024\032\b\022\004\022\0020\0250\022J\024\020\026\032\0020\0272\f\020\024\032\b\022\004\022\0020\0250\022J\b\020\030\032\0020\020H\002J\b\020\031\032\0020\020H\002J\b\020\032\032\0020\020H\002J\b\020\033\032\0020\020H\007J\020\020\034\032\0020\0352\b\b\002\020\017\032\0020\020R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\021\020\006\032\0020\0078F¢\006\006\032\004\b\006\020\bR\024\020\t\032\0020\0078BX\004¢\006\006\032\004\b\t\020\bR\024\020\n\032\0020\0078BX\004¢\006\006\032\004\b\n\020\bR\024\020\013\032\0020\0078BX\004¢\006\006\032\004\b\013\020\bR\026\020\f\032\n \016*\004\030\0010\r0\rX\004¢\006\002\n\000R\016\020\017\032\0020\020X\016¢\006\002\n\000¨\006\036"}, d2 = {"Lokhttp3/internal/platform/Platform$Companion;", "", "()V", "INFO", "", "WARN", "isAndroid", "", "()Z", "isBouncyCastlePreferred", "isConscryptPreferred", "isOpenJSSEPreferred", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "platform", "Lokhttp3/internal/platform/Platform;", "alpnProtocolNames", "", "", "protocols", "Lokhttp3/Protocol;", "concatLengthPrefixed", "", "findAndroidPlatform", "findJvmPlatform", "findPlatform", "get", "resetForTests", "", "okhttp"}) public static final class Companion {
/*     */     @JvmStatic
/*     */     @NotNull
/* 186 */     public final Platform get() { return Platform.platform; }
/*     */      private Companion() {}
/*     */     public final void resetForTests(@NotNull Platform platform) {
/* 189 */       Intrinsics.checkNotNullParameter(platform, "platform"); Platform.platform = platform;
/*     */     }
/*     */     @NotNull
/*     */     public final List<String> alpnProtocolNames(@NotNull List protocols) {
/* 193 */       Intrinsics.checkNotNullParameter(protocols, "protocols"); Iterable $this$filter$iv = protocols; int $i$f$filter = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 287 */       Iterable iterable1 = $this$filter$iv; Collection<Object> collection = new ArrayList(); int $i$f$filterTo = 0;
/* 288 */       for (Object element$iv$iv : iterable1) { Protocol it = (Protocol)element$iv$iv; int $i$a$-filter-Platform$Companion$alpnProtocolNames$1 = 0; if ((it != Protocol.HTTP_1_0))
/* 289 */           collection.add(element$iv$iv);  }  Iterable $this$map$iv = collection; int $i$f$map = 0;
/* 290 */       Iterable $this$filterTo$iv$iv = $this$map$iv; Collection<String> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)); int $i$f$mapTo = 0;
/* 291 */       for (Object item$iv$iv : $this$filterTo$iv$iv) {
/* 292 */         Protocol protocol = (Protocol)item$iv$iv; Collection<String> collection1 = destination$iv$iv; int $i$a$-map-Platform$Companion$alpnProtocolNames$2 = 0; String str = protocol.toString(); collection1.add(str);
/* 293 */       }  return (List<String>)destination$iv$iv;
/*     */     }
/*     */     
/*     */     public final boolean isAndroid() {
/*     */       return Intrinsics.areEqual("Dalvik", System.getProperty("java.vm.name"));
/*     */     }
/*     */     
/*     */     private final boolean isConscryptPreferred() {
/*     */       Intrinsics.checkNotNullExpressionValue(Security.getProviders()[0], "Security.getProviders()[0]");
/*     */       String preferredProvider = Security.getProviders()[0].getName();
/*     */       return Intrinsics.areEqual("Conscrypt", preferredProvider);
/*     */     }
/*     */     
/*     */     private final boolean isOpenJSSEPreferred() {
/*     */       Intrinsics.checkNotNullExpressionValue(Security.getProviders()[0], "Security.getProviders()[0]");
/*     */       String preferredProvider = Security.getProviders()[0].getName();
/*     */       return Intrinsics.areEqual("OpenJSSE", preferredProvider);
/*     */     }
/*     */     
/*     */     private final boolean isBouncyCastlePreferred() {
/*     */       Intrinsics.checkNotNullExpressionValue(Security.getProviders()[0], "Security.getProviders()[0]");
/*     */       String preferredProvider = Security.getProviders()[0].getName();
/*     */       return Intrinsics.areEqual("BC", preferredProvider);
/*     */     }
/*     */     
/*     */     private final Platform findPlatform() {
/*     */       return isAndroid() ? findAndroidPlatform() : findJvmPlatform();
/*     */     }
/*     */     
/*     */     private final Platform findAndroidPlatform() {
/*     */       AndroidLog.INSTANCE.enable();
/*     */       if (Android10Platform.Companion.buildIfSupported() == null) {
/*     */         Android10Platform.Companion.buildIfSupported();
/*     */         Intrinsics.checkNotNull(AndroidPlatform.Companion.buildIfSupported());
/*     */       } 
/*     */       return AndroidPlatform.Companion.buildIfSupported();
/*     */     }
/*     */     
/*     */     private final Platform findJvmPlatform() {
/*     */       if (isConscryptPreferred()) {
/*     */         ConscryptPlatform conscrypt = ConscryptPlatform.Companion.buildIfSupported();
/*     */         if (conscrypt != null)
/*     */           return conscrypt; 
/*     */       } 
/*     */       if (isBouncyCastlePreferred()) {
/*     */         BouncyCastlePlatform bc = BouncyCastlePlatform.Companion.buildIfSupported();
/*     */         if (bc != null)
/*     */           return bc; 
/*     */       } 
/*     */       if (isOpenJSSEPreferred()) {
/*     */         OpenJSSEPlatform openJSSE = OpenJSSEPlatform.Companion.buildIfSupported();
/*     */         if (openJSSE != null)
/*     */           return openJSSE; 
/*     */       } 
/*     */       Jdk9Platform jdk9 = Jdk9Platform.Companion.buildIfSupported();
/*     */       if (jdk9 != null)
/*     */         return jdk9; 
/*     */       Platform jdkWithJettyBoot = Jdk8WithJettyBootPlatform.Companion.buildIfSupported();
/*     */       if (jdkWithJettyBoot != null)
/*     */         return jdkWithJettyBoot; 
/*     */       return new Platform();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final byte[] concatLengthPrefixed(@NotNull List<? extends Protocol> protocols) {
/*     */       Intrinsics.checkNotNullParameter(protocols, "protocols");
/*     */       Buffer result = new Buffer();
/*     */       for (String protocol : alpnProtocolNames(protocols)) {
/*     */         result.writeByte(protocol.length());
/*     */         result.writeUtf8(protocol);
/*     */       } 
/*     */       return result.readByteArray();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/Platform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */