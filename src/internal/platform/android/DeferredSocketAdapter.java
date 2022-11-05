/*    */ package okhttp3.internal.platform.android;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.Protocol;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\003\030\0002\0020\001:\001\024B\r\022\006\020\002\032\0020\003¢\006\002\020\004J(\020\006\032\0020\0072\006\020\b\032\0020\t2\b\020\n\032\004\030\0010\0132\f\020\f\032\b\022\004\022\0020\0160\rH\026J\022\020\017\032\004\030\0010\0012\006\020\b\032\0020\tH\002J\022\020\020\032\004\030\0010\0132\006\020\b\032\0020\tH\026J\b\020\021\032\0020\022H\026J\020\020\023\032\0020\0222\006\020\b\032\0020\tH\026R\020\020\005\032\004\030\0010\001X\016¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\025"}, d2 = {"Lokhttp3/internal/platform/android/DeferredSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "socketAdapterFactory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "(Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;)V", "delegate", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getDelegate", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Factory", "okhttp"})
/*    */ public final class DeferredSocketAdapter
/*    */   implements SocketAdapter
/*    */ {
/*    */   private SocketAdapter delegate;
/*    */   private final Factory socketAdapterFactory;
/*    */   
/*    */   public DeferredSocketAdapter(@NotNull Factory socketAdapterFactory) {
/* 29 */     this.socketAdapterFactory = socketAdapterFactory; } @Nullable public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) { Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory); } public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) { Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory); }
/*    */ 
/*    */   
/*    */   public boolean isSupported() {
/* 33 */     return true;
/*    */   }
/*    */   
/*    */   public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
/* 37 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); return this.socketAdapterFactory.matchesSocket(sslSocket);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
/* 44 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols"); if (getDelegate(sslSocket) != null) { getDelegate(sslSocket).configureTlsExtensions(sslSocket, hostname, protocols); } else { getDelegate(sslSocket); }
/*    */   
/*    */   } @Nullable
/*    */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/* 48 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); getDelegate(sslSocket); return (getDelegate(sslSocket) != null) ? getDelegate(sslSocket).getSelectedProtocol(sslSocket) : null;
/*    */   }
/*    */   
/*    */   private final synchronized SocketAdapter getDelegate(SSLSocket sslSocket) {
/* 52 */     if (this.delegate == null && this.socketAdapterFactory.matchesSocket(sslSocket)) {
/* 53 */       this.delegate = this.socketAdapterFactory.create(sslSocket);
/*    */     }
/*    */     
/* 56 */     return this.delegate;
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000\bf\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H&J\020\020\006\032\0020\0072\006\020\004\032\0020\005H&¨\006\b"}, d2 = {"Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "", "create", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "matchesSocket", "", "okhttp"})
/*    */   public static interface Factory {
/*    */     boolean matchesSocket(@NotNull SSLSocket param1SSLSocket);
/*    */     
/*    */     @NotNull
/*    */     SocketAdapter create(@NotNull SSLSocket param1SSLSocket);
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/DeferredSocketAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */