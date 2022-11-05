/*    */ package okhttp3.internal.platform.android;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000<\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\bf\030\0002\0020\001J(\020\002\032\0020\0032\006\020\004\032\0020\0052\b\020\006\032\004\030\0010\0072\f\020\b\032\b\022\004\022\0020\n0\tH&J\022\020\013\032\004\030\0010\0072\006\020\004\032\0020\005H&J\b\020\f\032\0020\rH&J\020\020\016\032\0020\r2\006\020\004\032\0020\005H&J\020\020\017\032\0020\r2\006\020\020\032\0020\021H\026J\022\020\022\032\004\030\0010\0232\006\020\020\032\0020\021H\026¨\006\024"}, d2 = {"Lokhttp3/internal/platform/android/SocketAdapter;", "", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "matchesSocketFactory", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "okhttp"})
/*    */ public interface SocketAdapter {
/*    */   boolean isSupported();
/*    */   
/*    */   @Nullable
/*    */   X509TrustManager trustManager(@NotNull SSLSocketFactory paramSSLSocketFactory);
/*    */   
/*    */   boolean matchesSocket(@NotNull SSLSocket paramSSLSocket);
/*    */   
/*    */   boolean matchesSocketFactory(@NotNull SSLSocketFactory paramSSLSocketFactory);
/*    */   
/*    */   void configureTlsExtensions(@NotNull SSLSocket paramSSLSocket, @Nullable String paramString, @NotNull List<? extends Protocol> paramList);
/*    */   
/*    */   @Nullable
/*    */   String getSelectedProtocol(@NotNull SSLSocket paramSSLSocket);
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3)
/*    */   public static final class DefaultImpls {
/*    */     @Nullable
/*    */     public static X509TrustManager trustManager(@NotNull SocketAdapter $this, @NotNull SSLSocketFactory sslSocketFactory) {
/* 25 */       Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return null;
/*    */     } public static boolean matchesSocketFactory(@NotNull SocketAdapter $this, @NotNull SSLSocketFactory sslSocketFactory) {
/* 27 */       Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/SocketAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */