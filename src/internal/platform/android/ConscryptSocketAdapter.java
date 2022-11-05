/*    */ package okhttp3.internal.platform.android;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.internal.platform.ConscryptPlatform;
/*    */ import okhttp3.internal.platform.Platform;
/*    */ import org.conscrypt.Conscrypt;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\030\000 \0202\0020\001:\001\020B\005¢\006\002\020\002J(\020\003\032\0020\0042\006\020\005\032\0020\0062\b\020\007\032\004\030\0010\b2\f\020\t\032\b\022\004\022\0020\0130\nH\026J\022\020\f\032\004\030\0010\b2\006\020\005\032\0020\006H\026J\b\020\r\032\0020\016H\026J\020\020\017\032\0020\0162\006\020\005\032\0020\006H\026¨\006\021"}, d2 = {"Lokhttp3/internal/platform/android/ConscryptSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Companion", "okhttp"})
/*    */ public final class ConscryptSocketAdapter
/*    */   implements SocketAdapter
/*    */ {
/*    */   @NotNull
/*    */   private static final DeferredSocketAdapter.Factory factory;
/*    */   
/*    */   @Nullable
/*    */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/* 28 */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory); } public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) { Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory); } public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
/* 29 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); return Conscrypt.isConscrypt(sslSocket);
/*    */   } public boolean isSupported() {
/* 31 */     return ConscryptPlatform.Companion.isSupported();
/*    */   } @Nullable
/*    */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/* 34 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); return 
/* 35 */       matchesSocket(sslSocket) ? Conscrypt.getApplicationProtocol(sslSocket) : 
/* 36 */       null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
/* 45 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols"); if (matchesSocket(sslSocket)) {
/*    */       
/* 47 */       Conscrypt.setUseSessionTickets(sslSocket, true);
/*    */ 
/*    */       
/* 50 */       List names = Platform.Companion.alpnProtocolNames(protocols);
/* 51 */       Collection $this$toTypedArray$iv = names; int $i$f$toTypedArray = 0;
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
/* 65 */       Collection thisCollection$iv = $this$toTypedArray$iv;
/* 66 */       if (thisCollection$iv.toArray((Object[])new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  Conscrypt.setApplicationProtocols(sslSocket, (String[])thisCollection$iv.toArray((Object[])new String[0]));
/*    */     } 
/*    */   }
/*    */   
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   static {
/*    */     factory = new ConscryptSocketAdapter$Companion$factory$1();
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/internal/platform/android/ConscryptSocketAdapter$Companion;", "", "()V", "factory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */     
/*    */     @NotNull
/*    */     public final DeferredSocketAdapter.Factory getFactory() {
/*    */       return ConscryptSocketAdapter.factory;
/*    */     }
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\035\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000*\001\000\b\n\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H\026J\020\020\006\032\0020\0072\006\020\004\032\0020\005H\026¨\006\b"}, d2 = {"okhttp3/internal/platform/android/ConscryptSocketAdapter$Companion$factory$1", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "create", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "matchesSocket", "", "okhttp"})
/*    */   public static final class ConscryptSocketAdapter$Companion$factory$1 implements DeferredSocketAdapter.Factory {
/*    */     public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
/*    */       Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
/*    */       return (ConscryptPlatform.Companion.isSupported() && Conscrypt.isConscrypt(sslSocket));
/*    */     }
/*    */     
/*    */     @NotNull
/*    */     public SocketAdapter create(@NotNull SSLSocket sslSocket) {
/*    */       Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
/*    */       return new ConscryptSocketAdapter();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/ConscryptSocketAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */