/*    */ package okhttp3.internal.platform;
/*    */ 
/*    */ import java.security.KeyStore;
/*    */ import java.security.Provider;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import javax.net.ssl.SSLContext;
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.TrustManager;
/*    */ import javax.net.ssl.TrustManagerFactory;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.Protocol;
/*    */ import org.bouncycastle.jsse.BCSSLParameters;
/*    */ import org.bouncycastle.jsse.BCSSLSocket;
/*    */ import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000J\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\030\000 \0272\0020\001:\001\027B\007\b\002¢\006\002\020\002J-\020\005\032\0020\0062\006\020\007\032\0020\b2\b\020\t\032\004\030\0010\n2\021\020\013\032\r\022\t\022\0070\r¢\006\002\b\0160\fH\026J\022\020\017\032\004\030\0010\n2\006\020\007\032\0020\bH\026J\b\020\020\032\0020\021H\026J\b\020\022\032\0020\023H\026J\022\020\024\032\004\030\0010\0232\006\020\025\032\0020\026H\026R\016\020\003\032\0020\004X\004¢\006\002\n\000¨\006\030"}, d2 = {"Lokhttp3/internal/platform/BouncyCastlePlatform;", "Lokhttp3/internal/platform/Platform;", "()V", "provider", "Ljava/security/Provider;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "platformTrustManager", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"})
/*    */ public final class BouncyCastlePlatform
/*    */   extends Platform
/*    */ {
/*    */   static {
/*    */     boolean bool;
/*    */   }
/*    */   
/*    */   private static final boolean isSupported;
/* 35 */   private final Provider provider = (Provider)new BouncyCastleJsseProvider();
/*    */   @NotNull
/*    */   public SSLContext newSSLContext() {
/* 38 */     Intrinsics.checkNotNullExpressionValue(SSLContext.getInstance("TLS", this.provider), "SSLContext.getInstance(\"TLS\", provider)"); return SSLContext.getInstance("TLS", this.provider);
/*    */   } @NotNull
/*    */   public X509TrustManager platformTrustManager() {
/* 41 */     TrustManagerFactory factory = TrustManagerFactory.getInstance(
/* 42 */         "PKIX", "BCJSSE");
/* 43 */     factory.init((KeyStore)null);
/* 44 */     Intrinsics.checkNotNullExpressionValue(factory, "factory"); Intrinsics.checkNotNull(factory.getTrustManagers()); TrustManager[] trustManagers = factory.getTrustManagers();
/* 45 */     boolean bool1 = (trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-BouncyCastlePlatform$platformTrustManager$1 = 0;
/* 46 */       TrustManager[] arrayOfTrustManager = trustManagers; boolean bool = false; Intrinsics.checkNotNullExpressionValue(Arrays.toString((Object[])arrayOfTrustManager), "java.util.Arrays.toString(this)"); String str = "Unexpected default trust managers: " + Arrays.toString((Object[])arrayOfTrustManager); throw (Throwable)new IllegalStateException(str.toString()); }
/*    */     
/* 48 */     if (trustManagers[0] == null) throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");  return (X509TrustManager)trustManagers[0];
/*    */   }
/*    */   @Nullable
/*    */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/* 52 */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); throw (Throwable)new UnsupportedOperationException(
/* 53 */         "clientBuilder.sslSocketFactory(SSLSocketFactory) not supported with BouncyCastle");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
/* 60 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols"); if (sslSocket instanceof BCSSLSocket) {
/* 61 */       BCSSLParameters sslParameters = ((BCSSLSocket)sslSocket).getParameters();
/*    */ 
/*    */       
/* 64 */       List<String> names = Platform.Companion.alpnProtocolNames(protocols);
/* 65 */       Intrinsics.checkNotNullExpressionValue(sslParameters, "sslParameters"); Collection<String> $this$toTypedArray$iv = names; int $i$f$toTypedArray = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 98 */       Collection<String> thisCollection$iv = $this$toTypedArray$iv;
/* 99 */       if (thisCollection$iv.toArray(new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  sslParameters.setApplicationProtocols(thisCollection$iv.toArray(new String[0]));
/*    */       ((BCSSLSocket)sslSocket).setParameters(sslParameters);
/*    */     } else {
/*    */       super.configureTlsExtensions(sslSocket, hostname, (List)protocols);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/*    */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
/*    */     if (sslSocket instanceof BCSSLSocket) {
/*    */       String str = ((BCSSLSocket)sslSocket).getApplicationProtocol();
/*    */       if (str == null) {
/*    */       
/*    */       } else {
/*    */         String str1;
/*    */         switch (str1.hashCode()) {
/*    */           case 0:
/*    */             if (str1.equals(""));
/*    */             break;
/*    */         } 
/*    */       } 
/*    */     } else {
/*    */       return super.getSelectedProtocol(sslSocket);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\006\032\004\030\0010\007R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\003\020\005¨\006\b"}, d2 = {"Lokhttp3/internal/platform/BouncyCastlePlatform$Companion;", "", "()V", "isSupported", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/BouncyCastlePlatform;", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */     
/*    */     public final boolean isSupported() {
/*    */       return BouncyCastlePlatform.isSupported;
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public final BouncyCastlePlatform buildIfSupported() {
/*    */       return isSupported() ? new BouncyCastlePlatform(null) : null;
/*    */     }
/*    */   }
/*    */   
/*    */   static {
/*    */     try {
/*    */       Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider", false, Companion.getClass().getClassLoader());
/*    */       bool = true;
/*    */     } catch (ClassNotFoundException _) {
/*    */       bool = false;
/*    */     } 
/*    */     isSupported = bool;
/*    */   }
/*    */   
/*    */   private BouncyCastlePlatform() {}
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/BouncyCastlePlatform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */