/*    */ package okhttp3.internal.platform.android;
/*    */ 
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.internal.Util;
/*    */ import okhttp3.internal.platform.Platform;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000,\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\030\000 \0162\0020\001:\001\016B1\022\016\020\002\032\n\022\006\b\000\022\0020\0040\003\022\016\020\005\032\n\022\006\b\000\022\0020\0060\003\022\n\020\007\032\006\022\002\b\0030\003¢\006\002\020\bJ\020\020\t\032\0020\n2\006\020\013\032\0020\006H\026J\022\020\f\032\004\030\0010\r2\006\020\013\032\0020\006H\026R\022\020\007\032\006\022\002\b\0030\003X\004¢\006\002\n\000R\026\020\005\032\n\022\006\b\000\022\0020\0060\003X\004¢\006\002\n\000¨\006\017"}, d2 = {"Lokhttp3/internal/platform/android/StandardAndroidSocketAdapter;", "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "sslSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "sslSocketFactoryClass", "Ljavax/net/ssl/SSLSocketFactory;", "paramClass", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)V", "matchesSocketFactory", "", "sslSocketFactory", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "Companion", "okhttp"})
/*    */ public final class StandardAndroidSocketAdapter
/*    */   extends AndroidSocketAdapter
/*    */ {
/*    */   private final Class<? super SSLSocketFactory> sslSocketFactoryClass;
/*    */   private final Class<?> paramClass;
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   public StandardAndroidSocketAdapter(@NotNull Class<? super SSLSocket> sslSocketClass, @NotNull Class<? super SSLSocketFactory> sslSocketFactoryClass, @NotNull Class<?> paramClass) {
/* 34 */     super(sslSocketClass); this.sslSocketFactoryClass = sslSocketFactoryClass; this.paramClass = paramClass;
/*    */   }
/*    */   public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
/* 37 */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); return this.sslSocketFactoryClass.isInstance(sslSocketFactory);
/*    */   } @Nullable
/*    */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/* 40 */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory"); Object context = 
/* 41 */       Util.readFieldOrNull(sslSocketFactory, this.paramClass, 
/* 42 */         "sslParameters");
/*    */     
/* 44 */     Intrinsics.checkNotNull(context); X509TrustManager x509TrustManager = (X509TrustManager)Util.readFieldOrNull(context, X509TrustManager.class, "x509TrustManager");
/* 45 */     if (x509TrustManager == null); return (X509TrustManager)Util.readFieldOrNull(context, 
/* 46 */         X509TrustManager.class, 
/* 47 */         "trustManager");
/*    */   }
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\022\020\003\032\004\030\0010\0042\b\b\002\020\005\032\0020\006¨\006\007"}, d2 = {"Lokhttp3/internal/platform/android/StandardAndroidSocketAdapter$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/android/SocketAdapter;", "packageName", "", "okhttp"})
/*    */   public static final class Companion { @Nullable
/*    */     public final SocketAdapter buildIfSupported(@NotNull String packageName) {
/*    */       SocketAdapter socketAdapter;
/* 53 */       Intrinsics.checkNotNullParameter(packageName, "packageName"); try {
/* 54 */         if (Class.forName(packageName + ".OpenSSLSocketImpl") == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocket>");  Class<?> sslSocketClass = Class.forName(packageName + ".OpenSSLSocketImpl");
/*    */         
/* 56 */         if (Class.forName(packageName + ".OpenSSLSocketFactoryImpl") == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocketFactory>");  Class<?> sslSocketFactoryClass = Class.forName(packageName + ".OpenSSLSocketFactoryImpl");
/* 57 */         Class<?> paramsClass = Class.forName(packageName + ".SSLParametersImpl");
/*    */         
/* 59 */         Intrinsics.checkNotNullExpressionValue(paramsClass, "paramsClass"); socketAdapter = new StandardAndroidSocketAdapter((Class)sslSocketClass, (Class)sslSocketFactoryClass, paramsClass);
/* 60 */       } catch (Exception e) {
/* 61 */         Throwable throwable = e; String str = "unable to load android socket classes"; byte b = 5; Platform.Companion.get().log(str, b, throwable);
/* 62 */         socketAdapter = null;
/*    */       } 
/*    */       return socketAdapter;
/*    */     }
/*    */     
/*    */     private Companion() {} }
/*    */ 
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/StandardAndroidSocketAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */