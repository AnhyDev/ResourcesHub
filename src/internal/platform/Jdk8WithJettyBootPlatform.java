/*     */ package okhttp3.internal.platform;
/*     */ 
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Protocol;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000:\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\004\030\000 \0262\0020\001:\002\025\026B5\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\003\022\n\020\006\032\006\022\002\b\0030\007\022\n\020\b\032\006\022\002\b\0030\007¢\006\002\020\tJ\020\020\n\032\0020\0132\006\020\f\032\0020\rH\026J(\020\016\032\0020\0132\006\020\f\032\0020\r2\b\020\017\032\004\030\0010\0202\f\020\021\032\b\022\004\022\0020\0230\022H\026J\022\020\024\032\004\030\0010\0202\006\020\f\032\0020\rH\026R\022\020\006\032\006\022\002\b\0030\007X\004¢\006\002\n\000R\016\020\004\032\0020\003X\004¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\005\032\0020\003X\004¢\006\002\n\000R\022\020\b\032\006\022\002\b\0030\007X\004¢\006\002\n\000¨\006\027"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform;", "Lokhttp3/internal/platform/Platform;", "putMethod", "Ljava/lang/reflect/Method;", "getMethod", "removeMethod", "clientProviderClass", "Ljava/lang/Class;", "serverProviderClass", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V", "afterHandshake", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "configureTlsExtensions", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "AlpnProvider", "Companion", "okhttp"})
/*     */ public final class Jdk8WithJettyBootPlatform
/*     */   extends Platform
/*     */ {
/*     */   private final Method putMethod;
/*     */   private final Method getMethod;
/*     */   private final Method removeMethod;
/*     */   private final Class<?> clientProviderClass;
/*     */   private final Class<?> serverProviderClass;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public Jdk8WithJettyBootPlatform(@NotNull Method putMethod, @NotNull Method getMethod, @NotNull Method removeMethod, @NotNull Class<?> clientProviderClass, @NotNull Class<?> serverProviderClass) {
/*  32 */     this.putMethod = putMethod; this.getMethod = getMethod; this.removeMethod = removeMethod; this.clientProviderClass = clientProviderClass; this.serverProviderClass = serverProviderClass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
/*  38 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); Intrinsics.checkNotNullParameter(protocols, "protocols"); List<String> names = Platform.Companion.alpnProtocolNames(protocols);
/*     */     
/*     */     try {
/*  41 */       Object alpnProvider = Proxy.newProxyInstance(Platform.class.getClassLoader(), 
/*  42 */           new Class[] { this.clientProviderClass, this.serverProviderClass }, new AlpnProvider(names));
/*  43 */       this.putMethod.invoke(null, new Object[] { sslSocket, alpnProvider });
/*  44 */     } catch (InvocationTargetException e) {
/*  45 */       throw new AssertionError("failed to set ALPN", (Throwable)e);
/*  46 */     } catch (IllegalAccessException e) {
/*  47 */       throw new AssertionError("failed to set ALPN", (Throwable)e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void afterHandshake(@NotNull SSLSocket sslSocket) {
/*  52 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); try {
/*  53 */       this.removeMethod.invoke(null, new Object[] { sslSocket });
/*  54 */     } catch (IllegalAccessException e) {
/*  55 */       throw new AssertionError("failed to remove ALPN", (Throwable)e);
/*  56 */     } catch (InvocationTargetException e) {
/*  57 */       throw new AssertionError("failed to remove ALPN", (Throwable)e);
/*     */     } 
/*     */   }
/*     */   @Nullable
/*     */   public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
/*  62 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); try {
/*  63 */       if (Proxy.getInvocationHandler(this.getMethod.invoke(null, new Object[] { sslSocket })) == null) throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");  AlpnProvider provider = (AlpnProvider)Proxy.getInvocationHandler(this.getMethod.invoke(null, new Object[] { sslSocket }));
/*  64 */       if (!provider.getUnsupported() && provider.getSelected() == null) {
/*  65 */         Platform.log$default(this, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 0, null, 6, null);
/*  66 */         return null;
/*     */       } 
/*  68 */       return provider.getUnsupported() ? null : provider.getSelected();
/*  69 */     } catch (InvocationTargetException e) {
/*  70 */       throw new AssertionError("failed to get ALPN selected protocol", (Throwable)e);
/*  71 */     } catch (IllegalAccessException e) {
/*  72 */       throw new AssertionError("failed to get ALPN selected protocol", (Throwable)e);
/*     */     } 
/*     */   }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\030\002\n\000\n\002\020 \n\002\020\016\n\002\b\007\n\002\020\013\n\002\b\005\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\021\n\002\b\002\b\002\030\0002\0020\001B\023\022\f\020\002\032\b\022\004\022\0020\0040\003¢\006\002\020\005J0\020\021\032\004\030\0010\0222\006\020\023\032\0020\0222\006\020\024\032\0020\0252\016\020\026\032\n\022\004\022\0020\022\030\0010\027H\002¢\006\002\020\030R\024\020\002\032\b\022\004\022\0020\0040\003X\004¢\006\002\n\000R\034\020\006\032\004\030\0010\004X\016¢\006\016\n\000\032\004\b\007\020\b\"\004\b\t\020\nR\032\020\013\032\0020\fX\016¢\006\016\n\000\032\004\b\r\020\016\"\004\b\017\020\020¨\006\031"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$AlpnProvider;", "Ljava/lang/reflect/InvocationHandler;", "protocols", "", "", "(Ljava/util/List;)V", "selected", "getSelected", "()Ljava/lang/String;", "setSelected", "(Ljava/lang/String;)V", "unsupported", "", "getUnsupported", "()Z", "setUnsupported", "(Z)V", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "okhttp"})
/*     */   private static final class AlpnProvider implements InvocationHandler { private boolean unsupported; @Nullable
/*     */     private String selected; private final List<String> protocols;
/*     */     
/*     */     public AlpnProvider(@NotNull List<String> protocols) {
/*  80 */       this.protocols = protocols;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean getUnsupported() {
/*  85 */       return this.unsupported; } public final void setUnsupported(boolean <set-?>) { this.unsupported = <set-?>; }
/*     */     @Nullable
/*  87 */     public final String getSelected() { return this.selected; } public final void setSelected(@Nullable String <set-?>) { this.selected = <set-?>; }
/*     */     
/*     */     @Nullable
/*     */     public Object invoke(@NotNull Object proxy, @NotNull Method method, @Nullable Object[] args) throws Throwable {
/*  91 */       Intrinsics.checkNotNullParameter(proxy, "proxy"); Intrinsics.checkNotNullParameter(method, "method"); if (args == null); Object[] callArgs = new Object[0];
/*  92 */       String methodName = method.getName();
/*  93 */       Class<?> returnType = method.getReturnType();
/*  94 */       if (Intrinsics.areEqual(methodName, "supports") && Intrinsics.areEqual(boolean.class, returnType))
/*  95 */         return Boolean.valueOf(true); 
/*  96 */       if (Intrinsics.areEqual(methodName, "unsupported") && Intrinsics.areEqual(void.class, returnType)) {
/*  97 */         this.unsupported = true;
/*  98 */         return null;
/*  99 */       }  if (Intrinsics.areEqual(methodName, "protocols")) { Object[] arrayOfObject = callArgs; boolean bool = false; if ((arrayOfObject.length == 0))
/* 100 */           return this.protocols;  }
/*     */       
/* 102 */       if ((Intrinsics.areEqual(methodName, "selectProtocol") || Intrinsics.areEqual(methodName, "select")) && Intrinsics.areEqual(String.class, returnType) && callArgs.length == 1 && callArgs[0] instanceof List) {
/* 103 */         if (callArgs[0] == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");  List peerProtocols = (List)callArgs[0];
/*     */         
/* 105 */         byte b = 0; int i = peerProtocols.size(); if (b <= i)
/* 106 */           while (true) { if (peerProtocols.get(b) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.String");  String protocol = (String)peerProtocols.get(b);
/* 107 */             if (this.protocols.contains(protocol)) {
/* 108 */               this.selected = protocol;
/* 109 */               return this.selected;
/*     */             }  if (b != i) { b++; continue; }
/*     */              break; }
/* 112 */             this.selected = this.protocols.get(0);
/* 113 */         return this.selected;
/* 114 */       }  if ((Intrinsics.areEqual(methodName, "protocolSelected") || Intrinsics.areEqual(methodName, "selected")) && callArgs.length == 1) {
/* 115 */         if (callArgs[0] == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.String");  this.selected = (String)callArgs[0];
/* 116 */         return null;
/*     */       } 
/* 118 */       return method.invoke(this, Arrays.copyOf(callArgs, callArgs.length));
/*     */     } }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\004\030\0010\004¨\006\005"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"})
/*     */   public static final class Companion { private Companion() {}
/*     */     
/*     */     @Nullable
/*     */     public final Platform buildIfSupported() {
/* 125 */       String jvmVersion = System.getProperty("java.specification.version", "unknown");
/*     */       
/*     */       try {
/* 128 */         Intrinsics.checkNotNullExpressionValue(jvmVersion, "jvmVersion"); String str = jvmVersion; boolean bool = false; int version = Integer.parseInt(str);
/* 129 */         if (version >= 9) return null; 
/* 130 */       } catch (NumberFormatException numberFormatException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 136 */       try { String alpnClassName = "org.eclipse.jetty.alpn.ALPN";
/* 137 */         Class<?> alpnClass = Class.forName(alpnClassName, true, null);
/* 138 */         Class<?> providerClass = Class.forName(alpnClassName + "$Provider", true, null);
/* 139 */         Class<?> clientProviderClass = Class.forName(alpnClassName + "$ClientProvider", true, null);
/* 140 */         Class<?> serverProviderClass = Class.forName(alpnClassName + "$ServerProvider", true, null);
/* 141 */         Method putMethod = alpnClass.getMethod("put", new Class[] { SSLSocket.class, providerClass });
/* 142 */         Method getMethod = alpnClass.getMethod("get", new Class[] { SSLSocket.class });
/* 143 */         Method removeMethod = alpnClass.getMethod("remove", new Class[] { SSLSocket.class });
/*     */         
/* 145 */         Intrinsics.checkNotNullExpressionValue(putMethod, "putMethod"); Intrinsics.checkNotNullExpressionValue(getMethod, "getMethod"); Intrinsics.checkNotNullExpressionValue(removeMethod, "removeMethod"); Intrinsics.checkNotNullExpressionValue(clientProviderClass, "clientProviderClass"); Intrinsics.checkNotNullExpressionValue(serverProviderClass, "serverProviderClass"); return new Jdk8WithJettyBootPlatform(putMethod, getMethod, removeMethod, clientProviderClass, serverProviderClass); }
/* 146 */       catch (ClassNotFoundException classNotFoundException) {  }
/* 147 */       catch (NoSuchMethodException noSuchMethodException) {}
/*     */ 
/*     */       
/* 150 */       return null;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/Jdk8WithJettyBootPlatform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */