/*     */ package okhttp3.internal.connection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.UnknownServiceException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.ConnectionSpec;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0006\n\002\030\002\n\002\020\000\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\b\000\030\0002\0020\001B\023\022\f\020\002\032\b\022\004\022\0020\0040\003¢\006\002\020\005J\016\020\013\032\0020\0042\006\020\f\032\0020\rJ\016\020\016\032\0020\0072\006\020\017\032\0020\020J\020\020\b\032\0020\0072\006\020\021\032\0020\rH\002R\024\020\002\032\b\022\004\022\0020\0040\003X\004¢\006\002\n\000R\016\020\006\032\0020\007X\016¢\006\002\n\000R\016\020\b\032\0020\007X\016¢\006\002\n\000R\016\020\t\032\0020\nX\016¢\006\002\n\000¨\006\022"}, d2 = {"Lokhttp3/internal/connection/ConnectionSpecSelector;", "", "connectionSpecs", "", "Lokhttp3/ConnectionSpec;", "(Ljava/util/List;)V", "isFallback", "", "isFallbackPossible", "nextModeIndex", "", "configureSecureSocket", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "connectionFailed", "e", "Ljava/io/IOException;", "socket", "okhttp"})
/*     */ public final class ConnectionSpecSelector
/*     */ {
/*     */   private int nextModeIndex;
/*     */   private boolean isFallbackPossible;
/*     */   private boolean isFallback;
/*     */   private final List<ConnectionSpec> connectionSpecs;
/*     */   
/*     */   public ConnectionSpecSelector(@NotNull List<ConnectionSpec> connectionSpecs) {
/*  34 */     this.connectionSpecs = connectionSpecs;
/*     */   }
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
/*     */   @NotNull
/*     */   public final ConnectionSpec configureSecureSocket(@NotNull SSLSocket sslSocket) throws IOException {
/*  49 */     Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); ConnectionSpec tlsConfiguration = (ConnectionSpec)null; int j;
/*  50 */     for (int i = this.nextModeIndex; i < j; i++) {
/*  51 */       ConnectionSpec connectionSpec = this.connectionSpecs.get(i);
/*  52 */       if (connectionSpec.isCompatible(sslSocket)) {
/*  53 */         tlsConfiguration = connectionSpec;
/*  54 */         this.nextModeIndex = i + 1;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  59 */     if (tlsConfiguration == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  65 */       Intrinsics.checkNotNull(sslSocket.getEnabledProtocols()); String[] arrayOfString = sslSocket.getEnabledProtocols(); j = 0; Intrinsics.checkNotNullExpressionValue(Arrays.toString((Object[])arrayOfString), "java.util.Arrays.toString(this)"); throw (Throwable)new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.isFallback + ',' + " modes=" + this.connectionSpecs + ',' + " supported protocols=" + Arrays.toString((Object[])arrayOfString));
/*     */     } 
/*     */     
/*  68 */     this.isFallbackPossible = isFallbackPossible(sslSocket);
/*     */     
/*  70 */     tlsConfiguration.apply$okhttp(sslSocket, this.isFallback);
/*     */     
/*  72 */     return tlsConfiguration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean connectionFailed(@NotNull IOException e) {
/*  83 */     Intrinsics.checkNotNullParameter(e, "e"); this.isFallback = true;
/*     */     
/*  85 */     return 
/*  86 */       !this.isFallbackPossible ? false : (
/*     */ 
/*     */       
/*  89 */       (e instanceof java.net.ProtocolException) ? false : (
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       (e instanceof java.io.InterruptedIOException) ? false : (
/*     */ 
/*     */       
/*  97 */       (e instanceof javax.net.ssl.SSLHandshakeException && e.getCause() instanceof java.security.cert.CertificateException) ? false : (
/*     */ 
/*     */       
/* 100 */       (e instanceof javax.net.ssl.SSLPeerUnverifiedException) ? false : (
/*     */ 
/*     */       
/* 103 */       (e instanceof javax.net.ssl.SSLException))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean isFallbackPossible(SSLSocket socket) {
/* 115 */     for (int i = this.nextModeIndex, j = this.connectionSpecs.size(); i < j; i++) {
/* 116 */       if (((ConnectionSpec)this.connectionSpecs.get(i)).isCompatible(socket)) {
/* 117 */         return true;
/*     */       }
/*     */     } 
/* 120 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/ConnectionSpecSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */