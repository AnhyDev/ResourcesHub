/*     */ package okhttp3.internal.platform;
/*     */ 
/*     */ import android.os.Build;
/*     */ import android.security.NetworkSecurityPolicy;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.security.cert.TrustAnchor;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.SuppressSignatureCheck;
/*     */ import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
/*     */ import okhttp3.internal.platform.android.AndroidSocketAdapter;
/*     */ import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
/*     */ import okhttp3.internal.platform.android.CloseGuard;
/*     */ import okhttp3.internal.platform.android.ConscryptSocketAdapter;
/*     */ import okhttp3.internal.platform.android.DeferredSocketAdapter;
/*     */ import okhttp3.internal.platform.android.SocketAdapter;
/*     */ import okhttp3.internal.platform.android.StandardAndroidSocketAdapter;
/*     */ import okhttp3.internal.tls.CertificateChainCleaner;
/*     */ import okhttp3.internal.tls.TrustRootIndex;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000x\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\000\n\002\b\002\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\003\b\007\030\000 )2\0020\001:\002)*B\005¢\006\002\020\002J\020\020\b\032\0020\t2\006\020\n\032\0020\013H\026J\020\020\f\032\0020\r2\006\020\n\032\0020\013H\026J-\020\016\032\0020\0172\006\020\020\032\0020\0212\b\020\022\032\004\030\0010\0232\021\020\024\032\r\022\t\022\0070\025¢\006\002\b\0260\006H\026J \020\027\032\0020\0172\006\020\030\032\0020\0312\006\020\032\032\0020\0332\006\020\034\032\0020\035H\026J\022\020\036\032\004\030\0010\0232\006\020\020\032\0020\021H\026J\022\020\037\032\004\030\0010 2\006\020!\032\0020\023H\026J\020\020\"\032\0020#2\006\020\022\032\0020\023H\026J\032\020$\032\0020\0172\006\020%\032\0020\0232\b\020&\032\004\030\0010 H\026J\022\020\n\032\004\030\0010\0132\006\020'\032\0020(H\026R\016\020\003\032\0020\004X\004¢\006\002\n\000R\024\020\005\032\b\022\004\022\0020\0070\006X\004¢\006\002\n\000¨\006+"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform;", "Lokhttp3/internal/platform/Platform;", "()V", "closeGuard", "Lokhttp3/internal/platform/android/CloseGuard;", "socketAdapters", "", "Lokhttp3/internal/platform/android/SocketAdapter;", "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "buildTrustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "connectSocket", "socket", "Ljava/net/Socket;", "address", "Ljava/net/InetSocketAddress;", "connectTimeout", "", "getSelectedProtocol", "getStackTraceForCloseable", "", "closer", "isCleartextTrafficPermitted", "", "logCloseableLeak", "message", "stackTrace", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "CustomTrustRootIndex", "okhttp"})
/*     */ @SuppressSignatureCheck
/*     */ public final class AndroidPlatform
/*     */   extends Platform
/*     */ {
/*     */   private final List<SocketAdapter> socketAdapters;
/*     */   private final CloseGuard closeGuard;
/*     */   private static final boolean isSupported;
/*     */   
/*     */   public AndroidPlatform() {
/*  46 */     List list1 = CollectionsKt.listOfNotNull((Object[])new SocketAdapter[] {
/*  47 */           StandardAndroidSocketAdapter.Companion.buildIfSupported$default(StandardAndroidSocketAdapter.Companion, null, 1, null), 
/*  48 */           (SocketAdapter)new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory()), 
/*     */           
/*  50 */           (SocketAdapter)new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory()), 
/*  51 */           (SocketAdapter)new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory()) });
/*  52 */     AndroidPlatform androidPlatform = this; int $i$f$filter = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     List list2 = list1; Collection<Object> destination$iv$iv = new ArrayList(); int $i$f$filterTo = 0;
/* 166 */     for (Object element$iv$iv : list2) { SocketAdapter it = (SocketAdapter)element$iv$iv; int $i$a$-filter-AndroidPlatform$socketAdapters$1 = 0; if (it.isSupported())
/* 167 */         destination$iv$iv.add(element$iv$iv);  }  List<SocketAdapter> list = (List)destination$iv$iv;
/*     */     this.closeGuard = CloseGuard.Companion.get();
/*     */   }
/*     */   
/*     */   public void connectSocket(@NotNull Socket socket, @NotNull InetSocketAddress address, int connectTimeout) throws IOException {
/*     */     Intrinsics.checkNotNullParameter(socket, "socket");
/*     */     Intrinsics.checkNotNullParameter(address, "address");
/*     */     try {
/*     */       socket.connect(address, connectTimeout);
/*     */     } catch (ClassCastException e) {
/*     */       if (Build.VERSION.SDK_INT == 26)
/*     */         throw (Throwable)new IOException("Exception in connect", (Throwable)e); 
/*     */       throw (Throwable)e;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc 'sslSocketFactory'
/*     */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   6: aload_0
/*     */     //   7: getfield socketAdapters : Ljava/util/List;
/*     */     //   10: checkcast java/lang/Iterable
/*     */     //   13: astore_2
/*     */     //   14: iconst_0
/*     */     //   15: istore_3
/*     */     //   16: aload_2
/*     */     //   17: astore #4
/*     */     //   19: iconst_0
/*     */     //   20: istore #5
/*     */     //   22: aload #4
/*     */     //   24: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   29: astore #6
/*     */     //   31: aload #6
/*     */     //   33: invokeinterface hasNext : ()Z
/*     */     //   38: ifeq -> 76
/*     */     //   41: aload #6
/*     */     //   43: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   48: astore #7
/*     */     //   50: aload #7
/*     */     //   52: checkcast okhttp3/internal/platform/android/SocketAdapter
/*     */     //   55: astore #8
/*     */     //   57: iconst_0
/*     */     //   58: istore #9
/*     */     //   60: aload #8
/*     */     //   62: aload_1
/*     */     //   63: invokeinterface matchesSocketFactory : (Ljavax/net/ssl/SSLSocketFactory;)Z
/*     */     //   68: ifeq -> 31
/*     */     //   71: aload #7
/*     */     //   73: goto -> 77
/*     */     //   76: aconst_null
/*     */     //   77: checkcast okhttp3/internal/platform/android/SocketAdapter
/*     */     //   80: dup
/*     */     //   81: ifnull -> 93
/*     */     //   84: aload_1
/*     */     //   85: invokeinterface trustManager : (Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;
/*     */     //   90: goto -> 95
/*     */     //   93: pop
/*     */     //   94: aconst_null
/*     */     //   95: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #76	-> 6
/*     */     //   #77	-> 6
/*     */     //   #76	-> 6
/*     */     //   #164	-> 57
/*     */     //   #76	-> 60
/*     */     //   #76	-> 68
/*     */     //   #77	-> 84
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   57	11	8	it	Lokhttp3/internal/platform/android/SocketAdapter;
/*     */     //   60	8	9	$i$a$-find-AndroidPlatform$trustManager$1	I
/*     */     //   0	96	0	this	Lokhttp3/internal/platform/AndroidPlatform;
/*     */     //   0	96	1	sslSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
/*     */   }
/*     */   
/*     */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc 'sslSocket'
/*     */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   6: aload_3
/*     */     //   7: ldc 'protocols'
/*     */     //   9: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   12: aload_0
/*     */     //   13: getfield socketAdapters : Ljava/util/List;
/*     */     //   16: checkcast java/lang/Iterable
/*     */     //   19: astore #4
/*     */     //   21: iconst_0
/*     */     //   22: istore #5
/*     */     //   24: aload #4
/*     */     //   26: astore #6
/*     */     //   28: iconst_0
/*     */     //   29: istore #7
/*     */     //   31: aload #6
/*     */     //   33: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   38: astore #8
/*     */     //   40: aload #8
/*     */     //   42: invokeinterface hasNext : ()Z
/*     */     //   47: ifeq -> 85
/*     */     //   50: aload #8
/*     */     //   52: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   57: astore #9
/*     */     //   59: aload #9
/*     */     //   61: checkcast okhttp3/internal/platform/android/SocketAdapter
/*     */     //   64: astore #10
/*     */     //   66: iconst_0
/*     */     //   67: istore #11
/*     */     //   69: aload #10
/*     */     //   71: aload_1
/*     */     //   72: invokeinterface matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
/*     */     //   77: ifeq -> 40
/*     */     //   80: aload #9
/*     */     //   82: goto -> 86
/*     */     //   85: aconst_null
/*     */     //   86: checkcast okhttp3/internal/platform/android/SocketAdapter
/*     */     //   89: dup
/*     */     //   90: ifnull -> 104
/*     */     //   93: aload_1
/*     */     //   94: aload_2
/*     */     //   95: aload_3
/*     */     //   96: invokeinterface configureTlsExtensions : (Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V
/*     */     //   101: goto -> 105
/*     */     //   104: pop
/*     */     //   105: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #85	-> 12
/*     */     //   #86	-> 12
/*     */     //   #85	-> 12
/*     */     //   #164	-> 66
/*     */     //   #85	-> 69
/*     */     //   #85	-> 77
/*     */     //   #86	-> 93
/*     */     //   #87	-> 105
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   66	11	10	it	Lokhttp3/internal/platform/android/SocketAdapter;
/*     */     //   69	8	11	$i$a$-find-AndroidPlatform$configureTlsExtensions$1	I
/*     */     //   0	106	0	this	Lokhttp3/internal/platform/AndroidPlatform;
/*     */     //   0	106	1	sslSocket	Ljavax/net/ssl/SSLSocket;
/*     */     //   0	106	2	hostname	Ljava/lang/String;
/*     */     //   0	106	3	protocols	Ljava/util/List;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc 'sslSocket'
/*     */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   6: aload_0
/*     */     //   7: getfield socketAdapters : Ljava/util/List;
/*     */     //   10: checkcast java/lang/Iterable
/*     */     //   13: astore_2
/*     */     //   14: iconst_0
/*     */     //   15: istore_3
/*     */     //   16: aload_2
/*     */     //   17: astore #4
/*     */     //   19: iconst_0
/*     */     //   20: istore #5
/*     */     //   22: aload #4
/*     */     //   24: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   29: astore #6
/*     */     //   31: aload #6
/*     */     //   33: invokeinterface hasNext : ()Z
/*     */     //   38: ifeq -> 76
/*     */     //   41: aload #6
/*     */     //   43: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   48: astore #7
/*     */     //   50: aload #7
/*     */     //   52: checkcast okhttp3/internal/platform/android/SocketAdapter
/*     */     //   55: astore #8
/*     */     //   57: iconst_0
/*     */     //   58: istore #9
/*     */     //   60: aload #8
/*     */     //   62: aload_1
/*     */     //   63: invokeinterface matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
/*     */     //   68: ifeq -> 31
/*     */     //   71: aload #7
/*     */     //   73: goto -> 77
/*     */     //   76: aconst_null
/*     */     //   77: checkcast okhttp3/internal/platform/android/SocketAdapter
/*     */     //   80: dup
/*     */     //   81: ifnull -> 93
/*     */     //   84: aload_1
/*     */     //   85: invokeinterface getSelectedProtocol : (Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;
/*     */     //   90: goto -> 95
/*     */     //   93: pop
/*     */     //   94: aconst_null
/*     */     //   95: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #91	-> 6
/*     */     //   #164	-> 57
/*     */     //   #91	-> 60
/*     */     //   #91	-> 68
/*     */     //   #91	-> 84
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   57	11	8	it	Lokhttp3/internal/platform/android/SocketAdapter;
/*     */     //   60	8	9	$i$a$-find-AndroidPlatform$getSelectedProtocol$1	I
/*     */     //   0	96	0	this	Lokhttp3/internal/platform/AndroidPlatform;
/*     */     //   0	96	1	sslSocket	Ljavax/net/ssl/SSLSocket;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Object getStackTraceForCloseable(@NotNull String closer) {
/*     */     Intrinsics.checkNotNullParameter(closer, "closer");
/*     */     return this.closeGuard.createAndOpen(closer);
/*     */   }
/*     */   
/*     */   public void logCloseableLeak(@NotNull String message, @Nullable Object stackTrace) {
/*     */     Intrinsics.checkNotNullParameter(message, "message");
/*     */     boolean reported = this.closeGuard.warnIfOpen(stackTrace);
/*     */     if (!reported)
/*     */       Platform.log$default(this, message, 5, null, 4, null); 
/*     */   }
/*     */   
/*     */   public boolean isCleartextTrafficPermitted(@NotNull String hostname) {
/*     */     Intrinsics.checkNotNullParameter(hostname, "hostname");
/*     */     Intrinsics.checkNotNullExpressionValue(NetworkSecurityPolicy.getInstance(), "NetworkSecurityPolicy.getInstance()");
/*     */     return (Build.VERSION.SDK_INT >= 24) ? NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname) : ((Build.VERSION.SDK_INT >= 23) ? NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() : true);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public CertificateChainCleaner buildCertificateChainCleaner(@NotNull X509TrustManager trustManager) {
/*     */     Intrinsics.checkNotNullParameter(trustManager, "trustManager");
/*     */     AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager);
/*     */     return (AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager) != null) ? (CertificateChainCleaner)AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager) : super.buildCertificateChainCleaner(trustManager);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public TrustRootIndex buildTrustRootIndex(@NotNull X509TrustManager trustManager) {
/*     */     TrustRootIndex trustRootIndex;
/*     */     Intrinsics.checkNotNullParameter(trustManager, "trustManager");
/*     */     try {
/*     */       Method method = trustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[] { X509Certificate.class });
/*     */       Intrinsics.checkNotNullExpressionValue(method, "method");
/*     */       method.setAccessible(true);
/*     */       trustRootIndex = new CustomTrustRootIndex(trustManager, method);
/*     */     } catch (NoSuchMethodException e) {
/*     */       trustRootIndex = super.buildTrustRootIndex(trustManager);
/*     */     } 
/*     */     return trustRootIndex;
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\020\013\n\000\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\t\020\007\032\0020\003HÂ\003J\t\020\b\032\0020\005HÂ\003J\035\020\t\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\005HÆ\001J\023\020\n\032\0020\0132\b\020\f\032\004\030\0010\rHÖ\003J\022\020\016\032\004\030\0010\0172\006\020\020\032\0020\017H\026J\t\020\021\032\0020\022HÖ\001J\t\020\023\032\0020\024HÖ\001R\016\020\004\032\0020\005X\004¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\025"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform$CustomTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "findByIssuerAndSignatureMethod", "Ljava/lang/reflect/Method;", "(Ljavax/net/ssl/X509TrustManager;Ljava/lang/reflect/Method;)V", "component1", "component2", "copy", "equals", "", "other", "", "findByIssuerAndSignature", "Ljava/security/cert/X509Certificate;", "cert", "hashCode", "", "toString", "", "okhttp"})
/*     */   public static final class CustomTrustRootIndex implements TrustRootIndex {
/*     */     private final X509TrustManager trustManager;
/*     */     private final Method findByIssuerAndSignatureMethod;
/*     */     
/*     */     public boolean equals(@Nullable Object param1Object) {
/*     */       if (this != param1Object) {
/*     */         if (param1Object instanceof CustomTrustRootIndex) {
/*     */           CustomTrustRootIndex customTrustRootIndex = (CustomTrustRootIndex)param1Object;
/*     */           if (Intrinsics.areEqual(this.trustManager, customTrustRootIndex.trustManager) && Intrinsics.areEqual(this.findByIssuerAndSignatureMethod, customTrustRootIndex.findByIssuerAndSignatureMethod))
/*     */             return true; 
/*     */         } 
/*     */       } else {
/*     */         return true;
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*     */       return ((this.trustManager != null) ? this.trustManager.hashCode() : 0) * 31 + ((this.findByIssuerAndSignatureMethod != null) ? this.findByIssuerAndSignatureMethod.hashCode() : 0);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public String toString() {
/*     */       return "CustomTrustRootIndex(trustManager=" + this.trustManager + ", findByIssuerAndSignatureMethod=" + this.findByIssuerAndSignatureMethod + ")";
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final CustomTrustRootIndex copy(@NotNull X509TrustManager trustManager, @NotNull Method findByIssuerAndSignatureMethod) {
/*     */       Intrinsics.checkNotNullParameter(trustManager, "trustManager");
/*     */       Intrinsics.checkNotNullParameter(findByIssuerAndSignatureMethod, "findByIssuerAndSignatureMethod");
/*     */       return new CustomTrustRootIndex(trustManager, findByIssuerAndSignatureMethod);
/*     */     }
/*     */     
/*     */     private final Method component2() {
/*     */       return this.findByIssuerAndSignatureMethod;
/*     */     }
/*     */     
/*     */     private final X509TrustManager component1() {
/*     */       return this.trustManager;
/*     */     }
/*     */     
/*     */     public CustomTrustRootIndex(@NotNull X509TrustManager trustManager, @NotNull Method findByIssuerAndSignatureMethod) {
/*     */       this.trustManager = trustManager;
/*     */       this.findByIssuerAndSignatureMethod = findByIssuerAndSignatureMethod;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public X509Certificate findByIssuerAndSignature(@NotNull X509Certificate cert) {
/*     */       X509Certificate x509Certificate;
/*     */       Intrinsics.checkNotNullParameter(cert, "cert");
/*     */       try {
/*     */         if (this.findByIssuerAndSignatureMethod.invoke(this.trustManager, new Object[] { cert }) == null)
/*     */           throw new NullPointerException("null cannot be cast to non-null type java.security.cert.TrustAnchor"); 
/*     */         TrustAnchor trustAnchor = (TrustAnchor)this.findByIssuerAndSignatureMethod.invoke(this.trustManager, new Object[] { cert });
/*     */         x509Certificate = trustAnchor.getTrustedCert();
/*     */       } catch (IllegalAccessException e) {
/*     */         throw new AssertionError("unable to get issues and signature", (Throwable)e);
/*     */       } catch (InvocationTargetException _) {
/*     */         x509Certificate = null;
/*     */       } 
/*     */       return x509Certificate;
/*     */     }
/*     */   }
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\006\032\004\030\0010\007R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\003\020\005¨\006\b"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform$Companion;", "", "()V", "isSupported", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     public final boolean isSupported() {
/*     */       return AndroidPlatform.isSupported;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public final Platform buildIfSupported() {
/*     */       return isSupported() ? new AndroidPlatform() : null;
/*     */     }
/*     */   }
/*     */   
/*     */   static {
/*     */     boolean bool1 = (Build.VERSION.SDK_INT >= 21) ? true : false;
/*     */     boolean bool2 = false, bool3 = false;
/*     */     if (!bool1) {
/*     */       int $i$a$-check-AndroidPlatform$Companion$isSupported$1 = 0;
/*     */       String str = "Expected Android API level 21+ but was " + Build.VERSION.SDK_INT;
/*     */       throw (Throwable)new IllegalStateException(str.toString());
/*     */     } 
/*     */     isSupported = !Platform.Companion.isAndroid() ? false : (!(Build.VERSION.SDK_INT >= 30));
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/AndroidPlatform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */