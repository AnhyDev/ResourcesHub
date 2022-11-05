/*     */ package okhttp3.internal.platform.android;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.platform.AndroidPlatform;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000@\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\b\026\030\000 \0302\0020\001:\001\030B\025\022\016\020\002\032\n\022\006\b\000\022\0020\0040\003¢\006\002\020\005J(\020\f\032\0020\r2\006\020\016\032\0020\0042\b\020\017\032\004\030\0010\0202\f\020\021\032\b\022\004\022\0020\0230\022H\026J\022\020\024\032\004\030\0010\0202\006\020\016\032\0020\004H\026J\b\020\025\032\0020\026H\026J\020\020\027\032\0020\0262\006\020\016\032\0020\004H\026R\026\020\006\032\n \b*\004\030\0010\0070\007X\004¢\006\002\n\000R\026\020\t\032\n \b*\004\030\0010\0070\007X\004¢\006\002\n\000R\026\020\n\032\n \b*\004\030\0010\0070\007X\004¢\006\002\n\000R\016\020\013\032\0020\007X\004¢\006\002\n\000R\026\020\002\032\n\022\006\b\000\022\0020\0040\003X\004¢\006\002\n\000¨\006\031"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "(Ljava/lang/Class;)V", "getAlpnSelectedProtocol", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "setAlpnProtocols", "setHostname", "setUseSessionTickets", "configureTlsExtensions", "", "sslSocket", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Companion", "okhttp"})
/*     */ public class AndroidSocketAdapter implements SocketAdapter {
/*     */   private final Method setUseSessionTickets;
/*     */   private final Method setHostname;
/*     */   private final Method getAlpnSelectedProtocol;
/*     */   private final Method setAlpnProtocols;
/*     */   private final Class<? super SSLSocket> sslSocketClass;
/*     */   @NotNull
/*     */   private static final DeferredSocketAdapter.Factory playProviderFactory;
/*     */   
/*     */   @Nullable
/*     */   public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
/*     */     Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
/*     */     return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory);
/*     */   }
/*     */   
/*  33 */   public AndroidSocketAdapter(@NotNull Class<? super SSLSocket> sslSocketClass) { this.sslSocketClass = sslSocketClass;
/*     */     
/*  35 */     Intrinsics.checkNotNullExpressionValue(this.sslSocketClass.getDeclaredMethod("setUseSessionTickets", new Class[] { boolean.class }), "sslSocketClass.getDeclar…:class.javaPrimitiveType)"); this.setUseSessionTickets = this.sslSocketClass.getDeclaredMethod("setUseSessionTickets", new Class[] { boolean.class });
/*  36 */     this.setHostname = this.sslSocketClass.getMethod("setHostname", new Class[] { String.class });
/*  37 */     this.getAlpnSelectedProtocol = this.sslSocketClass.getMethod("getAlpnSelectedProtocol", new Class[0]);
/*     */     
/*  39 */     this.setAlpnProtocols = this.sslSocketClass.getMethod("setAlpnProtocols", new Class[] { byte[].class }); }
/*     */   
/*  41 */   public boolean isSupported() { return AndroidPlatform.Companion.isSupported(); }
/*     */   public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) { Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
/*  43 */     return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory); } public boolean matchesSocket(@NotNull SSLSocket sslSocket) { Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); return this.sslSocketClass.isInstance(sslSocket); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List protocols) {
/*  51 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols"); if (matchesSocket(sslSocket)) {
/*     */       
/*     */       try {
/*  54 */         this.setUseSessionTickets.invoke(sslSocket, new Object[] { Boolean.valueOf(true) });
/*     */         
/*  56 */         if (hostname != null)
/*     */         {
/*  58 */           this.setHostname.invoke(sslSocket, new Object[] { hostname });
/*     */         }
/*     */ 
/*     */         
/*  62 */         this.setAlpnProtocols.invoke(
/*  63 */             sslSocket, new Object[] {
/*  64 */               Platform.Companion.concatLengthPrefixed(protocols)
/*     */             });
/*  66 */       } catch (IllegalAccessException e) {
/*  67 */         throw new AssertionError(e);
/*  68 */       } catch (InvocationTargetException e) {
/*  69 */         throw new AssertionError(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc 'sslSocket'
/*     */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   6: aload_0
/*     */     //   7: aload_1
/*     */     //   8: invokevirtual matchesSocket : (Ljavax/net/ssl/SSLSocket;)Z
/*     */     //   11: ifne -> 16
/*     */     //   14: aconst_null
/*     */     //   15: areturn
/*     */     //   16: nop
/*     */     //   17: aload_0
/*     */     //   18: getfield getAlpnSelectedProtocol : Ljava/lang/reflect/Method;
/*     */     //   21: aload_1
/*     */     //   22: iconst_0
/*     */     //   23: anewarray java/lang/Object
/*     */     //   26: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   29: checkcast [B
/*     */     //   32: astore_2
/*     */     //   33: aload_2
/*     */     //   34: ifnull -> 62
/*     */     //   37: getstatic java/nio/charset/StandardCharsets.UTF_8 : Ljava/nio/charset/Charset;
/*     */     //   40: dup
/*     */     //   41: ldc 'StandardCharsets.UTF_8'
/*     */     //   43: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   46: astore_3
/*     */     //   47: iconst_0
/*     */     //   48: istore #4
/*     */     //   50: new java/lang/String
/*     */     //   53: dup
/*     */     //   54: aload_2
/*     */     //   55: aload_3
/*     */     //   56: invokespecial <init> : ([BLjava/nio/charset/Charset;)V
/*     */     //   59: goto -> 63
/*     */     //   62: aconst_null
/*     */     //   63: astore_2
/*     */     //   64: goto -> 123
/*     */     //   67: astore_3
/*     */     //   68: nop
/*     */     //   69: aload_3
/*     */     //   70: invokevirtual getMessage : ()Ljava/lang/String;
/*     */     //   73: ldc 'ssl == null'
/*     */     //   75: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   78: ifeq -> 85
/*     */     //   81: aconst_null
/*     */     //   82: goto -> 90
/*     */     //   85: aload_3
/*     */     //   86: checkcast java/lang/Throwable
/*     */     //   89: athrow
/*     */     //   90: checkcast java/lang/String
/*     */     //   93: astore_2
/*     */     //   94: goto -> 123
/*     */     //   97: astore_3
/*     */     //   98: new java/lang/AssertionError
/*     */     //   101: dup
/*     */     //   102: aload_3
/*     */     //   103: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   106: checkcast java/lang/Throwable
/*     */     //   109: athrow
/*     */     //   110: astore_3
/*     */     //   111: new java/lang/AssertionError
/*     */     //   114: dup
/*     */     //   115: aload_3
/*     */     //   116: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   119: checkcast java/lang/Throwable
/*     */     //   122: athrow
/*     */     //   123: aload_2
/*     */     //   124: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #76	-> 6
/*     */     //   #77	-> 14
/*     */     //   #80	-> 16
/*     */     //   #81	-> 17
/*     */     //   #82	-> 33
/*     */     //   #82	-> 62
/*     */     //   #83	-> 67
/*     */     //   #84	-> 68
/*     */     //   #86	-> 69
/*     */     //   #87	-> 85
/*     */     //   #84	-> 90
/*     */     //   #89	-> 97
/*     */     //   #90	-> 98
/*     */     //   #91	-> 110
/*     */     //   #92	-> 111
/*     */     //   #80	-> 123
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   33	30	2	alpnResult	[B
/*     */     //   68	26	3	e	Ljava/lang/NullPointerException;
/*     */     //   98	12	3	e	Ljava/lang/IllegalAccessException;
/*     */     //   111	12	3	e	Ljava/lang/reflect/InvocationTargetException;
/*     */     //   0	125	0	this	Lokhttp3/internal/platform/android/AndroidSocketAdapter;
/*     */     //   0	125	1	sslSocket	Ljavax/net/ssl/SSLSocket;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   16	64	67	java/lang/NullPointerException
/*     */     //   16	64	97	java/lang/IllegalAccessException
/*     */     //   16	64	110	java/lang/reflect/InvocationTargetException
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\030\020\007\032\0020\b2\016\020\t\032\n\022\006\b\000\022\0020\0130\nH\002J\016\020\f\032\0020\0042\006\020\r\032\0020\016R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\005\020\006¨\006\017"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter$Companion;", "", "()V", "playProviderFactory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getPlayProviderFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "build", "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "actualSSLSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "factory", "packageName", "", "okhttp"})
/*     */   public static final class Companion
/*     */   {
/*     */     private Companion() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final DeferredSocketAdapter.Factory getPlayProviderFactory() {
/*  97 */       return AndroidSocketAdapter.playProviderFactory;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final AndroidSocketAdapter build(Class<? super SSLSocket> actualSSLSocketClass) {
/* 107 */       Class<? super SSLSocket> possibleClass = actualSSLSocketClass;
/* 108 */       while (possibleClass != null && (Intrinsics.areEqual(possibleClass.getSimpleName(), "OpenSSLSocketImpl") ^ true) != 0) {
/* 109 */         possibleClass = possibleClass.getSuperclass();
/*     */         
/* 111 */         if (possibleClass == null) {
/* 112 */           throw new AssertionError(
/* 113 */               "No OpenSSLSocketImpl superclass of socket of type " + actualSSLSocketClass);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 118 */       Intrinsics.checkNotNull(possibleClass); return new AndroidSocketAdapter(possibleClass);
/*     */     }
/*     */     
/*     */     @NotNull
/* 122 */     public final DeferredSocketAdapter.Factory factory(@NotNull String packageName) { Intrinsics.checkNotNullParameter(packageName, "packageName"); return new AndroidSocketAdapter$Companion$factory$1(packageName); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\035\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000*\001\000\b\n\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H\026J\020\020\006\032\0020\0072\006\020\004\032\0020\005H\026¨\006\b"}, d2 = {"okhttp3/internal/platform/android/AndroidSocketAdapter$Companion$factory$1", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "create", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "matchesSocket", "", "okhttp"})
/*     */     public static final class AndroidSocketAdapter$Companion$factory$1 implements DeferredSocketAdapter.Factory { AndroidSocketAdapter$Companion$factory$1(String $captured_local_variable$0) {} public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
/* 124 */         Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullExpressionValue(sslSocket.getClass().getName(), "sslSocket.javaClass.name"); return StringsKt.startsWith$default(sslSocket.getClass().getName(), this.$packageName + '.', false, 2, null);
/*     */       } @NotNull
/*     */       public SocketAdapter create(@NotNull SSLSocket sslSocket) {
/* 127 */         Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); return AndroidSocketAdapter.Companion.build((Class)sslSocket.getClass());
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     playProviderFactory = Companion.factory("com.google.android.gms.org.conscrypt");
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/AndroidSocketAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */