/*     */ package okhttp3.internal.platform;
/*     */ 
/*     */ import java.security.KeyStore;
/*     */ import java.security.Provider;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.TrustManagerFactory;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Protocol;
/*     */ import org.conscrypt.Conscrypt;
/*     */ import org.conscrypt.ConscryptHostnameVerifier;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000H\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\030\000 \0302\0020\001:\002\030\031B\007\b\002¢\006\002\020\002J-\020\005\032\0020\0062\006\020\007\032\0020\b2\b\020\t\032\004\030\0010\n2\021\020\013\032\r\022\t\022\0070\r¢\006\002\b\0160\fH\026J\022\020\017\032\004\030\0010\n2\006\020\007\032\0020\bH\026J\b\020\020\032\0020\021H\026J\020\020\022\032\0020\0232\006\020\024\032\0020\025H\026J\b\020\026\032\0020\025H\026J\022\020\024\032\004\030\0010\0252\006\020\027\032\0020\023H\026R\016\020\003\032\0020\004X\004¢\006\002\n\000¨\006\032"}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform;", "Lokhttp3/internal/platform/Platform;", "()V", "provider", "Ljava/security/Provider;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "newSslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "platformTrustManager", "sslSocketFactory", "Companion", "DisabledHostnameVerifier", "okhttp"})
/*     */ public final class ConscryptPlatform
/*     */   extends Platform
/*     */ {
/*     */   private final Provider provider;
/*     */   private static final boolean isSupported;
/*     */   
/*     */   static {
/*     */     boolean bool;
/*     */   }
/*     */   
/*     */   private ConscryptPlatform() {
/*  38 */     Intrinsics.checkNotNullExpressionValue(Conscrypt.newProvider(), "Conscrypt.newProvider()"); this.provider = Conscrypt.newProvider();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public SSLContext newSSLContext() {
/*  44 */     Intrinsics.checkNotNullExpressionValue(SSLContext.getInstance("TLS", this.provider), "SSLContext.getInstance(\"TLS\", provider)"); return SSLContext.getInstance("TLS", this.provider);
/*     */   } @NotNull
/*     */   public X509TrustManager platformTrustManager() {
/*  47 */     TrustManagerFactory trustManagerFactory1 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()); boolean bool2 = false, bool3 = false; TrustManagerFactory $this$apply = trustManagerFactory1; int $i$a$-apply-ConscryptPlatform$platformTrustManager$trustManagers$1 = 0;
/*  48 */     $this$apply.init((KeyStore)null); Intrinsics.checkNotNullExpressionValue(trustManagerFactory1, "TrustManagerFactory.getI…(null as KeyStore?)\n    }"); Intrinsics.checkNotNull(trustManagerFactory1.getTrustManagers());
/*     */     TrustManager[] trustManagers = trustManagerFactory1.getTrustManagers();
/*  50 */     boolean bool1 = (trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager) ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-check-ConscryptPlatform$platformTrustManager$1 = 0;
/*  51 */       TrustManager[] arrayOfTrustManager = trustManagers; boolean bool = false; Intrinsics.checkNotNullExpressionValue(Arrays.toString((Object[])arrayOfTrustManager), "java.util.Arrays.toString(this)"); String str = "Unexpected default trust managers: " + Arrays.toString((Object[])arrayOfTrustManager); throw (Throwable)new IllegalStateException(str.toString()); }
/*     */     
/*  53 */     if (trustManagers[0] == null) throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");  X509TrustManager x509TrustManager = (X509TrustManager)trustManagers[0];
/*     */     
/*  55 */     Conscrypt.setHostnameVerifier(x509TrustManager, DisabledHostnameVerifier.INSTANCE);
/*  56 */     return x509TrustManager;
/*     */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\020\021\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\002\bÀ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J3\020\003\032\0020\0042\020\020\005\032\f\022\006\b\001\022\0020\007\030\0010\0062\b\020\b\032\004\030\0010\t2\b\020\n\032\004\030\0010\013H\026¢\006\002\020\fJ\032\020\003\032\0020\0042\b\020\b\032\004\030\0010\t2\b\020\n\032\004\030\0010\013¨\006\r"}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform$DisabledHostnameVerifier;", "Lorg/conscrypt/ConscryptHostnameVerifier;", "()V", "verify", "", "certs", "", "Ljava/security/cert/X509Certificate;", "hostname", "", "session", "Ljavax/net/ssl/SSLSession;", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;Ljavax/net/ssl/SSLSession;)Z", "okhttp"})
/*     */   public static final class DisabledHostnameVerifier implements ConscryptHostnameVerifier { static {
/*  59 */       DisabledHostnameVerifier disabledHostnameVerifier = new DisabledHostnameVerifier();
/*     */     }
/*     */     public static final DisabledHostnameVerifier INSTANCE;
/*     */     
/*     */     public final boolean verify(@Nullable String hostname, @Nullable SSLSession session) {
/*  64 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean verify(@Nullable X509Certificate[] certs, @Nullable String hostname, @Nullable SSLSession session) {
/*  72 */       return true;
/*     */     } }
/*     */   @Nullable
/*     */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/*  76 */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
/*  83 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols"); if (Conscrypt.isConscrypt(sslSocket)) {
/*     */       
/*  85 */       Conscrypt.setUseSessionTickets(sslSocket, true);
/*     */ 
/*     */       
/*  88 */       List<String> names = Platform.Companion.alpnProtocolNames(protocols);
/*  89 */       Collection<String> $this$toTypedArray$iv = names; int $i$f$toTypedArray = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       Collection<String> thisCollection$iv = $this$toTypedArray$iv;
/* 143 */       if (thisCollection$iv.toArray(new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  Conscrypt.setApplicationProtocols(sslSocket, thisCollection$iv.toArray(new String[0]));
/*     */     } else {
/*     */       super.configureTlsExtensions(sslSocket, hostname, (List)protocols);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/*     */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
/*     */     return Conscrypt.isConscrypt(sslSocket) ? Conscrypt.getApplicationProtocol(sslSocket) : super.getSelectedProtocol(sslSocket);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public SSLSocketFactory newSslSocketFactory(@NotNull X509TrustManager trustManager) {
/*     */     Intrinsics.checkNotNullParameter(trustManager, "trustManager");
/*     */     SSLContext sSLContext1 = newSSLContext();
/*     */     boolean bool1 = false, bool2 = false;
/*     */     SSLContext $this$apply = sSLContext1;
/*     */     int $i$a$-apply-ConscryptPlatform$newSslSocketFactory$1 = 0;
/*     */     $this$apply.init(null, new TrustManager[] { trustManager }, null);
/*     */     Intrinsics.checkNotNullExpressionValue(sSLContext1.getSocketFactory(), "newSSLContext().apply {\n…null)\n    }.socketFactory");
/*     */     return sSLContext1.getSocketFactory();
/*     */   }
/*     */   
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020\b\n\002\b\003\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\"\020\006\032\0020\0042\006\020\007\032\0020\b2\b\b\002\020\t\032\0020\b2\b\b\002\020\n\032\0020\bJ\b\020\013\032\004\030\0010\fR\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\003\020\005¨\006\r"}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform$Companion;", "", "()V", "isSupported", "", "()Z", "atLeastVersion", "major", "", "minor", "patch", "buildIfSupported", "Lokhttp3/internal/platform/ConscryptPlatform;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     public final boolean isSupported() {
/*     */       return ConscryptPlatform.isSupported;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public final ConscryptPlatform buildIfSupported() {
/*     */       return isSupported() ? new ConscryptPlatform(null) : null;
/*     */     }
/*     */     
/*     */     public final boolean atLeastVersion(int major, int minor, int patch) {
/*     */       Conscrypt.Version conscryptVersion = Conscrypt.version();
/*     */       if (conscryptVersion.major() != major)
/*     */         return (conscryptVersion.major() > major); 
/*     */       if (conscryptVersion.minor() != minor)
/*     */         return (conscryptVersion.minor() > minor); 
/*     */       return (conscryptVersion.patch() >= patch);
/*     */     }
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/*     */       Class.forName("org.conscrypt.Conscrypt$Version", false, Companion.getClass().getClassLoader());
/*     */       bool = (Conscrypt.isAvailable() && Companion.atLeastVersion(2, 1, 0)) ? true : false;
/*     */     } catch (NoClassDefFoundError e) {
/*     */       bool = false;
/*     */     } catch (ClassNotFoundException e) {
/*     */       bool = false;
/*     */     } 
/*     */     isSupported = bool;
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/ConscryptPlatform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */