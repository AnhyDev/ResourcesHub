/*    */ package okhttp3.internal.platform;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import javax.net.ssl.SSLParameters;
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import kotlin.text.StringsKt;
/*    */ import okhttp3.Protocol;
/*    */ import okhttp3.internal.SuppressSignatureCheck;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000<\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\b\026\030\000 \0222\0020\001:\001\022B\005¢\006\002\020\002J-\020\003\032\0020\0042\006\020\005\032\0020\0062\b\020\007\032\004\030\0010\b2\021\020\t\032\r\022\t\022\0070\013¢\006\002\b\f0\nH\027J\022\020\r\032\004\030\0010\b2\006\020\005\032\0020\006H\027J\022\020\016\032\004\030\0010\0172\006\020\020\032\0020\021H\026¨\006\023"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform;", "Lokhttp3/internal/platform/Platform;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"})
/*    */ public class Jdk9Platform
/*    */   extends Platform
/*    */ {
/*    */   private static final boolean isAvailable;
/*    */   
/*    */   static {
/*    */     boolean bool;
/*    */   }
/*    */   
/*    */   @SuppressSignatureCheck
/*    */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
/* 32 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols"); SSLParameters sslParameters = sslSocket.getSSLParameters();
/*    */     
/* 34 */     List<String> names = Platform.Companion.alpnProtocolNames(protocols);
/*    */     
/* 36 */     Intrinsics.checkNotNullExpressionValue(sslParameters, "sslParameters"); Collection<String> $this$toTypedArray$iv = names; int $i$f$toTypedArray = 0;
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
/* 90 */     Collection<String> thisCollection$iv = $this$toTypedArray$iv;
/* 91 */     if (thisCollection$iv.toArray(new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  sslParameters.setApplicationProtocols(thisCollection$iv.toArray(new String[0]));
/*    */     sslSocket.setSSLParameters(sslParameters);
/*    */   }
/*    */   
/*    */   @SuppressSignatureCheck
/*    */   @Nullable
/*    */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/*    */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
/*    */     try {
/*    */       String str = sslSocket.getApplicationProtocol();
/*    */       if (str == null) {
/*    */       
/*    */       } else {
/*    */         String str1;
/*    */         switch (str1.hashCode()) {
/*    */           case 0:
/*    */             if (str1.equals(""));
/*    */             break;
/*    */         } 
/*    */         return str;
/*    */       } 
/*    */     } catch (UnsupportedOperationException e) {
/*    */       String protocol;
/*    */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/*    */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
/*    */     throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\006\032\004\030\0010\007R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\003\020\005¨\006\b"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform$Companion;", "", "()V", "isAvailable", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Jdk9Platform;", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */     
/*    */     public final boolean isAvailable() {
/*    */       return Jdk9Platform.isAvailable;
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public final Jdk9Platform buildIfSupported() {
/*    */       return isAvailable() ? new Jdk9Platform() : null;
/*    */     }
/*    */   }
/*    */   
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   static {
/*    */     String jdkVersion = System.getProperty("java.specification.version");
/*    */     Integer majorVersion = (jdkVersion != null) ? StringsKt.toIntOrNull(jdkVersion) : null;
/*    */     try {
/*    */       SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
/*    */       bool = true;
/*    */     } catch (NoSuchMethodException nsme) {
/*    */       bool = false;
/*    */     } 
/*    */     isAvailable = (majorVersion != null) ? ((majorVersion.intValue() >= 9)) : bool;
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/Jdk9Platform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */