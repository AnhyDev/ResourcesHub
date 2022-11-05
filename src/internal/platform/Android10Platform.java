/*    */ package okhttp3.internal.platform;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.os.Build;
/*    */ import android.security.NetworkSecurityPolicy;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.collections.CollectionsKt;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.internal.SuppressSignatureCheck;
/*    */ import okhttp3.internal.platform.android.Android10SocketAdapter;
/*    */ import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
/*    */ import okhttp3.internal.platform.android.AndroidSocketAdapter;
/*    */ import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
/*    */ import okhttp3.internal.platform.android.ConscryptSocketAdapter;
/*    */ import okhttp3.internal.platform.android.DeferredSocketAdapter;
/*    */ import okhttp3.internal.platform.android.SocketAdapter;
/*    */ import okhttp3.internal.tls.CertificateChainCleaner;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000J\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\002\b\002\b\007\030\000 \0272\0020\001:\001\027B\005¢\006\002\020\002J\020\020\006\032\0020\0072\006\020\b\032\0020\tH\026J(\020\n\032\0020\0132\006\020\f\032\0020\r2\b\020\016\032\004\030\0010\0172\f\020\020\032\b\022\004\022\0020\0210\004H\026J\022\020\022\032\004\030\0010\0172\006\020\f\032\0020\rH\026J\020\020\023\032\0020\0242\006\020\016\032\0020\017H\027J\022\020\b\032\004\030\0010\t2\006\020\025\032\0020\026H\026R\024\020\003\032\b\022\004\022\0020\0050\004X\004¢\006\002\n\000¨\006\030"}, d2 = {"Lokhttp3/internal/platform/Android10Platform;", "Lokhttp3/internal/platform/Platform;", "()V", "socketAdapters", "", "Lokhttp3/internal/platform/android/SocketAdapter;", "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "Lokhttp3/Protocol;", "getSelectedProtocol", "isCleartextTrafficPermitted", "", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"})
/*    */ @SuppressSignatureCheck
/*    */ public final class Android10Platform
/*    */   extends Platform
/*    */ {
/*    */   private final List<SocketAdapter> socketAdapters;
/*    */   private static final boolean isSupported;
/*    */   
/*    */   public Android10Platform() {
/* 37 */     List list1 = CollectionsKt.listOfNotNull((Object[])new SocketAdapter[] {
/* 38 */           Android10SocketAdapter.Companion.buildIfSupported(), 
/* 39 */           (SocketAdapter)new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory()), 
/*    */           
/* 41 */           (SocketAdapter)new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory()), 
/* 42 */           (SocketAdapter)new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory()) });
/* 43 */     Android10Platform android10Platform = this; int $i$f$filter = 0;
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
/* 74 */     List list2 = list1; Collection<Object> destination$iv$iv = new ArrayList(); int $i$f$filterTo = 0;
/* 75 */     for (Object element$iv$iv : list2) { SocketAdapter it = (SocketAdapter)element$iv$iv; int $i$a$-filter-Android10Platform$socketAdapters$1 = 0; if (it.isSupported())
/* 76 */         destination$iv$iv.add(element$iv$iv);  }  List<SocketAdapter> list = (List)destination$iv$iv;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: ldc 'sslSocketFactory'
/*    */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*    */     //   6: aload_0
/*    */     //   7: getfield socketAdapters : Ljava/util/List;
/*    */     //   10: checkcast java/lang/Iterable
/*    */     //   13: astore_2
/*    */     //   14: iconst_0
/*    */     //   15: istore_3
/*    */     //   16: aload_2
/*    */     //   17: astore #4
/*    */     //   19: iconst_0
/*    */     //   20: istore #5
/*    */     //   22: aload #4
/*    */     //   24: invokeinterface iterator : ()Ljava/util/Iterator;
/*    */     //   29: astore #6
/*    */     //   31: aload #6
/*    */     //   33: invokeinterface hasNext : ()Z
/*    */     //   38: ifeq -> 76
/*    */     //   41: aload #6
/*    */     //   43: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   48: astore #7
/*    */     //   50: aload #7
/*    */     //   52: checkcast okhttp3/internal/platform/android/SocketAdapter
/*    */     //   55: astore #8
/*    */     //   57: iconst_0
/*    */     //   58: istore #9
/*    */     //   60: aload #8
/*    */     //   62: aload_1
/*    */     //   63: invokeinterface matchesSocketFactory : (Ljavax/net/ssl/SSLSocketFactory;)Z
/*    */     //   68: ifeq -> 31
/*    */     //   71: aload #7
/*    */     //   73: goto -> 77
/*    */     //   76: aconst_null
/*    */     //   77: checkcast okhttp3/internal/platform/android/SocketAdapter
/*    */     //   80: dup
/*    */     //   81: ifnull -> 93
/*    */     //   84: aload_1
/*    */     //   85: invokeinterface trustManager : (Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;
/*    */     //   90: goto -> 95
/*    */     //   93: pop
/*    */     //   94: aconst_null
/*    */     //   95: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #46	-> 6
/*    */     //   #47	-> 6
/*    */     //   #46	-> 6
/*    */     //   #73	-> 57
/*    */     //   #46	-> 60
/*    */     //   #46	-> 68
/*    */     //   #47	-> 84
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   57	11	8	it	Lokhttp3/internal/platform/android/SocketAdapter;
/*    */     //   60	8	9	$i$a$-find-Android10Platform$trustManager$1	I
/*    */     //   0	96	0	this	Lokhttp3/internal/platform/Android10Platform;
/*    */     //   0	96	1	sslSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
/*    */   }
/*    */   
/*    */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: ldc 'sslSocket'
/*    */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*    */     //   6: aload_3
/*    */     //   7: ldc 'protocols'
/*    */     //   9: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*    */     //   12: aload_0
/*    */     //   13: getfield socketAdapters : Ljava/util/List;
/*    */     //   16: checkcast java/lang/Iterable
/*    */     //   19: astore #4
/*    */     //   21: iconst_0
/*    */     //   22: istore #5
/*    */     //   24: aload #4
/*    */     //   26: astore #6
/*    */     //   28: iconst_0
/*    */     //   29: istore #7
/*    */     //   31: aload #6
/*    */     //   33: invokeinterface iterator : ()Ljava/util/Iterator;
/*    */     //   38: astore #8
/*    */     //   40: aload #8
/*    */     //   42: invokeinterface hasNext : ()Z
/*    */     //   47: ifeq -> 85
/*    */     //   50: aload #8
/*    */     //   52: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   57: astore #9
/*    */     //   59: aload #9
/*    */     //   61: checkcast okhttp3/internal/platform/android/SocketAdapter
/*    */     //   64: astore #10
/*    */     //   66: iconst_0
/*    */     //   67: istore #11
/*    */     //   69: aload #10
/*    */     //   71: aload_1
/*    */     //   72: invokeinterface matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
/*    */     //   77: ifeq -> 40
/*    */     //   80: aload #9
/*    */     //   82: goto -> 86
/*    */     //   85: aconst_null
/*    */     //   86: checkcast okhttp3/internal/platform/android/SocketAdapter
/*    */     //   89: dup
/*    */     //   90: ifnull -> 104
/*    */     //   93: aload_1
/*    */     //   94: aload_2
/*    */     //   95: aload_3
/*    */     //   96: invokeinterface configureTlsExtensions : (Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V
/*    */     //   101: goto -> 105
/*    */     //   104: pop
/*    */     //   105: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #51	-> 12
/*    */     //   #52	-> 12
/*    */     //   #51	-> 12
/*    */     //   #73	-> 66
/*    */     //   #51	-> 69
/*    */     //   #51	-> 77
/*    */     //   #52	-> 93
/*    */     //   #53	-> 105
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   66	11	10	it	Lokhttp3/internal/platform/android/SocketAdapter;
/*    */     //   69	8	11	$i$a$-find-Android10Platform$configureTlsExtensions$1	I
/*    */     //   0	106	0	this	Lokhttp3/internal/platform/Android10Platform;
/*    */     //   0	106	1	sslSocket	Ljavax/net/ssl/SSLSocket;
/*    */     //   0	106	2	hostname	Ljava/lang/String;
/*    */     //   0	106	3	protocols	Ljava/util/List;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: ldc 'sslSocket'
/*    */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*    */     //   6: aload_0
/*    */     //   7: getfield socketAdapters : Ljava/util/List;
/*    */     //   10: checkcast java/lang/Iterable
/*    */     //   13: astore_2
/*    */     //   14: iconst_0
/*    */     //   15: istore_3
/*    */     //   16: aload_2
/*    */     //   17: astore #4
/*    */     //   19: iconst_0
/*    */     //   20: istore #5
/*    */     //   22: aload #4
/*    */     //   24: invokeinterface iterator : ()Ljava/util/Iterator;
/*    */     //   29: astore #6
/*    */     //   31: aload #6
/*    */     //   33: invokeinterface hasNext : ()Z
/*    */     //   38: ifeq -> 76
/*    */     //   41: aload #6
/*    */     //   43: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   48: astore #7
/*    */     //   50: aload #7
/*    */     //   52: checkcast okhttp3/internal/platform/android/SocketAdapter
/*    */     //   55: astore #8
/*    */     //   57: iconst_0
/*    */     //   58: istore #9
/*    */     //   60: aload #8
/*    */     //   62: aload_1
/*    */     //   63: invokeinterface matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
/*    */     //   68: ifeq -> 31
/*    */     //   71: aload #7
/*    */     //   73: goto -> 77
/*    */     //   76: aconst_null
/*    */     //   77: checkcast okhttp3/internal/platform/android/SocketAdapter
/*    */     //   80: dup
/*    */     //   81: ifnull -> 93
/*    */     //   84: aload_1
/*    */     //   85: invokeinterface getSelectedProtocol : (Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;
/*    */     //   90: goto -> 95
/*    */     //   93: pop
/*    */     //   94: aconst_null
/*    */     //   95: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #57	-> 6
/*    */     //   #73	-> 57
/*    */     //   #57	-> 60
/*    */     //   #57	-> 68
/*    */     //   #57	-> 84
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   57	11	8	it	Lokhttp3/internal/platform/android/SocketAdapter;
/*    */     //   60	8	9	$i$a$-find-Android10Platform$getSelectedProtocol$1	I
/*    */     //   0	96	0	this	Lokhttp3/internal/platform/Android10Platform;
/*    */     //   0	96	1	sslSocket	Ljavax/net/ssl/SSLSocket;
/*    */   }
/*    */   
/*    */   @SuppressLint({"NewApi"})
/*    */   public boolean isCleartextTrafficPermitted(@NotNull String hostname) {
/*    */     Intrinsics.checkNotNullParameter(hostname, "hostname");
/*    */     return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public CertificateChainCleaner buildCertificateChainCleaner(@NotNull X509TrustManager trustManager) {
/*    */     Intrinsics.checkNotNullParameter(trustManager, "trustManager");
/*    */     AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager);
/*    */     return (AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager) != null) ? (CertificateChainCleaner)AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager) : super.buildCertificateChainCleaner(trustManager);
/*    */   }
/*    */   
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   static {
/*    */     isSupported = (Platform.Companion.isAndroid() && Build.VERSION.SDK_INT >= 29);
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\006\032\004\030\0010\007R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\003\020\005¨\006\b"}, d2 = {"Lokhttp3/internal/platform/Android10Platform$Companion;", "", "()V", "isSupported", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */     
/*    */     public final boolean isSupported() {
/*    */       return Android10Platform.isSupported;
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public final Platform buildIfSupported() {
/*    */       return isSupported() ? new Android10Platform() : null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/Android10Platform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */