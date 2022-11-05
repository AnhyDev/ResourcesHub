/*    */ package okhttp3.internal.platform.android;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.net.ssl.SSLSockets;
/*    */ import android.os.Build;
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import javax.net.ssl.SSLParameters;
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.internal.SuppressSignatureCheck;
/*    */ import okhttp3.internal.platform.Platform;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\b\007\030\000 \0202\0020\001:\001\020B\005¢\006\002\020\002J(\020\003\032\0020\0042\006\020\005\032\0020\0062\b\020\007\032\004\030\0010\b2\f\020\t\032\b\022\004\022\0020\0130\nH\027J\022\020\f\032\004\030\0010\b2\006\020\005\032\0020\006H\027J\b\020\r\032\0020\016H\026J\020\020\017\032\0020\0162\006\020\005\032\0020\006H\026¨\006\021"}, d2 = {"Lokhttp3/internal/platform/android/Android10SocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Companion", "okhttp"})
/*    */ @SuppressLint({"NewApi"})
/*    */ @SuppressSignatureCheck
/*    */ public final class Android10SocketAdapter
/*    */   implements SocketAdapter
/*    */ {
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   @Nullable
/*    */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/* 36 */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory); } public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) { Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory); } public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
/* 37 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); return SSLSockets.isSupportedSocket(sslSocket);
/*    */   } public boolean isSupported() {
/* 39 */     return Companion.isSupported();
/*    */   } @SuppressLint({"NewApi"})
/*    */   @Nullable
/*    */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/* 43 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); String str = sslSocket.getApplicationProtocol(); if (str == null) {  } else { String str1; switch (str1.hashCode()) { case 0:
/* 44 */           if (str1.equals("")); break; }
/* 45 */        return str; }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SuppressLint({"NewApi"})
/*    */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
/* 54 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols"); try {
/* 55 */       SSLSockets.setUseSessionTickets(sslSocket, true);
/*    */       
/* 57 */       SSLParameters sslParameters = sslSocket.getSSLParameters();
/*    */ 
/*    */       
/* 60 */       Intrinsics.checkNotNullExpressionValue(sslParameters, "sslParameters"); Collection $this$toTypedArray$iv = Platform.Companion.alpnProtocolNames(protocols); int $i$f$toTypedArray = 0;
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
/* 78 */       Collection thisCollection$iv = $this$toTypedArray$iv;
/* 79 */       if (thisCollection$iv.toArray((Object[])new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  sslParameters.setApplicationProtocols((String[])thisCollection$iv.toArray((Object[])new String[0]));
/*    */       sslSocket.setSSLParameters(sslParameters);
/*    */     } catch (IllegalArgumentException iae) {
/*    */       throw (Throwable)new IOException("Android internal error", (Throwable)iae);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\004\030\0010\004J\006\020\005\032\0020\006¨\006\007"}, d2 = {"Lokhttp3/internal/platform/android/Android10SocketAdapter$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/android/SocketAdapter;", "isSupported", "", "okhttp"})
/*    */   @SuppressSignatureCheck
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */     
/*    */     @Nullable
/*    */     public final SocketAdapter buildIfSupported() {
/*    */       return isSupported() ? new Android10SocketAdapter() : null;
/*    */     }
/*    */     
/*    */     public final boolean isSupported() {
/*    */       return (Platform.Companion.isAndroid() && Build.VERSION.SDK_INT >= 29);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/Android10SocketAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */