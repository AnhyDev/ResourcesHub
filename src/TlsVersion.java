/*    */ package okhttp3;
/*    */ 
/*    */ import kotlin.Deprecated;
/*    */ import kotlin.DeprecationLevel;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.ReplaceWith;
/*    */ import kotlin.jvm.JvmName;
/*    */ import kotlin.jvm.JvmStatic;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\020\n\000\n\002\020\016\n\002\b\n\b\001\030\000 \f2\b\022\004\022\0020\0000\001:\001\fB\017\b\002\022\006\020\002\032\0020\003¢\006\002\020\004J\r\020\002\032\0020\003H\007¢\006\002\b\006R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\005j\002\b\007j\002\b\bj\002\b\tj\002\b\nj\002\b\013¨\006\r"}, d2 = {"Lokhttp3/TlsVersion;", "", "javaName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "()Ljava/lang/String;", "-deprecated_javaName", "TLS_1_3", "TLS_1_2", "TLS_1_1", "TLS_1_0", "SSL_3_0", "Companion", "okhttp"})
/*    */ public enum TlsVersion {
/*    */   TLS_1_3, TLS_1_2, TLS_1_1, TLS_1_0, SSL_3_0;
/*    */   
/*    */   @JvmStatic
/*    */   @NotNull
/*    */   public static final TlsVersion forJavaName(@NotNull String javaName) {
/*    */     return Companion.forJavaName(javaName);
/*    */   }
/*    */   
/* 22 */   TlsVersion(String javaName) { this.javaName = javaName; } @JvmName(name = "javaName") @NotNull
/* 23 */   public final String javaName() { return this.javaName; }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private final String javaName;
/* 29 */   public static final Companion Companion = new Companion(null);
/*    */ 
/*    */   
/*    */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "javaName"), level = DeprecationLevel.ERROR)
/*    */   @JvmName(name = "-deprecated_javaName")
/*    */   @NotNull
/*    */   public final String -deprecated_javaName() {
/* 36 */     return this.javaName;
/*    */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\003\032\0020\0042\006\020\005\032\0020\006H\007¨\006\007"}, d2 = {"Lokhttp3/TlsVersion$Companion;", "", "()V", "forJavaName", "Lokhttp3/TlsVersion;", "javaName", "", "okhttp"})
/*    */   public static final class Companion { private Companion() {} @JvmStatic
/*    */     @NotNull
/*    */     public final TlsVersion forJavaName(@NotNull String javaName) {
/* 41 */       Intrinsics.checkNotNullParameter(javaName, "javaName"); String str = javaName; switch (str.hashCode())
/*    */       
/*    */       { 
/*    */         
/*    */         case 79201641:
/* 46 */           if (str.equals("SSLv3")); break;case 79923350: if (str.equals("TLSv1")); break;case -503070501: if (str.equals("TLSv1.3")); break;case -503070502: if (str.equals("TLSv1.2")); break;
/* 47 */         case -503070503: if (str.equals("TLSv1.1")); break; }  throw (Throwable)new IllegalArgumentException("Unexpected TLS version: " + javaName);
/*    */     } }
/*    */ 
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/TlsVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */