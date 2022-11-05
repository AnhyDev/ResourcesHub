/*    */ package okhttp3.internal.platform.android;
/*    */ 
/*    */ import android.net.http.X509TrustManagerExtensions;
/*    */ import java.security.cert.Certificate;
/*    */ import java.security.cert.CertificateException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import javax.net.ssl.SSLPeerUnverifiedException;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.internal.SuppressSignatureCheck;
/*    */ import okhttp3.internal.tls.CertificateChainCleaner;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000>\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\000\030\000 \0232\0020\001:\001\023B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J$\020\007\032\b\022\004\022\0020\t0\b2\f\020\n\032\b\022\004\022\0020\t0\b2\006\020\013\032\0020\fH\027J\023\020\r\032\0020\0162\b\020\017\032\004\030\0010\020H\002J\b\020\021\032\0020\022H\026R\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000¨\006\024"}, d2 = {"Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner;", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "x509TrustManagerExtensions", "Landroid/net/http/X509TrustManagerExtensions;", "(Ljavax/net/ssl/X509TrustManager;Landroid/net/http/X509TrustManagerExtensions;)V", "clean", "", "Ljava/security/cert/Certificate;", "chain", "hostname", "", "equals", "", "other", "", "hashCode", "", "Companion", "okhttp"})
/*    */ public final class AndroidCertificateChainCleaner
/*    */   extends CertificateChainCleaner
/*    */ {
/*    */   private final X509TrustManager trustManager;
/*    */   private final X509TrustManagerExtensions x509TrustManagerExtensions;
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   public AndroidCertificateChainCleaner(@NotNull X509TrustManager trustManager, @NotNull X509TrustManagerExtensions x509TrustManagerExtensions) {
/* 36 */     this.trustManager = trustManager; this.x509TrustManagerExtensions = x509TrustManagerExtensions;
/*    */   }
/*    */   
/*    */   @SuppressSignatureCheck
/*    */   @NotNull
/*    */   public List<Certificate> clean(@NotNull List chain, @NotNull String hostname) throws SSLPeerUnverifiedException {
/* 42 */     Intrinsics.checkNotNullParameter(chain, "chain"); Intrinsics.checkNotNullParameter(hostname, "hostname"); Collection $this$toTypedArray$iv = chain; int $i$f$toTypedArray = 0;
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
/* 74 */     Collection thisCollection$iv = $this$toTypedArray$iv;
/* 75 */     if (thisCollection$iv.toArray((Object[])new X509Certificate[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  X509Certificate[] certificates = (X509Certificate[])thisCollection$iv.toArray((Object[])new X509Certificate[0]); try { Intrinsics.checkNotNullExpressionValue(this.x509TrustManagerExtensions.checkServerTrusted(certificates, "RSA", hostname), "x509TrustManagerExtensio…ficates, \"RSA\", hostname)"); return this.x509TrustManagerExtensions.checkServerTrusted(certificates, "RSA", hostname); }
/* 76 */     catch (CertificateException ce) { SSLPeerUnverifiedException sSLPeerUnverifiedException1 = new SSLPeerUnverifiedException(ce.getMessage()); boolean bool1 = false, bool2 = false; SSLPeerUnverifiedException $this$apply = sSLPeerUnverifiedException1; int $i$a$-apply-AndroidCertificateChainCleaner$clean$1 = 0;
/*    */       $this$apply.initCause(ce);
/*    */       throw sSLPeerUnverifiedException1; }
/*    */   
/*    */   }
/*    */   
/*    */   public boolean equals(@Nullable Object other) {
/*    */     return (other instanceof AndroidCertificateChainCleaner && ((AndroidCertificateChainCleaner)other).trustManager == this.trustManager);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/*    */     return System.identityHashCode(this.trustManager);
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\022\020\003\032\004\030\0010\0042\006\020\005\032\0020\006H\007¨\006\007"}, d2 = {"Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */     
/*    */     @SuppressSignatureCheck
/*    */     @Nullable
/*    */     public final AndroidCertificateChainCleaner buildIfSupported(@NotNull X509TrustManager trustManager) {
/*    */       X509TrustManagerExtensions x509TrustManagerExtensions1;
/*    */       Intrinsics.checkNotNullParameter(trustManager, "trustManager");
/*    */       try {
/*    */         x509TrustManagerExtensions1 = new X509TrustManagerExtensions(trustManager);
/*    */       } catch (IllegalArgumentException iae) {
/*    */         x509TrustManagerExtensions1 = null;
/*    */       } 
/*    */       X509TrustManagerExtensions extensions = x509TrustManagerExtensions1;
/*    */       return (extensions != null) ? new AndroidCertificateChainCleaner(trustManager, extensions) : null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/AndroidCertificateChainCleaner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */